package com.example.data

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.json.JSONArray

class GateRepository(context: Context) {

    private val db = AppDatabase.getDatabase(context)
    private val doubtDao = db.doubtMessageDao()
    private val progressDao = db.subtopicProgressDao()
    private val savedDao = db.savedQuestionDao()
    private val statsDao = db.userStatsDao()

    // --- Static Syllabus Access ---
    val subjects: List<Subject> = GateSyllabus.subjects

    fun getSubjectById(subjectId: String): Subject? =
        subjects.find { it.id == subjectId }

    fun getTopicById(subjectId: String, topicId: String): Topic? =
        getSubjectById(subjectId)?.topics?.find { it.id == topicId }

    fun getSubtopicById(subjectId: String, topicId: String, subtopicId: String): Subtopic? =
        getTopicById(subjectId, topicId)?.subtopics?.find { it.id == subtopicId }

    // --- Doubt Support History ---
    fun getMessagesForSubtopic(subtopicId: String): Flow<List<DoubtMessageEntity>> =
        doubtDao.getMessagesForSubtopic(subtopicId)

    suspend fun insertMessage(subtopicId: String, role: String, text: String) {
        doubtDao.insertMessage(
            DoubtMessageEntity(subtopicId = subtopicId, role = role, text = text)
        )
    }

    suspend fun clearChatHistory(subtopicId: String) {
        doubtDao.clearHistory(subtopicId)
    }

    // --- Progress Tracking ---
    val allProgress: Flow<List<SubtopicProgressEntity>> = progressDao.getAllProgress()

    suspend fun updateProgress(
        subtopicId: String,
        subjectId: String,
        topicId: String,
        isCompleted: Boolean,
        scorePercent: Int,
        timeSpentAddSeconds: Long,
        questionsSolvedAdd: Int
    ) {
        val existing = progressDao.getProgressForSubtopic(subtopicId)
        val newProgress = SubtopicProgressEntity(
            subtopicId = subtopicId,
            subjectId = subjectId,
            topicId = topicId,
            isCompleted = isCompleted || (existing?.isCompleted ?: false),
            scorePercent = maxOf(scorePercent, existing?.scorePercent ?: 0),
            timeSpentSeconds = (existing?.timeSpentSeconds ?: 0) + timeSpentAddSeconds,
            questionsAttempted = (existing?.questionsAttempted ?: 0) + questionsSolvedAdd,
            lastStudiedAt = System.currentTimeMillis()
        )
        progressDao.saveProgress(newProgress)
    }

    // --- Bookmarks & Mistakes Notebook ---
    val bookmarkedQuestions: Flow<List<SavedQuestionEntity>> = savedDao.getBookmarks()
    val mistakeNotebookQuestions: Flow<List<SavedQuestionEntity>> = savedDao.getMistakeNotebook()

    suspend fun isQuestionBookmarked(questionId: String): Boolean {
        val saved = savedDao.getSavedQuestion(questionId)
        return saved != null && !saved.isMistakeNotebook
    }

    suspend fun toggleBookmark(question: GateQuestion, isBookmarked: Boolean) {
        if (isBookmarked) {
            val entity = SavedQuestionEntity(
                questionId = question.id,
                subjectId = question.subjectId,
                topicId = question.topicId,
                subtopicId = question.subtopicId,
                questionText = question.questionText,
                questionType = question.questionType.name,
                optionsJson = question.options?.let { JSONArray(it).toString() } ?: "[]",
                correctOptionsJson = question.correctOptions?.let { JSONArray(it).toString() } ?: "[]",
                explanation = question.explanation,
                isMistakeNotebook = false
            )
            savedDao.saveQuestion(entity)
        } else {
            savedDao.deleteById(question.id, isMistake = false)
        }
    }

    suspend fun addWrongAnswerToMistakes(question: GateQuestion, userNotes: String = "") {
        val entity = SavedQuestionEntity(
            questionId = question.id,
            subjectId = question.subjectId,
            topicId = question.topicId,
            subtopicId = question.subtopicId,
            questionText = question.questionText,
            questionType = question.questionType.name,
            optionsJson = question.options?.let { JSONArray(it).toString() } ?: "[]",
            correctOptionsJson = question.correctOptions?.let { JSONArray(it).toString() } ?: "[]",
            explanation = question.explanation,
            isMistakeNotebook = true,
            userNotes = if (userNotes.isNotEmpty()) userNotes else "Incorrect attempt on practice test."
        )
        savedDao.saveQuestion(entity)
    }

    suspend fun removeMistake(questionId: String) {
        savedDao.deleteById(questionId, isMistake = true)
    }

    // --- Streak & Stat Tracking ---
    val userStats: Flow<UserStatsEntity> = statsDao.getUserStatsFlow().map {
        it ?: UserStatsEntity()
    }

    suspend fun addXp(amount: Int) {
        val current = statsDao.getUserStats() ?: UserStatsEntity()
        val updated = current.copy(totalXp = current.totalXp + amount)
        statsDao.saveUserStats(updated)
    }

    suspend fun checkAndUpdateStreak() {
        val current = statsDao.getUserStats() ?: UserStatsEntity()
        val now = System.currentTimeMillis()
        val diff = now - current.lastActiveDay
        val millisecondsInDay = 24 * 60 * 60 * 1000L

        val updated = when {
            diff < millisecondsInDay -> {
                // Same day active, no streak update needed
                current
            }
            diff < (2 * millisecondsInDay) -> {
                // Active next consecutive day, increment streak and reward XP!
                current.copy(
                    streak = current.streak + 1,
                    totalXp = current.totalXp + 50,
                    lastActiveDay = now
                )
            }
            else -> {
                // Reset streak, update active day
                current.copy(
                    streak = 1,
                    lastActiveDay = now
                )
            }
        }
        statsDao.saveUserStats(updated)
    }

    suspend fun addTimeSpent(minutes: Int) {
        val current = statsDao.getUserStats() ?: UserStatsEntity()
        val updated = current.copy(timeSpentMinutes = current.timeSpentMinutes + minutes)
        statsDao.saveUserStats(updated)
    }
}
