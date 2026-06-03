package com.example.data

object AnalogElectronicsQuestions {

    val questions: List<GateQuestion> by lazy {
        group1 + group2 + group3
    }

    private val group1 = listOf(
        q(1, "Semiconductor Physics", "Which of the following is an intrinsic semiconductor?", listOf("Pure Silicon", "Silicon doped with Phosphorus", "Silicon doped with Boron", "Germanium doped with Arsenic"), "Pure Silicon"),
        q(2, "Semiconductor Physics", "The majority carriers in an n-type semiconductor are", listOf("Holes", "Electrons", "Ions", "Photons"), "Electrons"),
        q(3, "Semiconductor Physics", "The majority carriers in a p-type semiconductor are", listOf("Electrons", "Neutrons", "Holes", "Ions"), "Holes"),
        q(4, "Semiconductor Physics", "Which impurity is commonly used for n-type doping of silicon?", listOf("Boron", "Gallium", "Aluminum", "Phosphorus"), "Phosphorus"),
        q(5, "Semiconductor Physics", "Which impurity is commonly used for p-type doping of silicon?", listOf("Boron", "Phosphorus", "Arsenic", "Antimony"), "Boron"),
        q(6, "PN Junction Diode", "The cut-in voltage of a silicon diode is approximately", listOf("0.3 V", "0.7 V", "1.5 V", "5 V"), "0.7 V"),
        q(7, "PN Junction Diode", "The cut-in voltage of a germanium diode is approximately", listOf("0.3 V", "0.7 V", "1.2 V", "2 V"), "0.3 V"),
        q(8, "PN Junction Diode", "In reverse bias, the depletion region width", listOf("Decreases", "Remains zero", "Increases", "Becomes negative"), "Increases"),
        q(9, "PN Junction Diode", "The reverse saturation current in a diode is mainly due to", listOf("Majority carriers", "Minority carriers", "Photons", "Lattice ions"), "Minority carriers"),
        q(10, "PN Junction Diode", "The diode equation current is proportional to", listOf("e^(V/VT)", "V²", "1/V", "log(V)"), "e^(V/VT)"),
        q(11, "Diode Circuits", "An ideal diode in forward bias behaves as", listOf("Open circuit", "Short circuit", "Current source", "Voltage source"), "Short circuit"),
        q(12, "Diode Circuits", "An ideal diode in reverse bias behaves as", listOf("Short circuit", "Current source", "Open circuit", "Resistor"), "Open circuit"),
        q(13, "Zener Diode", "A Zener diode is normally operated in", listOf("Forward bias", "Reverse breakdown region", "Cutoff", "Active region"), "Reverse breakdown region"),
        q(14, "Zener Diode", "The primary application of a Zener diode is", listOf("Amplification", "Oscillation", "Voltage regulation", "Switching"), "Voltage regulation"),
        q(15, "Clippers and Clampers", "A clipper circuit is used to", listOf("Shift DC level", "Remove part of waveform", "Amplify signal", "Generate oscillation"), "Remove part of waveform"),
        q(16, "Clippers and Clampers", "A clamper circuit is used to", listOf("Shift DC level", "Limit current", "Amplify voltage", "Rectify AC"), "Shift DC level"),
        q(17, "BJT Fundamentals", "A BJT is a", listOf("Voltage-controlled device", "Current-controlled device", "Power-controlled device", "Charge-controlled device"), "Current-controlled device"),
        q(18, "BJT Fundamentals", "The relation between α and β is", listOf("β = α/(1-α)", "β = 1-α", "β = α²", "β = 1/α"), "β = α/(1-α)"),
        q(19, "BJT Fundamentals", "For active region operation of an NPN transistor", listOf("Both junctions reverse biased", "BE forward, BC reverse biased", "BE reverse, BC forward biased", "Both junctions forward biased"), "BE forward, BC reverse biased"),
        q(20, "BJT Fundamentals", "The emitter is heavily doped to", listOf("Reduce current", "Inject carriers efficiently", "Increase breakdown voltage", "Reduce gain"), "Inject carriers efficiently"),
        q(21, "BJT Biasing", "The purpose of transistor biasing is to", listOf("Increase temperature", "Fix operating point", "Reduce gain", "Increase noise"), "Fix operating point"),
        q(22, "BJT Biasing", "The most stable biasing method is", listOf("Fixed bias", "Collector feedback bias", "Voltage divider bias", "Emitter bias only"), "Voltage divider bias"),
        q(23, "BJT Small Signal Analysis", "The transconductance gm of a BJT is", listOf("IC/VT", "VT/IC", "βIC", "IC²"), "IC/VT"),
        q(24, "BJT Small Signal Analysis", "The small signal resistance rπ equals", listOf("β/gm", "gm/β", "1/gm", "βgm"), "β/gm"),
        q(25, "BJT Amplifiers", "A common-emitter amplifier provides", listOf("Current gain only", "Voltage gain only", "Both current and voltage gain", "Unity gain"), "Both current and voltage gain"),
        q(26, "BJT Amplifiers", "A common collector amplifier is also called", listOf("Emitter follower", "Collector follower", "Voltage follower", "Current mirror"), "Emitter follower"),
        q(27, "MOSFET Fundamentals", "A MOSFET is primarily a", listOf("Current-controlled device", "Voltage-controlled device", "Power-controlled device", "Frequency-controlled device"), "Voltage-controlled device"),
        q(28, "MOSFET Fundamentals", "The gate current of an ideal MOSFET is", listOf("Infinite", "1 A", "Zero", "10 mA"), "Zero"),
        q(29, "MOSFET Fundamentals", "An NMOS turns ON when", listOf("VGS < VT", "VGS > VT", "VDS = 0", "VGS = 0"), "VGS > VT"),
        q(30, "MOSFET Fundamentals", "In saturation region of MOSFET", listOf("VDS < VGS − VT", "VDS > VGS − VT", "VGS = 0", "VDS = 0"), "VDS > VGS − VT"),
        q(31, "MOSFET Small Signal Analysis", "The transconductance gm of a MOSFET is", listOf("∂ID/∂VGS", "∂VGS/∂ID", "VGS/ID", "ID/VDS"), "∂ID/∂VGS"),
        q(32, "MOSFET Amplifiers", "The common source amplifier provides", listOf("Voltage gain with phase inversion", "Unity gain", "Current gain only", "No gain"), "Voltage gain with phase inversion"),
        q(33, "Differential Amplifier", "The differential amplifier amplifies", listOf("Common mode signal", "Difference of inputs", "Noise only", "Supply voltage"), "Difference of inputs"),
        q(34, "Differential Amplifier", "CMRR stands for", listOf("Common Mode Rejection Ratio", "Current Mode Resistance Ratio", "Common Mode Response Range", "Current Matching Ratio"), "Common Mode Rejection Ratio"),
        q(35, "Current Mirrors", "A current mirror is used to", listOf("Mirror voltage", "Replicate current", "Amplify power", "Generate oscillation"), "Replicate current"),
        q(36, "Frequency Response", "The lower cutoff frequency is defined at", listOf("+3 dB", "-3 dB", "-10 dB", "0 dB"), "-3 dB"),
        q(37, "Frequency Response", "The bandwidth of an amplifier equals", listOf("fH + fL", "fH × fL", "fH − fL", "fL/fH"), "fH − fL"),
        q(38, "Feedback Amplifiers", "Negative feedback generally", listOf("Increases gain only", "Improves stability", "Causes oscillation", "Removes bandwidth"), "Improves stability"),
        q(39, "Feedback Amplifiers", "Negative feedback increases", listOf("Distortion", "Noise", "Bandwidth", "Temperature"), "Bandwidth"),
        q(40, "Oscillators", "The Barkhausen criterion requires loop gain magnitude", listOf("0", "0.5", "1", "10"), "1"),
        q(41, "Oscillators", "The phase shift required for oscillation is", listOf("0° or 360°", "45°", "90°", "180° only"), "0° or 360°"),
        q(42, "Operational Amplifiers", "The ideal op-amp has", listOf("Infinite gain", "Zero gain", "Unity gain", "Finite gain"), "Infinite gain"),
        q(43, "Operational Amplifiers", "The input resistance of an ideal op-amp is", listOf("0 Ω", "1 kΩ", "Infinite", "100 Ω"), "Infinite"),
        q(44, "Operational Amplifiers", "The output resistance of an ideal op-amp is", listOf("Infinite", "Zero", "1 kΩ", "10 Ω"), "Zero"),
        q(45, "Op-Amp Applications", "An inverting amplifier has a phase shift of", listOf("0°", "45°", "90°", "180°"), "180°"),
        q(46, "Op-Amp Applications", "The gain of a voltage follower is", listOf("0", "1", "10", "Infinite"), "1"),
        q(47, "Numerical - BJT", "For IC = 1 mA and VT = 25 mV, gm is approximately", listOf("0.04 S", "25 S", "0.001 S", "1 S"), "0.04 S"),
        q(48, "Numerical - Diode", "A silicon diode carries 10 mA current. The approximate forward voltage is", listOf("0.1 V", "0.3 V", "0.7 V", "5 V"), "0.7 V"),
        q(49, "Numerical - Op Amp", "For an inverting amplifier with Rf=100 kΩ and Rin=10 kΩ, the gain is", listOf("-10", "10", "-100", "100"), "-10"),
        q(50, "Numerical - MOSFET", "If VGS = 5 V and VT = 2 V, the overdrive voltage is", listOf("2 V", "3 V", "5 V", "7 V"), "3 V")
    )

