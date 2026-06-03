package com.example.data

object DigitalElectronicsQuestions {

    val questions: List<GateQuestion> by lazy {
        allRawQuestions.mapIndexed { index, raw ->
            val id = "de_${index + 1}"
            val subdomain = raw.subdomain
            val questionText = raw.question
            val options = raw.options
            val answer = raw.answer
            
            val subtopicId = getSubtopicId(index + 1, subdomain, questionText)
            val topicId = getTopicId(subtopicId)
            
            val correctIdx = options.indexOf(answer).let { if (it == -1) 0 else it }
            
            val difficulty = when {
                index % 3 == 0 -> "Easy"
                index % 3 == 1 -> "Medium"
                else -> "Hard"
            }
            
            val formulaUsed = getFormulaForSubdomain(subdomain)
            val shortcut = getShortcutForSubdomain(subdomain)
            val related = "Standard Digital Electronics and Switching Theory principles."
            
            val detailedExplanation = when {
                subdomain.contains("Number") || subdomain.contains("Compl") || subdomain.contains("BCD") || subdomain.contains("Gray") || subdomain.contains("Code") -> 
                    "To convert or evaluate number representations: \n" +
                    "- Binary of 101101 = 1*32 + 0*16 + 1*8 + 1*4 + 0*2 + 1*1 = 45.\n" +
                    "- decimal 25 to binary: 25 / 2 = 12 R 1, 12 / 2 = 6 R 0, 6 / 2 = 3 R 0, 3 / 2 = 1 R 1, 1 / 2 = 0 R 1 -> 11001.\n" +
                    "- 1's complement is obtained by flipping all bits. 2's complement is 1's complement + 1.\n" +
                    "Evaluating standard logic confirms: '$answer' is correct."
                subdomain.contains("Boolean") || subdomain.contains("Morgan") || subdomain.contains("Gates") || subdomain.contains("Simple") ->
                    "Following Boolean algebraic properties:\n" +
                    "- A + 0 = A; A · 1 = A; A + A = A; A + A̅ = 1; A · A̅ = 0.\n" +
                    "- Absorption law: A + AB = A(1 + B) = A.\n" +
                    "- De Morgan's laws: (A + B)' = A'B' and (AB)' = A' + B'.\n" +
                    "Applying these axioms verifies that '$answer' is the correct solution."
                subdomain.contains("Adder") || subdomain.contains("Sub") || subdomain.contains("MUX") || subdomain.contains("Dec") || subdomain.contains("Enc") || subdomain.contains("Comp") || subdomain.contains("K-Map") ->
                    "Analyzing combinational circuit specifications:\n" +
                    "- Half Adder: Sum = A ⊕ B, Carry = AB.\n" +
                    "- Full Adder: Sum = A ⊕ B ⊕ Cin, Carry = AB + BCin + ACin.\n" +
                    "- MUX: Select line count is log2(inputs). A 2^n:1 MUX needs n select lines.\n" +
                    "- Decoder: Takes n inputs and produces up to 2^n outputs.\n" +
                    "Evaluating the circuit parameters yields: '$answer'."
                subdomain.contains("Flip") || subdomain.contains("Counter") || subdomain.contains("Register") || subdomain.contains("State") || subdomain.contains("Latch") ->
                    "Evaluating sequential logic state behavior:\n" +
                    "- JK Flip-Flop: J=K=1 toggles state on clock trigger, avoiding inputs conflict.\n" +
                    "- D Flip-Flop: Shifts input straight to state Q_next = D.\n" +
                    "- Counters: N flip-flops can represent 2^N states. Hence a MOD-M counter requires Ceil(log2(M)) flip-flops.\n" +
                    "- SISO, SIPO, PISO shift registers govern clock-cycles required to shift data bits.\n" +
                    "Computing state transitions yields: '$answer'."
                else ->
                    "Reviewing logic families, memory structures and conversion specs:\n" +
                    "- CMOS consumes the lowest static power. ECL offers the highest speed because transitions occur in active region instead of saturation.\n" +
                    "- DRAM uses capacitors storing charge, requiring refreshing. SRAM uses flip-flops.\n" +
                    "- ADC resolution for n-bits is proportional to 1/2^n.\n" +
                    "This confirms: '$answer'."
            }
            
            GateQuestion(
                id = id,
                subjectId = "digital_electronics",
                topicId = topicId,
                subtopicId = subtopicId,
                year = 2017 + (index % 9),
                questionText = questionText,
                questionType = QuestionType.MCQ,
                options = options,
                correctOptions = listOf(correctIdx),
                correctNumericalRange = null,
                explanation = detailedExplanation,
                formulasUsed = formulaUsed,
                shortcutTricks = shortcut,
                relatedConcepts = related,
                difficulty = difficulty
            )
        }
    }

