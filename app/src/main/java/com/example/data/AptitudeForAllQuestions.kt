package com.example.data

object AptitudeForAllQuestions {

    val questions: List<GateQuestion> by lazy {
        list1 + list2Group1 + list2Group2 + list2Group3 + list2Group4
    }

    private fun q(
        id: String,
        category: String,
        subCategory: String,
        question: String,
        options: List<String>,
        answer: String
    ): GateQuestion {
        val subtopicId = getSubtopicId(category, subCategory, question)
        val topicId = when (subtopicId) {
            "afa_time_work", "afa_pct_profit", "afa_interest", "afa_avg_mixtures", "afa_ratio_ages",
            "afa_speed_distance", "afa_num_systems", "afa_algebra_logs", "afa_prob_combinations",
            "afa_set_data", "afa_geometry" -> "afa_aptitude"
            "afa_blood_relations", "afa_directions", "afa_ranking", "afa_coding_series",
            "afa_clocks_calendars", "afa_analogies_syllogisms" -> "afa_logical"
            "afa_syn_ant", "afa_vocab_grammar", "afa_sent_completion", "afa_comprehension_reasoning" -> "afa_verbal"
            else -> "afa_aptitude"
        }

        val correctIdx = options.indexOf(answer).let { if (it == -1) 0 else it }

        val difficulty = when (id.hashCode() % 3) {
            0 -> "Easy"
            1 -> "Medium"
            else -> "Hard"
        }

        val formulas = when (subCategory) {
            "Time and Work" -> "Work = Efficiency * Time, (M1 * D1 * H1) / W1 = (M2 * D2 * H2) / W2"
            "Percentages" -> "Percentage Change = (Change / Original) * 100"
            "Profit and Loss" -> "Profit % = (Gain / CP) * 100, Loss % = (Loss / CP) * 100"
            "Averages" -> "Average = Sum of observations / Number of observations"
            "Simple Interest" -> "S.I. = (P * R * T) / 100"
            "Compound Interest" -> "Amount = P * (1 + R/100)^T"
            "Probability" -> "Probability P(A) = Number of Favorable Outcomes / Total Outcomes"
            "Permutations & Combinations", "Permutations and Combinations" -> "nPr = n! / (n-r)!, nCr = n! / (r! * (n-r)!)"
            "Geometry" -> "Rectangle Perimeter = 2 * (L + W), Area = L * W"
            "HCF & LCM" -> "Product of two numbers = HCF * LCM"
            "Set Theory", "Venn Diagrams" -> "n(A u B) = n(A) + n(B) - n(A n B)"
            else -> "Standard logic application"
        }

        return GateQuestion(
            id = id,
            subjectId = "aptitude_for_all",
            topicId = topicId,
            subtopicId = subtopicId,
            year = 2021 + (id.hashCode() % 6).let { if (it < 0) -it else it },
            questionText = question,
            questionType = QuestionType.MCQ,
            options = options,
            correctOptions = listOf(correctIdx),
            correctNumericalRange = null,
            explanation = "Subject subdomain is $subCategory under $category. The correct answer is '$answer'.",
            formulasUsed = formulas,
            shortcutTricks = "Evaluate option constraints, look for simple cases or quick logic shortcuts.",
            relatedConcepts = "$subCategory, $category",
            difficulty = difficulty
        )
    }

    private fun getSubtopicId(category: String, subCategory: String, question: String): String {
        val cat = category.trim()
        val sub = subCategory.trim()
        val text = (question + " " + sub).lowercase()

        return when {
            // Verbal Ability
            cat.contains("Verbal") || text.contains("synonym") || text.contains("antonym") || text.contains("spelled") || text.contains("grammar") || text.contains("sentence completion") || text.contains("comprehension") || text.contains("critical reasoning") || text.contains("tone of author") -> {
                when {
                    text.contains("synonym") || text.contains("antonym") -> "afa_syn_ant"
                    text.contains("completed on time") || text.contains("brightest") || text.contains("neither ravi") || text.contains("meeting has been postponed") || text.contains("postponed to") || text.contains("results were") || text.contains("completion") -> "afa_sent_completion"
                    text.contains("tone of") || text.contains("assumption") || text.contains("comprehension") || text.contains("critical") || text.contains("argument") || text.contains("tone of author") -> "afa_comprehension_reasoning"
                    else -> "afa_vocab_grammar"
                }
            }
            // Logical Reasoning
            cat.contains("Logical") || sub.contains("Logical") || text.contains("relation") || text.contains("direction") || text.contains("walks") || text.contains("ranking") || text.contains("series") || text.contains("pattern") || text.contains("clock") || text.contains("calendar") || text.contains("analogy") || text.contains("analogies") || text.contains("odd one out") || text.contains("coding") || text.contains("syllogism") -> {
                when {
                    text.contains("relation") || text.contains("daughter") || text.contains("father") || text.contains("mother") || text.contains("son") || text.contains("brother") || text.contains("uncle") || text.contains("grandfather") || text.contains("grandmother") -> "afa_blood_relations"
                    text.contains("direction") || text.contains("walks") || text.contains("facing") || text.contains("east") || text.contains("west") || text.contains("north") || text.contains("south") || text.contains("compass") -> "afa_directions"
                    text.contains("ranking") || text.contains("ordering") || text.contains("from top") || text.contains("from bottom") || text.contains("row of") || text.contains("position from") || text.contains("tallest") -> "afa_ranking"
                    text.contains("clock") || text.contains("calendar") || text.contains("friday") || text.contains("monday") || text.contains("tuesday") || text.contains("wednesday") || text.contains("thursday") -> "afa_clocks_calendars"
                    text.contains("series") || text.contains("pattern") || text.contains("sequence") || text.contains("coded") || text.contains("coding") -> "afa_coding_series"
                    text.contains("analogy") || text.contains("analogies") || text.contains("odd one out") || text.contains("classification") || text.contains("syllogism") || text.contains("all engineers") || text.contains("smart") || text.contains("belief") || text.contains("assumptions are") -> "afa_analogies_syllogisms"
                    else -> "afa_analogies_syllogisms"
                }
            }
            // Quantitative Aptitude
            else -> {
                when {
                    text.contains("work") || text.contains("finish") || text.contains("pipe") || text.contains("cistern") || text.contains("leak") || text.contains("fill") || text.contains("tank") -> "afa_time_work"
                    text.contains("profit") || text.contains("loss") || text.contains("discount") || text.contains("marked price") || text.contains("selling price") || text.contains("cost price") || text.contains("Defective") || text.contains("defective") || text.contains("gain of") || text.contains("shopkeeper") || text.contains("percentage increase") -> "afa_pct_profit"
                    text.contains("interest") || text.contains("s.i.") || text.contains("c.i.") || text.contains("compounded") || text.contains("true discount") || text.contains("principal") -> "afa_interest"
                    text.contains("average") || text.contains("mean") || text.contains("mixture") || text.contains("mixes") || text.contains("alligation") || text.contains("partnership") || text.contains("invests") -> "afa_avg_mixtures"
                    text.contains("ratio") || text.contains("proportion") || text.contains("share") || text.contains("ages") || text.contains("years old") || text.contains("twice as old") -> "afa_ratio_ages"
                    text.contains("speed") || text.contains("distance") || text.contains("km/h") || text.contains("m/s") || text.contains("train") || text.contains("scooter") || text.contains("boat") || text.contains("stream") -> "afa_speed_distance"
                    text.contains("probability") || text.contains("permutations") || text.contains("permutation") || text.contains("combinations") || text.contains("combination") || text.contains("drawn") || text.contains("tossed") || text.contains("dice") || text.contains("coin") -> "afa_prob_combinations"
                    text.contains("subset") || text.contains("venn diagram") || text.contains("overlapping") || text.contains("union") || text.contains("intersection") || text.contains("set theory") || text.contains("graph") || text.contains("pie chart") || text.contains("sector") || text.contains("median") || text.contains("variance") || text.contains("standard deviation") || text.contains("statistics") || text.contains("range of") || text.contains("mode of") || text.contains("disjoint") -> "afa_set_data"
                    text.contains("geometry") || text.contains("mensuration") || text.contains("cube") || text.contains("rhombus") || text.contains("diagonal") || text.contains("radius") || text.contains("volume") || text.contains("perimeter") || text.contains("rectangle") || text.contains("sides") || text.contains("circle") -> "afa_geometry"
                    text.contains("log") || text.contains("solve for") || text.contains("roots of") || text.contains("quadratic") || text.contains("simplification") || text.contains("equations") || text.contains("optimization") -> "afa_algebra_logs"
                    else -> "afa_num_systems" // Default / Number systems, HCF, LCM, prime, etc.
                }
            }
        }
    }

