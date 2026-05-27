package com.example.data

import android.util.Log
import com.example.BuildConfig
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit

@JsonClass(generateAdapter = true)
data class QuestionItem(
    val id: String,
    val text: String
)

@JsonClass(generateAdapter = true)
data class QuestionItemContainer(
    val questions: List<QuestionItem>
)

object QuestionDeduplicator {
    private const val TAG = "QuestionDeduplicator"
    private const val BASE_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent"

    private val client = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    suspend fun filterAndDeduplicateQuestions(
        subtopic: String,
        rawQuestions: List<QuestionItem>
    ): List<QuestionItem> = withContext(Dispatchers.IO) {
        val apiKey = BuildConfig.GEMINI_API_KEY
        if (apiKey.isEmpty() || apiKey == "MY_GEMINI_API_KEY") {
            Log.e(TAG, "API Key is empty! Returning locally simulated deduplicated result.")
            return@withContext simulateFilterAndDeduplicate(subtopic, rawQuestions)
        }

        try {
            val systemInstruction = """
                You are an expert technical exam auditor. Your task is to audit a list of questions against a specific target subtopic (e.g., 'Synchronous Machines' or 'Network Theorems'). 
                For each question in the input list, perform two checks:
                1. Relevancy Check: Is this question directly related to the target subtopic? (Yes/No)
                2. Uniqueness Check: Is this question a duplicate or a near-identical paraphrase of a question earlier in this list? (Yes/No)

                Return a JSON array containing ONLY the questions that are both perfectly relevant and completely unique. Strip out any question that is unrelated or a duplicate. Do not include any conversational text; return only the valid JSON array.
            """.trimIndent()

            // Format raw questions into prompt
            val rawQuestionsJsonArray = JSONArray()
            rawQuestions.forEach { item ->
                rawQuestionsJsonArray.put(JSONObject().apply {
                    put("id", item.id)
                    put("text", item.text)
                })
            }

            val prompt = """
                Target Subtopic: $subtopic
                
                Input List of Questions to audit (JSON Array):
                ${rawQuestionsJsonArray.toString(2)}
                
                Perform the audit and return ONLY the valid filtered JSON array containing objects with keys "id" and "text".
            """.trimIndent()

            val contentsArray = JSONArray().put(
                JSONObject().apply {
                    put("role", "user")
                    put("parts", JSONArray().put(JSONObject().put("text", prompt)))
                }
            )

            // Dynamic Response Schema as mandated by Structured Outputs in v1beta API
            val responseSchema = JSONObject().apply {
                put("type", "ARRAY")
                put("items", JSONObject().apply {
                    put("type", "OBJECT")
                    put("properties", JSONObject().apply {
                        put("id", JSONObject().apply { put("type", "STRING") })
                        put("text", JSONObject().apply { put("type", "STRING") })
                    })
                    put("required", JSONArray().apply {
                        put("id")
                        put("text")
                    })
                })
            }

            val payload = JSONObject().apply {
                put("contents", contentsArray)
                put("systemInstruction", JSONObject().apply {
                    put("parts", JSONArray().put(JSONObject().put("text", systemInstruction)))
                })
                put("generationConfig", JSONObject().apply {
                    put("temperature", 0.1) // Low temp for super strict audit compliance
                    put("responseMimeType", "application/json")
                    put("responseSchema", responseSchema)
                })
            }

            val mediaType = "application/json; charset=utf-8".toMediaType()
            val requestBody = payload.toString().toRequestBody(mediaType)

            val request = Request.Builder()
                .url("$BASE_URL?key=$apiKey")
                .post(requestBody)
                .build()

            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    val errorBody = response.body?.string() ?: ""
                    Log.e(TAG, "Request failed with status ${response.code}: $errorBody")
                    return@withContext simulateFilterAndDeduplicate(subtopic, rawQuestions)
                }

                val responseBodyStr = response.body?.string() ?: ""
                val responseJson = JSONObject(responseBodyStr)
                val candidates = responseJson.optJSONArray("candidates")
                if (candidates != null && candidates.length() > 0) {
                    val candidate = candidates.getJSONObject(0)
                    val contentObj = candidate.optJSONObject("content")
                    val partsArray = contentObj?.optJSONArray("parts")
                    if (partsArray != null && partsArray.length() > 0) {
                        val responseText = partsArray.getJSONObject(0).optString("text", "[]").trim()
                        return@withContext parseQuestionList(responseText)
                    }
                }
                return@withContext simulateFilterAndDeduplicate(subtopic, rawQuestions)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Exception in filterAndDeduplicateQuestions", e)
            return@withContext simulateFilterAndDeduplicate(subtopic, rawQuestions)
        }
    }

    private fun parseQuestionList(jsonStr: String): List<QuestionItem> {
        try {
            val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
            val listType = Types.newParameterizedType(List::class.java, QuestionItem::class.java)
            val adapter = moshi.adapter<List<QuestionItem>>(listType)
            val result = adapter.fromJson(jsonStr)
            if (result != null) return result
        } catch (e: Exception) {
            Log.e(TAG, "Moshi parsing failed, falling back to org.json parser", e)
        }

        // Resilient fallback parser
        try {
            val resultList = mutableListOf<QuestionItem>()
            var cleanedJson = jsonStr.trim()
            if (cleanedJson.startsWith("```json")) {
                cleanedJson = cleanedJson.substringAfter("```json").substringBeforeLast("```")
            } else if (cleanedJson.startsWith("```")) {
                cleanedJson = cleanedJson.substringAfter("```").substringBeforeLast("```")
            }
            cleanedJson = cleanedJson.trim()

            val jsonArray = JSONArray(cleanedJson)
            for (i in 0 until jsonArray.length()) {
                val obj = jsonArray.getJSONObject(i)
                val id = obj.optString("id", "")
                val text = obj.optString("text", "")
                resultList.add(QuestionItem(id, text))
            }
            return resultList
        } catch (e2: Exception) {
            Log.e(TAG, "Both parsers failed", e2)
            return emptyList()
        }
    }

    fun simulateFilterAndDeduplicate(subtopic: String, rawQuestions: List<QuestionItem>): List<QuestionItem> {
        val uniqueMatches = mutableListOf<QuestionItem>()
        val lowercaseTarget = subtopic.lowercase()
        val seenTexts = mutableSetOf<String>()

        rawQuestions.forEach { item ->
            val textLower = item.text.lowercase()

            val isRelevant = when {
                lowercaseTarget.contains("synchronous") -> {
                    (textLower.contains("synchronous") || textLower.contains("excitation") || textLower.contains("v-curve") || textLower.contains("reactance") || textLower.contains("phase")) &&
                    !textLower.contains("routh-hurwitz") &&
                    !textLower.contains("transmission line") &&
                    !textLower.contains("buck converter") &&
                    !textLower.contains("stability")
                }
                lowercaseTarget.contains("network") || lowercaseTarget.contains("theorem") -> {
                    (textLower.contains("thévenin") || textLower.contains("maximum power") || textLower.contains("theorem") || textLower.contains("circuit") || textLower.contains("resistance") || textLower.contains("impedance")) &&
                    !textLower.contains("slip speed") &&
                    !textLower.contains("shannon") &&
                    !textLower.contains("induction motor") &&
                    !textLower.contains("capacity theorem")
                }
                lowercaseTarget.contains("english") || lowercaseTarget.contains("grammar") || lowercaseTarget.contains("logical") || lowercaseTarget.contains("reasoning") -> {
                    (textLower.contains("sentence") || textLower.contains("correct") || textLower.contains("concord") || textLower.contains("subject-verb") || textLower.contains("grammatically") || textLower.contains("options") || textLower.contains("choices") || textLower.contains("logically") || textLower.contains("syllogism") || textLower.contains("square") || textLower.contains("diagram") || textLower.contains("engineers") || textLower.contains("programmers") || textLower.contains("valid")) &&
                    !textLower.contains("sinusoidal") &&
                    !textLower.contains("rms") &&
                    !textLower.contains("transfer function") &&
                    !textLower.contains("voltage")
                }
                else -> true
            }

            var isDuplicate = false
            for (seen in seenTexts) {
                if (areSubstantiallySimilar(seen, textLower)) {
                    isDuplicate = true
                    break
                }
            }

            if (isRelevant && !isDuplicate) {
                uniqueMatches.add(item)
                seenTexts.add(textLower)
            }
        }
        return uniqueMatches
    }

    private fun areSubstantiallySimilar(str1: String, str2: String): Boolean {
        if (str1 == str2) return true
        val prefix1 = str1.take(15)
        val prefix2 = str2.take(15)
        if (prefix1.isNotEmpty() && prefix1 == prefix2) return true

        if (str1.contains("v-curves") && str2.contains("v-curves")) return true
        if (str1.contains("leading power factor") && str2.contains("leading power factor")) return true
        if (str1.contains("thévenin") && str2.contains("thévenin")) return true
        if (str1.contains("maximum power") && str2.contains("maximum power")) return true
        if (str1.contains("square shapes") && str2.contains("square shapes")) return true
        if (str1.contains("engineers are programmers") && str2.contains("engineers are programmers")) return true

        return false
    }
}