    private val group2 = listOf(
        q(51, "BJT Biasing", "The Q-point of a transistor amplifier refers to", listOf("Cutoff point", "Operating point", "Breakdown point", "Saturation point"), "Operating point"),
        q(52, "BJT Biasing", "The stability factor should ideally be", listOf("Maximum", "Infinite", "Minimum", "Equal to β"), "Minimum"),
        q(53, "BJT Biasing", "Emitter resistance in a bias circuit improves", listOf("Frequency response", "Thermal stability", "Power dissipation", "Switching speed"), "Thermal stability"),
        q(54, "BJT Small Signal Analysis", "The Early effect causes variation of", listOf("β only", "Collector current with VCE", "Base current with VBE", "Emitter current with temperature"), "Collector current with VCE"),
        q(55, "BJT Small Signal Analysis", "The output resistance due to Early effect is denoted by", listOf("rπ", "gm", "ro", "re"), "ro"),
        q(56, "BJT Amplifiers", "The common-base amplifier has", listOf("High current gain", "Current gain less than unity", "Infinite gain", "Zero gain"), "Current gain less than unity"),
        q(57, "BJT Amplifiers", "The input resistance of a common-base amplifier is generally", listOf("Very high", "Moderate", "Very low", "Infinite"), "Very low"),
        q(58, "BJT Amplifiers", "The common collector amplifier is mainly used for", listOf("Voltage amplification", "Impedance matching", "Oscillation", "Rectification"), "Impedance matching"),
        q(59, "BJT Amplifiers", "A CE amplifier introduces a phase shift of", listOf("0°", "90°", "180°", "270°"), "180°"),
        q(60, "Numerical - BJT", "For β = 100 and IB = 20 μA, collector current IC is", listOf("0.2 mA", "2 mA", "20 mA", "200 mA"), "2 mA"),
        q(61, "MOSFET Biasing", "The threshold voltage of a MOSFET is the minimum VGS required to", listOf("Cause breakdown", "Create inversion channel", "Reduce current", "Increase leakage"), "Create inversion channel"),
        q(62, "MOSFET Biasing", "The body effect in MOSFET causes", listOf("Decrease in threshold voltage", "Increase in threshold voltage", "Decrease in gm", "No change"), "Increase in threshold voltage"),
        q(63, "MOSFET Small Signal Analysis", "The small-signal drain current is approximately", listOf("gmVgs", "Vgs/gm", "gm²Vgs", "gm/Vgs"), "gmVgs"),
        q(64, "MOSFET Small Signal Analysis", "Channel length modulation introduces", listOf("Input resistance", "Output resistance", "Gate current", "Body current"), "Output resistance"),
        q(65, "MOSFET Amplifiers", "The common-drain amplifier is also known as", listOf("Source follower", "Drain follower", "Current mirror", "Cascode stage"), "Source follower"),
        q(66, "MOSFET Amplifiers", "The voltage gain of a source follower is approximately", listOf("0", "1", "10", "100"), "1"),
        q(67, "MOSFET Amplifiers", "A cascode amplifier is mainly used to", listOf("Reduce output resistance", "Increase gain and bandwidth", "Decrease gain", "Reduce input impedance"), "Increase gain and bandwidth"),
        q(68, "Differential Amplifier", "The differential gain is defined as", listOf("Vo/Vcm", "Vo/Vd", "Vd/Vo", "Vcm/Vo"), "Vo/Vd"),
        q(69, "Differential Amplifier", "Common-mode gain should ideally be", listOf("Maximum", "Equal to differential gain", "Zero", "Infinite"), "Zero"),
        q(70, "Differential Amplifier", "CMRR is defined as", listOf("Ad/Acm", "Acm/Ad", "Ad+Acm", "Ad−Acm"), "Ad/Acm"),
        q(71, "Current Mirrors", "An ideal current mirror has output current", listOf("Half of reference current", "Equal to reference current", "Twice reference current", "Zero"), "Equal to reference current"),
        q(72, "Current Mirrors", "The Widlar current source is used to generate", listOf("Large currents", "Small currents", "AC currents", "Negative currents"), "Small currents"),
        q(73, "Multistage Amplifiers", "The overall gain of cascaded amplifiers equals", listOf("Sum of gains", "Product of gains", "Difference of gains", "Average gain"), "Product of gains"),
        q(74, "Multistage Amplifiers", "Direct coupling is commonly used in", listOf("DC amplifiers", "RF amplifiers", "Power amplifiers", "Oscillators"), "DC amplifiers"),
        q(75, "Frequency Response", "At low frequencies, gain decreases mainly due to", listOf("Coupling and bypass capacitors", "Transistor gain", "Power supply", "Noise"), "Coupling and bypass capacitors"),
        q(76, "Frequency Response", "At high frequencies, gain decreases mainly due to", listOf("Internal capacitances", "Emitter resistor", "Collector resistor", "Supply voltage"), "Internal capacitances"),
        q(77, "Frequency Response", "The Miller effect is associated with", listOf("Emitter resistance", "Feedback capacitance", "Current mirror", "Bias network"), "Feedback capacitance"),
        q(78, "Feedback Amplifiers", "Positive feedback is generally used in", listOf("Voltage regulators", "Oscillators", "Rectifiers", "Filters"), "Oscillators"),
        q(79, "Feedback Amplifiers", "Negative feedback reduces", listOf("Bandwidth", "Input resistance", "Distortion", "Output resistance always"), "Distortion"),
        q(80, "Feedback Amplifiers", "The closed-loop gain with feedback is approximately", listOf("A/(1+Aβ)", "A(1+Aβ)", "Aβ", "1/Aβ"), "A/(1+Aβ)"),
        q(81, "Oscillators", "The Wien bridge oscillator generates", listOf("Square waves", "Triangular waves", "Sinusoidal waves", "Pulse trains"), "Sinusoidal waves"),
        q(82, "Oscillators", "An RC phase-shift oscillator typically uses", listOf("1 RC section", "2 RC sections", "3 RC sections", "5 RC sections"), "3 RC sections"),
        q(83, "Oscillators", "A crystal oscillator is preferred when", listOf("High power is needed", "High frequency stability is required", "Low gain is needed", "Low impedance is required"), "High frequency stability is required"),
        q(84, "Operational Amplifiers", "The slew rate of an op-amp is measured in", listOf("V/μs", "A/μs", "Ω/s", "Hz/V"), "V/μs"),
        q(85, "Operational Amplifiers", "Input offset voltage is ideally", listOf("Infinite", "1 V", "Zero", "10 V"), "Zero"),
        q(86, "Operational Amplifiers", "The gain-bandwidth product of an ideal op-amp is", listOf("0", "1 MHz", "Infinite", "100 Hz"), "Infinite"),
        q(87, "Op-Amp Applications", "An op-amp integrator uses", listOf("Capacitor in feedback path", "Resistor in feedback path", "Inductor in input path", "Diode in feedback path"), "Capacitor in feedback path"),
        q(88, "Op-Amp Applications", "An op-amp differentiator uses", listOf("Capacitor at input", "Inductor at input", "Transistor at input", "Current source"), "Capacitor at input"),
        q(89, "Op-Amp Applications", "A summing amplifier performs", listOf("Differentiation", "Integration", "Weighted addition", "Multiplication"), "Weighted addition"),
        q(90, "Numerical - BJT", "For IC = 2 mA and VT = 25 mV, gm is", listOf("0.02 S", "0.04 S", "0.08 S", "0.16 S"), "0.08 S"),
        q(91, "Numerical - BJT", "For β = 100 and gm = 40 mS, rπ is", listOf("250 Ω", "2.5 kΩ", "25 kΩ", "250 kΩ"), "2.5 kΩ"),
        q(92, "Numerical - MOSFET", "For gm = 5 mS and Vgs = 20 mV, the small-signal drain current is", listOf("10 μA", "50 μA", "100 μA", "1 mA"), "100 μA"),
        q(93, "Numerical - Op Amp", "An inverting amplifier has Rin=5 kΩ and Rf=50 kΩ. Gain equals", listOf("-5", "-10", "10", "50"), "-10"),
        q(94, "Numerical - Op Amp", "A non-inverting amplifier with Rf=9 kΩ and R1=1 kΩ has gain", listOf("9", "10", "11", "12"), "10"),
        q(95, "Numerical - Differential Amplifier", "If Ad = 1000 and Acm = 1, CMRR equals", listOf("10", "100", "1000", "10000"), "1000"),
        q(96, "Numerical - Frequency Response", "If fL=100 Hz and fH=100 kHz, bandwidth is", listOf("99.9 kHz", "100.1 kHz", "100 kHz", "99 kHz"), "99.9 kHz"),
        q(97, "Numerical - Oscillator", "For sustained oscillations, loop gain magnitude must be", listOf("0", "0.5", "1", "2"), "1"),
        q(98, "Numerical - Current Mirror", "If the reference current is 1 mA, the ideal mirrored current is", listOf("0.5 mA", "1 mA", "2 mA", "10 mA"), "1 mA"),
        q(99, "Numerical - Feedback", "For A=100 and β=0.09, the closed-loop gain is approximately", listOf("10", "11", "50", "100"), "10"),
        q(100, "Numerical - MOSFET", "For VGS=6 V and VT=2 V, the overdrive voltage is", listOf("2 V", "3 V", "4 V", "6 V"), "4 V")
    )