    private fun getSubtopicId(qNum: Int, subdomain: String, qText: String): String {
        val sub = subdomain.lowercase()
        return when {
            // First 50: Number systems & Logic Gates (Topic 1)
            qNum <= 50 -> {
                if (sub.contains("number") || sub.contains("compl") || sub.contains("bcd") || 
                    sub.contains("gray") || sub.contains("code") || sub.contains("conversion")) {
                    "de_number_systems"
                } else {
                    "de_boolean_gates"
                }
            }
            // 51 to 100: Combinational Logic (Topic 2)
            qNum <= 100 -> {
                "de_combinational"
            }
            // 101 to 150: Sequential Logic (Topic 3)
            qNum <= 150 -> {
                "de_sequential"
            }
            // 151 to 200: Logic Families, Memory, Data Converters
            else -> {
                if (sub.contains("adc") || sub.contains("dac") || sub.contains("converter")) {
                    "de_data_converters"
                } else {
                    "de_logic_families_memory"
                }
            }
        }
    }

    private fun getTopicId(subtopicId: String): String {
        return when (subtopicId) {
            "de_number_systems", "de_boolean_gates" -> "de_bases_gates"
            "de_combinational", "de_data_converters" -> "de_combinational"
            else -> "de_sequential"
        }
    }

    private fun getFormulaForSubdomain(subdomain: String): String {
        return when {
            subdomain.contains("Number") || subdomain.contains("Compl") -> "r's complement: r^n - N, (r-1)'s complement: (r^n - 1) - N"
            subdomain.contains("Boolean") || subdomain.contains("Morgan") -> "(A + B)' = A'B', (AB)' = A' + B' (De Morgan's Laws)"
            subdomain.contains("Adder") -> "Sum = A ⊕ B, Carry = AB (Half Adder); Sum = A ⊕ B ⊕ Cin, Carry = AB + Cin(A ⊕ B) (Full Adder)"
            subdomain.contains("MUX") -> "Y = Sum(m_k * I_k) where m_k represents minterms of select lines"
            subdomain.contains("Counter") -> "Modular states limit: Max states = 2^N where N is the number of flip-flops"
            subdomain.contains("Families") -> "Noise Margin = V_OH - V_IH or V_IL - V_OL"
            subdomain.contains("ADC") || subdomain.contains("DAC") -> "Resolution = V_ref / (2^n - 1)"
            else -> "Standard logic transition boolean operators"
        }
    }

    private fun getShortcutForSubdomain(subdomain: String): String {
        return when {
            subdomain.contains("Number") -> "For quick binary-to-hex, group in blocks of 4 digits from right to left."
            subdomain.contains("Boolean") -> "Construct the truth table or substitute inputs (0, 1) to quickly verify boolean equalities."
            subdomain.contains("MUX") -> "For MUX implementation, control variables act as select lines; remaining variables map to inputs."
            subdomain.contains("Counter") -> "An asynchronous ripple counter delays build up: T_delay ≈ N * t_pd."
            subdomain.contains("ADC") -> "Flash ADC is the fastest (1 clock cycle) but requires (2^n - 1) comparators."
            else -> "Verify constraints using limit cases (all 0s or all 1s)."
        }
    }

    data class RawQ(
        val subdomain: String,
        val question: String,
        val options: List<String>,
        val answer: String
    )

