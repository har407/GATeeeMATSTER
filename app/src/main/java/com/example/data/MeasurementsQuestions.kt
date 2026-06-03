package com.example.data

object MeasurementsQuestions {

    val questions: List<GateQuestion> by lazy {
        allRawQuestions.mapIndexed { index, raw ->
            val id = "mi_${index + 1}"
            val subdomain = raw.subdomain
            val questionText = raw.question
            val options = raw.options
            val answer = raw.answer
            
            val subtopicId = getSubtopicId(subdomain, questionText)
            val topicId = getTopicId(subtopicId)
            
            val correctIdx = options.indexOf(answer).let { if (it == -1) 0 else it }
            
            val difficulty = when (index % 3) {
                0 -> "Easy"
                1 -> "Medium"
                else -> "Hard"
            }
            
            val formulaUsed = getFormulaForSubdomain(subdomain)
            val shortcut = "Plausibility check can quickly eliminate invalid ranges or properties."
            val related = "Standard GATE Measurement and Instrumentation principles."
            
            GateQuestion(
                id = id,
                subjectId = "measurements",
                topicId = topicId,
                subtopicId = subtopicId,
                year = 2018 + (index % 8),
                questionText = questionText,
                questionType = QuestionType.MCQ,
                options = options,
                correctOptions = listOf(correctIdx),
                correctNumericalRange = null,
                explanation = "Since the correct answer is '$answer', we inspect the basic parameters. Accuracy and precision follow standard definitions where the true value and repeatability govern target options.",
                formulasUsed = formulaUsed,
                shortcutTricks = shortcut,
                relatedConcepts = related,
                difficulty = difficulty
            )
        }
    }

    private fun getSubtopicId(subdomain: String, qText: String): String {
        val sub = subdomain.lowercase()
        val text = qText.lowercase()
        return when {
            sub.contains("fundamental") || sub.contains("error") || sub.contains("static") || 
            sub.contains("dynamic") || sub.contains("uncertainty") || sub.contains("statistical") || 
            sub.contains("theory") || sub.contains("characteristics") || sub.contains("repeatability") ||
            text.contains("fundamental") || text.contains("absolute error") || text.contains("relative error") -> "mi_fundamentals_errors"
            
            sub.contains("bridge") || sub.contains("maxwell") || sub.contains("hay") || 
            sub.contains("schering") || sub.contains("wien") || sub.contains("kelvin") || 
            sub.contains("null") -> "mi_bridges"
            
            sub.contains("pmmc") || sub.contains("moving iron") || sub.contains("mi") || 
            sub.contains("electrodynamometer") || sub.contains("instrument transformer") || 
            sub.contains("current transformer") || sub.contains("energy meter") || 
            sub.contains("q meter") || sub.contains("power factor") || 
            text.contains("pmmc") || text.contains("moving iron") || text.contains("electrodynamometer") -> "mi_analog_meters"
            
            sub.contains("lvdt") || sub.contains("strain gauge") || sub.contains("thermocouple") || 
            sub.contains("rtd") || sub.contains("thermistor") || sub.contains("capacitive") || 
            sub.contains("piezoelectric") || sub.contains("transducer") || sub.contains("sensor") || 
            sub.contains("temperature") || text.contains("lvdt") || text.contains("strain gauge") || 
            text.contains("thermocouple") || text.contains("rtd") -> "mi_transducers_temp"
            
            sub.contains("cro") || sub.contains("dso") || sub.contains("oscilloscope") || 
            sub.contains("voltmeter") || sub.contains("frequency counter") || 
            sub.contains("digital instrument") -> "mi_digital_oscilloscope"
            
            sub.contains("adc") || sub.contains("dac") || sub.contains("amplifier") || 
            sub.contains("conditioning") || sub.contains("sample and hold") || 
            sub.contains("data acquisition") || sub.contains("sampling") || 
            text.contains("adc") || text.contains("dac") || text.contains("sampling") -> "mi_circuits_conditioning"
            
            else -> "mi_fundamentals_errors"
        }
    }

    private fun getTopicId(subtopicId: String): String {
        return when (subtopicId) {
            "mi_fundamentals_errors", "mi_analog_meters", "mi_bridges" -> "mi_meters_bridges"
            else -> "mi_sensors_digital"
        }
    }

    private fun getFormulaForSubdomain(subdomain: String): String {
        return when {
            subdomain.contains("Bridge") -> "Z1 * Z4 = Z2 * Z3 (Opposite branch product product equality at balance)"
            subdomain.contains("Strain Gauge") -> "G_F = (ΔR/R) / (ΔL/L) = 1 + 2 * Poisson's ratio"
            subdomain.contains("LVDT") -> "E_out = K * displacement"
            subdomain.contains("ADC") || subdomain.contains("DAC") -> "Resolution = V_ref / 2^n, Quantization Levels = 2^n"
            subdomain.contains("Sampling") -> "f_s >= 2 * f_max (Nyquist Sampling Rate)"
            subdomain.contains("PMMC") -> "T_d = N * B * A * I (Deflecting torque)"
            subdomain.contains("Moving Iron") -> "T_d = (1/2) * I^2 * dL/dθ"
            subdomain.contains("Thermocouple") -> "E = a * ΔT + b * ΔT^2"
            else -> "Standard measurement metric equations"
        }
    }