    private val group3 = listOf(
        q(151, "Feedback Topologies", "Voltage-series feedback primarily increases", listOf("Output resistance", "Input resistance", "Collector current", "Noise"), "Input resistance"),
        q(152, "Feedback Topologies", "Current-shunt feedback primarily decreases", listOf("Input resistance", "Output resistance", "Gain bandwidth product", "Phase margin"), "Input resistance"),
        q(153, "Feedback Topologies", "Voltage-shunt feedback is commonly used in", listOf("Current amplifiers", "Transresistance amplifiers", "Voltage amplifiers", "Transconductance amplifiers"), "Transresistance amplifiers"),
        q(154, "Feedback Topologies", "Current-series feedback realizes a", listOf("Voltage amplifier", "Current amplifier", "Transconductance amplifier", "Integrator"), "Transconductance amplifier"),
        q(155, "Multistage Amplifiers", "The overall voltage gain in dB of cascaded amplifiers equals", listOf("Product of individual dB gains", "Sum of individual dB gains", "Average of gains", "Difference of gains"), "Sum of individual dB gains"),
        q(156, "Multistage Amplifiers", "A cascode amplifier is a combination of", listOf("CE and CB", "CC and CE", "CB and CC", "CE and CE"), "CE and CB"),
        q(157, "Multistage Amplifiers", "The Miller effect is significantly reduced in", listOf("CE amplifier", "Cascode amplifier", "Emitter follower", "Differential pair"), "Cascode amplifier"),
        q(158, "Oscillators", "The frequency of oscillation of a Wien bridge oscillator is", listOf("1/(2πRC)", "RC", "2πRC", "1/RC²"), "1/(2πRC)"),
        q(159, "Oscillators", "An LC oscillator is preferred over an RC oscillator for", listOf("Low frequencies", "Audio frequencies", "High frequencies", "DC applications"), "High frequencies"),
        q(160, "Oscillators", "The frequency of an LC oscillator is proportional to", listOf("√LC", "1/(2π√LC)", "LC", "1/LC"), "1/(2π√LC)"),
        q(161, "Operational Amplifiers", "The gain of an ideal non-inverting amplifier is", listOf("Rf/R1", "1 + Rf/R1", "R1/Rf", "-Rf/R1"), "1 + Rf/R1"),
        q(162, "Operational Amplifiers", "The virtual short concept is applicable when the op-amp operates in", listOf("Open loop", "Positive feedback", "Negative feedback", "Saturation"), "Negative feedback"),
        q(163, "Operational Amplifiers", "An instrumentation amplifier is characterized by", listOf("Low input impedance", "High CMRR", "Low gain", "Low output impedance only"), "High CMRR"),
        q(164, "Operational Amplifiers", "The ideal output impedance of an instrumentation amplifier is", listOf("Infinite", "Very high", "Zero", "Equal to input impedance"), "Zero"),
        q(165, "Active Filters", "A second-order filter has an attenuation slope of", listOf("20 dB/decade", "40 dB/decade", "60 dB/decade", "80 dB/decade"), "40 dB/decade"),
        q(166, "Active Filters", "A band-pass filter passes frequencies", listOf("Below lower cutoff", "Above upper cutoff", "Between two cutoff frequencies", "At DC only"), "Between two cutoff frequencies"),
        q(167, "ADC", "The quantization error of an ideal ADC lies within", listOf("±1 LSB", "±0.5 LSB", "±2 LSB", "±4 LSB"), "±0.5 LSB"),
        q(168, "ADC", "Increasing ADC resolution decreases", listOf("Number of levels", "Quantization step size", "Accuracy", "Conversion complexity"), "Quantization step size"),
        q(169, "DAC", "A DAC converts", listOf("Analog signal to digital code", "Digital code to analog signal", "AC to DC", "DC to AC"), "Digital code to analog signal"),
        q(170, "DAC", "Monotonicity in a DAC means", listOf("Output always increases with input code", "Output always decreases", "Output is sinusoidal", "Output is periodic"), "Output always increases with input code"),
        q(171, "CMOS Circuits", "The noise margins of CMOS logic are generally", listOf("Very low", "Moderate", "High", "Zero"), "High"),
        q(172, "CMOS Circuits", "Dynamic power dissipation in CMOS is proportional to", listOf("CV²f", "CVf", "V/f", "C/V"), "CV²f"),
        q(173, "Numerical - BJT", "For IC = 5 mA and VT = 25 mV, gm is", listOf("0.05 S", "0.1 S", "0.2 S", "0.4 S"), "0.2 S"),
        q(174, "Numerical - BJT", "For β = 100 and IC = 10 mA, base current is", listOf("10 μA", "100 μA", "1 mA", "10 mA"), "100 μA"),
        q(175, "Numerical - MOSFET", "For VGS = 8 V and VT = 3 V, overdrive voltage equals", listOf("3 V", "4 V", "5 V", "8 V"), "5 V"),
        q(176, "Numerical - MOSFET", "For gm = 10 mS and vgs = 50 mV, the small-signal drain current is", listOf("0.1 mA", "0.5 mA", "1 mA", "5 mA"), "0.5 mA"),
        q(177, "Numerical - Op Amp", "An inverting amplifier has Rin = 2 kΩ and Rf = 20 kΩ. Gain is", listOf("-5", "-10", "10", "20"), "-10"),
        q(178, "Numerical - Op Amp", "A non-inverting amplifier has Rf = 99 kΩ and R1 = 1 kΩ. Gain is", listOf("99", "100", "101", "102"), "100"),
        q(179, "Numerical - Filters", "For R = 10 kΩ and C = 0.01 μF, cutoff frequency is approximately", listOf("159 Hz", "1.59 kHz", "15.9 kHz", "159 kHz"), "1.59 kHz"),
        q(180, "Numerical - Oscillator", "For R = 10 kΩ and C = 0.01 μF, Wien bridge oscillator frequency is approximately", listOf("159 Hz", "1.59 kHz", "15.9 kHz", "159 kHz"), "1.59 kHz"),
        q(181, "Numerical - ADC", "A 12-bit ADC has how many quantization levels?", listOf("1024", "2048", "4096", "8192"), "4096"),
        q(182, "Numerical - ADC", "For a 10 V full-scale 8-bit ADC, 1 LSB equals approximately", listOf("19.5 mV", "39.1 mV", "78.1 mV", "156.2 mV"), "39.1 mV"),
        q(183, "Numerical - DAC", "An ideal 4-bit DAC provides", listOf("4 levels", "8 levels", "16 levels", "32 levels"), "16 levels"),
        q(184, "Numerical - Feedback", "For A = 200 and β = 0.02, closed-loop gain is", listOf("20", "40", "50", "100"), "40"),
        q(185, "Numerical - Feedback", "If Ad = 2000 and Acm = 2, the CMRR is", listOf("100", "500", "1000", "2000"), "1000"),
        q(186, "Numerical - CMOS", "If capacitance doubles in a CMOS circuit, dynamic power becomes", listOf("Half", "Same", "Double", "Quadruple"), "Double"),
        q(187, "GATE Mixed Concepts", "An emitter follower is preferred when", listOf("High voltage gain is required", "Impedance matching is required", "Oscillation is required", "Rectification is required"), "Impedance matching is required"),
        q(188, "GATE Mixed Concepts", "The dominant pole of an amplifier determines mainly", listOf("Noise", "Low-frequency gain", "Bandwidth", "Power dissipation"), "Bandwidth"),
        q(189, "GATE Mixed Concepts", "An ideal current source has", listOf("Zero output resistance", "Infinite output resistance", "Unity output resistance", "Negative resistance"), "Infinite output resistance"),
        q(190, "GATE Mixed Concepts", "A differential amplifier rejects", listOf("Differential signals", "Common-mode signals", "AC signals", "DC signals"), "Common-mode signals"),
        q(191, "Revision", "The collector current of a BJT is primarily controlled by", listOf("Base current", "Collector voltage", "Emitter resistance", "Load resistance"), "Base current"),
        q(192, "Revision", "The gate current of an ideal MOSFET is", listOf("Infinite", "1 mA", "0 A", "10 μA"), "0 A"),
        q(193, "Revision", "Negative feedback generally reduces", listOf("Bandwidth", "Stability", "Gain", "Input resistance always"), "Gain"),
        q(194, "Revision", "A Zener diode is normally used as a", listOf("Rectifier", "Voltage regulator", "Oscillator", "Amplifier"), "Voltage regulator"),
        q(195, "Revision", "The ideal op-amp input resistance is", listOf("0 Ω", "1 kΩ", "100 kΩ", "Infinite"), "Infinite"),
        q(196, "Revision", "The ideal op-amp output resistance is", listOf("Infinite", "100 Ω", "1 Ω", "0 Ω"), "0 Ω"),
        q(197, "Revision", "The Barkhausen criterion requires total phase shift of", listOf("90°", "180°", "360° or 0°", "270°"), "360° or 0°"),
        q(198, "Revision", "The resolution of an n-bit ADC improves when", listOf("n decreases", "n increases", "reference voltage decreases only", "sampling frequency decreases"), "n increases"),
        q(199, "Revision", "CMOS technology is widely used because of", listOf("High static power dissipation", "Low noise margin", "Low power consumption", "Large gate current"), "Low power consumption"),
        q(200, "Revision", "The most important advantage of negative feedback is", listOf("Infinite gain", "Improved stability and reduced distortion", "Zero bandwidth", "Zero input resistance"), "Improved stability and reduced distortion")
    )