    private val list1 = listOf(
        q("afa_1_1", "Aptitude", "Time and Work", "12 workers finish a job in 15 days. How many days for 20 workers?", listOf("9", "10", "12", "15"), "9"),
        q("afa_1_2", "Logical Reasoning", "Number Series", "2, 6, 12, 20, 30, ?", listOf("40", "42", "56", "60"), "42"),
        q("afa_1_3", "Verbal Ability", "Synonyms", "Choose synonym of Rapid", listOf("Slow", "Fast", "Weak", "Small"), "Fast"),
        q("afa_1_4", "Aptitude", "Basic Algebra", "If x/5 = 8.09, find x", listOf("40.45", "45.40", "48", "50"), "40.45"),
        q("afa_1_5", "Logical Reasoning", "Comparisons", "A is taller than B. B is taller than C. Who is tallest?", listOf("A", "B", "C", "Cannot Say"), "A"),
        q("afa_1_6", "Aptitude", "Percentages", "25% of 400 = ?", listOf("50", "75", "100", "125"), "100"),
        q("afa_1_7", "Logical Reasoning", "Letter Series", "Find next: A, C, E, G, ?", listOf("H", "I", "J", "K"), "I"),
        q("afa_1_8", "Verbal Ability", "Antonyms", "Antonym of Honest", listOf("Truthful", "Sincere", "Dishonest", "Kind"), "Dishonest"),
        q("afa_1_9", "Aptitude", "Speed and Distance", "If a car travels at 60 km/h, how far does it travel in 2.5 hours?", listOf("120 km", "140 km", "150 km", "160 km"), "150 km"),
        q("afa_1_10", "Logical Reasoning", "Number Series", "Complete the series: 3, 9, 27, 81, ?", listOf("162", "243", "324", "729"), "243"),
        q("afa_1_11", "Verbal Ability", "Synonyms", "Choose the synonym of 'Abundant'", listOf("Scarce", "Plentiful", "Rare", "Lacking"), "Plentiful"),
        q("afa_1_12", "Aptitude", "Basic Algebra", "Solve for y: 3y - 7 = 14", listOf("5", "6", "7", "8"), "7"),
        q("afa_1_13", "Logical Reasoning", "Blood Relations", "Introducing a girl, Ram said, 'She is the daughter of my mother's only son.' How is Ram related to the girl?", listOf("Brother", "Uncle", "Father", "Grandfather"), "Father"),
        q("afa_1_14", "Aptitude", "Percentages", "What is 15% of 200?", listOf("20", "25", "30", "35"), "30"),
        q("afa_1_15", "Logical Reasoning", "Classification", "Find the odd one out", listOf("Apple", "Mango", "Potato", "Orange"), "Potato"),
        q("afa_1_16", "Verbal Ability", "Antonyms", "Antonym of 'Giant'", listOf("Huge", "Large", "Dwarf", "Tall"), "Dwarf"),
        q("afa_1_17", "Aptitude", "Profit and Loss", "An article bought for $200 is sold for $250. Find the profit percentage.", listOf("20%", "25%", "30%", "50%"), "25%"),
        q("afa_1_18", "Logical Reasoning", "Letter Series", "Complete the series: Z, X, V, T, ?", listOf("R", "S", "U", "Q"), "R"),
        q("afa_1_19", "Verbal Ability", "Synonyms", "Choose the synonym of 'Courageous'", listOf("Timid", "Fearful", "Brave", "Weak"), "Brave"),
        q("afa_1_20", "Aptitude", "Basic Algebra", "If 5x + 4 = 24, find x", listOf("2", "3", "4", "5"), "4"),
        q("afa_1_21", "Logical Reasoning", "Number Series", "Look at this series: 7, 10, 8, 11, 9, 12, ... What number should come next?", listOf("7", "10", "12", "13"), "10"),
        q("afa_1_22", "Aptitude", "Averages", "Find the average of 10, 20, 30, 40, and 50.", listOf("25", "30", "35", "40"), "30"),
        q("afa_1_23", "Verbal Ability", "Antonyms", "Antonym of 'Arrive'", listOf("Enter", "Depart", "Reach", "Welcome"), "Depart"),
        q("afa_1_24", "Logical Reasoning", "Coding-Decoding", "If BOOK is coded as 26611, how is PEN coded?", listOf("16514", "15513", "16414", "17615"), "16514"),
        q("afa_1_25", "Aptitude", "Speed and Distance", "A train 100 meters long passes a bridge at a speed of 72 km/h in 25 seconds. Find the length of the bridge.", listOf("300m", "400m", "500m", "600m"), "400m"),
        q("afa_1_26", "Logical Reasoning", "Number Series", "Find the next term: 1, 4, 9, 16, 25, ?", listOf("30", "35", "36", "49"), "36"),
        q("afa_1_27", "Verbal Ability", "Synonyms", "Choose the synonym of 'Brief'", listOf("Short", "Long", "Detailed", "Large"), "Short"),
        q("afa_1_28", "Aptitude", "Geometry", "If a rectangle has a length of 8cm and a width of 5cm, what is its perimeter?", listOf("13cm", "26cm", "40cm", "35cm"), "26cm"),
        q("afa_1_29", "Logical Reasoning", "Directions", "Point Q is 10 meters East of Point P. Point R is 10 meters North of Point Q. In which direction is R with respect to P?", listOf("North-East", "North-West", "South-East", "South-West"), "North-East"),
        q("afa_1_30", "Aptitude", "Number System", "What is the square root of 144?", listOf("11", "12", "13", "14"), "12"),
        q("afa_1_31", "Logical Reasoning", "Alphanumeric Series", "Complete the pattern: B2D, E3G, H4J, ?", listOf("K5M", "K5N", "L5M", "J5M"), "K5M"),
        q("afa_1_32", "Verbal Ability", "Antonyms", "Antonym of 'Generous'", listOf("Kind", "Stingy", "Benevolent", "Helpful"), "Stingy"),
        q("afa_1_33", "Aptitude", "Ratio and Proportion", "The ratio of two numbers is 3:4. If their sum is 70, find the larger number.", listOf("30", "35", "40", "45"), "40"),
        q("afa_1_34", "Aptitude", "Percentages", "Find the missing value: 80% of ? = 64", listOf("70", "75", "80", "85"), "80"),
        q("afa_1_35", "Logical Reasoning", "Analogies", "If 'Melt' is related to 'Liquid', then 'Freeze' is related to what?", listOf("Ice", "Solid", "Condense", "Water"), "Solid"),
        q("afa_1_36", "Verbal Ability", "Synonyms", "Choose the synonym of 'Fragile'", listOf("Strong", "Delicate", "Heavy", "Tough"), "Delicate"),
        q("afa_1_37", "Aptitude", "Simple Interest", "A sum of money doubles itself in 5 years at simple interest. What is the rate of interest per annum?", listOf("10%", "12%", "15%", "20%"), "20%"),
        q("afa_1_38", "Aptitude", "Number System", "Which number is a prime number?", listOf("9", "15", "21", "23"), "23"),
        q("afa_1_39", "Logical Reasoning", "Letter Series", "Find the next letter in the sequence: A, E, I, M, ?", listOf("P", "Q", "R", "U"), "Q"),
        q("afa_1_40", "Verbal Ability", "Antonyms", "Antonym of 'Artificial'", listOf("Fake", "Natural", "Synthetic", "Man-made"), "Natural"),
        q("afa_1_41", "Aptitude", "Simplification", "Solve: (40 + 8) / 2 - 3", listOf("21", "24", "27", "20"), "21"),
        q("afa_1_42", "Logical Reasoning", "Ranking & Ordering", "In a row of 30 students, A is 12th from the left. What is his position from the right?", listOf("18th", "19th", "20th", "21st"), "19th"),
        q("afa_1_43", "Verbal Ability", "Synonyms", "Choose the synonym of 'Gloom'", listOf("Happiness", "Sadness", "Bright", "Light"), "Sadness"),
        q("afa_1_44", "Aptitude", "Number System", "Find HCF of 24 and 36", listOf("6", "12", "18", "24"), "12"),
        q("afa_1_45", "Aptitude", "Linear Equations", "If 3x + 2y = 12 and y = 3, what is the value of x?", listOf("1", "2", "3", "4"), "2"),
        q("afa_1_46", "Logical Reasoning", "Analogies", "Complete the analogy: Clock : Time :: Thermometer : ?", listOf("Heat", "Radiation", "Temperature", "Energy"), "Temperature"),
        q("afa_1_47", "Verbal Ability", "Antonyms", "Antonym of 'Vague'", listOf("Clear", "Unclear", "Blurry", "Hazy"), "Clear"),
        q("afa_1_48", "Aptitude", "Profit and Loss", "A man buys a cycle for $1000 and sells it at a loss of 10%. What is the selling price?", listOf("$850", "$900", "$950", "$1100"), "$900"),
        q("afa_1_49", "Logical Reasoning", "Calendar", "If Friday was the day before yesterday, what day will it be day after tomorrow?", listOf("Monday", "Tuesday", "Wednesday", "Thursday"), "Tuesday"),
        q("afa_1_50", "Logical Reasoning", "Number Series", "What is the next number in the pattern: 100, 95, 85, 70, ?", listOf("60", "55", "50", "45"), "50"),
        q("afa_1_51", "Aptitude", "Percentages", "If a laptop costs $800 after a 20% discount, what was its original price?", listOf("$900", "$1000", "$1100", "$1200"), "$1000"),
        q("afa_1_52", "Logical Reasoning", "Number Series", "Find the next number in the sequence: 5, 11, 23, 47, ?", listOf("94", "95", "96", "100"), "95"),
        q("afa_1_53", "Verbal Ability", "Synonyms", "Choose the synonym of 'Industrious'", listOf("Lazy", "Hardworking", "Wealthy", "Intelligent"), "Hardworking"),
        q("afa_1_54", "Aptitude", "Exponents", "Evaluate: 4^3 - 5^2", listOf("39", "44", "19", "25"), "39"),
        q("afa_1_55", "Logical Reasoning", "Blood Relations", "Pointing to a man, a woman said, 'His mother is the only daughter of my father.' How is the woman related to the man?", listOf("Sister", "Grandmother", "Mother", "Aunt"), "Mother"),
        q("afa_1_56", "Aptitude", "Geometry", "A cube has a volume of 64 cubic centimeters. What is the length of one side?", listOf("4 cm", "6 cm", "8 cm", "16 cm"), "4 cm"),
        q("afa_1_57", "Logical Reasoning", "Alphanumeric Series", "Complete the series: J9, L16, N25, P36, ?", listOf("R47", "Q49", "R49", "S49"), "R49"),
        q("afa_1_58", "Verbal Ability", "Antonyms", "Antonym of 'Prohibit'", listOf("Ban", "Allow", "Forbid", "Prevent"), "Allow"),
        q("afa_1_59", "Aptitude", "Speed and Distance", "A stream flows at 3 km/h. A boat moves at 15 km/h in still water. What is the boat's speed going downstream?", listOf("12 km/h", "15 km/h", "18 km/h", "45 km/h"), "18 km/h"),
        q("afa_1_60", "Aptitude", "Percentages", "If 40% of a number is equal to 120, what is the number?", listOf("200", "250", "300", "400"), "300"),
        q("afa_1_61", "Logical Reasoning", "Classification", "Find the odd one out", listOf("Circle", "Triangle", "Sphere", "Square"), "Sphere"),
        q("afa_1_62", "Verbal Ability", "Synonyms", "Choose the synonym of 'Imminent'", listOf("Distant", "Famous", "Impending", "Delayed"), "Impending"),
        q("afa_1_63", "Aptitude", "Linear Equations", "If 7x - 9 = 5x + 15, find x", listOf("6", "10", "12", "15"), "12"),
        q("afa_1_64", "Aptitude", "Averages", "Find the average of the first five prime numbers.", listOf("5.0", "5.6", "6.0", "6.8"), "5.6"),
        q("afa_1_65", "Logical Reasoning", "Coding-Decoding", "If WATER is coded as YCVGT, how is HGCTV coded?", listOf("FIREY", "WATER", "EARTH", "FATHER"), "FATHER"),
        q("afa_1_66", "Verbal Ability", "Antonyms", "Antonym of 'Expand'", listOf("Grow", "Shrink", "Stretch", "Spread"), "Shrink"),
        q("afa_1_67", "Aptitude", "Time and Work", "A tank can be filled by Pipe A in 4 hours and emptied by Pipe B in 6 hours. If both pipes are open, how long to fill the tank?", listOf("8 hours", "10 hours", "12 hours", "24 hours"), "12 hours"),
        q("afa_1_68", "Logical Reasoning", "Number Series", "Complete the pattern: 2, 3, 5, 7, 11, 13, ?", listOf("15", "17", "19", "21"), "17"),
        q("afa_1_69", "Verbal Ability", "Synonyms", "Choose the synonym of 'Authentic'", listOf("Fake", "Genuine", "False", "Copied"), "Genuine"),
        q("afa_1_70", "Aptitude", "Geometry", "If the radius of a circle is doubled, its area increases by how many times?", listOf("2 times", "3 times", "4 times", "8 times"), "4 times"),
        q("afa_1_71", "Logical Reasoning", "Directions", "A man walks 6 km North, then turns East and walks 8 km. How far is he from his starting point?", listOf("10 km", "14 km", "12 km", "7 km"), "10 km"),
        q("afa_1_72", "Aptitude", "Decimals", "What is the value of 0.05 * 0.06?", listOf("0.3", "0.03", "0.003", "0.0003"), "0.003"),
        q("afa_1_73", "Verbal Ability", "Antonyms", "Antonym of 'Amusing'", listOf("Funny", "Boring", "Witty", "Entertaining"), "Boring"),
        q("afa_1_74", "Logical Reasoning", "Analogies", "Complete the analogy: Tree : Forest :: Soldier : ?", listOf("War", "Army", "Gun", "Barracks"), "Army"),
        q("afa_1_75", "Aptitude", "Time and Work", "A worker is paid $120 for 8 hours of work. How much will they earn for 12 hours of work?", listOf("$150", "$160", "$180", "$200"), "$180"),
        q("afa_1_76", "Logical Reasoning", "Letter Series", "Find the next letters in the series: AB, DE, GH, JK, ?", listOf("LM", "MN", "NO", "OP"), "MN"),
        q("afa_1_77", "Verbal Ability", "Synonyms", "Choose the synonym of 'Cease'", listOf("Start", "Stop", "Continue", "Prolong"), "Stop"),
        q("afa_1_78", "Aptitude", "Basic Algebra", "Solve for m: m/4 + 3 = 9", listOf("12", "16", "20", "24"), "24"),
        q("afa_1_79", "Aptitude", "Percentages", "In a class of 40 students, 60% are girls. How many boys are there?", listOf("14", "16", "24", "26"), "16"),
        q("afa_1_80", "Verbal Ability", "Antonyms", "Antonym of 'Despair'", listOf("Misery", "Hope", "Sadness", "Pain"), "Hope"),
        q("afa_1_81", "Aptitude", "Compound Interest", "Find the compound interest on $1000 for 2 years at 10% per annum compounded annually.", listOf("$200", "$210", "$220", "$250"), "$210"),
        q("afa_1_82", "Logical Reasoning", "Number Series", "Complete the pattern: 1, 8, 27, 64, ?", listOf("100", "121", "125", "216"), "125"),
        q("afa_1_83", "Verbal Ability", "Synonyms", "Choose the synonym of 'Flawless'", listOf("Perfect", "Broken", "Damaged", "Defective"), "Perfect"),
        q("afa_1_84", "Aptitude", "Percentages", "If 12 out of 30 items are defective, what percentage of items are not defective?", listOf("40%", "50%", "60%", "70%"), "60%"),
        q("afa_1_85", "Logical Reasoning", "Ages", "Five years ago, Sam was twice as old as Alice. If Alice is 15 years old now, how old is Sam today?", listOf("20", "25", "30", "35"), "25"),
        q("afa_1_86", "Logical Reasoning", "Letter Series", "Find the missing term: AZ, CX, EV, ?", listOf("GT", "GS", "HS", "FU"), "GT"),
        q("afa_1_87", "Verbal Ability", "Antonyms", "Antonym of 'Lenient'", listOf("Strict", "Kind", "Gentle", "Merciful"), "Strict"),
        q("afa_1_88", "Aptitude", "Mixtures", "A shopkeeper mixes 2 kg of sugar worth $30/kg with 3 kg of sugar worth $40/kg. Find the average cost per kg of the mixture.", listOf("$34", "$35", "$36", "$38"), "$36"),
        q("afa_1_89", "Aptitude", "Exponents", "If 3^x = 81, find the value of x", listOf("3", "4", "5", "6"), "4"),
        q("afa_1_90", "Logical Reasoning", "Analogies", "Complete the analogy: Page : Book :: Brick : ?", listOf("Wall", "Mud", "Mason", "Kiln"), "Wall"),
        q("afa_1_91", "Verbal Ability", "Synonyms", "Choose the synonym of 'Lethargic'", listOf("Active", "Sluggish", "Energetic", "Quick"), "Sluggish"),
        q("afa_1_92", "Aptitude", "Number System", "Find LCM of 12, 15, and 20.", listOf("45", "60", "90", "120"), "60"),
        q("afa_1_93", "Aptitude", "Scales & Maps", "A map scale is 1:50,000. If two towns are 4 cm apart on the map, what is the actual distance between them?", listOf("2 km", "5 km", "20 km", "50 km"), "2 km"),
        q("afa_1_94", "Verbal Ability", "Antonyms", "Antonym of 'Scarce'", listOf("Rare", "Abundant", "Few", "Limited"), "Abundant"),
        q("afa_1_95", "Aptitude", "Simplification", "Solve: 15 * 4 - (12 + 8) / 4", listOf("10", "15", "55", "58"), "55"),
        q("afa_1_96", "Logical Reasoning", "Calendar", "If Monday falls on the 3rd of a month, what will be the date on the fourth Monday of that month?", listOf("17th", "24th", "25th", "31st"), "24th"),
        q("afa_1_97", "Verbal Ability", "Synonyms", "Choose the synonym of 'Obligatory'", listOf("Optional", "Compulsory", "Voluntary", "Free"), "Compulsory"),
        q("afa_1_98", "Aptitude", "Geometry & Area", "If the side of a square is increased by 10%, by what percentage does its area increase?", listOf("10%", "20%", "21%", "100%"), "21%"),
        q("afa_1_99", "Logical Reasoning", "Classification", "Find the odd one out", listOf("Envy", "Hatred", "Jealousy", "Empathy"), "Empathy"),
        q("afa_1_100", "Logical Reasoning", "Number Series", "Complete the pattern: 4, 9, 19, 39, ?", listOf("59", "69", "79", "89"), "79"),
        q("afa_1_101", "Aptitude", "Permutations & Combinations", "In how many distinct ways can the letters of the word 'CORPORATION' be arranged so that the vowels always come together?", listOf("4800", "9600", "50400", "120960"), "50400"),
        q("afa_1_102", "Aptitude", "Probability", "A bag contains 4 white, 5 red, and 6 blue balls. Three balls are drawn at random. What is the probability that they are all of different colors?", listOf("24/91", "4/13", "7/15", "12/65"), "24/91"),
        q("afa_1_103", "Aptitude", "Time and Work", "A, B, and C can complete a task in 10, 12, and 15 days respectively. They start working together, but A leaves 5 days before completion and B leaves 3 days after A left. How long did the work last?", listOf("6 days", "7 days", "8 days", "5 days"), "7 days"),
        q("afa_1_104", "Aptitude", "Speed and Distance", "A man covers a certain distance on a scooter. If he travels 3 km/h faster, he would take 40 minutes less. If he travels 2 km/h slower, he would take 40 minutes more. Find the total distance.", listOf("36 km", "40 km", "48 km", "50 km"), "40 km"),
        q("afa_1_105", "Aptitude", "Mixtures & Alligations", "A vessel contains a mixture of milk and water in the ratio 7:5. When 9 liters of mixture are drawn off and the vessel is filled with water, the ratio of milk and water becomes 7:9. How many liters of milk were contained by the vessel initially?", listOf("14 liters", "21 liters", "24 liters", "28 liters"), "21 liters"),
        q("afa_1_106", "Aptitude", "Profit and Loss", "A dealer sells an article at a gain of 15%. Had he bought it for 10% less and sold it for $4 less, he would have gained 25%. Find the cost price of the article.", listOf("$140", "$150", "$160", "$180"), "$160"),
        q("afa_1_107", "Aptitude", "Compound Interest", "The difference between simple and compound interest compounded annually on a certain sum of money for 3 years at 10% per annum is $93. Find the sum.", listOf("$2500", "$3000", "$3500", "$4000"), "$3000"),
        q("afa_1_108", "Aptitude", "Boats and Streams", "A motorboat whose speed is 15 km/h in still water goes 30 km downstream and comes back in a total of 4 hours 30 minutes. Find the speed of the stream.", listOf("4 km/h", "5 km/h", "6 km/h", "8 km/h"), "5 km/h"),
        q("afa_1_109", "Aptitude", "Geometry & Mensuration", "A cylindrical container of base radius 6 cm and height 15 cm is full of ice cream. This ice cream is to be filled into cones of height 12 cm and radius 3 cm, having a hemispherical shape on the top. Find the number of such cones.", listOf("8", "10", "12", "15"), "10"),
        q("afa_1_110", "Aptitude", "Number System", "Find the unit digit in the product expression: (256)^137 * (123)^42 * (711)^89", listOf("2", "4", "6", "8"), "4"),
        q("afa_1_111", "Aptitude", "Pipes and Cisterns", "Three pipes A, B, and C can fill a tank in 6 hours. After working together for 2 hours, C is closed and A and B can fill the remaining part in 7 hours. Find the number of hours taken by C alone to fill the tank.", listOf("10 hours", "12 hours", "14 hours", "16 hours"), "14 hours"),
        q("afa_1_112", "Aptitude", "Averages", "The average weight of 8 persons increases by 2.5 kg when a new person comes in place of one of them weighing 65 kg. What is the weight of the new person?", listOf("76 kg", "80 kg", "85 kg", "90 kg"), "85 kg"),
        q("afa_1_113", "Aptitude", "Ratio and Proportion", "An amount of money is to be distributed among A, B, and C in the ratio 2:7:9. If the total share of A and B together is $300 less than C's share, what is B's share?", listOf("$1200", "$1500", "$1800", "$2100"), "$2100"),
        q("afa_1_114", "Aptitude", "Clocks", "At what time between 4 o'clock and 5 o'clock will the hands of a watch be pointing in opposite directions?", listOf("45 min past 4", "50 min past 4", "54 6/11 min past 4", "58 2/11 min past 4"), "54 6/11 min past 4"),
        q("afa_1_115", "Aptitude", "Partnership", "A, B, and C enter into a partnership. A invests 3 times as much as B and B invests two-third of what C invests. At the end of the year, the total profit earned is $6600. What is B's share?", listOf("$1200", "$1500", "$1800", "$2000"), "$1200"),
        q("afa_1_116", "Aptitude", "Surds and Indices", "Evaluate: [(√5 + √3) / (√5 - √3)] + [(√5 - √3) / (√5 + √3)]", listOf("6", "8", "10", "2√15"), "8"),
        q("afa_1_117", "Aptitude", "Ages", "Ten years ago, the age of a father was four times that of his son. Ten years hence, the father's age will be twice that of his son. What are their present ages?", listOf("50, 20", "60, 25", "45, 15", "40, 15"), "50, 20"),
        q("afa_1_118", "Aptitude", "Progressions", "Find the sum of all natural numbers between 100 and 300 which are exactly divisible by 7.", listOf("5586", "5724", "5816", "6004"), "5586"),
        q("afa_1_119", "Aptitude", "HCF & LCM", "The HCF of two numbers is 11 and their LCM is 7700. If one of the numbers is 275, find the other number.", listOf("290", "304", "308", "318"), "308"),
        q("afa_1_120", "Aptitude", "Quadratic Equations", "If the roots of the equation kx^2 + 4x + 1 = 0 are real and equal, what is the value of k?", listOf("2", "4", "6", "8"), "4"),
        q("afa_1_121", "Aptitude", "Permutations & Combinations", "Out of 7 consonants and 4 vowels, how many strings of 3 consonants and 2 vowels can be formed?", listOf("21000", "24400", "25200", "26800"), "25200"),
        q("afa_1_122", "Aptitude", "Probability", "Two dice are tossed simultaneously. What is the probability that the total score is a prime number?", listOf("5/12", "7/12", "1/2", "11/36"), "5/12"),
        q("afa_1_123", "Aptitude", "Time and Work", "A can build a wall in 20 days, which B can destroy in 30 days. If they work on alternate days starting with A, in how many days will the wall be fully built?", listOf("110 days", "113 days", "115 days", "120 days"), "113 days"),
        q("afa_1_124", "Aptitude", "Speed and Distance", "Two trains start at the same time from Aligarh and Delhi and proceed towards each other at 16 km/h and 21 km/h respectively. When they meet, it is found that one train has traveled 60 km more than the other. Find the distance between the two stations.", listOf("420 km", "444 km", "450 km", "480 km"), "444 km"),
        q("afa_1_125", "Aptitude", "Mixtures & Alligations", "In what ratio must water be mixed with milk to gain 20% by selling the mixture at the cost price of pure milk?", listOf("1:4", "1:5", "1:6", "2:5"), "1:5"),
        q("afa_1_126", "Aptitude", "Profit and Loss", "A shopkeeper allows a discount of 10% on the marked price of an item but charges 8% sales tax on the discounted price. If a customer pays $680.40, find the marked price.", listOf("$650", "$700", "$750", "$800"), "$700"),
        q("afa_1_127", "Aptitude", "True Discount", "The true discount on a bill due 9 months hence at 12% per annum is $540. Find the amount of the bill.", listOf("$6000", "$6540", "$5400", "$5940"), "$6540"),
        q("afa_1_128", "Aptitude", "Calendar & Clocks", "A clock is set right at 5 a.m. The clock loses 16 minutes in 24 hours. What will be the true time when the clock indicates 10 p.m. on the 4th day?", listOf("9 p.m.", "10 p.m.", "11 p.m.", "12 a.m."), "11 p.m."),
        q("afa_1_129", "Aptitude", "Geometry", "The lengths of the diagonals of a rhombus are 24 cm and 10 cm. Find the perimeter of the rhombus.", listOf("48 cm", "52 cm", "56 cm", "60 cm"), "52 cm"),
        q("afa_1_130", "Aptitude", "Number System", "What is the remainder when 2^31 is divided by 5?", listOf("1", "2", "3", "4"), "3"),
        q("afa_1_131", "Aptitude", "Logarithms", "If log 2 = 0.30103 and log 3 = 0.47712, find the number of digits in 6^20.", listOf("15", "16", "17", "18"), "16"),
        q("afa_1_132", "Aptitude", "Averages", "The average expenditure of a man for the first 5 months is $120 and for the next 7 months is $130. If he saves $290 in that year, what is his monthly average income?", listOf("$140", "$150", "$160", "$170"), "$150")
    )

