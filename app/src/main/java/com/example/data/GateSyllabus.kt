package com.example.data

object GateSyllabus {

    val subjects: List<Subject> by lazy {
        listOf(
            createAptitudeForAll(),
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

    private fun createEngineeringMath(): Subject {
        val subjectId = "engineering_math"
        val allMathQuestions = EngineeringMathematicsQuestions.questions

        fun getQuestions(subId: String, part: Int): List<GateQuestion> {
            val sq = allMathQuestions.filter { it.subtopicId == subId }
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
            name = "Engineering Mathematics",
            iconName = "calculate",
            topics = listOf(
                Topic(
                    id = "math_la",
                    subjectId = subjectId,
                    name = "Linear Algebra",
                    subtopics = listOf(
                        Subtopic(
                            id = "math_la_matrix_algebra",
                            topicId = "math_la",
                            subjectId = subjectId,
                            name = "Matrix Algebra & Systems of Equations",
                            theory = TheoryContent(
                                title = "Algebraic Matrix Properties & Rank Limits",
                                synopsis = "Covers fundamentals of matrix algebra, linear independent sets, transpositions, symmetric/skew-symmetric matrices, inverses, diagonalization, and nullities.",
                                detailedBullets = listOf(
                                    "Unit/Identity matrices: Identity transformation preserving core coordinate values.",
                                    "Rank of a Matrix: The maximum number of linearly independent row or column vectors.",
                                    "Inverse of a Matrix: Defined for non-singular matrices where determinant is non-zero."
                                ),
                                keyInsight = "The inverse is only defined for non-singular matrices; if the determinant is zero, no unique inverse vector mapping exists."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Transpose Product Rule",
                                    expression = "(AB)ᵀ = BᵀAᵀ",
                                    description = "Evaluates transpose properties of multiplicative matrices.",
                                    applicationTrick = "Remember the order of multiplication reverses when transposing or inverting matrix groups."
                                )
                            ),
                            pyqs = getQuestions("math_la_matrix_algebra", 0),
                            practiceQuestions = getQuestions("math_la_matrix_algebra", 1),
                            mockQuiz = getQuestions("math_la_matrix_algebra", 2)
                        ),
                        Subtopic(
                            id = "math_la_eigenvalues",
                            topicId = "math_la",
                            subjectId = subjectId,
                            name = "Eigenvalues, Eigenvectors & Cayley-Hamilton",
                            theory = TheoryContent(
                                title = "Linear Eigenvectors, Trace, and Cayley-Hamilton Theorem",
                                synopsis = "Focuses on characteristic formulations, eigenvalue scaling factors, Cayley-Hamilton operations, traces, and Hermitian matrices.",
                                detailedBullets = listOf(
                                    "Trace relation: The sum of diagonal elements matches the sum of all eigenvalues.",
                                    "Determinant relation: The product of all eigenvalues equals the matrix determinant.",
                                    "Cayley-Hamilton: Every square matrix satisfies its own characteristic quadratic polynomial equation."
                                ),
                                keyInsight = "Cayley-Hamilton is highly efficient to calculate high polynomial exponents and matrix inverses."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Characteristic Equation",
                                    expression = "det(A - λI) = 0",
                                    description = "Evaluates eigenvalues of square matrix A.",
                                    applicationTrick = "Trace(A) = Σλ_i, Det(A) = Πλ_i. Use these symmetric equations to find unknown eigenvalues instantly."
                                )
                            ),
                            pyqs = listOf(
                                GateQuestion(
                                    id = "pyq_math_la_1",
                                    subjectId = subjectId,
                                    topicId = "math_la",
                                    subtopicId = "math_la_eigenvalues",
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
                            ) + getQuestions("math_la_eigenvalues", 0),
                            practiceQuestions = getQuestions("math_la_eigenvalues", 1),
                            mockQuiz = getQuestions("math_la_eigenvalues", 2)
                        )
                    )
                ),
                Topic(
                    id = "math_calc",
                    subjectId = subjectId,
                    name = "Calculus & Vector Analysis",
                    subtopics = listOf(
                        Subtopic(
                            id = "math_calc_limits_differential",
                            topicId = "math_calc",
                            subjectId = subjectId,
                            name = "Limits, Continuity & Single-Variable Calculus",
                            theory = TheoryContent(
                                title = "Limits, Derivatives & Taylor/Maclaurin Series",
                                synopsis = "Covers differential limits, L'Hopital's rule, continuity of single variables, derivatives, optimization, and Taylor/Maclaurin series.",
                                detailedBullets = listOf(
                                    "L'Hopital's Rule: Evaluates 0/0 and inf/inf indeterminate limits by taking derivatives of the numerator and denominator.",
                                    "Continuity: A function is continuous at a if limit exists and equals the function evaluation f(a).",
                                    "Taylor Expansion: Represents functions as power series centered around any selected local point x = a."
                                ),
                                keyInsight = "Maclaurin Series represents a special case of Taylor Series where the expansion is centered exactly around x = 0."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Maclaurin Series of Exp",
                                    expression = "e\u1d6a = 1 + x + x\u00b2/2! + x\u00b3/3! + ...",
                                    description = "Evaluates the power series representation of the transcendental exponential function.",
                                    applicationTrick = "The radius of convergence for e^x, sin x, and cos x is infinite."
                                )
                            ),
                            pyqs = getQuestions("math_calc_limits_differential", 0),
                            practiceQuestions = getQuestions("math_calc_limits_differential", 1),
                            mockQuiz = getQuestions("math_calc_limits_differential", 2)
                        ),
                        Subtopic(
                            id = "math_calc_multivariable",
                            topicId = "math_calc",
                            subjectId = subjectId,
                            name = "Partial Derivations, Jacobians & Hessians",
                            theory = TheoryContent(
                                title = "Partial Derivatives, Coordinate Mappings & High Order optimization",
                                synopsis = "Covers derivatives of multiple variables, composite chain configurations, Jacobian spaces, and optimization bounds defined via Hessian matrices.",
                                detailedBullets = listOf(
                                    "Partial Derivative: Evaluates changes along a single selected axis while keeping other variables constant.",
                                    "Jacobian: Represents coordinates transformation scale factors when transposing multivariable spaces.",
                                    "Hessian: Second-order partial derivative matrix used in multivariable second derivative optimization tests."
                                ),
                                keyInsight = "The partial derivative captures localized univariate slope trajectories on complex n-dimensional surface spaces."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Chain Rule of Composites",
                                    expression = "df/dt = (\u2202f/\u2202x)*(dx/dt) + (\u2202f/\u2202y)*(dy/dt)",
                                    description = "Calculates total derivatives of multi-variable composite functions.",
                                    applicationTrick = "Trace all path dependencies in a tree structure to make sure no partial term is omitted."
                                )
                            ),
                            pyqs = getQuestions("math_calc_multivariable", 0),
                            practiceQuestions = getQuestions("math_calc_multivariable", 1),
                            mockQuiz = getQuestions("math_calc_multivariable", 2)
                        ),
                        Subtopic(
                            id = "math_calc_vector",
                            topicId = "math_calc",
                            subjectId = subjectId,
                            name = "Vector Calculus & Field Theorems",
                            theory = TheoryContent(
                                title = "Gradients, Divergence, Curl & Vector Integration Fields",
                                synopsis = "Addresses vector operators, directional derivative gradients, divergence density, curl rotations, and integral theorems of Gauss and Stokes.",
                                detailedBullets = listOf(
                                    "Gradient: Points along the steepest path of spatial scalar density elevation.",
                                    "Solenoidal: Fields with zero divergence everywhere, indicating no internal sources or sinks of flux.",
                                    "Irrotational: Fields with zero curl everywhere, implying conservative, path-independent potential configurations."
                                ),
                                keyInsight = "The curl of any gradient scalar field is always zero, representing conservative path-independent physical force areas."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Vector Divergence",
                                    expression = "div F = \u2202Fx/\u2202x + \u2202Fy/\u2202y + \u2202Fz/\u2202z",
                                    description = "Calculates local scalar expansion density of vector lines.",
                                    applicationTrick = "Gauss Divergence Theorem converts a surface integral of closed vectors into a simple volumetric integral of its divergence density."
                                )
                            ),
                            pyqs = listOf(
                                GateQuestion(
                                    id = "pyq_math_vector_1",
                                    subjectId = subjectId,
                                    topicId = "math_calc",
                                    subtopicId = "math_calc_vector",
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
                            ) + getQuestions("math_calc_vector", 0),
                            practiceQuestions = getQuestions("math_calc_vector", 1),
                            mockQuiz = getQuestions("math_calc_vector", 2)
                        )
                    )
                ),
                Topic(
                    id = "math_de",
                    subjectId = subjectId,
                    name = "Differential Equations",
                    subtopics = listOf(
                        Subtopic(
                            id = "math_de_first_order",
                            topicId = "math_de",
                            subjectId = subjectId,
                            name = "First Order Ordinary Differential Equations",
                            theory = TheoryContent(
                                title = "First-Order Classification, Integrating Factors & Variable Separability",
                                synopsis = "Addresses linear and non-linear first-order ordinary differential equations, integrating factors, exact differentials, and variables separable formulations.",
                                detailedBullets = listOf(
                                    "Integrating Factor (I.F.): Multiplier to convert a non-exact equations into exact integrable structures.",
                                    "Variables Separable: Rewriting equations to group all terms of variable x with dx and variable y with dy.",
                                    "Bernoulli Equations: Standard non-linear ODE form reducible to linear equations via variable substitutions."
                                ),
                                keyInsight = "The integrating factor of standard first-order linear ODE (y' + Py = Q) is computed as IF = exp(\u222bP dx)."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "First-Order Linear Solution",
                                    expression = "y * I.F. = \u222b (Q * I.F.) dx + C",
                                    description = "Direct solution format utilizing the integrating factor.",
                                    applicationTrick = "Ensure the coefficient of dy/dx is exactly 1 before evaluating the integrating factor."
                                )
                            ),
                            pyqs = getQuestions("math_de_first_order", 0),
                            practiceQuestions = getQuestions("math_de_first_order", 1),
                            mockQuiz = getQuestions("math_de_first_order", 2)
                        ),
                        Subtopic(
                            id = "math_de_higher_order",
                            topicId = "math_de",
                            subjectId = subjectId,
                            name = "Higher Order ODEs & Linear Systems",
                            theory = TheoryContent(
                                title = "Constant Coefficient Higher-Order Linear ODEs & Wronskian",
                                synopsis = "Deals with auxiliary algebraic equations, transient Complementary Functions, Particular Integrals, and the Wronskian linear independence test.",
                                detailedBullets = listOf(
                                    "Auxiliary Formulation: Maps linear derivative operators to algebraic variables, finding characteristic roots.",
                                    "Wronskian Test: Evaluates determinant of solutions and their derivatives to inspect linear independence coefficients.",
                                    "Transient response: Represented by the complementary function, modeling functional decays of physical setups."
                                ),
                                keyInsight = "Roots of constant-coefficient auxiliary equations determine if behavior is exponential, decaying, or oscillatory."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Repeated Auxiliary Roots CF",
                                    expression = "y_cf = (C\u2081 + C\u2082*x) * e^(m*x)",
                                    description = "Forms complementary functions under duplicated roots.",
                                    applicationTrick = "Multiply successive duplicated terms by progressive powers of x to preserve linear independence."
                                )
                            ),
                            pyqs = getQuestions("math_de_higher_order", 0),
                            practiceQuestions = getQuestions("math_de_higher_order", 1),
                            mockQuiz = getQuestions("math_de_higher_order", 2)
                        )
                    )
                ),
                Topic(
                    id = "math_complex",
                    subjectId = subjectId,
                    name = "Complex Variables",
                    subtopics = listOf(
                        Subtopic(
                            id = "math_complex_algebra_analytic",
                            topicId = "math_complex",
                            subjectId = subjectId,
                            name = "Complex Algebra & Analytic Functions",
                            theory = TheoryContent(
                                title = "Imaginary Algebra, Polar Conversions & Cauchy-Riemann equations",
                                synopsis = "Studies continuous complex values, magnitude modulations, polar shapes, complex conjugates, and Cauchy-Riemann constraints.",
                                detailedBullets = listOf(
                                    "Cauchy-Riemann Equations: u_x = v_y and u_y = -v_x are necessary conditions for differentiability.",
                                    "Conjugates: Obtained by flipping the sign of the imaginary coefficient to find polar and modulus configurations.",
                                    "Analyticity: Implies functional differentiability throughout complete open complex coordinate regions."
                                ),
                                keyInsight = "Cauchy-Riemann equations can be checked to ensure if a complex vector field matches source-free physical configurations."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Euler's Formula Relation",
                                    expression = "e^(j\u03b8) = cos \u03b8 + j sin \u03b8",
                                    description = "Relates imaginary complex exponents with real trigonometric projections.",
                                    applicationTrick = "Evaluate polar parameters: |z| = r, and the principal angle is Arg(z) = atan2(y, x)."
                                )
                            ),
                            pyqs = getQuestions("math_complex_algebra_analytic", 0),
                            practiceQuestions = getQuestions("math_complex_algebra_analytic", 1),
                            mockQuiz = getQuestions("math_complex_algebra_analytic", 2)
                        ),
                        Subtopic(
                            id = "math_complex_cauchy_residue",
                            topicId = "math_complex",
                            subjectId = subjectId,
                            name = "Cauchy's Integration & Residue Theorems",
                            theory = TheoryContent(
                                title = "Contour Integrations, Isolated poles & Residue calculations",
                                synopsis = "Studies path integrals around complex curves, evaluating residues at isolated poles, zeroes, and essential singularities.",
                                detailedBullets = listOf(
                                    "Zeroes and Poles: Points where functions evaluate to zero or approach local infinities.",
                                    "Poles and Residues: The coefficient of the (z-a)^-1 Laurent series expansion term represents the residue.",
                                    "Cauchy Integration: Maps cyclic integrals to simple functions of interior loop residue totals."
                                ),
                                keyInsight = "Poles residing strictly outside the circular integration path contour boundary do not contribute residue values."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Cauchy's Residue Integration Theorem",
                                    expression = "\u222e_C f(z) dz = 2 * \u03c0 * j * \u2211 Residues",
                                    description = "Computes contour integrals via residues inside the contour loop C.",
                                    applicationTrick = "For simple poles at z = a, Residue = lim_{z->a} [ (z - a) * f(z) ]."
                                )
                            ),
                            pyqs = listOf(
                                GateQuestion(
                                    id = "pyq_math_complex_1",
                                    subjectId = subjectId,
                                    topicId = "math_complex",
                                    subtopicId = "math_complex_cauchy_residue",
                                    year = 2021,
                                    questionText = "Evaluate the contour integral I = \u222e_C [ 1 / (z^2 - 1) ] dz, along the circular contour C defined as |z| = 1.5, mapped counter-clockwise.",
                                    questionType = QuestionType.MCQ,
                                    options = listOf("0", "\u03c0 * j", "2 * \u03c0 * j", "0.5 * \u03c0 * j"),
                                    correctOptions = listOf(0),
                                    explanation = "Integrand f(z) = 1 / [(z-1)(z+1)] has simple poles at z = 1 and z = -1.\nBoth poles lie inside the circular boundary C of radius 1.5.\nResidue at z = 1: lim_{z->1} [ (z-1) / ((z-1)(z+1)) ] = 1/2 = 0.5.\nResidue at z = -1: lim_{z->-1} [ (z+1) / ((z-1)(z+1)) ] = -1/2 = -0.5.\nSum of Residues = 0.5 + (-0.5) = 0.\nI = 2 * pi * j * (Sum of residues) = 2 * pi * j * (0) = 0.",
                                    formulasUsed = "Res_f(a) = lim_{z->a} (z-a)f(z), Integral = 2*pi*j*Sum(Res)",
                                    shortcutTricks = "Notice that the integrand is an even symmetric function with singularities balanced symmetrically on both sides of the origin inside the symmetric boundary. Their residues cancel out, giving 0.",
                                    relatedConcepts = "Contours integration, isolated residues",
                                    difficulty = "Medium"
                                )
                            ) + getQuestions("math_complex_cauchy_residue", 0),
                            practiceQuestions = getQuestions("math_complex_cauchy_residue", 1),
                            mockQuiz = getQuestions("math_complex_cauchy_residue", 2)
                        )
                    )
                ),
                Topic(
                    id = "math_probability",
                    subjectId = subjectId,
                    name = "Probability & Statistics",
                    subtopics = listOf(
                        Subtopic(
                            id = "math_probability_basics",
                            topicId = "math_probability",
                            subjectId = subjectId,
                            name = "Probability Theory & Bayes Theorem",
                            theory = TheoryContent(
                                title = "Sample Spaces, Conditional Probability & Bayes Updates",
                                synopsis = "Highlights basic sample spaces, axioms of probability, independent/mutually-exclusive events, conditional probabilities, and Bayes' Theorem updates.",
                                detailedBullets = listOf(
                                    "Axioms: All individual probabilities are strictly bounded within the range [0, 1]. Sum of all sample space outcomes is exactly 1.",
                                    "Independent Events: Multiplication rule P(A \u2229 B) = P(A) * P(B).",
                                    "Conditional Probability: Probability of event A occurring given B has already occurred: P(A|B) = P(A \u2229 B) / P(B)."
                                ),
                                keyInsight = "Bayes Theorem acts as a mathematical bridge to update prior probabilities dynamically as updated test results arrive."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Bayes Theorem Relation",
                                    expression = "P(A|B) = P(B|A)*P(A) / P(B)",
                                    description = "Evaluates posterior probability using likelihood parameters.",
                                    applicationTrick = "Denominator P(B) represents the total probability of event B across all mutually exclusive partitions."
                                )
                            ),
                            pyqs = getQuestions("math_probability_basics", 0),
                            practiceQuestions = getQuestions("math_probability_basics", 1),
                            mockQuiz = getQuestions("math_probability_basics", 2)
                        ),
                        Subtopic(
                            id = "math_probability_distributions",
                            topicId = "math_probability",
                            subjectId = subjectId,
                            name = "Random Variables & Probability Distributions",
                            theory = TheoryContent(
                                title = "Expected Values, PDF, CDF & Common distribution models",
                                synopsis = "Covers discrete and continuous random variables, expectations, variance properties, standard deviations, Binomial, Poisson, and Normal distributions.",
                                detailedBullets = listOf(
                                    "Expected value: The center of mass of distribution. E[X] = \u2211 x * P(x) for discrete systems.",
                                    "Variance: Measures spread around the mean. Var(X) = E[X\u00b2] - (E[X])\u00b2.",
                                    "Normal/Gaussian Distribution: Standard symmetric bell-shaped model described fully by its mean and variance."
                                ),
                                keyInsight = "For standard normal distributions, the mean is exactly 0 and the variance is exactly 1."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Poisson Distribution Probability",
                                    expression = "P(X = k) = (\u03bb^k * e^-\u03bb) / k!",
                                    description = "Calculates the probability of k occurrences within a fixed interval given mean rate \u03bb.",
                                    applicationTrick = "For Poisson variables, both the mean and the variance are exactly equal to \u03bb."
                                )
                            ),
                            pyqs = getQuestions("math_probability_distributions", 0),
                            practiceQuestions = getQuestions("math_probability_distributions", 1),
                            mockQuiz = getQuestions("math_probability_distributions", 2)
                        )
                    )
                ),
                Topic(
                    id = "math_numerical",
                    subjectId = subjectId,
                    name = "Numerical Methods",
                    subtopics = listOf(
                        Subtopic(
                            id = "math_numerical_root_finding",
                            topicId = "math_numerical",
                            subjectId = subjectId,
                            name = "Non-linear Equations & Root-Finding Methods",
                            theory = TheoryContent(
                                title = "Root Convergence, Bracketing & Slopes approximation",
                                synopsis = "Examines root-finding iterative mechanics on non-linear equations including Bisection, Regula-Falsi, Secant, and high-speed Newton-Raphson methods.",
                                detailedBullets = listOf(
                                    "Bisection Method: Safe bracketing method needing initial endpoints with sign changes; converges slowly but guaranteed.",
                                    "Secant Method: Approximates local tangent lines via double initial secants, removing derivative requirements.",
                                    "Newton-Raphson: Quadratic local convergence speed, utilizing local tangent derivatives."
                                ),
                                keyInsight = "If the derivative f'(x) equals zero or values hover close to local extremas, Newton-Raphson diverges."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Newton-Raphson Iteration Step",
                                    expression = "x_{n+1} = x_{n} - f(x_{n}) / f'(x_{n})",
                                    description = "Finds sequential local approximations of non-linear functions.",
                                    applicationTrick = "The error convergence of the Newton-Raphson method is quadratic (order 2)."
                                )
                            ),
                            pyqs = getQuestions("math_numerical_root_finding", 0),
                            practiceQuestions = getQuestions("math_numerical_root_finding", 1),
                            mockQuiz = getQuestions("math_numerical_root_finding", 2)
                        ),
                        Subtopic(
                            id = "math_numerical_integration",
                            topicId = "math_numerical",
                            subjectId = subjectId,
                            name = "Numerical Integration & Interpolation",
                            theory = TheoryContent(
                                title = "Approximate Integrations, Interpolations & Polynomial fitting",
                                synopsis = "Highlights numerical estimation of definite integration using Trapezoidal, Simpson's 1/3 rules, Lagrange interpolating polynomials, and boundary differences.",
                                detailedBullets = listOf(
                                    "Trapezoidal Rule: Approximates area under a curve using linear trapezoids; error is proportional to h\u00b2.",
                                    "Simpson's 1/3 Rule: Fits quadratic functions; requires even interval subsets and has error proportional to h\u2074.",
                                    "Lagrange Interpolation: Fits unique polynomials seamlessly passing through arbitrary scattered data coordinate points."
                                ),
                                keyInsight = "Simpson's 1/3 rule is exact for polynomials up to degree 3, despite fitting quadratic slices."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Trapezoidal Definite Integration Rule",
                                    expression = "\u222b f(x) dx \u2248 (h/2) * [ (y0 + yn) + 2*(y1 + ... + y_{n-1}) ]",
                                    description = "Evaluates definite area under continuous tabular coordinates.",
                                    applicationTrick = "h represents the width of the interval: h = (b - a) / n."
                                )
                            ),
                            pyqs = getQuestions("math_numerical_integration", 0),
                            practiceQuestions = getQuestions("math_numerical_integration", 1),
                            mockQuiz = getQuestions("math_numerical_integration", 2)
                        )
                    )
                )
            )
        )
    }

    private fun createNetworkTheory(): Subject {
        val subjectId = "network_theory"
        val allNtQuestions = NetworkTheoryQuestions.questions

        fun getQuestions(subId: String, part: Int): List<GateQuestion> {
            val sq = allNtQuestions.filter { it.subtopicId == subId }
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
            name = "Network Theory",
            iconName = "settings_ethernet",
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
                                )
                            ),
                            pyqs = getQuestions("nt_laws_basics", 0),
                            practiceQuestions = getQuestions("nt_laws_basics", 1),
                            mockQuiz = getQuestions("nt_laws_basics", 2)
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
                            name = "Thevenin, Norton, Superposition & Maximum Power",
                            theory = TheoryContent(
                                title = "Equivalizing Complex Active Linear Networks",
                                synopsis = "Thevenin and Norton theorems model active circuits using a single source and a series/parallel equivalent impedance representing terminal behaviors.",
                                detailedBullets = listOf(
                                    "Thevenin Voltage V_th: The open-circuit potential across target load terminals.",
                                    "Norton Current I_n: The short-circuit current across terminals.",
                                    "Equivalent Resistance R_th: Measured internally with all independent sources deactivated.",
                                    "Maximum Power Transfer Theorem: Accomplished when load impedance is the complex conjugate of the source impedance."
                                ),
                                keyInsight = "When dependent sources are present, R_th must be found by deactivating independent sources and applying an external test source (e.g., 1V or 1A) at the terminals."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Thevenin-Norton Equivalence",
                                    expression = "V_th = I_n * R_th",
                                    description = "Connects equivalent circuit models via standard source transformations.",
                                    applicationTrick = "Calculate short-circuit current and open-circuit voltage to find R_th = V_th / I_n instantly."
                                )
                            ),
                            pyqs = getQuestions("nt_theorems_active", 0),
                            practiceQuestions = getQuestions("nt_theorems_active", 1),
                            mockQuiz = getQuestions("nt_theorems_active", 2)
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
                            name = "First & Second Order Transient Response",
                            theory = TheoryContent(
                                title = "State Transitions & Circuit Energy Dynamics",
                                synopsis = "Evaluates circuit currents and voltage transitions when switching operations alter network configurations.",
                                detailedBullets = listOf(
                                    "Inductor current continuity: i_L(0+) = i_L(0-). Direct rate changes require infinite voltages.",
                                    "Capacitor voltage continuity: v_C(0+) = v_C(0-). Direct rate changes require infinite currents.",
                                    "Switching action at t=0: Uncharged capacitors act as short circuits, and unenergized inductors act as open circuits."
                                ),
                                keyInsight = "A first-order transient decays to 36.8% (1/e) of its initial displacement step at exactly t = one time constant."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "First Order Response Formula",
                                    expression = "x(t) = x(∞) + [ x(0+) - x(∞) ] * e^(-t/τ)",
                                    description = "Evaluates transient parameters at any time t.",
                                    applicationTrick = "RL series tau = L/R; RC series tau = R*C."
                                )
                            ),
                            pyqs = getQuestions("nt_trans_response", 0),
                            practiceQuestions = getQuestions("nt_trans_response", 1),
                            mockQuiz = getQuestions("nt_trans_response", 2)
                        )
                    )
                ),
                Topic(
                    id = "nt_ac_steady",
                    subjectId = subjectId,
                    name = "AC Steady-State Analysis",
                    subtopics = listOf(
                        Subtopic(
                            id = "nt_ac_steady_state",
                            topicId = "nt_ac_steady",
                            subjectId = subjectId,
                            name = "Phasors, Sinusoidal Steady-State & Complex Power",
                            theory = TheoryContent(
                                title = "AC Sinusoidal Steady-State Response",
                                synopsis = "Deals with phase relations, impedance and admittance, real, reactive, and apparent power in AC networks.",
                                detailedBullets = listOf(
                                    "Phasor Representation: Transforming sinusoidal voltages and currents from time-domain to complex frequency domain.",
                                    "Active/Real Power (P): Dissipated entirely by resistive components, measured in Watts.",
                                    "Reactive Power (Q): Alternates storage in magnetic/electric fields, measured in VAR.",
                                    "Apparent Power (S): The total complex power vector, measured in VA."
                                ),
                                keyInsight = "The complex power is represented as S = V * I*, where I* is the complex conjugate of the phasor current."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Complex Power Equation",
                                    expression = "S = P + jQ = V_rms * I_rms*",
                                    description = "Expresses total power as a combination of real active power and imaginary reactive power.",
                                    applicationTrick = "Always use the complex conjugate of current to ensure lagging current produces inductive (positive) reactive power."
                                )
                            ),
                            pyqs = getQuestions("nt_ac_steady_state", 0),
                            practiceQuestions = getQuestions("nt_ac_steady_state", 1),
                            mockQuiz = getQuestions("nt_ac_steady_state", 2)
                        )
                    )
                ),
                Topic(
                    id = "nt_resonance_coupled",
                    subjectId = subjectId,
                    name = "Resonance & Coupled Circuits",
                    subtopics = listOf(
                        Subtopic(
                            id = "nt_resonance_coupled_circuits",
                            topicId = "nt_resonance_coupled",
                            subjectId = subjectId,
                            name = "Series & Parallel Resonance, Magnetic Coupling",
                            theory = TheoryContent(
                                title = "Resonance & Magnetically Coupled Networks",
                                synopsis = "Details resonance parameters like selectivity, bandwidth, and dot-convention configurations for mutually coupled coils.",
                                detailedBullets = listOf(
                                    "Resonant Frequency: Canceling capacitive/inductive reactances, resulting in unity power factor.",
                                    "Bandwidth: The range of frequencies over which the power is at least 50% of its maximum value.",
                                    "Mutual Inductance: Polarity represented via dots defining positive/negative flux linkages."
                                ),
                                keyInsight = "At parallel resonance, input impedance is maximized (Z = R) and current is minimized, acting as a band-rejection mechanism."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Resonant Frequency",
                                    expression = "f_0 = 1 / (2 * π * sqrt(L * C))",
                                    description = "Formulation for natural undamped resonance in series/parallel LC networks.",
                                    applicationTrick = "Quality factor Q acts as the voltage magnification index inside series RLC resonance."
                                )
                            ),
                            pyqs = getQuestions("nt_resonance_coupled_circuits", 0),
                            practiceQuestions = getQuestions("nt_resonance_coupled_circuits", 1),
                            mockQuiz = getQuestions("nt_resonance_coupled_circuits", 2)
                        )
                    )
                ),
                Topic(
                    id = "nt_twoport",
                    subjectId = subjectId,
                    name = "Two-Port Networks",
                    subtopics = listOf(
                        Subtopic(
                            id = "nt_parameters_twoport",
                            topicId = "nt_twoport",
                            subjectId = subjectId,
                            name = "Z, Y, ABCD & Hybrid Parameters",
                            theory = TheoryContent(
                                title = "Two-Port Matrix Characterizations",
                                synopsis = "Employs open-circuit, short-circuit, transmission, and hybrid matrices to characterize network parameters.",
                                detailedBullets = listOf(
                                    "Z-parameters: Relate terminal voltages to currents under open-circuit conditions.",
                                    "Y-parameters: Relate terminal currents to voltages under short-circuit conditions.",
                                    "ABCD-parameters: Model cascaded connection networks by expressing ports transmission variables."
                                ),
                                keyInsight = "Reciprocal networks must verify Z12=Z21, Y12=Y21, h12=-h21, and AD-BC=1. Symmetric networks require Z11=Z22, Y11=Y22, h11*h22-h12*h21=1, and A=D."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Z & Y Conversions",
                                    expression = "[Y] = [Z]^-1",
                                    description = "Expresses admittance matrices as the exact inverse of impedance matrices.",
                                    applicationTrick = "Ensure the determinant of [Z] is non-zero before trying to directly compute [Y] parameters."
                                )
                            ),
                            pyqs = getQuestions("nt_parameters_twoport", 0),
                            practiceQuestions = getQuestions("nt_parameters_twoport", 1),
                            mockQuiz = getQuestions("nt_parameters_twoport", 2)
                        )
                    )
                ),
                Topic(
                    id = "nt_graph",
                    subjectId = subjectId,
                    name = "Network Graph Theory",
                    subtopics = listOf(
                        Subtopic(
                            id = "nt_graph_theory",
                            topicId = "nt_graph",
                            subjectId = subjectId,
                            name = "Trees, Tiesets, Cutsets & Incidence Matrix",
                            theory = TheoryContent(
                                title = "Topological Network Characterization",
                                synopsis = "Formulates incidence matrices, fundamental tie-set loops, cut-set matrices, and twigs relative to tree properties.",
                                detailedBullets = listOf(
                                    "Tree: A connected subgraph containing all nodes of the graph but no loops.",
                                    "Twigs: Tree branches. In a graph of N nodes, number of twigs = N - 1.",
                                    "Links: Branches belonging to the co-tree, forming fundamental tie-set loops."
                                ),
                                keyInsight = "The total number of independent KVL equations matches the number of link elements: B - N + 1."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Fundamental loops formula",
                                    expression = "Loops = B - N + 1",
                                    description = "Gives the total number of independent loops or link configurations.",
                                    applicationTrick = "For planar networks, this is exactly equal to the number of individual mesh compartments."
                                )
                            ),
                            pyqs = getQuestions("nt_graph_theory", 0),
                            practiceQuestions = getQuestions("nt_graph_theory", 1),
                            mockQuiz = getQuestions("nt_graph_theory", 2)
                        )
                    )
                )
            )
        )
    }

    private fun createSignalsAndSystems(): Subject {
        val subjectId = "signals_systems"
        val allSigQuestions = SignalsAndSystemsQuestions.questions

        fun getSigQuestions(subId: String, part: Int): List<GateQuestion> {
            val sq = allSigQuestions.filter { it.subtopicId == subId }
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
                            pyqs = getSigQuestions("sig_lti_convolution", 0),
                            practiceQuestions = getSigQuestions("sig_lti_convolution", 1),
                            mockQuiz = getSigQuestions("sig_lti_convolution", 2)
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
                            pyqs = getSigQuestions("sig_sampling_rate", 0),
                            practiceQuestions = getSigQuestions("sig_sampling_rate", 1),
                            mockQuiz = getSigQuestions("sig_sampling_rate", 2)
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
        val allPsQuestions = PowerSystemsQuestions.questions

        fun getQuestions(subId: String, part: Int): List<GateQuestion> {
            val sq = allPsQuestions.filter { it.subtopicId == subId }
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
                            pyqs = getQuestions("ps_econ_dispatch", 0),
                            practiceQuestions = getQuestions("ps_econ_dispatch", 1),
                            mockQuiz = getQuestions("ps_econ_dispatch", 2)
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
                            pyqs = getQuestions("ps_trans_lines", 0),
                            practiceQuestions = getQuestions("ps_trans_lines", 1),
                            mockQuiz = getQuestions("ps_trans_lines", 2)
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
                            pyqs = getQuestions("ps_flow_faults", 0),
                            practiceQuestions = getQuestions("ps_flow_faults", 1),
                            mockQuiz = getQuestions("ps_flow_faults", 2)
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
                            pyqs = getQuestions("ps_prot_stability", 0),
                            practiceQuestions = getQuestions("ps_prot_stability", 1),
                            mockQuiz = getQuestions("ps_prot_stability", 2)
                        )
                    )
                )
            )
        )
    }

    private fun createPowerElectronics(): Subject {
        val subjectId = "power_electronics"
        val allPeQuestions = PowerElectronicsQuestions.questions

        fun getPeQuestions(subId: String, part: Int): List<GateQuestion> {
            val sq = allPeQuestions.filter { it.subtopicId == subId }
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
                            pyqs = getPeQuestions("pe_device_char", 0),
                            practiceQuestions = getPeQuestions("pe_device_char", 1),
                            mockQuiz = getPeQuestions("pe_device_char", 2)
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
                            pyqs = getPeQuestions("pe_conv_buck_boost", 0),
                            practiceQuestions = getPeQuestions("pe_conv_buck_boost", 1),
                            mockQuiz = getPeQuestions("pe_conv_buck_boost", 2)
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
                            pyqs = getPeQuestions("pe_drives_char", 0),
                            practiceQuestions = getPeQuestions("pe_drives_char", 1),
                            mockQuiz = getPeQuestions("pe_drives_char", 2)
                        )
                    )
                )
            )
        )
    }

    private fun createAnalogElectronics(): Subject {
        val subjectId = "analog_electronics"
        val allAeQuestions = AnalogElectronicsQuestions.questions

        fun getAeQuestions(subId: String, part: Int): List<GateQuestion> {
            val sq = allAeQuestions.filter { it.subtopicId == subId }
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
                            pyqs = getAeQuestions("ae_dio_bias", 0),
                            practiceQuestions = getAeQuestions("ae_dio_bias", 1),
                            mockQuiz = getAeQuestions("ae_dio_bias", 2)
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
                            pyqs = getAeQuestions("ae_amp_feedback", 0),
                            practiceQuestions = getAeQuestions("ae_amp_feedback", 1),
                            mockQuiz = getAeQuestions("ae_amp_feedback", 2)
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
                            pyqs = getAeQuestions("ae_opamp_apps", 0),
                            practiceQuestions = getAeQuestions("ae_opamp_apps", 1),
                            mockQuiz = getAeQuestions("ae_opamp_apps", 2)
                        )
                    )
                )
            )
        )
    }

    private fun createDigitalElectronics(): Subject {
        val subjectId = "digital_electronics"
        val allDeQuestions = DigitalElectronicsQuestions.questions

        fun getDeQuestions(subId: String, part: Int): List<GateQuestion> {
            val sq = allDeQuestions.filter { it.subtopicId == subId }
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
            name = "Digital Electronics",
            iconName = "memory",
            topics = listOf(
                Topic(
                    id = "de_bases_gates",
                    subjectId = subjectId,
                    name = "Number Systems & Gates",
                    subtopics = listOf(
                        Subtopic(
                            id = "de_number_systems",
                            topicId = "de_bases_gates",
                            subjectId = subjectId,
                            name = "Number Systems & Codes",
                            theory = TheoryContent(
                                title = "Radix Representation, Complements & Logic Codes",
                                synopsis = "Addresses radix numeric representations (binary, octal, decimal, hex), standard sign-magnitude representations, 1's and 2's complements, BCD codes, excess-3, and single-transition Gray code.",
                                detailedBullets = listOf(
                                    "Decimal-Binary conversion: Successive division/multiplication by 2 yields discrete system digits.",
                                    "Complements: r's and (r-1)'s complements allow subtraction through simple adder hardware.",
                                    "Gray Code: A unit-distance non-weighted code primarily utilized in encoders to minimize transition hazards."
                                ),
                                keyInsight = "Successive numbers in Gray code differ by exactly one binary bit, dramatically reducing multi-bit synchronization issues in dynamic sensing."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "2's Complement",
                                    expression = "2's Complement = 1's Complement + 1",
                                    description = "Evaluates signed integer representations.",
                                    applicationTrick = "Leave the least significant zeros and the trailing first '1' unchanged; invert all other preceding bits to save time."
                                )
                            ),
                            pyqs = getDeQuestions("de_number_systems", 0),
                            practiceQuestions = getDeQuestions("de_number_systems", 1),
                            mockQuiz = getDeQuestions("de_number_systems", 2)
                        ),
                        Subtopic(
                            id = "de_boolean_gates",
                            topicId = "de_bases_gates",
                            subjectId = subjectId,
                            name = "Boolean Algebra & Logic Gates",
                            theory = TheoryContent(
                                title = "Boolean Minimization & Universal Gates",
                                synopsis = "Covers fundamental Boolean identity theorems, consensus logic, absorption laws, De Morgan's mathematical conversions, and universal NAND/NOR logic networks.",
                                detailedBullets = listOf(
                                    "De Morgan's Theorems: Converts products to sums and vice versa under global logical negation.",
                                    "Consensus Theorem: AB + A'C + BC simplifies to AB + A'C, eliminating redundant adjacent transitions.",
                                    "Universal Logic: Universal NAND and NOR gates can implement complete boolean spaces singly."
                                ),
                                keyInsight = "A NAND or NOR gate can function as a simple inverter by tying its inputs together or to active logic levels."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "De Morgan's Law of Products",
                                    expression = "(A * B)' = A' + B'",
                                    description = "Converts double negative products to simple negated additions.",
                                    applicationTrick = "'Break the negation bar, change the operator sign' to convert Boolean structures effortlessly."
                                )
                            ),
                            pyqs = getDeQuestions("de_boolean_gates", 0),
                            practiceQuestions = getDeQuestions("de_boolean_gates", 1),
                            mockQuiz = getDeQuestions("de_boolean_gates", 2)
                        )
                    )
                ),
                Topic(
                    id = "de_combinational",
                    subjectId = subjectId,
                    name = "Combinational Circuits",
                    subtopics = listOf(
                        Subtopic(
                            id = "de_combinational",
                            topicId = "de_combinational",
                            subjectId = subjectId,
                            name = "Multiplexers, Decoders & Arithmetic Logic",
                            theory = TheoryContent(
                                title = "Arithmetic Systems & Data Selectors",
                                synopsis = "Focuses on Karnaugh Maps (K-maps), SOP/POS representations, half/full adders and subtractors, decoders/encoders, and multiplexers (MUX) functioning as universal synthesizers.",
                                detailedBullets = listOf(
                                    "K-Map Optimization: Groups prime implicants in powers of 2 to yield minimal minimal literal SOP/POS statements.",
                                    "Adders: Ripple carry adders propagate carry lines sequentially, whereas Carry Look-Ahead systems predict secondary lines.",
                                    "Multiplexers: Act as multi-channel data selectors. An 2^n:1 MUX functions as a universal boolean generator."
                                ),
                                keyInsight = "An n-to-2^n decoder with active-low outputs yields minterms of input variables directly, which can form any logic sum using external logic."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Full Adder Carry Equation",
                                    expression = "C_out = A*B + C_in*(A ⊕ B)",
                                    description = "Evaluates ripple carry logic transitions.",
                                    applicationTrick = "In look-ahead adders, write as G_i + P_i * C_i where G_i = A*B (generate) and P_i = A+B (propagate)."
                                )
                            ),
                            pyqs = getDeQuestions("de_combinational", 0),
                            practiceQuestions = getDeQuestions("de_combinational", 1),
                            mockQuiz = getDeQuestions("de_combinational", 2)
                        ),
                        Subtopic(
                            id = "de_data_converters",
                            topicId = "de_combinational",
                            subjectId = subjectId,
                            name = "Data Converters (ADC & DAC)",
                            theory = TheoryContent(
                                title = "Analog-to-Digital & Digital-to-Analog Converters",
                                synopsis = "Covers R-2R ladders, weighted resistor DACs, flash ADCs, successive approximation ADCs, dual-slope integrations, resolution, and quantization margins.",
                                detailedBullets = listOf(
                                    "Resolution: Defined as step-size V_ref / (2^n - 1) representing minimal analog levels.",
                                    "Flash ADC: Employs (2^n - 1) simultaneous comparators to yield high speed conversions within one clock cycle.",
                                    "Successive Approximation (SAR): Employs a binary search ladder via a internal feedback DAC over exactly n clock cycles."
                                ),
                                keyInsight = "Dual-slope ADC offers superior noise immunity because it integrates inputs over configured cycles, cancelling out harmonic high frequency lines."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "DAC Output Voltage",
                                    expression = "V_out = V_ref * (Digital_Value / 2^n)",
                                    description = "Calculates converted analog voltages from binary values.",
                                    applicationTrick = "Treat resolution carefully when computing outputs for full-scale versus standard code conversions."
                                )
                            ),
                            pyqs = getDeQuestions("de_data_converters", 0),
                            practiceQuestions = getDeQuestions("de_data_converters", 1),
                            mockQuiz = getDeQuestions("de_data_converters", 2)
                        )
                    )
                ),
                Topic(
                    id = "de_sequential",
                    subjectId = subjectId,
                    name = "Sequential Circuits",
                    subtopics = listOf(
                        Subtopic(
                            id = "de_sequential",
                            topicId = "de_sequential",
                            subjectId = subjectId,
                            name = "Flip-Flops, Counters & State Machines",
                            theory = TheoryContent(
                                title = "State Memories, Counters & Finite State Machines",
                                synopsis = "Addresses latches, edge-triggered flip-flops, excitation/characteristic equations, ripple/synchronous counters, shift registers, and Moore/Mealy FSM controllers.",
                                detailedBullets = listOf(
                                    "Race-Around: Prevents safe toggling in JK flip-flops when active state overlaps clock high pulse; solved via Master-Slave FFs.",
                                    "Binary Counters: N flip-flops sequence through MOD-2^N states. Decoders clear states to build arbitrary MOD boundaries.",
                                    "FSM Models: Mealy outputs depend on inputs and current states; Moore outputs depend strictly on the current state."
                                ),
                                keyInsight = "Mealy machines react immediately to input changes in an asynchronous manner, while Moore machines synchronize fully with active clock edges."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "JK Flip-Flop Characteristic",
                                    expression = "Q_next = J*Q'_present + K'*Q_present",
                                    description = "Computes the next state of a JK flip flop.",
                                    applicationTrick = "Setting J=1, K=1 causes the state to toggle (invert) on the active transition edge."
                                )
                            ),
                            pyqs = getDeQuestions("de_sequential", 0),
                            practiceQuestions = getDeQuestions("de_sequential", 1),
                            mockQuiz = getDeQuestions("de_sequential", 2)
                        ),
                        Subtopic(
                            id = "de_logic_families_memory",
                            topicId = "de_sequential",
                            subjectId = subjectId,
                            name = "Logic Families & Semiconductor Memory",
                            theory = TheoryContent(
                                title = "TTL/CMOS/ECL Logic & Semiconductor RAM/ROM",
                                synopsis = "Compares digital gate logic technologies (TTL, CMOS, ECL), fan-out boundaries, setup/hold timings, metastabilities, static/dynamic RAM cells, and non-volatile ROM memories.",
                                detailedBullets = listOf(
                                    "CMOS Logic: Negligible static power dissipation due to complementary NMOS/PMOS configurations.",
                                    "ECL (Emitter Coupled Logic): Avoids transistor saturation, offering the absolute fastest switching speeds.",
                                    "SRAM vs DRAM: SRAM employs bistable latch circuits for storage; DRAM uses charge capacitors requiring periodic refreshing."
                                ),
                                keyInsight = "Violating setup or hold times at a flip-flop's input boundary results in metastability, where the output hovers indefinitely between 0 and 1."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Active Delay Limit",
                                    expression = "T_period >= T_co + T_comb + T_setup",
                                    description = "Estimates the minimum clock period to prevent setup violations.",
                                    applicationTrick = "Minimize combination logic depth (T_comb) to scale system clocks to higher frequencies."
                                )
                            ),
                            pyqs = getDeQuestions("de_logic_families_memory", 0),
                            practiceQuestions = getDeQuestions("de_logic_families_memory", 1),
                            mockQuiz = getDeQuestions("de_logic_families_memory", 2)
                        )
                    )
                )
            )
        )
    }

    private fun createElectromagneticTheory(): Subject {
        val subjectId = "electromagnetic_theory"
        val allEmfQuestions = ElectromagneticFieldsQuestions.questions

        fun getEmfQuestions(subId: String, part: Int): List<GateQuestion> {
            val sq = allEmfQuestions.filter { it.subtopicId == subId }
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
            name = "Electromagnetic Fields",
            iconName = "explore",
            topics = listOf(
                Topic(
                    id = "em_statics",
                    subjectId = subjectId,
                    name = "Electrostatics & Magnetostatics",
                    subtopics = listOf(
                        Subtopic(
                            id = "em_electrostatics",
                            topicId = "em_statics",
                            subjectId = subjectId,
                            name = "Electrostatics, Coulomb's Law & Capacitance",
                            theory = TheoryContent(
                                title = "Electrostatics Fields & Capacitors",
                                synopsis = "Investigates charges at rest, force fields, voltage gradients, and electrical storage parameters.",
                                detailedBullets = listOf(
                                    "Coulomb's Law: Force between point charges varies inversely with the square of the distance separator.",
                                    "Electric Potential: Defined as work per unit charge, negative gradient of potential yields electric field intensity.",
                                    "Capacitance: Proportional to the plate cross-sectional area and plate permittivity, and inversely to gap separation distance.",
                                    "Boundary Conditions: Tangential electric field component is always continuous across dielectric interfaces."
                                ),
                                keyInsight = "Adding standard dielectric material between capacitor plates increases field storage capacity relative to the material's dielectric constant."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Electric Field Intensity due to Point Charge",
                                    expression = "E = Q / (4 * pi * ε * r²)",
                                    description = "Evaluates electrostatic field strength at a radial distance from a point charge.",
                                    applicationTrick = "Symmetric spherical systems simplify integration under Gauss's law."
                                ),
                                FormulaItem(
                                    name = "Energy in Capacitance",
                                    expression = "W_E = ½ * C * V²",
                                    description = "Tracks work stored inside electrostatic fields between capacitor plates.",
                                    applicationTrick = "Alternatively expressed as ½ * Q * V or Q² / (2 * C)."
                                )
                            ),
                            pyqs = getEmfQuestions("em_electrostatics", 0),
                            practiceQuestions = getEmfQuestions("em_electrostatics", 1),
                            mockQuiz = getEmfQuestions("em_electrostatics", 2)
                        ),
                        Subtopic(
                            id = "em_magnetostatics",
                            topicId = "em_statics",
                            subjectId = subjectId,
                            name = "Magnetostatics, Ampere's Law & Inductance",
                            theory = TheoryContent(
                                title = "Magnetostatic Fields & Induced Fluxes",
                                synopsis = "Examines magnetic potentials, currents in filaments, Ampere circuital paths, and inductance attributes.",
                                detailedBullets = listOf(
                                    "Biot-Savart Law: Determines differential flux density vector proportional to current line vectors.",
                                    "Ampere's Law: Boundary integrals of H equal the net enclosed conduction current passing through the surface loop.",
                                    "Inductance: Represents direct flux linkage per driving excitation current unit stored inside the core.",
                                    "Boundary Conditions: Normal density components are continuous, but tangential H changes based on sheet current density."
                                ),
                                keyInsight = "Reluctance serves as the magnetic path analog to electrical resistance, restricting circulating fluxes through the circuit core."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Ampere's Circuital Integral",
                                    expression = "∮ H · dl = I_enclosed",
                                    description = "Differential and integral relationship matching magnetic intensity loops to driving currents.",
                                    applicationTrick = "Highly useful for symmetric current systems like coaxial Cables or infinitely long wires."
                                ),
                                FormulaItem(
                                    name = "Energy inside Inductance",
                                    expression = "W_H = ½ * L * I²",
                                    description = "Computes work stored within magnetic fields created inside coil conductors.",
                                    applicationTrick = "Relates stored power directly to coil current and self-inductance constants."
                                )
                            ),
                            pyqs = getEmfQuestions("em_magnetostatics", 0),
                            practiceQuestions = getEmfQuestions("em_magnetostatics", 1),
                            mockQuiz = getEmfQuestions("em_magnetostatics", 2)
                        )
                    )
                ),
                Topic(
                    id = "em_maxwell",
                    subjectId = subjectId,
                    name = "Maxwell's Equations",
                    subtopics = listOf(
                        Subtopic(
                            id = "em_maxwell_equations",
                            topicId = "em_maxwell",
                            subjectId = subjectId,
                            name = "Maxwell's Equations & Displacement Current",
                            theory = TheoryContent(
                                title = "Unification under Maxwell's Equations",
                                synopsis = "Analyzes modern time-varying fields, displacement current, Faraday's discovery, and charge conservation laws.",
                                detailedBullets = listOf(
                                    "Displacement Current: Added mathematically by Maxwell as dD/dt to fulfill the continuity of charge requirements under time-harmonic conditions.",
                                    "Faraday's Law: Identifies that changing magnetic flux induces negative rotational circulating voltage rings.",
                                    "∇·B = 0: Disproves isolated magnetic monocenters or charge points, verifying all magnetic fields exist purely as loops.",
                                    "∇·D = ρv: Reflects absolute electrostatic divergence matching the local volumic source distribution density."
                                ),
                                keyInsight = "The curl of the electric field intensity equals the negative time derivative of the magnetic flux density, indicating complete mutual coupling."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Faraday's Differential Equation",
                                    expression = "∇ × E = -∂B / ∂t",
                                    description = "Differential form linking electric field curls directly to rate of change of magnetic vectors.",
                                    applicationTrick = "Zero induced emf fields are observed if magnetic systems remain strictly static with time."
                                ),
                                FormulaItem(
                                    name = "Displacement Current Density",
                                    expression = "J_d = ∂D / ∂t",
                                    description = "Formulated by Maxwell to generalize Ampere's law for capacitors and time-harmonic designs.",
                                    applicationTrick = "Allows wave propagation through free-space vacuums without requiring physical charge paths."
                                )
                            ),
                            pyqs = getEmfQuestions("em_maxwell_equations", 0),
                            practiceQuestions = getEmfQuestions("em_maxwell_equations", 1),
                            mockQuiz = getEmfQuestions("em_maxwell_equations", 2)
                        )
                    )
                ),
                Topic(
                    id = "em_waves",
                    subjectId = subjectId,
                    name = "Waves & Propagation",
                    subtopics = listOf(
                        Subtopic(
                            id = "em_waves_propagation",
                            topicId = "em_waves",
                            subjectId = subjectId,
                            name = "EM Wave Propagation, Poynting Vector & Skin Depth",
                            theory = TheoryContent(
                                title = "Electromagnetic Uniform Plane Waves",
                                synopsis = "Addresses transverse electromagnetic propagation, wave velocities, skin damping, and power vectors.",
                                detailedBullets = listOf(
                                    "TEM wave properties: Electric and magnetic vectors are orthogonal to each other and perpendicular to wave flow vectors.",
                                    "Intrinsic Impedance: Ratio of E to H field, which measures exactly 377 Ohms (120 * pi) inside free-space vacuums.",
                                    "Poynting Statement: Vector product E x H represents the instantaneous direction and intensity of power flow density per unit area.",
                                    "Skin Depth (δ): Depicts penetration distance into conductors where wave amplitude decays to 36.8% of its boundary value."
                                ),
                                keyInsight = "Skin depth decreases rapidly as frequency increases, forcing high-frequency currents to run purely along outer wire layers."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Wavelength and Velocity Relation",
                                    expression = "v = f * λ",
                                    description = "Relates propagation speed directly to wave frequency and wave cycles.",
                                    applicationTrick = "Velocity is fixed to 3 * 10^8 m/s in free space."
                                ),
                                FormulaItem(
                                    name = "Poynting Energy Vector",
                                    expression = "S = E × H",
                                    description = "Represents overall direction and volumic power transport flow density of passing waves.",
                                    applicationTrick = "Unit of Poynting vector is Watts per square meter (W/m²)."
                                )
                            ),
                            pyqs = getEmfQuestions("em_waves_propagation", 0),
                            practiceQuestions = getEmfQuestions("em_waves_propagation", 1),
                            mockQuiz = getEmfQuestions("em_waves_propagation", 2)
                        )
                    )
                ),
                Topic(
                    id = "em_transmission",
                    subjectId = subjectId,
                    name = "Lines & Waveguides",
                    subtopics = listOf(
                        Subtopic(
                            id = "em_transmission_lines",
                            topicId = "em_transmission",
                            subjectId = subjectId,
                            name = "Transmission Line Equations, VSWR & Impedance Matching",
                            theory = TheoryContent(
                                title = "Distributed Parameter Lines & Standing Waves",
                                synopsis = "Examines characteristic impedances, voltage standing wave ratios (VSWR), and impedance matching transformations.",
                                detailedBullets = listOf(
                                    "Characteristic Impedance (Z0): Value of wave impedance along infinite lines, matching sqrt(L/C) for lossless systems.",
                                    "Voltage Standing Wave Ratio (VSWR): Relates peak to trough signal cycles, showing exactly 1 for ideal matching structures.",
                                    "Reflection Coefficient (Γ): Matches incident to bounced voltage signals, depending directly on mismatched loads: (ZL - Z0)/(ZL + Z0).",
                                    "Quarter-Wave Matcher: Transforms impedances using Zin = Z0² / ZL, utilizing exactly quarter-wavelength line lines."
                                ),
                                keyInsight = "Perfect line matching yields zero reflection coefficients, delivering maximum possible power flow straight to the load terminal."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Reflection Coefficient",
                                    expression = "Γ = (Z_L - Z_0) / (Z_L + Z_0)",
                                    description = "Evaluates mismatch levels at lines boundaries.",
                                    applicationTrick = "Values range strictly from -1 (shorts) to +1 (open circuits)."
                                ),
                                FormulaItem(
                                    name = "VSWR Calculation",
                                    expression = "VSWR = (1 + |Γ|) / (1 - |Γ|)",
                                    description = "Provides ratio of maximum to minimum voltage envelope amplitude along mismatched lines.",
                                    applicationTrick = "Matches perfectly to 1 when Γ is zero, and reaches infinity for open/short systems."
                                )
                            ),
                            pyqs = getEmfQuestions("em_transmission_lines", 0),
                            practiceQuestions = getEmfQuestions("em_transmission_lines", 1),
                            mockQuiz = getEmfQuestions("em_transmission_lines", 2)
                        ),
                        Subtopic(
                            id = "em_waveguides_radiation",
                            topicId = "em_transmission",
                            subjectId = subjectId,
                            name = "Waveguides, Cutoff Frequencies & Antennas",
                            theory = TheoryContent(
                                title = "Guided Fields, Rectangular Guides & Antennas",
                                synopsis = "Addresses hollow pipe guides, dominant modes, cutoff frequency conditions, and accelerating charge radiation energy.",
                                detailedBullets = listOf(
                                    "Hollow Waveguides: Coaxial cables support TEM, but hollow single-conductors strictly restrict TEM, supporting only TE or TM modes.",
                                    "Dominant Mode: Mode with the Absolute Lowest Cutoff Frequency. In rectangular pipes, this is the TE10 mode.",
                                    "Cutoff Frequency: Boundary frequency threshold below which wave propagation drops to exponentially decaying evanescent fields.",
                                    "Radiation Principle: Antennas radiate electromagnetic waves in response to accelerating charges along current elements."
                                ),
                                keyInsight = "TEM systems cannot survive inside single closed pipe guides because they require two isolated metallic boundaries to support static transverse gradients."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Cutoff Frequency of Rectangular Guide",
                                    expression = "f_c = (c / 2) * sqrt((m/a)² + (n/b)²)",
                                    description = "Determines boundary propagation cutoffs based on cross-section guide widths 'a' and heights 'b'.",
                                    applicationTrick = "TE10 dominant mode cutoff expression simplifies to c / (2 * a)."
                                ),
                                FormulaItem(
                                    name = "Waveguide Mode Conditions",
                                    expression = "TE Mode: E_z = 0, H_z ≠ 0; TM Mode: H_z = 0, E_z ≠ 0",
                                    description = "Classifies boundary field projections along propagation lines.",
                                    applicationTrick = "Enforces zero longitudinal gradients for respective fields."
                                )
                            ),
                            pyqs = getEmfQuestions("em_waveguides_radiation", 0),
                            practiceQuestions = getEmfQuestions("em_waveguides_radiation", 1),
                            mockQuiz = getEmfQuestions("em_waveguides_radiation", 2)
                        )
                    )
                )
            )
        )
    }

    private fun createMeasurements(): Subject {
        val subjectId = "measurements"
        val allMiQuestions = MeasurementsQuestions.questions

        fun getMiQuestions(subId: String, part: Int): List<GateQuestion> {
            val sq = allMiQuestions.filter { it.subtopicId == subId }
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
            name = "Measurements & Instrumentation",
            iconName = "speed",
            topics = listOf(
                Topic(
                    id = "mi_meters_bridges",
                    subjectId = subjectId,
                    name = "Measuring Meters & Bridges",
                    subtopics = listOf(
                        Subtopic(
                            id = "mi_fundamentals_errors",
                            topicId = "mi_meters_bridges",
                            subjectId = subjectId,
                            name = "Measurement Fundamentals & Error Analysis",
                            theory = TheoryContent(
                                title = "Fundamentals of Measurements & Error Analysis",
                                synopsis = "Examines system accuracies, repeatability limits, static characteristics, and systematic versus random uncertainties.",
                                detailedBullets = listOf(
                                    "Accuracy vs. Precision: Accuracy focuses on conforming to true standards, whereas Precision tracks self-consistency.",
                                    "Error Mitigation: Systematic bias is corrected via calibration, but random anomalies require statistical averaging.",
                                    "Dynamic Performance: Speed of response, fidelity and delay are keys to tracking rapid continuous waves."
                                ),
                                keyInsight = "By averaging multiple identical measurements, random noise elements drop following the square root of observation size."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Absolute Error",
                                    expression = "E_a = Y_measured - X_true",
                                    description = "Evaluates difference between measured and true physical quantities.",
                                    applicationTrick = "Treat absolute errors carefully when propagating values through operations."
                                )
                            ),
                            pyqs = getMiQuestions("mi_fundamentals_errors", 0),
                            practiceQuestions = getMiQuestions("mi_fundamentals_errors", 1),
                            mockQuiz = getMiQuestions("mi_fundamentals_errors", 2)
                        ),
                        Subtopic(
                            id = "mi_analog_meters",
                            topicId = "mi_meters_bridges",
                            subjectId = subjectId,
                            name = "Analog Electromechanical Meters",
                            theory = TheoryContent(
                                title = "Analog PMMC, MI & Instrument Transformers",
                                synopsis = "Explores electromechanical coil deflections, magnetic moving-irons, high current scaling, and power parameters.",
                                detailedBullets = listOf(
                                    "PMMC deflection: Permanent magnets produce flux; driving currents interact to move coils linearly: T_d = N*B*A*I.",
                                    "Moving Iron: Magnetic forces distort iron pieces, proportional to the square of current, allowing AC/DC RMS capture.",
                                    "Transformers: Secondary lines of Current Transformers (CT) must be shorted to prevent fatal magnetic saturations."
                                ),
                                keyInsight = "The secondary of any current transformer should never be open-circuited because heavy back-EMF values will saturating the core and burn the insulation."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "PMMC Deflection Torque",
                                    expression = "T_d = B * A * I * N",
                                    description = "The basic torque equation driving linear PMMC movements.",
                                    applicationTrick = "Since deflecting torque balances spring tension, pointer displacement varies directly with direct current input."
                                )
                            ),
                            pyqs = getMiQuestions("mi_analog_meters", 0),
                            practiceQuestions = getMiQuestions("mi_analog_meters", 1),
                            mockQuiz = getMiQuestions("mi_analog_meters", 2)
                        ),
                        Subtopic(
                            id = "mi_bridges",
                            topicId = "mi_meters_bridges",
                            subjectId = subjectId,
                            name = "DC & AC Bridge Networks",
                            theory = TheoryContent(
                                title = "Resistive & Reactive Impedance Bridges",
                                synopsis = "Covers standard null-detecting bridges used to capture resistance, high-Q inductance, and dielectric capacitance.",
                                detailedBullets = listOf(
                                    "DC bridges: Wheatstone evaluates medium levels, whereas Kelvin double bridges cancel lead wire resistances.",
                                    "Inductance Bridges: Maxwell is efficient for medium quality coils, while Hay bridges suit high-Q inductors.",
                                    "Capacitance & Loss: Schering bridge measures losses of clean dielectric systems via phase shift angles."
                                ),
                                keyInsight = "AC bridge balancing requires both phase angles and magnitude to balance and cancel detector current."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "General AC Bridge Balance",
                                    expression = "Z1 * Z4 = Z2 * Z3",
                                    description = "Balance matching condition of opposite branches.",
                                    applicationTrick = "Separate into real and imaginary parts to resolve the parameters independently."
                                )
                            ),
                            pyqs = getMiQuestions("mi_bridges", 0),
                            practiceQuestions = getMiQuestions("mi_bridges", 1),
                            mockQuiz = getMiQuestions("mi_bridges", 2)
                        )
                    )
                ),
                Topic(
                    id = "mi_sensors_digital",
                    subjectId = subjectId,
                    name = "Sensors & Digital Systems",
                    subtopics = listOf(
                        Subtopic(
                            id = "mi_transducers_temp",
                            topicId = "mi_sensors_digital",
                            subjectId = subjectId,
                            name = "Transducers & Temperature Sensors",
                            theory = TheoryContent(
                                title = "Electromechanical Transducers & Temperature Probes",
                                synopsis = "Principles of strain deformation grid factors, displacement coupling transformers, and standard thermal variables.",
                                detailedBullets = listOf(
                                    "Strain Gauges: Converts structural stretch to proportional grid resistance changes: dR/R = G_F * strain.",
                                    "LVDT displacement: Concentric secondary coils track core movements, producing phase-sensitive voltages.",
                                    "Temperature Sensors: RTD platinum resistance rises with heat, while thermistors drop, and thermocouples yield voltages."
                                ),
                                keyInsight = "An active transducer generates its own output voltage or current without requiring external bias potential."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Strain Gauge Factor",
                                    expression = "G_F = (dR/R) / Strain",
                                    description = "Deduces proportional resistance shift per unit deformation.",
                                    applicationTrick = "Typically G_F ≈ 2 for standard copper grids."
                                )
                            ),
                            pyqs = getMiQuestions("mi_transducers_temp", 0),
                            practiceQuestions = getMiQuestions("mi_transducers_temp", 1),
                            mockQuiz = getMiQuestions("mi_transducers_temp", 2)
                        ),
                        Subtopic(
                            id = "mi_digital_oscilloscope",
                            topicId = "mi_sensors_digital",
                            subjectId = subjectId,
                            name = "Digital Instruments & Oscilloscopes",
                            theory = TheoryContent(
                                title = "Cathode Ray Tubes, Storage & Frequency Counters",
                                synopsis = "Deals with analog CRT deflection plates, digitized memory samples, voltmeter scales, and gating cycles.",
                                detailedBullets = listOf(
                                    "CRT deflection: Electric fields accelerate focus beams vertically while sweep sawteeth move horizontally.",
                                    "Lissajous: Inputting two sinusoidal channels builds symmetric curves, confirming frequency ratios and phases.",
                                    "Frequency Counters: High precision reference crystals count logic pulses during configured gateway durations."
                                ),
                                keyInsight = "A DSO stores continuous signals into static digital memory, preventing dynamic decay observed on CRO tubes."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Lissajous Peak Frequency Ratio",
                                    expression = "fy / fx = Nx / Ny",
                                    description = "Connects horizontal and vertical intersections to channel frequencies.",
                                    applicationTrick = "Count the tangential peaks along each axis to calculate unknown ratios."
                                )
                            ),
                            pyqs = getMiQuestions("mi_digital_oscilloscope", 0),
                            practiceQuestions = getMiQuestions("mi_digital_oscilloscope", 1),
                            mockQuiz = getMiQuestions("mi_digital_oscilloscope", 2)
                        ),
                        Subtopic(
                            id = "mi_circuits_conditioning",
                            topicId = "mi_sensors_digital",
                            subjectId = subjectId,
                            name = "Data Acquisition, Signal Conditioning & Conversions",
                            theory = TheoryContent(
                                title = "Signal Conditioning, Amplifiers & Data Converters",
                                synopsis = "Covers high-CMRR differential instrumentation buffers, Nyquist filtering, and analog/digital conversions.",
                                detailedBullets = listOf(
                                    "Instrumentation Amplifiers: Connects three op-amps as a high impedance buffer to reject common noise.",
                                    "ADC Converters: Successive approximation is resource-efficient, whereas Flash grids prioritize absolute high speed.",
                                    "Nyquist rate: To prevent aliasing overlap distortion, sample frequencies must exceed twice the band width limit."
                                ),
                                keyInsight = "High CMRR is mandatory in instrumentation blocks to cancel out heavy identical noise voltages."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Instrumentation Amplifier CMRR",
                                    expression = "CMRR = Ad / Ac",
                                    description = "Evaluates common mode rejection performance.",
                                    applicationTrick = "Represent in dB using 20 * log10(Ad / Ac) for comparisons."
                                )
                            ),
                            pyqs = getMiQuestions("mi_circuits_conditioning", 0),
                            practiceQuestions = getMiQuestions("mi_circuits_conditioning", 1),
                            mockQuiz = getMiQuestions("mi_circuits_conditioning", 2)
                        )
                    )
                )
            )
        )
    }

    private fun createAptitudeForAll(): Subject {
        val subjectId = "aptitude_for_all"
        val allAfaQuestions = AptitudeForAllQuestions.questions

        fun getAfaQuestions(subId: String, part: Int): List<GateQuestion> {
            val sq = allAfaQuestions.filter { it.subtopicId == subId }
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
            name = "Aptitude for all",
            iconName = "psychology",
            topics = listOf(
                Topic(
                    id = "afa_aptitude",
                    subjectId = subjectId,
                    name = "Quantitative Aptitude",
                    subtopics = listOf(
                        Subtopic(
                            id = "afa_time_work",
                            topicId = "afa_aptitude",
                            subjectId = subjectId,
                            name = "Time & Work & Cisterns",
                            theory = TheoryContent(
                                title = "Work Rates, Pipe Flow and Labor Effort",
                                synopsis = "Covers linear work completion, inverse days-to-worker proportions, pipe inflows, leak depletion rates, and cistern capacities.",
                                detailedBullets = listOf(
                                    "Time & Work: Solve using reciprocal rate systems where Person A's rate 1/D1 and Person B's rate 1/D2 add together linearly to 1/D_total.",
                                    "Pipes & Cisterns: Inflow pipes act as positive work rates, while leakage or outflow paths represent negative rates.",
                                    "Alternating schedules: Sequence discrete time turns and calculate fractional job progress periodically."
                                ),
                                keyInsight = "When workers have varying efficiencies, their rates must be scaled relatively before addition."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Worker Work Rate Proportion",
                                    expression = "M1 * D1 * H1 = M2 * D2 * H2",
                                    description = "Evaluates relationship between workers, days, and active daily hours worked.",
                                    applicationTrick = "When intermediate workers leave or join, solve parts of the job as fractional rates."
                                )
                            ),
                            pyqs = getAfaQuestions("afa_time_work", 0),
                            practiceQuestions = getAfaQuestions("afa_time_work", 1),
                            mockQuiz = getAfaQuestions("afa_time_work", 2)
                        ),
                        Subtopic(
                            id = "afa_pct_profit",
                            topicId = "afa_aptitude",
                            subjectId = subjectId,
                            name = "Percentages, Profit & Loss",
                            theory = TheoryContent(
                                title = "Percentages, Cost margins and Taxes",
                                synopsis = "Addresses base multipliers, gain percentages, loss offsets, discounted price lines, marked premiums, and sales taxes.",
                                detailedBullets = listOf(
                                    "Percentage change: Calculated as net deviation divided by original value, scaled by 100.",
                                    "Profit Margin: Net gain expressed as SP - CP, while percentage gain is always computed relative to the base Purchase Cost (CP).",
                                    "Discount: Offsets are directly deducted from Marked Price (MP) to produce the Selling Price (SP)."
                                ),
                                keyInsight = "Successive discounts of X% and Y% are not additive; they apply sequentially as a product multiplier."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Profit Margin Percent",
                                    expression = "Gain % = ((SP - CP) / CP) * 100",
                                    description = "Measures profit efficiency relative to cost base.",
                                    applicationTrick = "For rapid computing, treat percentage multipliers as continuous decimals (e.g. 20% gain = 1.2 * CP)."
                                )
                            ),
                            pyqs = getAfaQuestions("afa_pct_profit", 0),
                            practiceQuestions = getAfaQuestions("afa_pct_profit", 1),
                            mockQuiz = getAfaQuestions("afa_pct_profit", 2)
                        ),
                        Subtopic(
                            id = "afa_interest",
                            topicId = "afa_aptitude",
                            subjectId = subjectId,
                            name = "Simple & Compound Interest",
                            theory = TheoryContent(
                                title = "Growth and Sinking Funds",
                                synopsis = "Principles of linear interest accumulates, exponential compounding intervals, and present value true discounts.",
                                detailedBullets = listOf(
                                    "Simple Interest: Remains constant each year since it only charges the primary principal amount.",
                                    "Compound Interest: Grows exponentially because interest earned in prior cycles is added to the principal base.",
                                    "True Discount: Represents the difference between the face value of a future bill and its present worth."
                                ),
                                keyInsight = "Under compounding, the effective annual yield increases as compounding intervals become more frequent."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Compound Accumulation Formula",
                                    expression = "A = P * (1 + R/100)^n",
                                    description = "Evaluates compound accumulation over n periods.",
                                    applicationTrick = "For compounding more than once a year, scale R down and scale n up by the interval factor."
                                )
                            ),
                            pyqs = getAfaQuestions("afa_interest", 0),
                            practiceQuestions = getAfaQuestions("afa_interest", 1),
                            mockQuiz = getAfaQuestions("afa_interest", 2)
                        ),
                        Subtopic(
                            id = "afa_avg_mixtures",
                            topicId = "afa_aptitude",
                            subjectId = subjectId,
                            name = "Averages, Mixtures & Partnerships",
                            theory = TheoryContent(
                                title = "Weighted Averages, Concentration and Shares",
                                synopsis = "Addresses mean value combinations, allegations, mixture dilutions, investment partnerships, and profit sharing.",
                                detailedBullets = listOf(
                                    "Averages: Sum of all discrete units divided by total count of those units.",
                                    "Alligation: Relates the ratio of quantities to the price differences of cheap and dear ingredients.",
                                    "Partnerships: Profit shares are distributed directly proportional to the product of capital invested and period of investment."
                                ),
                                keyInsight = "Alligation lines provide a rapid graphical methodology to swap weighted average weights easily."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Alligation Ratio",
                                    expression = "(Qty of Cheap / Qty of Dear) = (Dear Price - Mean Price) / (Mean Price - Cheap Price)",
                                    description = "Establishes mixture proportion of ingredients to hit target mean pricing.",
                                    applicationTrick = "Leverage this rule to quickly balance salt concentrations in fluid mixtures."
                                )
                            ),
                            pyqs = getAfaQuestions("afa_avg_mixtures", 0),
                            practiceQuestions = getAfaQuestions("afa_avg_mixtures", 1),
                            mockQuiz = getAfaQuestions("afa_avg_mixtures", 2)
                        ),
                        Subtopic(
                            id = "afa_ratio_ages",
                            topicId = "afa_aptitude",
                            subjectId = subjectId,
                            name = "Ratio, Proportion & Ages",
                            theory = TheoryContent(
                                title = "Relative Proportions and Temporal Ages",
                                synopsis = "Core numeric proportions, duplicate ratios, fourth proportionals, scale changes, and historic age shifts.",
                                detailedBullets = listOf(
                                    "Ratios: Represent relative magnitude, easily compared when scaled to a common factor variable x.",
                                    "Proportions: Equalities of fractions where product of extremes equals product of means.",
                                    "Temporal Ages: Difference in age between two people remains constant across any point in time."
                                ),
                                keyInsight = "Always set up a common variable factor 'x' for ratios to set up solvable algebraic systems."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Ratio Common Factor",
                                    expression = "A : B -> Ax and Bx",
                                    description = "Constructs linear equations from proportional statements.",
                                    applicationTrick = "Age difference is constant, so (Father_age + Y) - (Son_age + Y) = Constant."
                                )
                            ),
                            pyqs = getAfaQuestions("afa_ratio_ages", 0),
                            practiceQuestions = getAfaQuestions("afa_ratio_ages", 1),
                            mockQuiz = getAfaQuestions("afa_ratio_ages", 2)
                        ),
                        Subtopic(
                            id = "afa_speed_distance",
                            topicId = "afa_aptitude",
                            subjectId = subjectId,
                            name = "Speed, Time & Distance",
                            theory = TheoryContent(
                                title = "Kinematics, Relative Speeds and Water Currents",
                                synopsis = "Kinematic speed changes, average velocity for splits, train passings, and swimming relative to stream flow.",
                                detailedBullets = listOf(
                                    "Relative Speed: Addition of speeds when moving in opposite directions; subtraction of speeds when chasing.",
                                    "Average Speed split: When covering equal distances, given by the harmonic mean of the individual speeds.",
                                    "River Currents: Downstream speed equals boat speed plus stream speed; upstream is boat speed minus stream speed."
                                ),
                                keyInsight = "When a train passes a bridge or platform, the total distance is the sum of train length and bridge length."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Harmonic Average Speed",
                                    expression = "Avg Speed = 2 * S1 * S2 / (S1 + S2)",
                                    description = "Computes average speed for two equal-distance segments.",
                                    applicationTrick = "Never calculate the arithmetic average of speeds unless time intervals are identical."
                                )
                            ),
                            pyqs = getAfaQuestions("afa_speed_distance", 0),
                            practiceQuestions = getAfaQuestions("afa_speed_distance", 1),
                            mockQuiz = getAfaQuestions("afa_speed_distance", 2)
                        ),
                        Subtopic(
                            id = "afa_num_systems",
                            topicId = "afa_aptitude",
                            subjectId = subjectId,
                            name = "Number Systems & Progressions",
                            theory = TheoryContent(
                                title = "Numeric Integers, Factors and Series Sums",
                                synopsis = "Integrate prime properties, common divisors, divisibility checks, unit digit repetitions, and series sums.",
                                detailedBullets = listOf(
                                    "Primes: Integers strictly greater than 1 that only possess 1 and themselves as natural factors.",
                                    "HCF & LCM: Highest common factor and least common multiple of a set of integers.",
                                    "Arithmetic Series: Linear step changes where terms are summed by averaging first and last elements."
                                ),
                                keyInsight = "Unit digits of exponents repeat periodically after a cycle length of exactly 4."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "HCF & LCM Relation",
                                    expression = "HCF(a, b) * LCM(a, b) = a * b",
                                    description = "Deduces product equivalencies of positive numbers.",
                                    applicationTrick = "Quickly find HCF of fractions as (HCF of Numerators) / (LCM of Denominators)."
                                )
                            ),
                            pyqs = getAfaQuestions("afa_num_systems", 0),
                            practiceQuestions = getAfaQuestions("afa_num_systems", 1),
                            mockQuiz = getAfaQuestions("afa_num_systems", 2)
                        ),
                        Subtopic(
                            id = "afa_algebra_logs",
                            topicId = "afa_aptitude",
                            subjectId = subjectId,
                            name = "Basic Algebra & Logarithms",
                            theory = TheoryContent(
                                title = "Algebraic Expressions, Roots and Growth logs",
                                synopsis = "Variables solving, quadratic roots coefficients, logarithmic bases, and cost optimizations.",
                                detailedBullets = listOf(
                                    "Quadratic Equation: Ax^2 + Bx + C = 0 roots are real if discriminant is non-negative.",
                                    "Logarithmic Laws: Multiplication inside log expands to addition of individual term logs.",
                                    "Optimization: Derivatives determine high and low inflection thresholds in dynamic cost equations."
                                ),
                                keyInsight = "The base of logarithms must belong to positive real numbers excluded of 1."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Logarithm Base Change",
                                    expression = "log_a(x) = ln(x) / ln(a)",
                                    description = "Converts logarithmic expressions to natural base logarithms.",
                                    applicationTrick = "Useful to cancel out complex composite quotient logarithms sequentially."
                                )
                            ),
                            pyqs = getAfaQuestions("afa_algebra_logs", 0),
                            practiceQuestions = getAfaQuestions("afa_algebra_logs", 1),
                            mockQuiz = getAfaQuestions("afa_algebra_logs", 2)
                        ),
                        Subtopic(
                            id = "afa_prob_combinations",
                            topicId = "afa_aptitude",
                            subjectId = subjectId,
                            name = "Probability & Permutations",
                            theory = TheoryContent(
                                title = "Chance Events, Orderings and Groupings",
                                synopsis = "Examines likelihood limits, combinations, permutations, coin state counts, and deck outcomes.",
                                detailedBullets = listOf(
                                    "Permutations: Used when order of elements matters (e.g. arranging people in line).",
                                    "Combinations: Used when only selection is needed, irrespective of ordering sequence.",
                                    "Probability: Quantifies probability as favorable sample states divided by absolute universe states."
                                ),
                                keyInsight = "The probability of at least one target occurrence is simpler to find as 1 minus zero occurrences."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Combination Formula",
                                    expression = "nCr = n! / (r! * (n-r)!)",
                                    description = "Counts possible ways to pick r elements from n entities without ordering.",
                                    applicationTrick = "Always simplify factorials beforehand to prevent memory overflows."
                                )
                            ),
                            pyqs = getAfaQuestions("afa_prob_combinations", 0),
                            practiceQuestions = getAfaQuestions("afa_prob_combinations", 1),
                            mockQuiz = getAfaQuestions("afa_prob_combinations", 2)
                        ),
                        Subtopic(
                            id = "afa_set_data",
                            topicId = "afa_aptitude",
                            subjectId = subjectId,
                            name = "Set Theory, Venn Diagrams & Statistics",
                            theory = TheoryContent(
                                title = "Set Relationships, Venn diagrams and Data splits",
                                synopsis = "Set intersections, inclusion Venn regions, statistical averages, medians, modes, and variances.",
                                detailedBullets = listOf(
                                    "Venn Systems: Graphical groupings representing intersections, unions, and exclusive categories.",
                                    "Measures of Central Tendency: Mean (average), Median (middle value), and Mode (most frequent term).",
                                    "Variance: Calculates distance deviations from the average mean."
                                ),
                                keyInsight = "The sum of all percentage proportions inside a pie chart must equal exactly 360 degrees of circle angle."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Inclusion Exclusion Formula",
                                    expression = "n(A u B) = n(A) + n(B) - n(A n B)",
                                    description = "Evaluates overlaps between two logical sets.",
                                    applicationTrick = "Establish exclusive groups (A only, B only, Both) first on Venn maps."
                                )
                            ),
                            pyqs = getAfaQuestions("afa_set_data", 0),
                            practiceQuestions = getAfaQuestions("afa_set_data", 1),
                            mockQuiz = getAfaQuestions("afa_set_data", 2)
                        ),
                        Subtopic(
                            id = "afa_geometry",
                            topicId = "afa_aptitude",
                            subjectId = subjectId,
                            name = "Geometry & Mensuration",
                            theory = TheoryContent(
                                title = "Planar Lines, Dimensions and Volume bounds",
                                synopsis = "Covers rectangular areas, spherical volumes, cylinder sizes, circles, and perimeter boundaries.",
                                detailedBullets = listOf(
                                    "Perimeter: Evaluates sum of external boundary lines around closed geometries.",
                                    "Mensuration: Calculates three-dimensional volume boundaries and surface areas.",
                                    "Cylinders and Cones: Connects volume metrics between cylinder shapes and identical cone dimensions."
                                ),
                                keyInsight = "Scaling coordinates or lengths by k scales the perimeter by k, the area by k^2, and volume by k^3."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Circle Area",
                                    expression = "Area = pi * r^2",
                                    description = "Computes circle area given radius variable r.",
                                    applicationTrick = "If radius is scaled, area is modified proportional to the square of scale shift factor."
                                )
                            ),
                            pyqs = getAfaQuestions("afa_geometry", 0),
                            practiceQuestions = getAfaQuestions("afa_geometry", 1),
                            mockQuiz = getAfaQuestions("afa_geometry", 2)
                        )
                    )
                ),
                Topic(
                    id = "afa_logical",
                    subjectId = subjectId,
                    name = "Logical Reasoning",
                    subtopics = listOf(
                        Subtopic(
                            id = "afa_blood_relations",
                            topicId = "afa_logical",
                            subjectId = subjectId,
                            name = "Blood Relations",
                            theory = TheoryContent(
                                title = "Kinship Links and Family Trees",
                                synopsis = "Analysis of relative links, maternal structures, generation gaps, and coding relationships.",
                                detailedBullets = listOf(
                                    "Kinship tracking: Formulate step-by-step linkages of family branches with explicit notations.",
                                    "Generation levels: Set up vertical hierarchies representing grandparents, parents, self, and children.",
                                    "Coded relationships: Deduce relational terms based on mathematical symbolic operators (e.g. A + B)."
                                ),
                                keyInsight = "Do not assume the gender of a person strictly based on name labels alone; look for pronoun qualifiers."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Generation Delta",
                                    expression = "Delta = grandparent (+2), parent (+1), sibling (0), child (-1)",
                                    description = "Assigns numerical steps to navigate parentage trees.",
                                    applicationTrick = "Construct family graphs with gender tags (+ for male, - for female) to prevent confusion."
                                )
                            ),
                            pyqs = getAfaQuestions("afa_blood_relations", 0),
                            practiceQuestions = getAfaQuestions("afa_blood_relations", 1),
                            mockQuiz = getAfaQuestions("afa_blood_relations", 2)
                        ),
                        Subtopic(
                            id = "afa_directions",
                            topicId = "afa_logical",
                            subjectId = subjectId,
                            name = "Directions & Compass tracking",
                            theory = TheoryContent(
                                title = "Compass Points, Bearing moves and Distances",
                                synopsis = "Cardinal directions, step displacements, compass bearing shifts, and distance computations.",
                                detailedBullets = listOf(
                                    "Cardinal Directions: Coordinate plane representing North (up), South (down), East (right), and West (left).",
                                    "Displacements: Net diagonal distance from start position is found with the Pythagoras theorem.",
                                    "Compass Turns: Clockwise and counterclockwise angular turns relative to initial bearing directions."
                                ),
                                keyInsight = "Shadow shifts depend on sun coordinates: morning sun casts shadows directly West."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Pythagorean Displacement",
                                    expression = "Displacement = √(X^2 + Y^2)",
                                    description = "Finds direct distance between starting and ending points.",
                                    applicationTrick = "Map movements onto standard cartesians with origin representing the starting location."
                                )
                            ),
                            pyqs = getAfaQuestions("afa_directions", 0),
                            practiceQuestions = getAfaQuestions("afa_directions", 1),
                            mockQuiz = getAfaQuestions("afa_directions", 2)
                        ),
                        Subtopic(
                            id = "afa_ranking",
                            topicId = "afa_logical",
                            subjectId = subjectId,
                            name = "Ranking & Positions",
                            theory = TheoryContent(
                                title = "Ordered Lists, Positions and Rank Swaps",
                                synopsis = "Addresses positional counts inside queues, left/right order indices, and comparative rankings.",
                                detailedBullets = listOf(
                                    "Ordered positions: Evaluates total entities in series given positioning indexes.",
                                    "Rank exchanges: Swapping indexes allows determining the overall group capacity mathematically.",
                                    "Comparisons: Ordering heights or sizes based on relative inequalities (e.g. A > B)."
                                ),
                                keyInsight = "When indexing someone from both sides, the index overlap requires subtracting 1 to avoid double counting."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Rank-Position Counter",
                                    expression = "Total = Position_left + Position_right - 1",
                                    description = "Connects positioning indexes to total size.",
                                    applicationTrick = "Double check whether the question demands inclusive or exclusive indices."
                                )
                            ),
                            pyqs = getAfaQuestions("afa_ranking", 0),
                            practiceQuestions = getAfaQuestions("afa_ranking", 1),
                            mockQuiz = getAfaQuestions("afa_ranking", 2)
                        ),
                        Subtopic(
                            id = "afa_coding_series",
                            topicId = "afa_logical",
                            subjectId = subjectId,
                            name = "Series & Coding-Decoding",
                            theory = TheoryContent(
                                title = "Pattern Recognition, Shift codes and Series",
                                synopsis = "Number series patterns, word coding shifts, alphabet series, and index codes.",
                                detailedBullets = listOf(
                                    "Coding: Decoding cipher words by recognizing shift patterns in spelling letters (+1, -2, etc.).",
                                    "Number Series: Finding patterns in differences, square progressions, or Fibonacci sequences.",
                                    "Alphanumeric coding: Grouping letters and counting indices together to find sequence gaps."
                                ),
                                keyInsight = "Memorizing letter positions from 1 to 26 makes decoding shift questions significantly faster."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "EJOTY Rule",
                                    expression = "E=5, J=10, O=15, T=20, Y=25",
                                    description = "Establishes alphabetical index landmarks.",
                                    applicationTrick = "Use these letters as mental anchors to rapidly locate other alphabets."
                                )
                            ),
                            pyqs = getAfaQuestions("afa_coding_series", 0),
                            practiceQuestions = getAfaQuestions("afa_coding_series", 1),
                            mockQuiz = getAfaQuestions("afa_coding_series", 2)
                        ),
                        Subtopic(
                            id = "afa_clocks_calendars",
                            topicId = "afa_logical",
                            subjectId = subjectId,
                            name = "Clocks & Calendars",
                            theory = TheoryContent(
                                title = "Temporal Periodics, Hand angles and Leap counts",
                                synopsis = "Clock hand angles, minute/hour speed differentials, leap years, and calendar cycles.",
                                detailedBullets = listOf(
                                    "Clock Rates: Minute hand travels at 6° per minute; hour hand sweeps at 0.5° per minute.",
                                    "Calendar cycles: Ordinary years contain 1 odd day, while leap years contain 2 odd days.",
                                    "Leap Years: Years divisible by 4, except for century years which must be divisible by 400."
                                ),
                                keyInsight = "A clock's hands overlap or point in opposite directions periodically due to speed differences."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Clock Face Angle",
                                    expression = "Angle = |30 * H - 5.5 * M|",
                                    description = "Evaluates hand angle separation given hours H and minutes M.",
                                    applicationTrick = "For reflex angles, subtract the computed angle directly from 360 degrees."
                                )
                            ),
                            pyqs = getAfaQuestions("afa_clocks_calendars", 0),
                            practiceQuestions = getAfaQuestions("afa_clocks_calendars", 1),
                            mockQuiz = getAfaQuestions("afa_clocks_calendars", 2)
                        ),
                        Subtopic(
                            id = "afa_analogies_syllogisms",
                            topicId = "afa_logical",
                            subjectId = subjectId,
                            name = "Analogies & Syllogisms",
                            theory = TheoryContent(
                                title = "Relational Maps, Venn statements and logical limits",
                                synopsis = "Deductive verbal syllogisms, classification groupings, verbal analogies, and logical conclusions.",
                                detailedBullets = listOf(
                                    "Syllogisms: Use Venn diagram overlap rules to test statement validity.",
                                    "Analogies: Map the relationship between a reference pair onto a target pair.",
                                    "Classification: Identify outliers or odd terms that do not share the group's common pattern."
                                ),
                                keyInsight = "Logical statements are directional: 'All A are B' does not guarantee 'All B are A'."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Syllogistic Rules",
                                    expression = "All p are q -> (circle of p inside q)",
                                    description = "Builds graphical models representing logical statements.",
                                    applicationTrick = "Consider and draw all possible overlapping configurations before drawing conclusions."
                                )
                            ),
                            pyqs = getAfaQuestions("afa_analogies_syllogisms", 0),
                            practiceQuestions = getAfaQuestions("afa_analogies_syllogisms", 1),
                            mockQuiz = getAfaQuestions("afa_analogies_syllogisms", 2)
                        )
                    )
                ),
                Topic(
                    id = "afa_verbal",
                    subjectId = subjectId,
                    name = "Verbal Ability",
                    subtopics = listOf(
                        Subtopic(
                            id = "afa_syn_ant",
                            topicId = "afa_verbal",
                            subjectId = subjectId,
                            name = "Synonyms & Antonyms",
                            theory = TheoryContent(
                                title = "Lexical Nuances and Word Polarity",
                                synopsis = "Vocabulary synonyms, antonym structures, base prefixes, and contextual word meanings.",
                                detailedBullets = listOf(
                                    "Synonyms: Select words with closest contextual meaning under grammatical equivalents.",
                                    "Antonyms: Identify oppositional terms while preserving verb tenses and parts of speech.",
                                    "Roots: Recognize word stems and affixes to quickly identify overall word meanings."
                                ),
                                keyInsight = "Word polarities are often revealed by looking at prefixes like 'un-', 'dis-', or 'im-'."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Contextual Fit Rule",
                                    expression = "Verify if substituting the synonym keeps the sentence's meaning constant.",
                                    description = "Validates choice by textual replacement.",
                                    applicationTrick = "Read the surrounding sentences to understand the exact tone and nuance of the word."
                                )
                            ),
                            pyqs = getAfaQuestions("afa_syn_ant", 0),
                            practiceQuestions = getAfaQuestions("afa_syn_ant", 1),
                            mockQuiz = getAfaQuestions("afa_syn_ant", 2)
                        ),
                        Subtopic(
                            id = "afa_vocab_grammar",
                            topicId = "afa_verbal",
                            subjectId = subjectId,
                            name = "Vocabulary & Grammar",
                            theory = TheoryContent(
                                title = "Syntactic Grammar, Spelling and Word use",
                                synopsis = "Spelling rules, passive/active voice transformations, definite articles, and singular/plural prepositions.",
                                detailedBullets = listOf(
                                    "Spelling: Rules of vowel combinations (e.g. 'i before e except after c').",
                                    "Grammar: Maintain subject-verb agreement across singular and plural sentences.",
                                    "Articles: Precede singular nouns with indefinite qualifiers based on phonetic pronunciation sound."
                                ),
                                keyInsight = "Choose 'an' before silent-h words when they produce vowel sounds (e.g. 'an honest man')."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Article Selection Rule",
                                    expression = "Consonant Sound -> 'A', Vowel Sound -> 'An'",
                                    description = "Evaluates pronunciation phonetics for proper article pairing.",
                                    applicationTrick = "Analyze pronunciation sound rather than relying strictly on the starting letter."
                                )
                            ),
                            pyqs = getAfaQuestions("afa_vocab_grammar", 0),
                            practiceQuestions = getAfaQuestions("afa_vocab_grammar", 1),
                            mockQuiz = getAfaQuestions("afa_vocab_grammar", 2)
                        ),
                        Subtopic(
                            id = "afa_sent_completion",
                            topicId = "afa_verbal",
                            subjectId = subjectId,
                            name = "Sentence Completion",
                            theory = TheoryContent(
                                title = "Structural Fillers and Word Alignment",
                                synopsis = "Contextual blank fits, parts-of-speech alignments, sentence structure, and transitional conjunctions.",
                                detailedBullets = listOf(
                                    "Sentence Completion: Select fitting words based on contextual tone and surrounding sentences.",
                                    "Conjunction Clues: Contrast transitions demands opposite polarity terms in sentence setups.",
                                    "Subject Alignment: Keep verb forms aligned with compound nouns or pronouns."
                                ),
                                keyInsight = "Conjunctions like 'neither... nor' take singular verbs if the subject nearest to the verb is singular."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Conjunction Clue Rule",
                                    expression = "Transitions like 'however', 'although' imply semantic contrast.",
                                    description = "Guides blank completion selections across sentences.",
                                    applicationTrick = "Verify polarities of both blanks inside two-blank sentence problems."
                                )
                            ),
                            pyqs = getAfaQuestions("afa_sent_completion", 0),
                            practiceQuestions = getAfaQuestions("afa_sent_completion", 1),
                            mockQuiz = getAfaQuestions("afa_sent_completion", 2)
                        ),
                        Subtopic(
                            id = "afa_comprehension_reasoning",
                            topicId = "afa_verbal",
                            subjectId = subjectId,
                            name = "Comprehension & Critical Reasoning",
                            theory = TheoryContent(
                                title = "Narrative Comprehension and Argument Logic",
                                synopsis = "Addressing passage analysis, implicit assumptions, author tone, and sentence sequencing.",
                                detailedBullets = listOf(
                                    "Comprehension: Locate core arguments and stated facts directly within reading passages.",
                                    "Critical Reasoning: Spot logical fallacies, implicit assumptions, and variables that weaken arguments.",
                                    "Author Tone: Identify primary tones of authors based on lexical style (e.g. sarcastic, objective)."
                                ),
                                keyInsight = "Use negation testing: if negating the assumption collapses the argument, it is correct."
                            ),
                            formulaSheet = listOf(
                                FormulaItem(
                                    name = "Assumptions Rule",
                                    expression = "Assumption must be true for the argument to hold.",
                                    description = "Determines validity of implicit assumptions.",
                                    applicationTrick = "Negate the proposed assumption; check if it logically disproves the core conclusion."
                                )
                            ),
                            pyqs = getAfaQuestions("afa_comprehension_reasoning", 0),
                            practiceQuestions = getAfaQuestions("afa_comprehension_reasoning", 1),
                            mockQuiz = getAfaQuestions("afa_comprehension_reasoning", 2)
                        )
                    )
                )
            )
        )
    }
}
