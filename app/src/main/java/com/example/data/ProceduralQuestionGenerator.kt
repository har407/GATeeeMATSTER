package com.example.data

import java.util.Random
import java.util.Locale

object ProceduralQuestionGenerator {

    /**
     * Generates up to `count` (e.g., 100) highly distinct questions for any subtopic.
     * Uses a stable seed based on the subtopicId and index `i` to ensure that questions
     * are completely deterministic and mathematically consistent on every reload.
     */
    fun generateQuestions(
        subjectId: String,
        topicId: String,
        subtopicId: String,
        subtopicName: String,
        count: Int = 100
    ): List<GateQuestion> {
        val questions = mutableListOf<GateQuestion>()

        for (i in 0 until count) {
            val seed = (subtopicId.hashCode() + i * 31).toLong()
            val rand = Random(seed)

            // Select question type: MCQ, MSQ, NAT
            val questionType = when (i % 3) {
                0 -> QuestionType.MCQ
                1 -> QuestionType.MSQ
                else -> QuestionType.NAT
            }

            val difficulty = when (i % 5) {
                0, 1 -> "Easy"
                2, 3 -> "Medium"
                else -> "Hard"
            }

            val year = 2020 + rand.nextInt(7) // 2020 to 2026

            val question = when {
                subjectId == "general_aptitude" -> {
                    generateAptitudeQuestion(subjectId, topicId, subtopicId, subtopicName, i, year, questionType, difficulty, rand)
                }
                subjectId == "reasoning" -> {
                    generateReasoningQuestion(subjectId, topicId, subtopicId, subtopicName, i, year, questionType, difficulty, rand)
                }
                subjectId == "engineering_math" -> {
                    generateMathQuestion(subjectId, topicId, subtopicId, subtopicName, i, year, questionType, difficulty, rand)
                }
                subjectId == "control_systems" -> {
                    generateControlSystemsQuestion(subjectId, topicId, subtopicId, subtopicName, i, year, questionType, difficulty, rand)
                }
                else -> {
                    generateElectricalQuestion(subjectId, topicId, subtopicId, subtopicName, i, year, questionType, difficulty, rand)
                }
            }

            questions.add(question)
        }

        return questions
    }

    private fun generateAptitudeQuestion(
        subjectId: String,
        topicId: String,
        subtopicId: String,
        subtopicName: String,
        index: Int,
        year: Int,
        type: QuestionType,
        difficulty: String,
        rand: Random
    ): GateQuestion {
        return when {
            subtopicId == "apt_verb_grammar_usage" || subtopicId.startsWith("apt_verb_") -> {
                generateVerbalAptitudeQuestion(subjectId, topicId, subtopicId, subtopicName, index, year, type, difficulty, rand)
            }
            subtopicId == "apt_numerical_computation" || subtopicId.startsWith("apt_quant_") -> {
                generateQuantAptitudeQuestion(subjectId, topicId, subtopicId, subtopicName, index, year, type, difficulty, rand)
            }
            subtopicId == "apt_analytical_reasoning" || subtopicId.startsWith("apt_anal_") -> {
                generateAnalyticalAptitudeQuestion(subjectId, topicId, subtopicId, subtopicName, index, year, type, difficulty, rand)
            }
            subtopicId == "apt_spatial_mirroring" || subtopicId.startsWith("apt_spatial_") -> {
                generateSpatialAptitudeQuestion(subjectId, topicId, subtopicId, subtopicName, index, year, type, difficulty, rand)
            }
            else -> {
                generateDefaultAptitudeQuestion(subjectId, topicId, subtopicId, subtopicName, index, year, type, difficulty, rand)
            }
        }
    }