    private val list2Group1 = listOf(
        q("afa_2_1", "Aptitude", "Percentages", "A number is increased by 20% and then decreased by 20%. The net percentage change is", listOf("0%", "-4%", "4%", "-2%"), "-4%"),
        q("afa_2_2", "Aptitude", "Percentages", "If 25% of a number is 50, the number is", listOf("100", "150", "200", "250"), "200"),
        q("afa_2_3", "Aptitude", "Percentages", "60 is what percent of 240?", listOf("20%", "25%", "30%", "35%"), "25%"),
        q("afa_2_4", "Aptitude", "Percentages", "A salary increases from ₹40,000 to ₹46,000. The percentage increase is", listOf("10%", "12%", "15%", "20%"), "15%"),
        q("afa_2_5", "Aptitude", "Percentages", "If x is increased by 50%, the new value is", listOf("1.25x", "1.5x", "2x", "2.5x"), "1.5x"),
        q("afa_2_6", "Aptitude", "Ratio and Proportion", "The ratio 24:36 simplifies to", listOf("2:3", "3:2", "4:5", "5:6"), "2:3"),
        q("afa_2_7", "Aptitude", "Ratio and Proportion", "If A:B = 3:4 and B:C = 2:5, then A:C is", listOf("3:10", "6:20", "3:5", "5:6"), "3:10"),
        q("afa_2_8", "Aptitude", "Ratio and Proportion", "Divide ₹700 in the ratio 3:4.", listOf("₹300 and ₹400", "₹350 and ₹350", "₹250 and ₹450", "₹200 and ₹500"), "₹300 and ₹400"),
        q("afa_2_9", "Aptitude", "Ratio and Proportion", "If x:y = 5:2 and y=10, then x equals", listOf("20", "25", "30", "15"), "25"),
        q("afa_2_10", "Aptitude", "Ratio and Proportion", "Two numbers are in the ratio 7:9 and their sum is 64. The larger number is", listOf("28", "36", "40", "42"), "36"),
        q("afa_2_11", "Aptitude", "Averages", "The average of 10, 20 and 30 is", listOf("15", "20", "25", "30"), "20"),
        q("afa_2_12", "Aptitude", "Averages", "The average of first five natural numbers is", listOf("2", "3", "4", "5"), "3"),
        q("afa_2_13", "Aptitude", "Averages", "Average of 5 numbers is 12. Their sum is", listOf("50", "55", "60", "65"), "60"),
        q("afa_2_14", "Aptitude", "Averages", "The average age of 4 persons is 25 years. Total age is", listOf("75", "100", "125", "150"), "100"),
        q("afa_2_15", "Aptitude", "Averages", "Average of 8 and 12 is", listOf("8", "9", "10", "11"), "10"),
        q("afa_2_16", "Aptitude", "Profit and Loss", "An article bought for ₹100 is sold for ₹120. Profit percentage is", listOf("10%", "15%", "20%", "25%"), "20%"),
        q("afa_2_17", "Aptitude", "Profit and Loss", "If CP = ₹500 and SP = ₹450, loss percentage is", listOf("5%", "10%", "15%", "20%"), "10%"),
        q("afa_2_18", "Aptitude", "Profit and Loss", "Selling price of an article with 25% profit on CP ₹200 is", listOf("₹225", "₹240", "₹250", "₹260"), "₹250"),
        q("afa_2_19", "Aptitude", "Profit and Loss", "A trader gains ₹40 on selling an item costing ₹160. Profit percentage is", listOf("20%", "25%", "30%", "35%"), "25%"),
        q("afa_2_20", "Aptitude", "Profit and Loss", "Marked price ₹1000, discount 10%. Selling price is", listOf("₹800", "₹850", "₹900", "₹950"), "₹900"),
        q("afa_2_21", "Aptitude", "Vocabulary", "Choose the synonym of 'Abundant'.", listOf("Scarce", "Plentiful", "Rare", "Empty"), "Plentiful"),
        q("afa_2_22", "Aptitude", "Vocabulary", "Choose the antonym of 'Ancient'.", listOf("Old", "Historic", "Modern", "Traditional"), "Modern"),
        q("afa_2_23", "Aptitude", "Vocabulary", "Choose the synonym of 'Rapid'.", listOf("Slow", "Fast", "Weak", "Quiet"), "Fast"),
        q("afa_2_24", "Aptitude", "Vocabulary", "Choose the antonym of 'Expand'.", listOf("Increase", "Develop", "Contract", "Stretch"), "Contract"),
        q("afa_2_25", "Aptitude", "Vocabulary", "Choose the synonym of 'Precise'.", listOf("Accurate", "Vague", "Random", "Loose"), "Accurate"),
        q("afa_2_26", "Aptitude", "Grammar", "Choose the correct sentence.", listOf("He do not like coffee.", "He does not likes coffee.", "He does not like coffee.", "He not like coffee."), "He does not like coffee."),
        q("afa_2_27", "Aptitude", "Grammar", "Identify the correct passive form: 'She writes a letter.'", listOf("A letter is written by her.", "A letter was written by her.", "A letter written by her.", "A letter has written by her."), "A letter is written by her."),
        q("afa_2_28", "Aptitude", "Grammar", "Choose the correct article: ___ honest man.", listOf("A", "An", "The", "No article"), "An"),
        q("afa_2_29", "Aptitude", "Grammar", "Choose the correctly spelled word.", listOf("Recieve", "Receive", "Receeve", "Receve"), "Receive"),
        q("afa_2_30", "Aptitude", "Grammar", "Fill in the blank: She has been working here ___ 2019.", listOf("for", "since", "from", "by"), "since"),
        q("afa_2_31", "Aptitude", "Numerical Aptitude", "If x + y = 10 and x - y = 2, then x equals", listOf("4", "5", "6", "7"), "6"),
        q("afa_2_32", "Aptitude", "Numerical Aptitude", "The value of 2^5 is", listOf("16", "25", "32", "64"), "32"),
        q("afa_2_33", "Aptitude", "Numerical Aptitude", "The square root of 144 is", listOf("10", "11", "12", "13"), "12"),
        q("afa_2_34", "Aptitude", "Numerical Aptitude", "The value of (15 × 4) ÷ 3 is", listOf("15", "18", "20", "25"), "20"),
        q("afa_2_35", "Aptitude", "Numerical Aptitude", "If 3x = 27, x equals", listOf("6", "7", "8", "9"), "9"),
        q("afa_2_36", "Aptitude", "GATE Numerical", "A train travels 120 km in 2 hours. Its average speed is", listOf("40 km/h", "50 km/h", "60 km/h", "70 km/h"), "60 km/h"),
        q("afa_2_37", "Aptitude", "GATE Numerical", "Simple interest on ₹1000 at 10% per annum for 2 years is", listOf("₹100", "₹150", "₹200", "₹250"), "₹200"),
        q("afa_2_38", "Aptitude", "GATE Numerical", "The HCF of 24 and 36 is", listOf("6", "8", "12", "18"), "12"),
        q("afa_2_39", "Aptitude", "GATE Numerical", "The LCM of 6 and 8 is", listOf("12", "18", "24", "48"), "24"),
        q("afa_2_40", "Aptitude", "GATE Numerical", "If a car covers 180 km using 15 litres of fuel, mileage is", listOf("10 km/l", "12 km/l", "15 km/l", "18 km/l"), "12 km/l"),
        q("afa_2_41", "Aptitude", "Sentence Completion", "Choose the appropriate word: The results were ___ than expected.", listOf("good", "better", "best", "well"), "better"),
        q("afa_2_42", "Aptitude", "Sentence Completion", "The project was completed ___ time.", listOf("on", "at", "in", "for"), "on"),
        q("afa_2_43", "Aptitude", "Sentence Completion", "He is one of the ___ students in the class.", listOf("bright", "brighter", "brightest", "most bright"), "brightest"),
        q("afa_2_44", "Aptitude", "Sentence Completion", "Neither Ravi nor his friends ___ present.", listOf("was", "were", "is", "be"), "were"),
        q("afa_2_45", "Aptitude", "Sentence Completion", "The meeting has been postponed ___ next week.", listOf("to", "for", "in", "at"), "to"),
        q("afa_2_46", "Aptitude", "Theory", "A percentage is a fraction whose denominator is", listOf("10", "50", "100", "1000"), "100"),
        q("afa_2_47", "Aptitude", "Theory", "The arithmetic mean is commonly called", listOf("Median", "Mode", "Average", "Range"), "Average"),
        q("afa_2_48", "Aptitude", "Theory", "Profit occurs when", listOf("SP = CP", "SP > CP", "SP < CP", "Discount > MP"), "SP > CP"),
        q("afa_2_49", "Aptitude", "Theory", "Ratio compares", listOf("Two quantities of same kind", "Three quantities", "Angles only", "Percentages only"), "Two quantities of same kind"),
        q("afa_2_50", "Aptitude", "Theory", "A synonym is a word with", listOf("Opposite meaning", "Similar meaning", "No meaning", "Double meaning"), "Similar meaning")
    )