    private fun q(
        id: Int,
        subdomain: String,
        question: String,
        options: List<String>,
        answer: String
    ): GateQuestion {
        val subtopicId = when (subdomain) {
            "Semiconductor Physics", "PN Junction Diode", "Diode Circuits", "Zener Diode", 
            "Clippers and Clampers", "BJT Fundamentals", "BJT Biasing", "MOSFET Fundamentals", 
            "MOSFET Biasing", "Numerical - Diode" -> "ae_dio_bias"

            "BJT Small Signal Analysis", "BJT Amplifiers", "MOSFET Small Signal Analysis", 
            "MOSFET Amplifiers", "Differential Amplifier", "Current Mirrors", "Frequency Response", 
            "Feedback Amplifiers", "Multistage Amplifiers", "Feedback Topologies", 
            "Numerical - BJT", "Numerical - MOSFET", "Numerical - Differential Amplifier", 
            "Numerical - Frequency Response", "Numerical - Current Mirror", "Numerical - Feedback" -> "ae_amp_feedback"

            "Oscillators", "Operational Amplifiers", "Op-Amp Applications", "Numerical - Op Amp", 
            "Numerical - Oscillator", "Active Filters", "ADC", "DAC", "CMOS Circuits", 
            "Numerical - Filters", "Numerical - ADC", "Numerical - DAC", "Numerical - CMOS" -> "ae_opamp_apps"

            "GATE Mixed Concepts" -> {
                if (question.contains("pole") || question.contains("source") || question.contains("differential") || question.contains("follower")) {
                    "ae_amp_feedback"
                } else {
                    "ae_opamp_apps"
                }
            }

            "Revision" -> {
                if (question.contains("collector") || question.contains("gate") || question.contains("Zener")) {
                    "ae_dio_bias"
                } else if (question.contains("feedback")) {
                    "ae_amp_feedback"
                } else {
                    "ae_opamp_apps"
                }
            }

            else -> "ae_dio_bias"
        }

        val topicId = when (subtopicId) {
            "ae_dio_bias" -> "ae_diodes_transistors"
            "ae_amp_feedback" -> "ae_amplifiers"
            "ae_opamp_apps" -> "ae_opamps"
            else -> "ae_diodes_transistors"
        }

        val correctIdx = options.indexOf(answer).let { if (it == -1) 0 else it }

        val difficulty = when (id % 3) {
            0 -> "Easy"
            1 -> "Medium"
            else -> "Hard"
        }

        val formulas = when (subdomain) {
            "Semiconductor Physics", "PN Junction Diode", "Numerical - Diode" -> "Shockley: I_D = I_S * (exp(V_D / (n*V_T)) - 1)"
            "BJT Fundamentals", "BJT Biasing", "Numerical - BJT" -> "I_C = beta * I_B, g_m = I_C / V_T"
            "MOSFET Fundamentals", "MOSFET Biasing", "Numerical - MOSFET" -> "Saturation: I_D = 0.5 * k_n * (V_GS - V_T)^2"
            "Feedback Amplifiers", "Feedback Topologies", "Numerical - Feedback" -> "A_f = A / (1 + A*beta)"
            "Operational Amplifiers", "Op-Amp Applications", "Numerical - Op Amp" -> "Inverting: A_v = -R_f / R_in, Non-inverting: A_v = 1 + R_f / R_in"
            "Oscillators", "Numerical - Oscillator" -> "Barkhausen: |A*beta| = 1, Wien-Bridge: f = 1 / (2*pi*R*C)"
            "Active Filters", "Numerical - Filters" -> "f_c = 1 / (2*pi*R*C)"
            "CMOS Circuits", "Numerical - CMOS" -> "P_dyn = C * V_DD^2 * f"
            "ADC", "DAC", "Numerical - ADC", "Numerical - DAC" -> "Quantization Level = V_ref / 2^n"
            else -> "Standard circuit representation and operation rules"
        }

        val explanationText = "Based on $subdomain principles. Correct answer: '$answer'."

        return GateQuestion(
            id = "ae_q_$id",
            subjectId = "analog_electronics",
            topicId = topicId,
            subtopicId = subtopicId,
            year = 2018 + (id % 8),
            questionText = question,
            questionType = QuestionType.MCQ,
            options = options,
            correctOptions = listOf(correctIdx),
            correctNumericalRange = null,
            explanation = explanationText,
            formulasUsed = formulas,
            shortcutTricks = "Examine bias parameters, active states, ideal approximations, negative feedback properties, or standard formula constants.",
            relatedConcepts = "Analog Electronics, $subdomain",
            difficulty = difficulty
        )
    }
}