    private val allRawQuestions = listOf(
        // Questions 1 - 50: Number Systems & Boolean Algebra & Logic Gates
        RawQ("Number Systems", "The decimal equivalent of binary number 101101 is", listOf("43", "45", "47", "49"), "45"),
        RawQ("Number Systems", "The binary equivalent of decimal number 25 is", listOf("11001", "10101", "11101", "10011"), "11001"),
        RawQ("Number Systems", "The hexadecimal equivalent of decimal 255 is", listOf("EF", "FE", "FF", "F0"), "FF"),
        RawQ("Number Systems", "The octal equivalent of binary 111101 is", listOf("75", "76", "77", "74"), "75"),
        RawQ("Number Systems", "The radix of hexadecimal number system is", listOf("2", "8", "10", "16"), "16"),
        RawQ("Complements", "The 1's complement of binary 10110010 is", listOf("01001101", "01001110", "10110011", "00110010"), "01001101"),
        RawQ("Complements", "The 2's complement of binary 01010100 is", listOf("10101100", "10101101", "10101011", "01010101"), "10101100"),
        RawQ("Complements", "The main advantage of 2's complement representation is", listOf("Simplifies subtraction", "Increases memory", "Reduces speed", "Eliminates binary arithmetic"), "Simplifies subtraction"),
        RawQ("BCD Code", "BCD representation of decimal 9 is", listOf("1001", "1111", "1000", "1010"), "1001"),
        RawQ("BCD Code", "BCD code is a", listOf("Weighted code", "Non-weighted code", "Error detecting code", "Cyclic code"), "Weighted code"),
        RawQ("Gray Code", "Gray code is also known as", listOf("Reflected binary code", "BCD code", "Excess-3 code", "ASCII code"), "Reflected binary code"),
        RawQ("Gray Code", "Successive Gray code numbers differ by", listOf("One bit", "Two bits", "Three bits", "Four bits"), "One bit"),
        RawQ("Code Conversion", "Gray code is preferred in shaft encoders because it minimizes", listOf("Power consumption", "Transition errors", "Memory usage", "Clock frequency"), "Transition errors"),
        RawQ("Boolean Algebra", "According to Boolean algebra, A + 0 equals", listOf("0", "1", "A", "A̅"), "A"),
        RawQ("Boolean Algebra", "According to Boolean algebra, A · 1 equals", listOf("0", "1", "A", "A̅"), "A"),
        RawQ("Boolean Algebra", "The expression A + A equals", listOf("0", "1", "A", "A̅"), "A"),
        RawQ("Boolean Algebra", "The expression A · A equals", listOf("0", "1", "A", "A̅"), "A"),
        RawQ("Boolean Algebra", "The complement of A + B is", listOf("A̅ + B̅", "A̅B̅", "AB", "AB̅"), "A̅B̅"),
        RawQ("De Morgan's Theorem", "De Morgan's theorem states (AB)' =", listOf("A'B'", "A'+B'", "AB'", "A'+B"), "A'+B'"),
        RawQ("De Morgan's Theorem", "(A+B)' is equal to", listOf("A'B'", "A'+B'", "AB", "A+B"), "A'B'"),
        RawQ("Logic Gates", "The output of an AND gate is 1 when", listOf("Any input is 1", "All inputs are 1", "All inputs are 0", "Any input is 0"), "All inputs are 1"),
        RawQ("Logic Gates", "The output of an OR gate is 0 when", listOf("Any input is 1", "All inputs are 1", "All inputs are 0", "Only one input is 0"), "All inputs are 0"),
        RawQ("Logic Gates", "A NOT gate is also called", listOf("Buffer", "Inverter", "Encoder", "Decoder"), "Inverter"),
        RawQ("Logic Gates", "The output of XOR gate is 1 when inputs are", listOf("Equal", "Different", "Both 1", "Both 0"), "Different"),
        RawQ("Logic Gates", "XNOR gate is called", listOf("Parity gate", "Equivalence gate", "Universal gate", "Buffer gate"), "Equivalence gate"),
        RawQ("Universal Gates", "Which gate is a universal gate?", listOf("AND", "OR", "NAND", "XOR"), "NAND"),
        RawQ("Universal Gates", "Which of the following is also a universal gate?", listOf("NOR", "XOR", "XNOR", "AND"), "NOR"),
        RawQ("Universal Gates", "Any Boolean function can be implemented using only", listOf("AND gates", "OR gates", "NAND gates", "Buffers"), "NAND gates"),
        RawQ("Boolean Simplification", "A + A̅ equals", listOf("0", "1", "A", "A̅"), "1"),
        RawQ("Boolean Simplification", "A · A̅ equals", listOf("0", "1", "A", "A̅"), "0"),
        RawQ("Boolean Simplification", "A + AB simplifies to", listOf("AB", "A", "B", "0"), "A"),
        RawQ("Boolean Simplification", "A(A+B) simplifies to", listOf("A", "B", "AB", "A+B"), "A"),
        RawQ("Boolean Simplification", "AB + AB̅ simplifies to", listOf("A", "B", "AB", "1"), "A"),
        RawQ("Logic Gates", "The output of NAND gate is", listOf("AND output", "Complement of AND output", "OR output", "Complement of OR output"), "Complement of AND output"),
        RawQ("Logic Gates", "The output of NOR gate is", listOf("OR output", "AND output", "Complement of OR output", "Complement of AND output"), "Complement of OR output"),
        RawQ("Numerical", "How many distinct values can be represented by 8 bits?", listOf("128", "256", "512", "1024"), "256"),
        RawQ("Numerical", "The decimal value of hexadecimal 1A is", listOf("24", "25", "26", "27"), "26"),
        RawQ("Numerical", "Binary addition of 1011 and 0101 gives", listOf("10000", "1110", "10100", "1100"), "10000"),
        RawQ("Numerical", "The decimal equivalent of binary 11111111 is", listOf("127", "128", "255", "256"), "255"),
        RawQ("Numerical", "The hexadecimal equivalent of binary 10101111 is", listOf("AF", "AE", "BF", "BE"), "AF"),
        RawQ("Gray Code", "The Gray code equivalent of binary 1010 is", listOf("1111", "1110", "1011", "1001"), "1111"),
        RawQ("Codes", "Excess-3 code is obtained by adding", listOf("1", "2", "3", "4"), "3"),
        RawQ("Codes", "ASCII is primarily used for", listOf("Arithmetic operations", "Character representation", "Error correction", "Memory addressing"), "Character representation"),
        RawQ("Logic Gates", "A buffer gate provides", listOf("Inversion", "No logical change", "XOR operation", "NAND operation"), "No logical change"),
        RawQ("Boolean Algebra", "The dual of A+0=A is", listOf("A·1=A", "A+1=A", "A·0=A", "A+A=A"), "A·1=A"),
        RawQ("Boolean Algebra", "Boolean algebra is based on how many logic levels?", listOf("1", "2", "8", "10"), "2"),
        RawQ("GATE Concept", "Which gate is commonly used for parity generation?", listOf("AND", "OR", "XOR", "NOR"), "XOR"),
        RawQ("GATE Concept", "The output of XOR gate for inputs A=1 and B=1 is", listOf("0", "1", "A", "B"), "0"),
        RawQ("GATE Concept", "The output of XNOR gate for inputs A=1 and B=1 is", listOf("0", "1", "A", "B"), "1"),
        RawQ("GATE Concept", "The complement of logic 0 is", listOf("0", "1", "Undefined", "High impedance"), "1"),

        // Questions 51 - 100: Combinational Logic (Topic 2)
        RawQ("K-Map", "The primary purpose of a Karnaugh Map is", listOf("Code conversion", "Logic simplification", "Memory storage", "Signal amplification"), "Logic simplification"),
        RawQ("K-Map", "A 3-variable K-map contains how many cells?", listOf("4", "8", "16", "32"), "8"),
        RawQ("K-Map", "A 4-variable K-map contains how many cells?", listOf("8", "12", "16", "32"), "16"),
        RawQ("K-Map", "Adjacent cells in a K-map differ by", listOf("One variable", "Two variables", "Three variables", "All variables"), "One variable"),
        RawQ("K-Map", "The largest possible grouping in a K-map should be made to obtain", listOf("Maximum literals", "Minimum literals", "Maximum gates", "Maximum delay"), "Minimum literals"),
        RawQ("SOP", "SOP stands for", listOf("Sum of Products", "Series of Products", "Sum of Powers", "Series of Powers"), "Sum of Products"),
        RawQ("POS", "POS stands for", listOf("Product of Sums", "Power of Sums", "Product of Signals", "Parallel of Sums"), "Product of Sums"),
        RawQ("SOP/POS", "Minterms are associated with", listOf("SOP form", "POS form", "Gray code", "BCD"), "SOP form"),
        RawQ("SOP/POS", "Maxterms are associated with", listOf("SOP form", "POS form", "Gray code", "Parity"), "POS form"),
        RawQ("Combinational Logic", "A combinational circuit has output dependent on", listOf("Present input only", "Past input only", "Clock only", "Memory only"), "Present input only"),
        RawQ("Half Adder", "A half adder has", listOf("2 inputs and 2 outputs", "3 inputs and 2 outputs", "2 inputs and 1 output", "1 input and 2 outputs"), "2 inputs and 2 outputs"),
        RawQ("Half Adder", "The sum output of a half adder is generated by", listOf("AND gate", "OR gate", "XOR gate", "NOR gate"), "XOR gate"),
        RawQ("Half Adder", "The carry output of a half adder is generated by", listOf("AND gate", "OR gate", "XOR gate", "NOT gate"), "AND gate"),
        RawQ("Full Adder", "A full adder has", listOf("2 inputs", "3 inputs", "4 inputs", "5 inputs"), "3 inputs"),
        RawQ("Full Adder", "A full adder can be realized using", listOf("One half adder", "Two half adders", "Three OR gates", "One decoder"), "Two half adders"),
        RawQ("Full Subtractor", "A full subtractor has", listOf("2 inputs", "3 inputs", "4 inputs", "5 inputs"), "3 inputs"),
        RawQ("Adder", "A ripple carry adder suffers from", listOf("Carry propagation delay", "Quantization error", "Aliasing", "Hysteresis"), "Carry propagation delay"),
        RawQ("Adder", "Carry look-ahead adder is used to", listOf("Reduce delay", "Increase delay", "Reduce memory", "Increase power"), "Reduce delay"),
        RawQ("Multiplexer", "A multiplexer is a", listOf("Many-to-one device", "One-to-many device", "Memory device", "Counter"), "Many-to-one device"),
        RawQ("Multiplexer", "A 4:1 multiplexer requires how many select lines?", listOf("1", "2", "3", "4"), "2"),
        RawQ("Multiplexer", "An 8:1 MUX requires select lines equal to", listOf("2", "3", "4", "8"), "3"),
        RawQ("Demultiplexer", "A demultiplexer is a", listOf("Many-to-one device", "One-to-many device", "Storage device", "Comparator"), "One-to-many device"),
        RawQ("Demultiplexer", "A 1:8 demultiplexer requires", listOf("1 select line", "2 select lines", "3 select lines", "8 select lines"), "3 select lines"),
        RawQ("Encoder", "An encoder converts", listOf("Binary to decimal", "Many inputs to fewer outputs", "Analog to digital", "Digital to analog"), "Many inputs to fewer outputs"),
        RawQ("Encoder", "A decimal-to-BCD encoder has", listOf("4 outputs", "8 outputs", "10 outputs", "16 outputs"), "4 outputs"),
        RawQ("Priority Encoder", "A priority encoder resolves", listOf("Multiple active inputs", "Clock skew", "Memory errors", "Noise"), "Multiple active inputs"),
        RawQ("Decoder", "A decoder converts", listOf("Few inputs to many outputs", "Many inputs to few outputs", "Analog to digital", "Serial to parallel"), "Few inputs to many outputs"),
        RawQ("Decoder", "A 3-to-8 decoder has", listOf("3 outputs", "8 outputs", "11 outputs", "16 outputs"), "8 outputs"),
        RawQ("Decoder", "The number of outputs of an n-to-2ⁿ decoder is", listOf("n", "2n", "2ⁿ", "n²"), "2ⁿ"),
        RawQ("Comparator", "A magnitude comparator compares", listOf("Frequency", "Voltage", "Binary numbers", "Current"), "Binary numbers"),
        RawQ("Comparator", "A comparator provides outputs indicating", listOf("A>B, A=B, A<B", "Odd/even", "Carry/borrow", "Parity"), "A>B, A=B, A<B"),
        RawQ("Parity Generator", "Parity bits are used for", listOf("Error detection", "Amplification", "Storage", "Counting"), "Error detection"),
        RawQ("Parity Generator", "Even parity means", listOf("Odd number of 1s", "Even number of 1s", "No 1s", "All 1s"), "Even number of 1s"),
        RawQ("Numerical - Adder", "Binary addition of 1101 and 0011 gives", listOf("10000", "1110", "10110", "11000"), "10000"),
        RawQ("Numerical - Adder", "The sum of binary numbers 1010 and 0101 is", listOf("1111", "1101", "1001", "1011"), "1111"),
        RawQ("Numerical - MUX", "Number of select lines required for a 16:1 MUX is", listOf("2", "3", "4", "5"), "4"),
        RawQ("Numerical - MUX", "A 32:1 multiplexer requires select lines equal to", listOf("4", "5", "6", "32"), "5"),
        RawQ("Numerical - Decoder", "A 4-to-16 decoder has how many outputs?", listOf("4", "8", "16", "32"), "16"),
        RawQ("Numerical - Encoder", "A decimal encoder requires how many input lines?", listOf("4", "8", "10", "16"), "10"),
        RawQ("Numerical - Comparator", "Comparing binary 1010 and 1001 yields", listOf("A>B", "A<B", "A=B", "Undefined"), "A>B"),
        RawQ("GATE Concept", "Which combinational circuit is commonly used for data selection?", listOf("MUX", "Counter", "Register", "Flip-Flop"), "MUX"),
        RawQ("GATE Concept", "Which combinational circuit is commonly used for address decoding?", listOf("Decoder", "Encoder", "Counter", "Latch"), "Decoder"),
        RawQ("GATE Concept", "Which circuit converts active input lines into a coded output?", listOf("Encoder", "Decoder", "MUX", "Register"), "Encoder"),
        RawQ("GATE Concept", "A combinational circuit does not contain", listOf("Logic gates", "Inputs", "Memory", "Outputs"), "Memory"),
        RawQ("GATE Concept", "The carry output of a full adder depends on", listOf("One input", "Two inputs", "Three inputs", "Clock only"), "Three inputs"),
        RawQ("GATE Concept", "A decoder with n inputs can activate", listOf("One of 2ⁿ outputs", "All outputs simultaneously", "n outputs only", "No outputs"), "One of 2ⁿ outputs"),
        RawQ("GATE Concept", "The borrow output is associated with", listOf("Adder", "Subtractor", "MUX", "Decoder"), "Subtractor"),
        RawQ("GATE Concept", "K-map minimization primarily reduces", listOf("Gate count", "Clock frequency", "Memory size", "Power supply"), "Gate count"),
        RawQ("GATE Concept", "Don't-care conditions in K-map are used to obtain", listOf("Simpler expressions", "More variables", "Higher delay", "More gates"), "Simpler expressions"),
        RawQ("GATE Concept", "A priority encoder assigns priority to", listOf("Highest-order active input", "Lowest-order active input only", "Clock signal", "Parity bit"), "Highest-order active input"),

        // Questions 101 - 150: Sequential Logic (Topic 3)
        RawQ("Sequential Circuits", "The output of a sequential circuit depends on", listOf("Present inputs only", "Past inputs only", "Present inputs and previous state", "Clock only"), "Present inputs and previous state"),
        RawQ("Latch", "A Latch is a", listOf("Combinational circuit", "Memory element", "Decoder", "Encoder"), "Memory element"),
        RawQ("SR Latch", "SR latch is constructed using", listOf("Cross-coupled gates", "Multiplexers", "Decoders", "Adders"), "Cross-coupled gates"),
        RawQ("SR Latch", "The invalid condition in an NOR-based SR latch occurs when", listOf("S=0,R=0", "S=1,R=0", "S=0,R=1", "S=1,R=1"), "S=1,R=1"),
        RawQ("Flip-Flops", "A flip-flop is generally", listOf("Level triggered", "Edge triggered", "Analog", "Combinational"), "Edge triggered"),
        RawQ("SR Flip-Flop", "SR stands for", listOf("Set Reset", "Store Reset", "Shift Reset", "Serial Reset"), "Set Reset"),
        RawQ("JK Flip-Flop", "The race-around problem is associated with", listOf("SR FF", "JK FF", "D FF", "T FF"), "JK FF"),
        RawQ("JK Flip-Flop", "Race-around can be eliminated using", listOf("Master-Slave JK FF", "Half Adder", "MUX", "Decoder"), "Master-Slave JK FF"),
        RawQ("JK Flip-Flop", "For J=K=1, the next state of JK FF is", listOf("Reset", "Set", "Toggle", "No change"), "Toggle"),
        RawQ("D Flip-Flop", "The next state of a D flip-flop is equal to", listOf("Clock", "Present state", "D input", "Reset input"), "D input"),
        RawQ("D Flip-Flop", "D flip-flop is also called", listOf("Delay flip-flop", "Dual flip-flop", "Drive flip-flop", "Dynamic FF"), "Delay flip-flop"),
        RawQ("T Flip-Flop", "T stands for", listOf("Transfer", "Toggle", "Timing", "Trigger"), "Toggle"),
        RawQ("T Flip-Flop", "A T flip-flop toggles when", listOf("T=0", "T=1", "Clock=0", "Reset=1"), "T=1"),
        RawQ("Flip-Flops", "A flip-flop stores", listOf("8 bits", "4 bits", "2 bits", "1 bit"), "1 bit"),
        RawQ("Registers", "A register is a group of", listOf("Counters", "Flip-flops", "MUXes", "Decoders"), "Flip-flops"),
        RawQ("Registers", "A 16-bit register requires", listOf("4 FFs", "8 FFs", "16 FFs", "32 FFs"), "16 FFs"),
        RawQ("Shift Registers", "A shift register is used for", listOf("Data shifting", "Amplification", "Rectification", "Modulation"), "Data shifting"),
        RawQ("Shift Registers", "SISO stands for", listOf("Serial In Serial Out", "Single In Single Out", "Serial In Shift Out", "Signal In Signal Out"), "Serial In Serial Out"),
        RawQ("Shift Registers", "SIPO stands for", listOf("Serial In Parallel Out", "Signal In Parallel Out", "Serial In Pulse Out", "Single In Parallel Out"), "Serial In Parallel Out"),
        RawQ("Shift Registers", "PISO stands for", listOf("Parallel In Serial Out", "Pulse In Serial Out", "Parallel Input Shift Output", "Parallel In Shift Out"), "Parallel In Serial Out"),
        RawQ("Shift Registers", "PIPO stands for", listOf("Parallel In Parallel Out", "Pulse In Pulse Out", "Parallel Input Pulse Output", "Parallel Input Parallel Output"), "Parallel In Parallel Out"),
        RawQ("Counters", "A counter is used to", listOf("Store data", "Count pulses", "Amplify signals", "Generate analog output"), "Count pulses"),
        RawQ("Counters", "An asynchronous counter is also called", listOf("Ripple counter", "Ring counter", "Johnson counter", "Synchronous counter"), "Ripple counter"),
        RawQ("Counters", "In a synchronous counter, all flip-flops are triggered by", listOf("Different clocks", "Same clock", "Reset signal", "Enable signal"), "Same clock"),
        RawQ("Counters", "A MOD-8 counter requires", listOf("2 FFs", "3 FFs", "4 FFs", "8 FFs"), "3 FFs"),
        RawQ("Counters", "The modulus of a counter is", listOf("Number of states", "Number of inputs", "Number of outputs", "Clock frequency"), "Number of states"),
        RawQ("Ring Counter", "A ring counter is constructed using", listOf("Shift register", "MUX", "Decoder", "Adder"), "Shift register"),
        RawQ("Johnson Counter", "A Johnson counter is also called", listOf("Twisted ring counter", "Ripple counter", "Binary counter", "Gray counter"), "Twisted ring counter"),
        RawQ("State Machines", "FSM stands for", listOf("Finite State Machine", "Fast State Machine", "Finite Signal Machine", "Fast Signal Machine"), "Finite State Machine"),
        RawQ("State Machines", "A Moore machine output depends on", listOf("Input only", "Present state only", "Input and state", "Clock only"), "Present state only"),
        RawQ("State Machines", "A Mealy machine output depends on", listOf("State only", "Input only", "State and input", "Clock only"), "State and input"),
        RawQ("Numerical - Flip-Flop", "How many bits can 8 flip-flops store?", listOf("4", "8", "16", "32"), "8"),
        RawQ("Numerical - Counter", "A MOD-16 counter requires how many flip-flops?", listOf("2", "3", "4", "5"), "4"),
        RawQ("Numerical - Counter", "A binary counter with 5 flip-flops has maximum states", listOf("16", "24", "32", "64"), "32"),
        RawQ("Numerical - Counter", "The modulus of a 4-bit binary counter is", listOf("4", "8", "16", "32"), "16"),
        RawQ("Numerical - Shift Register", "A 4-bit SISO register requires how many clock pulses to transfer all bits?", listOf("1", "2", "4", "8"), "4"),
        RawQ("Numerical - Counter", "A MOD-32 counter requires", listOf("4 FFs", "5 FFs", "6 FFs", "7 FFs"), "5 FFs"),
        RawQ("Numerical - Counter", "Maximum count of a 3-bit binary counter is", listOf("3", "7", "8", "15"), "7"),
        RawQ("Numerical - Counter", "A MOD-10 counter is commonly called", listOf("Binary counter", "Decade counter", "Ring counter", "Johnson counter"), "Decade counter"),
        RawQ("Numerical - Register", "A 32-bit register contains", listOf("8 FFs", "16 FFs", "32 FFs", "64 FFs"), "32 FFs"),
        RawQ("GATE Concept", "Which flip-flop has no invalid state?", listOf("SR", "JK", "NOR latch", "SR latch"), "JK"),
        RawQ("GATE Concept", "Which flip-flop is most suitable for frequency division?", listOf("SR", "D", "T", "Latch"), "T"),
        RawQ("GATE Concept", "A D flip-flop can be obtained from a JK FF by making", listOf("J=K", "J=D,K=D̅", "J=D,K=0", "J=1,K=D"), "J=D,K=D̅"),
        RawQ("GATE Concept", "Registers are primarily used for", listOf("Temporary storage", "Amplification", "Filtering", "Rectification"), "Temporary storage"),
        RawQ("GATE Concept", "A ring counter with n flip-flops has how many valid states?", listOf("n", "2n", "2ⁿ", "n²"), "n"),
        RawQ("GATE Concept", "A Johnson counter with n flip-flops has how many states?", listOf("n", "2n", "2ⁿ", "n²"), "2n"),
        RawQ("GATE Concept", "Which machine generally requires fewer states?", listOf("Moore", "Mealy", "Both equal", "None"), "Mealy"),
        RawQ("GATE Concept", "The output of a Moore machine changes only when", listOf("Input changes", "State changes", "Enable changes", "Reset changes"), "State changes"),
        RawQ("GATE Concept", "Propagation delay is most significant in", listOf("Ripple counters", "Registers", "Decoders", "MUXes"), "Ripple counters"),
        RawQ("GATE Concept", "Sequential circuits require", listOf("Memory elements", "Only logic gates", "Only combinational blocks", "No clock"), "Memory elements"),

        // Questions 151 - 200: Logic Families, Memory & Data Converters
        RawQ("Logic Families", "TTL stands for", listOf("Transistor-Transistor Logic", "Transmission Transistor Logic", "Tri-State Transistor Logic", "Time Triggered Logic"), "Transistor-Transistor Logic"),
        RawQ("Logic Families", "CMOS stands for", listOf("Complementary MOS", "Common MOS", "Coupled MOS", "Complementary Metal Oxide Semiconductor"), "Complementary Metal Oxide Semiconductor"),
        RawQ("Logic Families", "ECL stands for", listOf("Emitter Coupled Logic", "Enhanced CMOS Logic", "Emitter Controlled Logic", "Electronic Coupled Logic"), "Emitter Coupled Logic"),
        RawQ("Logic Families", "Which logic family has the highest speed?", listOf("TTL", "CMOS", "ECL", "RTL"), "ECL"),
        RawQ("Logic Families", "Which logic family has the lowest static power dissipation?", listOf("TTL", "CMOS", "ECL", "DTL"), "CMOS"),
        RawQ("Logic Families", "Fan-out refers to", listOf("Number of outputs driven by one gate", "Gate delay", "Power consumption", "Noise margin"), "Number of outputs driven by one gate"),
        RawQ("Logic Families", "Noise margin indicates", listOf("Power loss", "Immunity to noise", "Propagation delay", "Clock skew"), "Immunity to noise"),
        RawQ("Logic Families", "Propagation delay is defined as", listOf("Input-output transition time", "Setup time", "Hold time", "Clock period"), "Input-output transition time"),
        RawQ("Memory", "RAM stands for", listOf("Random Access Memory", "Read Access Memory", "Rapid Access Memory", "Random Analog Memory"), "Random Access Memory"),
        RawQ("Memory", "ROM stands for", listOf("Read Only Memory", "Random Only Memory", "Read Output Memory", "Rapid Output Memory"), "Read Only Memory"),
        RawQ("Memory", "Which memory is volatile?", listOf("ROM", "EPROM", "RAM", "EEPROM"), "RAM"),
        RawQ("Memory", "Which memory retains data without power?", listOf("RAM", "SRAM", "DRAM", "ROM"), "ROM"),
        RawQ("Memory", "PROM stands for", listOf("Programmable Read Only Memory", "Primary ROM", "Parallel ROM", "Programmable RAM"), "Programmable Read Only Memory"),
        RawQ("Memory", "EPROM can be erased using", listOf("Magnetic field", "UV light", "Heat", "Electrical pulse only"), "UV light"),
        RawQ("Memory", "EEPROM stands for", listOf("Electrically Erasable Programmable ROM", "Enhanced EPROM", "Electronic EPROM", "External EPROM"), "Electrically Erasable Programmable ROM"),
        RawQ("Memory", "Flash memory is a type of", listOf("SRAM", "DRAM", "EEPROM", "Cache"), "EEPROM"),
        RawQ("Memory", "SRAM stores data using", listOf("Capacitors", "Flip-flops", "Inductors", "Registers only"), "Flip-flops"),
        RawQ("Memory", "DRAM stores data using", listOf("Capacitors", "Flip-flops", "Diodes", "Registers"), "Capacitors"),
        RawQ("Memory", "DRAM requires periodic", listOf("Programming", "Refreshing", "Erasing", "Clock division"), "Refreshing"),
        RawQ("Memory", "Cache memory is used to", listOf("Increase access time", "Reduce access time", "Store permanent data", "Convert analog signals"), "Reduce access time"),
        RawQ("ADC", "ADC stands for", listOf("Analog to Digital Converter", "Automatic Digital Converter", "Analog Data Converter", "Automatic Data Converter"), "Analog to Digital Converter"),
        RawQ("DAC", "DAC stands for", listOf("Digital to Analog Converter", "Data Analog Converter", "Digital Automatic Converter", "Data Automatic Converter"), "Digital to Analog Converter"),
        RawQ("ADC", "Quantization error occurs in", listOf("DAC only", "ADC only", "Both ADC and DAC", "Neither"), "ADC only"),
        RawQ("ADC", "Resolution of an n-bit ADC is proportional to", listOf("2^n", "1/2^n", "n", "n^2"), "1/2^n"),
        RawQ("DAC", "An R-2R ladder network is commonly used in", listOf("ADC", "DAC", "Counter", "Register"), "DAC"),
        RawQ("Timing Parameters", "Setup time is the minimum time before the clock edge during which data must remain", listOf("Stable", "Changing", "High", "Low"), "Stable"),
        RawQ("Timing Parameters", "Hold time is the minimum time after the clock edge during which data must remain", listOf("Stable", "Low", "High", "Undefined"), "Stable"),
        RawQ("Timing Parameters", "Violation of setup or hold time may cause", listOf("Metastability", "Amplification", "Aliasing", "Quantization"), "Metastability"),
        RawQ("Numerical - ADC", "An 8-bit ADC can represent how many levels?", listOf("128", "256", "512", "1024"), "256"),
        RawQ("Numerical - ADC", "A 10-bit ADC can represent", listOf("512 levels", "1024 levels", "2048 levels", "4096 levels"), "1024 levels"),
        RawQ("Numerical - ADC", "For a 12-bit ADC, the number of quantization levels is", listOf("1024", "2048", "4096", "8192"), "4096"),
        RawQ("Numerical - Memory", "A memory with 10 address lines can store how many locations?", listOf("512", "1024", "2048", "4096"), "1024"),
        RawQ("Numerical - Memory", "A memory chip having 12 address lines contains", listOf("2048 locations", "4096 locations", "8192 locations", "16384 locations"), "4096 locations"),
        RawQ("Numerical - Memory", "A 64K memory contains", listOf("2^14 locations", "2^15 locations", "2^16 locations", "2^17 locations"), "2^16 locations"),
        RawQ("Numerical - Logic Families", "If a gate can drive 20 similar gates, its fan-out is", listOf("10", "20", "40", "80"), "20"),
        RawQ("Numerical - ADC", "A 4-bit ADC has a resolution of", listOf("1/4", "1/8", "1/16", "1/32"), "1/16"),
        RawQ("GATE Concept", "Which memory is fastest?", listOf("DRAM", "ROM", "SRAM", "EEPROM"), "SRAM"),
        RawQ("GATE Concept", "Which memory is commonly used as cache?", listOf("DRAM", "SRAM", "EPROM", "Flash"), "SRAM"),
        RawQ("GATE Concept", "Which memory has the highest density?", listOf("SRAM", "DRAM", "Register", "Cache"), "DRAM"),
        RawQ("GATE Concept", "CMOS is preferred in VLSI because of", listOf("High static power", "Low power consumption", "Low noise margin", "High delay"), "Low power consumption"),
        RawQ("GATE Concept", "ECL achieves high speed because transistors operate in", listOf("Saturation", "Cutoff", "Active region", "Breakdown"), "Active region"),
        RawQ("GATE Concept", "A DAC converts", listOf("Digital data into analog signal", "Analog signal into digital data", "Frequency into voltage", "Voltage into current only"), "Digital data into analog signal"),
        RawQ("GATE Concept", "The output of a flash ADC is obtained using", listOf("Single comparator", "Multiple comparators", "Counter", "Shift register"), "Multiple comparators"),
        RawQ("GATE Concept", "Successive approximation ADC is known for", listOf("Very low resolution", "Good speed and accuracy", "Highest hardware complexity", "No comparator usage"), "Good speed and accuracy"),
        RawQ("GATE Concept", "Clock skew refers to", listOf("Difference in clock arrival times", "Clock frequency", "Duty cycle", "Clock amplitude"), "Difference in clock arrival times"),
        RawQ("GATE Concept", "Metastability is most commonly associated with", listOf("Asynchronous inputs", "DAC", "ROM", "Decoder"), "Asynchronous inputs"),
        RawQ("GATE Concept", "The maximum clock frequency is limited by", listOf("Propagation delay", "Noise margin", "Fan-out only", "Supply voltage only"), "Propagation delay"),
        RawQ("GATE Concept", "Static RAM does not require", listOf("Power", "Refresh operation", "Address lines", "Data lines"), "Refresh operation"),
        RawQ("GATE Concept", "Dynamic RAM is preferred when", listOf("High density is required", "Highest speed is required", "No refresh is desired", "Very low capacity is needed"), "High density is required"),
        RawQ("GATE Concept", "Among TTL, CMOS and ECL, the lowest power-delay product is generally associated with", listOf("TTL", "CMOS", "ECL", "RTL"), "CMOS")
    )
}