    private val list2Group2 = listOf(
        q("afa_2_51", "Aptitude", "Time and Work", "A can complete a work in 10 days. The fraction of work completed by A in one day is", listOf("1/5", "1/10", "1/15", "1/20"), "1/10"),
        q("afa_2_52", "Aptitude", "Time and Work", "A can do a job in 12 days and B in 18 days. Together they can complete it in", listOf("6 days", "7.2 days", "8 days", "9 days"), "7.2 days"),
        q("afa_2_53", "Aptitude", "Time and Work", "If a worker completes 25% of a job in 5 days, the entire job will take", listOf("10 days", "15 days", "20 days", "25 days"), "20 days"),
        q("afa_2_54", "Aptitude", "Time and Work", "A is twice as efficient as B. If B takes 20 days, A takes", listOf("5 days", "10 days", "15 days", "20 days"), "10 days"),
        q("afa_2_55", "Aptitude", "Time and Work", "If 5 men can complete a work in 12 days, 10 men can complete it in", listOf("4 days", "5 days", "6 days", "8 days"), "6 days"),
        q("afa_2_56", "Aptitude", "Pipes and Cisterns", "A pipe fills a tank in 8 hours. It fills what fraction of the tank in 1 hour?", listOf("1/4", "1/6", "1/8", "1/10"), "1/8"),
        q("afa_2_57", "Aptitude", "Pipes and Cisterns", "Pipe A fills a tank in 12 h and Pipe B in 18 h. Together they fill it in", listOf("6 h", "7.2 h", "8 h", "9 h"), "7.2 h"),
        q("afa_2_58", "Aptitude", "Pipes and Cisterns", "A pipe fills a tank in 10 h and a leak empties it in 20 h. Net filling time is", listOf("15 h", "20 h", "25 h", "30 h"), "20 h"),
        q("afa_2_59", "Aptitude", "Pipes and Cisterns", "Two identical pipes each fill a tank in 16 h. Together they fill it in", listOf("4 h", "6 h", "8 h", "10 h"), "8 h"),
        q("afa_2_60", "Aptitude", "Pipes and Cisterns", "A tank is filled by a pipe at 1/5 tank per hour. Time required is", listOf("3 h", "4 h", "5 h", "6 h"), "5 h"),
        q("afa_2_61", "Aptitude", "Time Speed Distance", "A car travels 240 km in 4 h. Its speed is", listOf("40 km/h", "50 km/h", "60 km/h", "80 km/h"), "60 km/h"),
        q("afa_2_62", "Aptitude", "Time Speed Distance", "Distance covered in 3 h at 50 km/h is", listOf("100 km", "120 km", "150 km", "180 km"), "150 km"),
        q("afa_2_63", "Aptitude", "Time Speed Distance", "Time taken to cover 180 km at 60 km/h is", listOf("2 h", "3 h", "4 h", "5 h"), "3 h"),
        q("afa_2_64", "Aptitude", "Time Speed Distance", "Average speed for equal distances at 40 km/h and 60 km/h is", listOf("48 km/h", "50 km/h", "52 km/h", "54 km/h"), "48 km/h"),
        q("afa_2_65", "Aptitude", "Time Speed Distance", "A train 200 m long crosses a pole in 10 s. Its speed is", listOf("20 m/s", "25 m/s", "30 m/s", "40 m/s"), "20 m/s"),
        q("afa_2_66", "Aptitude", "Simple Interest", "Simple interest on ₹5000 at 8% per annum for 2 years is", listOf("₹600", "₹700", "₹800", "₹900"), "₹800"),
        q("afa_2_67", "Aptitude", "Simple Interest", "Principal = ₹1000, Rate = 10%, Time = 3 years. SI =", listOf("₹200", "₹250", "₹300", "₹350"), "₹300"),
        q("afa_2_68", "Aptitude", "Simple Interest", "Amount on ₹2000 at 5% SI for 2 years is", listOf("₹2100", "₹2200", "₹2300", "₹2400"), "₹2200"),
        q("afa_2_69", "Aptitude", "Simple Interest", "Rate of SI if ₹100 becomes ₹120 in 4 years is", listOf("4%", "5%", "6%", "8%"), "5%"),
        q("afa_2_70", "Aptitude", "Simple Interest", "SI formula is", listOf("PRT/100", "P+R+T", "PR/100", "PT/100"), "PRT/100"),
        q("afa_2_71", "Aptitude", "Compound Interest", "Compound amount on ₹1000 at 10% for 2 years is", listOf("₹1100", "₹1200", "₹1210", "₹1220"), "₹1210"),
        q("afa_2_72", "Aptitude", "Compound Interest", "CI on ₹1000 at 10% for 2 years is", listOf("₹200", "₹210", "₹220", "₹230"), "₹210"),
        q("afa_2_73", "Aptitude", "Compound Interest", "Amount formula for CI is", listOf("P(1+r)^n", "P+r+n", "PRn", "P(1-r)^n"), "P(1+r)^n"),
        q("afa_2_74", "Aptitude", "Compound Interest", "Amount on ₹2000 at 5% for 1 year is", listOf("₹2050", "₹2100", "₹2150", "₹2200"), "₹2100"),
        q("afa_2_75", "Aptitude", "Compound Interest", "CI is always ___ SI for the same principal, rate and time (>1 year).", listOf("Less than", "Equal to", "Greater than", "Unrelated"), "Greater than"),
        q("afa_2_76", "Aptitude", "Mixtures and Allegations", "A mixture contains milk and water in the ratio 3:1. Fraction of milk is", listOf("1/4", "1/3", "3/4", "4/5"), "3/4"),
        q("afa_2_77", "Aptitude", "Mixtures and Allegations", "A 20 L mixture with milk:water = 4:1 contains water equal to", listOf("2 L", "4 L", "5 L", "8 L"), "4 L"),
        q("afa_2_78", "Aptitude", "Mixtures and Allegations", "If 10 L water is added to 40 L mixture, total volume becomes", listOf("40 L", "45 L", "50 L", "60 L"), "50 L"),
        q("afa_2_79", "Aptitude", "Mixtures and Allegations", "The rule used to mix two ingredients of different costs is", listOf("Partnership", "Allegation", "Ratio", "Average"), "Allegation"),
        q("afa_2_80", "Aptitude", "Mixtures and Allegations", "Milk:water = 1:4. Fraction of water is", listOf("1/5", "2/5", "3/5", "4/5"), "4/5"),
        q("afa_2_81", "Aptitude", "Series", "Find the next number: 2, 4, 8, 16, ?", listOf("20", "24", "32", "64"), "32"),
        q("afa_2_82", "Aptitude", "Series", "Find the next number: 1, 4, 9, 16, ?", listOf("20", "25", "30", "36"), "25"),
        q("afa_2_83", "Aptitude", "Series", "Find the next term: 3, 6, 12, 24, ?", listOf("36", "42", "48", "60"), "48"),
        q("afa_2_84", "Aptitude", "Series", "Find the next number: 5, 10, 15, 20, ?", listOf("22", "24", "25", "30"), "25"),
        q("afa_2_85", "Aptitude", "Series", "Find the next number: 2, 3, 5, 8, 13, ?", listOf("18", "19", "20", "21"), "21"),
        q("afa_2_86", "Aptitude", "Analogies", "Engineer : Machine :: Doctor : ?", listOf("Patient", "Hospital", "Medicine", "Nurse"), "Patient"),
        q("afa_2_87", "Aptitude", "Analogies", "Book : Read :: Food : ?", listOf("Cook", "Eat", "Serve", "Taste"), "Eat"),
        q("afa_2_88", "Aptitude", "Analogies", "Bird : Fly :: Fish : ?", listOf("Run", "Swim", "Jump", "Walk"), "Swim"),
        q("afa_2_89", "Aptitude", "Analogies", "Square : Four :: Triangle : ?", listOf("Two", "Three", "Five", "Six"), "Three"),
        q("afa_2_90", "Aptitude", "Analogies", "Pen : Write :: Knife : ?", listOf("Draw", "Cut", "Read", "Paint"), "Cut"),
        q("afa_2_91", "Aptitude", "Coding-Decoding", "If CAT is coded as DBU, then DOG is coded as", listOf("EPH", "EOH", "FQI", "DNG"), "EPH"),
        q("afa_2_92", "Aptitude", "Coding-Decoding", "If PEN is coded as QFO, then BOOK is coded as", listOf("CPPL", "CPPM", "BPPM", "DQQL"), "CPPL"),
        q("afa_2_93", "Aptitude", "Coding-Decoding", "If A=1, B=2, ..., Z=26, value of CAT is", listOf("21", "24", "26", "28"), "24"),
        q("afa_2_94", "Aptitude", "Coding-Decoding", "If CODE is written as DPEF, then DATA is written as", listOf("EBUB", "EBTA", "FCVC", "ECUB"), "EBUB"),
        q("afa_2_95", "Aptitude", "Coding-Decoding", "If MATH is coded as NBUI, the coding rule is", listOf("+1 to each letter", "-1 to each letter", "Reverse order", "Alternate letters"), "+1 to each letter"),
        q("afa_2_96", "Aptitude", "Theory", "Speed is defined as", listOf("Distance × Time", "Distance / Time", "Time / Distance", "Distance + Time"), "Distance / Time"),
        q("afa_2_97", "Aptitude", "Theory", "Work done is inversely proportional to", listOf("Efficiency", "Number of workers", "Time taken", "Output"), "Time taken"),
        q("afa_2_98", "Aptitude", "Theory", "Compound interest is calculated on", listOf("Principal only", "Principal and accumulated interest", "Interest only", "Rate only"), "Principal and accumulated interest"),
        q("afa_2_99", "Aptitude", "Theory", "In coding-decoding questions, the primary skill tested is", listOf("Grammar", "Pattern recognition", "Arithmetic", "Vocabulary"), "Pattern recognition"),
        q("afa_2_100", "Aptitude", "Theory", "An analogy identifies", listOf("A numerical value", "A relationship between pairs", "A grammar rule", "A probability"), "A relationship between pairs")
    )