    private fun generateVerbalAptitudeQuestion(
        subjectId: String, topicId: String, subtopicId: String, subtopicName: String,
        idx: Int, year: Int, type: QuestionType, difficulty: String, rand: Random
    ): GateQuestion {
        val qId = "proc_apt_${subtopicId}_$idx"
        return when (subtopicId) {
            "apt_verb_vocab", "apt_verb_completion" -> {
                val words = listOf(
                    Triple("benevolent", "generous and kind", "malicious"),
                    Triple("capricious", "unpredictable and impulsive", "stable"),
                    Triple("ephemeral", "short-lived and fleeting", "perpetual"),
                    Triple("obdurate", "stubborn and unyielding", "flexible")
                )
                val sel = words[idx % words.size]
                if (type == QuestionType.MCQ) {
                    val qText = if (subtopicId == "apt_verb_vocab") {
                        "Determine the closest synonym for the word \"${sel.first}\" in standard academic writing."
                    } else {
                        "Complete the sentence contextually: \"The philanthropist's ________ actions were celebrated by the entire community.\""
                    }
                    val correct = if (subtopicId == "apt_verb_vocab") sel.second else "benevolent"
                    val wrongOpt = if (subtopicId == "apt_verb_vocab") sel.third else "capricious"
                    val options = listOf(correct, wrongOpt, "strictly professional", "aggressive").shuffled(rand)
                    val correctIdx = options.indexOf(correct)
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = options, correctOptions = listOf(correctIdx),
                        explanation = "By vocabulary definition, '${sel.first}' means '${sel.second}'. Thus the correct answer is '$correct'.",
                        formulasUsed = "Verbal semantics", shortcutTricks = "Analyze word prefix/suffix context.",
                        relatedConcepts = "Vocabulary, Syllogistic logic", difficulty = difficulty
                    )
                } else if (type == QuestionType.MSQ) {
                    val qText = "Select all of the options that are synonyms or related words of \"${sel.first}\":"
                    val options = listOf(sel.second.split(" and ")[0], sel.second.split(" and ")[1], "altruistic", sel.third)
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = options, correctOptions = listOf(0, 1, 2),
                        explanation = "The words are synonyms of '${sel.first}', whereas '${sel.third}' is an antonym.",
                        formulasUsed = "Semantic matching", shortcutTricks = "Identify related conceptual terms.",
                        relatedConcepts = "Synonyms matching", difficulty = difficulty
                    )
                } else {
                    val ans = sel.first.length.toDouble()
                    val qText = "In the sentence completion task, how many letters are there in the word \"${sel.first.uppercase()}\"?"
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = null, correctOptions = null,
                        correctNumericalRange = (ans - 0.01)..(ans + 0.01),
                        explanation = "Counting the characters in '${sel.first.uppercase()}' yields exactly ${sel.first.length} characters.",
                        formulasUsed = "Character mapping count", shortcutTricks = "Examine spelling and count precisely.",
                        relatedConcepts = "Grammar lexicon rules", difficulty = difficulty
                    )
                }
            }
            "apt_verb_reading", "apt_verb_critical" -> {
                if (type == QuestionType.MCQ) {
                    val qText = """
                        Read the technical excerpt:
                        "Although parallel computing provides high throughput for matrix multiplications, power dissipation limits the deployment scale."
                        Which statement represents the main thesis or logical conclusion?
                    """.trimIndent()
                    val options = listOf(
                        "Deployment scale is limited by power dissipation despite superior parallel computing speedups.",
                        "Matrix multiplications cannot be processed concurrently.",
                        "Power dissipation is completely irrelevant to high throughput operations.",
                        "Parallel computing guarantees zero thermal limits."
                    ).shuffled(rand)
                    val correctIdx = options.indexOf("Deployment scale is limited by power dissipation despite superior parallel computing speedups.")
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = options, correctOptions = listOf(correctIdx),
                        explanation = "The passage contrasts the high-throughput benefits of parallel computing against physical limitations in power dissipation.",
                        formulasUsed = "Logical thesis parsing", shortcutTricks = "Look for contrast markers such as 'Although' or 'But' to locate main arguments.",
                        relatedConcepts = "Critical reading, theme extraction", difficulty = difficulty
                    )
                } else if (type == QuestionType.MSQ) {
                    val qText = "Select all of the logically valid deductions supported by the premises: \"All compilers translate source files. Some compilers optimize loops.\""
                    val options = listOf(
                        "There exists a translation tool that optimizes loops.",
                        "All tools that optimize loops are translators.",
                        "Some compilers that translate source files do not optimize loops is possible.",
                        "No compiler translates source files."
                    )
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = options, correctOptions = listOf(0, 1, 2),
                        explanation = "Since all compilers translate, any compiler that optimizes loops must also be a translator.",
                        formulasUsed = "Syllogistic logic parsing", shortcutTricks = "Use containment: loop optimizers are a subset of compilers or intersect. Compilers are subset of translators.",
                        relatedConcepts = "Deductive validity", difficulty = difficulty
                    )
                } else {
                    val qText = """
                        Let Statement A be "All compilers optimize loops" and Statement B be "Some compilers optimize loops".
                        If Statement A is True, what is the logical truth value of Statement B (1.0 for True, 0.0 for False)?
                    """.trimIndent()
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = null, correctOptions = null,
                        correctNumericalRange = 0.99..1.01,
                        explanation = "By subalternation rules in standard categorical logic, if the universal affirmative is true, the particular affirmative must also be true.",
                        formulasUsed = "Categorical subalternation", shortcutTricks = "If all items of a set possess a trait, any non-empty subpart also possesses it.",
                        relatedConcepts = "Syllogisms, Boolean states", difficulty = difficulty
                    )
                }
            }
            "apt_verb_analogies", "apt_verb_word_groups", "apt_verb_narrative_seq" -> {
                if (type == QuestionType.MCQ) {
                    val qText = "Identify the analogical relationship: COAL : LOCOMOTIVE :: ________ : ________"
                    val options = listOf("ELECTRICITY : MOTOR", "WIND : SAIL", "WATER : PIPELINE", "FUEL : FILTER").shuffled(rand)
                    val correctIdx = options.indexOf("ELECTRICITY : MOTOR")
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = options, correctOptions = listOf(correctIdx),
                        explanation = "Coal acts as the primary energy source powering locomotives, and electricity acts as the energy source powering motors.",
                        formulasUsed = "Analogy relations", shortcutTricks = "Map the functional relation: '[EnergySource] powers the [Device]'.",
                        relatedConcepts = "Analogical arguments, matching relations", difficulty = difficulty
                    )
                } else if (type == QuestionType.MSQ) {
                    val qText = "Select all of the options containing word groups representing mechanical parts of a macroscopic whole (PART : WHOLE relation):"
                    val options = listOf("SPOKE : WHEEL", "LEAF : TREE", "ENGINE : CAR", "WATER : PIPELINE")
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = options, correctOptions = listOf(0, 1, 2),
                        explanation = "A spoke is part of a wheel, leaf is part of a tree, and engine is part of a car.",
                        formulasUsed = "Semantic taxonomy", shortcutTricks = "Identify physical components vs containers.",
                        relatedConcepts = "Analogy associations", difficulty = difficulty
                    )
                } else {
                    val qText = """
                        In the analogy LIGHT : BLIND :: SOUND : [?], 
                        count the number of characters in the 4-letter response representing the corresponding sensory impairment.
                    """.trimIndent()
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = null, correctOptions = null,
                        correctNumericalRange = 3.99..4.01,
                        explanation = "The response is 'DEAF', which has exactly 4 characters.",
                        formulasUsed = "Analogy spelling mapping", shortcutTricks = "Compute the 4-letter synonym length.",
                        relatedConcepts = "Verbal Analogies", difficulty = difficulty
                    )
                }
            }
            else -> {
                val wordList = listOf(
                    Pair("benevolent", "generous and kind"),
                    Pair("capricious", "unpredictable and impulsive"),
                    Pair("ephemeral", "short-lived and fleeting"),
                    Pair("obdurate", "stubborn and unyielding")
                )
                val select = wordList[idx % wordList.size]
                if (type == QuestionType.MCQ) {
                    val qText = """
                        Select the option that represents the closest synonym for the word "${select.first}" under modern formal sentence usage.
                        Sentence: "The CEO was known for her ${select.first} actions during structural reorganizations."
                    """.trimIndent()
                    val options = listOf(select.second, "unreliable and slow", "strictly professional and formal", "aggressive and profit-focused").shuffled(rand)
                    val correctIndex = options.indexOf(select.second)
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = options, correctOptions = listOf(correctIndex),
                        explanation = "The word '${select.first}' is defined as being ${select.second}, which fits the contextual synonym usage in formal business writing.",
                        formulasUsed = "Verbal Lexicon rules", shortcutTricks = "Replace other options in the sentence.",
                        relatedConcepts = "Vocabulary, Grammar usage", difficulty = difficulty
                    )
                } else if (type == QuestionType.MSQ) {
                    val qText = "Identify which of the following English sentences are grammatically correct in formal writing: (Select all that apply)"
                    val options = listOf(
                        "Neither the principal nor the teachers are attending the annual summit.",
                        "If she had registered on time, she would have received the confirmation code.",
                        "Each of the candidates has submitted their reference statement.",
                        "In spite of his fatigue, he continued to compile the simulation data."
                    )
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = options, correctOptions = listOf(0, 1, 3),
                        explanation = "Option A is correct ('teachers are'). Option B is a valid third conditional frame. Option D is a valid phrase.",
                        formulasUsed = "English grammar concord", shortcutTricks = "Verify nearest noun subject-verb agreement.",
                        relatedConcepts = "Subject-verb agreement", difficulty = difficulty
                    )
                } else {
                    val qText = """
                        Read the sentence: "To write code efficiently requires focus and strict discipline."
                        Let N represent the total number of words in this sentence (excluding any punctuation).
                        Compute the exact numerical value of N.
                    """.trimIndent()
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = null, correctOptions = null,
                        correctNumericalRange = 8.99..9.01,
                        explanation = "Counting the words in the sentence yields exactly 9 words.",
                        formulasUsed = "Sentence parsing count", shortcutTricks = "Identify each word unit precisely.",
                        relatedConcepts = "Textual parsing", difficulty = difficulty
                    )
                }
            }
        }
    }

    private fun generateQuantAptitudeQuestion(
        subjectId: String, topicId: String, subtopicId: String, subtopicName: String,
        idx: Int, year: Int, type: QuestionType, difficulty: String, rand: Random
    ): GateQuestion {
        val qId = "proc_apt_${subtopicId}_$idx"
        return when (subtopicId) {
            "apt_quant_ratios", "apt_quant_percentages", "apt_quant_profit_loss" -> {
                if (type == QuestionType.MCQ) {
                    val qText = "Under \"$subtopicName\", if the price of a component is increased by 25%, by what percentage must a factory reduce utilization of the component to keep its overall budget unchanged?"
                    val options = listOf("20%", "25%", "15%", "33.33%").shuffled(rand)
                    val correctIdx = options.indexOf("20%")
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = options, correctOptions = listOf(correctIdx),
                        explanation = "If price becomes 1.25, utilization must become 1/1.25 = 0.8 to keep budget identical. This is a 20% reduction.",
                        formulasUsed = "P * U = Total budget", shortcutTricks = "+1/4 price increase -> -1/5 utilization change.",
                        relatedConcepts = "Ratios, percentage margins", difficulty = difficulty
                    )
                } else if (type == QuestionType.MSQ) {
                    val qText = "Select all mathematically correct percentage/ratio statements under \"$subtopicName\":"
                    val options = listOf(
                        "An increase of 50% followed by a decrease of 50% result in a net decrease of 25%.",
                        "If a:b = 2:3 and b:c = 4:5, the compounded ratio a:b:c is 8:12:15.",
                        "If cost price is 100, and it is marked up by 30%, then sold with a 10% discount, profit is 17%.",
                        "If selling price is less than cost price, a net profit is obtained directly."
                    )
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = options, correctOptions = listOf(0, 1, 2),
                        explanation = "Statement 1: 1.5 * 0.5 = 0.75 (25% decrease). Statement 2: LCM of 3 and 4 is 12 (8:12:15). Statement 3: Profit is 130 * 0.9 - 100 = 17.",
                        formulasUsed = "Profit & Ratio identities", shortcutTricks = "Substitute base 100 to quickly confirm assertions.",
                        relatedConcepts = "Profit CP SP ratios", difficulty = difficulty
                    )
                } else {
                    val cost = 80.0 + (idx % 3) * 10
                    val sp = cost * 1.25
                    val qText = "If a component's manufacturing cost is $$cost and we sell it to obtain exactly 25% profit margin, what is the selling price in dollars?"
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = null, correctOptions = null,
                        correctNumericalRange = (sp - 0.01)..(sp + 0.01),
                        explanation = "Selling Price = Cost * 1.25 = $sp.",
                        formulasUsed = "SP = CP * (1 + P%/100)", shortcutTricks = "Add one-quarter of CP to CP directly.",
                        relatedConcepts = "Percentages and profit", difficulty = difficulty
                    )
                }
            }
            "apt_quant_time_work", "apt_quant_permutation_combination", "apt_quant_probability" -> {
                if (type == QuestionType.MCQ) {
                    val qText = "In \"$subtopicName\", how many distinct ways can the letters of the word \"GATE\" be rearranged?"
                    val options = listOf("24", "12", "6", "48").shuffled(rand)
                    val correctIdx = options.indexOf("24")
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = options, correctOptions = listOf(correctIdx),
                        explanation = "'GATE' contains 4 distinct letters. Permutations = 4! = 24.",
                        formulasUsed = "n! permutations", shortcutTricks = "Compute standard permutations with non-repeating terms.",
                        relatedConcepts = "Combinatorics, factorials", difficulty = difficulty
                    )
                } else if (type == QuestionType.MSQ) {
                    val qText = "Select all of the valid probabilitic and combinatorial formulas under \"$subtopicName\":"
                    val options = listOf(
                        "C(5, 2) is exactly 10.",
                        "P(5, 2) is exactly 20.",
                        "If two fair coins are tossed, the probability of obtaining at least one head is 3/4.",
                        "C(4, 4) is exactly 4."
                    )
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = options, correctOptions = listOf(0, 1, 2),
                        explanation = "C(5,2)=10, P(5,2)=20, Coin toss at least one head = {HH, HT, TH} = 3/4. C(4,4) is 1, not 4.",
                        formulasUsed = "C(n, r) and P(n, r) formulations", shortcutTricks = "Manually check small factorials.",
                        relatedConcepts = "Probability & arrangements", difficulty = difficulty
                    )
                } else {
                    val ans = 15.0 // C(6, 2)
                    val qText = "Determine the exact value of combinatorial selections represented by C(6, 2) under \"$subtopicName\" rules."
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = null, correctOptions = null,
                        correctNumericalRange = (ans - 0.01)..(ans + 0.01),
                        explanation = "C(6, 2) = (6 * 5) / 2 = 15.",
                        formulasUsed = "C(n, r) = n! / (r! * (n-r)!)", shortcutTricks = "Compute small coefficients systematically.",
                        relatedConcepts = "Permutations & Selections", difficulty = difficulty
                    )
                }
            }
            "apt_quant_logarithms", "apt_quant_data_interpretation", "apt_quant_geometry", "apt_quant_mensuration" -> {
                if (type == QuestionType.MCQ) {
                    val qText = "Under \"$subtopicName\" properties, if the linear radius of a solid 3D sphere is doubled, its volume increases by what factor?"
                    val options = listOf("8", "4", "2", "6").shuffled(rand)
                    val correctIdx = options.indexOf("8")
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = options, correctOptions = listOf(correctIdx),
                        explanation = "Volume is proportional to the third power of linear scale: 2^3 = 8.",
                        formulasUsed = "V = 4/3 * pi * r^3", shortcutTricks = "Volume is proportional to k^3.",
                        relatedConcepts = "Mensuration geometry", difficulty = difficulty
                    )
                } else if (type == QuestionType.MSQ) {
                    val qText = "Select all mathematically sound geometric relationships for circular or regular boundaries in \"$subtopicName\":"
                    val options = listOf(
                        "Each interior angle of a regular hexagon is exactly 120 degrees.",
                        "In a right triangle with sides 6 cm and 8 cm, the hypotenuse is exactly 10 cm.",
                        "For any real numbers, log(ab) = log(a) + log(b) is a valid logarithm identity.",
                        "The volume of a cone is half the volume of a cylinder of identical radius and height."
                    )
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = options, correctOptions = listOf(0, 1, 2),
                        explanation = "Hexagon angle is 120 deg. Triangle 6-8-10 is Pythagorean triple. log(ab) = log(a)+log(b). Cone is 1/3 of cylinder, not half.",
                        formulasUsed = "Symmetry & logarithm properties", shortcutTricks = "Double check standard conic and cylinder formulas.",
                        relatedConcepts = "Geometry, logarithms, shape scaling", difficulty = difficulty
                    )
                } else {
                    val ans = 150.0 // 6 * s^2 where s = 5
                    val qText = "A regular 3D cube representing structural elements in \"$subtopicName\" has an edge length of 5.0 cm. Find the total surface area in sq cm."
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = null, correctOptions = null,
                        correctNumericalRange = (ans - 0.01)..(ans + 0.01),
                        explanation = "Area of a cube has 6 faces: 6 * s^2 = 6 * 25 = 150.0.",
                        formulasUsed = "Area = 6 * s^2", shortcutTricks = "Compute basic face area and scale by face counts.",
                        relatedConcepts = "Mensuration scaling", difficulty = difficulty
                    )
                }
            }
            else -> {
                val workCombos = listOf(Pair(12, 6), Pair(12, 24), Pair(10, 15))
                val pair = workCombos[idx % workCombos.size]
                val x = pair.first
                val y = pair.second
                val ans = (x * y).toDouble() / (x + y).toDouble()
                if (type == QuestionType.MCQ) {
                    val qText = """
                        An engineer drives from home at an average speed of 40 km/h and immediately returns along the exact same path driving at 60 km/h.
                        Compute the overall average speed (in km/h) for the entire round trip.
                    """.trimIndent()
                    val options = listOf("48.00 km/h", "50.00 km/h", "45.00 km/h", "52.50 km/h").shuffled(rand)
                    val correctIdx = options.indexOf("48.00 km/h")
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = options, correctOptions = listOf(correctIdx),
                        explanation = "V_avg = 2 * V1 * V2 / (V1 + V2) = 2 * 40 * 60 / 100 = 48 km/h.",
                        formulasUsed = "Harmonic Mean = 2 * v1 * v2 / (v1 + v2)", shortcutTricks = "Round trip average is harmonic mean, strictly less than modern arithmetic mean.",
                        relatedConcepts = "Speed rates computations", difficulty = difficulty
                    )
                } else if (type == QuestionType.MSQ) {
                    val qText = "Identify which of the following quantitative relationships is/are mathematically correct: (Select all that apply)"
                    val options = listOf(
                        "If a sum doubles itself in 10 years simple interest, interest rate is exactly 10%.",
                        "The compound interest on any principal is always strictly greater than or equal to simple interest for positive durations.",
                        "If three values are in ratio 2:3:5, their sum must always be an even integer.",
                        "For any two positive numbers, the Arithmetic Mean is always greater than or equal to Geometric Mean."
                    )
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = options, correctOptions = listOf(0, 1, 3),
                        explanation = "Option A is correct. Option B holds since interest compounds. Option D is standard AM-GM inequality. C is false because values can be decimals.",
                        formulasUsed = "Simple/Compound interest rules", shortcutTricks = "Use simple decimal offsets to test ratio properties.",
                        relatedConcepts = "In equalities, percentages", difficulty = difficulty
                    )
                } else {
                    val qText = """
                        Working alone, analyst A takes exactly $x hours to compile a statistical summary.
                        Analyst B takes exactly $y hours to complete the identical task. 
                        How many hours will they require to complete the task if they work together concurrently?
                    """.trimIndent()
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = null, correctOptions = null,
                        correctNumericalRange = (ans - 0.05)..(ans + 0.05),
                        explanation = "Combined working rate yields: T = (x * y) / (x + y) = ($x * $y) / ($x + $y) = $ans.",
                        formulasUsed = "Rate equations: 1/T = 1/x + 1/y", shortcutTricks = "Product divided by sum.",
                        relatedConcepts = "Concurrent rates", difficulty = difficulty
                    )
                }
            }
        }
    }

    private fun generateAnalyticalAptitudeQuestion(
        subjectId: String, topicId: String, subtopicId: String, subtopicName: String,
        idx: Int, year: Int, type: QuestionType, difficulty: String, rand: Random
    ): GateQuestion {
        val qId = "proc_apt_${subtopicId}_$idx"
        return when (subtopicId) {
            "apt_anal_number_series", "apt_anal_numerical_reasoning" -> {
                if (type == QuestionType.MCQ) {
                    val qText = "Determine the next term in the logical number series under \"$subtopicName\": 2, 5, 10, 17, 26, ?"
                    val options = listOf("37", "35", "41", "39").shuffled(rand)
                    val correctIdx = options.indexOf("37")
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = options, correctOptions = listOf(correctIdx),
                        explanation = "Pattern is n^2 + 1. For n = 6, 6^2 + 1 = 37.",
                        formulasUsed = "T_n = n^2 + 1", shortcutTricks = "Identify first-order differences: 3, 5, 7, 9, 11...",
                        relatedConcepts = "Progressions, sequences", difficulty = difficulty
                    )
                } else if (type == QuestionType.MSQ) {
                    val qText = "Select all of the option sequences that represent quadratic progression growth under \"$subtopicName\":"
                    val options = listOf(
                        "2, 5, 10, 17, 26, 37",
                        "1, 4, 9, 16, 25, 36",
                        "3, 6, 11, 18, 27, 38",
                        "2, 4, 8, 16, 32, 64"
                    )
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = options, correctOptions = listOf(0, 1, 2),
                        explanation = "A, B, and C grow quadratically (second difference is 2). D grows exponentially (powers of 2).",
                        formulasUsed = "Sequence difference tracking", shortcutTricks = "Evaluate adjacent difference progressions.",
                        relatedConcepts = "Number Series structures", difficulty = difficulty
                    )
                } else {
                    val ans = 125.0 // n^3 for n=5
                    val qText = "Solve for the next term in the cubic sequence under \"$subtopicName\": 1, 8, 27, 64, ?"
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = null, correctOptions = null,
                        correctNumericalRange = (ans - 0.01)..(ans + 0.01),
                        explanation = "Cubes: 1^3, 2^3, 3^3, 4^3, 5^3=125.",
                        formulasUsed = "T_n = n^3", shortcutTricks = "Perfect cubes recognition.",
                        relatedConcepts = "Cubic progressions", difficulty = difficulty
                    )
                }
            }
            "apt_anal_deduction_induction", "apt_anal_analogies" -> {
                if (type == QuestionType.MCQ) {
                    val qText = "Analyze the numerical analogy under \"$subtopicName\" and solve for the missing term: 4 : 16 :: 5 : ?"
                    val options = listOf("25", "20", "30", "125").shuffled(rand)
                    val correctIdx = options.indexOf("25")
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = options, correctOptions = listOf(correctIdx),
                        explanation = "The relationship is square mapping: x : x^2. Since 4 is mapped to 16, 5 maps to 25.",
                        formulasUsed = "Analogy square function f(x) = x^2", shortcutTricks = "Identify square mapping values on both sides.",
                        relatedConcepts = "Analogies", difficulty = difficulty
                    )
                } else if (type == QuestionType.MSQ) {
                    val qText = "Select all option pairs displaying the squaring function relation \"x : x^2\" under \"$subtopicName\":"
                    val options = listOf("3 : 9", "4 : 16", "5 : 25", "6 : 30")
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = options, correctOptions = listOf(0, 1, 2),
                        explanation = "3^2=9, 4^2=16, 5^2=25 are correct. 6^2 is 36, not 30.",
                        formulasUsed = "x : x^2", shortcutTricks = "Square left term and cross-check right term.",
                        relatedConcepts = "Symmetric analogies", difficulty = difficulty
                    )
                } else {
                    val ans = 27.0
                    val qText = "Determine the missing term in the cubic analogic relation under \"$subtopicName\": 2 : 8 :: 3 : ?"
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = null, correctOptions = null,
                        correctNumericalRange = (ans - 0.01)..(ans + 0.01),
                        explanation = "Cubic: 2^3=8, so 3^3=27.",
                        formulasUsed = "f(x) = x^3", shortcutTricks = "Cube of base 3.",
                        relatedConcepts = "Cubic analogical mappings", difficulty = difficulty
                    )
                }
            }
            else -> {
                if (type == QuestionType.MCQ) {
                    val stepsList = listOf(3, 4, 5, 6)
                    val step = stepsList[idx % stepsList.size]
                    val start = 2 + rand.nextInt(10)
                    val val1 = start
                    val val2 = val1 + step
                    val val3 = val2 + step + 1
                    val val4 = val3 + step + 2
                    val val5 = val4 + step + 3
                    val expected = val5 + step + 4
                    val qText = """
                        Find the missing number in the following analytical number series:
                        $val1, $val2, $val3, $val4, $val5, ?
                        This series represents an increasing difference sequence under logical analysis for "$subtopicName".
                    """.trimIndent()
                    val optCorrect = "$expected"
                    val optWrong1 = "${expected - 2}"
                    val optWrong2 = "${expected + 2}"
                    val optWrong3 = "${expected + step}"
                    val options = listOf(optCorrect, optWrong1, optWrong2, optWrong3).shuffled(rand)
                    val correctIdx = options.indexOf(optCorrect)
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = options, correctOptions = listOf(correctIdx),
                        explanation = "The differences increase by 1 at each step. Next value is $val5 + ${step + 4} = $expected.",
                        formulasUsed = "Progression of differences", shortcutTricks = "Examine consecutive differences.",
                        relatedConcepts = "Logical pattern analysis", difficulty = difficulty
                    )
                } else if (type == QuestionType.MSQ) {
                    val qText = "Consider Statement A: \"All algorithm steps are clear.\" and Statement B: \"Some clear steps are fast.\" Which of the following statements logically follows or matches Venn principles under \"$subtopicName\"?"
                    val options = listOf(
                        "Some algorithm steps might be fast.",
                        "It is possible for no algorithm steps to be fast.",
                        "Universal assertions are represented as subsets under Venn logic models.",
                        "No algorithm steps are clear."
                    )
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = options, correctOptions = listOf(0, 1, 2),
                        explanation = "A Venn overlap shows algorithm steps are inside clear steps, and clear steps overlap with fast steps. Hence, some algorithms *might* be fast, or none *might* be fast.",
                        formulasUsed = "Categorical logic representations", shortcutTricks = "Draw Venn circles.",
                        relatedConcepts = "Euler overlays", difficulty = difficulty
                    )
                } else {
                    val base = 4.0 + (idx % 4)
                    val ans = base * base
                    val qText = "A logical grid has $base rows and $base columns. Find the total number of basic single-grid cell locations representing \"$subtopicName\" vertices."
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = null, correctOptions = null,
                        correctNumericalRange = (ans - 0.01)..(ans + 0.01),
                        explanation = "A $base x $base grid has exactly $base * $base = $ans single-cell locations.",
                        formulasUsed = "Rows * Columns cells", shortcutTricks = "Multiply dimensions directly.",
                        relatedConcepts = "Analytical grid structures", difficulty = difficulty
                    )
                }
            }
        }
    }

    private fun generateSpatialAptitudeQuestion(
        subjectId: String, topicId: String, subtopicId: String, subtopicName: String,
        idx: Int, year: Int, type: QuestionType, difficulty: String, rand: Random
    ): GateQuestion {
        val qId = "proc_apt_${subtopicId}_$idx"
        return when (subtopicId) {
            "apt_spatial_rotation", "apt_spatial_shape_transformation" -> {
                if (type == QuestionType.MCQ) {
                    val qText = "Under \"$subtopicName\", if a 2D shape is rotated 90 degrees clockwise, then rotated 180 degrees counter-clockwise, what single-step rotation is identical?"
                    val options = listOf("90 degrees counter-clockwise", "90 degrees clockwise", "180 degrees clockwise", "270 degrees clockwise").shuffled(rand)
                    val correctIdx = options.indexOf("90 degrees counter-clockwise")
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = options, correctOptions = listOf(correctIdx),
                        explanation = "-90 + 180 = +90 degrees (which is 90 degrees counter-clockwise).",
                        formulasUsed = "Angular summation θ = θ1 + θ2", shortcutTricks = "Assign signs/directions to rotation angles.",
                        relatedConcepts = "Symmetry and rotation", difficulty = difficulty
                    )
                } else if (type == QuestionType.MSQ) {
                    val qText = "Select all geometric transformations that preserve the interior angles of any polygon under \"$subtopicName\":"
                    val options = listOf("Translation (Shift)", "Rotation", "Uniform scaling (Dilation)", "Shearing (Distortion)")
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = options, correctOptions = listOf(0, 1, 2),
                        explanation = "Translation, rotation, and scaling preserve shape similarity (angles). Shearing skews angles.",
                        formulasUsed = "Conformal transformations", shortcutTricks = "Preserving shape form preserves angles.",
                        relatedConcepts = "Symmetry coordinate mappings", difficulty = difficulty
                    )
                } else {
                    val ans = 8.0 // scale^3
                    val qText = "If a 3D solid sphere is uniformly dilated by a linear scale factor of 2.0 under \"$subtopicName\", by what factor does its volume increase?"
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = null, correctOptions = null,
                        correctNumericalRange = (ans - 0.01)..(ans + 0.01),
                        explanation = "Volume scale of dilation is linear scale ^ 3: 2^3 = 8.",
                        formulasUsed = "Dilation factor = scale^3", shortcutTricks = "Take cube of linear magnification.",
                        relatedConcepts = "Mensuration & dilation scaling", difficulty = difficulty
                    )
                }
            }
            "apt_spatial_paper_folding", "apt_spatial_pattern_recognition" -> {
                if (type == QuestionType.MCQ) {
                    val qText = "A square paper is folded in half once horizontally, and folded in half again vertically. If you make a single punch hole in the center, how many total punch holes appear when unfolded under \"$subtopicName\"?"
                    val options = listOf("4", "2", "1", "8").shuffled(rand)
                    val correctIdx = options.indexOf("4")
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = options, correctOptions = listOf(correctIdx),
                        explanation = "Folding twice yields 2^2 = 4 layers. Punching once goes through all 4 layers, yielding 4 holes.",
                        formulasUsed = "Layers = 2^f folds", shortcutTricks = "Each fold doubles sheet layers.",
                        relatedConcepts = "Symmetry folding logic", difficulty = difficulty
                    )
                } else if (type == QuestionType.MSQ) {
                    val qText = "Select all shapes displaying at least one reflective line of symmetry under \"$subtopicName\":"
                    val options = listOf("Square", "Rectangle", "Regular Hexagon", "Scalene Triangle")
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = options, correctOptions = listOf(0, 1, 2),
                        explanation = "Square, rectangle, and hexagon are highly symmetric. Scalene triangles possess no equal sides or angles.",
                        formulasUsed = "Reflective symmetry planes", shortcutTricks = "Identify if mirror reflection maps the shape back onto itself.",
                        relatedConcepts = "Symmetry of polygons", difficulty = difficulty
                    )
                } else {
                    val ans = 5.0
                    val qText = "Compute the exact count of lines of reflective symmetry possessed by a standard regular pentagon under \"$subtopicName\" rules."
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = null, correctOptions = null,
                        correctNumericalRange = (ans - 0.01)..(ans + 0.01),
                        explanation = "Regular n-sided polygon has n lines of reflective symmetry. A pentagon has 5 lines.",
                        formulasUsed = "Symmetry count = n", shortcutTricks = "Pass lines from each point to opposite midpoints.",
                        relatedConcepts = "Visual patterns", difficulty = difficulty
                    )
                }
            }
            else -> {
                if (type == QuestionType.MCQ) {
                    val qText = """
                        Under spatial mirroring rules representing design components of "$subtopicName", 
                        a flat asymmetric pattern is rotated 180 degrees counter-clockwise and then mirrored horizontally.
                        Which of the following operations describes the single-step equivalent transformation?
                    """.trimIndent()
                    val options = listOf("A simple vertical mirror reflection", "A simple horizontal mirror reflection", "A simple 90 degrees clockwise rotation", "No transformation (returns to exact initial state)").shuffled(rand)
                    val correctIndex = options.indexOf("A simple vertical mirror reflection")
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = options, correctOptions = listOf(correctIndex),
                        explanation = "A 180-degree rotation is equivalent to both horizontal and vertical mirroring. Rotating and then mirroring horizontally cancels the horizontal flip, leaving only a vertical mirror reflection.",
                        formulasUsed = "Spatial Matrix transformations", shortcutTricks = "Analyse an asymmetric 'L' shape trace.",
                        relatedConcepts = "Chirality, coordinate transformations", difficulty = difficulty
                    )
                } else if (type == QuestionType.MSQ) {
                    val qText = "Which of the following assertions about spatial properties of multi-dimensional shapes is/are correct? (Select all that apply)"
                    val options = listOf(
                        "A standard regular 3D tetrahedron has exactly 4 vertices and 6 linear edges.",
                        "Rotating a 3D object along its principal axis preserves all relative volume and edge metrics.",
                        "Reflecting an asymmetric 3D shape across any plane alters its chirality (handedness).",
                        "Every regular polyhedron must have an equal number of faces and vertices."
                    )
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = options, correctOptions = listOf(0, 1, 2),
                        explanation = "Faces F and vertices V are not always equal under Euler's formula (e.g. cube has 6 faces and 8 vertices).",
                        formulasUsed = "Euler's Polyhedral Theorem: F + V = E + 2", shortcutTricks = "Check with a simple cube to disprove face and vertex identity.",
                        relatedConcepts = "Polyhedrons bounds", difficulty = difficulty
                    )
                } else {
                    val s = 3 + (idx % 3)
                    val answer = 12 * (s - 2)
                    val qText = """
                        A solid cube of side $s cm is painted completely red on its outer faces. 
                        It is then cut into uniform $s^3 unit cubes of side 1 cm.
                        Determine the number of unit cubes that have exactly TWO faces painted red under "$subtopicName".
                    """.trimIndent()
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = null, correctOptions = null,
                        correctNumericalRange = (answer.toDouble() - 0.01)..(answer.toDouble() + 0.01),
                        explanation = "Two-sided painted cubes always lie on edges (not corners). There are 12 edges, each having s-2 middle cubes. Total = 12 * ($s - 2) = $answer.",
                        formulasUsed = "Count(2-painted) = 12 * (s - 2)", shortcutTricks = "Multiply 12 by (side - 2) directly.",
                        relatedConcepts = "Geometric division", difficulty = difficulty
                    )
                }
            }
        }
    }

    private fun generateDefaultAptitudeQuestion(
        subjectId: String,
        topicId: String,
        subtopicId: String,
        subtopicName: String,
        index: Int,
        year: Int,
        type: QuestionType,
        difficulty: String,
        rand: Random
    ): GateQuestion {
        val qId = "proc_apt_${subtopicId}_$index"
        val varA = 10 + rand.nextInt(30)
        val varB = 20 + rand.nextInt(40)
        val total = varA + varB
        val ratio = String.format(Locale.US, "%.2f", (varA.toDouble() / total.toDouble()) * 100)

        val qText = """
            An analysis of structural metrics in "$subtopicName" shows that out of $total trial conditions, exactly $varA satisfy primary criteria A and $varB satisfy criteria B. 
            If a single random trial is observed, what is the exact percentage probability (rounded to two decimal places) that it falls under criteria A?
        """.trimIndent()

        val optA = "$ratio%"
        val optB = String.format(Locale.US, "%.2f%%", ((varA + 5).toDouble() / total * 100))
        val optC = String.format(Locale.US, "%.2f%%", ((varA - 3).toDouble() / total * 100))
        val optD = String.format(Locale.US, "%.2f%%", (varB.toDouble() / total * 100))

        val options = listOf(optA, optB, optC, optD).shuffled(rand)
        val correctIndex = options.indexOf(optA)

        return GateQuestion(
            id = qId,
            subjectId = subjectId,
            topicId = topicId,
            subtopicId = subtopicId,
            year = year,
            questionText = qText,
            questionType = type,
            options = options,
            correctOptions = listOf(correctIndex),
            correctNumericalRange = null,
            explanation = "We compute the probability by dividing the subset count ($varA) by the total outcomes ($total) and multiplying by 100: ($varA / $total) * 100 = $ratio%.",
            formulasUsed = "P(A) = n(A) / N(total)",
            shortcutTricks = "Always build the ratio directly. Check that the final percentage correlates directly with the magnitude of parts.",
            relatedConcepts = "Probability, Ratio analysis, Quantitative Aptitude",
            difficulty = difficulty
        )
    }

    private fun generateMathQuestion(
        subjectId: String,
        topicId: String,
        subtopicId: String,
        subtopicName: String,
        index: Int,
        year: Int,
        type: QuestionType,
        difficulty: String,
        rand: Random
    ): GateQuestion {
        val qId = "proc_math_${subtopicId}_$index"

        return when (subtopicId) {
            "math_la_eig" -> {
                when (type) {
                    QuestionType.MCQ -> {
                        val a = 2 + rand.nextInt(5) // 2 to 6
                        val d = 7 + rand.nextInt(5) // 7 to 11
                        val b = 15 // Off-diagonal parameter

                        val qText = """
                            Consider an upper triangular 2x2 matrix representing factors of "$subtopicName":
                            \[ A = \begin{pmatrix} $a & $b \\ 0 & $d \end{pmatrix} \]
                            What are the exact eigenvalues of this matrix?
                        """.trimIndent()

                        val optA = "λ1 = $a, λ2 = $d"
                        val optB = "λ1 = ${a + 1}, λ2 = ${d - 1}"
                        val optC = "λ1 = 0, λ2 = ${a + d}"
                        val optD = "λ1 = ${a * d}, λ2 = 1"

                        val options = listOf(optA, optB, optC, optD).shuffled(rand)
                        val correctIndex = options.indexOf(optA)

                        GateQuestion(
                            id = qId,
                            subjectId = subjectId,
                            topicId = topicId,
                            subtopicId = subtopicId,
                            year = year,
                            questionText = qText,
                            questionType = type,
                            options = options,
                            correctOptions = listOf(correctIndex),
                            correctNumericalRange = null,
                            explanation = "For any upper or lower triangular matrix, as well as diagonal matrices, the characteristic equation det(λI - A) = 0 factorizes directly into diagonal elements: (λ - $a)(λ - $d) = 0. Therefore, the eigenvalues are simply the diagonal entries themselves: $a and $d.",
                            formulasUsed = "det(λI - A) = 0",
                            shortcutTricks = "Do not waste time applying (λ^2 - Trace*λ + Det) for triangular matrices. The eigenvalues are always identical to the elements of the primary diagonal.",
                            relatedConcepts = "Linear Algebra, Triangular matrices, Characteristic roots",
                            difficulty = difficulty
                        )
                    }
                    QuestionType.MSQ -> {
                        val qText = """
                            Regarding real symmetric matrices under "$subtopicName", which of the following statements is/are mathematically sound? (Select all that apply)
                        """.trimIndent()

                        val optA = "All eigenvalues of a real symmetric matrix must be strictly real."
                        val optB = "Eigenvectors corresponding to distinct eigenvalues of a real symmetric matrix are always orthogonal to each other."
                        val optC = "The determinant of any real symmetric matrix must always be strictly positive."
                        val optD = "For a real symmetric matrix A, transposition yields the original matrix, i.e., Aᵀ = A."

                        val listOpts = listOf(optA, optB, optC, optD)
                        val correctIdx = listOf(0, 1, 3)

                        GateQuestion(
                            id = qId,
                            subjectId = subjectId,
                            topicId = topicId,
                            subtopicId = subtopicId,
                            year = year,
                            questionText = qText,
                            questionType = type,
                            options = listOpts,
                            correctOptions = correctIdx,
                            correctNumericalRange = null,
                            explanation = "A real symmetric matrix is defined by Aᵀ = A (Option D). Standard theorems of linear algebra prove is that its eigenvalues are real (Option A) and distinct eigenvectors are orthogonal (Option B). Its determinant, however, can be negative or zero (e.g. [[-1, 0], [0, 1]] has det = -1), making Option C incorrect.",
                            formulasUsed = "Aᵀ = A; Spectral Theorem properties",
                            shortcutTricks = "Think of a simple mirror matrix like [[-1, 0], [0, 1]]. It is symmetric, yet its determinant is negative, instantly refuting Option C.",
                            relatedConcepts = "Orthogonal projections, Real spectrum values, Symmetric conditions",
                            difficulty = difficulty
                        )
                    }
                    QuestionType.NAT -> {
                        val a = 2 + rand.nextInt(3) // 2, 3, 4
                        val b = 2 + rand.nextInt(3) // 2, 3, 4
                        val c = 1 + rand.nextInt(3) // 1, 2, 3
                        val det = a * b * c
                        val roundedAns = det.toDouble()

                        val qText = """
                            A 3x3 diagonal system matrix governing "$subtopicName" has eigenvalues λ1 = $a, λ2 = $b, and λ3 = $c.
                            Compute the absolute determinant of this matrix.
                        """.trimIndent()

                        GateQuestion(
                            id = qId,
                            subjectId = subjectId,
                            topicId = topicId,
                            subtopicId = subtopicId,
                            year = year,
                            questionText = qText,
                            questionType = type,
                            options = null,
                            correctOptions = null,
                            correctNumericalRange = (roundedAns - 0.05)..(roundedAns + 0.05),
                            explanation = "A fundamental theorem of linear algebra states that the determinant of any matrix is exactly equal to the product of its eigenvalues: Det(A) = λ1 * λ2 * λ3 = $a * $b * $c = $det.",
                            formulasUsed = "Det(A) = Π(λ_i)",
                            shortcutTricks = "Simply multiply all given eigenvalues together to instantly obtain the determinant.",
                            relatedConcepts = "Determinants eigenvalues relations, Matrix spectrum",
                            difficulty = difficulty
                        )
                    }
                }
            }
            "math_calc_concepts" -> {
                when (type) {
                    QuestionType.MCQ -> {
                        val a = 2 + rand.nextInt(6) // 2 to 7
                        val b = 3 + rand.nextInt(6) // 3 to 8
                        val ansVal = a.toDouble() / b.toDouble()
                        val formattedAns = String.format(Locale.US, "%.3f", ansVal)

                        val qText = """
                            Evaluate the analytical limit under "$subtopicName" properties:
                            \[ L = \lim_{x \to 0} \frac{\sin($a x)}{$b x} \]
                            Select the correct computed limiting threshold.
                        """.trimIndent()

                        val optA = "L = $a / $b"
                        val optB = "L = $b / $a"
                        val optC = "L = 1.0"
                        val optD = "L = 0.0"

                        val options = listOf(optA, optB, optC, optD).shuffled(rand)
                        val correctIndex = options.indexOf(optA)

                        GateQuestion(
                            id = qId,
                            subjectId = subjectId,
                            topicId = topicId,
                            subtopicId = subtopicId,
                            year = year,
                            questionText = qText,
                            questionType = type,
                            options = options,
                            correctOptions = listOf(correctIndex),
                            correctNumericalRange = null,
                            explanation = "As x approaches 0, sin($a x)/($b x) represents a 0/0 indeterminate form. Applying L'Hopital's rule, we differentiate the numerator and denominator: lim_{x->0} ($a * cos($a x)) / $b = $a / $b because cos(0) = 1. Therefore, the limit is $a / $b.",
                            formulasUsed = "lim_{θ->0} sin(cθ)/cθ = 1; L'Hopital's Rule",
                            shortcutTricks = "The limit of sin(Ax)/Bx as x->0 is always simply the ratio A/B. This is instantaneous.",
                            relatedConcepts = "Calculus Limits, L'Hopital's Differentiation, Continuity",
                            difficulty = difficulty
                        )
                    }
                    QuestionType.MSQ -> {
                        val qText = """
                            For continuous and differentiable functions over an interval [a, b] under "$subtopicName", which of the following statements is/are correct? (Select all that apply)
                        """.trimIndent()

                        val optA = "According to Rolle's Theorem, if f(a) = f(b), there exists at least one c in (a, b) such that f'(c) = 0."
                        val optB = "The Mean Value Theorem states there exists at least one c in (a, b) such that f'(c) = (f(b) - f(a)) / (b - a)."
                        val optC = "If f'(x) > 0 for all points in [a, b], the function must be strictly increasing on that closed interval."
                        val optD = "A local saddle point must always have a strictly positive second derivative."

                        val listOpts = listOf(optA, optB, optC, optD)
                        val correctIdx = listOf(0, 1, 2)

                        GateQuestion(
                            id = qId,
                            subjectId = subjectId,
                            topicId = topicId,
                            subtopicId = subtopicId,
                            year = year,
                            questionText = qText,
                            questionType = type,
                            options = listOpts,
                            correctOptions = correctIdx,
                            correctNumericalRange = null,
                            explanation = "By definition, a saddle point has a zero first derivative but its second derivative can be zero, positive, or negative in different axes; it does not have a strictly positive second derivative in all directions, making Option D false. Mean Value limits and slopes are correct.",
                            formulasUsed = "Mean Value Theorem conditions, First Derivative indicators",
                            shortcutTricks = "Saddle points have local inflection properties, meaning second derivatives vanish or flip signs. Eliminate Option D immediately.",
                            relatedConcepts = "Mean Value Theorem, Rolle's conditions, Monotonic growth",
                            difficulty = difficulty
                        )
                    }
                    QuestionType.NAT -> {
                        val c = 2 + rand.nextInt(10) // 2 to 11
                        val answer = 4.0 + c

                        val qText = """
                            Find the absolute maximum value achieved by the function:
                            \[ f(x) = -x^2 + 4x + $c \]
                            governing localization peaks under "$subtopicName".
                        """.trimIndent()

                        GateQuestion(
                            id = qId,
                            subjectId = subjectId,
                            topicId = topicId,
                            subtopicId = subtopicId,
                            year = year,
                            questionText = qText,
                            questionType = type,
                            options = null,
                            correctOptions = null,
                            correctNumericalRange = (answer - 0.01)..(answer + 0.01),
                            explanation = "To find the maximum, we differentiate f(x) with respect to x: f'(x) = -2x + 4. Setting this to 0 gives x = 2. The second derivative is f''(x) = -2 < 0, confirming a maximum. Substituting x = 2 back into the function: f(2) = -(2^2) + 4*(2) + $c = -4 + 8 + $c = 4 + $c = $answer.",
                            formulasUsed = "f'(x) = 0; f''(x) < 0 peak criteria",
                            shortcutTricks = "The vertex of a parabole y = Ax^2 + Bx + C is at x = -B/(2A). For -x^2+4x+$c, x = -4/(-2) = 2. Compute f(2) quickly.",
                            relatedConcepts = "Extremum analysis, Optimization, Turning points",
                            difficulty = difficulty
                        )
                    }
                }
            }
            "math_de_sol" -> {
                when (type) {
                    QuestionType.MCQ -> {
                        val a = 2 + rand.nextInt(4) // 2 to 5

                        val qText = """
                            Solve the homogeneous first-order differential equation representing dynamic balances in "$subtopicName":
                            \[ \frac{dy}{dx} - $a y = 0 \]
                            with initial condition y(0) = C.
                        """.trimIndent()

                        val optA = "y(x) = C e^{$a x}"
                        val optB = "y(x) = C e^{-$a x}"
                        val optC = "y(x) = C x^{$a}"
                        val optD = "y(x) = $a C x"

                        val options = listOf(optA, optB, optC, optD).shuffled(rand)
                        val correctIndex = options.indexOf(optA)

                        GateQuestion(
                            id = qId,
                            subjectId = subjectId,
                            topicId = topicId,
                            subtopicId = subtopicId,
                            year = year,
                            questionText = qText,
                            questionType = type,
                            options = options,
                            correctOptions = listOf(correctIndex),
                            correctNumericalRange = null,
                            explanation = "This is a separable ODE: dy/y = $a dx => ln(y) = $a x + K => y(x) = e^K * e^{$a x} = C e^{$a x}. Since y(0) = C * e^0 = C, the final solution is y(x) = C e^{$a x}.",
                            formulasUsed = "dy/dx = k*y => y(x) = C * e^{k*x}",
                            shortcutTricks = "Homogeneous differential equation with positive coefficient yields an exponentially growing solution with the same factor. This instantly highlights Option A.",
                            relatedConcepts = "Separable differential equations, Growth exponents",
                            difficulty = difficulty
                        )
                    }
                    QuestionType.MSQ -> {
                        val qText = """
                            Which of the following functions represents/represent a mathematically valid basis of the solution space for the second-order ODE:
                            \[ \frac{d^2 y}{dx^2} + 4y = 0 \]
                            under "$subtopicName" boundaries? (Select all that apply)
                        """.trimIndent()

                        val optA = "y1(x) = sin(2x)"
                        val optB = "y2(x) = cos(2x)"
                        val optC = "y3(x) = e^{2j x}"
                        val optD = "y4(x) = e^{-2x}"

                        val listOpts = listOf(optA, optB, optC, optD)
                        val correctIdx = listOf(0, 1, 2)

                        GateQuestion(
                            id = qId,
                            subjectId = subjectId,
                            topicId = topicId,
                            subtopicId = subtopicId,
                            year = year,
                            questionText = qText,
                            questionType = type,
                            options = listOpts,
                            correctOptions = correctIdx,
                            correctNumericalRange = null,
                            explanation = "The characteristic equation is m^2 + 4 = 0 => m = ±2j. This yields a general solution of y(x) = A sin(2x) + B cos(2x), or equivalently in exponential complex form, C1 e^{2j x} + C2 e^{-2j x}. Therefore, sin(2x), cos(2x), and e^{2j x} are valid bases. Option D is incorrect since real exponential decay does not satisfy the sinusoidal characteristic equation.",
                            formulasUsed = "Characteristic ODE equation: m^2 + k^2 = 0",
                            shortcutTricks = "With no first-derivative term, +4y gives pure imaginary characteristic roots (±2j). Thus only trigonometric or complex imaginary exponents are sound bases.",
                            relatedConcepts = "Second-order ODE solutions, Trigonometric basis, Complex frequencies",
                            difficulty = difficulty
                        )
                    }
                    QuestionType.NAT -> {
                        val a = 2 + rand.nextInt(3) // 2 or 3 or 4
                        val b = 5 + rand.nextInt(6) // 5 to 10
                        // Integral of a*x^2 from 0 to 3 is a * (27)/3 = 9*a
                        val answer = 9.0 * a + b

                        val qText = """
                            Solve the initial value differential problem representing boundary changes in "$subtopicName":
                            \[ \frac{dy}{dx} = ${a * 3} x^2 \]
                            with initial condition y(0) = $b.
                            Compute the exact value of y(2).
                        """.trimIndent()

                        val compAns = (a * 3.0 * 2.0 * 2.0 * 2.0) / 3.0 + b

                        GateQuestion(
                            id = qId,
                            subjectId = subjectId,
                            topicId = topicId,
                            subtopicId = subtopicId,
                            year = year,
                            questionText = qText,
                            questionType = type,
                            options = null,
                            correctOptions = null,
                            correctNumericalRange = (compAns - 0.05)..(compAns + 0.05),
                            explanation = "Integrating with respect to x: y(x) = ∫ ${a * 3} x^2 dx = $a x^3 + C. Since y(0) = $b, we have C = $b, so y(x) = $a x^3 + $b. Evaluating this at x = 2: y(2) = $a * (2^3) + $b = 8*$a + $b = $compAns.",
                            formulasUsed = "y(x) = ∫ f(x) dx + C",
                            shortcutTricks = "Integrate the polynomial exponent first, then directly substitute the upper boundary and add the starting offset.",
                            relatedConcepts = "Polynomial differential integrations, Boundary values",
                            difficulty = difficulty
                        )
                    }
                }
            }
            "math_complex_residue" -> {
                when (type) {
                    QuestionType.MCQ -> {
                        val a = 1 + rand.nextInt(5) // 1 to 5
                        val b = 2 + rand.nextInt(5) // 2 to 6
                        val residue = a + b

                        val qText = """
                            Determine the analytic residue of the complex function:
                            \[ f(z) = \frac{z + $a}{z - $b} \]
                            at its simple pole located at z = $b within "$subtopicName".
                        """.trimIndent()

                        val optA = "$residue"
                        val optB = "${a - b}"
                        val optC = "1"
                        val optD = "0"

                        val options = listOf(optA, optB, optC, optD).shuffled(rand)
                        val correctIndex = options.indexOf(optA)

                        GateQuestion(
                            id = qId,
                            subjectId = subjectId,
                            topicId = topicId,
                            subtopicId = subtopicId,
                            year = year,
                            questionText = qText,
                            questionType = type,
                            options = options,
                            correctOptions = listOf(correctIndex),
                            correctNumericalRange = null,
                            explanation = "The function has a simple pole at z = $b. The residue at a simple pole is given by Res(f, $b) = lim_{z->$b} (z - $b)*f(z) = lim_{z->$b} (z + $a) = $b + $a = $residue.",
                            formulasUsed = "Res(f, c) = lim_{z->c} (z - c)*f(z)",
                            shortcutTricks = "For a simple rational pole P(z)/Q(z) where Q has a simple zero, the residue is simply P(pole value) if Q'(z) = 1. Substituting z = $b in (z + $a) yields $b + $a immediately.",
                            relatedConcepts = "Complex variables residues, Laurent series expansions, Simple poles",
                            difficulty = difficulty
                        )
                    }
                    QuestionType.MSQ -> {
                        val qText = """
                            Which of the following propositions about complex calculus is/are correct? (Select all that apply)
                        """.trimIndent()

                        val optA = "If a function f(z) is analytic everywhere inside and on a closed simple curve C, then ∮_C f(z) dz = 0 according to Cauchy."
                        val optB = "Cauchy's Residue Theorem states that ∮_C f(z) dz = 2πj * (Sum of residues of f(z) at all poles enclosed inside C)."
                        val optC = "The pole of a function f(z) is categorized as essential if its Laurent series contains infinite terms in the main positive expansion part."
                        val optD = "The function f(z) = e^z contains no singularities in the finite complex plane."

                        val listOpts = listOf(optA, optB, optC, optD)
                        val correctIdx = listOf(0, 1, 3)

                        GateQuestion(
                            id = qId,
                            subjectId = subjectId,
                            topicId = topicId,
                            subtopicId = subtopicId,
                            year = year,
                            questionText = qText,
                            questionType = type,
                            options = listOpts,
                            correctOptions = correctIdx,
                            correctNumericalRange = null,
                            explanation = "An essential singularity is classified by having infinite terms in its negative principal part (principal part), not its positive part, making Option C false. Analytical integrals and e^z definitions are correct.",
                            formulasUsed = "Cauchy Integral Theorem, Cauchy Residue Theorem",
                            shortcutTricks = "Singularities lie where the function diverges. e^z is an entire function, meaning it has zero finite singularities, validation Option D.",
                            relatedConcepts = "Cauchy-Riemann equations, Singularities classifications, Residue theorem",
                            difficulty = difficulty
                        )
                    }
                    QuestionType.NAT -> {
                        val w = 2 * (1 + rand.nextInt(3)) // 2, 4, 6
                        val a = 10 * w
                        val answer = a.toDouble() / (2.0 * w)

                        val qText = """
                            Evaluate the absolute magnitude of the residue of the complex function:
                            \[ f(z) = \frac{$a}{z^2 + $w^2} \]
                            at its simple pole located at z = + j$w.
                        """.trimIndent()

                        GateQuestion(
                            id = qId,
                            subjectId = subjectId,
                            topicId = topicId,
                            subtopicId = subtopicId,
                            year = year,
                            questionText = qText,
                            questionType = type,
                            options = null,
                            correctOptions = null,
                            correctNumericalRange = (answer - 0.01)..(answer + 0.01),
                            explanation = "We factorize the denominator: z^2 + $w^2 = (z - j$w)(z + j$w). The residue at z = j$w is Res(f, j$w) = lim_{z->j$w} (z - j$w)*f(z) = lim_{z->j$w} $a / (z + j$w) = $a / (2j$w) = -j * ${a / (2 * w)}. The absolute magnitude of this residue is simply $a / (2 * $w) = $answer.",
                            formulasUsed = "Residue simple pole limits",
                            shortcutTricks = "The residue of c / (z^2+w^2) at z=jw is always c / (2jw). Take its absolute magnitude which is c / 2w.",
                            relatedConcepts = "Complex magnitudes, Singular residues, Rational expansions",
                            difficulty = difficulty
                        )
                    }
                }
            }
            "math_prob_bayes" -> {
                when (type) {
                    QuestionType.MCQ -> {
                        val qText = """
                            A manufacturing line represents stats under "$subtopicName". 
                            Machine A produces 60% of components and Machine B produces 40%. 
                            The defects rate of Machine A is 2% and Machine B is 5%.
                            If a randomly chosen component is found to be defective, what is the probability that it was produced by Machine B?
                        """.trimIndent()

                        // Bayes theorem: P(B|D) = P(D|B)P(B) / (P(D|B)P(B) + P(D|A)P(A))
                        // P(B|D) = 0.05*0.40 / (0.05*0.40 + 0.02*0.60) = 0.020 / (0.020 + 0.012) = 0.02 / 0.032 = 0.625
                        val optA = "0.625"
                        val optB = "0.375"
                        val optC = "0.400"
                        val optD = "0.250"

                        val options = listOf(optA, optB, optC, optD).shuffled(rand)
                        val correctIndex = options.indexOf(optA)

                        GateQuestion(
                            id = qId,
                            subjectId = subjectId,
                            topicId = topicId,
                            subtopicId = subtopicId,
                            year = year,
                            questionText = qText,
                            questionType = type,
                            options = options,
                            correctOptions = listOf(correctIndex),
                            correctNumericalRange = null,
                            explanation = "By Bayes' Theorem: P(B|Defect) = [P(Def|B) * P(B)] / [P(Def|B)*P(B) + P(Def|A)*P(A)] = [0.05 * 0.40] / [0.05 * 0.40 + 0.02 * 0.60] = 0.020 / (0.020 + 0.012) = 0.020 / 0.032 = 0.625.",
                            formulasUsed = "Bayes' Law: P(X|Y) = P(Y|X)*P(X) / Σ P(Y|X_i)*P(X_i)",
                            shortcutTricks = "First write down the absolute joint defect percentages for each path: B's defective path is 2.0% and A's defective path is 1.2%. B's portion of total defects is therefore 2.0 / (2.0 + 1.2) = 2.0 / 3.2 = 0.625 directly.",
                            relatedConcepts = "Conditional Probability, Bayes Theorem, Defects partitioning",
                            difficulty = difficulty
                        )
                    }
                    QuestionType.MSQ -> {
                        val qText = """
                            For a classical standard normal random variable Z characterized under "$subtopicName" distributions, which of the following properties is/are correct? (Select all that apply)
                        """.trimIndent()

                        val optA = "The statistical mean of the standard normal distribution is exactly equal to 0."
                        val optB = "The standard deviation of the standard normal distribution is exactly equal to 1."
                        val optC = "The total area under the probability density curve is exactly equal to 1.0."
                        val optD = "The normal distribution is highly asymmetric, with a skewness coefficient greater than 1.0."

                        val listOpts = listOf(optA, optB, optC, optD)
                        val correctIdx = listOf(0, 1, 2)

                        GateQuestion(
                            id = qId,
                            subjectId = subjectId,
                            topicId = topicId,
                            subtopicId = subtopicId,
                            year = year,
                            questionText = qText,
                            questionType = type,
                            options = listOpts,
                            correctOptions = correctIdx,
                            correctNumericalRange = null,
                            explanation = "A standard normal distribution is perfectly symmetric about its mean, resulting in a skewness coefficient of exactly 0.0. This makes Option D completely false.",
                            formulasUsed = "Normal CDF integrations, Skewness formulas",
                            shortcutTricks = "Normal distributions are synonymous with standard bell curves, which are perfectly mirrored, so skewness is always zero.",
                            relatedConcepts = "Normal distribution, Mean and variance, Skewness symmetry",
                            difficulty = difficulty
                        )
                    }
                    QuestionType.NAT -> {
                        val p1 = 0.6
                        val p2 = 0.2
                        // P(A|B) = p1 / (p1 + p2) = 0.6 / 0.8 = 0.75
                        val answer = 0.75

                        val qText = """
                            Using Bayes' theory in "$subtopicName", let events A and A' be collectively exhaustive prior conditions with P(A) = 0.5. 
                            Let B be an observed symptom such that P(B|A) = $p1 and P(B|A') = $p2.
                            Compute the posterior updated probability P(A|B).
                        """.trimIndent()

                        GateQuestion(
                            id = qId,
                            subjectId = subjectId,
                            topicId = topicId,
                            subtopicId = subtopicId,
                            year = year,
                            questionText = qText,
                            questionType = type,
                            options = null,
                            correctOptions = null,
                            correctNumericalRange = (answer - 0.01)..(answer + 0.01),
                            explanation = "Since P(A) = 0.5 and A' is the complement, P(A') = 0.5. By Bayes' Theorem: P(A|B) = [P(B|A)*P(A)] / [P(B|A)*P(A) + P(B|A')*P(A')] = [$p1 * 0.5] / [$p1 * 0.5 + $p2 * 0.5] = $p1 / ($p1 + $p2) = $p1 / ${p1 + p2} = $answer.",
                            formulasUsed = "P(A|B) = P(B|A)*P(A) / (P(B|A)*P(A) + P(B|A')*P(A'))",
                            shortcutTricks = "Because prior probabilities are equal (each is 0.5), they cancel out. The solution is simply P(B|A) / (P(B|A) + P(B|A')).",
                            relatedConcepts = "Prior and posterior probability, Bayes theorem simplified",
                            difficulty = difficulty
                        )
                    }
                }
            }
            "math_num_integration" -> {
                when (type) {
                    QuestionType.MCQ -> {
                        val qText = """
                            Under standard "$subtopicName" theory, the Trapezoidal integration rule provides exact integral values for polynomials of maximum degree:
                        """.trimIndent()

                        val optA = "1 (Linear equations)"
                        val optB = "2 (Quadratic equations)"
                        val optC = "3 (Cubic equations)"
                        val optD = "0 (Constant equations only)"

                        val options = listOf(optA, optB, optC, optD).shuffled(rand)
                        val correctIndex = options.indexOf(optA)

                        GateQuestion(
                            id = qId,
                            subjectId = subjectId,
                            topicId = topicId,
                            subtopicId = subtopicId,
                            year = year,
                            questionText = qText,
                            questionType = type,
                            options = options,
                            correctOptions = listOf(correctIndex),
                            correctNumericalRange = null,
                            explanation = "The Trapezoidal rule approximates curves using linear trapezoids, which match linear polynomials (ax + b) exactly. Hence, its maximum degree for exact integration is 1.",
                            formulasUsed = "Numerical truncation errors",
                            shortcutTricks = "Trapezoidal fits straight lines, which are linear polynomials (degree 1). Simpson's 1/3 rule fits quadratics and yields exact integrals for cubic polynomials (degree 3).",
                            relatedConcepts = "Numerical Calculus limits, Truncation bounds, Simpson rules",
                            difficulty = difficulty
                        )
                    }
                    QuestionType.MSQ -> {
                        val qText = """
                            Which of the following properties are correct regarding Newton-Raphson and other numerical methods? (Select all that apply)
                        """.trimIndent()

                        val optA = "The Newton-Raphson root-finding method exhibits a quadratic rate of convergence near simple roots."
                        val optB = "The Trapezoidal integration rule typically overestimates the exact integral of functions that are strictly concave up (f''(x) > 0)."
                        val optC = "The Secant root-finding method converges faster than Newton-Raphson because it does not require calculating first derivatives."
                        val optD = "Simpson's 1/3 rule requires dividing the integration range into an even number of intervals."

                        val listOpts = listOf(optA, optB, optC, optD)
                        val correctIdx = listOf(0, 1, 3)

                        GateQuestion(
                            id = qId,
                            subjectId = subjectId,
                            topicId = topicId,
                            subtopicId = subtopicId,
                            year = year,
                            questionText = qText,
                            questionType = type,
                            options = listOpts,
                            correctOptions = correctIdx,
                            correctNumericalRange = null,
                            explanation = "Although the Secant method does not require derivatives, its rate of convergence (approx 1.618) is strictly slower than the Newton-Raphson quadratic rate (2.0), making Option C incorrect. Option D is a mandatory interval constraint.",
                            formulasUsed = "Convergence orders, Convexity integration adjustments",
                            shortcutTricks = "While the secant method is computationally cheaper per step, its convergence order is 1.62, which is slower than Newton-Raphson's 2.0. This flags Option C immediately.",
                            relatedConcepts = "Newton-Raphson convergence, Convexity errors, Simpson intervals",
                            difficulty = difficulty
                        )
                    }
                    QuestionType.NAT -> {
                        // Integrate x^3 from 0 to 2, h = 1.
                        // f(0) = 0, f(1) = 1, f(2) = 8
                        // Trapezoidal: (h/2) * [f(0) + 2*f(1) + f(2)] = (1/2) * [0 + 2*1 + 8] = 5.0
                        val answer = 5.0

                        val qText = """
                            Evaluate the integral:
                            \[ I = \int_{0}^{2} x^3 \; dx \]
                            using the Trapezoidal numerical integration rule with a step size of h = 1.
                            Compute the final numerical integration value.
                        """.trimIndent()

                        GateQuestion(
                            id = qId,
                            subjectId = subjectId,
                            topicId = topicId,
                            subtopicId = subtopicId,
                            year = year,
                            questionText = qText,
                            questionType = type,
                            options = null,
                            correctOptions = null,
                            correctNumericalRange = (answer - 0.01)..(answer + 0.01),
                            explanation = "With h = 1, the intervals are [0, 1] and [1, 2]. Evaluated function points are: f(0) = 0, f(1) = 1, f(2) = 8. Applying the Trapezoidal rule formula: I = (h / 2) * [f(0) + 2*f(1) + f(2)] = (1 / 2) * [0 + 2*(1) + 8] = (1 / 2) * 10 = 5.0.",
                            formulasUsed = "Trapezoidal Rule: I = (h/2) * [y_0 + 2*Σ(y_i) + y_n]",
                            shortcutTricks = "Compute and map the points separately. Since h is 1, the multiplier is simply 1/2. Keep the arithmetic simple.",
                            relatedConcepts = "Numerical quadrature, Trapezoidal intervals, Integration approximations",
                            difficulty = difficulty
                        )
                    }
                }
            }
            else -> {
                // Return default math question
                val a = 2 + rand.nextInt(5)
                val b = 1 + rand.nextInt(4)
                val c = 2 + rand.nextInt(4)
                val d = 3 + rand.nextInt(5)
                val det = a * d - b * c
                val trace = a + d

                val qText = """
                    Consider a standard 2x2 system representing localized factors of "$subtopicName":
                    \[ A = \begin{pmatrix} $a & $b \\ $c & $d \end{pmatrix} \]
                    Which of the following values represents the correct trace and determinant pair [Trace, Det] of matrix A?
                """.trimIndent()

                val optA = "Trace = $trace, Det = $det"
                val optB = "Trace = ${trace + 2}, Det = $det"
                val optC = "Trace = $trace, Det = ${det - 4}"
                val optD = "Trace = ${trace - 1}, Det = ${det + 3}"

                val options = listOf(optA, optB, optC, optD).shuffled(rand)
                val correctIndex = options.indexOf(optA)

                GateQuestion(
                    id = qId,
                    subjectId = subjectId,
                    topicId = topicId,
                    subtopicId = subtopicId,
                    year = year,
                    questionText = qText,
                    questionType = type,
                    options = options,
                    correctOptions = listOf(correctIndex),
                    correctNumericalRange = null,
                    explanation = "Trace is the sum of diagonal elements: $a + $d = $trace. Determinant is calculated as (a*d - b*c): ($a * $d) - ($b * $c) = ${a*d} - ${b*c} = $det.",
                    formulasUsed = "Trace(A) = a + d; Det(A) = ad - bc",
                    shortcutTricks = "Always calculate the Trace first as diagonal summation is instantaneous. This eliminates incorrect options quickly.",
                    relatedConcepts = "Linear Algebra, Determinants, Trace properties",
                    difficulty = difficulty
                )
            }
        }
    }

    private fun generateControlSystemsQuestion(
        subjectId: String,
        topicId: String,
        subtopicId: String,
        subtopicName: String,
        index: Int,
        year: Int,
        type: QuestionType,
        difficulty: String,
        rand: Random
    ): GateQuestion {
        val qId = "proc_cs_${subtopicId}_$index"

        return when (subtopicId) {
            "cs_mm_tf" -> {
                when (type) {
                    QuestionType.MCQ -> {
                        val g1 = 2 + rand.nextInt(7) // 2 to 8
                        val g2 = 3 + rand.nextInt(6) // 3 to 8
                        val h1 = 1 + rand.nextInt(4) // 1 to 4
                        val closedLoopDen = 1 + g1 * g2 * h1
                        val closedLoopNum = g1 * g2

                        val qText = """
                            Given a negative feedback control system representing model segments of "$subtopicName", where the forward path transfer function is G(s) = $g1 * $g2 and the feedback path is H(s) = $h1. 
                            Determine the closed-loop transfer function T(s) under standard negative unity/non-unity back coupling.
                        """.trimIndent()

                        val optA = "T(s) = $closedLoopNum / (1 + ${closedLoopNum * h1})"
                        val optB = "T(s) = $closedLoopNum / (1 - ${closedLoopNum * h1})"
                        val optC = "T(s) = $closedLoopNum / ${closedLoopNum * h1}"
                        val optD = "T(s) = 1 / (1 + $closedLoopDen)"

                        val options = listOf(optA, optB, optC, optD).shuffled(rand)
                        val correctIndex = options.indexOf(optA)

                        GateQuestion(
                            id = qId,
                            subjectId = subjectId,
                            topicId = topicId,
                            subtopicId = subtopicId,
                            year = year,
                            questionText = qText,
                            questionType = type,
                            options = options,
                            correctOptions = listOf(correctIndex),
                            correctNumericalRange = null,
                            explanation = "The general transfer function for a negative feedback system is T(s) = G(s) / (1 + G(s)H(s)). Substituting G(s) = ${g1 * g2} = $closedLoopNum and H(s) = $h1 gives T(s) = $closedLoopNum / (1 + $closedLoopNum * $h1) = $closedLoopNum / (1 + ${closedLoopNum * h1}).",
                            formulasUsed = "T(s) = G(s) / (1 + G(s)H(s))",
                            shortcutTricks = "For negative feedback, the denominator has a plus sign (+). Always check the sign first to eliminate choices.",
                            relatedConcepts = "Block Diagram Reduction, Loop Gain, Feedback Principles",
                            difficulty = difficulty
                        )
                    }
                    QuestionType.MSQ -> {
                        val qText = """
                            Which of the following assertions about Signal Flow Graphs (SFG) and Block Diagram Reduction is/are correct? (Select all that apply)
                        """.trimIndent()

                        val optA = "Mason's Gain Formula is directly applicable to both simple loop and multi-loop linear systems of any order."
                        val optB = "The transfer function obtained from Block Diagram reduction is mathematically identical to that obtained from Mason's Gain Formula."
                        val optC = "A loop is defined as a closed path that originates and terminates at the same node without touching any node more than once."
                        val optD = "Signal Flow Graphs can only be used to model non-linear dynamic feedback loops."

                        val listOpts = listOf(optA, optB, optC, optD)
                        val correctIdx = listOf(0, 1, 2)

                        GateQuestion(
                            id = qId,
                            subjectId = subjectId,
                            topicId = topicId,
                            subtopicId = subtopicId,
                            year = year,
                            questionText = qText,
                            questionType = type,
                            options = listOpts,
                            correctOptions = correctIdx,
                            correctNumericalRange = null,
                            explanation = "Signal Flow Graphs are based on linear equations, and Mason's gain formula applies strictly to LTI (Linear Time-Invariant) systems, making Option D false. Rules of reduction are exact and yield identical analytical formulations.",
                            formulasUsed = "Mason's Gain: T = Σ(P_k * Δ_k) / Δ",
                            shortcutTricks = "Eliminate statements claiming SFG/Block reduction models non-linear systems or produce different transfer functions.",
                            relatedConcepts = "SFG Reduction, Non-touching loops, LTI analysis",
                            difficulty = difficulty
                        )
                    }
                    QuestionType.NAT -> {
                        val g1 = 2 + rand.nextInt(5)
                        val g2 = 3 + rand.nextInt(4)
                        val answer = (g1 * g2).toDouble()

                        val qText = """
                            Consider a simple cascade of two non-interacting forward-path gain blocks G1 = $g1 and G2 = $g2 in a system representing "$subtopicName".
                            What is the overall open-loop forward path gain of this combined model?
                        """.trimIndent()

                        GateQuestion(
                            id = qId,
                            subjectId = subjectId,
                            topicId = topicId,
                            subtopicId = subtopicId,
                            year = year,
                            questionText = qText,
                            questionType = type,
                            options = null,
                            correctOptions = null,
                            correctNumericalRange = (answer - 0.01)..(answer + 0.01),
                            explanation = "For non-interacting cascade systems, the overall transfer function or gain is simply the product of the individual gains: G_total = G1 * G2 = $g1 * $g2 = $answer.",
                            formulasUsed = "G_total(s) = G1(s) * G2(s)",
                            shortcutTricks = "Multiply the individual transfer functions together. Cascade means direct multiplication.",
                            relatedConcepts = "Cascade gain configurations, Gain modeling",
                            difficulty = difficulty
                        )
                    }
                }
            }
            "cs_tr_order_steady" -> {
                when (type) {
                    QuestionType.MCQ -> {
                        val w = 2 * (2 + rand.nextInt(5)) // 4, 6, 8, 10, 12 rad/s
                        // Zeta = 0.5 -> wd = w * sqrt(0.75) = w * 0.866025
                        val wd = w * 0.866025
                        val tp = Math.PI / wd
                        val formattedTp = String.format(Locale.US, "%.3f", tp)

                        val qText = """
                            A standard second-order underdamped control system representing "$subtopicName" has a damping ratio (ζ) of 0.5 and natural frequency (ω_n) of $w rad/s.
                            Calculate the peak time (t_p) in seconds of the unit step time response.
                        """.trimIndent()

                        val optA = "$formattedTp sec"
                        val optB = String.format(Locale.US, "%.3f sec", tp * 1.5)
                        val optC = String.format(Locale.US, "%.3f sec", tp * 0.7)
                        val optD = String.format(Locale.US, "%.3f sec", Math.PI / w)

                        val options = listOf(optA, optB, optC, optD).shuffled(rand)
                        val correctIndex = options.indexOf(optA)

                        GateQuestion(
                            id = qId,
                            subjectId = subjectId,
                            topicId = topicId,
                            subtopicId = subtopicId,
                            year = year,
                            questionText = qText,
                            questionType = type,
                            options = options,
                            correctOptions = listOf(correctIndex),
                            correctNumericalRange = null,
                            explanation = "The peak time of an underdamped second-order system is t_p = π / ω_d. Here, damped frequency ω_d = ω_n * √(1 - ζ^2) = $w * √(1 - 0.5^2) = $w * 0.866025 = ${String.format(Locale.US, "%.4f", wd)} rad/s. Thus, t_p = π / ${String.format(Locale.US, "%.4f", wd)} = $formattedTp sec.",
                            formulasUsed = "t_p = π / ω_d; ω_d = ω_n * √(1 - ζ^2)",
                            shortcutTricks = "Damped frequency ω_d is always slightly less than natural frequency ω_n. For ζ = 0.5, ω_d is EXACTLY 0.866 * ω_n.",
                            relatedConcepts = "Transient Response, Decay frequency, Damped second-order response",
                            difficulty = difficulty
                        )
                    }
                    QuestionType.MSQ -> {
                        val qText = """
                            For a classical second-order control system characterized by damping ratio ζ, which of the following assertions is/are correct? (Select all that apply)
                        """.trimIndent()

                        val optA = "When ζ = 0, the system is completely undamped and produces infinite continuous harmonic oscillations."
                        val optB = "When 0 < ζ < 1, the step response shows a damped sinusoidal waveform decaying to 1.0."
                        val optC = "When ζ = 1, the response is critically damped, achieving the fastest response time without any overshoot."
                        val optD = "When ζ > 1, the system is overdamped, meaning it exhibits severe overshoot and rapid cycles."

                        val listOpts = listOf(optA, optB, optC, optD)
                        val correctIdx = listOf(0, 1, 2)

                        GateQuestion(
                            id = qId,
                            subjectId = subjectId,
                            topicId = topicId,
                            subtopicId = subtopicId,
                            year = year,
                            questionText = qText,
                            questionType = type,
                            options = listOpts,
                            correctOptions = correctIdx,
                            correctNumericalRange = null,
                            explanation = "For ζ > 1 (overdamped), the system is extremely sluggish, heavily damped, and exhibits absolutely zero overshoot, making Option D completely false.",
                            formulasUsed = "Characteristic response criteria",
                            shortcutTricks = "Overdamped systems never overshoot. Overshoot occurs strictly for underdamped systems (0 < ζ < 1).",
                            relatedConcepts = "Steady and Transient characteristics, Damping classifications",
                            difficulty = difficulty
                        )
                    }
                    QuestionType.NAT -> {
                        val a = 2 + rand.nextInt(5) // 2 to 6
                        val k = 10 * (2 + rand.nextInt(7)) // 20 to 80
                        val answer = a.toDouble() / k.toDouble()
                        val formattedAns = String.format(Locale.US, "%.4f", answer).toDouble()

                        val qText = """
                            A unity feedback control system modeling steady dynamics under "$subtopicName" has open-loop transfer function G(s) = $k / (s * (s + $a)).
                            Find the steady-state error (e_ss) for a unit ramp input r(t) = t.
                        """.trimIndent()

                        GateQuestion(
                            id = qId,
                            subjectId = subjectId,
                            topicId = topicId,
                            subtopicId = subtopicId,
                            year = year,
                            questionText = qText,
                            questionType = type,
                            options = null,
                            correctOptions = null,
                            correctNumericalRange = (formattedAns - 0.001)..(formattedAns + 0.001),
                            explanation = "The velocity error constant is K_v = lim_{s->0} s*G(s) = lim_{s->0} (s * $k) / (s*(s + $a)) = $k / $a. The steady-state error for a unit ramp is given by e_ss = 1 / K_v = $a / $k = $formattedAns.",
                            formulasUsed = "K_v = lim_{s->0} s*G(s); e_ss = 1 / K_v",
                            shortcutTricks = "For a Type 1 system, ramp error is directly pole speed 'a' divided by gain 'K'. Keep this simple ratio in mind.",
                            relatedConcepts = "Error constants, System Type classification, Static error coefficients",
                            difficulty = difficulty
                        )
                    }
                }
            }
            "cs_stab_routh_locus" -> {
                when (type) {
                    QuestionType.MCQ -> {
                        val a = 2 + rand.nextInt(3) // 2 to 4
                        val b = 3 + rand.nextInt(4) // 3 to 6
                        val limit = a * b

                        val qText = """
                            The characteristic equation of a unity feedback system in "$subtopicName" is given by: 
                            s^3 + $a s^2 + $b s + K = 0.
                            Determine the exact range of open-loop gain K for which the feedback loop remains strictly stable.
                        """.trimIndent()

                        val optA = "0 < K < $limit"
                        val optB = "K > $limit"
                        val optC = "0 < K < ${limit + 5}"
                        val optD = "K < 0"

                        val options = listOf(optA, optB, optC, optD).shuffled(rand)
                        val correctIndex = options.indexOf(optA)

                        GateQuestion(
                            id = qId,
                            subjectId = subjectId,
                            topicId = topicId,
                            subtopicId = subtopicId,
                            year = year,
                            questionText = qText,
                            questionType = type,
                            options = options,
                            correctOptions = listOf(correctIndex),
                            correctNumericalRange = null,
                            explanation = "By Routh-Hurwitz criterion, for a cubic polynomial s^3 + a*s^2 + b*s + K = 0, stability requires all coefficients positive (K > 0) and the inner product exceeds the outer product: a * b > K. Therefore, K must be in the range 0 < K < $limit.",
                            formulasUsed = "Routh stability row entries, Inner-Outer coefficient product",
                            shortcutTricks = "For a standard s^3 third-order system, stability limits can be found instantaneously by checking if K is bounded by the product of the intermediate coefficients.",
                            relatedConcepts = "Routh-Hurwitz Stability Criterion, Boundary oscillations",
                            difficulty = difficulty
                        )
                    }
                    QuestionType.MSQ -> {
                        val qText = """
                            Regarding the classical Root Locus plotting technique, which of the following assertions are correct? (Select all that apply)
                        """.trimIndent()

                        val optA = "The root locus is always completely symmetric with respect to the real axis of the s-plane."
                        val optB = "The branches of the root locus start at open-loop poles (K=0) and terminate at open-loop zeros (K=∞)."
                        val optC = "The centroid σ representing the intersection of asymptotes is calculated as (Σ Real Parts of Poles - Σ Real Parts of Zeros) / (P - Z)."
                        val optD = "Root locus can have branches that cross into the right half of the s-plane, which signifies stable decay."

                        val listOpts = listOf(optA, optB, optC, optD)
                        val correctIdx = listOf(0, 1, 2)

                        GateQuestion(
                            id = qId,
                            subjectId = subjectId,
                            topicId = topicId,
                            subtopicId = subtopicId,
                            year = year,
                            questionText = qText,
                            questionType = type,
                            options = listOpts,
                            correctOptions = correctIdx,
                            correctNumericalRange = null,
                            explanation = "The right half of the s-plane signifies unstable system behavior, not stable decay, making Option D false. Centroids and asymptotes follow exact complex calculus definitions.",
                            formulasUsed = "σ = (Σ p_i - Σ z_j) / (P - Z)",
                            shortcutTricks = "Observe poles and zeros counts. Asymptote lines always guide branches towards infinity when P > Z.",
                            relatedConcepts = "Root Locus rules, Asymptote centroid, Complex Pole locations",
                            difficulty = difficulty
                        )
                    }
                    QuestionType.NAT -> {
                        val p1 = 1 + rand.nextInt(3) // 1, 2, 3
                        val p2 = 4 + rand.nextInt(3) // 4, 5, 6
                        val z1 = 1 // Zero at -1
                        val centroid = ((-p1 - p2).toDouble() - (-z1).toDouble()) / 2.0
                        val roundedAns = String.format(Locale.US, "%.2f", centroid).toDouble()

                        val qText = """
                            A feedback loop under "$subtopicName" has open-loop system poles at s = 0, s = -$p1, and s = -$p2, and a single zero at s = -$z1. 
                            Calculate the coordinate of the root locus asymptote intersection (centroid, σ) on the real axis of the s-plane.
                        """.trimIndent()

                        GateQuestion(
                            id = qId,
                            subjectId = subjectId,
                            topicId = topicId,
                            subtopicId = subtopicId,
                            year = year,
                            questionText = qText,
                            questionType = type,
                            options = null,
                            correctOptions = null,
                            correctNumericalRange = (roundedAns - 0.05)..(roundedAns + 0.05),
                            explanation = "The centroid σ is computed as ((Σ Real parts of Poles) - (Σ Real parts of Zeros)) / (P - Z). Here, Poles = {0, -$p1, -$p2} with sum = -$p1 - $p2 = -${p1 + p2}. Zero = {-$z1} with sum = -$z1. Count P = 3, Z = 1. Therefore σ = (-${p1 + p2} - (-$z1)) / (3 - 1) = (-${p1 + p2 - z1}) / 2 = $roundedAns.",
                            formulasUsed = "σ = (Σ Real Poles - Σ Real Zeros) / (P - Z)",
                            shortcutTricks = "P - Z gives the number of asymptotes. Subtract zero coordinates from pole coordinates carefully making sure to respect negative signs.",
                            relatedConcepts = "Centroid calculations, Root Locus asymptotes",
                            difficulty = difficulty
                        )
                    }
                }
            }
            "cs_fr_plots" -> {
                when (type) {
                    QuestionType.MCQ -> {
                        val a = 2 + rand.nextInt(3) // 2 to 4
                        val b = 5 + rand.nextInt(4) // 5 to 8
                        val wpc = Math.sqrt((a * b).toDouble())
                        val formattedWpc = String.format(Locale.US, "%.2f", wpc)

                        val qText = """
                            A control system in "$subtopicName" has an open-loop phase lag configuration represented by:
                            G(s)H(s) = K / (s * (s + $a) * (s + $b)).
                            Find the phase crossover frequency (ω_pc) in rad/s of this frequency feed.
                        """.trimIndent()

                        val optA = "$formattedWpc rad/s"
                        val optB = String.format(Locale.US, "%.2f rad/s", wpc + 2.0)
                        val optC = String.format(Locale.US, "%.2f rad/s", wpc * 0.5)
                        val optD = String.format(Locale.US, "%.2f rad/s", (a + b).toDouble())

                        val options = listOf(optA, optB, optC, optD).shuffled(rand)
                        val correctIndex = options.indexOf(optA)

                        GateQuestion(
                            id = qId,
                            subjectId = subjectId,
                            topicId = topicId,
                            subtopicId = subtopicId,
                            year = year,
                            questionText = qText,
                            questionType = type,
                            options = options,
                            correctOptions = listOf(correctIndex),
                            correctNumericalRange = null,
                            explanation = "The phase crossover frequency is where the phase angle is exactly -180°. For G(jω) = K / (jω * (jω + $a) * (jω + $b)), the phase is -90° - tan^{-1}(ω/$a) - tan^{-1}(ω/$b) = -180° => tan^{-1}(ω/$a) + tan^{-1}(ω/$b) = 90°. This simplifies to ω_pc^2 = $a * $b. Thus ω_pc = √($a * $b) = √${a * b} = $formattedWpc rad/s.",
                            formulasUsed = "ω_pc = √(Pole1 * Pole2) for Type 1 third-order systems",
                            shortcutTricks = "For a Type 1 system with poles at -a and -b, the phase crossover frequency is simply the geometric mean of the two poles, i.e., √(a * b). Save time by computing this immediately.",
                            relatedConcepts = "Phase crossover frequency, Polar plot phase criteria, Stability margins",
                            difficulty = difficulty
                        )
                    }
                    QuestionType.MSQ -> {
                        val qText = """
                            Which of the following properties is/are correct regarding Bode Plots and Nyquist stability criteria? (Select all that apply)
                        """.trimIndent()

                        val optA = "A system is strictly stable if the Gain Margin (GM) and Phase Margin (PM) are both positive."
                        val optB = "The slope of the high frequency Bode magnitude plot for a first-order factor (1/(1+T s)) is -20 dB/dec."
                        val optC = "Nyquist stability criteria relates the number of closed-loop unstable poles to encirclements of the -1 + j0 point."
                        val optD = "The gain crossover frequency is always strictly equal to the corner frequency of the slowest pole."

                        val listOpts = listOf(optA, optB, optC, optD)
                        val correctIdx = listOf(0, 1, 2)

                        GateQuestion(
                            id = qId,
                            subjectId = subjectId,
                            topicId = topicId,
                            subtopicId = subtopicId,
                            year = year,
                            questionText = qText,
                            questionType = type,
                            options = listOpts,
                            correctOptions = correctIdx,
                            correctNumericalRange = null,
                            explanation = "The gain crossover frequency depends directly on loop gain K and can be placed anywhere in the spectrum; it is not bound in any way to equals the corner frequency of the slowest pole, making Option D false.",
                            formulasUsed = "GM = 1 / |G(jω_pc)|; PM = 180° + ∠G(jω_gc)",
                            shortcutTricks = "Both margins must be positive for stability in standard minimum-phase networks. First-order poles roll off at exactly -20 dB/decade.",
                            relatedConcepts = "Bode asymptotes, Nyquist encirclement equation, Frequency stability boundaries",
                            difficulty = difficulty
                        )
                    }
                    QuestionType.NAT -> {
                        val a = 2 + rand.nextInt(3) // 2 to 4
                        val b = 5 + rand.nextInt(3) // 5 to 7
                        val total = a + b
                        val prod = a * b
                        val kStableLimit = total * prod
                        val roundedAns = kStableLimit.toDouble()

                        val qText = """
                            A unity feedback control loop modeling "$subtopicName" has open-loop transfer function G(s) = K / (s * (s + $a) * (s + $b)).
                            Calculate the limiting value of open-loop gain K at the stability boundary (equivalent to Nyquist encirclement boundary).
                        """.trimIndent()

                        GateQuestion(
                            id = qId,
                            subjectId = subjectId,
                            topicId = topicId,
                            subtopicId = subtopicId,
                            year = year,
                            questionText = qText,
                            questionType = type,
                            options = null,
                            correctOptions = null,
                            correctNumericalRange = (roundedAns - 0.1)..(roundedAns + 0.1),
                            explanation = "The characteristic equation is s^3 + ${a+b}s^2 + ${a*b}s + K = 0. According to Routh-Hurwitz, at the margin of stability, the product of the inner coefficients equals the outer coefficients: (a+b) * (a*b) = K. Thus K_limit = $total * $prod = $kStableLimit.",
                            formulasUsed = "K_limit = (a + b) * a * b",
                            shortcutTricks = "The gain margin boundary value matches the marginal stability gain under Routh-Hurwitz criteria. Compute this easily to solve frequency-response questions.",
                            relatedConcepts = "Marginal Stability Gain, Frequency crossover parameters",
                            difficulty = difficulty
                        )
                    }
                }
            }
            "cs_ctrl_pid_leadlag" -> {
                when (type) {
                    QuestionType.MCQ -> {
                        val poleSquare = when (rand.nextInt(3)) {
                            0 -> 4
                            1 -> 9
                            else -> 16
                        }
                        val wm = Math.sqrt(poleSquare.toDouble())
                        val formattedWm = String.format(Locale.US, "%.1f", wm)

                        val qText = """
                            A phase-lead compensator used in "$subtopicName" schemes is characterized by the transfer function:
                            G_c(s) = (s + 1) / (s + $poleSquare).
                            Calculate the frequency (ω_m) in rad/s at which the maximum phase lead angle is introduced by this network.
                        """.trimIndent()

                        val optA = "$formattedWm rad/s"
                        val optB = String.format(Locale.US, "%.1f rad/s", wm * 2.0)
                        val optC = String.format(Locale.US, "%.1f rad/s", wm + 1.5)
                        val optD = "1.0 rad/s"

                        val options = listOf(optA, optB, optC, optD).shuffled(rand)
                        val correctIndex = options.indexOf(optA)

                        GateQuestion(
                            id = qId,
                            subjectId = subjectId,
                            topicId = topicId,
                            subtopicId = subtopicId,
                            year = year,
                            questionText = qText,
                            questionType = type,
                            options = options,
                            correctOptions = listOf(correctIndex),
                            correctNumericalRange = null,
                            explanation = "For a standard lead compensator given as G_c(s) = (s + z) / (s + p) where z = 1 and p = $poleSquare, the frequency of maximum phase shift is the geometric mean of its pole and zero: ω_m = √(z * p) = √(1 * $poleSquare) = $wm rad/s.",
                            formulasUsed = "ω_m = √(z * p)",
                            shortcutTricks = "The peak frequency of lead networks is always the geometric mean of the corner frequencies. Just compute √(z * p) directly.",
                            relatedConcepts = "Compensations, Phase characteristics, Network peaks",
                            difficulty = difficulty
                        )
                    }
                    QuestionType.MSQ -> {
                        val qText = """
                            Which of the following propositions is/are true regarding standard compensators and PID controllers? (Select all that apply)
                        """.trimIndent()

                        val optA = "A phase-lead network increases the transient speed (bandwidth) and improves the stability of the system response."
                        val optB = "A phase-lag network reduces high frequency noise and increases steady-state accuracy by adding gain at low frequencies."
                        val optC = "The derivative component of a PID controller tends to reduce transient overshoot and improve relative stability."
                        val optD = "An integral controller alone improves bandwidth speed and decreases transient overshoot."

                        val listOpts = listOf(optA, optB, optC, optD)
                        val correctIdx = listOf(0, 1, 2)

                        GateQuestion(
                            id = qId,
                            subjectId = subjectId,
                            topicId = topicId,
                            subtopicId = subtopicId,
                            year = year,
                            questionText = qText,
                            questionType = type,
                            options = listOpts,
                            correctOptions = correctIdx,
                            correctNumericalRange = null,
                            explanation = "An integral controller (I-control) slow down the system, decreases the bandwidth, and usually INCREASES overshoot/oscillatory trends, making Option D false.",
                            formulasUsed = "Controller transient influences",
                            shortcutTricks = "Integral actions (I) improve error but decay transient speed. Derivative actions (D) improve transients but amplify high-frequency noise.",
                            relatedConcepts = "Controller behaviors, Compensator differences",
                            difficulty = difficulty
                        )
                    }
                    QuestionType.NAT -> {
                        // Let alpha = 0.25 -> sin(phi_m) = (1-0.25)/(1+0.25) = 0.75 / 1.25 = 0.6
                        // phi_m = sin^-1(0.6) = 36.87 degrees
                        val answer = 36.87

                        val qText = """
                            A standard design of a lead compensator under "$subtopicName" features the transfer function:
                            G_c(s) = (1 + s) / (1 + 0.25 s).
                            Determine the maximum phase lead (φ_m) in degrees introduced by this compensator.
                        """.trimIndent()

                        GateQuestion(
                            id = qId,
                            subjectId = subjectId,
                            topicId = topicId,
                            subtopicId = subtopicId,
                            year = year,
                            questionText = qText,
                            questionType = type,
                            options = null,
                            correctOptions = null,
                            correctNumericalRange = (answer - 0.1)..(answer + 0.1),
                            explanation = "The general form is G_c(s) = (1 + T s) / (1 + α T s) where T = 1, and α = 0.25. The peak lead angle matches: sin(φ_m) = (1 - α) / (1 + α) = (1 - 0.25) / (1 + 0.25) = 0.75 / 1.25 = 0.6. Computing the inverse sine gives φ_m = sin^{-1}(0.6) = 36.87 degrees.",
                            formulasUsed = "sin(φ_m) = (1 - α) / (1 + α)",
                            shortcutTricks = "Remember standard values! If α = 0.25, sin(φ_m) is exactly 0.6 => φ_m is exactly 36.87°. If α = 0.33, sin(φ_m) leads to 30.0°.",
                            relatedConcepts = "Maximum phase angle, Lead properties",
                            difficulty = difficulty
                        )
                    }
                }
            }
            "cs_ss_variables" -> {
                when (type) {
                    QuestionType.MCQ -> {
                        val a2 = 2 + rand.nextInt(5)

                        val qText = """
                            A state variable model representing a plant under "$subtopicName" is defined as:
                            x_dot = A x + B u
                            where matrix A = [[0, 1], [-5, -$a2]] and vector B = [[0], [1]].
                            Find the determinant of the controllability matrix Q_c = [B   AB].
                        """.trimIndent()

                        val optA = "-1"
                        val optB = "0"
                        val optC = "$a2"
                        val optD = "5"

                        val options = listOf(optA, optB, optC, optD).shuffled(rand)
                        val correctIndex = options.indexOf(optA)

                        GateQuestion(
                            id = qId,
                            subjectId = subjectId,
                            topicId = topicId,
                            subtopicId = subtopicId,
                            year = year,
                            questionText = qText,
                            questionType = type,
                            options = options,
                            correctOptions = listOf(correctIndex),
                            correctNumericalRange = null,
                            explanation = "Vector B = [0; 1]. State product AB = [0 1; -5 -$a2]*[0; 1] = [1; -$a2]. Controllability matrix is Q_c = [B, AB] = [[0, 1], [1, -$a2]]. Its determinant is det(Q_c) = 0*(-$a2) - 1*1 = -1.",
                            formulasUsed = "Q_c = [B | AB]; det(Q_c)",
                            shortcutTricks = "For any system in controllable canonical form, Q_c always has full rank, and det(Q_c) is precisely -1 or 1.",
                            relatedConcepts = "Controllability Matrix, Canonical variables",
                            difficulty = difficulty
                        )
                    }
                    QuestionType.MSQ -> {
                        val qText = """
                            Which of the following properties is/are correct regarding state space mathematical models of dynamical systems? (Select all that apply)
                        """.trimIndent()

                        val optA = "The state transition matrix φ(t) can be calculated as the inverse Laplace transform: L^{-1}{(sI - A)^{-1}}."
                        val optB = "At t = 0, the state transition matrix φ(0) simplifies exactly to the identity matrix I."
                        val optC = "The eigenvalues of the state matrix A are mathematically identical to the closed-loop system poles."
                        val optD = "The state transition matrix must always evaluate to zero at any negative time intervals."

                        val listOpts = listOf(optA, optB, optC, optD)
                        val correctIdx = listOf(0, 1, 2)

                        GateQuestion(
                            id = qId,
                            subjectId = subjectId,
                            topicId = topicId,
                            subtopicId = subtopicId,
                            year = year,
                            questionText = qText,
                            questionType = type,
                            options = listOpts,
                            correctOptions = correctIdx,
                            correctNumericalRange = null,
                            explanation = "The state transition matrix is defined as e^{A t}. It does not vanish for negative times (e^{-A t} is perfectly valid representing reverse time integration), making Option D false.",
                            formulasUsed = "φ(t) = e^{At}; det(sI - A) = 0",
                            shortcutTricks = "Identify properties that violate the exponential definition of state translation: φ(t1 + t2) = φ(t1)φ(t2). This verifies identities easily.",
                            relatedConcepts = "State transition matrix, System poles, Exponential equations",
                            difficulty = difficulty
                        )
                    }
                    QuestionType.NAT -> {
                        val x = 2 + rand.nextInt(3) // 2 to 4
                        val y = 5 + rand.nextInt(4) // 5 to 8
                        val answer = y.toDouble()

                        val qText = """
                            Given a diagonal state-space representation matrix representing "$subtopicName":
                            A = [[$x, 0], [0, $y]].
                            Calculate the largest magnitude eigenvalue of this model's state matrix.
                        """.trimIndent()

                        GateQuestion(
                            id = qId,
                            subjectId = subjectId,
                            topicId = topicId,
                            subtopicId = subtopicId,
                            year = year,
                            questionText = qText,
                            questionType = type,
                            options = null,
                            correctOptions = null,
                            correctNumericalRange = (answer - 0.01)..(answer + 0.01),
                            explanation = "For a diagonal matrix, the eigenvalues are simply the diagonal entries themselves. Here, eigenvalues are $x and $y. The largest magnitude eigenvalue is max($x, $y) = $y.",
                            formulasUsed = "det(λI - A) = 0 => (λ - x)(λ - y) = 0",
                            shortcutTricks = "Save computation time: the eigenvalues of any diagonal or triangular matrix are strictly its diagonal elements. Just select the maximum value.",
                            relatedConcepts = "S-plane poles, Eigenvalue dynamics",
                            difficulty = difficulty
                        )
                    }
                }
            }
            else -> {
                // Return default control question
                val g1 = 2 + rand.nextInt(5)
                val g2 = 3 + rand.nextInt(4)
                val answer = (g1 * g2).toDouble()

                val qText = """
                    Consider a simple cascade of two non-interacting forward-path gain blocks G1 = $g1 and G2 = $g2 in a system representing "$subtopicName".
                    What is the overall open-loop forward path gain of this combined model?
                """.trimIndent()

                GateQuestion(
                    id = qId,
                    subjectId = subjectId,
                    topicId = topicId,
                    subtopicId = subtopicId,
                    year = year,
                    questionText = qText,
                    questionType = type,
                    options = null,
                    correctOptions = null,
                    correctNumericalRange = (answer - 0.01)..(answer + 0.01),
                    explanation = "For non-interacting cascade systems, the overall transfer function or gain is simply the product of the individual gains: G_total = G1 * G2 = $g1 * $g2 = $answer.",
                    formulasUsed = "G_total(s) = G1(s) * G2(s)",
                    shortcutTricks = "Multiply the individual transfer functions together. Cascade means direct multiplication.",
                    relatedConcepts = "Cascade gain configurations, Gain modeling",
                    difficulty = difficulty
                )
            }
        }
    }

    private fun generateElectricalQuestion(
        subjectId: String,
        topicId: String,
        subtopicId: String,
        subtopicName: String,
        index: Int,
        year: Int,
        type: QuestionType,
        difficulty: String,
        rand: Random
    ): GateQuestion {
        val qId = "proc_elec_${subtopicId}_$index"

        return when (type) {
            QuestionType.MCQ -> {
                val r1 = 5 * (2 + rand.nextInt(10)) // 10 to 60 ohms
                val r2 = 5 * (2 + rand.nextInt(10))
                val v = 10 * (1 + rand.nextInt(12)) // 10 to 120 V
                val iTotal = v.toDouble() / (r1 + r2)
                val v2 = iTotal * r2
                val formattedV2 = String.format(Locale.US, "%.2f", v2)

                val qText = """
                    An electrical circuit configuration representing "$subtopicName" contains a DC voltage source of $v V connected in series with two resistors, $r1 Ω and $r2 Ω. 
                    What is the steady-state load voltage drop (in Volts) across the $r2 Ω resistor?
                """.trimIndent()

                val optA = "$formattedV2 V"
                val optB = String.format(Locale.US, "%.2f V", v2 * 0.8)
                val optC = String.format(Locale.US, "%.2f V", v2 * 1.2)
                val optD = String.format(Locale.US, "%.2f V", (v.toDouble() - v2))

                val options = listOf(optA, optB, optC, optD).shuffled(rand)
                val correctIndex = options.indexOf(optA)

                GateQuestion(
                    id = qId,
                    subjectId = subjectId,
                    topicId = topicId,
                    subtopicId = subtopicId,
                    year = year,
                    questionText = qText,
                    questionType = type,
                    options = options,
                    correctOptions = listOf(correctIndex),
                    correctNumericalRange = null,
                    explanation = "According to the Voltage Division Rule, the voltage drop across $r2 Ω is given by: V_2 = V * (R2 / (R1 + R2)) = $v * ($r2 / ($r1 + $r2)) = $v * ($r2 / ${r1 + r2}) = $formattedV2 V.",
                    formulasUsed = "V_out = V_in * (R_target / R_total)",
                    shortcutTricks = "Resistors in series divide voltage in direct proportion to their resistance values. Estimate the ratio immediately.",
                    relatedConcepts = "Network Theory, KVL, Series Circuit Voltage Division",
                    difficulty = difficulty
                )
            }
            QuestionType.MSQ -> {
                val qText = """
                    Under standard electrical AC operations related to "$subtopicName" frameworks, which of the following assertions about resonant RLC networks is/are correct? (Select all that apply)
                """.trimIndent()

                val optA = "At resonance, the network input impedance is purely resistive and reaches its minimum value."
                val optB = "The voltage across the inductor and capacitor can exceed the source voltage amplitude at high Q values."
                val optC = "Resonance occurs strictly when inductive reactance equals capacitive reactance magnitude."
                val optD = "The system power factor is exactly zero at the resonant peak."

                val listOpts = listOf(optA, optB, optC, optD)
                // A, B, and C are correct. D is false since power factor is exactly 1 (unity) at resonance when impedance is purely resistive.
                val correctIdx = listOf(0, 1, 2)

                GateQuestion(
                    id = qId,
                    subjectId = subjectId,
                    topicId = topicId,
                    subtopicId = subtopicId,
                    year = year,
                    questionText = qText,
                    questionType = type,
                    options = listOpts,
                    correctOptions = correctIdx,
                    correctNumericalRange = null,
                    explanation = "At resonance, X_L = X_C, making the imaginary part of circuit impedance zero, so Z = R (minimum and real resistive). This creates a unity power factor (PF = cos(0) = 1.0). High quality factors Q cause voltage magnification across L and C scaling above source power.",
                    formulasUsed = "ω_0 = 1 / √(LC); Power Factor = cos(θ_z)",
                    shortcutTricks = "Resonance implies real alignment; hence, reactance vanishes, power factor must become unity (1.0). Never choose zero power factor.",
                    relatedConcepts = "AC Circuits, Resonance Impedance, Power Magnification",
                    difficulty = difficulty
                )
            }
            QuestionType.NAT -> {
                val turnsPrimary = 100 * (1 + rand.nextInt(10)) // 100 to 1000
                val ratio = 2 + rand.nextInt(9) // step down ratio (2 to 10)
                val secondaryTurns = turnsPrimary / ratio
                val inputVoltage = 20 * ratio * (2 + rand.nextInt(5)) // e.g. 240V
                val outputVoltage = inputVoltage.toDouble() / ratio
                val roundedAnswer = String.format(Locale.US, "%.1f", outputVoltage).toDouble()

                val qText = """
                    A power transformer modeling step-down properties in "$subtopicName" has exactly $turnsPrimary turns on the primary winding and $secondaryTurns turns on the secondary winding. 
                    If the primary side is excited by an RMS AC voltage source of $inputVoltage V, calculate the induced secondary RMS terminal voltage (in Volts).
                """.trimIndent()

                GateQuestion(
                    id = qId,
                    subjectId = subjectId,
                    topicId = topicId,
                    subtopicId = subtopicId,
                    year = year,
                    questionText = qText,
                    questionType = type,
                    options = null,
                    correctOptions = null,
                    correctNumericalRange = (roundedAnswer - 0.1)..(roundedAnswer + 0.1),
                    explanation = "The transformer voltage relation is given by: V_p / V_s = N_p / N_s. In this circuit, the turn ratio is $turnsPrimary / $secondaryTurns = $ratio. Therefore, secondary terminal voltage is V_s = V_p / ratio = $inputVoltage / $ratio = $roundedAnswer V.",
                    formulasUsed = "V_s = V_p * (N_s / N_p)",
                    shortcutTricks = "The step-down factor is simply the ratio of turns ($ratio). Divide input voltage by this integer immediately.",
                    relatedConcepts = "Transformers, Inductive coupling, Step-down machines",
                    difficulty = difficulty
                )
            }
        }
    }

    private fun generateReasoningQuestion(
        subjectId: String,
        topicId: String,
        subtopicId: String,
        subtopicName: String,
        index: Int,
        year: Int,
        type: QuestionType,
        difficulty: String,
        rand: Random
    ): GateQuestion {
        val qId = "proc_reasoning_${subtopicId}_$index"

        return when (type) {
            QuestionType.MCQ -> {
                if (index % 2 == 0) {
                    val stepsList = listOf(3, 4, 5, 6)
                    val step = stepsList[index % stepsList.size]
                    val start = 2 + rand.nextInt(10)
                    val val1 = start
                    val val2 = val1 + step
                    val val3 = val2 + step + 1
                    val val4 = val3 + step + 2
                    val val5 = val4 + step + 3
                    val expected = val5 + step + 4
                    
                    val qText = """
                        Find the missing number in the following analytical number series:
                        
                        $val1, $val2, $val3, $val4, $val5, ?
                        
                        This series represents an increasing difference sequence under logical analysis for "$subtopicName".
                    """.trimIndent()
                    
                    val optCorrect = "$expected"
                    val optWrong1 = "${expected - 2}"
                    val optWrong2 = "${expected + 2}"
                    val optWrong3 = "${expected + step}"
                    
                    val options = listOf(optCorrect, optWrong1, optWrong2, optWrong3).shuffled(rand)
                    val correctIdx = options.indexOf(optCorrect)
                    
                    GateQuestion(
                        id = qId,
                        subjectId = subjectId,
                        topicId = topicId,
                        subtopicId = subtopicId,
                        year = year,
                        questionText = qText,
                        questionType = type,
                        options = options,
                        correctOptions = listOf(correctIdx),
                        correctNumericalRange = null,
                        explanation = "The first-order differences of the series are: ($val2 - $val1) = $step, ($val3 - $val2) = ${step + 1}, ($val4 - $val3) = ${step + 2}, ($val5 - $val4) = ${step + 3}. The second-order difference is constantly +1. Therefore, the next first-order difference should be ${step + 4}. The missing term is $val5 + ${step + 4} = $expected.",
                        formulasUsed = "Arithmetic Progression of First-Order Differences",
                        shortcutTricks = "Identify first-order differences: they increase by 1 at each step. Add the next increment to the last term.",
                        relatedConcepts = "Number Series, Logical Patterns, Induction",
                        difficulty = difficulty
                    )
                } else {
                    val professions = listOf(
                        Pair("Programmers", "Engineers"),
                        Pair("Chemists", "Scientists"),
                        Pair("Architects", "Designers"),
                        Pair("Aeronauts", "Pilots")
                    )
                    val pair = professions[index % professions.size]
                    val p1 = pair.first
                    val p2 = pair.second
                    
                    val qText = """
                        Consider the following logical assertions under "$subtopicName" rules:
                        1. All $p1 are $p2.
                        2. Some $p2 are managers.
                        
                        Which of the following conclusions logically follows with absolute 100% certainty?
                    """.trimIndent()
                    
                    val optCorrect = "None of the other statements logically follow with absolute certainty."
                    val optWrong1 = "Some $p1 are managers."
                    val optWrong2 = "All $p1 are managers."
                    val optWrong3 = "No $p1 are managers."
                    
                    val options = listOf(optCorrect, optWrong1, optWrong2, optWrong3).shuffled(rand)
                    val correctIdx = options.indexOf(optCorrect)
                    
                    GateQuestion(
                        id = qId,
                        subjectId = subjectId,
                        topicId = topicId,
                        subtopicId = subtopicId,
                        year = year,
                        questionText = qText,
                        questionType = type,
                        options = options,
                        correctOptions = listOf(correctIdx),
                        correctNumericalRange = null,
                        explanation = "Set P ($p1) is a subset of E ($p2). Set E overlaps with M (managers). This does not guarantee that Set P overlaps with M. It is entirely possible for P and M to be disjoint while satisfying both premises. Thus, none of the specific assertions logically follow with absolute certainties.",
                        formulasUsed = "Set inclusions, Euler/Venn representations",
                        shortcutTricks = "Draw a Venn/Euler diagram placing $p1 entirely inside $p2, and overlapping 'managers' with $p2 without touching $p1. This disproves all other choices.",
                        relatedConcepts = "Syllogistic logic, Venn Diagrams, Validity",
                        difficulty = difficulty
                    )
                }
            }
            QuestionType.MSQ -> {
                val items = listOf(
                    Triple("Gears", "Mechanisms", "Metal"),
                    Triple("Resistors", "Components", "Passive"),
                    Triple("Capacitors", "Devices", "Linear"),
                    Triple("Compilers", "Translators", "Software")
                )
                val sel = items[index % items.size]
                val t1 = sel.first
                val t2 = sel.second
                val t3 = sel.third
                
                val qText = """
                    Given the following premises under "$subtopicName":
                    1. All $t1 are $t2.
                    2. No $t2 is $t3.
                    
                    Select all conclusions that are logically valid based strictly on the premises:
                """.trimIndent()
                
                val optA = "No $t1 is $t3."
                val optB = "Some $t2 are $t1."
                val optC = "No $t3 is $t1."
                val optD = "Some $t1 are $t3."
                
                val listOpts = listOf(optA, optB, optC, optD)
                val correctIdx = listOf(0, 1, 2)
                
                GateQuestion(
                    id = qId,
                    subjectId = subjectId,
                    topicId = topicId,
                    subtopicId = subtopicId,
                    year = year,
                    questionText = qText,
                    questionType = type,
                    options = listOpts,
                    correctOptions = correctIdx,
                    correctNumericalRange = null,
                    explanation = "Premise 1 states $t1 is a subset of $t2. Premise 2 states $t2 and $t3 are disjoint. Since $t1 is entirely inside $t2, it cannot have any overlap with $t3. Therefore, 'No $t1 is $t3' and 'No $t3 is $t1' are both logically certain. Also, since all $t1 are $t2, there are definitely 'Some $t2 are $t1'. Hence, options A, B, and C are valid.",
                    formulasUsed = "Universal categorical exclusion",
                    shortcutTricks = "If A is inside B, and B is external to C, then A must be completely external to C.",
                    relatedConcepts = "Syllogisms, Contrapositive logic",
                    difficulty = difficulty
                )
            }
            QuestionType.NAT -> {
                if (index % 2 == 0) {
                    val totalStudents = 20 + rand.nextInt(30)
                    val positionFromLeft = 5 + rand.nextInt(15)
                    val positionFromRight = totalStudents - positionFromLeft + 1
                    
                    val qText = """
                        In a class of $totalStudents engineering aspirants preparing for "$subtopicName", 
                        Rahul is ranked $positionFromLeft from the top. 
                        What is his rank from the bottom?
                    """.trimIndent()
                    
                    val answer = positionFromRight.toDouble()
                    
                    GateQuestion(
                        id = qId,
                        subjectId = subjectId,
                        topicId = topicId,
                        subtopicId = subtopicId,
                        year = year,
                        questionText = qText,
                        questionType = type,
                        options = null,
                        correctOptions = null,
                        correctNumericalRange = (answer - 0.01)..(answer + 0.01),
                        explanation = "The standard ranking relation is specified by the formula: Total = Rank from Top + Rank from Bottom - 1. Substituting given values: $totalStudents = $positionFromLeft + Rank_Bottom - 1. Therefore, Rank_Bottom = $totalStudents - $positionFromLeft + 1 = $positionFromRight.",
                        formulasUsed = "Total count = Pos_Left + Pos_Right - 1",
                        shortcutTricks = "Subtract the rank from the total number of people and add 1.",
                        relatedConcepts = "Linear arrangements, Ranking, Order puzzles",
                        difficulty = difficulty
                    )
                } else {
                    val chars = listOf("GATE", "NPTEL", "MATH", "EEE")
                    val str = chars[index % chars.size]
                    val sum = str.map { it.code - 64 }.sum()
                    
                    val qText = """
                        If each letter of the alphabet is assigned a number based on its alphabetic position (A=1, B=2, ..., Z=26), 
                        the analytical code sum represented by a word is the sum of its letters' positions. 
                        
                        Find the exact analytical code sum for the word "$str" under these rules.
                    """.trimIndent()
                    
                    val answer = sum.toDouble()
                    
                    GateQuestion(
                        id = qId,
                        subjectId = subjectId,
                        topicId = topicId,
                        subtopicId = subtopicId,
                        year = year,
                        questionText = qText,
                        questionType = type,
                        options = null,
                        correctOptions = null,
                        correctNumericalRange = (answer - 0.01)..(answer + 0.01),
                        explanation = "We translate each letter of '$str' to its 1-based position in the alphabet. Summing these values yields exactly: " + str.map { it.code - 64 }.joinToString(" + ") + " = $sum.",
                        formulasUsed = "Alphabetic alphanumeric letter conversion sum",
                        shortcutTricks = "Write down known anchor letter values (E=5, J=10, O=15, T=20, Y=25) to map adjacent letters quickly.",
                        relatedConcepts = "Coding-Decoding patterns, Alphanumeric mappings",
                        difficulty = difficulty
                    )
                }
            }
        }
    }
}
