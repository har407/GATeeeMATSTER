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
            formulaSheet = emptyList(),
            pyqs = emptyList(),
            practiceQuestions = emptyList(),
            mockQuiz = emptyList()
        )
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
                            formulaSheet = emptyList(),
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
                            formulaSheet = emptyList(),
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
                            id = "cs_mm_tf",
                            topicId = "cs_math_modeling",
                            subjectId = subjectId,
                            name = "Transfer Functions, Block Diagrams & Signal Flow Graphs",
                            theory = TheoryContent(
                                title = "System Modeling, Block Reductions & SFGs",
                                synopsis = "Covers differential models of physical systems, linear approximation, transform methods, block diagram reduction, and Mason's gain formula on signal flow graphs.",
                                detailedBullets = listOf(
                                    "Transfer Function: Ratio of Laplace transform of the output to Laplace transform of the input under zero initial conditions.",
                                    "Block Diagram Algebra: Rules for moving summing points and take-off points to simplify nested feedback loops.",
                                    "Signal Flow Graphs (SFG): Graphical representations of cause-and-effect relations. Mason's Gain Formula provides a direct feedback path ratio calculation without block reduction."
                                ),
                                keyInsight = "If two feedback loops touch each other, their product cannot be included in the non-touching loop term of determinant Δ."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Mason's Gain Formula",
                                    expression = "T = (1/Δ) * ∑ (P_k * Δ_k)",
                                    description = "Finds the overall transfer function of a signal flow graph directly.",
                                    applicationTrick = "Identify all individual loops and determine if any group of loops are mutually non-touching."
                                ),
                                FormulaItem(
                                    name = "Unity Negative-Feedback Gain",
                                    expression = "T(s) = G(s) / (1 + G(s)*H(s))",
                                    description = "Closed-loop transfer function with forward gain G(s) and feedback gain H(s).",
                                    applicationTrick = "If feedback is positive, the denominator becomes 1 - G(s)*H(s)."
                                )
                            ),
                            pyqs = listOf(
                                GateQuestion(
                                    id = "pyq_cs_mm_sfg",
                                    subjectId = subjectId,
                                    topicId = "cs_math_modeling",
                                    subtopicId = "cs_mm_tf",
                                    year = 2024,
                                    questionText = "For a signal flow graph, there are two forward paths: P1 = G1*G2*G3 and P2 = G4. There are three individual loops: L1 = -G2*H1, L2 = -G3*H2, and L3 = -G1*G2*G3*H3. Loops L1 and L2 are non-touching. What is the determinant Δ of this system?",
                                    questionType = QuestionType.MCQ,
                                    options = listOf(
                                        "1 + G2*H1 + G3*H2 + G1*G2*G3*H3",
                                        "1 + G2*H1 + G3*H2 + G1*G2*G3*H3 + G2*H1*G3*H2",
                                        "1 - G2*H1 - G3*H2 - G1*G2*G3*H3 + G2*G3*H1*H2",
                                        "1 - G2*H1 - G3*H2 - G1*G2*G3*H3"
                                    ),
                                    correctOptions = listOf(1),
                                    explanation = "According to Mason's Gain Rule, the determinant Δ is defined as: Δ = 1 - (Sum of individual loop gains) + (Sum of gain products of all possible combinations of two non-touching loops).\nIndividual loops are L1, L2, L3. Their gains are L1 = -G2*H1, L2 = -G3*H2, L3 = -G1*G2*G3*H3.\nTwo non-touching loops are L1 and L2. The product is L1*L2 = (-G2*H1) * (-G3*H2) = G2*H1*G3*H2.\nTherefore, Δ = 1 - (L1 + L2 + L3) + (L1 * L2)\nΔ = 1 - (-G2*H1 - G3*H2 - G1*G2*G3*H3) + (G2*H1*G3*H2)\nΔ = 1 + G2*H1 + G3*H2 + G1*G2*G3*H3 + G2*H1*G3*H2.",
                                    formulasUsed = "Δ = 1 - ∑L_i + ∑L_i*L_j",
                                    shortcutTricks = "Identify that L1 and L2 do not touch, so the term representing their gain product (+ L1*L2) must be present in Δ. Look for the positive product term G2*H1*G3*H2 with a '+' sign since (-)*(-) = + in the negative loops sum subtraction.",
                                    relatedConcepts = "Mason's Gain Rule, Signal Flow Graph, Determinant calculation",
                                    difficulty = "Medium"
                                )
                            ),
                            practiceQuestions = listOf(
                                GateQuestion(
                                    id = "pract_cs_mm_tf_val",
                                    subjectId = subjectId,
                                    topicId = "cs_math_modeling",
                                    subtopicId = "cs_mm_tf",
                                    year = 2023,
                                    questionText = "For a unity negative feedback system with forward path transfer function G(s) = 8 / (s + 2), find the value of the closed-loop transfer function T(s) at s = 2.",
                                    questionType = QuestionType.NAT,
                                    correctNumericalRange = 0.66..0.67,
                                    explanation = "The closed-loop transfer function T(s) is given by:\nT(s) = G(s) / (1 + G(s)*H(s))\nSince feedback H(s) = 1 (unity feedback):\nT(s) = G(s) / (1 + G(s)) = [8 / (s+2)] / [1 + 8/(s+2)] = 8 / (s+2 + 8) = 8 / (s + 10).\nAt s = 2:\nT(2) = 8 / (2 + 10) = 8 / 12 = 2 / 3 ≈ 0.667.",
                                    formulasUsed = "T(s) = G(s) / (1 + G(s))",
                                    shortcutTricks = "With H(s)=1, the closed loop pole moves from -2 to -2-8 = -10, yielding T(s) = 8/(s+10). For s=2, T(2) = 8/12 = 0.667 directly.",
                                    relatedConcepts = "Closed loop poles, transfer function analysis",
                                    difficulty = "Easy"
                                )
                            ),
                            mockQuiz = listOf(
                                GateQuestion(
                                    id = "mock_cs_mm_sfg_term",
                                    subjectId = subjectId,
                                    topicId = "cs_math_modeling",
                                    subtopicId = "cs_mm_tf",
                                    year = 2025,
                                    questionText = "In a signal flow graph, what is the value of the dynamic forward path cofactor Δ_k if the k-th forward path touches all individual loops in the graph?",
                                    questionType = QuestionType.MCQ,
                                    options = listOf(
                                        "Δ_k = 0",
                                        "Δ_k = 1",
                                        "Δ_k = Δ",
                                        "Δ_k = -1"
                                    ),
                                    correctOptions = listOf(1),
                                    explanation = "By definition, Δ_k is the cofactor of the k-th forward path, calculated from Δ by removing all loops that touch the k-th forward path.\nIf the k-th forward path touches all individual loops in the graph, all loop terms inside Δ are discarded (set to 0) for this cofactor calculation.\nThus, Δ_k reduces strictly to the baseline term: Δ_k = 1.",
                                    formulasUsed = "Δ_k = Δ evaluated by removing touching loops",
                                    shortcutTricks = "When a path touches every single loop, no loops remain in the sub-graph. The determinant of an empty loop graph is always 1.",
                                    relatedConcepts = "Mason's Gain Equation, Path cofactors",
                                    difficulty = "Easy"
                                )
                            )
                        )
                    )
                ),
                Topic(
                    id = "cs_time_response",
                    subjectId = subjectId,
                    name = "Time Response",
                    subtopics = listOf(
                        Subtopic(
                            id = "cs_tr_order_steady",
                            topicId = "cs_time_response",
                            subjectId = subjectId,
                            name = "First & Second Order Systems, Steady State Error",
                            theory = TheoryContent(
                                title = "Transient Responses & Steady-State Error Coefficients",
                                synopsis = "Deals with time domain responses of LTI control systems to standard inputs. Analyzes transient characteristics of 1st and 2nd-order systems, and steady-state errors.",
                                detailedBullets = listOf(
                                    "First-Order Transient: Characterized by time constant τ (tau). Time taken to reach 63.2% of its final value for a step input is exactly τ.",
                                    "Second-Order transient: Responses are categorized as Underdamped (0 < ζ < 1), Critically Damped (ζ = 1), Overdamped (ζ > 1), or Undamped (ζ = 0).",
                                    "Steady State Error (e_ss): Evaluates how well a system tracks reference inputs. Classified into Position (K_p), Velocity (K_v), and Acceleration (K_a) error constants dependent on system type."
                                ),
                                keyInsight = "System Type (number of open-loop poles at s=0) directly dictates the steady-state tracking error."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Peak Overshoot %",
                                    expression = "M_p = e^(-(ζ * π) / sqrt(1 - ζ^2)) * 100%",
                                    description = "Calculates maximum transient deviation under step input for underdamped systems.",
                                    applicationTrick = "M_p depends ONLY on the damping ratio ζ. The larger the ζ, the smaller the overshoot."
                                ),
                                FormulaItem(
                                    name = "Settling Time (2% Criterion)",
                                    expression = "t_s = 4 / (ζ * ω_n)",
                                    description = "Time required for response to stay within 2% of final value.",
                                    applicationTrick = "For 5% criterion, use 3 / (ζ * ω_n) instead."
                                ),
                                FormulaItem(
                                    name = "Steady-State Error Form",
                                    expression = "e_ss = lim_{s->0} [ s * R(s) / (1 + G(s)*H(s)) ]",
                                    description = "Computes steady-state tracking error using Final Value Theorem.",
                                    applicationTrick = "Ensure s*R(s)/(1+GH(s)) has no poles in the RHS before applying."
                                )
                            ),
                            pyqs = listOf(
                                GateQuestion(
                                    id = "pyq_cs_tr_static_error",
                                    subjectId = subjectId,
                                    topicId = "cs_time_response",
                                    subtopicId = "cs_tr_order_steady",
                                    year = 2023,
                                    questionText = "An open-loop transfer function of a unity feedback system is G(s) = 10 / (s*(s + 2)). Find the steady-state error when the system is subjected to a unit ramp input r(t) = t * u(t).",
                                    questionType = QuestionType.NAT,
                                    correctNumericalRange = 0.2..0.2,
                                    explanation = "For a unit ramp input R(s) = 1/s^2.\nThe steady-state error for a unit ramp in a unity feedback system is e_ss = 1 / K_v, where K_v is the velocity error constant.\nK_v = lim_{s->0} [ s * G(s) ] = lim_{s->0} [ s * 10 / (s*(s + 2)) ] = 10 / 2 = 5.\ne_ss = 1 / K_v = 1 / 5 = 0.2.",
                                    formulasUsed = "K_v = lim_{s->0} s*G(s), e_ss = 1 / K_v",
                                    shortcutTricks = "For a type-1 system, K_v is simply the gain factor divided by any remaining constant in the denominator when s=0, i.e., 10 / 2 = 5. Error e_ss = 1/5 = 0.2 instantly.",
                                    relatedConcepts = "Steady-state error, Velocity error constant, System type",
                                    difficulty = "Easy"
                                )
                            ),
                            practiceQuestions = listOf(
                                GateQuestion(
                                    id = "pract_cs_tr_specs",
                                    subjectId = subjectId,
                                    topicId = "cs_time_response",
                                    subtopicId = "cs_tr_order_steady",
                                    year = 2022,
                                    questionText = "A second-order closed-loop system is modeled by the transfer function T(s) = 100 / (s^2 + 10s + 100). Find the damping ratio and the peak time in seconds under unit step excitation.",
                                    questionType = QuestionType.MCQ,
                                    options = listOf(
                                        "ζ = 0.5, t_p = 0.363s",
                                        "ζ = 0.5, t_p = 0.726s",
                                        "ζ = 0.866, t_p = 0.363s",
                                        "ζ = 0.866, t_p = 0.726s"
                                    ),
                                    correctOptions = listOf(0),
                                    explanation = "Comparing s^2 + 10s + 100 with standard form s^2 + 2*ζ*ω_n*s + ω_n^2 = 0:\n1. ω_n^2 = 100 => ω_n = 10 rad/s.\n2. 2*ζ*ω_n = 10 => 20*ζ = 10 => ζ = 0.5 (Underdamped).\nPeak time t_p is given by: t_p = π / (ω_n * sqrt(1 - ζ^2)) = π / (10 * sqrt(1 - 0.25)) = π / (10 * sqrt(0.75)) = π / (10 * 0.866) = π / 8.66 ≈ 0.3627 seconds.",
                                    formulasUsed = "ω_n = sqrt(K), 2*ζ*ω_n = 2α, t_p = π / (ω_n*sqrt(1-ζ^2))",
                                    shortcutTricks = "With ω_n = 10 and ζ = 0.5, the damped frequency ω_d = ω_n * sqrt(1-ζ^2) = 10 * 0.866 = 8.66 rad/s. t_p = 3.1415 / 8.66 is clearly around 0.36s. This narrows the options immediately.",
                                    relatedConcepts = "Transient specifications, underdamped systems",
                                    difficulty = "Medium"
                                )
                            ),
                            mockQuiz = listOf(
                                GateQuestion(
                                    id = "mock_cs_tr_steady_state_step",
                                    subjectId = subjectId,
                                    topicId = "cs_time_response",
                                    subtopicId = "cs_tr_order_steady",
                                    year = 2025,
                                    questionText = "Determine the steady-state error e_ss of a system with open-loop transfer function G(s) = 100 / (s^2 + 15s + 50) and unity feedback under a unit step reference input r(t) = u(t).",
                                    questionType = QuestionType.NAT,
                                    correctNumericalRange = 0.33..0.34,
                                    explanation = "This is a Type-0 system, so static error position constant K_p is finite:\nK_p = lim_{s->0} [ G(s) ] = 100 / 50 = 2.\nThe steady-state error under a unit step input is:\ne_ss = 1 / (1 + K_p) = 1 / (1 + 2) = 1/3 ≈ 0.333.",
                                    formulasUsed = "K_p = lim_{s->0} G(s), e_ss = 1 / (1 + K_p)",
                                    shortcutTricks = "For Type-0 system, evaluate G(0) = 100/50 = 2. e_ss is 1/(1+2) = 1/3 = 0.333 instantly.",
                                    relatedConcepts = "System Type, Position error constant",
                                    difficulty = "Easy"
                                )
                            )
                        )
                    )
                ),
                Topic(
                    id = "cs_stability",
                    subjectId = subjectId,
                    name = "Stability",
                    subtopics = listOf(
                        Subtopic(
                            id = "cs_stab_routh_locus",
                            topicId = "cs_stability",
                            subjectId = subjectId,
                            name = "Routh-Hurwitz Criterion & Root Locus",
                            theory = TheoryContent(
                                title = "Absolute Stability Criteria & Closed-Loop Vector Trajectories",
                                synopsis = "Covers absolute stability via Routh-Hurwitz criterion and plotting roots of the characteristic equation (Root Locus) as a function of open-loop gain.",
                                detailedBullets = listOf(
                                    "Routh-Hurwitz Table: Grid constructed using coefficients of the characteristic equation. The number of sign changes in the first column equals the number of roots in the right-half s-plane.",
                                    "Asymptotes: Number of asymptotes = P - Z, meeting at centroid σ_a along the real axis with angles θ_a.",
                                    "Breakaway Points: Points where roots leave or enter the real axis, found by solving dK/ds = 0."
                                ),
                                keyInsight = "The angle of departure from complex poles is given by: φ_dep = 180° - ∑φ_p + ∑φ_z."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Centroid of Asymptotes",
                                    expression = "σ_a = (∑(Real part of Poles) - ∑(Real part of Zeros)) / (P - Z)",
                                    description = "Computes the real axis intersection point of the root locus asymptotes.",
                                    applicationTrick = "Include both real and complex poles/zeros correctly in the sum."
                                ),
                                FormulaItem(
                                    name = "Asymptotic Angles",
                                    expression = "θ_a = (2q + 1) * 180° / (P - Z)",
                                    description = "Angles of asymptotes with respect to the positive real axis.",
                                    applicationTrick = "For P - Z = 3, angles are always 60°, 180°, and 300° (or -60°)."
                                )
                            ),
                            pyqs = listOf(
                                GateQuestion(
                                    id = "pyq_cs_stab_routh",
                                    subjectId = subjectId,
                                    topicId = "cs_stability",
                                    subtopicId = "cs_stab_routh_locus",
                                    year = 2024,
                                    questionText = "The characteristic equation of a closed-loop system is given by s^3 + 3s^2 + 2s + K = 0. For the system to be stable, the range of the feedback gain K must satisfy:",
                                    questionType = QuestionType.MCQ,
                                    options = listOf(
                                        "K > 0",
                                        "0 < K < 6",
                                        "K < 6",
                                        "3 < K < 6"
                                    ),
                                    correctOptions = listOf(1),
                                    explanation = "Build the Routh Array:\ns^3 :  1     2\ns^2 :  3     K\ns^1 :  (3*2 - K)/3 = (6-K)/3\ns^0 :  K\nFor stability, all elements in the first column must be strictly positive:\n1) K > 0\n2) (6 - K) / 3 > 0 => 6 - K > 0 => K < 6.\nCombining these gives: 0 < K < 6.",
                                    formulasUsed = "Routh-Hurwitz array sign criteria",
                                    shortcutTricks = "For a third-order polynomial s^3 + a_2*s^2 + a_1*s + a_0 = 0 to be stable, the inner product must exceed the outer product: a_2 * a_1 > a_0 => 3 * 2 > K => K < 6. Since K must be positive, 0 < K < 6.",
                                    relatedConcepts = "Routh-Hurwitz criterion, Absolute stability",
                                    difficulty = "Easy"
                                )
                            ),
                            practiceQuestions = listOf(
                                GateQuestion(
                                    id = "pract_cs_stab_centroid",
                                    subjectId = subjectId,
                                    topicId = "cs_stability",
                                    subtopicId = "cs_stab_routh_locus",
                                    year = 2021,
                                    questionText = "Consider a open-loop transfer function G(s)H(s) = K / (s * (s + 1) * (s + 5)). Compute the real-axis centroid coordinate σ_a of the root locus asymptotes.",
                                    questionType = QuestionType.NAT,
                                    correctNumericalRange = -2.0..-2.0,
                                    explanation = "Open-loop poles are located at: s = 0, s = -1, s = -5. Number of poles P = 3.\nOpen-loop zeros: None. Number of zeros Z = 0.\nUsing the centroid formula:\nσ_a = (∑ Poles - ∑ Zeros) / (P - Z)\nσ_a = ((0 + (-1) + (-5)) - 0) / (3 - 0)\nσ_a = -6 / 3 = -2.0.",
                                    formulasUsed = "σ_a = (∑Re(P) - ∑Re(Z)) / (P - Z)",
                                    shortcutTricks = "Sum the poles: 0 + (-1) + (-5) = -6. Since there are 3 asymptotes, divide -6 by 3 directly to get -2.0.",
                                    relatedConcepts = "Root locus asymptotes, centroid of poles",
                                    difficulty = "Easy"
                                )
                            ),
                            mockQuiz = listOf(
                                GateQuestion(
                                    id = "mock_cs_stab_marginal_freq",
                                    subjectId = subjectId,
                                    topicId = "cs_stability",
                                    subtopicId = "cs_stab_routh_locus",
                                    year = 2025,
                                    questionText = "For the characteristic equation s^3 + 3s^2 + 2s + K = 0, find the frequency of sustained oscillations (in rad/s) when the system is marginally stable (K = 6).",
                                    questionType = QuestionType.NAT,
                                    correctNumericalRange = 1.41..1.42,
                                    explanation = "When K = 6, the row of s^1 in the Routh auxiliary array becomes zero. We form the auxiliary equation A(s) from the s^2 row above it:\nA(s) = 3*s^2 + K = 0.\nSubstituting K = 6:\n3*s^2 + 6 = 0 => s^2 + 2 = 0 => s = ±j*sqrt(2).\nThus, the frequency of sustained oscillations is ω = sqrt(2) ≈ 1.414 rad/s.",
                                    formulasUsed = "Auxiliary Equation A(s) from Routh Array",
                                    shortcutTricks = "For s^3 + a2*s^2 + a1*s + a0 = 0, auxiliary equation frequency ω = sqrt(a0/a2) = sqrt(K/3) = sqrt(6/3) = sqrt(2) ≈ 1.414 rad/s.",
                                    relatedConcepts = "Marginal stability, auxiliary equations",
                                    difficulty = "Medium"
                                )
                            )
                        )
                    )
                ),
                Topic(
                    id = "cs_freq_response",
                    subjectId = subjectId,
                    name = "Frequency Response",
                    subtopics = listOf(
                        Subtopic(
                            id = "cs_fr_plots",
                            topicId = "cs_freq_response",
                            subjectId = subjectId,
                            name = "Bode, Nyquist, and Polar Plots",
                            theory = TheoryContent(
                                title = "Frequency Domain Specifications & Nyquist Contours",
                                synopsis = "Examines frequency domain response using logarithmic scales (Bode Plot), polar coordinates (Polar Plot), and complex contour mapping (Nyquist plots).",
                                detailedBullets = listOf(
                                    "Bode Plot: Consists of magnitude (dB vs log ω) and phase (degrees vs log ω). Offers quick verification of Gain Margin (GM) and Phase Margin (PM).",
                                    "Gain Crossover (ω_gc) & Phase Crossover (ω_pc): ω_gc occurs where |G(jω)| = 1 (0 dB). ω_pc occurs where phase is -180°.",
                                    "Nyquist Stability Criterion: Relates system closed-loop stability to open-loop poles and encirclements of (-1 + j0) in G(H) plane: Z = N + P."
                                ),
                                keyInsight = "For minimum-phase systems, stable closed-loop behavior requires Phase Margin to be strictly greater than 0° at the gain crossover frequency."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Phase Margin (PM)",
                                    expression = "PM = 180° + ∠G(jω_gc)",
                                    description = "Measures additional phase delay allowable at gain crossover frequency before causing instability.",
                                    applicationTrick = "Subtract the absolute phase value at ω_gc from 180° directly."
                                ),
                                FormulaItem(
                                    name = "Gain Margin (GM)",
                                    expression = "GM = 1 / |G(jω_pc)|",
                                    description = "Measures factor by which system gain can be multiplied before causing instability.",
                                    applicationTrick = "If magnitude at phase crossover is 0.5, Gain Margin is 2.0 (or 20 * log10(2) = 6 dB)."
                                ),
                                FormulaItem(
                                    name = "Nyquist Encirclement Formula",
                                    expression = "N = Z - P",
                                    description = "Relates clockwise encirclements of (-1 + j0) to RHS closed-loop poles (Z) and RHS open-loop poles (P).",
                                    applicationTrick = "For stable closed loop (Z=0), N must be equal to -P (counter-clockwise encirclements)."
                                )
                            ),
                            pyqs = listOf(
                                GateQuestion(
                                    id = "pyq_cs_fr_pm_calc",
                                    subjectId = subjectId,
                                    topicId = "cs_freq_response",
                                    subtopicId = "cs_fr_plots",
                                    year = 2023,
                                    questionText = "For an open-loop system with transfer function G(s)H(s) = 1 / (s * (s + 1)), find the phase margin in degrees at the gain crossover frequency ω_gc ≈ 0.786 rad/s.",
                                    questionType = QuestionType.NAT,
                                    correctNumericalRange = 51.0..53.0,
                                    explanation = "The phase margin PM is given by: PM = 180° + ∠G(jω_gc)H(jω_gc).\n∠G(jω)H(jω) = -90° - tan^-1(ω).\nSubstituting ω_gc = 0.786:\n∠G(j0.786)H(j0.786) = -90° - tan^-1(0.786) ≈ -90° - 38.17° = -128.17°.\nPM = 180° - 128.17° = 51.83°.",
                                    formulasUsed = "∠GH(jω) = -90 - tan^-1(ω), PM = 180 + ∠GH(jω_gc)",
                                    shortcutTricks = "tan^-1(0.786) is approximately 38°. Subtracting 128° from 180° gives about 52°.",
                                    relatedConcepts = "Phase Margin, Bode plot cross-overs, relative stability",
                                    difficulty = "Medium"
                                )
                            ),
                            practiceQuestions = listOf(
                                GateQuestion(
                                    id = "pract_cs_fr_nyquist",
                                    subjectId = subjectId,
                                    topicId = "cs_freq_response",
                                    subtopicId = "cs_fr_plots",
                                    year = 2022,
                                    questionText = "For a unity negative feedback system, the open-loop transfer function G(s) has 1 pole in the right-half s-plane (P = 1). If the Nyquist plot of G(s) encircles the critical point (-1, j0) exactly once in the counter-clockwise direction, the number of closed-loop poles in the right-half s-plane (Z) is:",
                                    questionType = QuestionType.MCQ,
                                    options = listOf(
                                        "Z = 0 (Stable)",
                                        "Z = 1 (Unstable)",
                                        "Z = 2 (Unstable)",
                                        "Cannot be determined"
                                    ),
                                    correctOptions = listOf(0),
                                    explanation = "According to the Nyquist Stability Criterion:\nN = Z - P, where N is the number of CLOCKWISE encirclements of (-1, j0).\nSince the encirclement is COUNTER-CLOCKWISE, N = -1.\nGiven open-loop RHS poles P = 1.\nSubstituting into the formula: -1 = Z - 1 => Z = 0.\nSince Z = 0, there are no closed-loop poles in the right-half s-plane, which means the closed-loop system is stable.",
                                    formulasUsed = "N = Z - P",
                                    shortcutTricks = "CCW encirclements count as negative clockwise encirclements. Thus N = -1. Z = N + P = -1 + 1 = 0. Instantly stable!",
                                    relatedConcepts = "Nyquist stability criterion, contour encirclements",
                                    difficulty = "Medium"
                                )
                            ),
                            mockQuiz = listOf(
                                GateQuestion(
                                    id = "mock_cs_fr_bode_slope",
                                    subjectId = subjectId,
                                    topicId = "cs_freq_response",
                                    subtopicId = "cs_fr_plots",
                                    year = 2025,
                                    questionText = "A Bode magnitude plot has a slope that changes from -20 dB/decade to -60 dB/decade at frequency ω = 10 rad/s. This slope transition represents:",
                                    questionType = QuestionType.MCQ,
                                    options = listOf(
                                        "A simple zero at ω = 10",
                                        "A double pole at ω = 10",
                                        "A double zero at ω = 10",
                                        "A simple pole at ω = 10"
                                    ),
                                    correctOptions = listOf(1),
                                    explanation = "Each pole contributes a slope of -20 dB/decade at its corner frequency, while each zero contributes +20 dB/decade.\nThe slope changes from -20 dB/dec to -60 dB/dec, which is a net decrease in slope of (-60) - (-20) = -40 dB/dec.\nSince each pole decreases the slope by -20 dB/dec, a drop of -40 dB/dec indicates a double pole (two poles) at ω = 10 rad/s.",
                                    formulasUsed = "Slope change = -20 * (No. of poles at crossover) + 20 * (No. of zeros)",
                                    shortcutTricks = "A slope change of -40 dB/dec is always 2 poles (double pole). A change of +20 dB/dec is a single zero.",
                                    relatedConcepts = "Bode plots, corner frequencies, poles and zeros",
                                    difficulty = "Easy"
                                )
                            )
                        )
                    )
                ),
                Topic(
                    id = "cs_controllers_comp",
                    subjectId = subjectId,
                    name = "Controllers & Compensators",
                    subtopics = listOf(
                        Subtopic(
                            id = "cs_ctrl_pid_leadlag",
                            topicId = "cs_controllers_comp",
                            subjectId = subjectId,
                            name = "PID Controllers & Compensation Techniques",
                            theory = TheoryContent(
                                title = "Industrial Regulation & Phase Shifting Compensators",
                                synopsis = "Details proportional-integral-derivative controllers (PID) and physical realization of lead, lag, and lead-lag compensation networks.",
                                detailedBullets = listOf(
                                    "P-Controller: Increases speed of response but may preserve steady-state feedback offset error.",
                                    "I-Controller: Adds a pole at the origin, eliminating steady-state offset error but typically reducing relative stability margins.",
                                    "D-Controller: Adds a zero in LHP. Improves transient damping, raises Phase Margin, but increases noise sensitivity.",
                                    "Lead Compensator: Zero is closer to origin than pole. Acts like high-pass filter; increases phase margin and speed.",
                                    "Lag Compensator: Pole is closer to origin than zero. Acts like low-pass filter; reduces steady-state error by rising low-frequency gain."
                                ),
                                keyInsight = "A Phase-Lead compensator acts structurally as a High-Pass filter, whereas a Phase-Lag compensator serves as a Low-Pass filter."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Max Phase Frequency - Lead",
                                    expression = "ω_m = sqrt(ω_z * ω_p)",
                                    description = "Frequency at which peak positive phase lead shift occurs.",
                                    applicationTrick = "Calculated as the geometric mean of the lead pole and zero frequencies."
                                ),
                                FormulaItem(
                                    name = "Max Phase Shift - Lead",
                                    expression = "sin(φ_m) = (1 - α) / (1 + α)",
                                    description = "Relates maximum phase angle contribution to the lead factor alpha (α = Z / P < 1).",
                                    applicationTrick = "If α = 1/3, then sin(φ_m) = 0.5, yielding peak phase shift φ_m = 30°."
                                )
                            ),
                            pyqs = listOf(
                                GateQuestion(
                                    id = "pyq_cs_ctrl_lead_poles",
                                    subjectId = subjectId,
                                    topicId = "cs_controllers_comp",
                                    subtopicId = "cs_ctrl_pid_leadlag",
                                    year = 2024,
                                    questionText = "The transfer function of a phase-lead compensator is given by D(s) = (s + a) / (s + b). Which of the following conditions must be satisfied for this network to act as a proper phase-lead network?",
                                    questionType = QuestionType.MCQ,
                                    options = listOf(
                                        "a > b and both are positive",
                                        "a < b and both are positive",
                                        "a > b and both are negative",
                                        "a = b"
                                    ),
                                    correctOptions = listOf(1),
                                    explanation = "For a lead compensator D(s) = (s + a) / (s + b), the zero at -a must be closer to the origin than the pole at -b.\nIn terms of absolute values, this means a < b (or zero frequency a is smaller than pole frequency b).\nAdditionally, both poles and zeros must lie in the LHP, which requires both a and b to be positive constants.",
                                    formulasUsed = "Structural definition of Lead: Zero closer to origin than Pole",
                                    shortcutTricks = "Remember that a lead compensator behaves as a high-pass filter. At low frequencies s->0, gain is a/b. At high frequencies s->inf, gain is 1. For high-pass behavior, high frequency gain must exceed low frequency gain: 1 > a/b => b > a, meaning a < b.",
                                    relatedConcepts = "Phase-lead design, passive networks, filters",
                                    difficulty = "Easy"
                                )
                            ),
                            practiceQuestions = listOf(
                                GateQuestion(
                                    id = "pract_cs_ctrl_max_phase_freq",
                                    subjectId = subjectId,
                                    topicId = "cs_controllers_comp",
                                    subtopicId = "cs_ctrl_pid_leadlag",
                                    year = 2022,
                                    questionText = "For a lead compensator transfer function G_c(s) = (s + 2) / (s + 8), compute the frequency (in rad/s) at which the maximum phase lead occurs.",
                                    questionType = QuestionType.NAT,
                                    correctNumericalRange = 4.0..4.0,
                                    explanation = "For a compensator in the form (s + z) / (s + p), the zero is at z = 2 and the pole is at p = 8.\nThe maximum phase lead occurs at the geometric mean of the pole and zero frequencies:\nω_m = sqrt(z * p)\nω_m = sqrt(2 * 8) = sqrt(16) = 4.0 rad/s.",
                                    formulasUsed = "ω_m = sqrt(z * p)",
                                    shortcutTricks = "Instantly take the square root of the product of the numerator zero and denominator pole values: root(2 * 8) = 4.0.",
                                    relatedConcepts = "Geometric mean frequency, lead compensator specs",
                                    difficulty = "Easy"
                                )
                            ),
                            mockQuiz = listOf(
                                GateQuestion(
                                    id = "mock_cs_ctrl_pid_prop",
                                    subjectId = subjectId,
                                    topicId = "cs_controllers_comp",
                                    subtopicId = "cs_ctrl_pid_leadlag",
                                    year = 2025,
                                    questionText = "In industrial automation, what is the primary side-effect of using a heavy Integral (I) controller action?",
                                    questionType = QuestionType.MCQ,
                                    options = listOf(
                                        "Increases the steady-state error",
                                        "Increases noise amplification at high frequencies",
                                        "Reduces speed of rise time and causes system offset",
                                        "Increases system overshoot and tends to degrade relative stability"
                                    ),
                                    correctOptions = listOf(3),
                                    explanation = "An Integral controller action adds a pole at the origin, which increases the system type by 1 and removes any steady-state error.\nHowever, adding a pole pushes the root locus branches further to the right in the s-plane, which reduces the damping and the phase margin.\nThis typically results in increased system overshoot and degrades relative stability, potentially leading to oscillations or even instability if the integral gain is set too high.",
                                    formulasUsed = "Adding pole at origin = Phase delay -90 degrees",
                                    shortcutTricks = "Adding a pole at s=0 adds -90 degrees of negative phase lag. This directly reduces phase margin and thus degrades stability, causing more overshoot.",
                                    relatedConcepts = "PID controller tuning, integral action, system type",
                                    difficulty = "Medium"
                                )
                            )
                        )
                    )
                ),
                Topic(
                    id = "cs_state_space",
                    subjectId = subjectId,
                    name = "State Space Analysis",
                    subtopics = listOf(
                        Subtopic(
                            id = "cs_ss_variables",
                            topicId = "cs_state_space",
                            subjectId = subjectId,
                            name = "State Variables, Controllability, and Observability",
                            theory = TheoryContent(
                                title = "Multi-variable Modeling, Coordinate Transforms & System Matrix States",
                                synopsis = "Introduces modern state space control theory. Analyzes state variables translation, State Transition Matrix (STM) calculation, controllability, and observability criterion.",
                                detailedBullets = listOf(
                                    "State Equation & Output Equation: dx/dt = A*x + B*u, and y = C*x + D*u.",
                                    "State Transition Matrix (STM) φ(t) = e^{A*t} = L^{-1}{ (sI - A)^{-1} }. It maps state values from t=0 to any time t.",
                                    "Controllability & Observability: Matrix rank criteria of Kalman: Q_c = [ B | A*B | ... | A^{n-1}*B ] and Q_o = [ C | C*A | ... | C*A^{n-1} ]^T must be full rank."
                                ),
                                keyInsight = "If a pole-zero cancellation occurs in the input-output transfer function, that cancelled mode is guaranteed to be either uncontrollable, unobservable, or both."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "State Transition Matrix Expression",
                                    expression = "φ(t) = L^-1 { (sI - A)^-1 }",
                                    description = "Computes the continuous-time state transition matrix using inverse Laplace transforms.",
                                    applicationTrick = "For diagonal systems where A is diag(λ1, λ2), then φ(t) is simply diag(e^{λ1*t}, e^{λ2*t})."
                                ),
                                FormulaItem(
                                    name = "Transfer Matrix from State Space",
                                    expression = "T(s) = C * (sI - A)^-1 * B + D",
                                    description = "Evaluates overall input-to-output matrix relation from state space equations.",
                                    applicationTrick = "For single-input-single-output (SISO) physical systems, feedthrough term D is usually zero."
                                )
                            ),
                            pyqs = listOf(
                                GateQuestion(
                                    id = "pyq_cs_ss_controllability",
                                    subjectId = subjectId,
                                    topicId = "cs_state_space",
                                    subtopicId = "cs_ss_variables",
                                    year = 2024,
                                    questionText = "A state space model is given by dx/dt = [0 1; -2 -3]*x + [1; 1]*u. What is the determinant of its controllability matrix Q_c?",
                                    questionType = QuestionType.NAT,
                                    correctNumericalRange = -6.0..-6.0,
                                    explanation = "Here, A = [0 1; -2 -3] and B = [1; 1].\nWe calculate the vector A * B:\nA * B = [0*1 + 1*1; -2*1 + (-3)*1] = [1; -5].\nThe controllability matrix is:\nQ_c = [ B | A*B ] = [1 1; 1 -5].\nDeterminant of Q_c is:\ndet(Q_c) = 1*(-5) - 1*1 = -5 - 1 = -6.0.",
                                    formulasUsed = "Q_c = [ B | AB ], det(Q_c)",
                                    shortcutTricks = "Construct matrix [B | AB] directly. For A = [0 1; -2 -3] and B = [1; 1], the second column is [1; -5]. det = -5 - 1 = -6.",
                                    relatedConcepts = "Controllability matrix rank, state variable modeling",
                                    difficulty = "Medium"
                                )
                            ),
                            practiceQuestions = listOf(
                                GateQuestion(
                                    id = "pract_cs_ss_stm",
                                    subjectId = subjectId,
                                    topicId = "cs_state_space",
                                    subtopicId = "cs_ss_variables",
                                    year = 2023,
                                    questionText = "For a linear system with state matrix A = [-2 0; 0 -5], find the state transition matrix φ(t).",
                                    questionType = QuestionType.MCQ,
                                    options = listOf(
                                        "[e^(-2t) 0; 0 e^(-5t)]",
                                        "[e^(2t) 0; 0 e^(5t)]",
                                        "[1/2 0; 0 1/5]",
                                        "[e^(-2t) t; 0 e^(-5t)]"
                                    ),
                                    correctOptions = listOf(0),
                                    explanation = "When matrix A is diagonal, i.e., A = [λ1 0; 0 λ2], the state transition matrix φ(t) = e^(At) is also diagonal, given by φ(t) = [e^(λ1*t) 0; 0 e^(λ2*t)].\nSubstituting λ1 = -2 and λ2 = -5:\nφ(t) = [e^(-2t) 0; 0 e^(-5t)].",
                                    formulasUsed = "φ(t) = e^(At)",
                                    shortcutTricks = "Since there are no off-diagonal elements in A, each state equation is completely uncoupled: dx1/dt = -2x1, and dx2/dt = -5x2. The responses are x1(t) = e^(-2t)x1(0), and x2(t) = e^(-5t)x2(0). This corresponds to the diagonal exponential matrix immediately.",
                                    relatedConcepts = "State transition matrix, uncoupled differential systems",
                                    difficulty = "Easy"
                                )
                            ),
                            mockQuiz = listOf(
                                GateQuestion(
                                    id = "mock_cs_ss_observability",
                                    subjectId = subjectId,
                                    topicId = "cs_state_space",
                                    subtopicId = "cs_ss_variables",
                                    year = 2025,
                                    questionText = "A second order system is given with state matrix A = [1 2; 0 3] and output matrix C = [1 0]. The system is:",
                                    questionType = QuestionType.MCQ,
                                    options = listOf(
                                        "Fully Observable",
                                        "Unobservable",
                                        "Stable but Unobservable",
                                        "Marginally Observable"
                                    ),
                                    correctOptions = listOf(0),
                                    explanation = "To check if the system is observable, we construct the observability matrix Q_o:\nQ_o = [ C; C*A ].\nC = [1 0].\nC * A = [1 0] * [1 2; 0 3] = [1*1 + 0*0, 1*2 + 0*3] = [1 2].\nTherefore, Q_o = [1 0; 1 2].\nNow check the rank by finding the determinant:\ndet(Q_o) = 1*2 - 0*1 = 2.\nSince det(Q_o) = 2, which is non-zero, the observability matrix has full rank (rank = 2), so the system is fully observable.",
                                    formulasUsed = "Q_o = [ C; CA ], det(Q_o) != 0",
                                    shortcutTricks = "Compute C*A = [1 2]. Q_o is [1 0; 1 2]. Since the columns/rows are clearly linearly independent, rank is 2 (full rank), hence fully observable.",
                                    relatedConcepts = "Observability matrix rank, system identification",
                                    difficulty = "Medium"
                                )
                            )
                        )
                    )
                )
            )
        )
    }

    private fun createElectricalMachines(): Subject {
        val subjectId = "electrical_machines"
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
                            id = "em_trans_equiv",
                            topicId = "em_transformers",
                            subjectId = subjectId,
                            name = "Equivalent Circuits, Losses & Efficiency",
                            theory = TheoryContent(
                                title = "Transformer Modeling & Loss Analysis",
                                synopsis = "Addresses equivalent models, core hysteretic/eddy damping, variable winding copper losses, and maximum efficiency loading parameters.",
                                detailedBullets = listOf(
                                    "No-Load Losses (Iron loss P_i): Remain strictly constant across loading branches under rated operating voltage.",
                                    "Copper Losses (P_cu): Vary quadratically on loading fractions x: P_cu = x^2 * P_cu_fl.",
                                    "Condition for Maximum Efficiency: Variable copper loss equals constant iron core loss: P_cu = P_i."
                                ),
                                keyInsight = "At maximum efficiency, the load fraction x at which it occurs is: x = sqrt(P_i / P_cu_fl)."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Max Efficiency Loading Fraction",
                                    expression = "x = sqrt(P_i / P_cu_fl)",
                                    description = "Evaluates load fraction yielding maximum conversion efficiency.",
                                    applicationTrick = "If iron loss is 150W and FL copper loss is 600W, maximum efficiency occurs at exactly x_fl = sqrt(150/600) = sqrt(0.25) = 0.5 (or 50% load)."
                                )
                            ),
                            pyqs = listOf(
                                GateQuestion(
                                    id = "pyq_em_trans_1",
                                    subjectId = subjectId,
                                    topicId = "em_transformers",
                                    subtopicId = "em_trans_equiv",
                                    year = 2021,
                                    questionText = "A 10 kVA, single-phase transformer has a constant core (iron) loss of 150 W and a full-load copper loss of 600 W. At what percentage of full load does maximum efficiency occur?",
                                    questionType = QuestionType.MCQ,
                                    options = listOf("25%", "50%", "75%", "100%"),
                                    correctOptions = listOf(1),
                                    explanation = "Maximum efficiency condition: Variable Copper Loss = Constant Iron Loss.\nx^2 * P_cu_fl = P_i\nx = sqrt(P_i / P_cu_fl) = sqrt(150 / 600) = sqrt(1/4) = 0.5.\nThus, maximum efficiency occurs at 50% of the full-load rating.",
                                    formulasUsed = "x = sqrt(P_i / P_cu_fl)",
                                    shortcutTricks = "The root of 150 / 600 is root(1/4) = 0.5. Directly corresponds to 50% load fraction instantly.",
                                    relatedConcepts = "Transformer losses, efficiency curves, magnetic sizing",
                                    difficulty = "Easy"
                                )
                            ),
                            practiceQuestions = emptyList(),
                            mockQuiz = emptyList()
                        )
                    )
                ),
                Topic(
                    id = "em_dc",
                    subjectId = subjectId,
                    name = "DC Machines",
                    subtopics = listOf(
                        Subtopic(
                            id = "em_dc_motors",
                            topicId = "em_dc",
                            subjectId = subjectId,
                            name = "Motors Characteristics & Speed Control",
                            theory = TheoryContent(
                                title = "DC Motors: Shunt, Series & Regulation",
                                synopsis = "Covers operating speed-torque characteristics, back EMF, and speed regulation methods (armature series resistance, field flux control).",
                                detailedBullets = listOf(
                                    "Back EMF Equation: E_b = (P * Phi * Z * N) / (60 * A). Usually modeled as E_b = V - I_a * R_a.",
                                    "Speed Relationship: Speed N is proportional to E_b / Phi."
                                ),
                                keyInsight = "A DC Series motor must never be started on no-load because the series field current approaches zero, causing the speed to rise dangerously toward infinity."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Back EMF Voltage",
                                    expression = "E_b = V - I_a * R_a",
                                    description = "Evaluates developed internal back EMF for shunt motor.",
                                    applicationTrick = "Solve for armature current I_a using line input and field branches current values."
                                )
                            ),
                            pyqs = emptyList(),
                            practiceQuestions = emptyList(),
                            mockQuiz = emptyList()
                        )
                    )
                ),
                Topic(
                    id = "em_ac",
                    subjectId = subjectId,
                    name = "Induction & Synchronous Machines",
                    subtopics = listOf(
                        Subtopic(
                            id = "em_ac_rotary",
                            topicId = "em_ac",
                            subjectId = subjectId,
                            name = "Torque-Slip curves, Sync Speed & Alternators",
                            theory = TheoryContent(
                                title = "Rotating Fields, Alternators & Slip Factors",
                                synopsis = "Addresses rotating magnetic fields, synchronous speed equations, slip torque induction parameters, power-angle alternator grids.",
                                detailedBullets = listOf(
                                    "Synchronous Speed: N_s = 120 * f / P.",
                                    "Slip Equation: s = (N_s - N_r) / N_s. When stator field matches rotor speed, slip is zero.",
                                    "Power Angle: Alternator real power: P = (E * V / X_s) * sin(delta), where X_s is synchronous reactance."
                                ),
                                keyInsight = "An induction motor operates on a non-zero slip; operating at synchronous speed would produce zero torque as no current would be induced."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Synchronous Field Speed",
                                    expression = "N_s = 120 * f / P",
                                    description = "Calculates synchronous magnetic speed based on frequency and pole count.",
                                    applicationTrick = "Use integer pole pairs. For a 4-pole machine at 50Hz, N_s is exactly 120*50/4 = 1500 RPM."
                                )
                            ),
                            pyqs = listOf(
                                GateQuestion(
                                    id = "pyq_em_ac_1",
                                    subjectId = subjectId,
                                    topicId = "em_ac",
                                    subtopicId = "em_ac_rotary",
                                    year = 2022,
                                    questionText = "A 3-phase, 4-pole induction motor is supplied from a 50 Hz system. If the motor runs at 1440 RPM, what is the value of the rotor slip?",
                                    questionType = QuestionType.MCQ,
                                    options = listOf("2%", "4%", "5%", "6%"),
                                    correctOptions = listOf(1),
                                    explanation = "1. Calculate Synchronous Speed:\nN_s = 120 * f / P = 120 * 50 / 4 = 1500 RPM.\n2. Calculate matching Slip:\ns = (N_s - N_r) / N_s = (1500 - 1440) / 1500 = 60 / 1500 = 0.04 (or 4%).",
                                    formulasUsed = "N_s = 120*f/P, s = (N_s - N_r)/N_s",
                                    shortcutTricks = "Since 1440 RPM is close to 1500 RPM, the slip must be a low decimal: 60/1500 = 4% instantly.",
                                    relatedConcepts = "Slip torque parameters, rotating magnetic fields",
                                    difficulty = "Easy"
                                )
                            ),
                            practiceQuestions = emptyList(),
                            mockQuiz = emptyList()
                        )
                    )
                ),
                Topic(
                    id = "em_special",
                    subjectId = subjectId,
                    name = "Special Electrical Machines",
                    subtopics = listOf(
                        Subtopic(
                            id = "em_special_steppers",
                            topicId = "em_special",
                            subjectId = subjectId,
                            name = "Stepper & Servo Motors",
                            theory = TheoryContent(
                                title = "Stepper Angles, Servos & Special Transduction",
                                synopsis = "Analyzes brushless positioning devices, stepping resolution, servo feedbacks, and stepper motor operating regimes.",
                                detailedBullets = listOf(
                                    "Stepper step angle: Beta = (N_s - N_r)/(N_s * N_r) * 360 degrees.",
                                    "Microstepping: Subdivides winding phase currents continuously to achieve smaller step increments."
                                ),
                                keyInsight = "A stepper motor is an open-loop digital actuator, while a servo motor operates strictly as an encoder-based closed-loop feedback actuator."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Step Angle Equation",
                                    expression = "β = 360 / (m * N_r)",
                                    description = "Calculates stepping angular resolution for m-phases and N_r rotor teeth.",
                                    applicationTrick = "For a 2-phase stepper motor with 200 rotor segments, step angle is exactly 360 / (2 * 200) = 0.9 degrees."
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
                            formulaSheet = emptyList(),
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
                            formulaSheet = emptyList(),
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
                            formulaSheet = emptyList(),
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
                            formulaSheet = emptyList(),
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
