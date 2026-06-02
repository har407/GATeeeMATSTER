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
        val subtopicOffset = if (subtopicId.hashCode() == Int.MIN_VALUE) Int.MAX_VALUE else kotlin.math.abs(subtopicId.hashCode())
        val effIdx = idx + subtopicOffset
        return when (subtopicId) {
            "apt_verb_vocab", "apt_verb_completion" -> {
                val words = listOf(
                    Triple("benevolent", "generous and kind", "malicious"),
                    Triple("capricious", "unpredictable and impulsive", "stable"),
                    Triple("ephemeral", "short-lived and fleeting", "perpetual"),
                    Triple("obdurate", "stubborn and unyielding", "flexible"),
                    Triple("gregarious", "sociable and outgoing", "introverted"),
                    Triple("laconic", "using few words and concise", "verbose"),
                    Triple("pragmatic", "practical and sensible", "idealistic"),
                    Triple("cacophony", "harsh and discordant sounds", "harmony"),
                    Triple("ameliorate", "make something better and improve", "exacerbate"),
                    Triple("venerate", "regard with great respect and revere", "despise"),
                    Triple("recondite", "obscure and little known", "straightforward"),
                    Triple("didantic", "intended to teach and instructional", "uninformative"),
                    Triple("transient", "lasting only a short time", "permanent"),
                    Triple("equivocal", "open to more than one interpretation", "unambiguous"),
                    Triple("assiduous", "showing great care and persistent", "negligent"),
                    Triple("precarious", "not securely held and dangerously unstable", "safe"),
                    Triple("copious", "abundant in supply or quantity", "sparse"),
                    Triple("dearth", "a scarcity or lack of something", "abundance"),
                    Triple("fortuitous", "happening by a lucky accident", "intentional"),
                    Triple("mitigate", "make less severe or serious", "intensify"),
                    Triple("ostentatious", "vulgar or pretentious display", "modest"),
                    Triple("reticent", "not revealing one's thoughts readily", "communicative"),
                    Triple("taciturn", "reserved or uncommunicative in speech", "loquacious"),
                    Triple("zealot", "a person who is fanatical and uncompromising", "moderate"),
                    Triple("apathy", "lack of interest or concern", "enthusiasm"),
                    Triple("audacious", "showing a willingness to take surprisingly bold risks", "timid"),
                    Triple("candid", "truthful and straightforward", "evasive"),
                    Triple("dogmatic", "inclined to lay down principles as incontrovertibly true", "flexible"),
                    Triple("fastidious", "very attentive to and concerned about accuracy", "careless"),
                    Triple("loquacious", "tending to talk a great deal", "silent")
                )
                val sel = words[effIdx % words.size]
                if (type == QuestionType.MCQ) {
                    val qText = if (subtopicId == "apt_verb_vocab") {
                        "Determine the closest synonym for the word \"${sel.first}\" in standard academic writing."
                    } else {
                        val sentence = when (sel.first) {
                            "benevolent" -> "The philanthropist's ________ actions were celebrated by the entire community."
                            "capricious" -> "The weather during autumn was ________, changing from bright sunlight to storm in minutes."
                            "ephemeral" -> "The cherry blossom season is beautiful but ________, lasting only a few short days."
                            "obdurate" -> "Despite hours of intense negotiation, the management remained ________ and refused to compromise."
                            "gregarious" -> "As a ________ individual, she thrived in busy academic conferences and collaborative group labs."
                            "laconic" -> "The engineer's ________ response of 'Done' conveyed all necessary information without excess words."
                            "pragmatic" -> "We need a ________ approach to solving the power dissipation limit, rather than an idealized theoretical one."
                            "cacophony" -> "Entering the industrial turbine hall, the visitor was met with a loud ________ of metallic screeches."
                            "ameliorate" -> "Installing liquid nitrogen cooling jackets was done to ________ the rapid heating of superconducting qubits."
                            "venerate" -> "Young apprentices ________ the old master craftsmen who built the original analog grids."
                            "recondite" -> "He spent his career studying ________ topics in multi-variable complex variables."
                            "didantic", "didactic", "didantic" -> "Her lectures were strictly ________, focusing entirely on analytical guidelines."
                            "transient" -> "The voltage surge was ________, lasting only a millisecond before the fuse tripped."
                            "equivocal" -> "The tester's safety summary was ________, containing conflicting assessments that confused researchers."
                            "assiduous" -> "Through twenty years of ________ study, she compiled the definitive volume on linear matrix systems."
                            "precarious" -> "The sensor was placed in a ________ position on the outer lip of the pressure chamber."
                            "copious" -> "The meteorologist collected ________ amounts of rain statistics during the monsoon season."
                            "dearth" -> "A sudden ________ of raw component materials forced the plant to delay its production plan."
                            "fortuitous" -> "It was a ________ discovery when a minor calculation mistake led to a breakthrough in control loops."
                            "mitigate" -> "Adjusting the feedback loop gain was necessary to ________ the loud harmonic oscillations."
                            "ostentatious" -> "He avoided ________ declarations, presenting his mathematical proofs in a quiet, modest style."
                            "reticent" -> "The usually vocal analyst was ________ to comment on the preliminary research results."
                            "taciturn" -> "She was known as a quiet, ________ researcher who preferred coding in silence to public debates."
                            "zealot" -> "A true ________ for efficiency, he refused to use any compiler that didn't optimize loops."
                            "apathy" -> "The widespread ________ among the technicians slowed the integration of new safety protocols."
                            "audacious" -> "Their ________ plan to reconstruct the entire power grid within 24 hours surprised administrators."
                            "candid" -> "In a ________ interview, the lead engineer admitted that testing has been severely neglected."
                            "dogmatic" -> "We should avoid a ________ adherence to theoretical models when actual empirical data contradicts them."
                            "fastidious" -> "The team compiled the database with ________ attention, ensuring zero typographical mistakes."
                            "loquacious" -> "The ________ speaker talked at great length, turning a short review into a three-hour seminar."
                            else -> "The team made a ________ effort to ensure that the final system components matched standard criteria."
                        }
                        "Complete the sentence contextually: \"$sentence\""
                    }
                    val correct = if (subtopicId == "apt_verb_vocab") sel.second else sel.first
                    val wrongOpt = if (subtopicId == "apt_verb_vocab") sel.third else sel.third
                    val options = listOf(correct, wrongOpt, "strictly professional", "aggressive").shuffled(rand)
                    val correctIdx = options.indexOf(correct)
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = options, correctOptions = listOf(correctIdx),
                        explanation = if (subtopicId == "apt_verb_vocab") {
                            "By vocabulary definition, '${sel.first}' means '${sel.second}'. Thus the correct answer is '$correct'."
                        } else {
                            "Based on the contextual sentence meaning, the word '${sel.first}' (meaning '${sel.second}') fits the blank perfectly."
                        },
                        formulasUsed = "Verbal semantics", shortcutTricks = "Analyze word prefix/suffix context.",
                        relatedConcepts = "Vocabulary, Syllogistic logic", difficulty = difficulty
                    )
                } else if (type == QuestionType.MSQ) {
                    val qText = "Select all of the options that are related to the meaning or synonym list of \"${sel.first}\":"
                    val options = listOf(
                        "Meaning covers: ${sel.second}",
                        "Suitable for high-frequency academic vocabulary",
                        "Represents a standard English semantic term",
                        "Its primary synonym is \"${sel.third}\""
                    )
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = options, correctOptions = listOf(0, 1, 2),
                        explanation = "The options indicating the definition '${sel.second}' and its academic nature are correct. Option D of '${sel.third}' is incorrect since that word is an antonym, not a synonym.",
                        formulasUsed = "Semantic matching", shortcutTricks = "Recognize definitions versus antonyms easily.",
                        relatedConcepts = "Synonyms matching", difficulty = difficulty
                    )
                } else {
                    val ans = sel.first.length.toDouble()
                    val qText = "In the sentence completion task, how many letters are there in the word \"${sel.first.uppercase(Locale.US)}\"?"
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = null, correctOptions = null,
                        correctNumericalRange = (ans - 0.01)..(ans + 0.01),
                        explanation = "Counting the characters in '${sel.first.uppercase(Locale.US)}' yields exactly ${sel.first.length} characters.",
                        formulasUsed = "Character mapping count", shortcutTricks = "Examine spelling and count precisely.",
                        relatedConcepts = "Grammar lexicon rules", difficulty = difficulty
                    )
                }
            }
            "apt_verb_reading", "apt_verb_critical" -> {
                val readings = listOf(
                    Triple(
                        "Although parallel computing provides high throughput for matrix multiplications, power dissipation limits the deployment scale.",
                        "Deployment scale is limited by power dissipation despite superior parallel computing speedups.",
                        listOf("Matrix multiplications cannot be processed concurrently.", "Power dissipation is completely irrelevant to high throughput operations.", "Parallel computing guarantees zero thermal limits.")
                    ),
                    Triple(
                        "While superconducting qubits offer fast gate execution, environmental noise limits their quantum coherence times. Resolving decoherence is critical for achieving fault-tolerant computation.",
                        "Coherence times are restricted by environmental noise despite fast gates.",
                        listOf("Superconducting qubits have slow gate execution speeds.", "Decoherence plays no role in fault-tolerant systems.", "Superconducting grids operate at room temperatures.")
                    ),
                    Triple(
                        "Solar and wind power offer carbon-neutral energy production, but their intermittent nature requires wide-scale deployment of battery storage systems. Without adequate storage, grid balancing becomes highly volatile.",
                        "Intermittent generation necessitates battery storage to prevent grid volatility.",
                        listOf("Renewable energy is highly stable and does not require storage.", "Carbon-neutral production is impossible with solar energy.", "Grid balancing is simplified by direct wind inputs.")
                    ),
                    Triple(
                        "Deep learning architectures excel at identifying complex spatial patterns in MRI scans. However, their black-box nature makes clinical explanation problematic, causing integration issues with doctors.",
                        "Doctor integration is limited by the non-interpretable black-box nature of deep models.",
                        listOf("Deep learning models are completely incapable of spatial analysis.", "MRI scans cannot be verified by machine learning architectures.", "AI models provide instant, clear explanations to medical staff.")
                    ),
                    Triple(
                        "While edge processing allows real-time inference with ultra-low latency for steering decisions, thermal limitations prevent equipping vehicles with extensive high-end server hardware. Thus, lightweight network models are required.",
                        "Thermal limitations necessitate lightweight models for real-time edge vehicle steering.",
                        listOf("Edge processing has high latency unsuitable for vehicle control.", "Vehicles can hold standard high-end server arrays without heating.", "Lightweight networks require mainframe cloud connections.")
                    ),
                    Triple(
                        "Containerization enables uniform application deployment across heterogeneous servers. However, shared kernel architectures introduce potential side-channel vulnerability threats compared to virtual machines.",
                        "Shared kernels create side-channel security vulnerabilities in container deployments.",
                        listOf("Containers do not allow uniform deployments across servers.", "Virtual machines use shared kernels and are less secure than containers.", "Side-channel vulnerabilities are impossible in cloud layers.")
                    )
                )
                val selText = readings[effIdx % readings.size]
                if (type == QuestionType.MCQ) {
                    val qText = """
                        Read the technical excerpt carefully:
                        "${selText.first}"
                        Which statement represents the main thesis or logical conclusion?
                    """.trimIndent()
                    val options = (listOf(selText.second) + selText.third).shuffled(rand)
                    val correctIdx = options.indexOf(selText.second)
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = options, correctOptions = listOf(correctIdx),
                        explanation = "The passage directly contrasts the functional advantages of the topic with its active real-world constraints: '${selText.second}'.",
                        formulasUsed = "Logical thesis parsing", shortcutTricks = "Examine the main transition markers to locate key analytical constraints.",
                        relatedConcepts = "Critical reading, theme extraction", difficulty = difficulty
                    )
                } else if (type == QuestionType.MSQ) {
                    val concepts = listOf(
                        Triple("compilers", "translate source files", "optimize loops"),
                        Triple("assemblers", "generate machine code", "resolve symbols"),
                        Triple("interpreters", "execute code line-by-line", "compile bytecode"),
                        Triple("parsers", "produce syntax trees", "detect indentation faults"),
                        Triple("loaders", "load segments into RAM", "schedule operating processes"),
                        Triple("linkers", "bind library functions", "eliminate dead code blocks")
                    )
                    val selConcept = concepts[effIdx % concepts.size]
                    val c1 = selConcept.first
                    val c2 = selConcept.second
                    val c3 = selConcept.third
                    val qText = "For verification task #[MS-${effIdx + 300}], select all of the logically valid deductions supported by the premises: \"All $c1 $c2. Some $c1 $c3.\""
                    val options = listOf(
                        "There exists a tool that $c2 and also $c3.",
                        "Any tool that does not $c2 cannot be a $c1.",
                        "Some tools that $c2 do not $c3 is possible.",
                        "No $c1 $c2."
                    )
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = options, correctOptions = listOf(0, 1, 2),
                        explanation = "Since all $c1 $c2, any $c1 that $c3 must also $c2. Option B is is valid because if a tool does not $c2, it cannot be a $c1.",
                        formulasUsed = "Syllogistic logic parsing", shortcutTricks = "Use containment: $c3 actors are a subset of $c1 or intersect. $c1 are subset of $c2.",
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
                    val analogies = listOf(
                        Triple("COAL : LOCOMOTIVE", "ELECTRICITY : MOTOR", listOf("WATER : PIPELINE", "FUEL : FILTER", "WIND : SAIL")),
                        Triple("AUTHOR : BOOK", "ARCHITECT : BUILDING", listOf("DOCTOR : STETHOSCOPE", "TEACHER : CHALK", "ENGINEER : ENGINE")),
                        Triple("MICROSCOPE : BACTERIA", "TELESCOPE : STAR", listOf("SCALPEL : SURGERY", "CAMERA : PHOTO", "CLOCK : TIME")),
                        Triple("CONDUCTOR : ORCHESTRA", "DIRECTOR : FILM", listOf("PILOT : AIRPLANE", "WRITER : NOVEL", "SOLDIER : ARMY")),
                        Triple("EPIDEMIC : VIRUS", "FLOOD : WATER", listOf("FIRE : COAL", "WIND : SAIL", "EARTHQUAKE : PLATE")),
                        Triple("OXYGEN : RESPIRATION", "FUEL : COMBUSTION", listOf("LIGHT : VISION", "WATER : DRINKING", "AIR : WIND")),
                        Triple("HEAT : EVAPORATION", "COLD : CONDENSATION", listOf("LIGHT : REFRACTION", "FORCE : MOTION", "SPEED : VELOCITY")),
                        Triple("SURGEON : SCALPEL", "WRITER : PEN", listOf("MASON : BRICK", "PILOT : AIRSTRIP", "BAKER : OVEN")),
                        Triple("SCALING : MAP", "RATIO : PROPORTION", listOf("LENGTH : WIDTH", "VOLUME : MASS", "AREA : GRID")),
                        Triple("SOLVENT : SOLUTE", "WATER : SALT", listOf("ACID : BASE", "IRON : STEEL", "OXYGEN : HYDROGEN"))
                    )
                    val sel = analogies[effIdx % analogies.size]
                    val qText = "Identify the option that displays the most appropriate analogical relationship matching: ${sel.first} :: ________ : ________"
                    val options = (listOf(sel.second) + sel.third).shuffled(rand)
                    val correctIdx = options.indexOf(sel.second)
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = options, correctOptions = listOf(correctIdx),
                        explanation = "The target pair maps the fundamental functional/relational link shown by ${sel.first}. Symmetrically, ${sel.second} represents this link perfectly.",
                        formulasUsed = "Analogy relations", shortcutTricks = "Formulate a relational sentence between the original pair to test the options.",
                        relatedConcepts = "Analogical arguments, matching relations", difficulty = difficulty
                    )
                } else if (type == QuestionType.MSQ) {
                    val msqOptions = listOf(
                        listOf("SPOKE : WHEEL", "LEAF : TREE", "ENGINE : CAR", "WATER : PIPELINE") to listOf(0, 1, 2),
                        listOf("RESISTOR : CIRCUIT", "KEYBOARD : COMPUTER", "PAGE : BOOK", "STREET : ROAD") to listOf(0, 1, 2),
                        listOf("NUCLEUS : ATOM", "STAR : GALAXY", "PIXEL : MONITOR", "HEAT : COLD") to listOf(0, 1, 2),
                        listOf("CHASSIS : VEHICLE", "FIBER : CABLE", "CELL : TISSUE", "GLUCOSE : PROTEIN") to listOf(0, 1, 2)
                    )
                    val selMsq = msqOptions[effIdx % msqOptions.size]
                    val qText = "Select all of the options containing word groups representing mechanical/physical parts of a macroscopic whole (PART : WHOLE relation):"
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = selMsq.first, correctOptions = selMsq.second,
                        explanation = "Under taxonomy principles, the first three items express physical sub-components of a host system structure.",
                        formulasUsed = "Semantic taxonomy", shortcutTricks = "Identify physical components vs descriptive bounds.",
                        relatedConcepts = "Analogy associations", difficulty = difficulty
                    )
                } else {
                    val natOptions = listOf(
                        Triple("LIGHT : BLIND :: SOUND : [?]", "DEAF", 4.0),
                        Triple("ODOR : ANOSMIC :: TASTE : [?]", "AGEUSIC", 7.0),
                        Triple("TOUCH : NUMB :: SIGHT : [?]", "BLIND", 5.0),
                        Triple("SPEECH : MUTE :: HEAR : [?]", "DEAF", 4.0)
                    )
                    val selNat = natOptions[effIdx % natOptions.size]
                    val qText = """
                        In the analogy ${selNat.first}, 
                        count the number of characters in the response representing the corresponding sensory impairment.
                    """.trimIndent()
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = null, correctOptions = null,
                        correctNumericalRange = (selNat.third - 0.01)..(selNat.third + 0.01),
                        explanation = "The response is '${selNat.second}', which contains exactly ${selNat.second.length} characters.",
                        formulasUsed = "Analogy spelling mapping", shortcutTricks = "Solve the analogy synonym first, and then count.",
                        relatedConcepts = "Verbal Analogies", difficulty = difficulty
                    )
                }
            }
            else -> {
                val words = listOf(
                    Triple("benevolent", "generous and kind", "malicious"),
                    Triple("capricious", "unpredictable and impulsive", "stable"),
                    Triple("ephemeral", "short-lived and fleeting", "perpetual"),
                    Triple("obdurate", "stubborn and unyielding", "flexible"),
                    Triple("gregarious", "sociable and outgoing", "introverted"),
                    Triple("laconic", "using few words and concise", "verbose")
                )
                val select = words[effIdx % words.size]
                if (type == QuestionType.MCQ) {
                    val qText = """
                        Select the option that represents the closest synonym for the word "${select.first}" under modern formal sentence usage.
                        Sentence: "The executive was known for her ${select.first} actions during structural reorganizations."
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
                    val tagStr = "SC-${1000 + effIdx}"
                    val qText = "For grammar evaluation card $tagStr, identify which of the following English sentences are grammatically correct in formal academic writing: (Select all that apply)"
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
                    val sentences = listOf(
                        "Writing clean code requires persistent attention." to 6.0,
                        "To write code efficiently requires focus and strict discipline." to 9.0,
                        "Optimization should be performed only after profiling bottlenecks." to 9.0,
                        "Designing adaptive systems demands deep architectural insights and testing." to 10.0,
                        "Routh-Hurwitz stability criterion provides a highly systematic algebraic test." to 10.0,
                        "Every solid sphere has five reflective planes under standard dilations." to 10.0,
                        "Syllogistic logic forms the basis of computer reasoning systems today." to 10.0,
                        "A feedback compensator modifies open-loop dynamics to achieve transient safety." to 11.0,
                        "A real symmetric matrix always possesses strictly real orthogonal eigenvectors." to 11.0,
                        "The velocity error constant of any Type one system is finite." to 11.0
                    )
                    val selPair = sentences[effIdx % sentences.size]
                    val sText = selPair.first
                    val ans = selPair.second
                    val qText = """
                        Read the sentence: "$sText"
                        Let N represent the total number of words in this sentence (excluding any punctuation).
                        Compute the exact numerical value of N.
                    """.trimIndent()
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = null, correctOptions = null,
                        correctNumericalRange = (ans - 0.01)..(ans + 0.01),
                        explanation = "Counting the words in the sentence yields exactly ${ans.toInt()} words.",
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
        val subtopicOffset = if (subtopicId.hashCode() == Int.MIN_VALUE) Int.MAX_VALUE else kotlin.math.abs(subtopicId.hashCode())
        val effIdx = idx + subtopicOffset
        return when (subtopicId) {
            "apt_quant_ratios", "apt_quant_percentages", "apt_quant_profit_loss" -> {
                if (type == QuestionType.MCQ) {
                    val pPercentList = listOf(20, 25, 40, 50, 100)
                    val p = pPercentList[effIdx % pPercentList.size]
                    val reduction = (p.toDouble() / (100.0 + p.toDouble())) * 100.0
                    val formattedRed = String.format(Locale.US, "%.2f", reduction)

                    val qText = "Under \"$subtopicName\", if the price of a manufacturing component is increased by $p%, by what approximate percentage must a factory reduce its raw utilization of that component to keep its overall budget unchanged?"
                    val correctOpt = "$formattedRed%"
                    val wrong1 = "${p}%"
                    val wrong2 = String.format(Locale.US, "%.2f%%", reduction * 1.2)
                    val wrong3 = String.format(Locale.US, "%.2f%%", reduction * 0.8)
                    val options = listOf(correctOpt, wrong1, wrong2, wrong3).distinct().shuffled(rand)
                    val correctIdx = options.indexOf(correctOpt)
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = options, correctOptions = listOf(correctIdx),
                        explanation = "If price increases to (1 + $p/100) times CP, then utilization must drop to 1 / (1 + $p/100) to maintain budgetary equality. The exact net reduction is $formattedRed%.",
                        formulasUsed = "P * U = Total budget", shortcutTricks = "Change CP to 1.0, increase by ratio and check the inverse.",
                        relatedConcepts = "Ratios, percentage margins", difficulty = difficulty
                    )
                } else if (type == QuestionType.MSQ) {
                    val msqSets = listOf(
                        Pair(
                            "Select all mathematically correct percentage/ratio statements under \"$subtopicName\":",
                            listOf(
                                "An increase of 50% followed by a decrease of 50% result in a net decrease of 25%.",
                                "If a:b = 2:3 and b:c = 4:5, the compounded ratio a:b:c is 8:12:15.",
                                "If cost price is 100, and it is marked up by 30%, then sold with a 10% discount, profit is 17%.",
                                "If selling price is less than cost price, a net profit is obtained directly."
                            ) to listOf(0, 1, 2)
                        ),
                        Pair(
                            "Which of the following propositions regarding growth, percentage margins, and ratios are mathematically correct under \"$subtopicName\"?",
                            listOf(
                                "An increase of 20% followed by another increase of 10% results in a net increase of 32%.",
                                "If x is 25% larger than y, then y is 20% smaller than x.",
                                "If a:b is 3:4, then a is 75% of b.",
                                "A markup of 100% followed by a 100% discount results in zero cost price loss."
                            ) to listOf(0, 1, 2)
                        ),
                        Pair(
                            "Identify the true assertions regarding rates and proportions for industrial operations under \"$subtopicName\":",
                            listOf(
                                "If the ratio of capacities of two tanks is 3:5, the larger tank holds 66.6% more than the smaller.",
                                "A 10% profit margin calculated on selling price is strictly greater than 10% margin on cost price.",
                                "If a factory throughput is scaled by 1.15, it represents a 15% increase.",
                                "If a:b = 1:2, then b is 50% of a."
                            ) to listOf(0, 1, 2)
                        )
                    )
                    val selSet = msqSets[effIdx % msqSets.size]
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = selSet.first, questionType = type, options = selSet.second.first, correctOptions = selSet.second.second,
                        explanation = "Applying basic arithmetic proportions and percentages, the correct options satisfy the mathematical constraints.",
                        formulasUsed = "Profit & Ratio identities", shortcutTricks = "Substitute base 100 to quickly confirm assertions.",
                        relatedConcepts = "Profit CP SP ratios", difficulty = difficulty
                    )
                } else {
                    val cost = 80.0 + (effIdx % 3) * 10
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
                    val wordsList = listOf("GATE", "EXAM", "KOTLIN", "GRADLE", "ROUTER", "ENGINE", "SYSTEM", "SIGNAL", "MATRIX", "VECTOR")
                    val word = wordsList[effIdx % wordsList.size]
                    val len = word.length
                    val frequencies = word.groupingBy { it }.eachCount()
                    var factorials = 1L
                    for (i in 1..len) {
                        factorials *= i
                    }
                    var denominator = 1L
                    for (cnt in frequencies.values) {
                        var itemFactorial = 1L
                        for (i in 1..cnt) {
                            itemFactorial *= i
                        }
                        denominator *= itemFactorial
                    }
                    val totalPermutations = factorials / denominator
                    val qText = "In \"$subtopicName\", how many mathematically distinct ways can the letters of the word \"$word\" be rearranged?"
                    val correctOpt = "$totalPermutations"
                    val wrongOpt1 = "${totalPermutations + 12}"
                    val wrongOpt2 = "${totalPermutations / 2}"
                    val wrongOpt3 = "${totalPermutations * 2}"
                    val options = listOf(correctOpt, wrongOpt1, wrongOpt2, wrongOpt3).distinct().shuffled(rand)
                    val correctIdx = options.indexOf(correctOpt)
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = options, correctOptions = listOf(correctIdx),
                        explanation = "Using the formula for permutations with indistinguishable objects: N! / (c1! * c2! * ...), the rearrangement yields exactly $totalPermutations.",
                        formulasUsed = "n! / (n1! * n2! ...) permutations", shortcutTricks = "Count duplicate characters and divide the factorial safely.",
                        relatedConcepts = "Combinatorics, factorials", difficulty = difficulty
                    )
                } else if (type == QuestionType.MSQ) {
                    val msqCombinatorics = listOf(
                        listOf(
                            "C(5, 2) is exactly 10.",
                            "P(5, 2) is exactly 20.",
                            "If two fair coins are tossed, the probability of obtaining at least one head is 3/4.",
                            "C(4, 4) is exactly 4."
                        ) to listOf(0, 1, 2),
                        listOf(
                            "C(6, 2) is exactly 15.",
                            "P(4, 2) is exactly 12.",
                            "Tossing a single fair six-sided die twice has a 1/36 probability of landing double sixes.",
                            "For any positive n, C(n, 1) is exactly n!."
                        ) to listOf(0, 1, 2),
                        listOf(
                            "C(7, 2) is exactly 21.",
                            "P(6, 2) is exactly 30.",
                            "If an event has a probability p of occurring, the complementary event has probability 1-p.",
                            "C(3, 2) is exactly 6."
                        ) to listOf(0, 1, 2)
                    )
                    val selCombinatorics = msqCombinatorics[effIdx % msqCombinatorics.size]
                    val qText = "Select all of the valid probabilistic and combinatorial propositions under \"$subtopicName\":"
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = selCombinatorics.first, correctOptions = selCombinatorics.second,
                        explanation = "Based on standard combinations, permutations, and probabilistic event spacing, the selected choices are valid bounds.",
                        formulasUsed = "C(n, r) and P(n, r) formulations", shortcutTricks = "Manually check small factorials.",
                        relatedConcepts = "Probability & arrangements", difficulty = difficulty
                    )
                } else {
                    val n = 5 + (effIdx % 4) // 5, 6, 7, 8
                    val r = 2
                    val ans = (n * (n - 1) / 2).toDouble()
                    val qText = "Determine the exact number of combinatorial selections of $r items from a set of $n distinct items (represented by C($n, $r)) under \"$subtopicName\" principles."
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = null, correctOptions = null,
                        correctNumericalRange = (ans - 0.01)..(ans + 0.01),
                        explanation = "C($n, 2) = ($n * ${n - 1}) / 2 = $ans.",
                        formulasUsed = "C(n, r) = n! / (r! * (n-r)!)", shortcutTricks = "Compute small coefficients systematically.",
                        relatedConcepts = "Permutations & Selections", difficulty = difficulty
                    )
                }
            }
            "apt_quant_logarithms", "apt_quant_data_interpretation", "apt_quant_geometry", "apt_quant_mensuration" -> {
                if (type == QuestionType.MCQ) {
                    val scaleFactorList = listOf(2, 3, 4, 5)
                    val k = scaleFactorList[effIdx % scaleFactorList.size]
                    val volumeFactor = k * k * k
                    val qText = "Under \"$subtopicName\" scaling laws, if the linear radius of a solid 3D sphere is multiplied by $k, its total volume increases by what factor?"
                    val correctOpt = "$volumeFactor"
                    val wrongOpt1 = "${k * k}"
                    val wrongOpt2 = "${2 * k}"
                    val wrongOpt3 = "${volumeFactor + k}"
                    val options = listOf(correctOpt, wrongOpt1, wrongOpt2, wrongOpt3).distinct().shuffled(rand)
                    val correctIdx = options.indexOf(correctOpt)
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = options, correctOptions = listOf(correctIdx),
                        explanation = "Volume of a 3D sphere is proportional to the third power of its linear dimensions (r^3). Scaling by $k scales volume by $k^3 = $volumeFactor.",
                        formulasUsed = "V = 4/3 * pi * r^3", shortcutTricks = "Volume is proportional to k^3.",
                        relatedConcepts = "Mensuration geometry", difficulty = difficulty
                    )
                } else if (type == QuestionType.MSQ) {
                    val geometryMsqs = listOf(
                        listOf(
                            "Each interior angle of a regular hexagon is exactly 120 degrees.",
                            "In a right triangle with sides 6 cm and 8 cm, the hypotenuse is exactly 10 cm.",
                            "For any real numbers, log(ab) = log(a) + log(b) is a valid logarithm identity.",
                            "The volume of a cone is half the volume of a cylinder of identical radius and height."
                        ) to listOf(0, 1, 2),
                        listOf(
                            "Each interior angle of an equilateral triangle is exactly 60 degrees.",
                            "The sum of interior angles of any planar quadrilateral is exactly 360 degrees.",
                            "For positive b and x, log_b(x^k) = k * log_b(x) is a valid identity.",
                            "A circle with radius r has a circumference of π * r."
                        ) to listOf(0, 1, 2),
                        listOf(
                            "The interior angle sum of a regular pentagon is exactly 540 degrees.",
                            "If the sides of similar triangles are in 1:2 ratio, then their areas are in 1:4 ratio.",
                            "The derivative of ln(x) is 1/x for all x > 0.",
                            "A sphere of radius r has a total surface area of 2 * π * r^2."
                        ) to listOf(0, 1, 2)
                    )
                    val selGeometry = geometryMsqs[effIdx % geometryMsqs.size]
                    val qText = "Select all mathematically sound geometric and logarithmic relationships under \"$subtopicName\":"
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = selGeometry.first, correctOptions = selGeometry.second,
                        explanation = "Evaluating polygon angles, Pythagorean triplets, logarithmic rules, or area scaling confirms these options as mathematically true.",
                        formulasUsed = "Symmetry & logarithm properties", shortcutTricks = "Double check standard conic and cylinder formulas.",
                        relatedConcepts = "Geometry, logarithms, shape scaling", difficulty = difficulty
                    )
                } else {
                    val s = 3 + (effIdx % 5) // 3, 4, 5, 6, 7
                    val ans = 6.0 * s * s
                    val qText = "A regular 3D cube representing structural elements in \"$subtopicName\" has an edge length of $s.0 cm. Find the total surface area in square centimeters."
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = null, correctOptions = null,
                        correctNumericalRange = (ans - 0.01)..(ans + 0.01),
                        explanation = "A regular 3D cube consists of 6 identical square faces. Thus, Total Area = 6 * s^2 = 6 * ($s.0)^2 = $ans sq cm.",
                        formulasUsed = "Area = 6 * s^2", shortcutTricks = "Compute basic face area and scale by face counts.",
                        relatedConcepts = "Mensuration scaling", difficulty = difficulty
                    )
                }
            }
            else -> {
                val workCombos = listOf(Pair(12, 6), Pair(12, 24), Pair(10, 15))
                val pair = workCombos[effIdx % workCombos.size]
                val x = pair.first
                val y = pair.second
                val ans = (x * y).toDouble() / (x + y).toDouble()
                if (type == QuestionType.MCQ) {
                    val speedPairs = listOf(Pair(40, 60), Pair(30, 60), Pair(50, 75), Pair(60, 90), Pair(40, 80))
                    val sp = speedPairs[effIdx % speedPairs.size]
                    val s1 = sp.first
                    val s2 = sp.second
                    val avgSpeed = (2.0 * s1 * s2) / (s1 + s2)
                    val correctStr = String.format(Locale.US, "%.2f km/h", avgSpeed)
                    val wm1 = String.format(Locale.US, "%.2f km/h", (s1 + s2) / 2.0)
                    val wm2 = String.format(Locale.US, "%.2f km/h", avgSpeed - 5.0)
                    val wm3 = String.format(Locale.US, "%.2f km/h", avgSpeed + 5.0)
                    
                    val qText = """
                        An engineer drives from home at an average speed of $s1 km/h for a task representing "$subtopicName" and immediately returns along the exact same path driving at $s2 km/h.
                        Compute the overall average speed (in km/h) for the entire round trip.
                    """.trimIndent()
                    val options = listOf(correctStr, wm1, wm2, wm3).distinct().shuffled(rand)
                    val correctIdx = options.indexOf(correctStr)
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = options, correctOptions = listOf(correctIdx),
                        explanation = "V_avg = 2 * V1 * V2 / (V1 + V2) = 2 * $s1 * $s2 / (${s1 + s2}) = $correctStr.",
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
        val subtopicOffset = if (subtopicId.hashCode() == Int.MIN_VALUE) Int.MAX_VALUE else kotlin.math.abs(subtopicId.hashCode())
        val effIdx = idx + subtopicOffset
        return when (subtopicId) {
            "apt_anal_number_series", "apt_anal_numerical_reasoning" -> {
                if (type == QuestionType.MCQ) {
                    val startOffset = 1 + (effIdx % 5) // 1 to 5
                    val multiplier = 1 + (effIdx % 3)  // 1 to 3
                    val seq = (1..5).map { (it * it) * multiplier + startOffset }
                    val expected = 36 * multiplier + startOffset
                    val qText = "Determine the next term in the logical number series under \"$subtopicName\": ${seq.joinToString(", ")}, ?"
                    val optCorrect = "$expected"
                    val optWrong1 = "${expected - (5 * multiplier)}"
                    val optWrong2 = "${expected + (5 * multiplier)}"
                    val optWrong3 = "${expected + 11}"
                    val options = listOf(optCorrect, optWrong1, optWrong2, optWrong3).distinct().shuffled(rand)
                    val correctIdx = options.indexOf(optCorrect)
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = options, correctOptions = listOf(correctIdx),
                        explanation = "Pattern of terms is T_n = n^2 * $multiplier + $startOffset. For n = 6, 6^2 * $multiplier + $startOffset = $expected.",
                        formulasUsed = "T_n = n^2 * m + s", shortcutTricks = "Identify first-order differences to observe the quadratic step size.",
                        relatedConcepts = "Progressions, sequences", difficulty = difficulty
                    )
                } else if (type == QuestionType.MSQ) {
                    val offset = effIdx % 4
                    val qText = "Select all of the option sequences that represent quadratic progression growth under \"$subtopicName\":"
                    val options = listOf(
                        "${offset + 2}, ${offset + 5}, ${offset + 10}, ${offset + 17}, ${offset + 26}, ${offset + 37}",
                        "${offset + 1}, ${offset + 4}, ${offset + 9}, ${offset + 16}, ${offset + 25}, ${offset + 36}",
                        "${offset + 3}, ${offset + 6}, ${offset + 11}, ${offset + 18}, ${offset + 27}, ${offset + 38}",
                        "${offset + 2}, ${offset + 4}, ${offset + 8}, ${offset + 16}, ${offset + 32}, ${offset + 64}"
                    )
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = options, correctOptions = listOf(0, 1, 2),
                        explanation = "A, B, and C grow quadratically (second-order difference is exactly 2). Option D grows exponentially (powers of 2), which is not quadratic progress.",
                        formulasUsed = "Sequence difference tracking", shortcutTricks = "Evaluate adjacent difference progressions.",
                        relatedConcepts = "Number Series structures", difficulty = difficulty
                    )
                } else {
                    val baseValue = 5 + (effIdx % 5) // 5, 6, 7, 8, 9
                    val seqTerms = (1 until baseValue).map { (it * it * it) }
                    val ans = (baseValue * baseValue * baseValue).toDouble()
                    val qText = "Solve for the next term in the cubic sequence under \"$subtopicName\": ${seqTerms.joinToString(", ")}, ?"
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = null, correctOptions = null,
                        correctNumericalRange = (ans - 0.01)..(ans + 0.01),
                        explanation = "Cubes: " + (1..baseValue).joinToString { "n^3 = $it^3" } + " = $ans.",
                        formulasUsed = "T_n = n^3", shortcutTricks = "Recognizing perfect integer cubes.",
                        relatedConcepts = "Cubic progressions", difficulty = difficulty
                    )
                }
            }
            "apt_anal_deduction_induction", "apt_anal_analogies" -> {
                if (type == QuestionType.MCQ) {
                    val base1 = 3 + (effIdx % 5) // 3 to 7
                    val base2 = base1 + 1
                    val square1 = base1 * base1
                    val square2 = base2 * base2
                    val qText = "Analyze the numerical analogy under \"$subtopicName\" and solve for the missing term: $base1 : $square1 :: $base2 : ?"
                    val options = listOf("$square2", "${square2 - 5}", "${square2 + 5}", "${base2 * 5}").distinct().shuffled(rand)
                    val correctIdx = options.indexOf("$square2")
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = options, correctOptions = listOf(correctIdx),
                        explanation = "The relationship is square mapping: x : x^2. Since $base1 is mapped to $square1, the counterpart $base2 maps directly to $square2.",
                        formulasUsed = "Analogy square function f(x) = x^2", shortcutTricks = "Identify square mapping values on both sides.",
                        relatedConcepts = "Analogies", difficulty = difficulty
                    )
                } else if (type == QuestionType.MSQ) {
                    val k = 2 + (effIdx % 4) // 2 to 5
                    val sq1 = k * k
                    val sq2 = (k + 1) * (k + 1)
                    val sq3 = (k + 2) * (k + 2)
                    val sq4_wrong = (k + 3) * (k + 3) - 6
                    val options = listOf("$k : $sq1", "${k + 1} : $sq2", "${k + 2} : $sq3", "${k + 3} : $sq4_wrong")
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = "Select all option pairs displaying the squaring function relation \"x : x^2\" under \"$subtopicName\":",
                        questionType = type,
                        options = options, correctOptions = listOf(0, 1, 2),
                        explanation = "Since $k^2=$sq1, ${(k+1)}^2=$sq2, and ${(k+2)}^2=$sq3 are mathematically correct. However, ${(k+3)}^2 is ${(k+3)*(k+3)}, not $sq4_wrong.",
                        formulasUsed = "x : x^2", shortcutTricks = "Square left term and cross-check right term.",
                        relatedConcepts = "Symmetric analogies", difficulty = difficulty
                    )
                } else {
                    val baseValue = 2 + (effIdx % 3) // 2, 3, 4
                    val resultValue1 = baseValue * baseValue * baseValue
                    val targetBase = baseValue + 1
                    val ans = (targetBase * targetBase * targetBase).toDouble()
                    val qText = "Determine the missing term in the cubic analogic relation under \"$subtopicName\": $baseValue : $resultValue1 :: $targetBase : ?"
                    GateQuestion(
                        id = qId, subjectId = subjectId, topicId = topicId, subtopicId = subtopicId, year = year,
                        questionText = qText, questionType = type, options = null, correctOptions = null,
                        correctNumericalRange = (ans - 0.01)..(ans + 0.01),
                        explanation = "Cubic analogy mapping: $baseValue^3 = $resultValue1, so $targetBase^3 = $ans.",
                        formulasUsed = "f(x) = x^3", shortcutTricks = "Recognize cube of base $targetBase.",
                        relatedConcepts = "Cubic analogical mappings", difficulty = difficulty
                    )
                }
            }
            else -> {
                if (type == QuestionType.MCQ) {
                    val stepsList = listOf(3, 4, 5, 6)
                    val step = stepsList[effIdx % stepsList.size]
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
                    val base = 4.0 + (effIdx % 4)
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
        val subtopicOffset = if (subtopicId.hashCode() == Int.MIN_VALUE) Int.MAX_VALUE else kotlin.math.abs(subtopicId.hashCode())
        val effIdx = idx + subtopicOffset
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

                if (type == QuestionType.MCQ || type == QuestionType.MSQ) {
                    val optCorrect = "${answer.toInt()}"
                    val optWrong1 = "${(answer + 4).toInt()}"
                    val optWrong2 = "${(answer - 2).toInt()}"
                    val optWrong3 = "${(answer * 2).toInt()}"
                    val optionsList = listOf(optCorrect, optWrong1, optWrong2, optWrong3).shuffled(rand)
                    val correctIndex = optionsList.indexOf(optCorrect)

                    GateQuestion(
                        id = qId,
                        subjectId = subjectId,
                        topicId = topicId,
                        subtopicId = subtopicId,
                        year = year,
                        questionText = qText,
                        questionType = type,
                        options = optionsList,
                        correctOptions = listOf(correctIndex),
                        correctNumericalRange = null,
                        explanation = "For non-interacting cascade systems, the overall transfer function or gain is simply the product of the individual gains: G_total = G1 * G2 = $g1 * $g2 = $answer.",
                        formulasUsed = "G_total(s) = G1(s) * G2(s)",
                        shortcutTricks = "Multiply the individual transfer functions together. Cascade means direct multiplication.",
                        relatedConcepts = "Cascade gain configurations, Gain modeling",
                        difficulty = difficulty
                    )
                } else {
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

        var questionText = ""
        var optionsList: List<String>? = null
        var correctOptionsList: List<Int>? = null
        var correctRange: ClosedRange<Double>? = null
        var explanationText = ""
        var formulaUsed = ""
        var shortcut = ""
        var concepts = ""

        when (subtopicId) {
            "nt_laws_basics" -> {
                val r1 = 4.0 + (index % 5) * 2.0
                val r2 = 10.0 + (index % 5) * 2.0
                val req = (r1 * r2) / (r1 + r2)
                when (type) {
                    QuestionType.MCQ -> {
                        questionText = "Under \"$subtopicName\", calculate the equivalent resistance (in ohms) of $r1 Ω and $r2 Ω resistors connected in parallel."
                        val correct = String.format(Locale.US, "%.2f Ω", req)
                        val incorrects = listOf(String.format(Locale.US, "%.2f Ω", r1 + r2), String.format(Locale.US, "%.2f Ω", r2 - r1), "1.00 Ω")
                        optionsList = (listOf(correct) + incorrects).distinct().shuffled(rand)
                        correctOptionsList = listOf(optionsList!!.indexOf(correct))
                        explanationText = "Parallel equivalent resistance formula is R_eq = (R1 * R2) / (R1 + R2) = ($r1 * $r2) / ($r1 + $r2) = $correct."
                        formulaUsed = "R_eq = (R1*R2)/(R1+R2)"
                        shortcut = "The parallel resistance is always strictly smaller than the smallest individual resistor."
                        concepts = "Resistor parallel configurations"
                    }
                    QuestionType.MSQ -> {
                        questionText = "Which of the following assertions about electrical network laws and parameters under \"$subtopicName\" are correct? (Select all that apply)"
                        optionsList = listOf("KCL is a direct manifestation of conservation of charge.", "KVL is based on conservation of energy.", "KVL is valid for non-linear circuits.", "KCL is only valid for planar networks.")
                        correctOptionsList = listOf(0, 1, 2)
                        explanationText = "KCL represents conservation of charge, while KVL represents conservation of energy. Both laws apply to any lumped network regardless of linearity or planar structure. Therefore, options A, B, and C are correct, whereas option D is incorrect."
                        formulaUsed = "Kirchhoff's Current Law, Kirchhoff's Voltage Law"
                        shortcut = "Identify conservation principles instantly."
                        concepts = "Charge vs Energy Conservation"
                    }
                    QuestionType.NAT -> {
                        val current = 2.0 + (index % 4)
                        val voltage = current * (r1 + r2)
                        questionText = "An electrical series loop under \"$subtopicName\" has $r1 Ω and $r2 Ω resistors with a steady-state current of $current A flowing. Calculate the source voltage (in Volts) across the series combinations."
                        correctRange = (voltage - 0.1)..(voltage + 0.1)
                        explanationText = "Voltage = I * R_total = I * (R1 + R2) = $current * ($r1 + $r2) = $voltage V."
                        formulaUsed = "V = I * (R1 + R2)"
                        shortcut = "Always sum series resistors first before multiplying with source current."
                        concepts = "Ohm's series voltage division"
                    }
                }
            }
            "nt_theorems_active" -> {
                val vth = 10.0 + (index % 5) * 2.0
                val rth = 4.0 + (index % 3)
                val pmax = (vth * vth) / (4.0 * rth)
                when (type) {
                    QuestionType.MCQ -> {
                        questionText = "For a network with a Thevenin equivalent voltage source of $vth V in series with a Thevenin resistance of $rth Ω under \"$subtopicName\", find the maximum power (in Watts) that can be delivered to a load resistor."
                        val correct = String.format(Locale.US, "%.2f W", pmax)
                        val incorrects = listOf(String.format(Locale.US, "%.2f W", pmax * 0.8), String.format(Locale.US, "%.2f W", pmax * 1.5), "5.00 W")
                        optionsList = (listOf(correct) + incorrects).distinct().shuffled(rand)
                        correctOptionsList = listOf(optionsList!!.indexOf(correct))
                        explanationText = "Maximum power transfer occurs when R_L = R_th. The power delivered under this condition is P = (V_th)^2 / (4 * R_th) = ($vth)^2 / (4 * $rth) = $correct."
                        formulaUsed = "P_max = (V_th^2) / (4 * R_th)"
                        shortcut = "Shortcut: The denominator factor is strictly 4 times the equivalent impedance."
                        concepts = "Maximum Power Transfer Theorem"
                    }
                    QuestionType.MSQ -> {
                        questionText = "Which of the following statements about network theorems under \"$subtopicName\" are correct?"
                        optionsList = listOf("Superposition is strictly valid for linear bilateral elements.", "Thevenin's equivalent resistance is identical to Norton's equivalent resistance.", "Tellegen's theorem is valid for non-linear, time-varying networks.", "Reciprocity applies to networks with dynamic dependent sources.")
                        correctOptionsList = listOf(0, 1, 2)
                        explanationText = "Superposition requires linearity, while Thevenin and Norton resistances are identical. Tellegen's theorem relies strictly on KCL and KVL, so it applies to any network regardless of linearity. Reciprocity does not generally apply to circuits with dependent sources."
                        formulaUsed = "Superposition, Reciprocity boundaries"
                        shortcut = "Check for linearity and source conditions in theorem applicability."
                        concepts = "Active Network Theorems"
                    }
                    QuestionType.NAT -> {
                        val vSource = 12.0 + (index % 4) * 2.0
                        val r1 = 6.0
                        val r2 = 6.0
                        val rthCalc = (r1 * r2) / (r1 + r2)
                        questionText = "Consider a circuit representing \"$subtopicName\" with a voltage source of $vSource V in series with a resistor bridge where the equivalent Thevenin output impedance is looking into a parallel combination of $r1 Ω and $r2 Ω. Calculate the Norton equivalent resistance (in Ω)."
                        correctRange = (rthCalc - 0.05)..(rthCalc + 0.05)
                        explanationText = "Norton equivalent resistance is identical to Thevenin resistance: R_N = R_th = ($r1 * $r2) / ($r1 + $r2) = $rthCalc Ω."
                        formulaUsed = "R_N = R_th"
                        shortcut = "Since R_th and R_N are identical, calculate the equivalent de-energized resistance directly."
                        concepts = "Norton Equivalent Resistance"
                    }
                }
            }
            "nt_ac_resonance" -> {
                val qfactor = 10.0 + (index % 4) * 5.0
                val rValue = 2.0 + (index % 3)
                when (type) {
                    QuestionType.MCQ -> {
                        questionText = "In a series RLC resonant circuit under \"$subtopicName\", the Quality factor is measured to be $qfactor with a resistance of $rValue Ω. Calculate the inductive reactance (in Ω) at resonance."
                        val xl = qfactor * rValue
                        val correct = String.format(Locale.US, "%.1f Ω", xl)
                        val incorrects = listOf(String.format(Locale.US, "%.1f Ω", xl / 2.0), String.format(Locale.US, "%.1f Ω", xl * 1.5), "20.0 Ω")
                        optionsList = (listOf(correct) + incorrects).distinct().shuffled(rand)
                        correctOptionsList = listOf(optionsList!!.indexOf(correct))
                        explanationText = "The quality factor of a series RLC circuit is defined as Q = X_L / R at resonance. Therefore, inductive reactance is X_L = Q * R = $qfactor * $rValue = $xl Ω."
                        formulaUsed = "Q = X_L / R"
                        shortcut = "Multiply Q by R directly to find XL."
                        concepts = "Series resonance parameters"
                    }
                    QuestionType.MSQ -> {
                        questionText = "Which of the following assertions are correct regarding AC circuit resonance in \"$subtopicName\"?"
                        optionsList = listOf("At series resonance, the circuit impedance is purely resistive and minimum.", "At series resonance, the current is maximum.", "At parallel resonance, the circuit impedance is maximum.", "The power factor at resonance is exactly unit (1.0).")
                        correctOptionsList = listOf(0, 1, 2, 3)
                        explanationText = "At series resonance, Z = R (minimum), current is maximum. At parallel resonance, admittance is minimum, so Z is maximum. Both resonance situations result in a purely real impedance, which implies unity power factor."
                        formulaUsed = "Resonant impedance properties"
                        shortcut = "Impedance is minimum for series, maximum for parallel. Both occur at unity power factor."
                        concepts = "Series vs Parallel AC states"
                    }
                    QuestionType.NAT -> {
                        val lH = 0.1
                        val cValueU = 10.0 + (index % 4) * 10.0
                        val cFarad = cValueU * 1e-6
                        val omegaCalc = 1.0 / Math.sqrt(lH * cFarad)
                        questionText = "Under \"$subtopicName\", a series RLC AC circuit has an inductance of $lH H and a capacitance of $cValueU μF. Compute the exact undamped resonant peak frequency (in rad/s)."
                        correctRange = (omegaCalc - 5.0)..(omegaCalc + 5.0)
                        explanationText = "The angular resonant frequency is ω_0 = 1 / √(L * C) = 1 / √($lH * $cFarad) = $omegaCalc rad/s."
                        formulaUsed = "ω_0 = 1 / √(L * C)"
                        shortcut = "Use the inverse root product of inductor and capacitor directly."
                        concepts = "Resonant frequency tuning"
                    }
                }
            }
            "nt_trans_response" -> {
                val valR = 10.0 + (index % 5) * 5.0
                val valC = 2.0 + (index % 3) * 2.0
                val tc = valR * valC
                when (type) {
                    QuestionType.MCQ -> {
                        questionText = "Find the steady-state transient time constant (in milliseconds) for a first-order RC network modeling \"$subtopicName\" where R = $valR kΩ and C = $valC μF."
                        val correct = String.format(Locale.US, "%.1f ms", tc)
                        val incorrects = listOf(String.format(Locale.US, "%.1f ms", tc / 2.0), String.format(Locale.US, "%.1f ms", tc * 1.5), "50.0 ms")
                        optionsList = (listOf(correct) + incorrects).distinct().shuffled(rand)
                        correctOptionsList = listOf(optionsList!!.indexOf(correct))
                        explanationText = "The time constant for an RC circuit is given by τ = R * C. Using R in kΩ and C in μF, the units automatically multiply to milliseconds: τ = $valR * $valC = $tc ms."
                        formulaUsed = "τ = R * C"
                        shortcut = "Kilo-ohms times micro-farads immediately yields milliseconds."
                        concepts = "Transient RC circuits"
                    }
                    QuestionType.MSQ -> {
                        questionText = "Under standard electrical AC transient response regimes in \"$subtopicName\", which of the following assertions are correct?"
                        optionsList = listOf("The voltage across a capacitor cannot change instantaneously.", "The current through an inductor cannot change instantaneously.", "At t = 0+, a fully discharged capacitor acts as a short circuit.", "At t = ∞ (steady state), a capacitor behaves as an open circuit in a DC network.")
                        correctOptionsList = listOf(0, 1, 2, 3)
                        explanationText = "By definition of energy conservation, capacitor voltage and inductor current cannot jump step changes. Upon excitation at t = 0+, a neutral capacitor acts as a short circuit. It transitions to open circuit at infinite steady-state boundaries."
                        formulaUsed = "Capacitor/Inductor transient states"
                        shortcut = "Instantly recognize continuity of physical state variables (v_C and i_L)."
                        concepts = "Time-domain initial conditions"
                    }
                    QuestionType.NAT -> {
                        val inductance = 0.1 + (index % 4) * 0.1
                        val iVal = 2.0
                        val energy = 0.5 * inductance * iVal * iVal
                        questionText = "An RL charging branch under \"$subtopicName\" has an inductance L = $inductance H and a current of $iVal A. Calculate the total magnetic energy (in Joules) stored in the inductor."
                        correctRange = (energy - 0.01)..(energy + 0.01)
                        explanationText = "Energy stored in an inductor is W_L = 0.5 * L * I^2 = 0.5 * $inductance * ($iVal)^2 = $energy J."
                        formulaUsed = "W_L = 0.5 * L * I^2"
                        shortcut = "Multiply half-inductance by the square of current."
                        concepts = "Inductor magnetic storage"
                    }
                }
            }
            "nt_parameters_3phase" -> {
                val factor = 2.0 + (index % 5)
                when (type) {
                    QuestionType.MCQ -> {
                        questionText = "A symmetrical, reciprocal T-network representing \"$subtopicName\" has impedance parameters Z_11 = ${10.0 * factor} Ω and Z_12 = ${5.0 * factor} Ω. Determine the value of open-loop impedance parameter Z_22."
                        val z22Val = 10.0 * factor
                        val correct = String.format(Locale.US, "%.1f Ω", z22Val)
                        val incorrects = listOf(String.format(Locale.US, "%.1f Ω", 5.0 * factor), String.format(Locale.US, "%.1f Ω", 15.0 * factor), "50.0 Ω")
                        optionsList = (listOf(correct) + incorrects).distinct().shuffled(rand)
                        correctOptionsList = listOf(optionsList!!.indexOf(correct))
                        explanationText = "Since the network is symmetrical, we must have Z_11 = Z_22 by definition. Thus, Z_22 = Z_11 = ${10.0 * factor} Ω."
                        formulaUsed = "Z_11 = Z_22 (symmetry condition)"
                        shortcut = "In symmetrical networks, diagonal parameter elements are always equal."
                        concepts = "Symmetry requirements in two-ports"
                    }
                    QuestionType.MSQ -> {
                        questionText = "Which of the following conditions correctly identify reciprocity and symmetry in two-port network parameters?"
                        optionsList = listOf("Reciprocal: Z12 = Z21", "Symmetrical: Z11 = Z22", "Reciprocal: AD - BC = 1", "Symmetrical: A = D")
                        correctOptionsList = listOf(0, 1, 2, 3)
                        explanationText = "Reciprocity is defined by Z12=Z21, Y12=Y21, and (AD-BC)=1. Symmetry is defined by Z11=Z22, Y11=Y22, and A=D."
                        formulaUsed = "Two-port parameters matrices conditions"
                        shortcut = "Memorize symmetry: A=D, Z11=Z22. Reciprocity: AD-BC=1, Z12=Z21."
                        concepts = "Network reciprocity matrix properties"
                    }
                    QuestionType.NAT -> {
                        val phaseVoltage = 230.0
                        val resPhase = 10.0 + (index % 5) * 5.0
                        val activePower = 3.0 * (phaseVoltage * phaseVoltage) / resPhase
                        questionText = "A balanced three-phase delta-connected pure resistive load is excited by a line voltage of $phaseVoltage V under \"$subtopicName\". If each phase has a resistance of $resPhase Ω, calculate the total active power load (in Watts) absorbed by the circuit."
                        correctRange = (activePower - 50.0)..(activePower + 50.0)
                        explanationText = "For delta configuration, phase voltage equals line voltage = $phaseVoltage V. Power per phase = V_phase^2 / R = ($phaseVoltage)^2 / $resPhase = ${phaseVoltage * phaseVoltage / resPhase} W. Total balanced active power = 3 * P_phase = 3 * ${phaseVoltage * phaseVoltage / resPhase} = $activePower W."
                        formulaUsed = "P_total = 3 * (V_line^2) / R"
                        shortcut = "For delta connected loads, scale individual power phases by factor 3."
                        concepts = "3phase power configurations"
                    }
                }
            }
            "sig_lti_convolution" -> {
                val scale = 1.0 + (index % 5)
                when (type) {
                    QuestionType.MCQ -> {
                        questionText = "A continuous-time signal enters an LTI system under \"$subtopicName\" with input x(t) = $scale · u(t) and impulse response h(t) = e^{-t} u(t). Evaluate the output y(t) as t approaches infinity."
                        val correct = String.format(Locale.US, "%.1f u(t)", scale)
                        val incorrects = listOf("0.0 u(t)", "Infinity", String.format(Locale.US, "%.1f t", scale))
                        optionsList = (listOf(correct) + incorrects).distinct().shuffled(rand)
                        correctOptionsList = listOf(optionsList!!.indexOf(correct))
                        explanationText = "The transfer function is H(s) = 1/(s+1). Using final value theorem or direct integration, y(∞) is the steady-state gain times input scaling: scale * H(0) = $scale * 1.0 = $scale."
                        formulaUsed = "y(∞) = x_0 * H(0)"
                        shortcut = "Steady-state value is input scale factor times DC response gain."
                        concepts = "Step responses of first order models"
                    }
                    QuestionType.MSQ -> {
                        questionText = "Which of the following properties are correct regarding Linear Time-Invariant (LTI) systems under \"$subtopicName\"?"
                        optionsList = listOf("An LTI system is Stable if its impulse response is absolutely integrable.", "An LTI system is Causal if its impulse response is zero for all t < 0.", "The convolution of any signal with a unit impulse δ(t) yields the signal itself.", "All LTI systems must be completely memoryless.")
                        correctOptionsList = listOf(0, 1, 2)
                        explanationText = "BIBO stability requires absolute integrability of impulse h(t) (Option A). Causality requires h(t) = 0 for t < 0 (Option B). Unit impulse is the identity element under convolution (Option C). LTI systems can possess memory."
                        formulaUsed = "LTI system principles"
                        shortcut = "Impulse response completely characterizes the causal and stability states."
                        concepts = "Causality and stability criteria"
                    }
                    QuestionType.NAT -> {
                        val inputAmp = 2.0 + (index % 4)
                        val result = inputAmp * 5.0
                        questionText = "An LTI integration block under \"$subtopicName\" convolves an input x(t) = $inputAmp · δ(t - 3) with an impulse response h(t) = 5 · δ(t - 2). Compute the amplitude multiplier of the resulting output impulse."
                        correctRange = (result - 0.01)..(result + 0.01)
                        explanationText = "Convolution of scaled impulses: a·δ(t-t1) * b·δ(t-t2) = (a*b)·δ(t - (t1+t2)). Here multiplier is $inputAmp * 5 = $result."
                        formulaUsed = "a·δ(t-t1) * b·δ(t-t2) = a*b·δ(t-t1-t2)"
                        shortcut = "Multiply the impulse coefficient factors directly."
                        concepts = "Shift operations in impulse convolution"
                    }
                }
            }
            "sig_sampling_rate" -> {
                val fhz = 100.0 + (index % 5) * 50
                val fNyqVal = 2.0 * fhz
                when (type) {
                    QuestionType.MCQ -> {
                        questionText = "A continuous-time AC communication terminal representing \"$subtopicName\" transmits x(t) = cos(2π · $fhz · t). Determine the minimum Nyquist sampling rate (in Hz) to safely digitize this signal."
                        val correct = String.format(Locale.US, "%.1f Hz", fNyqVal)
                        val incorrects = listOf(String.format(Locale.US, "%.1f Hz", fhz), String.format(Locale.US, "%.1f Hz", fNyqVal * 1.5), "50.0 Hz")
                        optionsList = (listOf(correct) + incorrects).distinct().shuffled(rand)
                        correctOptionsList = listOf(optionsList!!.indexOf(correct))
                        explanationText = "According to the Nyquist theorem, perfect recovery requires sampling at a rate strictly greater than or equal to twice the maximum frequency component. f_nyquist = 2 * f_max = 2 * $fhz = $fNyqVal Hz."
                        formulaUsed = "f_s >= 2 * f_max"
                        shortcut = "Double the highest operating signal frequency instantly."
                        concepts = "Shannon sampling theorems"
                    }
                    QuestionType.MSQ -> {
                        questionText = "Which of the following assertions are correct regarding Signal Transforms and Sampling inside \"$subtopicName\"?"
                        optionsList = listOf("Aliasing occurs if the sampling frequency is strictly less than the Nyquist rate.", "The Fourier Transform of an even real signal is always purely real.", "The Laplace transform ROC must not contain any poles.", "Increasing sampling rate always shifts the analytical frequency spectra down.")
                        correctOptionsList = listOf(0, 1, 2)
                        explanationText = "Violation of Nyquist rate results in aliasing (Option A). Symmetry of Fourier transform ensures real even signals yield real transforms (Option B). By definition, the Region of Convergence (ROC) cannot contain poles (Option C). Increasing the sampling rates does not shift base frequency states."
                        formulaUsed = "Nyquist criterion, Fourier properties"
                        shortcut = "Remember that mathematical poles exist where infinite limits of parameters break bounds."
                        concepts = "Laplace ROC and poles boundaries"
                    }
                    QuestionType.NAT -> {
                        val constantVal = 2.0 + (index % 5)
                        questionText = "Determine the value of the unilateral Laplace Transform of x(t) = e^{-$constantVal t} u(t) evaluated at s = 0 under \"$subtopicName\" calculations."
                        val resLap = 1.0 / constantVal
                        correctRange = (resLap - 0.01)..(resLap + 0.01)
                        explanationText = "The Laplace transform is X(s) = 1 / (s + $constantVal). Evaluating this at s = 0: X(0) = 1 / $constantVal = $resLap."
                        formulaUsed = "L{e^{-at}} = 1/(s+a)"
                        shortcut = "Inverse of exponential decay constant factor is equivalent to DC Laplace gain."
                        concepts = "One-sided Laplace transforms"
                    }
                }
            }
            "em_trans_equiv" -> {
                val turnRatio = 2.0 + (index % 5)
                when (type) {
                    QuestionType.MCQ -> {
                        questionText = "An ideal transformer representing \"$subtopicName\" has an armature winding with $turnRatio:1 step-down turn ratio. If 240 V is excited on the primary winding, calculate the induced secondary RMS voltage (in Volts)."
                        val secVolts = 240.0 / turnRatio
                        val correct = String.format(Locale.US, "%.1f V", secVolts)
                        val incorrects = listOf(String.format(Locale.US, "%.1f V", 240.0 * turnRatio), "120.0 V", "24.0 V")
                        optionsList = (listOf(correct) + incorrects).distinct().shuffled(rand)
                        correctOptionsList = listOf(optionsList!!.indexOf(correct))
                        explanationText = "For ideal transform configurations: V_p / V_s = N_p / N_s = turn_ratio. V_s = V_p / turn_ratio = 240.0 / $turnRatio = $secVolts V."
                        formulaUsed = "V_s = V_p / N_ratio"
                        shortcut = "Divide input voltage by specified transfer turns ratio directly."
                        concepts = "Ideal winding equations"
                    }
                    QuestionType.MSQ -> {
                        questionText = "Which of the following assertions are correct regarding transformers modeling in \"$subtopicName\"?"
                        optionsList = listOf("Transformer efficiency is maximum at a load where core loss equals copper loss.", "Eddie current losses are minimized by using thin insulated laminations.", "Open circuit test is conducted to determine core/iron losses.", "Short circuit test is generally conducted with low voltage on high-voltage winding.")
                        correctOptionsList = listOf(0, 1, 2, 3)
                        explanationText = "All listed properties represent classical transformer engineering models. Efficiency peak occurs under copper-loss to iron-loss balance. Laminations interrupt eddy current circulation paths. OC test yields core loss, SC test yields copper parameters on convenient sides."
                        formulaUsed = "Transformer losses criteria"
                        shortcut = "Equate core losses with armature copper losses for maximum energy transfer efficiency."
                        concepts = "Losses profiles and testing configurations"
                    }
                    QuestionType.NAT -> {
                        val plc = 100.0 + (index % 3) * 50.0
                        questionText = "A single-phase distribution transformer modeling \"$subtopicName\" has core/iron losses of $plc W. Calculate the copper loss (in Watts) when the transformer is operating under conditions of maximum efficiency."
                        correctRange = (plc - 0.1)..(plc + 0.1)
                        explanationText = "By classical machines theorem, standard maximum efficiency occurs when variable copper loss equals the constant iron loss. Hence maximum efficiency copper loss is identical to core losses = $plc W."
                        formulaUsed = "P_cu = P_core at η_max"
                        shortcut = "Equate loss variables instantly."
                        concepts = "Efficiency optimization rules"
                    }
                }
            }
            "em_dc_motors" -> {
                val vt = 220.0
                val ia = 10.0 + (index % 5) * 5.0
                val ra = 0.5
                val backEmf = vt - ia * ra
                when (type) {
                    QuestionType.MCQ -> {
                        questionText = "A DC shunt motor operating under \"$subtopicName\" is connected across a $vt V supply. If the armature current is $ia A and armature resistance is $ra Ω, calculate the induced back EMF (in Volts)."
                        val correct = String.format(Locale.US, "%.1f V", backEmf)
                        val incorrects = listOf(String.format(Locale.US, "%.1f V", vt + ia * ra), "200.0 V", "240.0 V")
                        optionsList = (listOf(correct) + incorrects).distinct().shuffled(rand)
                        correctOptionsList = listOf(optionsList!!.indexOf(correct))
                        explanationText = "The back EMF of a DC motor is given by E_b = V_t - I_a * R_a = $vt - $ia * $ra = $backEmf V."
                        formulaUsed = "E_b = V_t - I_a * R_a"
                        shortcut = "Subtract the armature resistive voltage drop from supply voltage."
                        concepts = "Armature back EMF loops"
                    }
                    QuestionType.MSQ -> {
                        questionText = "Which of the following assertions are correct regarding DC machines under \"$subtopicName\"?"
                        optionsList = listOf("In a DC shunt motor, speed is relatively constant with load variations.", "A DC series motor must never be started at no load.", "The commutator in a DC machine converts internal AC voltage to external DC terminals.", "Under motor conditions, the developed back EMF is always larger than terminal voltage.")
                        correctOptionsList = listOf(0, 1, 2)
                        explanationText = "Shunt motor fields are constant, yielding stable speed (Option A). At no-load, series torque vanishes, sending speed dangerously high to limit values (Option B). Commutator functions as mechanical rectifier (Option C). Back EMF in motor is always smaller than terminal voltage because of inner voltage drops."
                        formulaUsed = "DC Machines torque-speed curves"
                        shortcut = "No-load running of series motors causes exponential speed runaways."
                        concepts = "Motoring and commutation models"
                    }
                    QuestionType.NAT -> {
                        val torqueFactor = 1.5 + (index % 4) * 0.5
                        val armatureI = 20.0
                        val electromagnetTorque = torqueFactor * armatureI
                        questionText = "A DC motor modeling \"$subtopicName\" has an armature constant of $torqueFactor N-m/A. If the armature current draws exactly $armatureI A, calculate the developed electromagnetic torque (in N-m)."
                        correctRange = (electromagnetTorque - 0.05)..(electromagnetTorque + 0.05)
                        explanationText = "Armature torque is T_e = K_a_phi * I_a = $torqueFactor * $armatureI = $electromagnetTorque N-m."
                        formulaUsed = "T_e = K_t * I_a"
                        shortcut = "Multiply torque constant by armature current."
                        concepts = "DC machine torque generation"
                    }
                }
            }
            "em_ac_rotary" -> {
                val poles = 4 + (index % 3) * 2 // 4, 6, 8 poles
                val freq = 50.0
                val ns = (120 * freq) / poles
                when (type) {
                    QuestionType.MCQ -> {
                        questionText = "Compute the synchronous speed (in rpm) of a $poles-pole AC induction motor operating on a $freq Hz system under \"$subtopicName\"."
                        val correct = String.format(Locale.US, "%d rpm", ns.toInt())
                        val incorrects = listOf(String.format(Locale.US, "%d rpm", (ns * 0.9).toInt()), String.format(Locale.US, "%d rpm", (ns + 300).toInt()), "1000 rpm")
                        optionsList = (listOf(correct) + incorrects).distinct().shuffled(rand)
                        correctOptionsList = listOf(optionsList!!.indexOf(correct))
                        explanationText = "Synchronous speed is defined by N_s = 120 * f / P = 120 * $freq / $poles = $ns rpm."
                        formulaUsed = "N_s = 120 * f / P"
                        shortcut = "Use 120 * freq / poles directly to find AC rotor speed fields."
                        concepts = "AC rotating synchronous speed"
                    }
                    QuestionType.MSQ -> {
                        questionText = "Which of the following assertions are correct regarding AC rotating machines under \"$subtopicName\"?"
                        optionsList = listOf("At starting, the slip of an induction motor is exactly 1.0.", "The rotor speed of a three-phase induction motor is always slightly less than the synchronous speed.", "Insering resistance into the rotor circuit of a slip-ring motor increases starting torque.", "A synchronous motor is self-starting under standard AC excitation.")
                        correctOptionsList = listOf(0, 1, 2)
                        explanationText = "Starting implies N_r = 0, so slip s = (N_s - 0)/N_s = 1.0. If N_r reaches N_s, torque is zero, so induction motors must operate with minor slip. Rotor resistance is classical starting torque extension method. Symmetrical synchronous motors require dampers to start."
                        formulaUsed = "Induction motor slip mechanics"
                        shortcut = "Synchronous motors are never self-starting without secondary helper field devices."
                        concepts = "Slip dynamics and torque vectors"
                    }
                    QuestionType.NAT -> {
                        val motorSlip = 0.04 + (index % 3) * 0.02
                        val rotorFreq = motorSlip * freq
                        questionText = "A three-phase 50 Hz induction machine under \"$subtopicName\" operates with a rotor slip of $motorSlip. Compute the rotor induced current frequency (in Hz)."
                        correctRange = (rotorFreq - 0.01)..(rotorFreq + 0.01)
                        explanationText = "The rotor currency frequency is f_r = s * f = $motorSlip * $freq = $rotorFreq Hz."
                        formulaUsed = "f_r = s * f"
                        shortcut = "Multiply slip coefficient by the system grid frequency."
                        concepts = "Rotor slip frequencies"
                    }
                }
            }
            "em_special_steppers" -> {
                val steps = 100.0 + (index % 5) * 50.0
                val sa = 360.0 / steps
                when (type) {
                    QuestionType.MCQ -> {
                        questionText = "A special stepper motor representing \"$subtopicName\" is designed for $steps steps per revolution. Determine its step angle resolution (in degrees)."
                        val correct = String.format(Locale.US, "%.2f°", sa)
                        val incorrects = listOf(String.format(Locale.US, "%.2f°", sa * 2.0), String.format(Locale.US, "%.2f°", sa / 2.0), "1.80°")
                        optionsList = (listOf(correct) + incorrects).distinct().shuffled(rand)
                        correctOptionsList = listOf(optionsList!!.indexOf(correct))
                        explanationText = "Step angle is given by step_angle = 360° / total_steps = 360 / $steps = $sa°."
                        formulaUsed = "Step Angle = 360° / N_steps"
                        shortcut = "Divide 360 by total steps in one full revolution."
                        concepts = "Stepper incremental angular strides"
                    }
                    QuestionType.MSQ -> {
                        questionText = "Which of the following statements about special stepper and PM brushless motors are correct under \"$subtopicName\"?"
                        optionsList = listOf("Stepper motors are typically operated in open-loop position control mode.", "Variable reluctance stepper motors do not use any permanent magnets.", "An eddy-current brushless system undergoes periodic mechanical brush wear.", "Microstepping simplifies torque control by reducing stepping bounds.")
                        correctOptionsList = listOf(0, 1)
                        explanationText = "Steppers run in open-loop without encoder feedback (Option A). VR steppers have simple iron teeth rotors, no PMs (Option B). Brushless motors utilize solid-state commutation, avoiding brush wear entirely. Microstepping divides current vectors for high angular resolution, not purely torque bounds."
                        formulaUsed = "Stepper motor classifications"
                        shortcut = "VR refers to 'Variable Reluctance' showing lack of PM presence."
                        concepts = "Microstep resolution properties"
                    }
                    QuestionType.NAT -> {
                        val revs = 2.0 + (index % 4)
                        val totalSteps = revs * steps
                        questionText = "Find the total number of pulse inputs required to rotate a $steps steps/revolution stepper motor by exactly $revs full revolutions under \"$subtopicName\" systems."
                        correctRange = (totalSteps - 0.1)..(totalSteps + 0.1)
                        explanationText = "Total steps required is given by target_steps = revolutions * steps_per_revolution = $revs * $steps = $totalSteps."
                        formulaUsed = "Total Pulse Steps = Revs * Step_Resolution"
                        shortcut = "Multiply revolution count directly by the steps profile resolution factor."
                        concepts = "Discrete stepper positioning pulses"
                    }
                }
            }
            "ps_econ_dispatch" -> {
                val base = 10 + (index % 5) * 5
                val icCostVal = 2.0 * base
                when (type) {
                    QuestionType.MCQ -> {
                        questionText = "Two generating units scheduling fuel margins under \"$subtopicName\" operate with equal incremental costs dC/dP = $base + 0.5 P (in Rs/MWh). If a unit delivers P_1 = 20 MW, determine the incremental cost (in Rs/MWh)."
                        val price = base + 0.5 * 20.0
                        val correct = String.format(Locale.US, "%.1f Rs/MWh", price)
                        val incorrects = listOf(String.format(Locale.US, "%.1f Rs/MWh", price - 5.0), String.format(Locale.US, "%.1f Rs/MWh", price * 1.2), "50.0 Rs/MWh")
                        optionsList = (listOf(correct) + incorrects).distinct().shuffled(rand)
                        correctOptionsList = listOf(optionsList!!.indexOf(correct))
                        explanationText = "Incremental fuel cost at P_1 = 20 MW is dC/dP_1 = $base + 0.5 * (20.0) = $price Rs/MWh."
                        formulaUsed = "Incr_Cost = a + b * P"
                        shortcut = "Substitute the power output directly into the incremental cost function."
                        concepts = "Economic load scheduling dispatch"
                    }
                    QuestionType.MSQ -> {
                        questionText = "Which of the following assertions are correct regarding economic power generation and dispatch under \"$subtopicName\"?"
                        optionsList = listOf("Optimal scheduling requires all active plant increments to operate at identical incremental fuel costs.", "Transmission line active losses are completely neglected in absolute equal-increment models.", "The penalty factor of a plant depends on its electrical transmission line losses.", "Spinning reserve allows instant load pickup without generator start transitions.")
                        correctOptionsList = listOf(0, 1, 2, 3)
                        explanationText = "Economic load dispatch requires equal incremental cost coordination (Option A). Simple equal-increment models ignore line losses (Option B). Penalty factors explicitly scale unit cost lines by transmission derivatives (Option C). Spinning reserves keep turbines hot for immediate load picks."
                        formulaUsed = "Economic dispatch coordination equations"
                        shortcut = "Equal incremental costs criteria represents maximum optimization."
                        concepts = "Economic dispatch with transmission losses"
                    }
                    QuestionType.NAT -> {
                        val pGen = 50.0 + (index % 5) * 10
                        val pf = 1.0 + (index % 3) * 0.1
                        val effIncrCost = 25.0
                        val trueCost = effIncrCost / pf
                        questionText = "A power plant operating under \"$subtopicName\" has a penalty factor of $pf at a generation of $pGen MW. If its coordinates require an effective incremental cost of $effIncrCost Rs/MWh, find the unit internal incremental cost (in Rs/MWh)."
                        correctRange = (trueCost - 0.2)..(trueCost + 0.2)
                        explanationText = "The coordinated dispatch relation is dC_i / dP_i * L_i = effective_cost, where L_i is the penalty factor. Thus internal cost dC_i / dP_i = effective_cost / L_i = $effIncrCost / $pf = $trueCost Rs/MWh."
                        formulaUsed = "dC/dP = Effective_Cost / Penalty_Factor"
                        shortcut = "Divide the system-wide price by the local penalty factor."
                        concepts = "Penalty factors and transmission loss dispatch"
                    }
                }
            }
            "ps_trans_lines" -> {
                val valueL = 1.0e-3
                val valueC = (10.0 + (index % 5) * 5.0) * 1e-9
                val zc = Math.sqrt(valueL / valueC)
                when (type) {
                    QuestionType.MCQ -> {
                        questionText = "Compute the characteristic surge impedance (in Ω) of a lossless transmission line modeling \"$subtopicName\" where parameters are L = 1.0 mH/km and C = ${10.0 + (index % 5) * 5.0} nF/km."
                        val correct = String.format(Locale.US, "%.1f Ω", zc)
                        val incorrects = listOf(String.format(Locale.US, "%.1f Ω", zc * 1.5), String.format(Locale.US, "%.1f Ω", zc * 0.7), "400.0 Ω")
                        optionsList = (listOf(correct) + incorrects).distinct().shuffled(rand)
                        correctOptionsList = listOf(optionsList!!.indexOf(correct))
                        explanationText = "Surge impedance of a lossless transmission line is Z_c = √(L / C). For L = $valueL H and C = $valueC F, Z_c = √($valueL / $valueC) = $zc Ω."
                        formulaUsed = "Z_c = √(L / C)"
                        shortcut = "Substitute inductances and capacitances directly under the square root."
                        concepts = "Lossless transmission line parameters"
                    }
                    QuestionType.MSQ -> {
                        questionText = "Which of the following assertions are correct regarding transmission line behaviors in \"$subtopicName\"?"
                        optionsList = listOf("The Ferranti effect refers to voltage rises at the receiving end of lightly loaded lines.", "Surge impedance loading is completely independent of the transmission line length.", "ABCD parameters easily represent cascade groups of transmission spans directly.", "Series compensation capacitors are mainly used to decrease the overall line reactance.")
                        correctOptionsList = listOf(0, 1, 2, 3)
                        explanationText = "Lighthly loaded or open-circuit lines cause capacitive currents which boost receiving voltage above feed voltage (Ferranti). Z_c is purely based on L and C rates per length. Cascade sections correspond to simple ABCD matrix products. Series capacitors cancel reactive impedance to drop reactance bounds."
                        formulaUsed = "Transmission line propagation parameters"
                        shortcut = "Surge impedance is length-invariant. Standard series compensation cancels inductance reactance."
                        concepts = "Transient line modeling, surge loads, corona effects"
                    }
                    QuestionType.NAT -> {
                        val length = 100.0 + (index % 5) * 50
                        val resistancePerKm = 0.1
                        val totalSeriesR = length * resistancePerKm
                        questionText = "A standard $length km transmission line under \"$subtopicName\" has a series loop resistance of $resistancePerKm Ω/km. Calculate the total series line resistance (in Ω) representing loop losses."
                        correctRange = (totalSeriesR - 0.1)..(totalSeriesR + 0.1)
                        explanationText = "Total series resistance is given by R_total = R_per_km * length = $resistancePerKm * $length = $totalSeriesR Ω."
                        formulaUsed = "R_total = R_per_km * Line_Length"
                        shortcut = "Multiply resistance per unit length directly by total line span."
                        concepts = "Line parameter aggregation"
                    }
                }
            }
            "ps_flow_faults" -> {
                val vBase = 11.0
                val mvaBase = 100.0 + (index % 5) * 10.0
                val impedancePu = 0.1
                val shortCircuitCap = mvaBase / impedancePu
                when (type) {
                    QuestionType.MCQ -> {
                        questionText = "Compute the short-circuit capacity (in MVA) of a power system bus representing \"$subtopicName\" where system base is $mvaBase MVA and equivalent per-unit impedance looking back is $impedancePu pu."
                        val correct = String.format(Locale.US, "%.1f MVA", shortCircuitCap)
                        val incorrects = listOf(String.format(Locale.US, "%.1f MVA", shortCircuitCap * 0.1), String.format(Locale.US, "%.1f MVA", shortCircuitCap * 1.5), "500.0 MVA")
                        optionsList = (listOf(correct) + incorrects).distinct().shuffled(rand)
                        correctOptionsList = listOf(optionsList!!.indexOf(correct))
                        explanationText = "Short circuit capacity is given by S_sc = MVA_base / Z_pu = $mvaBase / $impedancePu = $shortCircuitCap MVA."
                        formulaUsed = "S_sc = MVA_base / Z_pu"
                        shortcut = "Divide the MVA base value directly by the per-unit impedance factor."
                        concepts = "Short-circuit fault analysis"
                    }
                    QuestionType.MSQ -> {
                        questionText = "Which of the following conditions represent symmetrical components classifications in \"$subtopicName\"?"
                        optionsList = listOf("Positive sequence components always have the identical phase sequence as original phasors.", "Negative sequence components have phase sequences opposite to positive sequence.", "Zero sequence components are equal in magnitude and strictly in phase with each other.", "Symmetrical three-phase faults strictly involve zero-sequence current flow.")
                        correctOptionsList = listOf(0, 1, 2)
                        explanationText = "Self-contained positive components maintain source rotation (Option A). Negative components switch rotation sequence (Option B). Zero components represent co-phasal vectors (Option C). Balanced Symmetrical 3-phase faults don't have earth paths, meaning zero sequence current is strictly zero."
                        formulaUsed = "Symmetrical components transformations"
                        shortcut = "Symmetrical faults never possess any zero sequence currents."
                        concepts = "Sequence network components"
                    }
                    QuestionType.NAT -> {
                        val baseVal = 100.0
                        val kvLine = 10.0 + (index % 4) * 10
                        val systemBaseZ = (kvLine * kvLine) / baseVal
                        questionText = "A power system grid under \"$subtopicName\" operates with a system base of $baseVal MVA and a line voltage of $kvLine kV. Calculate the baseline characteristic base impedance (in Ω)."
                        correctRange = (systemBaseZ - 0.1)..(systemBaseZ + 0.1)
                        explanationText = "Base impedance is given by Z_base = (kV_line)^2 / MVA_base = ($kvLine)^2 / $baseVal = $systemBaseZ Ω."
                        formulaUsed = "Z_base = kV^2 / MVA"
                        shortcut = "Square the voltage base value, then divide directly by the power base."
                        concepts = "Per-unit system constants"
                    }
                }
            }
            "ps_prot_stability" -> {
                val faultX = 0.1 + (index % 4) * 0.1
                val maxLimit = 1.0 / faultX
                when (type) {
                    QuestionType.MCQ -> {
                        questionText = "A power swing bus representing \"$subtopicName\" has terminal voltages V_1 = 1.0 pu and V_2 = 1.0 pu separated by an impedance reactanc of X = $faultX pu. Calculate the maximum steady-state power transfer capability (in pu)."
                        val correct = String.format(Locale.US, "%.2f pu", maxLimit)
                        val incorrects = listOf(String.format(Locale.US, "%.2f pu", maxLimit * 0.8), "1.00 pu", "5.00 pu")
                        optionsList = (listOf(correct) + incorrects).distinct().shuffled(rand)
                        correctOptionsList = listOf(optionsList!!.indexOf(correct))
                        explanationText = "Maximum power transfer in steady-state stability is calculated by P_max = (V_1 * V_2) / X = (1.0 * 1.0) / $faultX = $maxLimit pu."
                        formulaUsed = "P_max = (V_1 * V_2) / X"
                        shortcut = "Under unity voltages, maximum power is simply the reciprocal of path reactance."
                        concepts = "Steady-state stability criteria"
                    }
                    QuestionType.MSQ -> {
                        questionText = "Which of the following assertions are correct regarding systems protection schemes and relays in \"$subtopicName\"?"
                        optionsList = listOf("Distance protection schemes are commonly applied on high voltage transmission lines.", "Differential relays are typically utilized to protect generators and transformers against winding faults.", "An overcurrent relay acts on thresholds exceeding specific charging amperes.", "Reactance distance relays are completely immune to arc resistance variations.")
                        correctOptionsList = listOf(0, 1, 2, 3)
                        explanationText = "Distance schemes assess impedance indices (Option A). Differential schemes match current entries and exits, signaling internal failures instantly (Option B). Overcurrent systems evaluate fault current surges (Option C). Reactance-based distance units operate on the reactive component of line impedance, rendering them immune to arcing resistance."
                        formulaUsed = "Relay operating parameters constraints"
                        shortcut = "Reactance relays measure imaginary impedance components, thereby ignoring pure real arcing resistance."
                        concepts = "Relaying protection models"
                    }
                    QuestionType.NAT -> {
                        val synchInertia = 4.0 + (index % 4)
                        val kineticEnergy = 0.5 * synchInertia * 1.0
                        questionText = "A synchronous turbine under \"$subtopicName\" has an inertia rating of H = $synchInertia MJ/MVA on a base of 1.0 MVA. Compute the total kinetic energy storage (in Megajoules) at synchronous speed."
                        correctRange = (synchInertia - 0.05)..(synchInertia + 0.05)
                        explanationText = "Kinetic energy stored at synchronous speed is E_k = H * S_base = $synchInertia * 1.0 = $synchInertia MJ."
                        formulaUsed = "E_k = H * S_base"
                        shortcut = "Kinetic storage matches H-constant on unit MVA bases."
                        concepts = "Inertia characteristics of generators"
                    }
                }
            }
            "pe_device_char" -> {
                val triggerI = 20.0 + (index % 5) * 5.0
                when (type) {
                    QuestionType.MCQ -> {
                        questionText = "Under \"$subtopicName\", the holding current of a specific power SCR is measured to be $triggerI mA. Which of the following values represents the most realistic latching current for this device?"
                        val latchI = triggerI * 2.5
                        val correct = String.format(Locale.US, "%.1f mA", latchI)
                        val incorrects = listOf(String.format(Locale.US, "%.1f mA", triggerI * 0.5), String.format(Locale.US, "%.1f mA", triggerI), "5000.0 mA")
                        optionsList = (listOf(correct) + incorrects).distinct().shuffled(rand)
                        correctOptionsList = listOf(optionsList!!.indexOf(correct))
                        explanationText = "For any practical silicon controlled rectifier (SCR), the latching current (trigger status retention threshold) is typically 2 to 3 times larger than the holding current (conduction retention threshold). A factor of 2.5 gives $triggerI * 2.5 = $latchI mA."
                        formulaUsed = "I_latching ≈ 2.5 * I_holding"
                        shortcut = "Multiply holding current by approximately 2.5 to find latching current."
                        concepts = "Thyristor triggering thresholds"
                    }
                    QuestionType.MSQ -> {
                        questionText = "Which of the following assertions are correct regarding power electronic device parameters in \"$subtopicName\"?"
                        optionsList = listOf("The holding current is the minimum anode current required to maintain thyristor conduction.", "Snubber circuits are mainly used to protect thyristors against high dv/dt transients.", "A series inductor protects thyristors against high di/dt surges.", "The gate trigger current represents the threshold to toggle the SCR to conduction.")
                        correctOptionsList = listOf(0, 1, 2, 3)
                        explanationText = "All statements represent fundamental characteristics of power semiconductor switches. Holding current keeps SCR active (Option A). RC snubber networks limit high dv/dt voltage transients across terminals (Option B). Series inductors filter fast current surges (di/dt) (Option C). Gate excitation initiates conduction (Option D)."
                        formulaUsed = "Power semiconductor gate-anode structures"
                        shortcut = "Shorthand: snubber protects dv/dt; series inductor protects di/dt."
                        concepts = "Power switch ratings and protective networks"
                    }
                    QuestionType.NAT -> {
                        val loopCurrent = 10.0 + (index % 5) * 2.5
                        val fVoltsDrop = 1.4
                        val switchWatts = loopCurrent * fVoltsDrop
                        questionText = "A power diode operating in steady-state conduction under \"$subtopicName\" carries a continuous DC current of $loopCurrent A with a forward voltage drop of $fVoltsDrop V. Compute the resulting switch conduction losses (in Watts)."
                        correctRange = (switchWatts - 0.1)..(switchWatts + 0.1)
                        explanationText = "The power loss under continuous conduction is P = I_F * V_F = $loopCurrent * $fVoltsDrop = $switchWatts W."
                        formulaUsed = "P_loss = I * V_f"
                        shortcut = "Directly multiply terminal current by forward junction voltage drop."
                        concepts = "Conduction losses of switches"
                    }
                }
            }
            "pe_conv_buck_boost" -> {
                val inputV = 24.0
                val duty = 0.25 + (index % 5) * 0.1
                val outputV = inputV * duty
                when (type) {
                    QuestionType.MCQ -> {
                        questionText = "A standard Buck (step-down) DC-DC converter modeling \"$subtopicName\" is excited by an input voltage of $inputV V. If the duty cycle is $duty under continuous conduction mode, calculate the average output voltage (in Volts)."
                        val correct = String.format(Locale.US, "%.2f V", outputV)
                        val incorrects = listOf(String.format(Locale.US, "%.2f V", inputV / duty), String.format(Locale.US, "%.2f V", inputV * (1.0 - duty)), "12.00 V")
                        optionsList = (listOf(correct) + incorrects).distinct().shuffled(rand)
                        correctOptionsList = listOf(optionsList!!.indexOf(correct))
                        explanationText = "For a Buck converter operating in CCM, output voltage is V_o = D * V_in, where D is the duty cycle. V_o = $duty * $inputV = $outputV V."
                        formulaUsed = "V_o = D * V_in"
                        shortcut = "For Buck converters, multiply input voltage directly by duty cycle."
                        concepts = "Step-down converter dynamics"
                    }
                    QuestionType.MSQ -> {
                        questionText = "Which of the following assertions are correct regarding DC-DC converter models in \"$subtopicName\"?"
                        optionsList = listOf("A Buck converter acts exclusively as a step-down voltage system.", "A Boost converter acts exclusively as a step-up voltage system.", "A Buck-Boost converter can either step up or step down voltage based on duty cycle.", "In continuous conduction mode, the inductor current never reaches zero.")
                        correctOptionsList = listOf(0, 1, 2, 3)
                        explanationText = "All listed statements represent correct fundamental principles. Buck converter output is V_in*D (always <= V_in). Boost output is V_in/(1-D) (always >= V_in). Buck-Boost is V_in*D/(1-D), which can scale below or above input thresholds. CCM requires inductance current to stay positive."
                        formulaUsed = "DC-DC transfer equations"
                        shortcut = "CCM refers to 'Continuous Conduction Mode' meaning uninterrupted energy flow in inductor."
                        concepts = "Converter modes and topologies"
                    }
                    QuestionType.NAT -> {
                        val vinB = 12.0
                        val dB = 0.4 + (index % 3) * 0.1
                        val voB = vinB / (1.0 - dB)
                        questionText = "A standard Boost converter under \"$subtopicName\" is energized by an input voltage of $vinB V. If the duty cycle is set to $dB, calculate the ideal output voltage (in Volts) in CCM."
                        correctRange = (voB - 0.1)..(voB + 0.1)
                        explanationText = "For an ideal Boost converter, V_o = V_in / (1 - D) = $vinB / (1.0 - $dB) = $voB V."
                        formulaUsed = "V_o = V_in / (1 - D)"
                        shortcut = "Divide the input voltage by the complement of the duty cycle."
                        concepts = "Step-up converter voltage relations"
                    }
                }
            }
            "pe_drives_char" -> {
                val factor = 1.0 + (index % 5)
                when (type) {
                    QuestionType.MCQ -> {
                        questionText = "A converter-fed DC motor drive under \"$subtopicName\" is operated with armature voltage control. If the motor is running at a constant torque and speed is scaled by a factor of $factor, determine feed voltage scaling."
                        val correct = String.format(Locale.US, "Linear scale: %.1f times", factor)
                        val incorrects = listOf("Square scale", "Logarithmic scale", "Highly irregular non-linear")
                        optionsList = (listOf(correct) + incorrects).distinct().shuffled(rand)
                        correctOptionsList = listOf(optionsList!!.indexOf(correct))
                        explanationText = "In armature-controlled DC motors, the speed is proportional to back EMF: E_b = K · ω. If torque is constant, the drop I_a * R_a is constant, making terminal voltage V scale linearly with speed variations: V ≈ K · ω."
                        formulaUsed = "ω ∝ (V_t - I_a * R_a)"
                        shortcut = "In the constant-torque armature speed control zone, speed is linear with voltage."
                        concepts = "Armature speed-torque regimes"
                    }
                    QuestionType.MSQ -> {
                        questionText = "Which of the following assertions are correct regarding chopper and rectifier drives in \"$subtopicName\"?"
                        optionsList = listOf("A Class A chopper operates in the first quadrant, providing motoring action.", "Regenerative braking in DC motor drives requires feeding energy back to the source.", "Symmetrical dual controllers allow full four-quadrant speed and torque controls.", "AC variable frequency drives (VFDs) typically adjust synchronous speed via input frequency.")
                        correctOptionsList = listOf(0, 1, 2, 3)
                        explanationText = "All statements are correct. Motoring requires positive voltage and voltage flows. Regenerative braking loops convert kinetic deceleration to electrical grid feedback. Dual antiparallel converter configurations cover full reversible current and voltage planes (four quadrant). VFD synchronous speed scales directly with freq."
                        formulaUsed = "Four quadrant drive models"
                        shortcut = "Multi-quadrant operations require bilateral control of both current (torque) and voltage (speed) polarities."
                        concepts = "Quadrants profiles and controls"
                    }
                    QuestionType.NAT -> {
                        val baseE = 100.0 + (index % 5) * 20.0
                        val multi = 1.5
                        val targetE = baseE * multi
                        questionText = "A DC motor drive under \"$subtopicName\" develops a back EMF of $baseE V at 1000 rpm. Calculate the back EMF (in Volts) if speed is increased to 1500 rpm with a constant magnetic flux."
                        correctRange = (targetE - 0.2)..(targetE + 0.2)
                        explanationText = "Back EMF is directly proportional to speed under constant magnetic flux conditions: E_b_2 = E_b_1 * (N2 / N1) = $baseE * (1500 / 1000) = $baseE * 1.5 = $targetE V."
                        formulaUsed = "E_b ∝ Speed (N)"
                        shortcut = "Scale basic back EMF directly by speed ratio factor (1.5)."
                        concepts = "Motor speed projection constants"
                    }
                }
            }
            "ae_dio_bias" -> {
                val inputDc = 5.0 + (index % 5)
                val dioDrop = 0.7
                val resultCurrent = (inputDc - dioDrop) / 1.0
                when (type) {
                    QuestionType.MCQ -> {
                        questionText = "A silicon diode biasing circuit representing \"$subtopicName\" connects a DC source of $inputDc V in series with the forward-biased diode and a 1.0 kΩ resistor. Calculate the loop current (in mA)."
                        val correct = String.format(Locale.US, "%.2f mA", resultCurrent)
                        val incorrects = listOf(String.format(Locale.US, "%.2f mA", inputDc), String.format(Locale.US, "%.2f mA", (inputDc + dioDrop) / 1.0), "0.00 mA")
                        optionsList = (listOf(correct) + incorrects).distinct().shuffled(rand)
                        correctOptionsList = listOf(optionsList!!.indexOf(correct))
                        explanationText = "Active forward-biased silicon diodes have a nominal junction drop of 0.7 V. Applying KVL, the voltage across the series resistor is V_R = V_in - V_D = $inputDc - 0.7 = ${inputDc - dioDrop} V. Current I = V_R / R = ${inputDc - dioDrop} V / 1.0 kΩ = $resultCurrent mA."
                        formulaUsed = "I = (V_in - V_D) / R"
                        shortcut = "Subtract 0.7 V from source voltage first before dividing by resistance."
                        concepts = "Diode forward bias voltage drops"
                    }
                    QuestionType.MSQ -> {
                        questionText = "Which of the following assertions are correct regarding diode circuit applications in \"$subtopicName\"?"
                        optionsList = listOf("Diode clipping circuits are typically used to limit signal amplitudes from crossing thresholds.", "Clamping circuits alter the DC level of an AC waveform without changing its shape.", "A Zener diode acts as a constant voltage regulator in its reverse breakdown region.", "An ideal diode has zero impedance when forward biased and infinite impedance when reverse biased.")
                        correctOptionsList = listOf(0, 1, 2, 3)
                        explanationText = "All listed statements represent correct fundamental diode application principles under Material Design scopes. Clippers restrict peaks, clampers introduce DC bias level offsets, Zeners regulate via stable breakdown levels, and ideal switches are zero/infinite resistive."
                        formulaUsed = "Diode applications and characteristics"
                        shortcut = "Identify Zener properties: constant voltage regulation strictly in reverse breakdown configurations."
                        concepts = "Voltage clamping and clipper configurations"
                    }
                    QuestionType.NAT -> {
                        val vzValue = 5.0 + (index % 4) * 0.5
                        questionText = "A Zener diode voltage regulator circuit under \"$subtopicName\" employs a Zener diode with a breakdown rating of $vzValue V. If the reverse bias input voltage is 10 V, calculate the regulated output load voltage (in Volts)."
                        correctRange = (vzValue - 0.01)..(vzValue + 0.01)
                        explanationText = "In reverse breakdown, the Zener diode maintains a constant voltage equal to its breakdown rating across its terminals. The output voltage is regulated to exactly $vzValue V."
                        formulaUsed = "V_out = V_Z"
                        shortcut = "Regulated output is identically equal to the Zener breakdown rating."
                        concepts = "Zener reverse breakdown regulation"
                    }
                }
            }
            "ae_amp_feedback" -> {
                val openGain = 200.0
                val fbRatio = 0.01 + (index % 5) * 0.01
                val closedGain = openGain / (1.0 + openGain * fbRatio)
                when (type) {
                    QuestionType.MCQ -> {
                        questionText = "Evaluate the closed-loop voltage gain of a negative feedback amplifier representing \"$subtopicName\" where open-loop gain A = $openGain and feedback factor β = $fbRatio."
                        val correct = String.format(Locale.US, "%.2f", closedGain)
                        val incorrects = listOf(String.format(Locale.US, "%.2f", openGain * fbRatio), "1.00", "50.00")
                        optionsList = (listOf(correct) + incorrects).distinct().shuffled(rand)
                        correctOptionsList = listOf(optionsList!!.indexOf(correct))
                        explanationText = "Closed-loop voltage gain is A_f = A / (1 + A * β) = $openGain / (1 + $openGain * $fbRatio) = $closedGain."
                        formulaUsed = "A_f = A / (1 + A * β)"
                        shortcut = "Apply feedback formula with loop gain factor (1+Aβ) in denominator."
                        concepts = "Negative feedback amplifier gain"
                    }
                    QuestionType.MSQ -> {
                        questionText = "Which of the following assertions are correct regarding negative feedback in amplifiers under \"$subtopicName\"?"
                        optionsList = listOf("Negative feedback stabilizes the overall voltage gain against temperature fluctuations.", "Negative feedback increases the bandwidth of the amplifier.", "Negative feedback reduces non-linear harmonic distortion.", "Negative feedback increases the open-loop gain (A) of the amplifier.")
                        correctOptionsList = listOf(0, 1, 2)
                        explanationText = "By classical feedback theory, introducing negative loops drops absolute gain but stabilizes it (Option A). Bandwidth increases by the same loop feedback factor (Option B). Non-linear distortions drop (Option C). Open loop gain A is property of inner transistor, unaffected by external feedback structures."
                        formulaUsed = "Negative feedback characteristics"
                        shortcut = "Negative feedback trades off open-loop gain to acquire bandwidth and stability."
                        concepts = "Feedback trade-offs and gain margins"
                    }
                    QuestionType.NAT -> {
                        val loopFactor = 1.0 + openGain * fbRatio
                        questionText = "For the feedback amplifier under \"$subtopicName\" with open-loop gain A = $openGain and feedback factor β = $fbRatio, calculate the feedback loop feedback factor multiplier (1 + Aβ)."
                        correctRange = (loopFactor - 0.01)..(loopFactor + 0.01)
                        explanationText = "The feedback loop multiplier is (1 + A * β) = 1 + $openGain * $fbRatio = $loopFactor."
                        formulaUsed = "Feedback factor multiplier = 1 + A*β"
                        shortcut = "Multiply open-loop gain by feedback ratio and add one."
                        concepts = "Loop gain factors"
                    }
                }
            }
            "ae_opamp_apps" -> {
                val rfK = 10.0 + (index % 5) * 10
                val riK = 5.0
                val invertingGain = -rfK / riK
                when (type) {
                    QuestionType.MCQ -> {
                        questionText = "Calculate the closed-loop voltage gain of an ideal inverting operational amplifier representing \"$subtopicName\" where feedback resistor R_f = $rfK kΩ and input resistor R_in = $riK kΩ."
                        val correct = String.format(Locale.US, "%.1f", invertingGain)
                        val incorrects = listOf(String.format(Locale.US, "%.1f", 1.0 + rfK / riK), "0.0", "-1.0")
                        optionsList = (listOf(correct) + incorrects).distinct().shuffled(rand)
                        correctOptionsList = listOf(optionsList!!.indexOf(correct))
                        explanationText = "For ideal inverting op-amp configurations, gain is A_v = -R_f / R_in = -$rfK / $riK = $invertingGain."
                        formulaUsed = "A_v = -R_f / R_in"
                        shortcut = "Divide feedback resistance by input resistance and place a negative sign."
                        concepts = "Inverting amplifier transfer functions"
                    }
                    QuestionType.MSQ -> {
                        questionText = "Which of the following properties are correct regarding an ideal operational amplifier under \"$subtopicName\"?"
                        optionsList = listOf("Ideal operational amplifiers possess infinite input impedance.", "Ideal operational amplifiers possess zero output impedance.", "Ideal operational amplifiers possess infinite open-loop voltage gain.", "Ideal operational amplifiers possess zero common-mode rejection ratio (CMRR).")
                        correctOptionsList = listOf(0, 1, 2)
                        explanationText = "Ideal Op-Amps are modeled with R_in = ∞ (Option A), R_out = 0 (Option B), and A = ∞ (Option C). The ideal Common Mode Rejection Ratio (CMRR) is infinite (not zero), making Option D incorrect."
                        formulaUsed = "Ideal Op-Amp parameters"
                        shortcut = "Ideal parameters: R_in=∞, R_out=0, Open-loop gain=∞, CMRR=∞."
                        concepts = "Operational amplifier characteristics"
                    }
                    QuestionType.NAT -> {
                        val nonInvertingGain = 1.0 + rfK / riK
                        questionText = "An ideal non-inverting operational amplifier circuit under \"$subtopicName\" has resistors R_f = $rfK kΩ and R_in = $riK kΩ. Calculate the closed-loop voltage gain factor."
                        correctRange = (nonInvertingGain - 0.05)..(nonInvertingGain + 0.05)
                        explanationText = "For ideal non-inverting op-amp configurations, gain is A_v = 1 + R_f / R_in = 1 + $rfK / $riK = $nonInvertingGain."
                        formulaUsed = "A_v = 1 + R_f / R_in"
                        shortcut = "In non-inverting configurations, add one to the feedback-to-input resistance ratio."
                        concepts = "Non-inverting op-amp circuits"
                    }
                }
            }
            "de_numbers_gates" -> {
                val decValue = 10 + (index % 15)
                val binStr = Integer.toBinaryString(decValue)
                when (type) {
                    QuestionType.MCQ -> {
                        questionText = "Convert the decimal integer $decValue representing \"$subtopicName\" values to its equivalent binary representation."
                        val correct = binStr
                        val incorrects = listOf(Integer.toBinaryString(decValue + 2), Integer.toBinaryString(decValue - 1), "11111")
                        optionsList = (listOf(correct) + incorrects).distinct().shuffled(rand)
                        correctOptionsList = listOf(optionsList!!.indexOf(correct))
                        explanationText = "Decimal $decValue can be converted to binary using successive divisions by 2. This yields the binary sequence $binStr."
                        formulaUsed = "Decimal to binary conversion algorithm"
                        shortcut = "Convert base using binary powers indices."
                        concepts = "Radix representations"
                    }
                    QuestionType.MSQ -> {
                        questionText = "Which of the following assertions are correct regarding digital logic and gates under \"$subtopicName\"?"
                        optionsList = listOf("NAND is a universal logic gate.", "NOR is a universal logic gate.", "Any boolean function can be realized using NAND gates only.", "XOR is a universal gate.")
                        correctOptionsList = listOf(0, 1, 2)
                        explanationText = "NAND and NOR gates can implement AND, OR, and NOT functions, making them universal gates. XOR cannot implement simple AND or OR configurations on its own without complement assistance, so it is not universal."
                        formulaUsed = "Boolean universal structures proofs"
                        shortcut = "Only NAND and NOR are classified as universal logic gates."
                        concepts = "Universal logic configurations"
                    }
                    QuestionType.NAT -> {
                        val totalStates = Math.pow(2.0, (2.0 + (index % 4))).toInt()
                        questionText = "An n-bit binary counter under \"$subtopicName\" has exactly ${2 + (index % 4)} bits. Calculate the total number of distinct numerical states of this counter."
                        correctRange = (totalStates.toDouble() - 0.1)..(totalStates.toDouble() + 0.1)
                        explanationText = "An n-bit binary system has total_states = 2^n = 2^{${2 + (index % 4)}} = $totalStates distinct states."
                        formulaUsed = "Total states = 2^n"
                        shortcut = "Raise base 2 to the power of bit size."
                        concepts = "Binary permutations"
                    }
                }
            }
            "de_comb_mux" -> {
                val mult = 2.0 + (index % 3)
                when (type) {
                    QuestionType.MCQ -> {
                        questionText = "Consider a digital multiplexer under \"$subtopicName\" with $mult select lines. How many input channels can this multiplexer route?"
                        val inputCount = Math.pow(2.0, mult).toInt()
                        val correct = String.format(Locale.US, "%d inputs", inputCount)
                        val incorrects = listOf(String.format(Locale.US, "%d inputs", inputCount * 2), String.format(Locale.US, "%d inputs", inputCount - 1), "3 inputs")
                        optionsList = (listOf(correct) + incorrects).distinct().shuffled(rand)
                        correctOptionsList = listOf(optionsList!!.indexOf(correct))
                        explanationText = "A multiplexer with n select lines can route up to 2^n input channels to a single output. For n = $mult select lines, input channels = 2^$mult = $inputCount."
                        formulaUsed = "N_inputs = 2^(N_selects)"
                        shortcut = "Raise 2 to the power of select line count."
                        concepts = "Multiplexer routing configurations"
                    }
                    QuestionType.MSQ -> {
                        questionText = "Which of the following digital components represent purely combinational logic elements under \"$subtopicName\"?"
                        optionsList = listOf("Multiplexers", "Decoders", "Full Adders", "Shift Registers")
                        correctOptionsList = listOf(0, 1, 2)
                        explanationText = "Multiplexers, decoders, and adders are combinational systems since their output states depend strictly on instantaneous inputs. Shift registers contain flip-flop memory elements, making them sequential devices."
                        formulaUsed = "Logic system classifications"
                        shortcut = "If a device lacks memory elements like flip-flops or clocks, it is strictly combinational."
                        concepts = "Combinational vs Sequential designs"
                    }
                    QuestionType.NAT -> {
                        val numS = 4.0
                        questionText = "Determine the exact number of select lines required to implement a 16-to-1 multiplexer under \"$subtopicName\" configurations."
                        correctRange = (numS - 0.01)..(numS + 0.01)
                        explanationText = "For 2^n = 16 input lines, select lines required is n = log2(16) = $numS."
                        formulaUsed = "Select Lines = log2(Inputs)"
                        shortcut = "16 is 2 raised to power 4, hence 4 select lines are required."
                        concepts = "Sizing multiplexers"
                    }
                }
            }
            "de_seq_counters" -> {
                val modFactor = 4 + (index % 5)
                when (type) {
                    QuestionType.MCQ -> {
                        questionText = "A cascade of flip-flops represents a counter under \"$subtopicName\" with a division modulo of $modFactor. If the source input clock frequency is 24 kHz, determine the output frequency (in kHz)."
                        val outFreq = 24.0 / modFactor
                        val correct = String.format(Locale.US, "%.2f kHz", outFreq)
                        val incorrects = listOf("24.00 kHz", String.format(Locale.US, "%.2f kHz", 24.0 * modFactor), "1.00 kHz")
                        optionsList = (listOf(correct) + incorrects).distinct().shuffled(rand)
                        correctOptionsList = listOf(optionsList!!.indexOf(correct))
                        explanationText = "A modulo-N counter divides the input clock frequency by N. For input clock = 24 kHz and mod = $modFactor, output frequency = 24 / $modFactor = $outFreq kHz."
                        formulaUsed = "f_out = f_in / N"
                        shortcut = "Divide the input frequency directly by the modulo factor N."
                        concepts = "Clock division frequency dividers"
                    }
                    QuestionType.MSQ -> {
                        questionText = "Which of the following assertions are correct regarding sequential logic gates and counters in \"$subtopicName\"?"
                        optionsList = listOf("A flip-flop represents a fundamental 1-bit memory element.", "Synchronous counters trigger all flip-flops synchronously via a shared clock.", "Asynchronous counters suffer from cumulative propagation delays (skew).", "A JK flip-flop undergoes race-around conditions when inputs J and K are both 1 and enable is active.")
                        correctOptionsList = listOf(0, 1, 2, 3)
                        explanationText = "All statements are correct sequential design rules. Flip-flops store 1 bit. Synchronous drives distribute clock paths uniformly. Asynchronous drives route flip-flop outputs to subsequent clock slots, generating delay skews. State toggle race occurs when both inputs are energized."
                        formulaUsed = "Sequential logic properties"
                        shortcut = "Remember that asynchronous counters are also called ripple counters due to delay ripples."
                        concepts = "Clock distribution and race conditions"
                    }
                    QuestionType.NAT -> {
                        val ffCount = 3 + (index % 4)
                        val synchStates = Math.pow(2.0, ffCount.toDouble())
                        questionText = "A synchronous binary counter modeling \"$subtopicName\" is constructed using exactly $ffCount flip-flops. Calculate the total number of distinct state indexes of this counter."
                        correctRange = (synchStates - 0.1)..(synchStates + 0.1)
                        explanationText = "The total number of digital state indexes for synchronous counters with N flip-flops is 2^N = 2^$ffCount = $synchStates states."
                        formulaUsed = "N_states = 2^n"
                        shortcut = "Raise 2 to the power of flip-flop count."
                        concepts = "Flip-flop state structures"
                    }
                }
            }
            "em_statics_equations" -> {
                val distanceSquare = 2.0 + (index % 4)
                val forceMultiplier = 1.0 / (distanceSquare * distanceSquare)
                when (type) {
                    QuestionType.MCQ -> {
                        questionText = "Under \"$subtopicName\", electrostatic Coulomb's forces are evaluated between two point charges. If distance is scaled by $distanceSquare, calculate the scaling factor of the force."
                        val correct = String.format(Locale.US, "Decline: %.3f times", forceMultiplier)
                        val incorrects = listOf("Increase: 2.00 times", "Decline: 1.000 times", "Linear decay")
                        optionsList = (listOf(correct) + incorrects).distinct().shuffled(rand)
                        correctOptionsList = listOf(optionsList!!.indexOf(correct))
                        explanationText = "Coulomb's Law states electrostatic force is inversely proportional to the square of distance (r): F ∝ 1 / r^2. If distance is scaled by $distanceSquare, the force is scaled by 1 / ($distanceSquare)^2 = $forceMultiplier."
                        formulaUsed = "F ∝ 1 / r^2"
                        shortcut = "Inverse of square distance factor holds."
                        concepts = "Coulomb's inverse square laws"
                    }
                    QuestionType.MSQ -> {
                        questionText = "Which of the following equations represent classical Maxwell forms in time-varying field frameworks under \"$subtopicName\"? (Select all that apply)"
                        optionsList = listOf("curl E = -dB/dt", "curl H = J + dD/dt", "div D = ρ", "div B = 0")
                        correctOptionsList = listOf(0, 1, 2, 3)
                        explanationText = "All listed equations represent classical differential Maxwell forms. Faraday's law of induction (Option A), generalized Ampere's law (Option B), Gauss's electrostatic law (Option C), and Gauss's magnetostatic law representing absence of monopoles (Option D)."
                        formulaUsed = "Maxwell's Equations"
                        shortcut = "Memorize Maxwell standard differential representations."
                        concepts = "Maxwell postulates, charge densities, boundary bounds"
                    }
                    QuestionType.NAT -> {
                        val chargeA = 2.0 + (index % 5)
                        val factorCalc = chargeA * 9.0
                        questionText = "Calculate the scalar coefficient (in 10^9 N-m²/C²) representing relative electrostatic forces of charge size $chargeA C at distance 1 m under \"$subtopicName\" definitions."
                        correctRange = (factorCalc - 0.5)..(factorCalc + 0.5)
                        explanationText = "The force scalar is constant_k * q_1 = 9e9 * $chargeA = $factorCalc e9."
                        formulaUsed = "F = k * q"
                        shortcut = "Constant k represents approximately 9 billion in standard units, so scale by charge."
                        concepts = "Permittivity coefficients"
                    }
                }
            }
            "em_wave_prop_lines" -> {
                val zl = 100.0 + (index % 5) * 50.0
                val z0 = 50.0
                val gamma = (zl - z0) / (zl + z0)
                when (type) {
                    QuestionType.MCQ -> {
                        questionText = "Evaluate the real reflection coefficient (Γ) of a lossless transmission line modeling \"$subtopicName\" where load impedance is $zl Ω and characteristic line impedance is $z0 Ω."
                        val correct = String.format(Locale.US, "%.3f", gamma)
                        val incorrects = listOf("0.000", "1.000", String.format(Locale.US, "%.3f", gamma * 1.5))
                        optionsList = (listOf(correct) + incorrects).distinct().shuffled(rand)
                        correctOptionsList = listOf(optionsList!!.indexOf(correct))
                        explanationText = "The reflection coefficient is given by Γ = (Z_L - Z_0) / (Z_L + Z_0) = ($zl - $z0) / ($zl + $z0) = ${zl - z0} / ${zl + z0} = $gamma."
                        formulaUsed = "Γ = (Z_L - Z_0) / (Z_L + Z_0)"
                        shortcut = "Divide the impedance mismatch difference by the impedance sum factor directly."
                        concepts = "Line propagation and impedance mismatch"
                    }
                    QuestionType.MSQ -> {
                        questionText = "Which of the following assertions are correct regarding wave propagation in transmission lines under \"$subtopicName\"?"
                        optionsList = listOf("The Voltage Standing Wave Ratio (VSWR) is always greater than or equal to 1.0.", "Skin depth of a conductor decreases as frequency increases.", "Electromagnetic waves in a vacuum travel exactly at the speed of light.", "In a lossless medium, the characteristic wave impedance is purely imaginary.")
                        correctOptionsList = listOf(0, 1, 2)
                        explanationText = "VSWR bounds range from 1.0 to infinity (Option A). Higher frequencies drive skin effect, reducing skin depth (Option B). Vacuum propagation speed is c ≈ 3e8 m/s (Option C). Lossless wave impedance is purely real (not imaginary), making Option D incorrect."
                        formulaUsed = "Wave propagation equations"
                        shortcut = "Impedance of lossless structures is real. VSWR baseline is 1.0 representing perfect matching."
                        concepts = "VSWR matching, reflection, skin effect"
                    }
                    QuestionType.NAT -> {
                        val vswr = (1.0 + gamma) / (1.0 - gamma)
                        questionText = "Compute the Voltage Standing Wave Ratio (VSWR) of the transmission line configuration containing load Z_L = $zl Ω and characteristic impedance Z_0 = $z0 Ω under \"$subtopicName\" wave systems."
                        correctRange = (vswr - 0.05)..(vswr + 0.05)
                        explanationText = "First calculate the reflection coefficient Γ = (Z_L - Z_0) / (Z_L + Z_0) = ($zl - $z0) / ($zl + $z0) = $gamma. Then VSWR = (1 + |Γ|) / (1 - |Γ|) = (1 + $gamma) / (1 - $gamma) = $vswr."
                        formulaUsed = "VSWR = (1 + |Γ|) / (1 - |Γ|)"
                        shortcut = "VSWR can also be computed directly as Z_L / Z_0 if Z_L > Z_0: $zl / $z0 = $vswr."
                        concepts = "Standing waves parameters"
                    }
                }
            }
            "mi_meters_ac_dc" -> {
                val rm = 10.0
                val mult = 2 + (index % 5)
                val rsh = rm / (mult - 1)
                when (type) {
                    QuestionType.MCQ -> {
                        questionText = "Under \"$subtopicName\", we want to extend the range of a PMMC ammeter with internal resistance R_m = $rm Ω by a multiplier of $mult. Calculate the required shunt resistance (R_sh) across ammeter terminals."
                        val correct = String.format(Locale.US, "%.2f Ω", rsh)
                        val incorrects = listOf(String.format(Locale.US, "%.2f Ω", rm), String.format(Locale.US, "%.2f Ω", rm * (mult - 1)), "1.00 Ω")
                        optionsList = (listOf(correct) + incorrects).distinct().shuffled(rand)
                        correctOptionsList = listOf(optionsList!!.indexOf(correct))
                        explanationText = "To extend ammeter ranges: R_sh = R_m / (m - 1), where m is the scaling multiplier factor. R_sh = $rm / ($mult - 1) = $rsh Ω."
                        formulaUsed = "R_sh = R_m / (m - 1)"
                        shortcut = "Divide ammeter internal resistance directly by multiplier factor minus one."
                        concepts = "Meter shunt resistance range extension"
                    }
                    QuestionType.MSQ -> {
                        questionText = "Which of the following assertions are correct regarding AC and DC electrical measuring instruments under \"$subtopicName\"?"
                        optionsList = listOf("Permanent Magnet Moving Coil (PMMC) instruments measure average (DC) values only.", "Moving Iron (MI) instruments can measure both AC and DC root-mean-square currents.", "A Wheatstone bridge is typically utilized to measure medium resistances.", "A Schering bridge is commonly applied to evaluate high-frequency inductor values.")
                        correctOptionsList = listOf(0, 1, 2)
                        explanationText = "PMMC registers only DC due to unidirectional torque (Option A). MI torque is proportional to current square, making it valid for both AC and DC (Option B). Wheatstone is standard for medium resistance (Option C). Schering bridges are used to measure capacitive values and dissipation factors, not inductances."
                        formulaUsed = "Dynamic measuring systems"
                        shortcut = "PMMC registers only DC average; MI registers general RMS values."
                        concepts = "Bridge balances and coil indicators"
                    }
                    QuestionType.NAT -> {
                        val r1Val = 100.0
                        val r2Val = 10.0 * (1 + (index % 5))
                        val capacityVal = 1e-6
                        val lxVal = r1Val * r2Val * capacityVal
                        questionText = "In a Maxwell inductance bridge representing \"$subtopicName\", balance occurs with branch resistors R1 = $r1Val Ω, R2 = $r2Val Ω, and parallel tuning capacitor C3 = 1.0 μF. Compute the balanced target inductance value L_x (in Henrys)."
                        correctRange = (lxVal - 0.001)..(lxVal + 0.001)
                        explanationText = "Balance criteria for Maxwell bridges specifies balanced inductance L_x = R1 * R2 * C3. L_x = $r1Val * $r2Val * $capacityVal = $lxVal H."
                        formulaUsed = "L_x = R1 * R2 * C3"
                        shortcut = "Multiply the balancing branch resistors directly by the tuning capacitor."
                        concepts = "Maxwell bridge inductive balances"
                    }
                }
            }
            "mi_transducers_cro" -> {
                val cycleNum = 2.0 + (index % 4)
                val freqKhz = cycleNum / 1.0 // 1 ms sweep
                val freqHz = freqKhz * 1000.0
                when (type) {
                    QuestionType.MCQ -> {
                        questionText = "An auxiliary thermocouple temperature probe representing \"$subtopicName\" exhibits linear thermo-EMF. Which of the following describes thermoelectric characteristics?"
                        val correct = "Voltage: Seebeck threshold output"
                        val incorrects = listOf("Current: Hall current output", "Capacitor: Dielectric charge", "Optoelectronic: LED emissions")
                        optionsList = (listOf(correct) + incorrects).distinct().shuffled(rand)
                        correctOptionsList = listOf(optionsList!!.indexOf(correct))
                        explanationText = "Thermocouple instruments operate strictly under thermoelectric conversion, yielding Seebeck-dependent voltage outputs across temperature interfaces."
                        formulaUsed = "Seebeck thermal conversion"
                        shortcut = "Thermocouple registers voltage outputs directly tied to junction gradients."
                        concepts = "Junction thermal potentials"
                    }
                    QuestionType.MSQ -> {
                        questionText = "Which of the following assertions are correct regarding transducers and CRO measurements under \"$subtopicName\"?"
                        optionsList = listOf("LVDT represents a passive inductive displacement transducer.", "Thermocouples operate strictly under Seebeck thermo-EMF potentials.", "A resistive strain gauge converts strain changes to resistive changes.", "A piezoelectric transducer can be applied to measure static pressures under steady steady indefinitely.")
                        correctOptionsList = listOf(0, 1, 2)
                        explanationText = "LVDT varies mutual inductances (Option A). Thermocouple operates under active thermoelectric Seebeck loops (Option B). Strain gauges operate on structural resistance changes under strain (Option C). Piezoelectric materials allow charge leaking, making them unsuitable for pure static pressures over long times."
                        formulaUsed = "Transducers physical actions"
                        shortcut = "Piezoelectric transducers require dynamic physical variations, failing under static states."
                        concepts = "Transducers models and CRO scopes"
                    }
                    QuestionType.NAT -> {
                        questionText = "A CRO terminal under \"$subtopicName\" shows exactly $cycleNum cycles of a sinusoidal wave on its horizontal display scale. If the total time axis duration is exactly 1.0 millisecond, compute the wave frequency (in Hz)."
                        correctRange = (freqHz - 10.0)..(freqHz + 10.0)
                        explanationText = "Frequency is cycles divided by duration: f = $cycleNum cycles / 1.0 ms = $freqKhz kHz = $freqHz Hz."
                        formulaUsed = "f = cycles / duration"
                        shortcut = "Multiply trace cycle density directly by inverse sweep milliseconds base."
                        concepts = "CRO time domain frequency computations"
                    }
                }
            }
            else -> {
                // Symmetrical general electrical fallback
                val turnsPrimaryVal = 100 * (1 + (index % 10))
                val ratioVal = 2 + (index % 9)
                val secTurns = turnsPrimaryVal / ratioVal
                val vInput = 20 * ratioVal * (2 + (index % 5))
                val vOutput = vInput.toDouble() / ratioVal
                when (type) {
                    QuestionType.MCQ -> {
                        questionText = "Consider a standard power transformer representing \"$subtopicName\" which has $turnsPrimaryVal turns on the primary winding and $secTurns turns on the secondary. Calculate the step-down ratio."
                        val correct = String.format(Locale.US, "Ratio = %d:1", ratioVal)
                        val incorrects = listOf("Ratio = 1:1", "Ratio = 10:1", "Ratio = 100:1")
                        optionsList = (listOf(correct) + incorrects).distinct().shuffled(rand)
                        correctOptionsList = listOf(optionsList!!.indexOf(correct))
                        explanationText = "The turn step-down ratio is simply N_p / N_s = $turnsPrimaryVal / $secTurns = $ratioVal."
                        formulaUsed = "Ratio = N_p / N_s"
                        shortcut = "Divide primary turns by secondary turns directly."
                        concepts = "Transformer winding definitions"
                    }
                    QuestionType.MSQ -> {
                        questionText = "Select all correct assertions regarding standard AC transformers under \"$subtopicName\"."
                        optionsList = listOf("Ideal transformers conserve power such that S_primary = S_secondary.", "Transformers do not perform any frequency modification.", "Iron losses depend primarily on voltage and frequency.", "Active winding copper losses are strictly load independent.")
                        correctOptionsList = listOf(0, 1, 2)
                        explanationText = "Ideal devices converse total apparent power (Option A). Transformers shift current and voltage scales but preserve standard grid frequency (Option B). Core losses are invariant index with load under constant grid state (Option C). Active copper losses scale in proportion to current squared, which is highly load dependent."
                        formulaUsed = "Elementary transformer equations"
                        shortcut = "Copper losses scale quadratically with active loop load currents."
                        concepts = "Losses profiles"
                    }
                    QuestionType.NAT -> {
                        questionText = "A power transformer modeling step-down properties in \"$subtopicName\" has $turnsPrimaryVal turns on the primary winding and $secTurns turns on the secondary. If the primary voltage is $vInput V, calculate the secondary terminal voltage (in Volts)."
                        correctRange = (vOutput - 0.1)..(vOutput + 0.1)
                        explanationText = "Winding relation gives V_s = V_p * (N_s / N_p) = $vInput / $ratioVal = $vOutput V."
                        formulaUsed = "V_s = V_p * (N_s / N_p)"
                        shortcut = "Divide primary voltage directly by turn ratio factor."
                        concepts = "Step down voltages models"
                    }
                }
            }
        }

        return GateQuestion(
            id = qId,
            subjectId = subjectId,
            topicId = topicId,
            subtopicId = subtopicId,
            year = year,
            questionText = questionText,
            questionType = type,
            options = optionsList,
            correctOptions = correctOptionsList,
            correctNumericalRange = correctRange,
            explanation = explanationText,
            formulasUsed = formulaUsed,
            shortcutTricks = shortcut,
            relatedConcepts = concepts,
            difficulty = difficulty
        )
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
        val subtopicOffset = if (subtopicId.hashCode() == Int.MIN_VALUE) Int.MAX_VALUE else kotlin.math.abs(subtopicId.hashCode())
        val effIdx = index + subtopicOffset

        return when (type) {
            QuestionType.MCQ -> {
                if (effIdx % 2 == 0) {
                    val stepsList = listOf(3, 4, 5, 6)
                    val step = stepsList[effIdx % stepsList.size]
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
                        Pair("Aeronauts", "Pilots"),
                        Pair("Biologists", "Researchers"),
                        Pair("Cardiologists", "Doctors"),
                        Pair("Attorneys", "Lawyers"),
                        Pair("Acousticians", "Physicists"),
                        Pair("Economists", "Analysts"),
                        Pair("Archaeologists", "Historians"),
                        Pair("Auditors", "Accountants"),
                        Pair("Miners", "Technicians"),
                        Pair("Botanists", "Academics"),
                        Pair("Dermatologists", "Clinicians"),
                        Pair("Statisticians", "Mathematicians")
                    )
                    val pair = professions[effIdx % professions.size]
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
                    Triple("Compilers", "Translators", "Software"),
                    Triple("Screws", "Fasteners", "Magnetic"),
                    Triple("Turbines", "Generators", "Pneumatic"),
                    Triple("Diodes", "Semiconductors", "Bilateral"),
                    Triple("Inductors", "Reactors", "Resistive"),
                    Triple("Transistors", "Amplifiers", "Passive"),
                    Triple("Protocols", "Standards", "Hardware"),
                    Triple("Files", "Data-structures", "Analog"),
                    Triple("Keyboards", "Inputs", "Wireless"),
                    Triple("Routers", "Gateways", "Cabled"),
                    Triple("Algorithms", "Procedures", "Static"),
                    Triple("Sensors", "Transducers", "Actuators")
                )
                val sel = items[effIdx % items.size]
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
                if (effIdx % 2 == 0) {
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
                    val chars = listOf("GATE", "NPTEL", "MATH", "EEE", "CODE", "FLOW", "NODE", "DATA", "KERN", "LINK", "HEAP", "STAC", "LOOP", "TREE", "BYTE")
                    val str = chars[effIdx % chars.size]
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
