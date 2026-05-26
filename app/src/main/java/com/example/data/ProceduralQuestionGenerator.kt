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
        val qId = "proc_apt_${subtopicId}_$index"

        return when (subtopicId) {
            "apt_verb_grammar_usage" -> {
                when (type) {
                    QuestionType.MCQ -> {
                        val wordList = listOf(
                            Pair("benevolent", "generous and kind"),
                            Pair("capricious", "unpredictable and impulsive"),
                            Pair("ephemeral", "short-lived and fleeting"),
                            Pair("obdurate", "stubborn and unyielding")
                        )
                        val select = wordList[index % wordList.size]
                        val qText = """
                            Select the option that represents the closest synonym for the word "${select.first}" under modern formal sentence usage.
                            Sentence: "The CEO was known for her ${select.first} actions during structural reorganizations."
                        """.trimIndent()

                        val optA = select.second
                        val optB = "unreliable and slow"
                        val optC = "strictly professional and formal"
                        val optD = "aggressive and profit-focused"

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
                            explanation = "The word '${select.first}' is defined as being ${select.second}, which fits the contextual synonym usage in formal business writing.",
                            formulasUsed = "Verbal Lexicon rules",
                            shortcutTricks = "Examine the word root and replace other options in the sentence to test semantic consistency.",
                            relatedConcepts = "Vocabulary, Synonyms, Grammar usage",
                            difficulty = difficulty
                        )
                    }
                    QuestionType.MSQ -> {
                        val qText = """
                            Identify which of the following English sentences are grammatically correct in formal writing: (Select all that apply)
                        """.trimIndent()

                        val optA = "Neither the principal nor the teachers are attending the annual summit."
                        val optB = "If she had registered on time, she would have received the confirmation code."
                        val optC = "Each of the candidates has submitted their reference statement."
                        val optD = "In spite of his fatigue, he continued to compile the simulation data."

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
                            explanation = "Option A is correct because when singular and plural subjects are connected by 'neither/nor', the verb matches the closer noun ('teachers' is plural, so 'are' is used). Option B is a perfect third conditional frame. Option D uses a correct prepositional phrase. Option C traditionally prefers his/her reference in highly formal writing, though 'their' is widely accepted, making A, B, and D the most strictly flawless standard.",
                            formulasUsed = "English grammar concord, Conditional frames",
                            shortcutTricks = "Check conditional agreements: 'If + had + V3' matches 'would + have + V3'. Test nearest noun agreements in neither/nor.",
                            relatedConcepts = "Subject-verb agreement, Verbal logic",
                            difficulty = difficulty
                        )
                    }
                    QuestionType.NAT -> {
                        val length = 8 + rand.nextInt(8) // 8 to 15
                        val slots = 2
                        val answer = length.toDouble()

                        val qText = """
                            Read the sentence: "To write code efficiently requires focus and strict discipline."
                            Let N represent the total number of words in this sentence (excluding any punctuation).
                            Compute the exact numerical value of N.
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
                            explanation = "Counting the words in the sentence: 'To' (1), 'write' (2), 'code' (3), 'efficiently' (4), 'requires' (5), 'focus' (6), 'and' (7), 'strict' (8), 'discipline' (9). There are exactly 9 words in this sentence.",
                            formulasUsed = "Sentence parsing count",
                            shortcutTricks = "Read through and count each token carefully. Double-check small functional words like 'and', 'to'.",
                            relatedConcepts = "Verbal Comprehension, Textual parsing",
                            difficulty = difficulty
                        )
                    }
                }
            }
            "apt_analytical_reasoning" -> {
                when (type) {
                    QuestionType.MCQ -> {
                        val numA = 3 + rand.nextInt(4) // 3 to 6
                        val numB = numA + 1
                        val qText = """
                            In a study environment representing "$subtopicName", $numB software developers are seated in a row. 
                            If Dev A must sit exactly adjacent to Dev B, and Dev C cannot sit adjacent to Dev A. 
                            How many distinct seating arrangements exist?
                        """.trimIndent()

                        val ansVal = 24 * numA
                        val optA = "$ansVal arrangements"
                        val optB = "${ansVal + 12} arrangements"
                        val optC = "${ansVal / 2} arrangements"
                        val optD = "${ansVal * 2} arrangements"

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
                            explanation = "Using standard permutation under restriction rule, treating Dev A and Dev B as a single unit yields a specific group permutation factor multiplied by relative exclusions, calculating exactly to $ansVal valid arrangements.",
                            formulasUsed = "P(n, r) with constraints",
                            shortcutTricks = "Formulate a block unit for adjacent items first, then subtract cases violating the second constraint.",
                            relatedConcepts = "Permutations, Restricted seating, Syllogistic logic",
                            difficulty = difficulty
                        )
                    }
                    QuestionType.MSQ -> {
                        val qText = """
                            Evaluate the truth value of the statements under "$subtopicName" logic rules: (Select all that apply)
                        """.trimIndent()

                        val optA = "If all algorithms are processes, and some processes are efficient, all algorithms must be efficient."
                        val optB = "If all networks are graphs, and no graphs are trees, then no networks are trees."
                        val optC = "If some models are valid, then some models are not valid is a logical statement depending on set intersection."
                        val optD = "The negation of 'All compilers are software' is 'At least one compiler is not software'."

                        val listOpts = listOf(optA, optB, optC, optD)
                        val correctIdx = listOf(1, 2, 3)

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
                            explanation = "Option A is a classical syllogism error (all algorithms are not necessarily efficient). Option B is completely correct by Venn set exclusion. Option C is a standard set theory truth. Option D represents the correct categorical logical negation of a universal statement.",
                            formulasUsed = "Syllogistic logic boundaries",
                            shortcutTricks = "Use Venn diagrams to verify categorical assertions. Universal statements are negated by existential statements.",
                            relatedConcepts = "Truth Tables, Venn Sets, Deductive Syllogisms",
                            difficulty = difficulty
                        )
                    }
                    QuestionType.NAT -> {
                        val valInputs = listOf(Pair(3, 1), Pair(4, 1), Pair(5, 1))
                        val select = valInputs[index % valInputs.size]
                        val ans = select.first.toDouble()

                        val qText = """
                            A logical puzzle involves placing elements under "$subtopicName". 
                            If Statement P implies Statement Q, and we are told that Q is False. 
                            Let P's truth state be a real number: 1.0 representing True, and 0.0 representing False.
                            What is the logical truth state of Statement P?
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
                            correctNumericalRange = 0.0..0.0,
                            explanation = "By Modus Tollens rule of classical inference, if P -> Q is True, and Q is False, then P MUST be False. Therefore, its truth value is exactly 0.0.",
                            formulasUsed = "Modus Tollens: ((P -> Q) ∧ ¬Q) -> ¬P",
                            shortcutTricks = "Contrapositive is logically equivalent to the conditional. If Q is false, then P is immediately false.",
                            relatedConcepts = "Analytical deductions, Logic gates, Implication rules",
                            difficulty = difficulty
                        )
                    }
                }
            }
            "apt_spatial_mirroring" -> {
                when (type) {
                    QuestionType.MCQ -> {
                        val qText = """
                            Under spatial mirroring rules representing design components of "$subtopicName", 
                            a flat asymmetric pattern is rotated 180 degrees counter-clockwise and then mirrored horizontally.
                            Which of the following operations describes the single-step equivalent transformation?
                        """.trimIndent()

                        val optA = "A simple vertical mirror reflection"
                        val optB = "A simple horizontal mirror reflection"
                        val optC = "A simple 90 degrees clockwise rotation"
                        val optD = "No transformation (returns to exact initial state)"

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
                            explanation = "A 180-degree rotation is equivalent to both horizontal and vertical mirroring combined. If you rotate 180 and then mirror horizontally again, the horizontal mirroring is negated, leaving a single vertical mirror reflection.",
                            formulasUsed = "Spatial Matrix transformations",
                            shortcutTricks = "Draw a simple asymmetric shape (like 'L') on paper, rotate it, and flip it to trace the visual result instantly.",
                            relatedConcepts = "Spatial symmetry, Matrix rotation, Mirror mappings",
                            difficulty = difficulty
                        )
                    }
                    QuestionType.MSQ -> {
                        val qText = """
                            Which of the following assertions about spatial properties of multi-dimensional shapes is/are correct? (Select all that apply)
                        """.trimIndent()

                        val optA = "A standard regular 3D tetrahedron has exactly 4 vertices and 6 linear edges."
                        val optB = "Rotating a 3D object along its principal axis preserves all relative volume and edge metrics."
                        val optC = "Reflecting an asymmetric 3D shape across any plane alters its chirality (handedness)."
                        val optD = "Every regular polyhedron must have an equal number of faces and vertices."

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
                            explanation = "Euler's formula for polyhedra is F + V = E + 2. Not all regular polyhedra have F = V (e.g. cube has 6 faces and 8 vertices), making Option D false. Ratios and structures follow rigorous geometric theorems.",
                            formulasUsed = "Euler's Polyhedral Theorem: F + V = E + 2",
                            shortcutTricks = "Analyse a standard cube to verify if faces equal vertices. Instant rejection of Option D.",
                            relatedConcepts = "Polyhedrons, Spatial dimensions, Chirality symmetry",
                            difficulty = difficulty
                        )
                    }
                    QuestionType.NAT -> {
                        val s = 3 + rand.nextInt(4) // 3, 4, 5, 6
                        val answer = 12 * (s - 2)
                        val formattedAns = answer.toDouble()

                        val qText = """
                            A solid cube of side $s cm is painted completely red on its outer faces. 
                            It is then cut into uniform $s^3 unit cubes of side 1 cm.
                            Determine the number of unit cubes that have exactly TWO faces painted red.
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
                            correctNumericalRange = (formattedAns - 0.01)..(formattedAns + 0.01),
                            explanation = "In a painted cube of side 's', the unit cubes with exactly 2 painted faces lie along the edges of the original cube (excluding the corners). There are 12 edges on a cube, and each edge has (s - 2) such cubes. Hence, Total = 12 * ($s - 2) = 12 * ${s - 2} = $answer cubes.",
                            formulasUsed = "Count(2-painted) = 12 * (s - 2)",
                            shortcutTricks = "Two-sided painted cubes always lie on edges. Since a cube has 12 edges, the formula is always 12 multiplied by (side - 2).",
                            relatedConcepts = "Spatial geometry, Painted cubes division, Edges counts",
                            difficulty = difficulty
                        )
                    }
                }
            }
            "apt_numerical_computation" -> {
                when (type) {
                    QuestionType.MCQ -> {
                        val speed1 = 40 + rand.nextInt(20) // 40 to 60
                        val speed2 = 60 + rand.nextInt(30) // 60 to 90
                        val avgSpeed = (2.0 * speed1 * speed2) / (speed1 + speed2)
                        val formattedAvg = String.format(Locale.US, "%.2f", avgSpeed)

                        val qText = """
                            An engineer drives from home to an analytical workstation at an average speed of $speed1 km/h, 
                            and immediately returns along the exact same path driving at an average speed of $speed2 km/h. 
                            Compute the overall average speed (in km/h) for the entire round trip.
                        """.trimIndent()

                        val optA = "$formattedAvg km/h"
                        val optB = String.format(Locale.US, "%.2f km/h", (speed1 + speed2) / 2.0)
                        val optC = String.format(Locale.US, "%.2f km/h", avgSpeed - 5.0)
                        val optD = String.format(Locale.US, "%.2f km/h", avgSpeed + 3.5)

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
                            explanation = "The average speed for a round trip with constant distance segments is the harmonic mean of the speeds: V_avg = 2 * V1 * V2 / (V1 + V2) = 2 * $speed1 * $speed2 / ($speed1 + $speed2) = ${2 * speed1 * speed2} / ${speed1 + speed2} = $formattedAvg km/h.",
                            formulasUsed = "Harmonic Mean = 2 * v1 * v2 / (v1 + v2)",
                            shortcutTricks = "The average speed for equal-distance segments is ALWAYS strictly less than the simple arithmetic mean. This rules out simple average calculations instantly.",
                            relatedConcepts = "Quantitative aptitude, Speed rate formulas, Average calculations",
                            difficulty = difficulty
                        )
                    }
                    QuestionType.MSQ -> {
                        val qText = """
                            Identify which of the following quantitative relationships is/are mathematically correct: (Select all that apply)
                        """.trimIndent()

                        val optA = "If a sum of money doubles itself in 10 years under simple interest, the annual interest rate is exactly 10%."
                        val optB = "The compound interest on any principal is always strictly greater than or equal to the simple interest for any positive duration."
                        val optC = "If three values are in ratio 2:3:5, their sum must always be an even integer."
                        val optD = "For any two positive numbers, the Arithmetic Mean is always greater than or equal to the Geometric Mean."

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
                            explanation = "Option A is correct: Sum doubles implies Interest = Principal, so PRT/100 = P => R * 10 / 100 = 1 => R = 10%. Option B is correct since compound interest gains interest-on-interest after year 1 (and equals SI for year 1). Option D is the standard AM-GM inequality. Option C is false because values could be decimals (e.g. 0.2, 0.3, 0.5 sum to 1.0 which is odd), so AM-GM holds.",
                            formulasUsed = "Simple Interest: I = P*R*T/100; AM >= GM",
                            shortcutTricks = "Always assume arbitrary variables to test ratios. Decimals break constraints of Option C instantly.",
                            relatedConcepts = "Interest rates, AM-GM inequality, Ratio properties",
                            difficulty = difficulty
                        )
                    }
                    QuestionType.NAT -> {
                        // Work combinations
                        val workCombos = listOf(
                            Pair(12, 6),   // Product/Sum = 72/18 = 4.0
                            Pair(12, 24),  // Product/Sum = 288/36 = 8.0
                            Pair(10, 15)   // Product/Sum = 150/25 = 6.0
                        )
                        val pair = workCombos[index % workCombos.size]
                        val x = pair.first
                        val y = pair.second
                        val ans = (x * y).toDouble() / (x + y).toDouble()

                        val qText = """
                            Working alone, analyst A takes exactly $x hours to compile a statistical summary.
                            Analyst B takes exactly $y hours to complete the identical task. 
                            How many hours will they require to complete the task if they work together concurrently?
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
                            correctNumericalRange = (ans - 0.05)..(ans + 0.05),
                            explanation = "Working together, their total rate is 1/x + 1/y = (x + y) / (x * y). Thus, the time needed is the reciprocal: T = (x * y) / (x + y) = ($x * $y) / ($x + $y) = ${x * y} / ${x + y} = $ans hours.",
                            formulasUsed = "Combined time T = (x * y) / (x + y)",
                            shortcutTricks = "Compute the product divided by the sum of their individual speeds to instantly find combined time.",
                            relatedConcepts = "Work ratios, Rate analysis, Quantitative computations",
                            difficulty = difficulty
                        )
                    }
                }
            }
            else -> {
                // Return default general aptitude question
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
                    explanation = "We compute the probability by dividing the subset count ($varA) by the total outcomes ($total) and multiplying by 100: ($varA / $total) * 100 = $ratio%.",
                    formulasUsed = "P(A) = n(A) / N(total)",
                    shortcutTricks = "Always build the ratio directly. Check that the final percentage correlates directly with the magnitude of parts.",
                    relatedConcepts = "Probability, Ratio analysis, Quantitative Aptitude",
                    difficulty = difficulty
                )
            }
        }
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
}
