package com.example.data

object GateSyllabus {

    val subjects: List<Subject> by lazy {
        listOf(
            createGeneralAptitude(),
            createReasoning(),
            createEngineeringMath(),
            createNetworkTheory(),
            createSignalsAndSystems(),
            createControlSystems(),
            createElectricalMachines(),
            createPowerSystems(),
            createPowerElectronics(),
            createAnalogElectronics(),
            createDigitalElectronics(),
            createElectromagneticTheory(),
            createMeasurements()
        )
    }

    private fun createGeneralAptitude(): Subject {
        val subjectId = "general_aptitude"
        return Subject(
            id = subjectId,
            name = "General Aptitude",
            iconName = "menu_book",
            topics = listOf(
                Topic(
                    id = "apt_verbal_ability",
                    subjectId = subjectId,
                    name = "Verbal Ability",
                    subtopics = listOf(
                        Subtopic(
                            id = "apt_verb_grammar_usage",
                            topicId = "apt_verbal_ability",
                            subjectId = subjectId,
                            name = "English Grammar",
                            theory = TheoryContent(
                                title = "English Grammar & Usage",
                                synopsis = "Covers essential rules of English grammar count, articles, tenses, subject-verb agreement (concord), and structural prepositional usage.",
                                detailedBullets = listOf(
                                    "Verb Tenses: Master absolute consistency across compound clauses.",
                                    "Articles & Concord: Rules for 'a', 'an' and 'the' with singular/plural countable nouns.",
                                    "Conjunctions: Proper utilization of coordinating/correlative connectors (neither...nor, either...or)."
                                ),
                                keyInsight = "When dual subjects are connected by neither/nor or either/or, the verb matches the closer subject."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Correlative Concord Rule",
                                    expression = "Neither [Subj 1] nor [Subj 2] (plural) -> Plural Verb",
                                    description = "The verb agrees with the closer subject class.",
                                    applicationTrick = "Check grammatical plurality of the noun next to the verb."
                                )
                            ),
                            pyqs = listOf(
                                GateQuestion(
                                    id = "pyq_apt_verb_vocab_1",
                                    subjectId = subjectId,
                                    topicId = "apt_verbal_ability",
                                    subtopicId = "apt_verb_grammar_usage",
                                    year = 2024,
                                    questionText = "Select the correct pair of words to complete the following sentence:\n\n'The researcher's main argument was so _______ that even the most skeptical members of the advisory board were persuaded to fund the project; however, its implementation remains a highly _______ endeavour.'",
                                    questionType = QuestionType.MCQ,
                                    options = listOf(
                                        "cogent, precarious",
                                        "specious, lucrative",
                                        "tenuous, formidable",
                                        "prosaic, simple"
                                    ),
                                    correctOptions = listOf(0),
                                    explanation = "'Cogent' means clear, logical, and convincing, which aligns perfectly with persuading a skeptical board. 'Precarious' means dependent on chance, uncertain, or risky, which matches the contrast introduced by 'however'. Therefore, this pair fits the context precisely.",
                                    formulasUsed = "Contextual semantic matching and conjunctive transition analysis",
                                    shortcutTricks = "The key indicator is 'persuaded' which requires the first blank to mean highly convincing. This narrows choices to 'cogent'. Verify with 'precarious' which satisfies the contrast.",
                                    relatedConcepts = "Vocabulary, sentence completion, conjunctions",
                                    difficulty = "Medium"
                                )
                            ),
                            practiceQuestions = listOf(
                                GateQuestion(
                                    id = "pract_apt_verb_concord_1",
                                    subjectId = subjectId,
                                    topicId = "apt_verbal_ability",
                                    subtopicId = "apt_verb_grammar_usage",
                                    year = 2023,
                                    questionText = "Identify the grammatically correct sentence from the options below:",
                                    questionType = QuestionType.MCQ,
                                    options = listOf(
                                        "Neither the main speaker nor the event coordinators is prepared to address the media.",
                                        "Neither the main speaker nor the event coordinators are prepared to address the media.",
                                        "Neither the main speaker nor the event coordinators is preparing to address the media.",
                                        "Neither the main speaker nor the event coordinators has prepared to address the media."
                                    ),
                                    correctOptions = listOf(1),
                                    explanation = "When two subjects are connected by 'neither... nor', the verb agrees with the subject closest to it. Here, 'event coordinators' is plural and closest to the verb, hence requiring the plural verb 'are'.",
                                    formulasUsed = "Proximity Concord rule with correlative conjunctions",
                                    shortcutTricks = "The noun next to the verb is plural ('event coordinators'). Eliminate all singular options immediately ('is', 'has').",
                                    relatedConcepts = "Subject-verb agreement, conjunctions",
                                    difficulty = "Easy"
                                )
                            ),
                            mockQuiz = listOf(
                                GateQuestion(
                                    id = "mock_apt_verb_reading_1",
                                    subjectId = subjectId,
                                    topicId = "apt_verbal_ability",
                                    subtopicId = "apt_verb_grammar_usage",
                                    year = 2025,
                                    questionText = "Read the following passage carefully:\n\n'While the primary goal of modern architecture is functional spatial utility, it is equally crucial that physical structures resonate with the local cultural heritage, rather than imposing a sterile, homogenised concrete aesthetic.'\n\nWhich of the following statements summarizes the main theme of the passage?",
                                    questionType = QuestionType.MCQ,
                                    options = listOf(
                                        "Utility is the only benchmark for evaluating modern physical structures.",
                                        "Modern architecture must balance functional utility with local cultural resonance.",
                                        "Sterile concrete aesthetics are more economical than cultural preservation.",
                                        "Cultural heritage is the absolute prerequisite for structural spatial durability."
                                    ),
                                    correctOptions = listOf(1),
                                    explanation = "The passage highlights a dual requirement for modern architecture: achieving functional utility while ensuring cultural resonance, opposing the imposition of purely sterile designs. This indicates a necessary balance.",
                                    formulasUsed = "Reading comprehension theme identification",
                                    shortcutTricks = "Look for balancing connectors like 'equally crucial', which indicates both concepts must exist in parallel.",
                                    relatedConcepts = "Critical reading, comprehension",
                                    difficulty = "Easy"
                                )
                            )
                        ),
                        createSimpleSubtopic("apt_verb_completion", "apt_verbal_ability", subjectId, "Sentence Completion", "Comprehending structural semantic transitions to fill contextual blanks elegantly.", "Always check the grammatical tone transitions such as 'consequently' or 'however' to match positive or negative option choices."),
                        createSimpleSubtopic("apt_verb_vocab", "apt_verbal_ability", subjectId, "Vocabulary", "Mastery of common synonyms, antonyms, prefix, suffix, and contextual definitions.", "Examine the word root and replace options in the sentence definition."),
                        createSimpleSubtopic("apt_verb_reading", "apt_verbal_ability", subjectId, "Reading Comprehension", "Extracting major logical arguments and central themes from detailed technical texts.", "Scan the primary statement, note key thesis transitions, and identify structural conclusions."),
                        createSimpleSubtopic("apt_verb_analogies", "apt_verbal_ability", subjectId, "Verbal Analogies", "Mapping structural associations from one contextual class pair to another.", "Define the exact relational verb between the first pair (e.g. 'is a part of') to map it symmetrically."),
                        createSimpleSubtopic("apt_verb_word_groups", "apt_verbal_ability", subjectId, "Word Groups", "Categorizing vocabulary and identifying thematic logical anomalies within word segments.", "Identify the common architectural or functional classification among choices and pick the outlier."),
                        createSimpleSubtopic("apt_verb_critical", "apt_verbal_ability", subjectId, "Critical Reasoning", "Evaluating logical assertions, strengthening or weakening contextual arguments.", "Isolate the core assumption. An argument is strengthened when its underlying assumptions are verified."),
                        createSimpleSubtopic("apt_verb_narrative_seq", "apt_verbal_ability", subjectId, "Narrative Sequencing", "Ordering randomized paragraph statements to form coherent textual sequences.", "Identify the standalone introductory statement first, then track chronological and conjunctive transition cues.")
                    )
                ),
                Topic(
                    id = "apt_quantitative",
                    subjectId = subjectId,
                    name = "Quantitative Aptitude",
                    subtopics = listOf(
                        createSimpleSubtopic("apt_quant_ratios", "apt_quantitative", subjectId, "Ratios", "Mathematical ratio tracking, distributions, mixtures, and logical partitions.", "Express ratios in standard algebraic coefficients (e.g., k, 3k, 5k) to simplify solving."),
                        createSimpleSubtopic("apt_quant_percentages", "apt_quantitative", subjectId, "Percentages", "Percentage change tracking, profit scaling, discount models, and rate calculations.", "A percentage increase of x% followed by a decrease of x% always represents a net loss of (x/10)^2 %."),
                        createSimpleSubtopic("apt_quant_profit_loss", "apt_quantitative", subjectId, "Profit and Loss", "Calculations related to cost price, selling price, discounts, markups, and profit margins.", "Establish cost price as 100% base to compute sequential percentages directly."),
                        createSimpleSubtopic("apt_quant_time_work", "apt_quantitative", subjectId, "Time and Work", "Combined rate solving, pipe flow speeds, and worker timeline allocations.", "Speed rate of a worker is inversely proportional to time: Rate = 1/T."),
                        createSimpleSubtopic("apt_quant_permutation_combination", "apt_quantitative", subjectId, "Permutation and Combination", "Arrangement permutations, selection combinations, and restricted grouped ordering.", "Check if order matters. Use Permutation if order is material, and Combination for selections."),
                        createSimpleSubtopic("apt_quant_probability", "apt_quantitative", subjectId, "Probability", "Probabilistic definitions, independent events, mutually exclusive constraints, and conditional formulas.", "For conditional queries, utilize the partition sum under Bayes Theorem directly."),
                        createSimpleSubtopic("apt_quant_logarithms", "apt_quantitative", subjectId, "Logarithms", "Logarithmic standard operations, functional base scaling, and exponential models.", "Change of base formula: log_b(a) = log_c(a) / log_c(b)."),
                        createSimpleSubtopic("apt_quant_data_interpretation", "apt_quantitative", subjectId, "Data Interpretation", "Reading data arrays, tables, pie charts, and trend line distributions.", "Always inspect pie chart percentages against absolute bounds before computing numerical values."),
                        createSimpleSubtopic("apt_quant_geometry", "apt_quantitative", subjectId, "Geometry", "Properties of circular chords, triangle similarities, parallel line transversals, and coordinates.", "Apply properties of similar triangles to set up linear distance ratios instantly."),
                        createSimpleSubtopic("apt_quant_mensuration", "apt_quantitative", subjectId, "Mensuration", "Surface area and volume limits for cones, cylinders, regular polygons, and spheres.", "Scaling a shape's linear dimensions by k scales its surface area by k^2 and volume by k^3."),
                        Subtopic(
                            id = "apt_numerical_computation",
                            topicId = "apt_quantitative",
                            subjectId = subjectId,
                            name = "Numerical Computation",
                            theory = TheoryContent(
                                title = "Quantitative Methods & Data Interpretation",
                                synopsis = "Covers essential calculations in geometry, mensuration limits, statistical distributions, probabilistic experiments, and interpreting charts.",
                                detailedBullets = listOf(
                                    "Geometry & Mensuration: Rules of circular chords, interior polygon sums, and surface area/volumes of cones, cylinders, and spheres.",
                                    "Elementary Statistics: Center metrics (Mean, Median, Mode) and dispersion scales (Variance, Standard Deviation).",
                                    "Probability: Basic combinations, independent events, mutually exclusive constraints, and Bayes Theorem.",
                                    "Data Graphs & Interpretation: Analyzing slope, trends, and values in line graphs, bar charts, and pie charts."
                                ),
                                keyInsight = "For asymmetric or highly skewed datasets, the median represents a much more reliable metric of central tendency than the mean."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Bayes Theorem Formulation",
                                    expression = "P(A|B) = [P(B|A) * P(A)] / P(B)",
                                    description = "Evaluates posterior probability given prior conditions.",
                                    applicationTrick = "Use a tree diagram to divide favorable conditional branches over the total probability sum."
                                ),
                                FormulaItem(
                                    name = "Sphere Surface & Volume",
                                    expression = "Volume = (4/3)*π*r³, Area = 4*π*r²",
                                    description = "Gives the analytical boundaries for 3D spheres.",
                                    applicationTrick = "Scaling the radius r by factor k scales surface area by k² and volume by k³."
                                )
                            ),
                            pyqs = listOf(
                                GateQuestion(
                                    id = "pyq_apt_num_cone",
                                    subjectId = subjectId,
                                    topicId = "apt_quantitative",
                                    subtopicId = "apt_numerical_computation",
                                    year = 2024,
                                    questionText = "A conical tank of base radius 6 m and height 12 m is being filled with water at a constant rate of 3 cubic meters per minute. Find the rate of rise of the water level (in meters per minute) when the depth of water in the tank is exactly 4 m.",
                                    questionType = QuestionType.NAT,
                                    correctOptions = null,
                                    correctNumericalRange = 0.23..0.25,
                                    explanation = "Volume of a cone: V = (1/3) * π * r² * h.\nBy similar triangles, base radius R and height H of the tank scale together, so r/h = 6/12 = 1/2 => r = h/2.\n\nSubstitute r into the volume formula:\nV = (1/3) * π * (h/2)² * h = (π / 12) * h³.\n\nDifferentiate with respect to time t using the chain rule:\ndV/dt = (π / 12) * 3h² * (dh/dt) = (π * h² / 4) * (dh/dt).\n\nGiven dV/dt = 3 m³/min and depth h = 4 m:\n3 = (π * 4² / 4) * (dh/dt) => 3 = 4π * (dh/dt).\ndh/dt = 3 / (4π) ≈ 0.2387 meters per minute.",
                                    formulasUsed = "V = (1/3)*π*r²*h, Chain Rule of derivatives dV/dt = (dV/dh) * (dh/dt)",
                                    shortcutTricks = "Express the relation of r and h first, then differentiate V(h) with respect to h: dV/dh = π*h²/4. Divide dV/dt by dV/dh to find dh/dt immediately.",
                                    relatedConcepts = "Mensuration, rates of change, geometry",
                                    difficulty = "Medium"
                                )
                            ),
                            practiceQuestions = listOf(
                                GateQuestion(
                                    id = "pract_apt_num_prob",
                                    subjectId = subjectId,
                                    topicId = "apt_quantitative",
                                    subtopicId = "apt_numerical_computation",
                                    year = 2023,
                                    questionText = "A bag contains 5 red balls and 7 blue balls. If three balls are drawn at random without replacement, what is the probability that exactly two of the drawn balls are red?",
                                    questionType = QuestionType.MCQ,
                                    options = listOf(
                                        "7/22",
                                        "21/44",
                                        "5/12",
                                        "35/110"
                                    ),
                                    correctOptions = listOf(0),
                                    explanation = "To draw exactly 2 red and 1 blue ball without replacement:\n- Favorable choices = C(5, 2) * C(7, 1) = 10 * 7 = 70.\n- Total choices to draw 3 balls from 12 = C(12, 3) = (12 * 11 * 10) / 6 = 220.\n- Probability = Favorable / Total = 70 / 220 = 7 / 22.",
                                    formulasUsed = "Combinations formula C(n, r) and hypergeometric probability",
                                    shortcutTricks = "Write the calculation simply: 10 * 7 / 220 = 70/220 = 7/22 immediately.",
                                    relatedConcepts = "Probability theory, stats & counts, numerical reasoning",
                                    difficulty = "Easy"
                                )
                            ),
                            mockQuiz = listOf(
                                GateQuestion(
                                    id = "mock_apt_num_cagr",
                                    subjectId = subjectId,
                                    topicId = "apt_quantitative",
                                    subtopicId = "apt_numerical_computation",
                                    year = 2025,
                                    questionText = "The following line graph depicts the annual revenue (in million USD) of a software firm over five years:\n- Year 1: 10\n- Year 2: 15\n- Year 3: 12\n- Year 4: 24\n- Year 5: 30\n\nDetermine the compounded annual growth rate (CAGR) in percentage of the firm's revenue from Year 1 to Year 5.",
                                    questionType = QuestionType.NAT,
                                    correctOptions = null,
                                    correctNumericalRange = 31.0..32.0,
                                    explanation = "Compound Annual Growth Rate is calculated as:\nCAGR = (End Value / Start Value) ^ (1 / n) - 1, where n is the number of compounding intervals.\n\nHere, Start Value (Yr 1) = 10, End Value (Yr 5) = 30.\nGrowth period n = 5 - 1 = 4 intervals.\nCAGR = (30 / 10) ^ (1 / 4) - 1 = 3 ^ 0.25 - 1.\n3 ^ 0.25 ≈ 1.31607.\nCAGR = 0.31607 = 31.61%.",
                                    formulasUsed = "CAGR = (End/Start) ^ (1/n) - 1",
                                    shortcutTricks = "Since n is 4 years, look for CAGR where (1 + r) ^ 4 = 3. Since 1.316 ^ 4 ≈ 3.0, r must be around 31.6%.",
                                    relatedConcepts = "Data graphs, compound metrics, estimation",
                                    difficulty = "Hard"
                                )
                            )
                        )
                    )
                ),
                Topic(
                    id = "apt_analytical_aptitude",
                    subjectId = subjectId,
                    name = "Analytical Aptitude",
                    subtopics = listOf(
                        Subtopic(
                            id = "apt_analytical_reasoning",
                            topicId = "apt_analytical_aptitude",
                            subjectId = subjectId,
                            name = "Logical Reasoning",
                            theory = TheoryContent(
                                title = "Deductive & Inductive Structural Reasoning",
                                synopsis = "Analytical aptitude evaluates the ability to draw logically sound conclusions, recognize patterns, establish analogical patterns, and evaluate inductive/deductive reasoning chains.",
                                detailedBullets = listOf(
                                    "Deductive Logic: Standard syllogisms where conclusions are certain if universal premises are true.",
                                    "Inductive Logic: Identifying recurring sequences or relationships and generalizing rules.",
                                    "Analogical Reasoning: Mapping structural associations from one context to another (e.g., Cause-to-Effect, Class-to-Member).",
                                    "Number Relations: Reasoning ability for proportions, inequalities, and linear network relations."
                                ),
                                keyInsight = "When testing syllogisms, draw multiple Venn diagrams to see if any scenario invalidates the proposed conclusion. A statement is a valid deduction only if it is true in all cases."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Syllogism Intersection Rule",
                                    expression = "No A are B -> A ∩ B = ∅",
                                    description = "Defines absolute exclusion zones between set elements.",
                                    applicationTrick = "Draw non-overlapping rings to represent excluded classes completely."
                                )
                            ),
                            pyqs = listOf(
                                GateQuestion(
                                    id = "pyq_apt_analytical_venn",
                                    subjectId = subjectId,
                                    topicId = "apt_analytical_aptitude",
                                    subtopicId = "apt_analytical_reasoning",
                                    year = 2024,
                                    questionText = "In a group of 30 research scholars, 18 study Physics, 15 study Chemistry, and 12 study Computer Science. 8 scholars study both Physics and Chemistry, 6 study Chess/Chemistry and Computer Science, and 5 study Physics and Computer Science. If 3 scholars study all three subjects, find the number of research scholars who study exactly one subject.",
                                    questionType = QuestionType.NAT,
                                    correctOptions = null,
                                    correctNumericalRange = 16.0..16.0,
                                    explanation = "Let P, C, and S represent scholars studying Physics, Chemistry, and Computer Science respectively.\nTotal scholars = 30.\nn(P)=18, n(C)=15, n(S)=12.\nn(P∩C)=8, n(C∩S)=6, n(P∩S)=5.\nn(P∩C∩S)=3.\n\nNow calculate individual intersection regions:\n- Only all three = 3.\n- Only Physics and Chemistry = 8 - 3 = 5.\n- Only Chemistry and Computer Science = 6 - 3 = 3.\n- Only Physics and Computer Science = 5 - 3 = 2.\n\nNow calculate single subjects:\n- Only Physics = 18 - (5 + 2 + 3) = 8.\n- Only Chemistry = 15 - (5 + 3 + 3) = 4.\n- Only Computer Science = 12 - (2 + 3 + 3) = 4.\n\nTotal scholars who study exactly one subject = 8 + 4 + 4 = 16 scholars.",
                                    formulasUsed = "Three-set inclusion-exclusion Venn intersections",
                                    shortcutTricks = "Draw a 3-ring Venn diagram. Start with 3 in the center, subtract 3 from the double overlaps (5, 3, 2), and then subtract those from original sums.",
                                    relatedConcepts = "Logical reasoning, Venn diagrams, Set theory",
                                    difficulty = "Medium"
                                )
                            ),
                            practiceQuestions = listOf(
                                GateQuestion(
                                    id = "pract_apt_analytical_syll",
                                    subjectId = subjectId,
                                    topicId = "apt_analytical_aptitude",
                                    subtopicId = "apt_analytical_reasoning",
                                    year = 2023,
                                    questionText = "Analyze the following logical statement sequence:\n\n1. All smart grids are power networks.\n2. Some power networks are unstable.\n3. None of the unstable systems are critical.\n\nWhich of the following statements must be true?",
                                    questionType = QuestionType.MCQ,
                                    options = listOf(
                                        "Some smart grids are unstable.",
                                        "Some power networks are not critical.",
                                        "All smart grids are unstable systems.",
                                        "No power networks are critical systems."
                                    ),
                                    correctOptions = listOf(1),
                                    explanation = "Since some power networks are unstable and none of the unstable systems are critical, those specific unstable power networks cannot be critical. Thus, the statement 'Some power networks are not critical' is always logically true. Smart grids do not have a mandatory overlapping relationship with unstable networks based on the premises, so other choices are invalid.",
                                    formulasUsed = "Categorical Syllogisms rules",
                                    shortcutTricks = "Focus on the intersection: unstable power networks cannot intersect critical. Hence, those power networks are definitely not critical.",
                                    relatedConcepts = "Deductive reasoning, Syllogisms",
                                    difficulty = "Medium"
                                )
                            ),
                            mockQuiz = listOf(
                                GateQuestion(
                                    id = "mock_apt_analytical_seq",
                                    subjectId = subjectId,
                                    topicId = "apt_analytical_aptitude",
                                    subtopicId = "apt_analytical_reasoning",
                                    year = 2025,
                                    questionText = "Find the missing number in the following analytical progression sequence:\n\n3, 11, 31, 69, 131, _______",
                                    questionType = QuestionType.NAT,
                                    correctOptions = null,
                                    correctNumericalRange = 223.0..223.0,
                                    explanation = "Let's analyze the sequence:\nYear 1: 3 = 1³ + 1 + 1\nYear 2: 11 = 2³ + 2 + 1\nYear 3: 31 = 3³ + 3 + 1\nYear 4: 69 = 4³ + 4 + 1\nYear 5: 131 = 5³ + 5 + 1\n\nFollowing the pattern n³ + n + 1, the next term is:\nYear 6: 6³ + 6 + 1 = 216 + 6 + 1 = 223.\n\nAlternatively, calculating first differences (8, 20, 38, 62) and second differences (12, 18, 24) reveals a standard linear arithmetic progression with interval 6, confirming the next term is 131 + (62 + 30) = 223.",
                                    formulasUsed = "n³ + n + 1 series progression or second-difference tracking",
                                    shortcutTricks = "Compute the differences twice. The second differences increase by 6. Add 30 to 62 to get 92, and 131 + 92 = 223.",
                                    relatedConcepts = "Number relations, analytical groupings",
                                    difficulty = "Hard"
                                )
                            )
                        ),
                        createSimpleSubtopic("apt_anal_deduction_induction", "apt_analytical_aptitude", subjectId, "Deduction and Induction", "Analysing classical logical deductions, empirical inductions, and truth assertions.", "An inductive claim evaluates likelihood bases, whereas deductive claims demand absolute certainty of universal propositions."),
                        createSimpleSubtopic("apt_anal_number_series", "apt_analytical_aptitude", subjectId, "Number Series", "Finding sequential logic, arithmetic, cubes, geometric growth, and anomalous elements in number arrays.", "Track first-order and second-order differences to resolve polynomial progression boundaries quickly."),
                        createSimpleSubtopic("apt_anal_analogies", "apt_analytical_aptitude", subjectId, "Analogies", "Mapping relational groupings and logic symmetric sets from word elements.", "Define the active relational logic on the primary set before evaluating choices."),
                        createSimpleSubtopic("apt_anal_numerical_reasoning", "apt_analytical_aptitude", subjectId, "Numerical Reasoning", "Using simple algebra, fractions, ratios, and equation modeling to interpret logical constraints.", "Translate constraints directly into standard linear algebraic equations for rapid solution.")
                    )
                ),
                Topic(
                    id = "apt_spatial_aptitude",
                    subjectId = subjectId,
                    name = "Spatial Aptitude",
                    subtopics = listOf(
                        createSimpleSubtopic("apt_spatial_rotation", "apt_spatial_aptitude", subjectId, "Rotation", "Mental rotation of 2D shapes and 3D geometric views to verify spatial orientation matches.", "Pay close attention to key markers such as shaded sides, dots, or vertex tags while rotating elements."),
                        Subtopic(
                            id = "apt_spatial_mirroring",
                            topicId = "apt_spatial_aptitude",
                            subjectId = subjectId,
                            name = "Mirroring",
                            theory = TheoryContent(
                                title = "Spatial Translation, Mirroring & Cutting Styles",
                                synopsis = "Spatial aptitude evaluates the capacity to mentally manipulate, assemble, mirror, rotate, scale, translate, fold, cut, and group 2-D and 3-D symmetric patterns.",
                                detailedBullets = listOf(
                                    "Rotations & Reflections: Track asymmetric markers (like shading, dots, corners) clockwise under 2D/3D transformations.",
                                    "Paper Folding & Cutting: Unfold sheets along designated creases, reflecting punched holes symmetrically across boundary folds.",
                                    "Assembling & Grouping: Align separate geometric fragments into 2D configurations or solid 3D structures.",
                                    "Symmetry & Pattern Recognition: Identify line and rotational symmetries across complex geometric arrays."
                                ),
                                keyInsight = "A vertical fold crease acts strictly as a vertical mirror (left-right inversion), whereas a horizontal crease acts as a horizontal mirror (top-bottom reflection)."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "2D Rotation Matrix",
                                    expression = "x' = x*cos(θ) - y*sin(θ), y' = x*sin(θ) + y*cos(θ)",
                                    description = "Rotates points mathematically about the origin in the coordinate plane.",
                                    applicationTrick = "For 90° clockwise rotation, map coordinates from (x, y) to (y, -x) directly."
                                )
                            ),
                            pyqs = listOf(
                                GateQuestion(
                                    id = "pyq_apt_spatial_fold",
                                    subjectId = subjectId,
                                    topicId = "apt_spatial_aptitude",
                                    subtopicId = "apt_spatial_mirroring",
                                    year = 2024,
                                    questionText = "A transparent sheet of paper with a pattern printed on it is folded along the dotted line as shown below.\n\nPattern: A leftward-pointing triangle '◀' is printed on the left half, and a vertical line '|' is printed on the right half, offset slightly to the right of the center.\n\nWhen the sheet is folded from left to right, which of the following combined patterns will appear?",
                                    questionType = QuestionType.MCQ,
                                    options = listOf(
                                        "A rightward-pointing triangle intersecting the vertical line: '▶|'",
                                        "A rightward-pointing triangle with the vertical line passing behind it: '|▶'",
                                        "A leftward-pointing triangle overlapping the vertical line: '◀|'",
                                        "A leftward-pointing triangle and a separate horizontal line"
                                    ),
                                    correctOptions = listOf(0),
                                    explanation = "When folded horizontally along a vertical mirror axis, any pattern on the left-hand side is mirrored to the right-hand side. The leftward-pointing triangle '◀' reflects to look rightward '▶'. The vertical line on the right half remains stationary, so the mirrored triangle overlaps and intersects it, resulting in '▶|'.",
                                    formulasUsed = "Horizontal reflection transformation",
                                    shortcutTricks = "A left-to-right fold mirrors horizontally. Left directions point right, which eliminates choices containing '◀'.",
                                    relatedConcepts = "Mirroring, shape transformation, 2D patterns",
                                    difficulty = "Medium"
                                )
                            ),
                            practiceQuestions = listOf(
                                GateQuestion(
                                    id = "pract_apt_spatial_cube",
                                    subjectId = subjectId,
                                    topicId = "apt_spatial_aptitude",
                                    subtopicId = "apt_spatial_mirroring",
                                    year = 2023,
                                    questionText = "Consider a hollow 3D solid cube. Two of its adjacent faces are painted black, and the remaining four faces are painted white. If this cube is sliced into 8 equal-sized smaller cubes, how many of these smaller cubes will have at least one face painted black?",
                                    questionType = QuestionType.NAT,
                                    correctOptions = null,
                                    correctNumericalRange = 6.0..6.0,
                                    explanation = "A cube has 6 faces, and two adjacent faces are painted black.\nWhen sliced into 8 smaller cubes, each face of the original cube is divided among exactly 4 of the smaller cubes.\n\nUsing the Principle of Inclusion-Exclusion:\n- Small cubes touching Face 1 = 4.\n- Small cubes touching Face 2 = 4.\n- Since the faces are adjacent, they share a single edge containing exactly 2 of the smaller cubes. Therefore, 2 smaller cubes touch BOTH black faces.\n- Total small cubes with at least one black face = 4 + 4 - 2 = 6.",
                                    formulasUsed = "Inclusion-exclusion principle on geometric faces",
                                    shortcutTricks = "Out of the 8 smaller cubes, only the 2 cubes along the edge opposite the black faces have absolutely zero black paint. Thus: 8 - 2 = 6 cubes.",
                                    relatedConcepts = "3D assembly, solid geometry cutting, visualization",
                                    difficulty = "Hard"
                                )
                            ),
                            mockQuiz = listOf(
                                GateQuestion(
                                    id = "mock_apt_spatial_tile",
                                    subjectId = subjectId,
                                    topicId = "apt_spatial_aptitude",
                                    subtopicId = "apt_spatial_mirroring",
                                    year = 2025,
                                    questionText = "What is the minimum number of identical, non-overlapping equilateral triangles of side length 1 cm required to completely tile a regular hexagon of side length 3 cm?",
                                    questionType = QuestionType.NAT,
                                    correctOptions = null,
                                    correctNumericalRange = 54.0..54.0,
                                    explanation = "A regular hexagon of side length S = 3 cm consists of 6 large equilateral triangles of side length 3 cm each.\n\nThe area of one large triangle represents (scale factor)² = 3² = 9 times the area of a smaller 1 cm triangle.\n\nThus, each of the 6 regions requires 9 small triangles. Total triangles required = 6 * 9 = 54.",
                                    formulasUsed = "Area of regular hexagon, area scaling ratio (Area ∝ S²)",
                                    shortcutTricks = "Scaling side down by 1/3 increases required tile volume by reciprocal square (3² = 9). Six regular quadrants yield 6 * 9 = 54 tiles.",
                                    relatedConcepts = "2-D pattern tiling, scaling, grouping",
                                    difficulty = "Easy"
                                )
                            )
                        ),
                        createSimpleSubtopic("apt_spatial_paper_folding", "apt_spatial_aptitude", subjectId, "Paper Folding", "Visualizing folded sheet punch-hole outcomes and symmetric crease tracking.", "Unfold the paper crease-by-crease, reflecting existing punches as mirror points across the folded boundaries."),
                        createSimpleSubtopic("apt_spatial_pattern_recognition", "apt_spatial_aptitude", subjectId, "Pattern Recognition", "Identifying repeating sequence patterns, matrix completions, and visual symmetry indices.", "Verify row-wise and column-wise shifts in shape parameters (lines, shapes, shading) to identify the transformation rule."),
                        createSimpleSubtopic("apt_spatial_shape_transformation", "apt_spatial_aptitude", subjectId, "Shape Transformation", "Visual transformation, dilation, warping, and multi-step geometric operations.", "Isolate each step of the transformation and verify shape integrity at individual intervals.")
                    )
                )
            )
        )
    }

    private fun createSimpleSubtopic(
        id: String,
        topicId: String,
        subjectId: String,
        name: String,
        synopsis: String,
        keyInsight: String
    ): Subtopic {
        return Subtopic(
            id = id,
            topicId = topicId,
            subjectId = subjectId,
            name = name,
            theory = TheoryContent(
                title = name,
                synopsis = synopsis,
                detailedBullets = listOf(
                    "Core Principles: Understand the fundamental definitions and axiomatic structures of $name.",
                    "Key Methods: Master standard problem-solving strategies, direct mappings, and algebraic formulations.",
                    "Analytical Limits: Avoid popular trap cases by recognizing constraints and boundaries early."
                ),
                keyInsight = keyInsight
            ),
            formulaSheet = getFormulasForSubtopicId(id),
            pyqs = emptyList(),
            practiceQuestions = emptyList(),
            mockQuiz = emptyList()
        )
    }

    private fun getFormulasForSubtopicId(subtopicId: String): List<FormulaItem> {
        return when (subtopicId) {
            "apt_quant_ratios" -> listOf(
                FormulaItem(
                    name = "Direct Ratio Representation",
                    expression = "a : b = a / b",
                    description = "Relation of comparison between two quantities of the same class.",
                    applicationTrick = "Multiply both values by a common multiplier k (e.g. ak and bk) to set up simple algebraic equations."
                ),
                FormulaItem(
                    name = "Law of Proportion",
                    expression = "If a:b = c:d, then a * d = b * c",
                    description = "Equivalence of two ratios where a and d are extremes, b and c are means.",
                    applicationTrick = "Solve for an unknown element x directly using basic cross-multiplication."
                ),
                FormulaItem(
                    name = "Rule of Alligations (Mixtures)",
                    expression = "(Qty of Cheaper) / (Qty of Dearer) = (CP of Dearer - Mean Price) / (Mean Price - CP of Cheaper)",
                    description = "Allows finding the proportion in which two ingredients of different prices are mixed to produce a specific average price.",
                    applicationTrick = "Draw a crossed line matching CP of Dearer, CP of Cheaper, and Mean to avoid sign errors."
                )
            )
            "apt_quant_percentages" -> listOf(
                FormulaItem(
                    name = "Percentage Increase/Decrease",
                    expression = "% Change = ((Final - Initial) / Initial) * 100",
                    description = "Measures proportional change relative to the initial value base.",
                    applicationTrick = "Use multipliers: a 15% increase is multiplying by 1.15; a 15% decrease is multiplying by 0.85."
                ),
                FormulaItem(
                    name = "Successive Percentage Changes",
                    expression = "Net % Change = x + y + (x * y) / 100",
                    description = "Calculates total rate update after two sequential percentage trends.",
                    applicationTrick = "Substitute negative values for percent decreases. For equal increase & decrease x, net change is always -x^2/100 %."
                )
            )
            "apt_quant_profit_loss" -> listOf(
                FormulaItem(
                    name = "Profit and Loss Percent",
                    expression = "Profit % = (P / CP) * 100, Loss % = (L / CP) * 100",
                    description = "Calculates absolute financial outcomes normalized on the purchase base (CP).",
                    applicationTrick = "Never calculate profit/loss percent based on SP unless explicitly requested."
                ),
                FormulaItem(
                    name = "Marked Price & Discount Rule",
                    expression = "SP = MP * (1 - Discount/100)",
                    description = "Relates selling price to list marked price after subtracting a trade discount rate.",
                    applicationTrick = "MP * (1 - d%) = CP * (1 + g%) is an incredibly useful shortcut that links markup and gain ratios."
                )
            )
            "apt_quant_time_work" -> listOf(
                FormulaItem(
                    name = "Daily Work Rate Relation",
                    expression = "Rate = 1 / Days",
                    description = "Specifies that the quantity of work completed in 1 day is inversely proportional to time to complete.",
                    applicationTrick = "Add individual daily rates directly: Rate_total = Rate_A + Rate_B."
                ),
                FormulaItem(
                    name = "Chain Rule of Work Force",
                    expression = "(M1 * D1 * H1) / W1 = (M2 * D2 * H2) / W2",
                    description = "Relates manpower (M), duration days (D), hourly shifts (H), and absolute output volume (W).",
                    applicationTrick = "Keep work rates W1 and W2 in the denominator, all other timing parameters in the numerator."
                )
            )
            "apt_quant_permutation_combination" -> listOf(
                FormulaItem(
                    name = "Permutation Formula (Arrangements)",
                    expression = "nPr = n! / (n - r)!",
                    description = "Determines unique arrangements of r items chosen from a distinct set of n items.",
                    applicationTrick = "Use permutations when order is significant (e.g., forming secret code numbers)."
                ),
                FormulaItem(
                    name = "Combination Formula (Selections)",
                    expression = "nCr = n! / (r! * (n - r)!) = nPr / r!",
                    description = "Count of unique ways to select r elements from n distinct items regardless of arrangement sequence.",
                    applicationTrick = "Remember nCr is always smaller or equal to nPr. Note that nCr = nC(n-r)."
                ),
                FormulaItem(
                    name = "Circular Permutations",
                    expression = "Arrangements = (n - 1)!",
                    description = "Counts ways to arrange n items around an symmetrical circular loop.",
                    applicationTrick = "If a key/necklace loop is flip-symmetric, divide the result by 2 to yield (n - 1)! / 2."
                )
            )
            "apt_quant_probability" -> listOf(
                FormulaItem(
                    name = "Classical Probability Formula",
                    expression = "P(A) = n(A) / n(S)",
                    description = "Ratio of favorable outcomes n(A) to total sample space outcomes n(S).",
                    applicationTrick = "Calculate sample space first to set up the boundary of valid outcomes."
                ),
                FormulaItem(
                    name = "Conditional Probability Statement",
                    expression = "P(A | B) = P(A ∩ B) / P(B)",
                    description = "Returns probability of event A occurring given that event B has occurred.",
                    applicationTrick = "Think of it as restricting the total sample space strictly to outcomes belonging inside event B."
                )
            )
            "apt_quant_logarithms" -> listOf(
                FormulaItem(
                    name = "Logarithmic Expansion Properties",
                    expression = "log(xy) = log x + log y, log(x/y) = log x - log y",
                    description = "Translates multiplication and division into simple addition or subtraction under matching base criteria.",
                    applicationTrick = "Use log_b(x^n) = n * log_b(x) to bring exponents down for easy linear differentiation or division."
                ),
                FormulaItem(
                    name = "Change of Base Property",
                    expression = "log_b(a) = log_c(a) / log_c(b)",
                    description = "Transforms any arbitrary log base b into standard convenient bases (like natural e or standard base 10).",
                    applicationTrick = "Remember log_b(a) * log_a(b) = 1. Reciprocating a logarithm swaps the base and argument."
                )
            )
            "apt_quant_data_interpretation" -> listOf(
                FormulaItem(
                    name = "Compound Growth Rate",
                    expression = "CAGR = [(Value_End / Value_Start)^(1/n) - 1] * 100",
                    description = "Expresses geometric year-on-year growth rate over a discrete time span.",
                    applicationTrick = "Use logarithmic scaling or estimation if exact fractional powers are tedious to compute manually."
                ),
                FormulaItem(
                    name = "Degree-to-Percentage Scaling",
                    expression = "% value = (Degrees / 360) * 100",
                    description = "Converts angular sector spans in pie charts to relative decimal metric values.",
                    applicationTrick = "Utilize 10% = 36 degrees and 1% = 3.6 degrees as standard rapid mental converters."
                )
            )
            "apt_quant_geometry" -> listOf(
                FormulaItem(
                    name = "Internal Angles of Polygons",
                    expression = "Sum of Angles = (n - 2) * 180°",
                    description = "Computes accumulated degrees inside any regular or irregular closed flat n-sided polygon.",
                    applicationTrick = "Divide by n to compute a single angle of a perfectly regular polygon."
                ),
                FormulaItem(
                    name = "Heron's Triangle Area",
                    expression = "Area = √[s(s - a)(s - b)(s - c)] where s = (a + b + c)/2",
                    description = "Returns complete surface area of any triangle utilizing only its three bounding lengths.",
                    applicationTrick = "Check if the triangle is right-angled first (a^2+b^2=c^2) to see if simple 0.5*b*h applies instead."
                )
            )
            "apt_quant_mensuration" -> listOf(
                FormulaItem(
                    name = "Surface Area & Volume of a Sphere",
                    expression = "Volume = (4/3) * π * r^3, Area = 4 * π * r^2",
                    description = "Spherically symmetric volume bounds and curved surface boundaries.",
                    applicationTrick = "Differentiating Volume with respect to r gives Area (d/dr [4/3 * pi * r^3] = 4 * pi * r^2)."
                ),
                FormulaItem(
                    name = "Cone Parameters",
                    expression = "Volume = (1/3) * π * r^2 * h, Slant length (l) = √(r^2 + h^2)",
                    description = "Formulations of right circular cones.",
                    applicationTrick = "The curved surface area is exactly π*r*l, excluding the flat base area of π*r^2."
                )
            )
            "apt_verb_completion", "apt_verb_vocab", "apt_verb_reading", "apt_verb_analogies",
            "apt_verb_word_groups", "apt_verb_critical", "apt_verb_narrative_seq" -> listOf(
                FormulaItem(
                    name = "Structural Semantic Relation",
                    expression = "Clue + Transition Indicator -> Output Context",
                    description = "Identifies direction of argument flow (positive/agreement vs negative/contradiction).",
                    applicationTrick = "Look first for contrast markers like 'however', 'although', 'nonetheless' to reverse semantic polarity."
                ),
                FormulaItem(
                    name = "Grammatical Agreement Constraint",
                    expression = "Singular Subject -> Singular Verb / Plural Subject -> Plural Verb",
                    description = "Maintains concord alignment across clauses.",
                    applicationTrick = "Cross out secondary prepositional phrases (e.g. 'as well as the teachers') to isolate the singular main subject."
                )
            )
            "apt_anal_deduction_induction", "apt_anal_analogies" -> listOf(
                FormulaItem(
                    name = "Syllogistic Venn Intersection",
                    expression = "A ⊂ B, B ∩ C ≠ ∅ -> A ∩ C may or may not be non-empty",
                    description = "Translates logical premises to set theory inequalities.",
                    applicationTrick = "Draw maximum intersection and minimal overlap cases. A conclusion is valid only if true in BOTH."
                )
            )
            "apt_anal_number_series" -> listOf(
                FormulaItem(
                    name = "Arithmetic & Geometric Progressions",
                    expression = "AP Term: T_n = a + (n-1)d, GP Term: T_n = a * r^(n-1)",
                    description = "Predictive coordinates for standard sequences of terms.",
                    applicationTrick = "If first-order differences form an AP, the original series represents a quadratic sequence."
                ),
                FormulaItem(
                    name = "Second-Order Difference Resolve",
                    expression = "D_2 = Diff(Diff(T_n))",
                    description = "Evaluates progressive differences of first differences to identify polynomial progressions.",
                    applicationTrick = "Most difficult series questions in GATE reduce to constant values in the second or third subtraction layer."
                )
            )
            "apt_anal_numerical_reasoning" -> listOf(
                FormulaItem(
                    name = "Linear Equation Modeling",
                    expression = "a*x + b*y = c",
                    description = "Translates descriptive word constraints into solvable coordinate variables.",
                    applicationTrick = "Always count equations to match variables. N independent variables require N independent equations."
                )
            )
            "apt_spatial_rotation", "apt_spatial_paper_folding", "apt_spatial_pattern_recognition", "apt_spatial_shape_transformation" -> listOf(
                FormulaItem(
                    name = "Planar Reflection Matrix",
                    expression = "x' = -x (for Y-axis mirror), y' = -y (for X-axis mirror)",
                    description = "Reflects planar points or shapes symmetrically across central boundary axes.",
                    applicationTrick = "For paper folding, treat folds as mirrors and reflect existing holes directly across the fold line."
                ),
                FormulaItem(
                    name = "Spatial Symmetry Scaling",
                    expression = "Area ∝ (Scale Factor)^2, Volume ∝ (Scale Factor)^3",
                    description = "Calculates geometric area/volume changes when relative sizes scale.",
                    applicationTrick = "If length doubles (k=2), volume multiplies by 2^3 = 8, while surface area multiplies by 2^2 = 4."
                )
            )
            else -> listOf(
                FormulaItem(
                    name = "Fundamental Subject Concept Formulation",
                    expression = "V_output = f(Inputs, Parameters)",
                    description = "Relates theoretical concepts with corresponding mathematical dependencies.",
                    applicationTrick = "Check boundary constraints and source limits to confirm active equations."
                )
            )
        }
    }

    private fun createReasoning(): Subject {
        val subjectId = "reasoning"
        return Subject(
            id = subjectId,
            name = "Reasoning",
            iconName = "psychology",
            topics = listOf(
                Topic(
                    id = "re_logical",
                    subjectId = subjectId,
                    name = "Logical Reasoning",
                    subtopics = listOf(
                        Subtopic(
                            id = "re_log_analysis",
                            topicId = "re_logical",
                            subjectId = subjectId,
                            name = "Syllogisms & Analytical Series",
                            theory = TheoryContent(
                                title = "Deductions, Number Series, & Syllogism Analysis",
                                synopsis = "Addresses categorical logic assertions (Syllogisms), pattern recognition sequences, and deductive argument mappings.",
                                detailedBullets = listOf(
                                    "Venn Diagram Method: Standard tool to evaluate categorical syllogisms (e.g., All A are B, Some B are C).",
                                    "Premise Verification: A conclusion is only logically valid if it is true in every possible representing Venn diagram layout.",
                                    "Series progress: Find first-order differences, and then second-order differences. Most complex mathematical series resolve instantly under second-order subtraction."
                                ),
                                keyInsight = "Do not rely on real-world factual correctness of statements. Analyze validity strictly based on the relational rules stated in the given premises."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Intersection & Subset Formulation",
                                    expression = "All A are B <=> A ⊂ B, Some A are B <=> A ∩ B ≠ ∅",
                                    description = "Sets up subset, intersection, and disjoint definitions for categorical syllogism evaluations.",
                                    applicationTrick = "Draw both the minimal overlap diagram and maximum subset overlap diagrams to trace absolute logical truths."
                                ),
                                FormulaItem(
                                    name = "Number Series Progression Step",
                                    expression = "D_1(n) = T(n+1) - T(n), D_2(n) = D_1(n+1) - D_1(n)",
                                    description = "Evaluates difference mappings of sequence items to discover quadratic or geometric patterns.",
                                    applicationTrick = "If first differences are in AP, second differences are constant, highlighting a quadratic T(n) equation."
                                )
                            ),
                            pyqs = listOf(
                                GateQuestion(
                                    id = "pyq_re_syll_1",
                                    subjectId = subjectId,
                                    topicId = "re_logical",
                                    subtopicId = "re_log_analysis",
                                    year = 2022,
                                    questionText = "Consider the following premises:\n1. All electrical machines are quiet.\n2. Some quiet devices are expensive.\nWhich of the following conclusions logically follows from these premises?\n(Select the most appropriate option)",
                                    questionType = QuestionType.MCQ,
                                    options = listOf(
                                        "All electrical machines are expensive.",
                                        "Some electrical machines are expensive.",
                                        "No electrical machines are expensive.",
                                        "None of the above conclusions logically follow from the premises."
                                    ),
                                    correctOptions = listOf(3),
                                    explanation = "Let E = Set of all electrical machines, Q = Set of quiet devices, and X = Set of expensive devices.\nPremise 1: E is a subset of Q.\nPremise 2: Q intersects X (there is a non-empty overlap between Q and X).\nThis does not guarantee that E intersects X. It is possible for E and X to be completely disjoint while satisfying both premises. Thus, none of the specific conclusions (All E are X, Some E are X, or No E are X) logically follow as absolute certainties.",
                                    formulasUsed = "Set Intersections and Inclusion boundaries",
                                    shortcutTricks = "Draw a Venn diagram where E is entirely inside Q, and X overlaps with Q but has zero overlap with E. This valid representation instantly disproves the first three choices.",
                                    relatedConcepts = "Categorical Syllogisms, Venn Diagrams",
                                    difficulty = "Medium"
                                )
                            ),
                            practiceQuestions = emptyList(),
                            mockQuiz = emptyList()
                        )
                    )
                )
            )
        )
    }

    private fun createEngineeringMath(): Subject {
        val subjectId = "engineering_math"
        return Subject(
            id = subjectId,
            name = "Engineering Mathematics",
            iconName = "calculate",
            topics = listOf(
                Topic(
                    id = "math_la",
                    subjectId = subjectId,
                    name = "Linear Algebra",
                    subtopics = listOf(
                        Subtopic(
                            id = "math_la_eig",
                            topicId = "math_la",
                            subjectId = subjectId,
                            name = "Matrices, Determinants, Rank & Eigenvalues",
                            theory = TheoryContent(
                                title = "Eigenvalues, Rank & Systems Analysis",
                                synopsis = "Covers fundamentals of matrix algebra, linear independent sets, matrix transformations, characteristic equations, and eigenvalues properties.",
                                detailedBullets = listOf(
                                    "Determinant & Product of Eigenvalues: Det(A) is equal to the product of all eigenvalues of matrix A.",
                                    "Trace & Sum of Eigenvalues: The sum of the main diagonal elements of A equals the sum of its eigenvalues.",
                                    "Cayley-Hamilton Theorem: A square matrix satisfies its own characteristic equation, enabling efficient computation of matrix powers (A^n) and inverses (A^-1).",
                                    "Rank of a Matrix: The maximum number of linearly independent row or column vectors."
                                ),
                                keyInsight = "If a matrix is singular (Det(A) = 0), at least one of its eigenvalues is exactly zero, signaling a spatial collapse during transformation."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Cayley-Hamilton Identity",
                                    expression = "p(A) = 0",
                                    description = "A standard matrix equation substituting matrix A into its characteristic polynomial coefficients.",
                                    applicationTrick = "Compute A^-1 by dividing the polynomial equation by A, isolating the inverse term on one side easily."
                                )
                            ),
                            pyqs = listOf(
                                GateQuestion(
                                    id = "pyq_math_la_1",
                                    subjectId = subjectId,
                                    topicId = "math_la",
                                    subtopicId = "math_la_eig",
                                    year = 2024,
                                    questionText = "A 2x2 matrix A has eigenvalues 2 and 3. What is the determinant of the matrix A^2?",
                                    questionType = QuestionType.MCQ,
                                    options = listOf("5", "6", "25", "36"),
                                    correctOptions = listOf(3),
                                    explanation = "If A has eigenvalues 2 and 3, then matrix A^2 has eigenvalues 2^2 = 4 and 3^2 = 9.\nThe determinant of A^2 is equal to the product of its eigenvalues:\nDet(A^2) = 4 * 9 = 36.\nAlternatively, Det(A) = 2 * 3 = 6. Det(A^2) = (Det(A))^2 = 6^2 = 36.",
                                    formulasUsed = "Det(A^k) = (Det(A))^k, Eigenvalue mapping for polynomial functions",
                                    shortcutTricks = "Simply square the determinant of A: (2 * 3)^2 = 36 in one second.",
                                    relatedConcepts = "Determinants, Matrix functions, spectral properties",
                                    difficulty = "Easy"
                                )
                            ),
                            practiceQuestions = emptyList(),
                            mockQuiz = emptyList()
                        )
                    )
                ),
                Topic(
                    id = "math_calc",
                    subjectId = subjectId,
                    name = "Calculus & Vector Analysis",
                    subtopics = listOf(
                        Subtopic(
                            id = "math_calc_concepts",
                            topicId = "math_calc",
                            subjectId = subjectId,
                            name = "Limits, Extremas & Vector Theorems",
                            theory = TheoryContent(
                                title = "Limits, Optimization, & Vector Calculus Fields",
                                synopsis = "Covers differential limits (L'Hopital's), maxima-minima optimization, and vector fields integration theorems (Gradient, Divergence, Curl).",
                                detailedBullets = listOf(
                                    "L'Hopital's Rule: Evaluates indeterminate 0/0 or inf/inf limits by taking ratio of individual derivatives: f'(x)/g'(x).",
                                    "Maxima & Minima: Occurs where first derivative f'(c) = 0. If second derivative f''(c) < 0, it is a local maximum. If f''(c) > 0, it is a local minimum.",
                                    "Divergence Theorem: Relates flux out of a closed surface to volume integral: Integral_S F.dS = Integral_V (Div F) dV.",
                                    "Stokes Theorem: Relates line integral around a closed boundary loop to surface curl: Integral_C F.dr = Integral_S (Curl F).dS."
                                ),
                                keyInsight = "The curl of any gradient field is always zero (Curl(Grad V) = 0), representing conservative path-independent physical forces."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Divergence Coefficient",
                                    expression = "div F = dFx/dx + dFy/dy + dFz/dz",
                                    description = "Measures local outward spatial expansion density of field vector lines.",
                                    applicationTrick = "If divergence is exactly zero everywhere, the vector field is solenoidal (no internal sources or sinks)."
                                )
                            ),
                            pyqs = listOf(
                                GateQuestion(
                                    id = "pyq_math_vector_1",
                                    subjectId = subjectId,
                                    topicId = "math_calc",
                                    subtopicId = "math_calc_concepts",
                                    year = 2023,
                                    questionText = "What is the divergence of the vector field F = (x^2)*i + (y^2)*j + (z^2)*k evaluated at the spatial coordinate point (1, 2, 3)?",
                                    questionType = QuestionType.NAT,
                                    correctNumericalRange = 12.0..12.0,
                                    explanation = "F_x = x^2, F_y = y^2, F_z = z^2.\nDiv F = d(x^2)/dx + d(y^2)/dy + d(z^2)/dz = 2x + 2y + 2z.\nEvaluating at (1, 2, 3):\nDiv F = 2(1) + 2(2) + 2(3) = 2 + 4 + 6 = 12.",
                                    formulasUsed = "div F = dFx/dx + dFy/dy + dFz/dz",
                                    shortcutTricks = "Differentiate each term with respect to its variable: 2x, 2y, 2z. Simply compute 2 * (1 + 2 + 3) = 12.",
                                    relatedConcepts = "Vector divergence, cartesian coordinates, field evaluation",
                                    difficulty = "Easy"
                                )
                            ),
                            practiceQuestions = emptyList(),
                            mockQuiz = emptyList()
                        )
                    )
                ),
                Topic(
                    id = "math_de",
                    subjectId = subjectId,
                    name = "Differential Equations",
                    subtopics = listOf(
                        Subtopic(
                            id = "math_de_sol",
                            topicId = "math_de",
                            subjectId = subjectId,
                            name = "First & Higher Order ODEs",
                            theory = TheoryContent(
                                title = "Ordinary Differential Equations, I.F. & States",
                                synopsis = "Addresses linear and non-linear differential formulations used to outline physical transients, circuit currents, and boundary states.",
                                detailedBullets = listOf(
                                    "Integrating Factor (I.F.): Used to make first-order linear equations exact. For dy/dx + P*y = Q, the I.F. = exp(Integral P dx).",
                                    "Second Order Constant Coefficient ODEs: Solved by separating into Complementary Function (CF, transient part) and Particular Integral (PI, steady forced output)."
                                ),
                                keyInsight = "The CF represents the natural internal decay modes governed by the physical system characteristics, while the PI models the external energy forcing profile."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "First-Order I.F. Solver",
                                    expression = "y * I.F. = ∫ (Q * I.F.) dx + C",
                                    description = "Integrates first-order exact systems directly.",
                                    applicationTrick = "The left-hand side is always the product of the dependent variable and the integrating factor."
                                )
                            ),
                            pyqs = emptyList(),
                            practiceQuestions = emptyList(),
                            mockQuiz = emptyList()
                        )
                    )
                ),
                Topic(
                    id = "math_complex",
                    subjectId = subjectId,
                    name = "Complex Variables",
                    subtopics = listOf(
                        Subtopic(
                            id = "math_complex_residue",
                            topicId = "math_complex",
                            subjectId = subjectId,
                            name = "Analytic Functions & Cauchy Integration",
                            theory = TheoryContent(
                                title = "Cauchy-Riemann, Analyticity, & Residue Theorems",
                                synopsis = "Studies continuous complex functions, derivatives, path contours calculations, and residues at isolated simple poles.",
                                detailedBullets = listOf(
                                    "Cauchy-Riemann Equations: u_x = v_y and u_y = -v_x are necessary and sufficient for complex analyticity.",
                                    "Isolated Poles: Points where a function fails to remain analytic (denominators approach zero).",
                                    "Cauchy's Residue Theorem: Contour integral of f(z)dz = 2 * pi * i * (Sum of residues at poles lying strictly inside closed path boundary C)."
                                ),
                                keyInsight = "Poles lying strictly outside the closed boundary loop do not contribute to integration results."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Cauchy Residue Theorem Relation",
                                    expression = "∮_C f(z) dz = 2 * π * j * ∑ Residues",
                                    description = "Evaluates contour integrals via localized functional residues.",
                                    applicationTrick = "For simple poles at z = a, computes residue as: lim_{z -> a} [ (z - a) * f(z) ]."
                                )
                            ),
                            pyqs = listOf(
                                GateQuestion(
                                    id = "pyq_math_complex_1",
                                    subjectId = subjectId,
                                    topicId = "math_complex",
                                    subtopicId = "math_complex_residue",
                                    year = 2021,
                                    questionText = "Evaluate the contour integral I = ∮_C [ 1 / (z^2 - 1) ] dz, along the circular contour C defined as |z| = 1.5, mapped counter-clockwise.",
                                    questionType = QuestionType.MCQ,
                                    options = listOf("0", "π * j", "2 * π * j", "0.5 * π * j"),
                                    correctOptions = listOf(0),
                                    explanation = "Integrand f(z) = 1 / [(z-1)(z+1)] has simple poles at z = 1 and z = -1.\nBoth poles lie inside the circular boundary C of radius 1.5.\nResidue at z = 1: lim_{z->1} [ (z-1) / ((z-1)(z+1)) ] = 1/2 = 0.5.\nResidue at z = -1: lim_{z->-1} [ (z+1) / ((z-1)(z+1)) ] = -1/2 = -0.5.\nSum of Residues = 0.5 + (-0.5) = 0.\nI = 2 * pi * j * (Sum of residues) = 2 * pi * j * (0) = 0.",
                                    formulasUsed = "Res_f(a) = lim_{z->a} (z-a)f(z), Integral = 2*pi*j*Sum(Res)",
                                    shortcutTricks = "Notice that the integrand is an even symmetric function with singularities balanced symmetrically on both sides of the origin inside the symmetric boundary. Their residues cancel out, giving 0.",
                                    relatedConcepts = "Contours integration, isolated residues",
                                    difficulty = "Medium"
                                )
                            ),
                            practiceQuestions = emptyList(),
                            mockQuiz = emptyList()
                        )
                    )
                ),
                Topic(
                    id = "math_probability",
                    subjectId = subjectId,
                    name = "Probability & Statistics",
                    subtopics = listOf(
                        Subtopic(
                            id = "math_prob_bayes",
                            topicId = "math_probability",
                            subjectId = subjectId,
                            name = "Probability, Bayes & Distributions",
                            theory = TheoryContent(
                                title = "Conditional Probability & Bayes Identity",
                                synopsis = "Evaluates probability distributions, mean values, variance coefficients, and posterior probabilities based on updated evidence criteria.",
                                detailedBullets = listOf(
                                    "Bayes Theorem: Fits posterior calculations symmetrically P(A|B) = [P(B|A) * P(A)] / P(B).",
                                    "Mean & Expected Value: E[X] = Sum of (x * P(x)) for discrete variables.",
                                    "Normal Distribution: Bell-shaped density function dominated by central mean and standard deviation limits."
                                ),
                                keyInsight = "Bayes Theorem serves as a dynamic feedback loop to adjust prior probabilities as new test data or parameters emerge."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Bayes Probability Formula",
                                    expression = "P(A|B) = [ P(B|A) * P(A) ] / [ P(B|A)*P(A) + P(B|A')*P(A') ]",
                                    description = "Evaluates posterior probability given observed evidence parameter.",
                                    applicationTrick = "The denominator acts as the total probability of event B across all mutually exclusive scenarios."
                                )
                            ),
                            pyqs = emptyList(),
                            practiceQuestions = emptyList(),
                            mockQuiz = emptyList()
                        )
                    )
                ),
                Topic(
                    id = "math_numerical",
                    subjectId = subjectId,
                    name = "Numerical Methods",
                    subtopics = listOf(
                        Subtopic(
                            id = "math_num_integration",
                            topicId = "math_numerical",
                            subjectId = subjectId,
                            name = "Numerical Integration & Root Finding",
                            theory = TheoryContent(
                                title = "Root Convergence, Trapezoidal & Simpson's Integrals",
                                synopsis = "Deals with numerical approximations of roots, integration, and interpolations.",
                                detailedBullets = listOf(
                                    "Newton-Raphson Iteration: x_{n+1} = x_n - f(x_n)/f'(x_n). Provides quadratic local convergence.",
                                    "Trapezoidal Rule: Approximates integral via linear intervals: Integral = (h/2) * [ (y0 + yn) + 2*(y1 + y2 + ... + y_{n-1}) ].",
                                    "Simpson's 1/3 Rule: Fits quadratic intervals, requiring an even number of interval slices: Integral = (h/3) * [ (y0 + yn) + 4*(Odd y) + 2*(Even y) ]."
                                ),
                                keyInsight = "Trapezoidal rule is exact for linear polynomials (order 1), whereas Simpson's 1/3 rule is exact for polynomials of degree up to 3."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Newton-Raphson Step",
                                    expression = "x_new = x_old - f(x_old) / f'(x_old)",
                                    description = "Calculates successive root approximations using local derivative slopes.",
                                    applicationTrick = "If f'(x) is close to zero near the root, Newton-Raphson will experience slow convergence or divergence."
                                )
                            ),
                            pyqs = emptyList(),
                            practiceQuestions = emptyList(),
                            mockQuiz = emptyList()
                        )
                    )
                )
            )
        )
    }

    private fun createNetworkTheory(): Subject {
        val subjectId = "network_theory"
        return Subject(
            id = subjectId,
            name = "Network Theory",
            iconName = "share",
            topics = listOf(
                Topic(
                    id = "nt_laws",
                    subjectId = subjectId,
                    name = "Basic Elements & Circuit Laws",
                    subtopics = listOf(
                        Subtopic(
                            id = "nt_laws_basics",
                            topicId = "nt_laws",
                            subjectId = subjectId,
                            name = "KCL, KVL, Dependent & Independent Sources",
                            theory = TheoryContent(
                                title = "Kirchhoff's Laws & Source Formulations",
                                synopsis = "Examines fundamental circuit elements (R, L, C) and physical current-voltage equations governing loops and nodal boundaries.",
                                detailedBullets = listOf(
                                    "KCL (Nodal Law): The algebraic sum of currents entering any circuit node must equal zero. Relates directly to Conservation of Charge.",
                                    "KVL (Loop Law): The algebraic sum of potential differences around any closed circuit path or loop is zero. Relates directly to Conservation of Energy.",
                                    "Dependent Sources: Controlled voltage/current parameters scaling on remote elements variables."
                                ),
                                keyInsight = "An ideal voltage source has exactly zero series internal resistance, whereas an ideal current source has infinite parallel internal resistance."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Kirchhoff's Current Law (KCL)",
                                    expression = "Σ I_inbound = Σ I_outbound (or Σ I_node = 0)",
                                    description = "Algebraic sum of all currents meeting at a node is zero, deriving directly from conservation of charge.",
                                    applicationTrick = "Choose one node as ground reference (0V) and state nodal potential voltages to solve quickly."
                                ),
                                FormulaItem(
                                    name = "Kirchhoff's Voltage Law (KVL)",
                                    expression = "Σ V_around_loop = 0 (or Σ V_rise = Σ V_drop)",
                                    description = "Algebraic sum of all electrical potential differences across elements around any closed loop is zero.",
                                    applicationTrick = "Follow a consistent clockwise or counterclockwise loop direction, aligning signs with entry terminals (+ to - represents drop)."
                                ),
                                FormulaItem(
                                    name = "Ohm's Law & Dissipated Power",
                                    expression = "V = I * R, P = V * I = I^2 * R = V^2 / R",
                                    description = "Voltage drop matches current scaled by resistance. Governs simple linear active resistor lines.",
                                    applicationTrick = "Use V^2/R for parallel connections where voltage is shared, and I^2*R for series connections where current is shared."
                                )
                            ),
                            pyqs = emptyList(),
                            practiceQuestions = emptyList(),
                            mockQuiz = emptyList()
                        )
                    )
                ),
                Topic(
                    id = "nt_theorems",
                    subjectId = subjectId,
                    name = "Network Theorems",
                    subtopics = listOf(
                        Subtopic(
                            id = "nt_theorems_active",
                            topicId = "nt_theorems",
                            subjectId = subjectId,
                            name = "Thevenin, Norton & Maximum Power Trans",
                            theory = TheoryContent(
                                title = "Equivalizing Complex Active Linear Networks",
                                synopsis = "Thevenin and Norton theorems model active circuits using a single source and a series/parallel equivalent impedance representing terminal behaviors.",
                                detailedBullets = listOf(
                                    "Thevenin Voltage V_th: The open-circuit potential across target load terminals.",
                                    "Norton Current I_n: The short-circuit current across terminals.",
                                    "Equivalent Resistance R_th: Measured internally with all independent sources deactivated (voltages short-circuited, currents open-circuited).",
                                    "Maximum Power Transfer Theorem: Accomplished when load resistance matching R_L = R_th, yielding exactly 50% power transfer efficiency."
                                ),
                                keyInsight = "When dependent sources are present, R_th must be found by deactivating independent sources and applying an external test source (e.g., 1V or 1A) at the terminals: R_th = 1 / I_test."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Thevenin-Norton Equivalence",
                                    expression = "V_th = I_n * R_th",
                                    description = "Connects equivalent circuit models via standard source transformations.",
                                    applicationTrick = "Calculate short-circuit current and open-circuit voltage to find R_th = V_th / I_n instantly."
                                )
                            ),
                            pyqs = listOf(
                                GateQuestion(
                                    id = "pyq_nt_theorems_1",
                                    subjectId = subjectId,
                                    topicId = "nt_theorems",
                                    subtopicId = "nt_theorems_active",
                                    year = 2022,
                                    questionText = "A linear electrical network has an open-circuit voltage of 24 V at its terminals and a source series resistance of 6 Ohms. What is the current flowing in Amperes through a load resistance of 12 Ohms connected across these terminals?",
                                    questionType = QuestionType.MCQ,
                                    options = listOf("1.33 A", "1.50 A", "2.00 A", "4.00 A"),
                                    correctOptions = listOf(0),
                                    explanation = "According to Thevenin's theorem, we represent the network as a series circuit:\nI = V_th / (R_th + R_L).\nGiven V_th = 24 V, R_th = 6 Ohms, and R_L = 12 Ohms:\nI = 24 / (6 + 12) = 24 / 18 = 4 / 3 = 1.33 A.",
                                    formulasUsed = "I = V_th / (R_th + R_L)",
                                    shortcutTricks = "Total circuit resistance is R_th + R_L = 18 Ohms. Compute 24V / 18 Ohms = 1.33A directly.",
                                    relatedConcepts = "Thevenin equivalent circuits, loading calculations",
                                    difficulty = "Easy"
                                )
                            ),
                            practiceQuestions = emptyList(),
                            mockQuiz = emptyList()
                        )
                    )
                ),
                Topic(
                    id = "nt_ac",
                    subjectId = subjectId,
                    name = "AC Circuits & Resonance",
                    subtopics = listOf(
                        Subtopic(
                            id = "nt_ac_resonance",
                            topicId = "nt_ac",
                            subjectId = subjectId,
                            name = "Resonance, Phasors & Complex Power",
                            theory = TheoryContent(
                                title = "AC Alternating Current Steady-State and Tuning Filters",
                                synopsis = "Addresses phasor domains, reactive power, power factor corrections, and frequency alignments at resonance.",
                                detailedBullets = listOf(
                                    "Resonance Frequency: The frequency where capacitive and inductive reactances fully cancel out, causing the circuit to behave as a pure resistor.",
                                    "Series Resonance: Minimum impedance (Z = R) and maximum current. Voltage amplification occurs across inductive/capacitive elements.",
                                    "Parallel Resonance: Maximum impedance (Z = R) and minimum line current. Rebehaves as a bandstop selector.",
                                    "Power Factor: Cos(theta) is active real power divided by apparent power."
                                ),
                                keyInsight = "At resonance, the network input power factor is exactly unity (power factor = 1.0), meaning voltage and current waveforms are perfectly in phase."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Resonating Matching Formula",
                                    expression = "f_0 = 1 / (2 * π * sqrt(L * C))",
                                    description = "Calculates the exact undamped natural resonance frequency.",
                                    applicationTrick = "For series circuits with given series L and C, evaluate 1/sqrt(LC) and divide by 2*pi."
                                )
                            ),
                            pyqs = listOf(
                                GateQuestion(
                                    id = "pyq_nt_ac_1",
                                    subjectId = subjectId,
                                    topicId = "nt_ac",
                                    subtopicId = "nt_ac_resonance",
                                    year = 2023,
                                    questionText = "For an RLC series circuit with R = 10 Ohms, L = 10 mH, and C = 10 μF, find the resonant frequency in Hertz.",
                                    questionType = QuestionType.NAT,
                                    correctNumericalRange = 503.2..503.3,
                                    explanation = "Using the resonant frequency formula:\nf_0 = 1 / [ 2 * pi * sqrt(L * C) ]\nL * C = 10 * 10^-3 * 10 * 10^-6 = 10^-7.\nsqrt(L * C) = sqrt(10^-7) = 3.162277 * 10^-4.\nf_0 = 1 / [ 2 * pi * (3.162277 * 10^-4) ] = 1 / (1.9869 * 10^-3) = 503.29 Hz.",
                                    formulasUsed = "f_0 = 1 / (2 * pi * sqrt(L * C))",
                                    shortcutTricks = "Recognize 1 / sqrt(LC) = 3162.27 rad/sec. Divide this by 2*pi directly to find f_0 = 503.29 Hz.",
                                    relatedConcepts = "Series resonance, undamped filter tuning",
                                    difficulty = "Medium"
                                )
                            ),
                            practiceQuestions = emptyList(),
                            mockQuiz = emptyList()
                        )
                    )
                ),
                Topic(
                    id = "nt_transients",
                    subjectId = subjectId,
                    name = "Transient Analysis",
                    subtopics = listOf(
                        Subtopic(
                            id = "nt_trans_response",
                            topicId = "nt_transients",
                            subjectId = subjectId,
                            name = "RL, RC & RLC Transient Response",
                            theory = TheoryContent(
                                title = "State Transitions & Circuit Energy Dynamics",
                                synopsis = "Evaluates circuit currents and voltage transitions when switching operations alter network configurations.",
                                detailedBullets = listOf(
                                    "Inductor current continuity: i_L(0+) = i_L(0-). Direct rate changes require infinite voltages.",
                                    "Capacitor voltage continuity: v_C(0+) = v_C(0-). Direct rate changes require infinite currents.",
                                    "Switching action at t=0: Uncharged capacitors act as short circuits, and unenergized inductors act as open circuits."
                                ),
                                keyInsight = "A first-order transient decays to 36.8% (1/e) of its initial displacement step at exactly t = one time constant (one tau)."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "First Order Response Formula",
                                    expression = "x(t) = x(∞) + [ x(0+) - x(∞) ] * e^(-t/τ)",
                                    description = "Evaluates transient parameters (capacitor voltage or inductor current) at any time t.",
                                    applicationTrick = "To find the time constant (tau), compute the equivalent resistance seen by the energy storage element: RL series tau = L/R; RC series tau = R*C."
                                )
                            ),
                            pyqs = emptyList(),
                            practiceQuestions = emptyList(),
                            mockQuiz = emptyList()
                        )
                    )
                ),
                Topic(
                    id = "nt_twoport",
                    subjectId = subjectId,
                    name = "Two-Port Networks & 3-Phase",
                    subtopics = listOf(
                        Subtopic(
                            id = "nt_parameters_3phase",
                            topicId = "nt_twoport",
                            subjectId = subjectId,
                            name = "ABCD, Z, Y Elements & Star-Delta",
                            theory = TheoryContent(
                                title = "Two-Port Parameter Networks & 3-Phase Systems",
                                synopsis = "Details Z (impedance), Y (admittance), ABCD (transmission) matrix parameters alongside three-phase balanced active real/reactive calculations.",
                                detailedBullets = listOf(
                                    "Z-parameters: Express voltages as linear combinations of currents (open circuit conditions).",
                                    "Y-parameters: Express currents as linear combinations of voltages (short circuit conditions).",
                                    "ABCD-parameters: Express input parameters (V1, I1) in terms of output parameters (V2, -I2), used extensively in transmission lines modeling.",
                                    "Three-Phase Systems: In a balanced star configuration, V_line = sqrt(3) * V_phase. In a delta configuration, I_line = sqrt(3) * I_phase."
                                ),
                                keyInsight = "For symmetric networks: Z11 = Z22, AD - BC = 1. For reciprocal networks: Z12 = Z21, Y12 = Y21, AD - BC = 1."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Balanced Three-Phase Power",
                                    expression = "P = sqrt(3) * V_line * I_line * cos(θ)",
                                    description = "Calculates total active real power delivered to a balanced 3-phase load.",
                                    applicationTrick = "theta represents the phase impedance angle, which is the angle between phase voltage and phase current, NOT line values."
                                )
                            ),
                            pyqs = emptyList(),
                            practiceQuestions = emptyList(),
                            mockQuiz = emptyList()
                        )
                    )
                )
            )
        )
    }

    private fun createSignalsAndSystems(): Subject {
        val subjectId = "signals_systems"
        return Subject(
            id = subjectId,
            name = "Signals and Systems",
            iconName = "analytics",
            topics = listOf(
                Topic(
                    id = "sig_lti",
                    subjectId = subjectId,
                    name = "Signal Properties & LTI Systems",
                    subtopics = listOf(
                        Subtopic(
                            id = "sig_lti_convolution",
                            topicId = "sig_lti",
                            subjectId = subjectId,
                            name = "Continuous & Discrete Time LTI, Convolution",
                            theory = TheoryContent(
                                title = "Classifying Signals and Computing LTI Convolution",
                                synopsis = "Addresses energy vs power properties, causality boundaries, system stability, and convolution integration.",
                                detailedBullets = listOf(
                                    "Impulse Response: An LTI system is fully modeled by its response h(t) to an impulse input delta(t).",
                                    "Causality Condition: h(t) = 0 for t < 0. The output at any time depends only on present and past inputs.",
                                    "Stability (BIBO): A system is stable if and only if its impulse response is absolutely integrable: Integral of |h(t)| dt is finite."
                                ),
                                keyInsight = "Convolution in the time domain is mathematically equivalent to multiplication in the frequency domain (Laplace or Fourier) - a key concept for circuit analysis."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Continuous Convolution Integral",
                                    expression = "y(t) = ∫_{-∞}^{∞} x(τ) * h(t - τ) dτ",
                                    description = "Integrates input signals against impulse parameters with time shifts.",
                                    applicationTrick = "Convoluting a signal x(t) with a time-shifted impulse delta(t - t0) simply shifts x(t) to x(t - t0) directly."
                                )
                            ),
                            pyqs = emptyList(),
                            practiceQuestions = emptyList(),
                            mockQuiz = emptyList()
                        )
                    )
                ),
                Topic(
                    id = "sig_transforms",
                    subjectId = subjectId,
                    name = "Transforms & Sampling",
                    subtopics = listOf(
                        Subtopic(
                            id = "sig_sampling_rate",
                            topicId = "sig_transforms",
                            subjectId = subjectId,
                            name = "Fourier Series, Laplace, Z-Transforms & Sampling",
                            theory = TheoryContent(
                                title = "Frequency-Domain Mappings & Sampling Criteria",
                                synopsis = "Studies continuous Fourier Series, continuous/discrete transforms, convergence boundaries (ROC), and Nyquist rate criteria.",
                                detailedBullets = listOf(
                                    "Laplace Transform: Extends Fourier domains to complex frequency s = sigma + j*omega. Pole placements govern dynamic decay rates.",
                                    "Z-Transform: The discrete-time equivalent of Laplace transform. Poles must lie inside the unit circle (|z| < 1) for causal system stability.",
                                    "Sampling Theorem: To prevent aliasing, a continuous signal must be sampled at a rate greater than twice its highest frequency component."
                                ),
                                keyInsight = "Nyquist Rate represented as f_s = 2 * f_max. Sampling below this limits the ability to reconstruct the original continuous waveform."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Nyquist Sampling Interval",
                                    expression = "T_s <= 1 / (2 * f_max)",
                                    description = "Details the maximum elapsed time allowed between consecutive samples.",
                                    applicationTrick = "Identify the highest individual frequency component inside composite formulations (e.g. sum of cosine waves) to determine the absolute f_max."
                                )
                            ),
                            pyqs = listOf(
                                GateQuestion(
                                    id = "pyq_sig_sampling_1",
                                    subjectId = subjectId,
                                    topicId = "sig_transforms",
                                    subtopicId = "sig_sampling_rate",
                                    year = 2022,
                                    questionText = "What is the Nyquist rate in Hertz for the continuous signal x(t) = cos(2000 * π * t) + sin(4000 * π * t)?",
                                    questionType = QuestionType.MCQ,
                                    options = listOf("2000 Hz", "4000 Hz", "6000 Hz", "8000 Hz"),
                                    correctOptions = listOf(1),
                                    explanation = "Let the composite components represent f1 and f2:\nComponent 1: cos(2000 * pi * t) => omega1 = 2000 * pi => 2 * pi * f1 = 2000 * pi => f1 = 1000 Hz.\nComponent 2: sin(4000 * pi * t) => omega2 = 4000 * pi => 2 * pi * f2 = 4000 * pi => f2 = 2000 Hz.\nThe maximum frequency component present in the signal is f_max = Max(f1, f2) = 2000 Hz.\nUsing the Nyquist Rate formula:\nNyquist rate f_s = 2 * f_max = 2 * 2000 = 4000 Hz.",
                                    formulasUsed = "omega = 2*pi*f, f_s = 2 * f_max",
                                    shortcutTricks = "Identify the highest angular frequency: 4000*pi rad/sec. Divide this directly by pi to get the Nyquist rate in Hz: 4000*pi / pi = 4000 Hz.",
                                    relatedConcepts = "Sampling Theorem, Nyquist rate calculations",
                                    difficulty = "Easy"
                                )
                            ),
                            practiceQuestions = emptyList(),
                            mockQuiz = emptyList()
                        )
                    )
                )
            )
        )
    }

    private fun createControlSystems(): Subject {
        val subjectId = "control_systems"
        val allCsQuestions = ControlSystemsQuestions.questions

        fun getQuestions(subId: String, part: Int): List<GateQuestion> {
            val sq = allCsQuestions.filter { it.subtopicId == subId }
            if (sq.isEmpty()) return emptyList()
            val chunk = (sq.size + 2) / 3
            return when (part) {
                0 -> sq.take(chunk)
                1 -> sq.drop(chunk).take(chunk)
                else -> sq.drop(chunk * 2)
            }
        }

        return Subject(
            id = subjectId,
            name = "Control Systems",
            iconName = "settings",
            topics = listOf(
                Topic(
                    id = "cs_math_modeling",
                    subjectId = subjectId,
                    name = "Mathematical Modeling",
                    subtopics = listOf(
                        Subtopic(
                            id = "cs_mm_tf_model",
                            topicId = "cs_math_modeling",
                            subjectId = subjectId,
                            name = "Mathematical Modeling & Transfer Function",
                            theory = TheoryContent(
                                title = "LTI Physical System Modeling & Laplace Transforms",
                                synopsis = "Explaining differential formulations of electrical and mechanical systems and analyzing transfer function bounds under zero initial states.",
                                detailedBullets = listOf(
                                    "Transfer Function: Evaluates output Laplace transform over input Laplace transform with zero physical starting conditions.",
                                    "Force-Voltage Analogy: Translates Force to Voltage, Mass to Inductance (L), Damper to Resistance (R), and Spring stiffness to elastance (1/C).",
                                    "Force-Current Analogy: Translates Force to Current, Mass to Capacitance (C), Damper to Conductance (G), and Spring stiffness to reluctance (1/L)."
                                ),
                                keyInsight = "A linear time-invariant (LTI) system transfer function depends uniquely on structural parameters, and remains independent of input type/amount."
                            ),
                            formulaSheet = listOf(
                                FormulaItem("Transfer Function Matrix", "T(s) = Y(s) / X(s)", "Rational representation of complex frequency response.", "Set all state derivatives and integrals to zero before computing ratio.")
                            ),
                            pyqs = getQuestions("cs_mm_tf_model", 0),
                            practiceQuestions = getQuestions("cs_mm_tf_model", 1),
                            mockQuiz = getQuestions("cs_mm_tf_model", 2)
                        ),
                        Subtopic(
                            id = "cs_mm_block",
                            topicId = "cs_math_modeling",
                            subjectId = subjectId,
                            name = "Block Diagram Reduction",
                            theory = TheoryContent(
                                title = "Block Diagrams & Simplification Algebra",
                                synopsis = "Addresses modern structural simplifications of complex system block diagrams through moving summing and take-off nodes.",
                                detailedBullets = listOf(
                                    "Cascading: Two blocks G1 and G2 in series simply multiply: G_eq = G1 * G2.",
                                    "Parallel: Outputs of two parallel blocks are added: G_eq = G1 + G2.",
                                    "Unity Feedback: Closed loop transfer function for negative feedback: T(s) = G(s) / (1 + G(s)H(s)). With positive feedback: T(s) = G(s) / (1 - G(s)H(s))."
                                ),
                                keyInsight = "Swapping adjacent summing junctions of identical signs leaves physical signal parameters completely unmodified."
                            ),
                            formulaSheet = listOf(
                                FormulaItem("Negative Feedback Loop", "T(s) = G(s) / (1 + G(s)H(s))", "Standard closed loop transfer function under negative feedback.", "Sign in denominator is opposite of feedback path multiplier.")
                            ),
                            pyqs = getQuestions("cs_mm_block", 0),
                            practiceQuestions = getQuestions("cs_mm_block", 1),
                            mockQuiz = getQuestions("cs_mm_block", 2)
                        ),
                        Subtopic(
                            id = "cs_mm_sfg",
                            topicId = "cs_math_modeling",
                            subjectId = subjectId,
                            name = "Signal Flow Graphs",
                            theory = TheoryContent(
                                title = "Mason's Gain Formula on Directed Graphs",
                                synopsis = "Details signal flow graph structural analysis, identifying nodes, forward paths, and individual or touching loops.",
                                detailedBullets = listOf(
                                    "Source Node: A node containing strictly outgoing transmission branches.",
                                    "Sink Node: A node containing strictly incoming transmission branches.",
                                    "Non-touching Loops: Feedback loops that share no physical node coordinates."
                                ),
                                keyInsight = "Mason's Gain formula is universally equivalent to block diagram reduction, but highly systematic for complex loop topologies."
                            ),
                            formulaSheet = listOf(
                                FormulaItem("Mason's Gain Formula", "T = Σ (P_k * Δ_k) / Δ", "Computes global graph transmission gain mathematically.", "The determinant Δ incorporates all single and combinations of non-touching loops.")
                            ),
                            pyqs = getQuestions("cs_mm_sfg", 0),
                            practiceQuestions = getQuestions("cs_mm_sfg", 1),
                            mockQuiz = getQuestions("cs_mm_sfg", 2)
                        )
                    )
                ),
                Topic(
                    id = "cs_time_response",
                    subjectId = subjectId,
                    name = "Time Response Analysis",
                    subtopics = listOf(
                        Subtopic(
                            id = "cs_tr_analysis",
                            topicId = "cs_time_response",
                            subjectId = subjectId,
                            name = "Time Response Analysis",
                            theory = TheoryContent(
                                title = "Transient Response of First & Second Order Systems",
                                synopsis = "Analyzes state dynamics as a function of time following step, ramp, or impulse input configurations.",
                                detailedBullets = listOf(
                                    "First-Order Response: Dominates by time constant τ, reaching 63.2% in 1τ and 98% in 4τ.",
                                    "Damping Ratio (ζ): ζ=0 undamped; 0<ζ<1 underdamped; ζ=1 critically damped; ζ>1 overdamped.",
                                    "Damped Frequency: ω_d = ω_n * sqrt(1 - ζ^2), characterizing oscillation periodicity."
                                ),
                                keyInsight = "A critically damped system guarantees the fastest step-response settling time without generating transient overshoot."
                            ),
                            formulaSheet = listOf(
                                FormulaItem("Peak Overshoot Fraction", "M_p = e^{-π * ζ / sqrt(1 - ζ^2)}", "Computes maximum proportional transient overshoot.", "Overshoot is independent of natural frequency and relates only to ζ.")
                            ),
                            pyqs = getQuestions("cs_tr_analysis", 0),
                            practiceQuestions = getQuestions("cs_tr_analysis", 1),
                            mockQuiz = getQuestions("cs_tr_analysis", 2)
                        ),
                        Subtopic(
                            id = "cs_tr_steady_error",
                            topicId = "cs_time_response",
                            subjectId = subjectId,
                            name = "Steady State Error",
                            theory = TheoryContent(
                                title = "Steady State Accuracy & Error Constants",
                                synopsis = "Addresses the tracking offset of feedback systems after the initial transient oscillations decay completely.",
                                detailedBullets = listOf(
                                    "System Type: Defined by number of open-loop poles residing exactly at s=0.",
                                    "Type 0 System: Yields finite step error: 1/(1+Kp), infinite ramp error.",
                                    "Type 1 System: Yields zero step error, finite ramp error: 1/Kv, infinite parabolic error."
                                ),
                                keyInsight = "Increasing the system type improves steady-state accuracy but typically reduces system stability margins."
                            ),
                            formulaSheet = listOf(
                                FormulaItem("Steady State Error (General)", "e_ss = lim_{s->0} [ s * R(s) / (1 + G(s)H(s)) ]", "Computes tracking offset for any reference input R(s).", "Must evaluate poles of G(s)H(s) at s=0 first to determine System Type.")
                            ),
                            pyqs = getQuestions("cs_tr_steady_error", 0),
                            practiceQuestions = getQuestions("cs_tr_steady_error", 1),
                            mockQuiz = getQuestions("cs_tr_steady_error", 2)
                        )
                    )
                ),
                Topic(
                    id = "cs_stability",
                    subjectId = subjectId,
                    name = "Stability",
                    subtopics = listOf(
                        Subtopic(
                            id = "cs_stab_routh",
                            topicId = "cs_stability",
                            subjectId = subjectId,
                            name = "Stability & Routh-Hurwitz",
                            theory = TheoryContent(
                                title = "Routh-Hurwitz Stability Criterion",
                                synopsis = "Formulates algebraic sign checklists on characteristic equations without computing exact pole roots.",
                                detailedBullets = listOf(
                                    "Necessary Condition: All characteristic equation coefficients must be strictly positive and non-zero.",
                                    "Routh Array Row of Zeros: Indicates symmetric roots (e.g., pairs on jw-axis), solved via Auxiliary Polynomial derivative.",
                                    "Right Half Plane Roots: The number of sign changes in the first column of the Routh array equals right-half plane poles (unstable)."
                                ),
                                keyInsight = "A single sign change in the first column of the Routh array is sufficient to prove system instability."
                            ),
                            formulaSheet = listOf(
                                FormulaItem("Routh Array Auxiliary Polynomial", "A(s) = a0*s^{2k} + a1*s^{2k-2} + ...", "Forms symmetric polynomial for a zero-row case.", "Differentiate dA/ds directly to replace the row of zeros and proceed.")
                            ),
                            pyqs = getQuestions("cs_stab_routh", 0),
                            practiceQuestions = getQuestions("cs_stab_routh", 1),
                            mockQuiz = getQuestions("cs_stab_routh", 2)
                        ),
                        Subtopic(
                            id = "cs_stab_locus",
                            topicId = "cs_stability",
                            subjectId = subjectId,
                            name = "Root Locus",
                            theory = TheoryContent(
                                title = "Root Locus Construction & Design Analysis",
                                synopsis = "Tracks of closed-loop pole locations on the s-plane as feedback loop gain K varies from 0 to infinity.",
                                detailedBullets = listOf(
                                    "Number of Branches: Equal to the number of open-loop poles (P). Branches terminate at zeros or infinity.",
                                    "Asymptote Centroid: Computed as (Sum of Pole Real Parts - Sum of Zero Real Parts) / (P - Z).",
                                    "Breakpoint Criterion: Solved via dK/ds = 0, indicating coordinate split or merger points on the real axis."
                                ),
                                keyInsight = "Adding an open-loop pole pulls root locus branches to the right, whereas adding a zero pulls them to the left."
                            ),
                            formulaSheet = listOf(
                                FormulaItem("Angle of Asymptotes", "θ_q = (2q + 1) * 180° / (P - Z)", "Angle of departure lines heading to infinity.", "Iterate q from 0 up to (P - Z - 1).")
                            ),
                            pyqs = getQuestions("cs_stab_locus", 0),
                            practiceQuestions = getQuestions("cs_stab_locus", 1),
                            mockQuiz = getQuestions("cs_stab_locus", 2)
                        )
                    )
                ),
                Topic(
                    id = "cs_freq_response",
                    subjectId = subjectId,
                    name = "Frequency Response",
                    subtopics = listOf(
                        Subtopic(
                            id = "cs_fr_analysis",
                            topicId = "cs_freq_response",
                            subjectId = subjectId,
                            name = "Frequency Response Analysis",
                            theory = TheoryContent(
                                title = "Sinusoidal Steady State & Frequency Performance Metrics",
                                synopsis = "Investigates sinusoidal tracking responses under a range of input frequencies, detailing resonant variables.",
                                detailedBullets = listOf(
                                    "Resonant Frequency (ω_r): Frequency at which peak magnitude occurs under sinusoidal inputs.",
                                    "Resonant Peak (M_r): Maximum magnification of tracking value: lower damping ratio creates higher peaks.",
                                    "Bandwidth: Frequency range where output magnitude drops by -3 dB (1/sqrt(2)) from its DC value."
                                ),
                                keyInsight = "Bandwidth is inversely proportional to rise time; a fast system requires wide frequency transmission ranges."
                            ),
                            formulaSheet = listOf(
                                FormulaItem("Resonant Peak", "M_r = 1 / (2 * ζ * sqrt(1 - ζ^2))", "Computes maximum magnitude peak under underdamped states.", "Applicable only for ζ < 0.707. No peak exists for ζ >= 0.707.")
                            ),
                            pyqs = getQuestions("cs_fr_analysis", 0),
                            practiceQuestions = getQuestions("cs_fr_analysis", 1),
                            mockQuiz = getQuestions("cs_fr_analysis", 2)
                        ),
                        Subtopic(
                            id = "cs_fr_bode",
                            topicId = "cs_freq_response",
                            subjectId = subjectId,
                            name = "Bode Plots",
                            theory = TheoryContent(
                                title = "Logarithmic Magnitude & Phase Bode Plots",
                                synopsis = "Presents frequency responses using logarithmic plots of magnitude (dB) and phase angles versus frequency.",
                                detailedBullets = listOf(
                                    "Poles & Zeros: Every first-order pole contributes -20 dB/dec slope above corner frequency; every zero contributes +20 dB/dec.",
                                    "Gain Margin (GM): Phase margin check at Phase Crossover Frequency (where phase is -180°).",
                                    "Phase Margin (PM): Gain margin check at Gain Crossover Frequency (where magnitude is 0 dB / factor 1)."
                                ),
                                keyInsight = "Logarithmic scaling enables rapid graphical cascade multiplication via simple algebraic addition of slopes."
                            ),
                            formulaSheet = listOf(
                                FormulaItem("Gain Margin (dB)", "GM_{dB} = -20 * log10(|G(jω_{pc})H(jω_{pc})|)", "Computes dB margin of gain safety factor.", "Positive GM indicates stable loop margins, whereas negative GM indicates loop instability.")
                            ),
                            pyqs = getQuestions("cs_fr_bode", 0),
                            practiceQuestions = getQuestions("cs_fr_bode", 1),
                            mockQuiz = getQuestions("cs_fr_bode", 2)
                        ),
                        Subtopic(
                            id = "cs_fr_nyquist",
                            topicId = "cs_freq_response",
                            subjectId = subjectId,
                            name = "Polar & Nyquist Plots",
                            theory = TheoryContent(
                                title = "Polar Representation & Nyquist Encirclement Theorem",
                                synopsis = "Addresses complex magnitude and phase polar response loci, checking stability borders.",
                                detailedBullets = listOf(
                                    "Polar Plot: Maps magnitude and phase in a polar coordinate format as frequency ω varies from 0 to infinity.",
                                    "Nyquist Contour: Encloses the entire right half of the s-plane, mapping it to standard frequency curves.",
                                    "Critical Point (-1, j0): Closed-loop stability relates directly to encirclements of this unit vector."
                                ),
                                keyInsight = "If the open loop is stable, the closed loop is stable if the polar plot does not encircle (-1, j0)."
                            ),
                            formulaSheet = listOf(
                                FormulaItem("Nyquist Stability Formula", "Z = P - N", "Relates closed-loop unstable poles (Z) to open-loop unstable poles (P).", "N is counter-clockwise encirclements of the point (-1, j0) on complex coordinate canvas.")
                            ),
                            pyqs = getQuestions("cs_fr_nyquist", 0),
                            practiceQuestions = getQuestions("cs_fr_nyquist", 1),
                            mockQuiz = getQuestions("cs_fr_nyquist", 2)
                        )
                    )
                ),
                Topic(
                    id = "cs_controllers_comp",
                    subjectId = subjectId,
                    name = "Controllers & Compensators",
                    subtopics = listOf(
                        Subtopic(
                            id = "cs_ctrl_compensators",
                            topicId = "cs_controllers_comp",
                            subjectId = subjectId,
                            name = "Compensators & Controllers",
                            theory = TheoryContent(
                                title = "Compensator Classes & PID Regulators",
                                synopsis = "Details lead, lag, and lead-lag compensation configurations, and PID industrial regulators.",
                                detailedBullets = listOf(
                                    "Lead Compensator: Zero is closer to origin than pole. Serves as high-pass filter, raising speed and PM.",
                                    "Lag Compensator: Pole is closer to origin than zero. Serves as low-pass filter, attenuating high-frequency gains.",
                                    "PID Controller: Combines proportional tracking speed, integral steady-state accuracy, and derivative dampings."
                                ),
                                keyInsight = "A Derivative controller (D) improves stability margins but amplifies high-frequency channel noises."
                            ),
                            formulaSheet = listOf(
                                FormulaItem("Geometric Mean Shift", "ω_m = sqrt(ω_z * ω_p)", "Computes peak Phase Lead compensator frequency.", "Place compensator pole and zero symmetrically about targeted operating point.")
                            ),
                            pyqs = getQuestions("cs_ctrl_compensators", 0),
                            practiceQuestions = getQuestions("cs_ctrl_compensators", 1),
                            mockQuiz = getQuestions("cs_ctrl_compensators", 2)
                        )
                    )
                ),
                Topic(
                    id = "cs_state_space",
                    subjectId = subjectId,
                    name = "State Space Analysis",
                    subtopics = listOf(
                        Subtopic(
                            id = "cs_ss_analysis",
                            topicId = "cs_state_space",
                            subjectId = subjectId,
                            name = "State Space Analysis",
                            theory = TheoryContent(
                                title = "State Variable Multi-variable Time Domain Analysis",
                                synopsis = "Introduces modern controls, modeling internal system coordinates, checking controllability, and resolving STM equations.",
                                detailedBullets = listOf(
                                    "State Matrices: System Matrix A (n x n), Input Matrix B, Output Matrix C, Feedthrough Matrix D.",
                                    "State Transition Matrix (STM): maps initial state values x(0) to dynamic time states x(t).",
                                    "Eigenvalues: Solved via det(sI - A) = 0, determining basic natural modes of the system."
                                ),
                                keyInsight = "Similarity transforms under non-singular matrices leave system characteristic eigenvalues completely invariant."
                            ),
                            formulaSheet = listOf(
                                FormulaItem("Controllability Matrix", "Q_c = [ B | AB | A^2B | ... | A^{n-1}B ]", "Kalman controllability test matrix.", "The system is fully controllable if the determinant of Q_c is non-zero (full rank n).")
                            ),
                            pyqs = getQuestions("cs_ss_analysis", 0),
                            practiceQuestions = getQuestions("cs_ss_analysis", 1),
                            mockQuiz = getQuestions("cs_ss_analysis", 2)
                        )
                    )
                )
            )
        )
    }

    private fun createElectricalMachines(): Subject {
        val subjectId = "electrical_machines"
        val allEmQuestions = ElectricalMachinesQuestions.questions

        fun getQuestions(subId: String, part: Int): List<GateQuestion> {
            val sq = allEmQuestions.filter { it.subtopicId == subId }
            if (sq.isEmpty()) return emptyList()
            val chunk = (sq.size + 2) / 3
            return when (part) {
                0 -> sq.take(chunk)
                1 -> sq.drop(chunk).take(chunk)
                else -> sq.drop(chunk * 2)
            }
        }

        return Subject(
            id = subjectId,
            name = "Electrical Machines",
            iconName = "build",
            topics = listOf(
                Topic(
                    id = "em_transformers",
                    subjectId = subjectId,
                    name = "Transformers",
                    subtopics = listOf(
                        Subtopic(
                            id = "em_trans_single_phase",
                            topicId = "em_transformers",
                            subjectId = subjectId,
                            name = "Single-phase Transformers",
                            theory = TheoryContent(
                                title = "Single-phase Transformer Modeling & Analysis",
                                synopsis = "Addresses equivalent models, core hysteretic/eddy damping, open/short-circuit parameters, regulation, and efficiency.",
                                detailedBullets = listOf(
                                    "Equivalent Circuit: Referred winding resistances and leakage reactances modeled through turn squared ratios: R2' = R2 / a^2.",
                                    "Open Circuit Test: Performed on low-voltage side at rated voltage to determine core loss (hysteresis + eddy current) and magnetizing parameters.",
                                    "Short Circuit Test: Performed on high-voltage side with reduced voltage to check rated copper loss and series winding reactances.",
                                    "Maximum Efficiency: Occurs when variable copper loss equals constant core iron loss: P_cu = P_core."
                                ),
                                keyInsight = "At maximum efficiency, the load fraction x at which it occurs is: x = sqrt(P_core / P_cu_fl)."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Loading Fraction for Max Efficiency",
                                    expression = "x = sqrt(P_core / P_cu_fl)",
                                    description = "Evaluates optimum load fraction yielding maximum conversion efficiency.",
                                    applicationTrick = "If iron loss is 150W and FL copper loss is 600W, maximum efficiency occurs at exactly x_fl = sqrt(150/600) = sqrt(0.25) = 0.5 (or 50% load)."
                                ),
                                FormulaItem(
                                    name = "Voltage Regulation Approximation",
                                    expression = "VR = I_2 * (R_eq2 * cos(θ) ± X_eq2 * sin(θ)) / V_2",
                                    description = "Calculates secondary terminal voltage drop under lagging (+) or leading (-) power factor loads.",
                                    applicationTrick = "Voltage regulation can be zero or negative only under leading power factor conditions."
                                )
                            ),
                            pyqs = getQuestions("em_trans_single_phase", 0),
                            practiceQuestions = getQuestions("em_trans_single_phase", 1),
                            mockQuiz = getQuestions("em_trans_single_phase", 2)
                        ),
                        Subtopic(
                            id = "em_trans_three_phase",
                            topicId = "em_transformers",
                            subjectId = subjectId,
                            name = "Three-phase Transformers & Autotransformers",
                            theory = TheoryContent(
                                title = "3-Phase Transformers, Parallel Runs & Autotransformers",
                                synopsis = "Explores vector group phase displacements, parallel matching constraints, and autotransformer power transfer components.",
                                detailedBullets = listOf(
                                    "Vector Groups: Phase displacements (e.g., Dy11 represents 30° leading) determine safe parallel grid connections.",
                                    "Parallel Conditions: Units must have identical voltage ratios, same polarities, same phase sequence, and identical phase-displacements.",
                                    "Autotransformer Power: Transfers power via both induction and direct electrical conduction, greatly reducing winding mass.",
                                    "Conduction Power: P_cond = k * P_total, where k is the auto-turns ratio (V_LV / V_HV)."
                                ),
                                keyInsight = "Autotransformers are extremely efficient and lightweight when the high-voltage and low-voltage ratings are very close to each other (i.e., k is close to 1.0)."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Power Transferred Conductively",
                                    expression = "P_conductive = k * P_input = (V_LV / V_HV) * P_input",
                                    description = "Calculates the component of power transferred directly via physical terminal connections.",
                                    applicationTrick = "Subtract inductive power (1-k)*P_input from total output to find the conductive power component directly."
                                ),
                                FormulaItem(
                                    name = "Autotransformer Rating Boost",
                                    expression = "Rating_auto = [1 / (1 - k)] * Rating_2winding",
                                    description = "Gives the increased power handling capability when re-connecting as an autotransformer.",
                                    applicationTrick = "For a 5 kVA 400/200V transformer reconnected as a 400/600V autotransformer, k = 400/600 = 2/3, so new rating is 5 / (1 - 2/3) = 15 kVA."
                                )
                            ),
                            pyqs = getQuestions("em_trans_three_phase", 0),
                            practiceQuestions = getQuestions("em_trans_three_phase", 1),
                            mockQuiz = getQuestions("em_trans_three_phase", 2)
                        )
                    )
                ),
                Topic(
                    id = "em_dc",
                    subjectId = subjectId,
                    name = "DC Machines",
                    subtopics = listOf(
                        Subtopic(
                            id = "em_dc_generators_motors",
                            topicId = "em_dc",
                            subjectId = subjectId,
                            name = "Operating Characteristics & Starting",
                            theory = TheoryContent(
                                title = "DC Motors & Generators: Char & Starters",
                                synopsis = "Covers operating speed-torque characteristics, back EMF voltage loops, and starting protectors (3-point & 4-point starters).",
                                detailedBullets = listOf(
                                    "Back EMF Equation: E_b = V - I_a * R_a = (P * Phi * Z * N) / (60 * A). Speed N is proportional to E_b / Phi.",
                                    "Shunt Characteristics: Shunt motor exhibits extremely stable speed-torque curves, suitable for constant-speed loads.",
                                    "Series Hazard: Must never be started under zero load; low flux drives speed dangerously towards infinity.",
                                    "Multi-point Starters: Limit excess starting armature currents by inserting high series starting resistance blocks."
                                ),
                                keyInsight = "A DC Series motor develops startup torque proportional to the square of starting current, making it ideal for high-inertia traction duty."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Back EMF Voltage",
                                    expression = "E_b = V - I_a * R_a",
                                    description = "Evaluates developed internal back EMF under motor conditions.",
                                    applicationTrick = "Solve for armature current I_a using line input and field branches current values."
                                ),
                                FormulaItem(
                                    name = "Electromagnetic Torque developed",
                                    expression = "T_e = (P * Z * Phi * I_a) / (2 * π * A) = K_a * Phi * I_a",
                                    description = "Computes internal mechanical torque developed in the armature structure.",
                                    applicationTrick = "Torque is directly proportional to armature current under constant shunt excitation flux."
                                )
                            ),
                            pyqs = getQuestions("em_dc_generators_motors", 0),
                            practiceQuestions = getQuestions("em_dc_generators_motors", 1),
                            mockQuiz = getQuestions("em_dc_generators_motors", 2)
                        ),
                        Subtopic(
                            id = "em_dc_reaction_control",
                            topicId = "em_dc",
                            subjectId = subjectId,
                            name = "Armature Reaction, Commutation & Speed Control",
                            theory = TheoryContent(
                                title = "Armature Reaction, Commutation & Speed Control",
                                synopsis = "Examines cross-magnetizing distortions, mechanical brush commutation transients, and armature vs. field speed regulation.",
                                detailedBullets = listOf(
                                    "Armature Reaction: Armature MMF distorsts main pole flux, shifting Magnetic Neutral Axis (MNA) in the direction of rotation.",
                                    "Interpoles & Compensating Windings: Neutralize armature MMF cross-fields and reverse reactive EMF to eliminate commutator sparking.",
                                    "Armature Speed Control: Adjusting terminal voltage regulates speed smoothly below rated base value.",
                                    "Field Speed Control: Decreasing field flux (field weakening) regulates motor speed above rated base value."
                                ),
                                keyInsight = "Compensating windings are placed in main pole shoe slots and must be connected in series with the armature to cancel reaction MMF."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Armature Voltage Speed Control",
                                    expression = "N = (V_t - I_a * R_a) / (K_a * Phi)",
                                    description = "Determines motor operating speed based on terminal voltage, armature drops, and field flux.",
                                    applicationTrick = "To reduce speed below base speed, increase external series armature resistance, raising the resistive drop."
                                )
                            ),
                            pyqs = getQuestions("em_dc_reaction_control", 0),
                            practiceQuestions = getQuestions("em_dc_reaction_control", 1),
                            mockQuiz = getQuestions("em_dc_reaction_control", 2)
                        )
                    )
                ),
                Topic(
                    id = "em_induction",
                    subjectId = subjectId,
                    name = "Induction Machines",
                    subtopics = listOf(
                        Subtopic(
                            id = "em_induction_three_phase",
                            topicId = "em_induction",
                            subjectId = subjectId,
                            name = "Three-phase Induction Motors",
                            theory = TheoryContent(
                                title = "Induction Motors, Slip & Torque Characteristics",
                                synopsis = "Analyzes rotating magnetic fields, synchronous speed equations, slip torque induction parameters, power splits, and tests.",
                                detailedBullets = listOf(
                                    "Synchronous Speed: N_s = 120 * f / P.",
                                    "Slip Equation: s = (N_s - N_r) / N_s. Active motoring slip typically ranges from 2% to 6%.",
                                    "Blocked-Rotor Test: Conducted at reduced voltage to determine winding leakage reactances and resistances.",
                                    "Rotor Copper Loss: P_rcu = s * P_g, where P_g represents air gap power."
                                ),
                                keyInsight = "When three-phase stator windings are excited with balanced AC currents, they generate a constant amplitude magnetic field rotating at synchronous speed."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Slip Power Balance Split",
                                    expression = "P_g : P_rcu : P_mech = 1 : s : (1 - s)",
                                    description = "Air gap power distribution across rotor winding losses and mechanical shaft outputs.",
                                    applicationTrick = "Rotor copper loss is always fraction s of air gap input: P_rcu = s * P_g."
                                ),
                                FormulaItem(
                                    name = "Slip for Maximum Torque",
                                    expression = "s_mT = R_2 / X_2",
                                    description = "Yields slip at which the motor develops its peak breakdown torque.",
                                    applicationTrick = "Inserting external rotor resistance shifts peak torque to higher slips, leaving its peak Nm amplitude unchanged."
                                )
                            ),
                            pyqs = getQuestions("em_induction_three_phase", 0),
                            practiceQuestions = getQuestions("em_induction_three_phase", 1),
                            mockQuiz = getQuestions("em_induction_three_phase", 2)
                        ),
                        Subtopic(
                            id = "em_induction_single_phase",
                            topicId = "em_induction",
                            subjectId = subjectId,
                            name = "Single-phase Induction Motors",
                            theory = TheoryContent(
                                title = "Single-phase Induction Motors & Starting Mechanisms",
                                synopsis = "Analyzes double-revolving field theory, starting winding splits, capacitor run/start configurations, and shaded-pole motors.",
                                detailedBullets = listOf(
                                    "Pulsating Field: Stator single-phase excitation creates a pulsating standstill field with zero starting torque.",
                                    "Double Revolving Theory: Pulsating field is modeled as forward and backward rotating fields of equal magnitude, with backward-slip: s_b = 2 - s.",
                                    "Capacitor Start: Inserts auxiliary winding with series capacitor to split phase angles, generating starting torque.",
                                    "Shaded Pole: Shaded copper rings delay flux build-up, shifting magnetic field sweeps towards the shaded sector."
                                ),
                                keyInsight = "A centrifugal switch automatically disconnects the auxiliary starter loop when motor speeds approach 75% of synchronous speed."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Backward Field Slip",
                                    expression = "s_b = 2 - s",
                                    description = "Determines the slip experienced by the rotor conductors relative to the backward-rotating magnetic field.",
                                    applicationTrick = "If forward slip is 4% (0.04), the backward field slip is exactly 2 - 0.04 = 1.96 index units."
                                )
                            ),
                            pyqs = getQuestions("em_induction_single_phase", 0),
                            practiceQuestions = getQuestions("em_induction_single_phase", 1),
                            mockQuiz = getQuestions("em_induction_single_phase", 2)
                        )
                    )
                ),
                Topic(
                    id = "em_synchronous",
                    subjectId = subjectId,
                    name = "Synchronous Machines",
                    subtopics = listOf(
                        Subtopic(
                            id = "em_synchronous_generators",
                            topicId = "em_synchronous",
                            subjectId = subjectId,
                            name = "Synchronous Generators (Alternators)",
                            theory = TheoryContent(
                                title = "Synchronous Alternator Models & Regulation Theory",
                                synopsis = "Addresses cylindrical & salient pole models, regulation tests (EMF, MMF, Potier), parallel runs, and power-angle limits.",
                                detailedBullets = listOf(
                                    "Armature Reaction: Lagging load power factors demagnetize pole flux; leading factors magnetize pole flux.",
                                    "Regulation Methods: EMF (Synchronous Impedance) is pessimistic; MMF (Ampere-Turns) is optimistic.",
                                    "Short Circuit Ratio (SCR): Reciprocal of per-unit synchronous reactance: SCR = 1 / X_d_pu.",
                                    "Salience Reluctance: Direct and Quadrature axis reactances (Xd > Xq) yield an additional reluctance power term."
                                ),
                                keyInsight = "Adjusting the prime mover input controls active power output, whereas adjusting field excitation control alters reactive power grids."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Blondel's Two-Reaction Alternator Power",
                                    expression = "P = (E*V/Xd)*sin(d) + (V^2/2)*(1/Xq - 1/Xd)*sin(2d)",
                                    description = "Power output of salient-pole generators including excitation and reluctance terms.",
                                    applicationTrick = "For cylindrical non-salient rotor machines, Xd = Xq, collapsing the reluctance term to zero."
                                )
                            ),
                            pyqs = getQuestions("em_synchronous_generators", 0),
                            practiceQuestions = getQuestions("em_synchronous_generators", 1),
                            mockQuiz = getQuestions("em_synchronous_generators", 2)
                        ),
                        Subtopic(
                            id = "em_synchronous_motors",
                            topicId = "em_synchronous",
                            subjectId = subjectId,
                            name = "Synchronous Motors & Excitation Control",
                            theory = TheoryContent(
                                title = "Synchronous Motors, V-Curves & Transients",
                                synopsis = "Covers armature V-curves, synchronous condenser operations, starting techniques, and hunting transients.",
                                detailedBullets = listOf(
                                    "V-Curves: Armature current versus exciters field current. Over-excited motor runs at leading power factor.",
                                    "Synchronous Condenser: Over-excited motor running on no-load acts as dynamic capacitor to improve power factor.",
                                    "Hunting Phenomenon: Sudden load spikes cause rotor speeds to oscillate, damped via slot damper windings.",
                                    "Starting: Not self-starting; damper windings allow starting as temporary induction motors."
                                ),
                                keyInsight = "A synchronous motor runs strictly at synchronous speed: N_s = 120 * f / P under all stable loaded steady-states."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Power Factor excitation tracking",
                                    expression = "I_a = f(I_f) at Constant Mechanical Load",
                                    description = "Armature current tracks a V-shape as field current varies.",
                                    applicationTrick = "Armature current is minimum at unity power factor excitation state."
                                )
                            ),
                            pyqs = getQuestions("em_synchronous_motors", 0),
                            practiceQuestions = getQuestions("em_synchronous_motors", 1),
                            mockQuiz = getQuestions("em_synchronous_motors", 2)
                        )
                    )
                )
            )
        )
    }

    private fun createPowerSystems(): Subject {
        val subjectId = "power_systems"
        return Subject(
            id = subjectId,
            name = "Power Systems",
            iconName = "bolt",
            topics = listOf(
                Topic(
                    id = "ps_generation_econ",
                    subjectId = subjectId,
                    name = "Power Generation & Economics",
                    subtopics = listOf(
                        Subtopic(
                            id = "ps_econ_dispatch",
                            topicId = "ps_generation_econ",
                            subjectId = subjectId,
                            name = "Generation types, Dispatch & Tariffs",
                            theory = TheoryContent(
                                title = "Generation Dynamics & Economic Dispatch",
                                synopsis = "Covers power generation methods (thermal, hydro, nuclear, renewable) and economic optimization of generator outputs.",
                                detailedBullets = listOf(
                                    "Generation types: Thermal (coal baseline), Hydro (fast response), Nuclear (steady base load), Renewables (variable).",
                                    "Incremental Fuel Cost: dC_i / dP_i represents the derivative cost rate of adding another megawatt at plant i.",
                                    "Economic dispatch coordinating rule: Best load distribution occurs when incremental costs equal a common parameter, neglecting line losses: dC1/dP1 = dC2/dP2 = lambda."
                                ),
                                keyInsight = "To achieve minimum total operating cost, the incremental fuel costs of all active units must be mathematically identical."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Incremental Cost Equality",
                                    expression = "dC_i / dP_i = λ (constant)",
                                    description = "Optimization rule that schedules matching units to minimize total cost.",
                                    applicationTrick = "Solve the incremental linear equations simultaneously under constraint Sum(P_i) = P_demand."
                                )
                            ),
                            pyqs = emptyList(),
                            practiceQuestions = emptyList(),
                            mockQuiz = emptyList()
                        )
                    )
                ),
                Topic(
                    id = "ps_transmission",
                    subjectId = subjectId,
                    name = "Transmission & Distribution",
                    subtopics = listOf(
                        Subtopic(
                            id = "ps_trans_lines",
                            topicId = "ps_transmission",
                            subjectId = subjectId,
                            name = "Line parameters, Inductance, Capacitance & Corona",
                            theory = TheoryContent(
                                title = "Overhead Transmission Line Modeling",
                                synopsis = "Covers HV line parameters calculated via self GMR (Geometric Mean Radius) and mutual GMD (Geometric Mean Distance) formulations.",
                                detailedBullets = listOf(
                                    "Line Inductance: Relates to self-GMR (r' = 0.7788 r) which accounts for internal flux fields.",
                                    "Line Capacitance: Determined by outer radius (r) boundaries as charge resides strictly on the outer conductor surface.",
                                    "Corona: Local dielectric breakdown of air surrounding the conductor when local electric field intensity exceeds ~30 kV/cm."
                                ),
                                keyInsight = "Line transposition balances magnetic and electric couplings, ensuring equal phase inductances and capacitances."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Winding Loop Inductance",
                                    expression = "L = 2 * 10^(-7) * ln(GMD / GMR) H/m",
                                    description = "Evaluates line inductance based on GMD and GMR spaces.",
                                    applicationTrick = "GMR use r' = 0.7788 r, while GMD uses symmetric physical span calculations."
                                )
                            ),
                            pyqs = listOf(
                                GateQuestion(
                                    id = "pyq_ps_lines_1",
                                    subjectId = subjectId,
                                    topicId = "ps_transmission",
                                    subtopicId = "ps_trans_lines",
                                    year = 2022,
                                    questionText = "The internal self Geometric Mean Radius (GMR) of a solid cylinder conductor of radius r used in overhead inductance calculations is:",
                                    questionType = QuestionType.MCQ,
                                    options = listOf("0.5 r", "0.707 r", "0.7788 r", "1.0 r"),
                                    correctOptions = listOf(2),
                                    explanation = "To account for internal flux linkage, the actual radius is mathematically scaled by e^(-1/4). self GMR = r' = r * e^(-0.25) ≈ 0.7788 r.",
                                    formulasUsed = "GMR = r * e^(-1/4)",
                                    shortcutTricks = "Standard adjustment coefficient for solid cylindrical conductors is exactly 0.7788.",
                                    relatedConcepts = "GMR and GMD modeling",
                                    difficulty = "Easy"
                                )
                            ),
                            practiceQuestions = emptyList(),
                            mockQuiz = emptyList()
                        )
                    )
                ),
                Topic(
                    id = "ps_flow_stability",
                    subjectId = subjectId,
                    name = "Power Flow & Faults Analysis",
                    subtopics = listOf(
                        Subtopic(
                            id = "ps_flow_faults",
                            topicId = "ps_flow_stability",
                            subjectId = subjectId,
                            name = "Load Flow, Symmetrical & Unsymmetrical Faults",
                            theory = TheoryContent(
                                title = "Newton-Raphson Load Flow & Power System Faults",
                                synopsis = "Covers numerical power flows (Admittance grid Ybus), symmetrical short circuits, and unsymmetrical faults parsed via positive, negative, and zero sequence components.",
                                detailedBullets = listOf(
                                    "Load Flow: Schedules system voltages and angles using numerical iterative methods (Gauss-Seidel, Newton-Raphson).",
                                    "Symmetrical (3-phase) Faults: Balanced, requiring only positive sequence calculations.",
                                    "Single Line-to-Ground (LG) Fault: Most common unbalanced fault; positive, negative, and zero sequence networks are connected in series.",
                                    "Line-to-Line (LL) Fault: Connects positive and negative sequence networks in parallel (zero sequence current is zero)."
                                ),
                                keyInsight = "For Line-to-Ground (LG) faults, the total fault current is three times the zero-sequence current: I_f = 3 * I_a0."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Fault current in solid LG fault",
                                    expression = "I_f = 3 * E_a / (Z1 + Z2 + Z0)",
                                    description = "Evaluates line-to-ground fault current using sequence impedances.",
                                    applicationTrick = "If the system neutral is ungrounded, Z0 becomes infinite, reducing the LG fault current to zero."
                                )
                            ),
                            pyqs = emptyList(),
                            practiceQuestions = emptyList(),
                            mockQuiz = emptyList()
                        )
                    )
                ),
                Topic(
                    id = "ps_faults",
                    subjectId = subjectId,
                    name = "Stability & Protection",
                    subtopics = listOf(
                        Subtopic(
                            id = "ps_prot_stability",
                            topicId = "ps_faults",
                            subjectId = subjectId,
                            name = "Swing Equation, Relays & Circuit Breakers",
                            theory = TheoryContent(
                                title = "Generator Dynamics & Power Protection System",
                                synopsis = "Addresses generator swing dynamics, transient stability margins, and protective relay/circuit breaker operations.",
                                detailedBullets = listOf(
                                    "Swing Equation: M * (d^2 delta / dt^2) = P_m - P_e governs rotor acceleration dynamics.",
                                    "Equal Area Criterion (EAC): A graphical tool to evaluate modern transient stability without integrating the non-linear swing equation.",
                                    "Relays: Detect abnormal conditions and signal circuit breakers to open."
                                ),
                                keyInsight = "A circuit breaker's interrupting capacity is typically rated in terms of Symmetrical Short-Circuit Megavolt-Amperes (MVA)."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Swing Equation Mechanics",
                                    expression = "M * d^2δ/dt^2 = P_m - P_e",
                                    description = "Rotor acceleration equation relating input mechanical power (P_m) and output electrical power (P_e).",
                                    applicationTrick = "M is the angular momentum, which can be expressed in terms of the inertia constant H."
                                )
                            ),
                            pyqs = emptyList(),
                            practiceQuestions = emptyList(),
                            mockQuiz = emptyList()
                        )
                    )
                )
            )
        )
    }

    private fun createPowerElectronics(): Subject {
        val subjectId = "power_electronics"
        return Subject(
            id = subjectId,
            name = "Power Electronics",
            iconName = "flash_on",
            topics = listOf(
                Topic(
                    id = "pe_devices",
                    subjectId = subjectId,
                    name = "Power Semiconductor Devices",
                    subtopics = listOf(
                        Subtopic(
                            id = "pe_device_char",
                            topicId = "pe_devices",
                            subjectId = subjectId,
                            name = "SCR, MOSFET, IGBT & TRIAC Characteristics",
                            theory = TheoryContent(
                                title = "Thyristors, Transistors & Gate Control Switches",
                                synopsis = "Analysis of static/dynamic characteristics of major switching devices, conduction limits, turn-on/turn-off mechanisms.",
                                detailedBullets = listOf(
                                    "Silicon Controlled Rectifier (SCR): Semi-controlled 4-layer latching device requiring a gate pulse to turn on. It can turn off only via natural or forced commutation (current must drop below holding current).",
                                    "Power MOSFET: Voltage-controlled, majority-carrier device. High-speed switching capabilities but high on-state resistance at high voltages.",
                                    "IGBT: Combines a voltage-controlled high-impedance gate (MOS) with low on-state conduction drops (BJT)."
                                ),
                                keyInsight = "While a Thyristor is a latching device, a MOSFET is fully controlled, meaning conduction ceases as soon as the gate drive potential drops to zero."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "SCR Latching & Holding Condition",
                                    expression = "I_anode > I_latching (To turn ON), I_anode < I_holding (To turn OFF)",
                                    description = "Latching current is the minimum anode current to sustain the ON-state after removing the gate drive pulse. Holding current is the limit below which SCR drops back to forward blocking state.",
                                    applicationTrick = "Latching current (I_L) is always larger than holding current (I_H); typically, I_L ≈ 2 to 3 times I_H."
                                ),
                                FormulaItem(
                                    name = "Power Loss Formulation",
                                    expression = "P_conduction = V_on * I_avg, P_switching = (V_off * I_on) / 2 * (t_on + t_off) * f_sw",
                                    description = "Conduction loss matches static on-state characteristics. Switching loss scales linearly with transition speed durations (t_on, t_off) and frequency.",
                                    applicationTrick = "High frequencies cause switching loss to dominate, making MOSFET superior to SCR for high frequency operation."
                                )
                            ),
                            pyqs = emptyList(),
                            practiceQuestions = emptyList(),
                            mockQuiz = emptyList()
                        )
                    )
                ),
                Topic(
                    id = "pe_converters",
                    subjectId = subjectId,
                    name = "Converters & Inverters",
                    subtopics = listOf(
                        Subtopic(
                            id = "pe_conv_buck_boost",
                            topicId = "pe_converters",
                            subjectId = subjectId,
                            name = "Controlled Rectifiers, Buck & Boost Converters",
                            theory = TheoryContent(
                                title = "Phase-Controlled Rectifiers & Switching Regulator Choppers",
                                synopsis = "Covers average calculations for AC-DC rectifiers and DC-DC step-down (buck) / step-up (boost) converters.",
                                detailedBullets = listOf(
                                    "Single-Phase Full Converter: Average output voltage with highly inductive load: V_avg = (2 * V_max / pi) * cos(alpha).",
                                    "Buck Converter (Step-Down Chopper): Output voltage scale: V_out = D * V_in, where D is the duty cycle.",
                                    "Boost Converter (Step-Up Chopper): Output voltage scale: V_out = V_in / (1 - D)."
                                ),
                                keyInsight = "We assume ideal continuous conduction mode (CCM), meaning the inductor current never drops to zero during the switching cycle."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Buck voltage regulator output",
                                    expression = "V_o = D * V_in",
                                    description = "Step-down converter transfer function based on duty cycle scale.",
                                    applicationTrick = "D is regulated strictly between 0 and 1. If duty cycle is 0.4 with 50V input, output is 20V."
                                ),
                                FormulaItem(
                                    name = "Boost voltage regulator output",
                                    expression = "V_o = V_in / (1 - D)",
                                    description = "Step-up converter transfer function based on duty cycle.",
                                    applicationTrick = "As D approaches 1, output voltage theoretically approaches infinity, limited by inductor internal resistance."
                                )
                            ),
                            pyqs = listOf(
                                GateQuestion(
                                    id = "pyq_pe_buck_1",
                                    subjectId = subjectId,
                                    topicId = "pe_converters",
                                    subtopicId = "pe_conv_buck_boost",
                                    year = 2023,
                                    questionText = "A step-down (buck) DC-DC converter is fed from an input voltage of 50 V and operates at a duty cycle of 0.4 under continuous conduction mode. Identify the average output voltage in Volts.",
                                    questionType = QuestionType.NAT,
                                    correctNumericalRange = 20.0..20.0,
                                    explanation = "Average output voltage for a buck converter operating in continuous conduction mode is:\nV_out = D * V_in\nGiven D = 0.4 and V_in = 50 V:\nV_out = 0.4 * 50 = 20 V.",
                                    formulasUsed = "V_out = D * V_in",
                                    shortcutTricks = "Direct scaling: 40% of the input voltage (50) is 20 V, resolved instantly.",
                                    relatedConcepts = "Buck dc-dc switching converter",
                                    difficulty = "Easy"
                                )
                            ),
                            practiceQuestions = emptyList(),
                            mockQuiz = emptyList()
                        )
                    )
                ),
                Topic(
                    id = "pe_drives",
                    subjectId = subjectId,
                    name = "Motor Drives & PWM",
                    subtopics = listOf(
                        Subtopic(
                            id = "pe_drives_char",
                            topicId = "pe_drives",
                            subjectId = subjectId,
                            name = "AC, DC Motor drives & Inverters VSIs/CSIs",
                            theory = TheoryContent(
                                title = "Motor Speed Control, VSIs & SPWM",
                                synopsis = "Addresses AC & DC motor electric drives, three-phase Voltage Source Inverters (VSI 120/180 degree conduction), sinusoidal PWM (SPWM).",
                                detailedBullets = listOf(
                                    "DC Drives: Speed controlled using single-phase or three-phase fully controlled rectifiers modifying armature voltage.",
                                    "Inverter 180-Degree Conduction Mode: Each switch conducts for 180 degrees. Line RMS voltage: V_line_rms = sqrt(2/3) * V_dc ≈ 0.816 * V_dc.",
                                    "Sinusoidal PWM (SPWM): Adjusts output voltage fundamental component and shifts harmonics to higher frequencies by comparing a reference sine wave with a triangular carrier."
                                ),
                                keyInsight = "Under 180-degree conduction, two switches in each phase branch never conduct simultaneously, preventing shoot-through faults."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "VSI 180-degree line voltage RMS",
                                    expression = "V_L_rms = sqrt(2/3) * V_dc ≈ 0.816 * V_dc",
                                    description = "RMS line output voltage for a three-phase voltage source inverter in square-wave mode.",
                                    applicationTrick = "This is the line-to-line RMS voltage. Phase RMS voltage is sqrt(2)*V_dc/3."
                                )
                            ),
                            pyqs = emptyList(),
                            practiceQuestions = emptyList(),
                            mockQuiz = emptyList()
                        )
                    )
                )
            )
        )
    }

    private fun createAnalogElectronics(): Subject {
        val subjectId = "analog_electronics"
        return Subject(
            id = subjectId,
            name = "Analog Electronics",
            iconName = "tune",
            topics = listOf(
                Topic(
                    id = "ae_diodes_transistors",
                    subjectId = subjectId,
                    name = "Diodes & Transistors",
                    subtopics = listOf(
                        Subtopic(
                            id = "ae_dio_bias",
                            topicId = "ae_diodes_transistors",
                            subjectId = subjectId,
                            name = "PN Junctions, Clippers, Clampers & Biasing",
                            theory = TheoryContent(
                                title = "Diode Applications & Transistor Biasing",
                                synopsis = "Covers PN junction diode models, clippers (wave slicing), clampers (DC level shifters), and BJT/MOSFET biasing structures.",
                                detailedBullets = listOf(
                                    "PN Junction Turn-on Drop: Typically 0.7V for Silicon and 0.3V for Germanium diodes.",
                                    "Clippers: Use diodes to restrict waveform amplitudes above or below specific reference boundaries.",
                                    "Clampers: Use capacitors and diodes to add a DC bias to the input AC signal without altering its shape.",
                                    "BJT Active operating region: Base-Emitter is forward-biased, Base-Collector is reverse-biased."
                                ),
                                keyInsight = "To ensure a stable operating point (Q-point) in BJT circuits, use self-bias (voltage divider bias) configuration to minimize thermal runaway risks."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Shockley Diode Equation",
                                    expression = "I_D = I_S * [exp(V_D / (η * V_T)) - 1]",
                                    description = "Models the current of a PN junction diode under forward or reverse voltage drops. V_T is thermal voltage, η is the ideality factor.",
                                    applicationTrick = "At room temperature (300 K), the thermal voltage V_T = k * T / q is approximately 25.86 mV (often rounded to 25 mV or 26 mV in exam papers)."
                                ),
                                FormulaItem(
                                    name = "BJT Bias Relationship",
                                    expression = "I_C = β * I_B + I_CEO, I_E = I_C + I_B = (1 + β) * I_B",
                                    description = "Relates base current (I_B), collector current (I_C), and emitter current (I_E).",
                                    applicationTrick = "For high β (β >> 1), we assume collector current is approximately equal to emitter current: I_C ≈ I_E."
                                ),
                                FormulaItem(
                                    name = "Collector-to-Emitter Voltage",
                                    expression = "V_CE = V_CC - I_C * R_C - I_E * R_E",
                                    description = "The DC bias voltage across the collector and emitter terminals of the transistor.",
                                    applicationTrick = "For saturation detection, check if V_CE is less than V_CE_sat (typically around 0.2V)."
                                )
                            ),
                            pyqs = emptyList(),
                            practiceQuestions = emptyList(),
                            mockQuiz = emptyList()
                        )
                    )
                ),
                Topic(
                    id = "ae_amplifiers",
                    subjectId = subjectId,
                    name = "Amplifiers & Feedback",
                    subtopics = listOf(
                        Subtopic(
                            id = "ae_amp_feedback",
                            topicId = "ae_amplifiers",
                            subjectId = subjectId,
                            name = "CE / CS amplfiers & Feedback Topologies",
                            theory = TheoryContent(
                                title = "Common Emitter (CE) amplfiers & Negative Feedback",
                                synopsis = "Addresses transistor level small-signal models, CE gains, and the four negative feedback configurations.",
                                detailedBullets = listOf(
                                    "CE amplifier: Characteristics include a 180-degree phase shift, high voltage gain, and high current gain.",
                                    "Feedback classification: Voltage-Series, Voltage-Shunt, Current-Series, and Current-Shunt.",
                                    "Effect of Negative Feedback: Reduces gain, but improves bandwidth, decreases distortion, and stabilizes the circuit gain against parameter variations."
                                ),
                                keyInsight = "Voltage-Series negative feedback increases input impedance (good for buffers) and decreases output impedance (good for voltage sources)."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Closed-loop feedback gain",
                                    expression = "A_f = A / (1 + A * β)",
                                    description = "Evaluates overall gain with negative feedback scaling loop beta.",
                                    applicationTrick = "For negative feedback, the denominator is 1 + A*beta. If A*beta is much larger than 1, the closed-loop gain is approximately 1/beta."
                                )
                            ),
                            pyqs = emptyList(),
                            practiceQuestions = emptyList(),
                            mockQuiz = emptyList()
                        )
                    )
                ),
                Topic(
                    id = "ae_opamps",
                    subjectId = subjectId,
                    name = "Op-Amps & Waveforms",
                    subtopics = listOf(
                        Subtopic(
                            id = "ae_opamp_apps",
                            topicId = "ae_opamps",
                            subjectId = subjectId,
                            name = "Ideal Op-Amps, Integrator & Oscillators",
                            theory = TheoryContent(
                                title = "Op-Amp Golden Rules, Integrators & Sinusoidal Oscillators",
                                synopsis = "Studies operational amplifiers applications under negative feedback, active filters, integrators, and sinusoidal oscillators.",
                                detailedBullets = listOf(
                                    "Virtual Short Concept: Under negative feedback, the potential difference between the op-amp input terminals is zero: V_plus = V_minus.",
                                    "Zero Input Current: Zero current flows into the terminals due to infinite input impedance: I_plus = I_minus = 0.",
                                    "Integrator: A capacitor in the feedback path integrates the input signal. Differentiator: A capacitor at the input differentiates the signal.",
                                    "Sinusoidal Oscillators: Require positive feedback satisfying the Barkhausen criterion: loop gain A*beta must be exactly equal to 1.0 with a phase shift of 0 (or 360) degrees."
                                ),
                                keyInsight = "An integrator acts like a low-pass filter, whereas a differentiator acts like a high-pass filter."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Inverting Operational Gain",
                                    expression = "A_v = -R_f / R1",
                                    description = "Calculates inverting amplifier gain with Rf feedback.",
                                    applicationTrick = "Directly applicable when input is tied to R1 and V_plus is grounded."
                                ),
                                FormulaItem(
                                    name = "RC Phase-Shift Oscillator Frequency",
                                    expression = "f = 1 / (2 * π * R * C * sqrt(6))",
                                    description = "Calculates oscillates frequency for three cascaded RC phases.",
                                    applicationTrick = "Each of the three RC stages introduces a 60-degree phase shift, totaling 180 degrees. The inverting op-amp provides the remaining 180 degrees."
                                )
                            ),
                            pyqs = listOf(
                                GateQuestion(
                                    id = "pyq_ae_opamp_1",
                                    subjectId = subjectId,
                                    topicId = "ae_opamps",
                                    subtopicId = "ae_opamp_apps",
                                    year = 2022,
                                    questionText = "An inverting amplifier configuration is designed with an ideal op-amp, an input resistance R1 = 5 kOhms, and feedback resistance Rf = 25 kOhms. Calculate the closed-loop voltage gain.",
                                    questionType = QuestionType.MCQ,
                                    options = listOf("-5", "5", "-6", "6"),
                                    correctOptions = listOf(0),
                                    explanation = "According to the ideal op-amp inverting amplifier formula:\nA_v = -R_f / R1.\nGiven Rf = 25 kOhms and R1 = 5 kOhms:\nA_v = -25 / 5 = -5.",
                                    formulasUsed = "A_v = -Rf / R1",
                                    shortcutTricks = "The ratio of Rf to R1 is 25 / 5 = 5. Since it is inverting, add a negative sign: -5. Option at index 0 is correct.",
                                    relatedConcepts = "Operational amplifiers, negative feedback scaling",
                                    difficulty = "Easy"
                                )
                            ),
                            practiceQuestions = emptyList(),
                            mockQuiz = emptyList()
                        )
                    )
                )
            )
        )
    }

    private fun createDigitalElectronics(): Subject {
        val subjectId = "digital_electronics"
        return Subject(
            id = subjectId,
            name = "Digital Electronics",
            iconName = "memory",
            topics = listOf(
                Topic(
                    id = "de_bases_gates",
                    subjectId = subjectId,
                    name = "Number Systems & Gates",
                    subtopics = listOf(
                        Subtopic(
                            id = "de_numbers_gates",
                            topicId = "de_bases_gates",
                            subjectId = subjectId,
                            name = "Bases, Boolean Algebra & K-Maps",
                            theory = TheoryContent(
                                title = "Number System Representations & Logic Simplification",
                                synopsis = "Covers binary/octal/hex conversions, Boolean algebra rules, De Morgan's laws, and K-Map logic simplification.",
                                detailedBullets = listOf(
                                    "Universal Gates: NAND and NOR can synthesize any arbitrary Boolean logic function.",
                                    "De Morgan's Laws: (A + B)' = A' * B' and (A * B)' = A' + B'.",
                                    "Karnaugh Maps (K-Maps): Symmetrically groups adjacent '1' cells to minimize boolean equations."
                                ),
                                keyInsight = "K-Map groupings must always be powers of 2 (1, 2, 4, 8, 16) to eliminate redundant terms successfully."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "De Morgan's Laws",
                                    expression = "NOT(A AND B) = NOT A OR NOT B, NOT(A OR B) = NOT A AND NOT B",
                                    description = "Provides mathematical logic equivalence to invert product or sum groupings cleanly.",
                                    applicationTrick = "Break the line and change the sign (e.g., from + to · or from · to +) when resolving compliments."
                                ),
                                FormulaItem(
                                    name = "Boole's Absorption & Consensus Theorem",
                                    expression = "A + A * B = A, A * B + NOT A * C + B * C = A * B + NOT A * C",
                                    description = "Allows absorption and deletion of redundant term groups in complex Boolean equations.",
                                    applicationTrick = "The variable that appears with and without complement (A and NOT A) in separate terms can absorb the third term containing their partners."
                                )
                            ),
                            pyqs = emptyList(),
                            practiceQuestions = emptyList(),
                            mockQuiz = emptyList()
                        )
                    )
                ),
                Topic(
                    id = "de_combinational",
                    subjectId = subjectId,
                    name = "Combinational Circuits",
                    subtopics = listOf(
                        Subtopic(
                            id = "de_comb_mux",
                            topicId = "de_combinational",
                            subjectId = subjectId,
                            name = "Multiplexers, Decoders & Adders",
                            theory = TheoryContent(
                                title = "Data Selectors & Arithmetic Circuits",
                                synopsis = "Addresses multiplexer (MUX) operations, decoders, encoders, and hardware adders/subtractors.",
                                detailedBullets = listOf(
                                    "Multiplexer (MUX): Acts as a data selector. A 2^n-to-1 MUX uses n control lines to route a selected input to the output.",
                                    "Universal Synthesizer: A 2^n-to-1 MUX can realize any Boolean function of n+1 variables."
                                ),
                                keyInsight = "A 2-to-1 MUX can implement any 2-variable Boolean function directly without any external logic gates."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "2-to-1 MUX Output Expression",
                                    expression = "Y = S' * I_0 + S * I_1",
                                    description = "Evaluates multiplexer output based on selection line S.",
                                    applicationTrick = "By tieing I_0 and I_1 to various logic states (0, 1, or variables), a 2-to-1 MUX can implement AND, OR, and XOR gates."
                                )
                            ),
                            pyqs = emptyList(),
                            practiceQuestions = emptyList(),
                            mockQuiz = emptyList()
                        )
                    )
                ),
                Topic(
                    id = "de_sequential",
                    subjectId = subjectId,
                    name = "Sequential Circuits",
                    subtopics = listOf(
                        Subtopic(
                            id = "de_seq_counters",
                            topicId = "de_sequential",
                            subjectId = subjectId,
                            name = "Flip-Flops, Counters & Registers",
                            theory = TheoryContent(
                                title = "State Memory, State Tables & Counters",
                                synopsis = "Covers synchronous latches, flip-flops (SR, JK, D, T), excitation tables, shift registers, and synchronous/asynchronous counter design.",
                                detailedBullets = listOf(
                                    "Flip-Flops: State memory elements that transfer data on clock edges (edge-triggered).",
                                    "D Flip-Flop characteristic: Q_next = D. T Flip-Flop characteristic: Q_next = T XOR Q_present.",
                                    "JK Flip-Flop: Eliminates the invalid state of SR flip-flops by toggling when J = K = 1."
                                ),
                                keyInsight = "To build a modulo-N down/up counter, you require at least Ceil(log2 N) flip-flop items."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Flip-Flop Characteristic Equations",
                                    expression = "SR: Q_next = S + R_bar * Q, JK: Q_next = J * Q_bar + K_bar * Q, D: Q_next = D, T: Q_next = T ⊕ Q",
                                    description = "Predictive mathematical models of state updates for major memory latch configurations on clock triggering.",
                                    applicationTrick = "Use JK flip flop for toggle state: setting J = K = 1 always yields Q_next = Q_bar."
                                ),
                                FormulaItem(
                                    name = "Counter State Boundary Condition",
                                    expression = "MOD <= 2^N",
                                    description = "Specifies that N flip-flops can represent up to 2^N unique binary state sequences.",
                                    applicationTrick = "To build a MOD-10 counter, you need a minimum of 4 flip-flops (since 2^3 = 8 < 10 <= 16 = 2^4)."
                                )
                            ),
                            pyqs = emptyList(),
                            practiceQuestions = emptyList(),
                            mockQuiz = emptyList()
                        )
                    )
                )
            )
        )
    }

    private fun createElectromagneticTheory(): Subject {
        val subjectId = "electromagnetic_theory"
        return Subject(
            id = subjectId,
            name = "Electromagnetic Fields",
            iconName = "explore",
            topics = listOf(
                Topic(
                    id = "em_statics_maxwell",
                    subjectId = subjectId,
                    name = "Electrostatics & Maxwell",
                    subtopics = listOf(
                        Subtopic(
                            id = "em_statics_equations",
                            topicId = "em_statics_maxwell",
                            subjectId = subjectId,
                            name = "Electro-Magnetostatics & Maxwell Equations",
                            theory = TheoryContent(
                                title = "Electric/Magnetic Fields & Faraday/Ampere Laws",
                                synopsis = "Unifies electrostatic Coulomb fields, magnetostatic Ampere loops, and time-varying Maxwell equations.",
                                detailedBullets = listOf(
                                    "Gauss's Law (Electrostatics): Div(D) = rho_v. Relates charge density to electric flux displacement.",
                                    "Gauss's Law (Magnetostatics): Div(B) = 0. Confirms that isolated magnetic monopoles do not exist.",
                                    "Faraday's Law: Curl(E) = -dB/dt. A time-varying magnetic field induces a circulating electric field.",
                                    "Ampere-Maxwell Law: Curl(H) = J + dD/dt. Relates magnetic field intensity to conduction and displacement currents."
                                ),
                                keyInsight = "Maxwell's introduction of the displacement current term (dD/dt) restored mathematical consistency to Ampere's Law for time-varying fields."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Gauss's Divergence Law",
                                    expression = "div D_vector = ρ_v",
                                    description = "Differential form of electrostatic Gauss law.",
                                    applicationTrick = "Integrate divergence over the volume to find the total enclosed charge."
                                )
                            ),
                            pyqs = emptyList(),
                            practiceQuestions = emptyList(),
                            mockQuiz = emptyList()
                        )
                    )
                ),
                Topic(
                    id = "em_wave_transmission",
                    subjectId = subjectId,
                    name = "Waves & Transmission",
                    subtopics = listOf(
                        Subtopic(
                            id = "em_wave_prop_lines",
                            topicId = "em_wave_transmission",
                            subjectId = subjectId,
                            name = "Wave propagation, skin depth & lines",
                            theory = TheoryContent(
                                title = "TEM Wave Propagations & Attenuations",
                                synopsis = "Addresses transverse electromagnetic wave equations (TEM), wave impedance, medium losses, skin depth, and transmission lines.",
                                detailedBullets = listOf(
                                    "TEM Wave: Electric and magnetic fields are mutually perpendicular to each other and orthogonal to the direction of wave propagation.",
                                    "Wave Impedance: In free space, it is approx 377 Ohms (120*pi).",
                                    "Skin Depth (delta): The distance a wave travels into a lossy conductor where its field amplitude attenuates to 1/e ≈ 37% of its initial surface value."
                                ),
                                keyInsight = "Skin depth is inversely proportional to the square root of frequency, forcing high-frequency currents to flow strictly along the outer shell of conductors."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Intrinsic Impedance of Free Space",
                                    expression = "η_0 = sqrt(μ_0 / ε_0) ≈ 377 Ω",
                                    description = "The ratio of electric to magnetic field strength amplitudes in free space.",
                                    applicationTrick = "Can be written as exactly 120 * pi Ohms."
                                )
                            ),
                            pyqs = emptyList(),
                            practiceQuestions = emptyList(),
                            mockQuiz = emptyList()
                        )
                    )
                )
            )
        )
    }

    private fun createMeasurements(): Subject {
        val subjectId = "measurements"
        return Subject(
            id = subjectId,
            name = "Measurements & Instrumentation",
            iconName = "speed",
            topics = listOf(
                Topic(
                    id = "mi_meters_bridges",
                    subjectId = subjectId,
                    name = "Measuring Meters & Bridges",
                    subtopics = listOf(
                        Subtopic(
                            id = "mi_meters_ac_dc",
                            topicId = "mi_meters_bridges",
                            subjectId = subjectId,
                            name = "PMMC, Moving Iron, Wheatstone & AC Bridges",
                            theory = TheoryContent(
                                title = "DC/AC Measuring Instruments & Impedance Bridges",
                                synopsis = "Covers PMMC and MI instruments behaviors, alongside bridge networks used to find unknown resistances, inductances, and capacitances.",
                                detailedBullets = listOf(
                                    "PMMC (Permanent Magnet Moving Coil): Responds strictly to DC or average current values. Features linear scale.",
                                    "Moving Iron (MI) Meters: Deflecting torque is proportional to the square of current. Responds to composite system RMS values (both AC and DC).",
                                    "Wheatstone Bridge: Evaluates medium resistances at DC balance: R1*R4 = R2*R3.",
                                    "AC Bridges: Balance requires both magnitude and phase matching: Z1*Z4 = Z2*Z3. Maxwell/Anderson bridges for inductances, Schering bridge for capacitance."
                                ),
                                keyInsight = "At bridge balance, zero current flows through the central detector branch, indicating equal potential at both detector terminal nodes."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Bridge Balance matching state",
                                    expression = "Z1 * Z4 = Z2 * Z3",
                                    description = "Evaluates balancing condition of opposite passive branches.",
                                    applicationTrick = "Separate the complex balance equation into real and imaginary parts to solve for resistance and reactance parameters independently."
                                )
                            ),
                            pyqs = emptyList(),
                            practiceQuestions = emptyList(),
                            mockQuiz = emptyList()
                        )
                    )
                ),
                Topic(
                    id = "mi_sensors_digital",
                    subjectId = subjectId,
                    name = "Sensors & Digital Systems",
                    subtopics = listOf(
                        Subtopic(
                            id = "mi_transducers_cro",
                            topicId = "mi_sensors_digital",
                            subjectId = subjectId,
                            name = "LVDT, Strain Gauges, CRO & DSO Meters",
                            theory = TheoryContent(
                                title = "Industrial Transducers & Oscilloscopes",
                                synopsis = "Details electromechanical sensors (LVDT, Strain Gauge) and digital measurement tools (CRO, DSO).",
                                detailedBullets = listOf(
                                    "Strain Gauge: Measures mechanical strain based on resistance variations under physical stress.",
                                    "LVDT (Linear Variable Differential Transformer): An inductive transducer yielding a linear voltage response relative to displacement.",
                                    "CRO (Cathode Ray Oscilloscope): Lissajous patterns are used to evaluate phase differences and frequency ratios between two signal channels."
                                ),
                                keyInsight = "An LVDT outputs exactly zero potential at its central magnetic symmetry null position."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Strain Gauge Factor",
                                    expression = "G_F = (dR / R) / strain",
                                    description = "Relates change in resistance to physical strain deformation.",
                                    applicationTrick = "Can also be written as: G_F = 1 + 2 * Poisson_ratio + piezoresistive_factor."
                                )
                            ),
                            pyqs = emptyList(),
                            practiceQuestions = emptyList(),
                            mockQuiz = emptyList()
                        )
                    )
                )
            )
        )
    }
}
