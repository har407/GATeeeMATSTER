package com.example.data

object AptitudeQuestions {
    val questions: List<GateQuestion> by lazy {
        AptitudeQuestions1.questions +
        AptitudeQuestions2.questions +
        AptitudeQuestions3.questions +
        AptitudeQuestions4.questions +
        AptitudeQuestions5.questions +
        AptitudeQuestions6.questions
    }

    fun makeQ(
        id: Int,
        category: String,
        subCategory: String,
        question: String,
        options: List<String>,
        answer: String
    ): GateQuestion {
        val subId = getSubtopicId(category, subCategory, question)
        val topicId = when {
            subId.startsWith("apt_verb_") -> "apt_verbal_ability"
            subId.startsWith("apt_quant_") || subId == "apt_numerical_computation" -> "apt_quantitative"
            subId.startsWith("apt_anal_") || subId == "apt_analytical_reasoning" -> "apt_analytical_aptitude"
            subId.startsWith("apt_spatial_") -> "apt_spatial_aptitude"
            else -> "apt_quantitative"
        }
        val correctIdx = options.indexOf(answer).let { if (it == -1) 0 else it }

        return GateQuestion(
            id = "apt_q_$id",
            subjectId = "general_aptitude",
            topicId = topicId,
            subtopicId = subId,
            year = 2020 + (id % 7),
            questionText = question,
            questionType = QuestionType.MCQ,
            options = options,
            correctOptions = listOf(correctIdx),
            correctNumericalRange = null,
            explanation = "The correct answer is $answer. This is solved based on the core properties of $subCategory.",
            formulasUsed = "General $subCategory formula application",
            shortcutTricks = "Examine boundaries and match options to test efficiently.",
            relatedConcepts = "$subCategory, General Aptitude",
            difficulty = when (id % 3) {
                0 -> "Easy"
                1 -> "Medium"
                else -> "Hard"
            }
        )
    }

    fun getSubtopicId(category: String, subCategory: String, question: String): String {
        return when (category) {
            "Aptitude" -> {
                when (subCategory) {
                    "Time and Work", "Pipes and Cisterns", "Speed and Distance", "Trains", "Boats and Streams" -> "apt_quant_time_work"
                    "Percentages", "Simple Interest", "Compound Interest", "True Discount", "Discounts" -> "apt_quant_percentages"
                    "Profit and Loss" -> "apt_quant_profit_loss"
                    "Ratio and Proportion", "Partnership", "Mixtures", "Mixtures & Alligations", "Scales & Maps" -> "apt_quant_ratios"
                    "Permutations & Combinations" -> "apt_quant_permutation_combination"
                    "Probability", "Dice Probability" -> "apt_quant_probability"
                    "Logarithms" -> "apt_quant_logarithms"
                    "Stocks and Shares", "Data Interpretation" -> "apt_quant_data_interpretation"
                    "Geometry", "Geometry & Triangles", "Height and Distance" -> "apt_quant_geometry"
                    "Geometry & Mensuration", "Area & Perimeter", "Volume and Surface Area", "Mensuration", "Mensuration (2D)", "Mensuration (3D)" -> "apt_quant_mensuration"
                    "Basic Algebra", "Quadratic Equations", "Linear Equations", "Simplification", "Number System", "Exponents", "Decimals", "Decimal Fractions", "Surds and Indices", "HCF & LCM", "Inequalities", "Cube and Cube Roots", "Square and Square Roots", "Averages", "Statistics" -> "apt_numerical_computation"
                    else -> "apt_numerical_computation"
                }
            }
            "Logical Reasoning" -> {
                when (subCategory) {
                    "Number Series", "Series Systems", "Number Analogy" -> "apt_anal_number_series"
                    "Syllogisms", "Binary Logic" -> "apt_anal_deduction_induction"
                    "Analogies" -> "apt_anal_analogies"
                    "Input-Output Parsing", "Input-Output Problems", "Calendar", "Calendar Logic", "Calendar Complexity", "Cryptarithms" -> "apt_anal_numerical_reasoning"
                    "Mirror Images" -> "apt_spatial_mirroring"
                    "Rotation", "Cubes & Dice", "Dice Patterns" -> "apt_spatial_rotation"
                    "Paper Folding", "Spatial Tracking" -> "apt_spatial_paper_folding"
                    "Pattern Recognition" -> "apt_spatial_pattern_recognition"
                    "Shape Transformation" -> "apt_spatial_shape_transformation"
                    else -> "apt_analytical_reasoning"
                }
            }
            "Verbal Ability" -> {
                when (subCategory) {
                    "Synonyms", "Antonyms", "Synonyms Nuance", "Antonyms Nuance", "Word Formation & Roots", "Foreign Words", "Homophones & Homonyms", "Contextual Meaning", "Vocabulary" -> "apt_verb_vocab"
                    "Sentence Completion", "Cloze Test", "Contextual Usage", "Contextual Word Choice", "Prepositions", "Conjunctions", "Articles", "Subject-Verb Agreement", "Tenses", "Grammar Nuance" -> "apt_verb_completion"
                    "Analogies Verbal", "Verbal Analogies" -> "apt_verb_analogies"
                    "Spotting Errors", "Error Detection", "Sentence Improvement" -> "apt_verb_grammar_usage"
                    "Idioms & Phrases", "One Word Substitution", "One-Word Substitutions", "Word Groups" -> "apt_verb_word_groups"
                    "Para-Jumbles", "Para Jumbles" -> "apt_verb_narrative_seq"
                    "Critical Reasoning", "Critical Comprehension", "Statement and Argument", "Cause and Effect", "Course of Action", "Tone of Author" -> "apt_verb_critical"
                    "Reading Comprehension" -> "apt_verb_reading"
                    else -> "apt_verb_vocab"
                }
            }
            else -> "apt_numerical_computation"
        }
    }
}