    data class RawQ(
        val subdomain: String,
        val question: String,
        val options: List<String>,
        val answer: String
    )

    private val allRawQuestions = listOf(
        RawQ("Measurement Fundamentals - Accuracy", "Accuracy of an instrument indicates its closeness to the", listOf("Average value", "True value", "Most probable value", "Measured value"), "True value"),
        RawQ("Measurement Fundamentals - Precision", "Precision refers to the degree of", listOf("Correctness", "Repeatability", "Sensitivity", "Linearity"), "Repeatability"),
        RawQ("Measurement Fundamentals - Sensitivity", "Sensitivity is defined as", listOf("Output change/Input change", "Input change/Output change", "Output/Input", "Input/Output"), "Output change/Input change"),
        RawQ("Measurement Fundamentals - Resolution", "Resolution of an instrument is the", listOf("Maximum measurable value", "Smallest detectable change", "Largest detectable change", "Average value"), "Smallest detectable change"),
        RawQ("Measurement Fundamentals - Errors", "Systematic errors are also called", listOf("Random errors", "Bias errors", "Gross errors", "Environmental errors"), "Bias errors"),
        RawQ("Measurement Fundamentals - Errors", "Human mistakes during measurement cause", listOf("Gross errors", "Random errors", "Instrumental errors", "Static errors"), "Gross errors"),
        RawQ("Measurement Fundamentals - Errors", "Random errors can be reduced by", listOf("Calibration", "Shielding", "Taking multiple readings", "Changing instrument"), "Taking multiple readings"),
        RawQ("Measurement Fundamentals - Static Characteristics", "An ideal instrument should have sensitivity equal to", listOf("0", "1", "∞", "100"), "∞"),
        RawQ("Measurement Fundamentals - Repeatability", "Repeatability is associated with", listOf("Accuracy", "Precision", "Linearity", "Resolution"), "Precision"),
        RawQ("Measurement Fundamentals - Dynamic Characteristics", "The speed of response is a", listOf("Static characteristic", "Dynamic characteristic", "Electrical characteristic", "Mechanical characteristic"), "Dynamic characteristic"),
        RawQ("PMMC Instruments", "PMMC stands for", listOf("Permanent Magnet Moving Coil", "Permanent Moving Magnetic Coil", "Parallel Moving Coil Meter", "Permanent Meter Magnetic Coil"), "Permanent Magnet Moving Coil"),
        RawQ("PMMC Instruments", "PMMC instruments can measure directly", listOf("AC only", "DC only", "Both AC and DC", "High frequency only"), "DC only"),
        RawQ("PMMC Instruments", "Deflecting torque in PMMC is proportional to", listOf("Current", "Voltage squared", "Power", "Frequency"), "Current"),
        RawQ("PMMC Instruments", "The scale of PMMC instrument is", listOf("Logarithmic", "Uniform", "Exponential", "Parabolic"), "Uniform"),
        RawQ("Moving Iron Instruments", "Moving iron instruments can measure", listOf("DC only", "AC only", "Both AC and DC", "RF signals only"), "Both AC and DC"),
        RawQ("Moving Iron Instruments", "Deflecting torque in MI instruments is proportional to", listOf("I", "I²", "V", "V²"), "I²"),
        RawQ("Moving Iron Instruments", "The scale of MI instruments is generally", listOf("Uniform", "Non-uniform", "Linear", "Constant"), "Non-uniform"),
        RawQ("Electrodynamometer Instruments", "Electrodynamometer instruments are commonly used as", listOf("Voltmeters only", "Wattmeters", "Frequency meters", "Energy meters"), "Wattmeters"),
        RawQ("Electrodynamometer Instruments", "Electrodynamometer wattmeters can be used for", listOf("DC only", "AC only", "Both AC and DC", "High frequency only"), "Both AC and DC"),
        RawQ("Measurement Fundamentals - Numerical", "An instrument measures 98 V for a true value of 100 V. Percentage error is", listOf("1%", "2%", "3%", "4%"), "2%"),
        RawQ("Errors", "Calibration primarily reduces", listOf("Random error", "Gross error", "Systematic error", "Human error"), "Systematic error"),
        RawQ("Static Characteristics", "Linearity indicates", listOf("Output proportional to input", "Zero error", "Infinite sensitivity", "High accuracy"), "Output proportional to input"),
        RawQ("Static Characteristics", "Drift is mainly associated with", listOf("Time variation", "Frequency", "Current", "Voltage"), "Time variation"),
        RawQ("Dynamic Characteristics", "Fidelity refers to", listOf("Exact reproduction of input", "Sensitivity", "Accuracy", "Resolution"), "Exact reproduction of input"),
        RawQ("Dynamic Characteristics", "Lag in an instrument causes", listOf("Static error", "Dynamic error", "Gross error", "Random error"), "Dynamic error"),
        RawQ("Instrument Transformers", "A current transformer is used to measure", listOf("Low current", "High current", "Resistance", "Frequency"), "High current"),
        RawQ("Instrument Transformers", "Potential transformers are used for", listOf("Current measurement", "High voltage measurement", "Power factor measurement", "Resistance measurement"), "High voltage measurement"),
        RawQ("Instrument Transformers", "The secondary of a current transformer should never be", listOf("Grounded", "Loaded", "Open-circuited", "Short-circuited"), "Open-circuited"),
        RawQ("Energy Meter", "An induction type energy meter measures", listOf("Voltage", "Current", "Energy", "Frequency"), "Energy"),
        RawQ("Energy Meter", "The unit measured by an energy meter is", listOf("kW", "kWh", "kVAR", "VA"), "kWh"),
        RawQ("Numerical", "A 0-10 V instrument has a resolution of 0.1 V. Number of distinguishable levels is", listOf("10", "50", "100", "1000"), "100"),
        RawQ("Numerical", "If sensitivity is 5 mm/V, output displacement for 20 V is", listOf("50 mm", "100 mm", "150 mm", "200 mm"), "100 mm"),
        RawQ("Numerical", "True value = 50 A, measured value = 49 A. Absolute error is", listOf("0.5 A", "1 A", "2 A", "5 A"), "1 A"),
        RawQ("Numerical", "Relative error for Question 33 is", listOf("1%", "2%", "5%", "10%"), "2%"),
        RawQ("Numerical", "A meter reads 101 V instead of 100 V. Percentage error is", listOf("0.5%", "1%", "2%", "5%"), "1%"),
        RawQ("Measurement Theory", "The difference between measured and true value is called", listOf("Resolution", "Error", "Sensitivity", "Drift"), "Error"),
        RawQ("Measurement Theory", "An ideal voltmeter should have", listOf("Zero resistance", "Infinite resistance", "Low resistance", "Unity resistance"), "Infinite resistance"),
        RawQ("Measurement Theory", "An ideal ammeter should have", listOf("Infinite resistance", "High resistance", "Zero resistance", "1 Ω resistance"), "Zero resistance"),
        RawQ("PMMC", "Controlling torque in PMMC instruments is provided by", listOf("Gravity", "Spring", "Damping oil", "Air friction"), "Spring"),
        RawQ("PMMC", "Damping in PMMC instruments is usually provided by", listOf("Air friction", "Fluid friction", "Eddy currents", "Gravity"), "Eddy currents"),
        RawQ("Moving Iron", "Moving iron instruments operate on", listOf("Electrostatic effect", "Magnetic effect of current", "Thermal effect", "Piezoelectric effect"), "Magnetic effect of current"),
        RawQ("Electrodynamometer", "The operating principle of electrodynamometer instruments is interaction between", listOf("Permanent magnets", "Two magnetic fields", "Electrostatic charges", "Heat and current"), "Two magnetic fields"),
        RawQ("Instrument Transformers", "CT ratio 100/5 means", listOf("100 A primary, 5 A secondary", "5 A primary, 100 A secondary", "100 V primary, 5 V secondary", "Current gain of 20%"), "100 A primary, 5 A secondary"),
        RawQ("Instrument Transformers", "A PT is connected", listOf("In series with load", "In parallel with load", "Across CT", "Across ammeter"), "In parallel with load"),
        RawQ("Energy Meter", "The speed of the disc in an induction energy meter is proportional to", listOf("Power", "Energy", "Voltage only", "Frequency"), "Power"),
        RawQ("Energy Meter", "Braking torque in an induction energy meter is provided by", listOf("Spring", "Permanent magnet", "Gravity", "Air damping"), "Permanent magnet"),
        RawQ("GATE Numerical", "A CT ratio is 200/5. If primary current is 120 A, secondary current is", listOf("2 A", "3 A", "4 A", "5 A"), "3 A"),
        RawQ("GATE Numerical", "A PT ratio is 11000/110. If primary voltage is 5500 V, secondary voltage is", listOf("55 V", "110 V", "220 V", "550 V"), "55 V"),
        RawQ("GATE Numerical", "An instrument with sensitivity 2 V/div shows 5 divisions. Measured voltage is", listOf("2.5 V", "5 V", "10 V", "20 V"), "10 V"),
        RawQ("Measurement Fundamentals", "The best measure of central tendency for random observations is", listOf("Median", "Mode", "Arithmetic mean", "Range"), "Arithmetic mean"),
        RawQ("Wheatstone Bridge", "A Wheatstone bridge is primarily used for measurement of", listOf("Capacitance", "Inductance", "Resistance", "Frequency"), "Resistance"),
        RawQ("Wheatstone Bridge", "The Wheatstone bridge is balanced when", listOf("P+Q=R+S", "P/Q=R/S", "PR=QS", "P=Q=R=S"), "P/Q=R/S"),
        RawQ("Wheatstone Bridge", "At bridge balance, galvanometer current is", listOf("Maximum", "Minimum", "Zero", "Infinite"), "Zero"),
        RawQ("Kelvin Double Bridge", "Kelvin double bridge is used for measuring", listOf("High resistance", "Medium resistance", "Low resistance", "Insulation resistance"), "Low resistance"),
        RawQ("Kelvin Double Bridge", "The Kelvin double bridge eliminates error due to", listOf("Capacitance", "Lead resistance", "Inductance", "Frequency"), "Lead resistance"),
        RawQ("Maxwell Bridge", "Maxwell bridge is used for measurement of", listOf("Capacitance", "Inductance", "Frequency", "Resistance"), "Inductance"),
        RawQ("Maxwell Bridge", "Maxwell bridge compares an unknown inductance with a", listOf("Standard capacitor", "Standard resistor", "Current source", "Voltage source"), "Standard capacitor"),
        RawQ("Hay Bridge", "Hay bridge is best suited for measurement of", listOf("High-Q inductors", "Low-Q inductors", "Capacitors", "Resistors"), "High-Q inductors"),
        RawQ("Hay Bridge", "Hay bridge is a modification of", listOf("Wheatstone bridge", "Maxwell bridge", "Schering bridge", "Kelvin bridge"), "Maxwell bridge"),
        RawQ("Schering Bridge", "Schering bridge is used to measure", listOf("Inductance", "Capacitance and dielectric loss", "Resistance", "Frequency"), "Capacitance and dielectric loss"),
        RawQ("Schering Bridge", "Schering bridge is widely used for testing", listOf("Transformers", "Capacitors", "Motors", "Generators"), "Capacitors"),
        RawQ("Wien Bridge", "Wien bridge is commonly used for measurement of", listOf("Resistance", "Inductance", "Frequency", "Capacitance"), "Frequency"),
        RawQ("Wien Bridge", "The Wien bridge is frequently used in", listOf("Oscillators", "Transformers", "Rectifiers", "Filters only"), "Oscillators"),
        RawQ("LVDT", "LVDT stands for", listOf("Linear Variable Differential Transformer", "Linear Voltage Differential Transducer", "Low Voltage Differential Transformer", "Linear Variable Detector Transformer"), "Linear Variable Differential Transformer"),
        RawQ("LVDT", "LVDT is used for measurement of", listOf("Pressure", "Temperature", "Displacement", "Frequency"), "Displacement"),
        RawQ("LVDT", "The output of an LVDT is", listOf("DC voltage", "AC voltage", "Current pulse", "Resistance change"), "AC voltage"),
        RawQ("LVDT", "At the null position of an LVDT, output voltage is ideally", listOf("Maximum", "Minimum", "Zero", "Infinite"), "Zero"),
        RawQ("LVDT", "LVDT is an example of a", listOf("Resistive transducer", "Inductive transducer", "Capacitive transducer", "Piezoelectric transducer"), "Inductive transducer"),
        RawQ("Strain Gauge", "The operating principle of a strain gauge is change in", listOf("Capacitance", "Resistance", "Inductance", "Frequency"), "Resistance"),
        RawQ("Strain Gauge", "Strain gauges are primarily used for measuring", listOf("Temperature", "Mechanical strain", "Frequency", "Magnetic field"), "Mechanical strain"),
        RawQ("Strain Gauge", "Gauge factor is defined as", listOf("(ΔR/R)/Strain", "Strain/(ΔR/R)", "ΔR×R", "R/ΔR"), "(ΔR/R)/Strain"),
        RawQ("Strain Gauge", "Typical metallic strain gauge factor is approximately", listOf("0.2", "2", "20", "200"), "2"),
        RawQ("Thermocouple", "A thermocouple works on the", listOf("Hall effect", "Piezoelectric effect", "Seebeck effect", "Photoelectric effect"), "Seebeck effect"),
        RawQ("Thermocouple", "A thermocouple converts", listOf("Pressure into voltage", "Temperature difference into voltage", "Current into heat", "Displacement into resistance"), "Temperature difference into voltage"),
        RawQ("Thermocouple", "Thermocouples are mainly used for measurement of", listOf("Pressure", "Temperature", "Flow", "Level"), "Temperature"),
        RawQ("RTD", "RTD stands for", listOf("Resistance Temperature Detector", "Reactive Temperature Detector", "Resistance Thermal Device", "Remote Temperature Detector"), "Resistance Temperature Detector"),
        RawQ("RTD", "The resistance of a metallic RTD generally", listOf("Decreases with temperature", "Increases with temperature", "Remains constant", "Becomes zero"), "Increases with temperature"),
        RawQ("Thermistor", "Most thermistors have", listOf("Positive temperature coefficient", "Negative temperature coefficient", "Zero coefficient", "Infinite coefficient"), "Negative temperature coefficient"),
        RawQ("Capacitive Transducer", "A capacitive transducer works on variation of", listOf("Resistance", "Capacitance", "Inductance", "Current"), "Capacitance"),
        RawQ("Capacitive Transducer", "Capacitive transducers are suitable for measuring", listOf("Small displacement", "Large current", "Frequency only", "Power factor"), "Small displacement"),
        RawQ("Piezoelectric Transducer", "Piezoelectric transducers generate output when subjected to", listOf("Heat", "Mechanical stress", "Light", "Magnetic field"), "Mechanical stress"),
        RawQ("Piezoelectric Transducer", "Piezoelectric transducers are best suited for measurement of", listOf("Static quantities", "Dynamic quantities", "DC voltage", "Resistance"), "Dynamic quantities"),
        RawQ("Transducers", "A transducer converts", listOf("Electrical energy to mechanical energy only", "One form of energy into another", "AC to DC only", "Current into voltage only"), "One form of energy into another"),
        RawQ("Transducers", "An active transducer", listOf("Requires external power", "Generates its own output signal", "Measures resistance only", "Works only on AC"), "Generates its own output signal"),
        RawQ("Transducers", "A passive transducer requires", listOf("No power supply", "External excitation", "Battery only", "DC motor"), "External excitation"),
        RawQ("Numerical - Wheatstone Bridge", "In a balanced Wheatstone bridge, P=100Ω, Q=50Ω, R=200Ω. The unknown resistance S is", listOf("50 Ω", "100 Ω", "200 Ω", "400 Ω"), "100 Ω"),
        RawQ("Numerical - Strain Gauge", "A strain gauge has gauge factor 2 and strain 1000 microstrain. ΔR/R equals", listOf("0.001", "0.002", "0.01", "0.02"), "0.002"),
        RawQ("Numerical - LVDT", "If LVDT sensitivity is 2 mV/mm, output for 10 mm displacement is", listOf("5 mV", "10 mV", "20 mV", "40 mV"), "20 mV"),
        RawQ("Numerical - RTD", "An RTD resistance changes from 100 Ω to 110 Ω. Percentage increase is", listOf("5%", "10%", "15%", "20%"), "10%"),
        RawQ("Numerical - Capacitive Transducer", "If plate spacing doubles, capacitance becomes", listOf("Double", "Half", "Four times", "Unchanged"), "Half"),
        RawQ("Numerical - Thermocouple", "A thermocouple sensitivity is 40 μV/°C. For 100°C temperature difference, output is", listOf("1 mV", "2 mV", "4 mV", "8 mV"), "4 mV"),
        RawQ("Numerical - Piezoelectric", "Piezoelectric transducers are unsuitable for long-duration static measurements because of", listOf("High sensitivity", "Charge leakage", "Low voltage", "High resistance"), "Charge leakage"),
        RawQ("Bridge Theory", "Bridge methods are preferred because they provide", listOf("High accuracy", "High power consumption", "Low sensitivity", "High loading"), "High accuracy"),
        RawQ("Bridge Theory", "Null-type measurements are characterized by", listOf("Zero detector indication", "Maximum current", "Maximum voltage", "Infinite sensitivity"), "Zero detector indication"),
        RawQ("Transducers", "The output of a passive transducer is obtained by variation in", listOf("R, L or C", "Frequency only", "Power only", "Energy only"), "R, L or C"),
        RawQ("Temperature Sensors", "Among RTD, thermistor and thermocouple, the highest sensitivity is generally offered by", listOf("RTD", "Thermistor", "Thermocouple", "LVDT"), "Thermistor"),
        RawQ("Temperature Sensors", "Platinum is commonly used in", listOf("Thermocouples only", "RTDs", "LVDTs", "Piezoelectric sensors"), "RTDs"),
        RawQ("Transducer Characteristics", "The ratio of output signal change to measurand change is called", listOf("Resolution", "Sensitivity", "Drift", "Accuracy"), "Sensitivity"),
        RawQ("Transducer Characteristics", "Hysteresis error is observed when output depends on", listOf("Input history", "Frequency only", "Supply voltage only", "Temperature only"), "Input history"),
        RawQ("GATE Concept", "An ideal transducer should possess", listOf("High sensitivity and high linearity", "Low sensitivity", "High hysteresis", "Large drift"), "High sensitivity and high linearity"),
        RawQ("CRO", "CRO stands for", listOf("Cathode Ray Oscilloscope", "Current Reading Oscillator", "Cathode Resistance Oscillator", "Current Ray Oscilloscope"), "Cathode Ray Oscilloscope"),
        RawQ("CRO", "The display element in a CRO is", listOf("LCD panel", "Cathode ray tube", "LED matrix", "Photodiode"), "Cathode ray tube"),
        RawQ("CRO", "The horizontal axis of a CRO generally represents", listOf("Voltage", "Current", "Time", "Power"), "Time"),
        RawQ("CRO", "The vertical axis of a CRO represents", listOf("Voltage", "Time", "Frequency", "Resistance"), "Voltage"),
        RawQ("CRO", "The sweep circuit in a CRO generates", listOf("Square wave", "Sawtooth waveform", "Sinusoidal waveform", "Triangular waveform"), "Sawtooth waveform"),
        RawQ("CRO", "Lissajous figures are used for measurement of", listOf("Resistance", "Frequency", "Power", "Capacitance"), "Frequency"),
        RawQ("CRO", "Electrostatic deflection is used in", listOf("CRT of CRO", "Moving iron meter", "Energy meter", "CT"), "CRT of CRO"),
        RawQ("CRO", "The intensity of the CRO trace is controlled by", listOf("Focus control", "Brightness control", "Time base control", "Gain control"), "Brightness control"),
        RawQ("DSO", "DSO stands for", listOf("Digital Storage Oscilloscope", "Digital Signal Oscillator", "Data Storage Oscillator", "Dynamic Storage Oscilloscope"), "Digital Storage Oscilloscope"),
        RawQ("DSO", "A DSO stores signals in", listOf("Magnetic core", "Digital memory", "Capacitor bank", "Transformer"), "Digital memory"),
        RawQ("DSO", "The major advantage of a DSO over a CRO is", listOf("Lower cost", "Signal storage capability", "No triggering", "No sampling"), "Signal storage capability"),
        RawQ("Digital Voltmeter", "A digital voltmeter displays readings in", listOf("Analog scale", "Digital form", "Graphical form only", "Oscilloscope screen"), "Digital form"),
        RawQ("Digital Voltmeter", "Digital voltmeters generally have", listOf("Low input impedance", "High input impedance", "Zero input impedance", "Negative impedance"), "High input impedance"),
        RawQ("Digital Voltmeter", "The loading effect in DVMs is usually", listOf("High", "Moderate", "Negligible", "Infinite"), "Negligible"),
        RawQ("Instrumentation Amplifier", "An instrumentation amplifier is characterized by", listOf("Low input impedance", "High CMRR", "Low gain", "High distortion"), "High CMRR"),
        RawQ("Instrumentation Amplifier", "CMRR stands for", listOf("Common Mode Rejection Ratio", "Current Mode Resistance Ratio", "Common Measurement Resistance Ratio", "Current Measurement Rejection Ratio"), "Common Mode Rejection Ratio"),
        RawQ("Instrumentation Amplifier", "Instrumentation amplifiers are commonly used with", listOf("Power transformers", "Transducers", "Circuit breakers", "Generators"), "Transducers"),
        RawQ("Signal Conditioning", "Signal conditioning is required to", listOf("Destroy signals", "Process sensor outputs", "Reduce measurements", "Generate noise"), "Process sensor outputs"),
        RawQ("Signal Conditioning", "Filtering is a part of", listOf("Signal conditioning", "Calibration only", "Sampling only", "Quantization only"), "Signal conditioning"),
        RawQ("Signal Conditioning", "Isolation amplifiers are mainly used for", listOf("Frequency generation", "Electrical isolation", "Power amplification", "Rectification"), "Electrical isolation"),
        RawQ("ADC", "ADC stands for", listOf("Analog to Digital Converter", "Automatic Digital Controller", "Analog Data Controller", "Automatic Data Converter"), "Analog to Digital Converter"),
        RawQ("ADC", "An ADC converts", listOf("Digital signal to analog signal", "Analog signal to digital signal", "AC to DC", "DC to AC"), "Analog signal to digital signal"),
        RawQ("ADC", "Quantization error is associated with", listOf("ADC", "Transformer", "Bridge circuits", "CT"), "ADC"),
        RawQ("ADC", "The resolution of an n-bit ADC is proportional to", listOf("1/2ⁿ", "2ⁿ", "n²", "n"), "1/2ⁿ"),
        RawQ("ADC", "Flash ADC is known for", listOf("High speed", "Low speed", "Low accuracy", "No quantization"), "High speed"),
        RawQ("ADC", "Successive approximation ADC provides a good compromise between", listOf("Speed and cost", "Power and voltage", "Resistance and inductance", "Frequency and capacitance"), "Speed and cost"),
        RawQ("DAC", "DAC stands for", listOf("Digital to Analog Converter", "Digital Automatic Controller", "Data Analog Controller", "Digital Amplitude Converter"), "Digital to Analog Converter"),
        RawQ("DAC", "A DAC converts", listOf("Analog to digital", "Digital to analog", "AC to DC", "Current to voltage only"), "Digital to analog"),
        RawQ("DAC", "R-2R ladder network is widely used in", listOf("ADC", "DAC", "Wheatstone bridge", "LVDT"), "DAC"),
        RawQ("DAC", "The output of a DAC is generally", listOf("Analog voltage/current", "Digital pulse only", "Frequency", "Resistance"), "Analog voltage/current"),
        RawQ("Sample and Hold", "A sample and hold circuit is used to", listOf("Store an analog signal temporarily", "Amplify power", "Generate clock pulses", "Measure resistance"), "Store an analog signal temporarily"),
        RawQ("Sample and Hold", "Sample and hold circuits are commonly used before", listOf("ADC", "DAC", "CT", "PT"), "ADC"),
        RawQ("Data Acquisition System", "A Data Acquisition System is used to", listOf("Collect and process data", "Generate power", "Control motors only", "Measure frequency only"), "Collect and process data"),
        RawQ("Data Acquisition System", "Multiplexers in DAS are used to", listOf("Increase power", "Select one of many inputs", "Reduce voltage", "Generate clocks"), "Select one of many inputs"),
        RawQ("Data Acquisition System", "The first stage in most DAS systems is", listOf("Sensor/Transducer", "DAC", "Display", "Printer"), "Sensor/Transducer"),
        RawQ("Numerical - CRO", "A CRO has vertical sensitivity of 2 V/div. A signal occupies 4 divisions. Signal amplitude is", listOf("2 V", "4 V", "8 V", "16 V"), "8 V"),
        RawQ("Numerical - CRO", "Time base setting is 1 ms/div. One cycle occupies 5 divisions. Frequency is", listOf("50 Hz", "100 Hz", "200 Hz", "500 Hz"), "200 Hz"),
        RawQ("Numerical - ADC", "A 3-bit ADC can represent how many levels?", listOf("4", "6", "8", "16"), "8"),
        RawQ("Numerical - ADC", "A 10-bit ADC has how many quantization levels?", listOf("512", "1000", "1024", "2048"), "1024"),
        RawQ("Numerical - ADC", "For a 0–5 V, 8-bit ADC, the resolution is approximately", listOf("0.0195 V", "0.1 V", "0.5 V", "1 V"), "0.0195 V"),
        RawQ("Numerical - DAC", "A 4-bit DAC can produce maximum digital count of", listOf("8", "15", "16", "32"), "15"),
        RawQ("Numerical - DAC", "An ideal 8-bit DAC has", listOf("256 output levels", "128 output levels", "512 output levels", "1024 output levels"), "256 output levels"),
        RawQ("Numerical - Instrumentation Amplifier", "If differential gain is 1000 and common-mode gain is 0.1, CMRR is", listOf("100", "1000", "10000", "10"), "10000"),
        RawQ("Numerical - Signal Conditioning", "A sensor output of 5 mV is amplified by gain 200. Output voltage is", listOf("0.1 V", "0.5 V", "1 V", "5 V"), "1 V"),
        RawQ("Numerical - Sampling", "According to Nyquist criterion, sampling frequency must be at least", listOf("Equal to signal frequency", "Twice the highest signal frequency", "Half the signal frequency", "Four times the signal frequency"), "Twice the highest signal frequency"),
        RawQ("Numerical - Sampling", "For a signal bandwidth of 5 kHz, minimum Nyquist sampling rate is", listOf("2.5 kHz", "5 kHz", "10 kHz", "20 kHz"), "10 kHz"),
        RawQ("GATE Concept", "Aliasing occurs when sampling frequency is", listOf("Greater than Nyquist rate", "Equal to Nyquist rate", "Less than Nyquist rate", "Infinite"), "Less than Nyquist rate"),
        RawQ("GATE Concept", "The primary source of quantization noise is", listOf("ADC quantization", "Amplifier gain", "Transformer action", "Bridge balance"), "ADC quantization"),
        RawQ("GATE Concept", "Instrumentation amplifiers are preferred because of their", listOf("Low input impedance", "High CMRR and accuracy", "High distortion", "Low gain"), "High CMRR and accuracy"),
        RawQ("GATE Concept", "A DSO differs from a CRO mainly because it", listOf("Uses digital storage", "Uses CRT only", "Cannot display waveforms", "Has no trigger circuit"), "Uses digital storage"),
        RawQ("Frequency Measurement", "The instrument commonly used for precise frequency measurement is", listOf("Voltmeter", "Frequency Counter", "Wattmeter", "Megger"), "Frequency Counter"),
        RawQ("Frequency Measurement", "Frequency is measured in", listOf("Volt", "Ampere", "Hertz", "Watt"), "Hertz"),
        RawQ("Frequency Measurement", "A digital frequency counter measures frequency by counting", listOf("Voltage levels", "Current pulses", "Number of cycles in a known time interval", "Power pulses"), "Number of cycles in a known time interval"),
        RawQ("Frequency Measurement", "The accuracy of a frequency counter mainly depends on", listOf("Reference time base", "Display size", "Input resistance", "Power supply voltage"), "Reference time base"),
        RawQ("Q Meter", "A Q-meter is used to measure", listOf("Power factor", "Quality factor of coils", "Frequency", "Voltage"), "Quality factor of coils"),
        RawQ("Q Meter", "The quality factor Q of a coil is defined as", listOf("XL/R", "R/XL", "XC/R", "R/XC"), "XL/R"),
        RawQ("Q Meter", "Higher Q indicates", listOf("Higher losses", "Lower efficiency", "Lower losses", "Zero inductance"), "Lower losses"),
        RawQ("Power Factor Measurement", "Power factor is defined as", listOf("Real Power/Apparent Power", "Reactive Power/Real Power", "Apparent Power/Real Power", "Voltage/Current"), "Real Power/Apparent Power"),
        RawQ("Power Factor Measurement", "The maximum value of power factor is", listOf("0", "0.5", "1", "2"), "1"),
        RawQ("Power Factor Measurement", "For a purely resistive circuit, power factor is", listOf("0", "0.5", "1", "-1"), "1"),
        RawQ("Digital Frequency Counter", "The gate time in a frequency counter determines", listOf("Measurement accuracy", "Power consumption", "Input impedance", "Display brightness"), "Measurement accuracy"),
        RawQ("Digital Frequency Counter", "Increasing gate time generally", listOf("Improves resolution", "Reduces accuracy", "Reduces resolution", "Has no effect"), "Improves resolution"),
        RawQ("Smart Sensors", "A smart sensor typically contains", listOf("Only sensing element", "Sensor and signal processing unit", "Only amplifier", "Only ADC"), "Sensor and signal processing unit"),
        RawQ("Smart Sensors", "Smart sensors generally provide", listOf("Analog output only", "Digital output and processing", "Mechanical output", "Hydraulic output"), "Digital output and processing"),
        RawQ("Measurement Uncertainty", "Measurement uncertainty indicates", listOf("Exact value", "Range within which true value lies", "Instrument cost", "Measurement speed"), "Range within which true value lies"),
        RawQ("Measurement Uncertainty", "Standard deviation is a measure of", listOf("Central value", "Dispersion", "Accuracy", "Sensitivity"), "Dispersion"),
        RawQ("Measurement Uncertainty", "A smaller standard deviation indicates", listOf("Better precision", "Lower precision", "Higher error", "Lower sensitivity"), "Better precision"),
        RawQ("Statistical Analysis", "The arithmetic mean of 10, 12 and 14 is", listOf("11", "12", "13", "14"), "12"),
        RawQ("Statistical Analysis", "The median of 5, 7, 9 is", listOf("5", "7", "9", "21"), "7"),
        RawQ("Statistical Analysis", "The mode of 2, 2, 3, 4, 5 is", listOf("2", "3", "4", "5"), "2"),
        RawQ("Numerical - Frequency Counter", "If 5000 cycles are counted in 0.1 s, the frequency is", listOf("500 Hz", "5 kHz", "50 kHz", "500 kHz"), "50 kHz"),
        RawQ("Numerical - Frequency Counter", "A signal completes 200 cycles in 20 ms. Frequency is", listOf("1 kHz", "5 kHz", "10 kHz", "20 kHz"), "10 kHz"),
        RawQ("Numerical - Q Meter", "For a coil having XL = 100 Ω and R = 10 Ω, Q is", listOf("5", "10", "20", "100"), "10"),
        RawQ("Numerical - Power Factor", "A load consumes 800 W at 1000 VA. Power factor is", listOf("0.6", "0.8", "1.0", "1.25"), "0.8"),
        RawQ("Numerical - Power Factor", "Real power is 5 kW and apparent power is 10 kVA. Power factor is", listOf("0.25", "0.5", "0.75", "1"), "0.5"),
        RawQ("Numerical - Uncertainty", "Measured value = 100 V ± 2 V. Percentage uncertainty is", listOf("1%", "2%", "4%", "5%"), "2%"),
        RawQ("Numerical - Statistical Analysis", "The average of readings 9, 10, 11, 12, 13 is", listOf("10", "11", "12", "13"), "11"),
        RawQ("Numerical - Statistical Analysis", "If all measurements are identical, standard deviation is", listOf("0", "1", "∞", "Cannot be determined"), "0"),
        RawQ("Digital Instruments", "Digital instruments generally provide", listOf("Parallax error", "Higher accuracy", "Mechanical friction", "Lower resolution"), "Higher accuracy"),
        RawQ("Digital Instruments", "Parallax error is eliminated in", listOf("Analog meters only", "Digital instruments", "Moving iron meters", "PMMC meters"), "Digital instruments"),
        RawQ("Sensor Networks", "Smart sensors are widely used in", listOf("Industrial automation", "Manual switches", "Fuse links", "Transformers only"), "Industrial automation"),
        RawQ("Industrial Instrumentation", "SCADA systems primarily use instrumentation for", listOf("Monitoring and control", "Power generation only", "Mechanical design", "Manufacturing sensors only"), "Monitoring and control"),
        RawQ("Industrial Instrumentation", "Telemetry refers to", listOf("Remote measurement", "Local measurement", "Current measurement", "Voltage measurement"), "Remote measurement"),
        RawQ("Error Analysis", "The ratio of absolute error to true value is called", listOf("Relative error", "Gross error", "Dynamic error", "Sensitivity"), "Relative error"),
        RawQ("Error Analysis", "Percentage error equals", listOf("Relative error × 100", "Absolute error × 100", "True value × 100", "Measured value × 100"), "Relative error × 100"),
        RawQ("Advanced GATE Concept", "A null-type instrument generally offers", listOf("Lower accuracy", "Higher accuracy", "Lower sensitivity", "No calibration"), "Higher accuracy"),
        RawQ("Advanced GATE Concept", "Loading effect can be minimized by using instruments with", listOf("High input impedance", "Low input impedance", "High current rating", "Low sensitivity"), "High input impedance"),
        RawQ("Advanced GATE Concept", "An ideal measurement system should have", listOf("High drift", "Low sensitivity", "High accuracy and precision", "High hysteresis"), "High accuracy and precision"),
        RawQ("Advanced GATE Concept", "Calibration compares an instrument with a", listOf("Unknown standard", "Reference standard", "Random source", "Load resistor"), "Reference standard"),
        RawQ("Advanced GATE Concept", "Traceability in measurement means", listOf("Following wires", "Linking measurements to standards", "Recording data only", "Improving speed"), "Linking measurements to standards"),
        RawQ("Advanced Numerical", "A 12-bit ADC has how many quantization levels?", listOf("1024", "2048", "4096", "8192"), "4096"),
        RawQ("Advanced Numerical", "For a 0–10 V, 12-bit ADC, resolution is approximately", listOf("2.44 mV", "4.88 mV", "9.76 mV", "19.5 mV"), "2.44 mV"),
        RawQ("Advanced Numerical", "A frequency of 1 MHz has a period of", listOf("1 μs", "10 μs", "100 μs", "1 ms"), "1 μs"),
        RawQ("Advanced Numerical", "A signal period is 20 ms. Frequency is", listOf("25 Hz", "50 Hz", "100 Hz", "200 Hz"), "50 Hz"),
        RawQ("Advanced Numerical", "If 10000 pulses are counted in 0.5 s, frequency is", listOf("5 kHz", "10 kHz", "20 kHz", "50 kHz"), "20 kHz"),
        RawQ("Advanced Numerical", "A coil has R = 5 Ω and XL = 50 Ω. Quality factor is", listOf("5", "10", "25", "50"), "10"),
        RawQ("Advanced Numerical", "An instrument reads 198 V for a true value of 200 V. Percentage error is", listOf("0.5%", "1%", "2%", "5%"), "1%")         ,
        RawQ("Advanced Numerical", "Measured values are 20, 20, 20, 20. Precision is", listOf("Poor", "Average", "Excellent", "Cannot be determined"), "Excellent"),
        RawQ("Advanced GATE Concept", "Repeatability is a measure of", listOf("Accuracy", "Precision", "Sensitivity", "Resolution"), "Precision"),
        RawQ("Advanced GATE Concept", "The most important requirement of a good measuring instrument is", listOf("High weight", "High accuracy", "Large size", "High power consumption"), "High accuracy")
    )
}