    private val list2Group3 = listOf(
        q("afa_2_101", "Aptitude", "Probability", "A fair coin is tossed once. The probability of getting a head is", listOf("0", "1/4", "1/2", "1"), "1/2"),
        q("afa_2_102", "Aptitude", "Probability", "A die is thrown. Probability of getting an even number is", listOf("1/2", "1/3", "2/3", "1/6"), "1/2"),
        q("afa_2_103", "Aptitude", "Probability", "Probability of getting a number greater than 4 on a die is", listOf("1/6", "1/3", "1/2", "2/3"), "1/3"),
        q("afa_2_104", "Aptitude", "Probability", "Two coins are tossed. Probability of getting exactly one head is", listOf("1/4", "1/2", "3/4", "1"), "1/2"),
        q("afa_2_105", "Aptitude", "Probability", "A card is drawn from a standard deck. Probability of drawing a king is", listOf("1/13", "1/52", "4/13", "1/4"), "1/13"),
        q("afa_2_106", "Aptitude", "Permutations and Combinations", "Number of ways to arrange 3 distinct objects is", listOf("3", "6", "9", "12"), "6"),
        q("afa_2_107", "Aptitude", "Permutations and Combinations", "Number of ways to choose 2 items from 5 items is", listOf("5", "8", "10", "12"), "10"),
        q("afa_2_108", "Aptitude", "Permutations and Combinations", "Number of permutations of 4 distinct letters is", listOf("16", "24", "32", "64"), "24"),
        q("afa_2_109", "Aptitude", "Permutations and Combinations", "Number of ways to arrange letters of 'GATE' is", listOf("12", "18", "24", "30"), "24"),
        q("afa_2_110", "Aptitude", "Permutations and Combinations", "Number of ways to select 1 item from 10 items is", listOf("10", "20", "5", "15"), "10"),
        q("afa_2_111", "Aptitude", "Set Theory", "If A has 3 elements and B has 4 elements with no common elements, A ∪ B has", listOf("7", "12", "1", "0"), "7"),
        q("afa_2_112", "Aptitude", "Set Theory", "If A ∩ B = ∅, then sets are called", listOf("Equal", "Disjoint", "Universal", "Finite"), "Disjoint"),
        q("afa_2_113", "Aptitude", "Set Theory", "Universal set is denoted by", listOf("U", "A", "B", "∅"), "U"),
        q("afa_2_114", "Aptitude", "Set Theory", "If A ⊆ B, then", listOf("All elements of A are in B", "No elements in common", "A is larger than B", "A is empty"), "All elements of A are in B"),
        q("afa_2_115", "Aptitude", "Set Theory", "Number of subsets of a set with n elements is", listOf("n", "2n", "n²", "2^n"), "2^n"),
        q("afa_2_116", "Aptitude", "Venn Diagrams", "Venn diagrams are used to represent", listOf("Numbers", "Sets", "Graphs", "Equations"), "Sets"),
        q("afa_2_117", "Aptitude", "Venn Diagrams", "In a Venn diagram, overlapping region represents", listOf("Union", "Intersection", "Complement", "Difference"), "Intersection"),
        q("afa_2_118", "Aptitude", "Venn Diagrams", "A single circle in Venn diagram represents", listOf("One set", "Two sets", "Three sets", "Universal set only"), "One set"),
        q("afa_2_119", "Aptitude", "Venn Diagrams", "A ∪ B means", listOf("Common elements only", "All elements in A and B", "Only A", "Only B"), "All elements in A and B"),
        q("afa_2_120", "Aptitude", "Venn Diagrams", "A ∩ B means", listOf("All elements", "No elements", "Common elements", "Complement"), "Common elements"),
        q("afa_2_121", "Aptitude", "Data Interpretation", "Data interpretation primarily deals with", listOf("Graphs and tables", "Grammar", "Coding", "Logic gates"), "Graphs and tables"),
        q("afa_2_122", "Aptitude", "Data Interpretation", "A bar graph represents data using", listOf("Lines", "Bars", "Circles", "Points only"), "Bars"),
        q("afa_2_123", "Aptitude", "Data Interpretation", "Pie charts represent data in", listOf("Rectangles", "Angles", "Squares", "Dots"), "Angles"),
        q("afa_2_124", "Aptitude", "Data Interpretation", "Mean of data represents", listOf("Middle value", "Average value", "Most frequent value", "Difference"), "Average value"),
        q("afa_2_125", "Aptitude", "Data Interpretation", "Median is", listOf("Average", "Middle value", "Maximum value", "Minimum value"), "Middle value"),
        q("afa_2_126", "Aptitude", "Statistics", "Mean of 2, 4, 6, 8 is", listOf("4", "5", "6", "7"), "5"),
        q("afa_2_127", "Aptitude", "Statistics", "Median of 1, 3, 5 is", listOf("1", "3", "5", "4"), "3"),
        q("afa_2_128", "Aptitude", "Statistics", "Mode is", listOf("Average", "Middle value", "Most frequent value", "Range"), "Most frequent value"),
        q("afa_2_129", "Aptitude", "Statistics", "Range of 10, 20, 30 is", listOf("10", "20", "30", "40"), "20"),
        q("afa_2_130", "Aptitude", "Statistics", "Variance measures", listOf("Central tendency", "Spread of data", "Count of data", "Sum of data"), "Spread of data"),
        q("afa_2_131", "Aptitude", "Blood Relations", "Pointing to a man, Rahul said: He is my father’s son. The man is Rahul’s", listOf("Father", "Brother", "Uncle", "Cousin"), "Brother"),
        q("afa_2_132", "Aptitude", "Blood Relations", "A is B’s mother, B is C’s father. A is C’s", listOf("Mother", "Grandmother", "Aunt", "Sister"), "Grandmother"),
        q("afa_2_133", "Aptitude", "Blood Relations", "X is Y’s brother, Y is Z’s sister. X is Z’s", listOf("Brother", "Father", "Cousin", "Uncle"), "Brother"),
        q("afa_2_134", "Aptitude", "Blood Relations", "If A is the father of B, and B is the father of C, then A is C’s", listOf("Brother", "Grandfather", "Uncle", "Father"), "Grandfather"),
        q("afa_2_135", "Aptitude", "Blood Relations", "A woman says: He is my mother’s only son. The person is her", listOf("Father", "Brother", "Son", "Uncle"), "Brother"),
        q("afa_2_136", "Aptitude", "Directions", "A man walks 10m north and then 10m east. His final direction from start is", listOf("North-East", "North-West", "South-East", "South-West"), "North-East"),
        q("afa_2_137", "Aptitude", "Directions", "A person moves south, then west. Final direction is", listOf("South-West", "North-East", "North-West", "South-East"), "South-West"),
        q("afa_2_138", "Aptitude", "Directions", "Facing north, a person turns right then left. Final direction is", listOf("East", "West", "North", "South"), "North"),
        q("afa_2_139", "Aptitude", "Directions", "A man walks 5m east, 5m north, 5m west. Final position is", listOf("At start", "North", "East", "West"), "North"),
        q("afa_2_140", "Aptitude", "Directions", "If South-East is changed to North-West, then East becomes", listOf("West", "North", "South", "North-East"), "West"),
        q("afa_2_141", "Aptitude", "Ranking", "If A is 3rd from top in a class of 10, his rank from bottom is", listOf("6", "7", "8", "9"), "8"),
        q("afa_2_142", "Aptitude", "Ranking", "In a row of 20 students, B is 5th from left. His position from right is", listOf("14", "15", "16", "17"), "16"),
        q("afa_2_143", "Aptitude", "Ranking", "If there are 25 students, and rank is 10th from top, from bottom is", listOf("14", "15", "16", "17"), "16"),
        q("afa_2_144", "Aptitude", "Ranking", "A is 2nd from left in a row of 8. From right he is", listOf("6", "7", "8", "9"), "7"),
        q("afa_2_145", "Aptitude", "Ranking", "Total students = 30, rank from bottom = 12. Rank from top is", listOf("17", "18", "19", "20"), "19"),
        q("afa_2_146", "Aptitude", "Logical Reasoning", "All engineers are smart. Some smart are lazy. Conclusion: Some engineers may be lazy is", listOf("True", "False", "Cannot be determined", "Always false"), "Cannot be determined"),
        q("afa_2_147", "Aptitude", "Logical Reasoning", "If all A are B and all B are C, then A are", listOf("Not C", "C", "Some C", "None"), "C"),
        q("afa_2_148", "Aptitude", "Logical Reasoning", "Statement: All cats are animals. Conclusion: All animals are cats.", listOf("True", "False", "Cannot be determined", "Partially true"), "False"),
        q("afa_2_149", "Aptitude", "Logical Reasoning", "Cause and effect questions test", listOf("Memory", "Logical relationship", "Grammar", "Calculation"), "Logical relationship"),
        q("afa_2_150", "Aptitude", "Logical Reasoning", "Assumptions are", listOf("Explicit statements", "Hidden beliefs", "Numbers", "Equations"), "Hidden beliefs")
    )

    private val list2Group4 = listOf(
        q("afa_2_151", "Aptitude", "Data Interpretation", "A table shows sales increased from 100 to 150. Percentage increase is", listOf("25%", "30%", "40%", "50%"), "50%"),
        q("afa_2_152", "Aptitude", "Data Interpretation", "If profit is 20% on cost price 500, selling price is", listOf("550", "600", "650", "700"), "600"),
        q("afa_2_153", "Aptitude", "Data Interpretation", "A pie chart shows 90° sector. Percentage represented is", listOf("10%", "20%", "25%", "30%"), "25%"),
        q("afa_2_154", "Aptitude", "Data Interpretation", "If total students = 200 and 60 prefer tea, percentage is", listOf("20%", "25%", "30%", "35%"), "30%"),
        q("afa_2_155", "Aptitude", "Data Interpretation", "If mean of 5 numbers is 10, sum is", listOf("40", "45", "50", "55"), "50"),
        q("afa_2_156", "Aptitude", "Probability", "Probability of getting two heads in three coin tosses is", listOf("1/8", "3/8", "1/2", "5/8"), "3/8"),
        q("afa_2_157", "Aptitude", "Probability", "Probability of not getting a 6 on a die is", listOf("1/6", "5/6", "2/3", "1/2"), "5/6"),
        q("afa_2_158", "Aptitude", "Probability", "Two dice are thrown. Probability of sum = 7 is", listOf("1/6", "1/12", "1/9", "1/8"), "1/6"),
        q("afa_2_159", "Aptitude", "Probability", "Probability of drawing a red card from a deck is", listOf("1/4", "1/2", "3/4", "1/13"), "1/2"),
        q("afa_2_160", "Aptitude", "Probability", "If event A has probability 0.3, probability of not A is", listOf("0.3", "0.5", "0.7", "1.3"), "0.7"),
        q("afa_2_161", "Aptitude", "Permutation and Combination", "Number of ways to arrange 5 people in a row is", listOf("25", "60", "120", "150"), "120"),
        q("afa_2_162", "Aptitude", "Permutation and Combination", "Number of ways to choose 3 items from 6 items is", listOf("10", "12", "15", "20"), "20"),
        q("afa_2_163", "Aptitude", "Permutation and Combination", "Number of permutations of 'ABC' is", listOf("3", "4", "5", "6"), "6"),
        q("afa_2_164", "Aptitude", "Permutation and Combination", "Number of ways to arrange 4 letters with repetition allowed (A, B, C) is", listOf("64", "81", "27", "16"), "81"),
        q("afa_2_165", "Aptitude", "Permutation and Combination", "Number of ways to select 2 boys from 5 boys is", listOf("5", "10", "15", "20"), "10"),
        q("afa_2_166", "Aptitude", "Statistics", "Mean of 10, 20, 30, 40 is", listOf("20", "25", "30", "35"), "25"),
        q("afa_2_167", "Aptitude", "Statistics", "Median of 2, 4, 6, 8, 10 is", listOf("4", "5", "6", "7"), "6"),
        q("afa_2_168", "Aptitude", "Statistics", "Mode of 1, 2, 2, 3, 4 is", listOf("1", "2", "3", "4"), "2"),
        q("afa_2_169", "Aptitude", "Statistics", "Variance is always", listOf("Negative", "Zero or positive", "Positive only", "Negative or zero"), "Zero or positive"),
        q("afa_2_170", "Aptitude", "Statistics", "Standard deviation measures", listOf("Central value", "Spread of data", "Sum of data", "Count only"), "Spread of data"),
        q("afa_2_171", "Aptitude", "Set Theory", "If A = {1,2} and B = {2,3}, then A ∩ B is", listOf("{1}", "{2}", "{3}", "{1,2,3}"), "{2}"),
        q("afa_2_172", "Aptitude", "Set Theory", "If A = {1,2} and B = {2,3}, then A ∪ B is", listOf("{1,2,3}", "{1,2}", "{2,3}", "{1,3}"), "{1,2,3}"),
        q("afa_2_173", "Aptitude", "Set Theory", "Empty set is denoted by", listOf("0", "∅", "U", "1"), "∅"),
        q("afa_2_174", "Aptitude", "Set Theory", "If a set has no elements, it is called", listOf("Universal set", "Null set", "Finite set", "Infinite set"), "Null set"),
        q("afa_2_175", "Aptitude", "Set Theory", "If a set has n elements, number of subsets is", listOf("n", "n^2", "2^n", "n!"), "2^n"),
        q("afa_2_176", "Aptitude", "Logical Reasoning", "Statement: All pens are books. Conclusion: Some books are pens is", listOf("True", "False", "Cannot be determined", "Always true"), "Cannot be determined"),
        q("afa_2_177", "Aptitude", "Logical Reasoning", "If some A are B and all B are C, then some A are", listOf("C", "Not C", "None", "A only"), "C"),
        q("afa_2_178", "Aptitude", "Logical Reasoning", "Assumption questions test", listOf("Calculation", "Hidden assumptions", "Grammar", "Speed"), "Hidden assumptions"),
        q("afa_2_179", "Aptitude", "Logical Reasoning", "Cause and effect is used to identify", listOf("Time relation", "Logical relation", "Speed", "Distance"), "Logical relation"),
        q("afa_2_180", "Aptitude", "Logical Reasoning", "If all A are B and no B are C, then A and C are", listOf("Related", "Unrelated", "Disjoint", "Equal"), "Disjoint"),
        q("afa_2_181", "Aptitude", "Mixed Aptitude", "A 10% increase followed by 10% decrease results in net change", listOf("0%", "-1%", "-2%", "+1%"), "-1%"),
        q("afa_2_182", "Aptitude", "Mixed Aptitude", "If speed doubles, time taken becomes", listOf("Half", "Double", "Same", "Four times"), "Half"),
        q("afa_2_183", "Aptitude", "Mixed Aptitude", "If work doubles, time becomes", listOf("Half", "Double", "Same", "Four times"), "Double"),
        q("afa_2_184", "Aptitude", "Mixed Aptitude", "If CP increases and SP remains same, profit", listOf("Increases", "Decreases", "Same", "Zero"), "Decreases"),
        q("afa_2_185", "Aptitude", "Mixed Aptitude", "If radius doubles, area becomes", listOf("2 times", "3 times", "4 times", "8 times"), "4 times"),
        q("afa_2_186", "Aptitude", "Optimization", "Maximum value of a function is found using", listOf("Mean", "Derivative", "Ratio", "Sum"), "Derivative"),
        q("afa_2_187", "Aptitude", "Optimization", "Minimum cost problems involve", listOf("Maximization", "Minimization", "Addition", "Subtraction"), "Minimization"),
        q("afa_2_188", "Aptitude", "Optimization", "Optimal value means", listOf("Maximum or minimum", "Average", "Median", "Random"), "Maximum or minimum"),
        q("afa_2_189", "Aptitude", "Optimization", "In GATE problems, optimization often uses", listOf("Graphs only", "Calculus methods", "Tables", "Counting only"), "Calculus methods"),
        q("afa_2_190", "Aptitude", "Optimization", "Derivative equal to zero gives", listOf("Maximum or minimum point", "Random point", "Zero always", "Undefined point"), "Maximum or minimum point"),
        q("afa_2_191", "Aptitude", "Advanced Probability", "If two events are independent, P(A ∩ B) equals", listOf("P(A)+P(B)", "P(A)×P(B)", "P(A)-P(B)", "1"), "P(A)×P(B)"),
        q("afa_2_192", "Aptitude", "Advanced Probability", "If P(A)=0.5 and P(B)=0.5 independent, P(A∩B)=?", listOf("0.25", "0.5", "0.75", "1"), "0.25"),
        q("afa_2_193", "Aptitude", "Advanced Probability", "Probability of at least one head in two tosses is", listOf("1/4", "1/2", "3/4", "1"), "3/4"),
        q("afa_2_194", "Aptitude", "Advanced Probability", "If event is certain, probability is", listOf("0", "1/2", "1", "Undefined"), "1"),
        q("afa_2_195", "Aptitude", "Advanced Probability", "If event is impossible, probability is", listOf("0", "1", "1/2", "Undefined"), "0"),
        q("afa_2_196", "Aptitude", "Revision Mix", "If A takes 5 days and B takes 10 days, together they take", listOf("3.33 days", "4 days", "5 days", "6 days"), "3.33 days"),
        q("afa_2_197", "Aptitude", "Revision Mix", "If CP=100 and SP=110, profit % is", listOf("5%", "10%", "15%", "20%"), "10%"),
        q("afa_2_198", "Aptitude", "Revision Mix", "If distance = 100 km and speed = 25 km/h, time is", listOf("2 h", "3 h", "4 h", "5 h"), "4 h"),
        q("afa_2_199", "Aptitude", "Revision Mix", "If mean of 4 numbers is 20, their sum is", listOf("60", "70", "80", "90"), "80"),
        q("afa_2_200", "Aptitude", "Revision Mix", "A set with 3 elements has how many subsets?", listOf("6", "7", "8", "9"), "8")
    )
}
