package com.example.data

object ElectromagneticFieldsQuestions {

    val questions: List<GateQuestion> by lazy {
        list1 + list2 + list3 + list4
    }

    private fun q(
        id: Int,
        subdomain: String,
        question: String,
        options: List<String>,
        answer: String
    ): GateQuestion {
        val qLower = question.lowercase()
        val sLower = subdomain.lowercase()
        
        // Map to Topic / Subtopic cleanly based on ID ranges/types for exact accuracy
        val (topicId, subtopicId) = when (id) {
            in 1..20, 46, 47, 151, 152, 192 -> Pair("em_statics", "em_electrostatics")
            in 21..35, 48, 49, 153, 154, 191, 193 -> Pair("em_statics", "em_magnetostatics")
            in 36..45, 50, 89, 181, 182, 183, 184, 185, 199 -> Pair("em_maxwell", "em_maxwell_equations")
            in 51..100, 171, 172, 176, 177, 178, 179, 180, 186, 187, 188, 189, 190 -> Pair("em_waves", "em_waves_propagation")
            in 101..150, 194, 195, 196, 197, 198 -> Pair("em_transmission", "em_transmission_lines")
            in 155..170, 173, 174, 175, 200 -> Pair("em_transmission", "em_waveguides_radiation")
            else -> Pair("em_transmission", "em_waveguides_radiation")
        }

        val correctIdx = options.indexOf(answer).let { if (it == -1) 0 else it }
        val difficulty = when (id % 3) {
            0 -> "Easy"
            1 -> "Medium"
            else -> "Hard"
        }

        val formulas = when (subtopicId) {
            "em_electrostatics" -> "F = q1*q2 / (4πε0r²), E = -∇V, C = εA/d, W = ½CV²"
            "em_magnetostatics" -> "dB = (μ0 I dl x r) / (4πr²), ∮H·dl = I_enc, W = ½LI²"
            "em_maxwell_equations" -> "∇·D = ρv, ∇·B = 0, ∇xE = -∂B/∂t, ∇xH = J + ∂D/∂t"
            "em_waves_propagation" -> "v = fλ, η = sqrt(μ/ε) ≈ 377 Ω, S_vector = E x H, δ = 1 / sqrt(πfμσ)"
            "em_transmission_lines" -> "Z0 = sqrt(L/C), Γ = (ZL-Z0)/(ZL+Z0), VSWR = (1+|Γ|)/(1-|Γ|), Zin = Z0² / ZL"
            "em_waveguides_radiation" -> "fc = (c/2) * sqrt((m/a)² + (n/b)²), TEM Mode: Ez=0, Hz=0"
            else -> "Standard electromagnetic formula"
        }

        val mappedSubdomain = when (subtopicId) {
            "em_electrostatics" -> "Electrostatics"
            "em_magnetostatics" -> "Magnetostatics"
            "em_maxwell_equations" -> "Maxwell's Equations"
            "em_waves_propagation" -> "Electromagnetic Waves"
            "em_transmission_lines" -> "Transmission Lines"
            "em_waveguides_radiation" -> "Waveguides & Radiation"
            else -> subdomain
        }

        return GateQuestion(
            id = "emf_$id",
            subjectId = "electromagnetic_theory",
            topicId = topicId,
            subtopicId = subtopicId,
            year = 2020 + (id % 7),
            questionText = question,
            questionType = QuestionType.MCQ,
            options = options,
            correctOptions = listOf(correctIdx),
            correctNumericalRange = null,
            explanation = "Evaluated under the rules of $mappedSubdomain. The correct option is '$answer'.",
            formulasUsed = formulas,
            shortcutTricks = "Examine dimensions, apply limiting cases (e.g. skin depth at extreme frequencies), or resolve symmetrical characteristics.",
            relatedConcepts = "$mappedSubdomain, Electromagnetic Theory",
            difficulty = difficulty
        )
    }

    private val list1 = listOf(
        q(1, "Electrostatics - Coulomb's Law", "Coulomb's law gives the force between two", listOf("Current elements", "Magnetic poles", "Point charges", "Dipoles only"), "Point charges"),
        q(2, "Electrostatics - Coulomb's Law", "The SI unit of electric charge is", listOf("Volt", "Coulomb", "Weber", "Tesla"), "Coulomb"),
        q(3, "Electrostatics - Coulomb's Law", "The force between two point charges varies inversely as", listOf("r", "r²", "√r", "r³"), "r²"),
        q(4, "Electrostatics - Electric Field", "The SI unit of electric field intensity is", listOf("V/m", "Wb/m²", "A/m", "N"), "V/m"),
        q(5, "Electrostatics - Electric Field", "Electric field intensity due to a point charge is proportional to", listOf("1/r", "1/r²", "r", "r²"), "1/r²"),
        q(6, "Electrostatics - Electric Flux", "Electric flux density is denoted by", listOf("E", "D", "B", "H"), "D"),
        q(7, "Electrostatics - Electric Flux", "The SI unit of electric flux density is", listOf("C/m²", "V/m", "A/m", "T"), "C/m²"),
        q(8, "Electrostatics - Gauss Law", "Gauss's law relates electric flux to", listOf("Current", "Magnetic flux", "Enclosed charge", "Voltage"), "Enclosed charge"),
        q(9, "Electrostatics - Gauss Law", "The closed surface used in Gauss law is called", listOf("Ampere surface", "Gaussian surface", "Equipotential surface", "Faraday surface"), "Gaussian surface"),
        q(10, "Electrostatics - Potential", "Electric potential is measured in", listOf("Volt", "Ampere", "Tesla", "Henry"), "Volt"),
        q(11, "Electrostatics - Potential", "The negative gradient of electric potential gives", listOf("Current density", "Electric field", "Flux density", "Charge density"), "Electric field"),
        q(12, "Electrostatics - Potential", "An equipotential surface is always", listOf("Parallel to E", "Perpendicular to E", "Parallel to H", "Circular"), "Perpendicular to E"),
        q(13, "Electrostatics - Capacitance", "The SI unit of capacitance is", listOf("Henry", "Farad", "Weber", "Tesla"), "Farad"),
        q(14, "Electrostatics - Capacitance", "Capacitance of a parallel plate capacitor is proportional to", listOf("Plate separation", "Area of plates", "1/Area", "Resistance"), "Area of plates"),
        q(15, "Electrostatics - Capacitance", "Increasing dielectric constant causes capacitance to", listOf("Decrease", "Increase", "Become zero", "Remain unchanged"), "Increase"),
        q(16, "Electrostatics - Boundary Conditions", "Tangential component of electric field at dielectric boundary is", listOf("Discontinuous", "Continuous", "Infinite", "Zero always"), "Continuous"),
        q(17, "Electrostatics - Energy", "Energy stored in a capacitor is", listOf("CV²", "½CV²", "QV", "Q²/C"), "½CV²"),
        q(18, "Electrostatics - Numerical", "A charge of 2 C experiences a force of 10 N. Electric field intensity is", listOf("2 V/m", "5 V/m", "10 V/m", "20 V/m"), "5 V/m"),
        q(19, "Electrostatics - Numerical", "A capacitor of 2 μF charged to 100 V stores energy", listOf("0.01 J", "0.001 J", "0.1 J", "1 J"), "0.01 J"),
        q(20, "Electrostatics - Numerical", "Capacitance of two 2 μF capacitors in parallel is", listOf("1 μF", "2 μF", "4 μF", "8 μF"), "4 μF"),
        q(21, "Magnetostatics - Magnetic Field", "Magnetic flux density is denoted by", listOf("E", "H", "B", "D"), "B"),
        q(22, "Magnetostatics - Magnetic Field", "The SI unit of magnetic flux density is", listOf("Tesla", "Weber/m", "Henry", "Ampere"), "Tesla"),
        q(23, "Magnetostatics - Magnetic Field", "Magnetic field intensity is denoted by", listOf("B", "H", "D", "E"), "H"),
        q(24, "Magnetostatics - Magnetic Field", "The SI unit of H is", listOf("A/m", "Tesla", "Wb", "Henry"), "A/m"),
        q(25, "Magnetostatics - Biot Savart Law", "Biot-Savart law is used to determine", listOf("Electric field", "Magnetic field", "Potential", "Capacitance"), "Magnetic field"),
        q(26, "Magnetostatics - Ampere Law", "Ampere's circuital law relates magnetic field to", listOf("Charge", "Voltage", "Current", "Power"), "Current"),
        q(27, "Magnetostatics - Ampere Law", "∮H·dl equals", listOf("Flux", "Current enclosed", "Voltage", "Power"), "Current enclosed"),
        q(28, "Magnetostatics - Magnetic Flux", "Magnetic flux is measured in", listOf("Tesla", "Weber", "Henry", "Farad"), "Weber"),
        q(29, "Magnetostatics - Magnetic Circuits", "Magnetic reluctance is analogous to", listOf("Resistance", "Capacitance", "Inductance", "Power"), "Resistance"),
        q(30, "Magnetostatics - Inductance", "The SI unit of inductance is", listOf("Farad", "Tesla", "Henry", "Weber"), "Henry"),
        q(31, "Magnetostatics - Inductance", "Energy stored in an inductor is", listOf("LI²", "½LI²", "CV²", "IV"), "½LI²"),
        q(32, "Magnetostatics - Numerical", "An inductor of 2 H carrying 3 A stores energy", listOf("3 J", "6 J", "9 J", "18 J"), "9 J"),
        q(33, "Magnetostatics - Numerical", "Flux density for μ=2 and H=5 A/m is", listOf("2", "5", "10", "25"), "10"),
        q(34, "Magnetostatics - Boundary Conditions", "Normal component of magnetic flux density is", listOf("Continuous", "Zero", "Infinite", "Discontinuous always"), "Continuous"),
        q(35, "Magnetostatics - Boundary Conditions", "Tangential component of H changes according to", listOf("Surface current density", "Charge density", "Potential", "Voltage"), "Surface current density"),
        q(36, "Maxwell Equations", "Maxwell's equations unify", listOf("Electricity and Magnetism", "Mechanics and Heat", "Optics and Acoustics", "Electronics and Control"), "Electricity and Magnetism"),
        q(37, "Maxwell Equations", "Displacement current was introduced by", listOf("Ampere", "Faraday", "Maxwell", "Gauss"), "Maxwell"),
        q(38, "Maxwell Equations", "Faraday's law relates changing magnetic field to", listOf("Resistance", "Induced electric field", "Charge", "Current density"), "Induced electric field"),
        q(39, "Maxwell Equations", "∇·B equals", listOf("1", "−1", "0", "∞"), "0"),
        q(40, "Maxwell Equations", "∇·D equals", listOf("ρv", "0", "J", "B"), "ρv"),
        q(41, "Maxwell Equations", "The curl of E is related to", listOf("Current density", "Magnetic flux variation", "Charge density", "Potential only"), "Magnetic flux variation"),
        q(42, "Maxwell Equations", "The curl of H is related to", listOf("Current density and displacement current", "Charge only", "Potential only", "Flux only"), "Current density and displacement current"),
        q(43, "Maxwell Equations", "Displacement current density equals", listOf("∂D/∂t", "∂B/∂t", "ρv", "J/ρ"), "∂D/∂t"),
        q(44, "Maxwell Equations", "The continuity equation expresses conservation of", listOf("Energy", "Charge", "Momentum", "Flux"), "Charge"),
        q(45, "Maxwell Equations", "Electromagnetic waves can exist due to", listOf("Coupled E and H fields", "Charge only", "Current only", "Resistance"), "Coupled E and H fields"),
        q(46, "Mixed GATE Numerical", "Capacitance of two 4 μF capacitors in series is", listOf("2 μF", "4 μF", "8 μF", "16 μF"), "2 μF"),
        q(47, "Mixed GATE Numerical", "A charge of 5 C in an electric field of 4 V/m experiences force", listOf("1 N", "9 N", "20 N", "25 N"), "20 N"),
        q(48, "Mixed GATE Numerical", "An inductor of 1 H carrying 2 A stores energy", listOf("1 J", "2 J", "4 J", "8 J"), "2 J"),
        q(49, "Mixed GATE Numerical", "If B=0.5 T and area=2 m² normal to field, flux is", listOf("0.25 Wb", "1 Wb", "2 Wb", "4 Wb"), "1 Wb"),
        q(50, "Mixed GATE Theory", "Electromagnetic field theory is fundamentally based on", listOf("Kirchhoff's laws", "Maxwell's equations", "Ohm's law", "Thevenin theorem"), "Maxwell's equations")
    )

    private val list2 = listOf(
        q(51, "Electromagnetic Waves - Wave Equation", "Electromagnetic waves in free space are", listOf("Longitudinal", "Transverse", "Scalar", "Stationary"), "Transverse"),
        q(52, "Electromagnetic Waves - Wave Equation", "The velocity of electromagnetic waves in free space is approximately", listOf("3×10^6 m/s", "3×10^7 m/s", "3×10^8 m/s", "3×10^9 m/s"), "3×10^8 m/s"),
        q(53, "Electromagnetic Waves - Wave Equation", "The propagation constant is generally represented by", listOf("α+jβ", "R+jX", "σ+jω", "E+jH"), "α+jβ"),
        q(54, "Electromagnetic Waves - Attenuation", "The attenuation constant is denoted by", listOf("β", "γ", "α", "η"), "α"),
        q(55, "Electromagnetic Waves - Phase Constant", "The phase constant is denoted by", listOf("α", "β", "η", "μ"), "β"),
        q(56, "Electromagnetic Waves - Plane Waves", "In a uniform plane wave, E and H fields are", listOf("Parallel", "Perpendicular", "Zero", "Random"), "Perpendicular"),
        q(57, "Electromagnetic Waves - Plane Waves", "The direction of wave propagation is given by", listOf("E×H", "H×E", "E+H", "E−H"), "E×H"),
        q(58, "Electromagnetic Waves - Wavelength", "Wavelength is related to frequency by λ =", listOf("cf", "f/c", "c/f", "1/cf"), "c/f"),
        q(59, "Electromagnetic Waves - Frequency", "The SI unit of frequency is", listOf("Rad/s", "Hertz", "Weber", "Tesla"), "Hertz"),
        q(60, "Electromagnetic Waves - Intrinsic Impedance", "Intrinsic impedance of free space is approximately", listOf("50 Ω", "75 Ω", "377 Ω", "1000 Ω"), "377 Ω"),
        q(61, "Polarization", "If the electric field maintains a fixed direction, polarization is", listOf("Circular", "Linear", "Elliptical", "Random"), "Linear"),
        q(62, "Polarization", "Equal orthogonal components with 90° phase difference produce", listOf("Linear polarization", "Circular polarization", "No polarization", "Standing wave"), "Circular polarization"),
        q(63, "Polarization", "The most general polarization state is", listOf("Linear", "Circular", "Elliptical", "Uniform"), "Elliptical"),
        q(64, "Poynting Vector", "The Poynting vector represents", listOf("Charge density", "Power flow density", "Magnetic flux", "Potential"), "Power flow density"),
        q(65, "Poynting Vector", "The Poynting vector is given by", listOf("E·H", "E×H", "E/H", "H/E"), "E×H"),
        q(66, "Poynting Vector", "The SI unit of Poynting vector is", listOf("W", "W/m²", "J/m", "V/m"), "W/m²"),
        q(67, "Skin Depth", "Skin depth decreases with increasing", listOf("Frequency", "Resistivity", "Permeability denominator", "All media dimensions"), "Frequency"),
        q(68, "Skin Depth", "Skin effect is more prominent at", listOf("Low frequency", "High frequency", "DC only", "Zero frequency"), "High frequency"),
        q(69, "Skin Depth", "Skin depth is the depth where current density falls to about", listOf("90%", "50%", "36.8%", "10%"), "36.8%"),
        q(70, "Wave Propagation", "A lossless medium has conductivity", listOf("Infinite", "Zero", "Very high", "Equal to permittivity"), "Zero"),
        q(71, "Wave Propagation", "In a lossless medium, attenuation constant α is", listOf("1", "β", "0", "∞"), "0"),
        q(72, "Wave Propagation", "Phase velocity in a medium is", listOf("ωβ", "β/ω", "ω/β", "1/ωβ"), "ω/β"),
        q(73, "Wave Propagation", "The propagation constant of a lossless medium is", listOf("α", "jβ", "α+jβ", "0"), "jβ"),
        q(74, "Wave Propagation", "In free space, μr equals", listOf("0", "1", "377", "8.854×10^-12"), "1"),
        q(75, "Wave Propagation", "In free space, εr equals", listOf("0", "1", "377", "4π×10^-7"), "1"),
        q(76, "Maxwell Equations", "The differential form of Gauss's law for electricity is", listOf("∇·D=ρv", "∇·B=0", "∇×E=0", "∇×H=J"), "∇·D=ρv"),
        q(77, "Maxwell Equations", "The differential form of Gauss's law for magnetism is", listOf("∇·B=0", "∇·D=ρv", "∇×E=0", "∇×H=J"), "∇·B=0"),
        q(78, "Maxwell Equations", "Faraday's law in differential form is", listOf("∇×E=-∂B/∂t", "∇·B=0", "∇×H=J", "∇·D=ρ"), "∇×E=-∂B/∂t"),
        q(79, "Maxwell Equations", "Ampere-Maxwell law in differential form is", listOf("∇×H=J+∂D/∂t", "∇×E=0", "∇·B=0", "∇·D=ρ"), "∇×H=J+∂D/∂t"),
        q(80, "Maxwell Equations", "Maxwell added which term to Ampere's law?", listOf("Charge density", "Displacement current", "Magnetic current", "Potential"), "Displacement current"),
        q(81, "Numerical - Wave Propagation", "An EM wave has frequency 300 MHz in free space. Its wavelength is", listOf("0.1 m", "1 m", "10 m", "100 m"), "1 m"),
        q(82, "Numerical - Wave Propagation", "If E=377 V/m in free space, the corresponding H is", listOf("0.5 A/m", "1 A/m", "2 A/m", "377 A/m"), "1 A/m"),
        q(83, "Numerical - Poynting Vector", "For E=100 V/m and H=2 A/m perpendicular to each other, power density is", listOf("50 W/m²", "100 W/m²", "200 W/m²", "400 W/m²"), "200 W/m²"),
        q(84, "Numerical - Intrinsic Impedance", "Intrinsic impedance of free space is closest to", listOf("188 Ω", "250 Ω", "377 Ω", "500 Ω"), "377 Ω"),
        q(85, "Numerical - Wavelength", "A 100 MHz wave in free space has wavelength", listOf("1 m", "2 m", "3 m", "30 m"), "3 m"),
        q(86, "Numerical - Frequency", "A wave with wavelength 0.5 m in free space has frequency", listOf("300 MHz", "600 MHz", "150 MHz", "60 MHz"), "600 MHz"),
        q(87, "Numerical - Phase Velocity", "For ω=100 rad/s and β=2 rad/m, phase velocity is", listOf("25 m/s", "50 m/s", "100 m/s", "200 m/s"), "50 m/s"),
        q(88, "Numerical - Power Density", "If E=50 V/m and H=4 A/m, the magnitude of Poynting vector is", listOf("50 W/m²", "100 W/m²", "200 W/m²", "400 W/m²"), "200 W/m²"),
        q(89, "Numerical - Maxwell Equations", "In free space, if magnetic flux density is constant with time, induced electric field is", listOf("Maximum", "Zero", "Infinite", "Oscillatory"), "Zero"),
        q(90, "Numerical - Polarization", "Two orthogonal electric field components of equal magnitude and 90° phase difference produce", listOf("Linear polarization", "Circular polarization", "No wave", "Standing wave"), "Circular polarization"),
        q(91, "GATE Style Theory", "In a uniform plane wave, E, H and propagation direction form a", listOf("Parallel set", "Right-handed orthogonal set", "Left-handed set", "Coplanar set"), "Right-handed orthogonal set"),
        q(92, "GATE Style Theory", "The medium with σ=0 and finite ε, μ is called", listOf("Perfect conductor", "Lossless dielectric", "Good conductor", "Magnetic conductor"), "Lossless dielectric"),
        q(93, "GATE Style Theory", "Electromagnetic waves cannot propagate in a perfect conductor because", listOf("β=0", "Skin depth is zero", "μ=0", "ε=0"), "Skin depth is zero"),
        q(94, "GATE Style Theory", "The ratio E/H in a medium is called", listOf("Admittance", "Intrinsic impedance", "Conductivity", "Reluctance"), "Intrinsic impedance"),
        q(95, "GATE Style Theory", "Power carried by an EM wave is proportional to", listOf("E only", "H only", "E×H", "E+H"), "E×H"),
        q(96, "GATE Style Numerical", "A 30 MHz wave in free space has wavelength", listOf("1 m", "3 m", "10 m", "30 m"), "10 m"),
        q(97, "GATE Style Numerical", "For E=754 V/m in free space, H equals", listOf("1 A/m", "2 A/m", "3 A/m", "4 A/m"), "2 A/m"),
        q(98, "GATE Style Numerical", "A wave of frequency 1 GHz in free space has wavelength", listOf("0.03 m", "0.3 m", "3 m", "30 m"), "0.3 m"),
        q(99, "GATE Style Numerical", "For E=3770 V/m in free space, H is", listOf("1 A/m", "5 A/m", "10 A/m", "20 A/m"), "10 A/m"),
        q(100, "GATE Style Theory", "The foundation for electromagnetic wave propagation is provided by", listOf("Ohm's law", "Kirchhoff's laws", "Maxwell's equations", "Thevenin theorem"), "Maxwell's equations")
    )

    private val list3 = listOf(
        q(101, "Transmission Lines - Basics", "A transmission line is characterized by distributed", listOf("R only", "L only", "R, L, C and G", "C only"), "R, L, C and G"),
        q(102, "Transmission Lines - Basics", "Characteristic impedance is denoted by", listOf("ZL", "Z0", "ZS", "ZR"), "Z0"),
        q(103, "Transmission Lines - Basics", "The characteristic impedance of a lossless line is", listOf("√(R/G)", "√(L/C)", "R/L", "G/C"), "√(L/C)"),
        q(104, "Transmission Lines - Propagation Constant", "The propagation constant of a transmission line is denoted by", listOf("η", "γ", "ρ", "τ"), "γ"),
        q(105, "Transmission Lines - Matching", "Maximum power transfer occurs when", listOf("ZL=0", "ZL=∞", "ZL=Z0", "ZL=2Z0"), "ZL=Z0"),
        q(106, "Reflection Coefficient", "Reflection coefficient is represented by", listOf("β", "Γ", "η", "α"), "Γ"),
        q(107, "Reflection Coefficient", "For a matched load, reflection coefficient is", listOf("1", "-1", "0", "∞"), "0"),
        q(108, "Reflection Coefficient", "For an open-circuited line, Γ equals", listOf("0", "1", "-1", "0.5"), "1"),
        q(109, "Reflection Coefficient", "For a short-circuited line, Γ equals", listOf("1", "-1", "0", "0.5"), "-1"),
        q(110, "Reflection Coefficient", "The magnitude of reflection coefficient lies between", listOf("0 and 1", "1 and 2", "-2 and 2", "0 and ∞"), "0 and 1"),
        q(111, "VSWR", "VSWR stands for", listOf("Voltage Standing Wave Ratio", "Variable Standing Wave Resistance", "Voltage Source Wave Ratio", "Voltage Signal Wave Response"), "Voltage Standing Wave Ratio"),
        q(112, "VSWR", "For a perfectly matched line, VSWR is", listOf("0", "1", "∞", "2"), "1"),
        q(113, "VSWR", "For total reflection, VSWR is", listOf("0", "1", "∞", "0.5"), "∞"),
        q(114, "Standing Waves", "Standing waves are produced due to", listOf("Attenuation only", "Reflection of waves", "DC excitation", "Magnetic saturation"), "Reflection of waves"),
        q(115, "Standing Waves", "A voltage maximum occurs due to", listOf("Constructive interference", "Destructive interference", "No reflection", "Zero frequency"), "Constructive interference"),
        q(116, "Smith Chart", "Smith chart is mainly used for", listOf("Matrix calculations", "Impedance matching", "Field plotting", "Numerical integration"), "Impedance matching"),
        q(117, "Smith Chart", "Smith chart is based on plotting", listOf("Reflection coefficient", "Voltage", "Current", "Magnetic field"), "Reflection coefficient"),
        q(118, "Impedance Matching", "Impedance matching is done to", listOf("Increase attenuation", "Reduce power transfer", "Eliminate reflections", "Increase VSWR"), "Eliminate reflections"),
        q(119, "Impedance Matching", "A quarter-wave transformer has electrical length", listOf("λ/8", "λ/4", "λ/2", "λ"), "λ/4"),
        q(120, "Impedance Matching", "The characteristic impedance of a quarter-wave transformer is", listOf("ZL/Z0", "√(Z0ZL)", "Z0+ZL", "Z0−ZL"), "√(Z0ZL)"),
        q(121, "Transmission Lines - Lossless Line", "For a lossless transmission line, R and G are", listOf("Finite", "Infinite", "Zero", "Equal"), "Zero"),
        q(122, "Transmission Lines - Velocity", "Velocity of propagation on a lossless line is", listOf("√(LC)", "1/√(LC)", "L/C", "C/L"), "1/√(LC)"),
        q(123, "Transmission Lines - Input Impedance", "Input impedance of a matched line equals", listOf("0", "Z0", "ZL²", "∞"), "Z0"),
        q(124, "Transmission Lines - Input Impedance", "For a lossless line of length λ/2, input impedance equals", listOf("Z0", "ZL", "Z0²/ZL", "0"), "ZL"),
        q(125, "Transmission Lines - Input Impedance", "For a quarter-wave line, Zin equals", listOf("ZL", "Z0", "Z0²/ZL", "ZL²/Z0"), "Z0²/ZL"),
        q(126, "Numerical - Reflection Coefficient", "For Z0=50Ω and ZL=50Ω, reflection coefficient magnitude is", listOf("0", "0.5", "1", "2"), "0"),
        q(127, "Numerical - Reflection Coefficient", "For an open circuit load, |Γ| equals", listOf("0", "0.5", "1", "2"), "1"),
        q(128, "Numerical - VSWR", "If |Γ|=0, VSWR is", listOf("0", "1", "2", "∞"), "1"),
        q(129, "Numerical - VSWR", "If |Γ|=1, VSWR is", listOf("0", "1", "2", "∞"), "∞"),
        q(130, "Numerical - Quarter Wave Transformer", "If Z0=50Ω and ZL=200Ω, quarter-wave transformer impedance is", listOf("25Ω", "50Ω", "100Ω", "200Ω"), "100Ω"),
        q(131, "Numerical - Wavelength", "At 300 MHz, free-space wavelength is", listOf("0.1 m", "1 m", "10 m", "100 m"), "1 m"),
        q(132, "Numerical - Transmission Line", "For L=1 μH/m and C=100 pF/m, Z0 is", listOf("50Ω", "100Ω", "10Ω", "500Ω"), "100Ω"),
        q(133, "Numerical - Propagation Velocity", "For L=1 μH/m and C=100 pF/m, velocity is", listOf("10^7 m/s", "10^8 m/s", "10^9 m/s", "3×10^8 m/s"), "10^8 m/s"),
        q(134, "Numerical - Matching", "A matched transmission line has reflected power", listOf("Maximum", "Half", "Zero", "Infinite"), "Zero"),
        q(135, "Numerical - Input Impedance", "A λ/2 lossless line terminated in 75Ω has input impedance", listOf("50Ω", "75Ω", "100Ω", "25Ω"), "75Ω"),
        q(136, "Transmission Lines - Theory", "Characteristic impedance is independent of", listOf("Load impedance", "L", "C", "Frequency in ideal lossless line"), "Load impedance"),
        q(137, "Transmission Lines - Theory", "The telegrapher equations describe", listOf("Electric machines", "Transmission lines", "Control systems", "Semiconductors"), "Transmission lines"),
        q(138, "Transmission Lines - Theory", "A distortionless line satisfies", listOf("R/L = G/C", "R=0", "G=0", "R=G"), "R/L = G/C"),
        q(139, "Transmission Lines - Theory", "The attenuation constant unit is", listOf("dB", "Np/m", "Ω", "A/m"), "Np/m"),
        q(140, "Transmission Lines - Theory", "The phase constant unit is", listOf("rad/m", "Ω", "Np", "W/m²"), "rad/m"),
        q(141, "GATE Style Problem", "If Γ=0.5, reflected voltage amplitude is", listOf("Half of incident voltage", "Equal to incident voltage", "Double incident voltage", "Zero"), "Half of incident voltage"),
        q(142, "GATE Style Problem", "For a matched line, power delivered to load is", listOf("Zero", "Half incident power", "Maximum", "Infinite"), "Maximum"),
        q(143, "GATE Style Problem", "An open-circuit line reflects with phase shift", listOf("0°", "90°", "180°", "270°"), "0°"),
        q(144, "GATE Style Problem", "A short-circuit line reflects with phase shift", listOf("0°", "90°", "180°", "45°"), "180°"),
        q(145, "GATE Style Problem", "Quarter-wave transformer is generally used for", listOf("Amplification", "Oscillation", "Impedance matching", "Filtering"), "Impedance matching"),
        q(146, "GATE Style Problem", "Smith chart can represent", listOf("Normalized impedance", "Only resistance", "Only reactance", "Only conductance"), "Normalized impedance"),
        q(147, "GATE Style Problem", "Standing wave minima occur due to", listOf("Constructive interference", "Destructive interference", "No interference", "Resonance only"), "Destructive interference"),
        q(148, "GATE Style Problem", "For Γ=0, all incident power is", listOf("Reflected", "Absorbed by source", "Delivered to load", "Lost in air"), "Delivered to load"),
        q(149, "GATE Style Problem", "The load impedance equal to characteristic impedance results in", listOf("Standing waves", "Perfect matching", "Infinite reflection", "Zero transmission"), "Perfect matching"),
        q(150, "GATE Style Problem", "Transmission line theory is most important when line length is", listOf("Negligible compared to wavelength", "Comparable to wavelength", "Always zero", "Independent of wavelength"), "Comparable to wavelength")
    )

    private val list4 = listOf(
        q(151, "Boundary Conditions", "At the interface of two dielectric media, the tangential component of electric field intensity is", listOf("Zero", "Discontinuous", "Continuous", "Infinite"), "Continuous"),
        q(152, "Boundary Conditions", "The normal component of electric flux density changes according to", listOf("Current density", "Surface charge density", "Magnetic flux", "Power density"), "Surface charge density"),
        q(153, "Boundary Conditions", "The normal component of magnetic flux density across a boundary is", listOf("Infinite", "Discontinuous", "Continuous", "Zero"), "Continuous"),
        q(154, "Boundary Conditions", "The tangential component of magnetic field intensity changes according to", listOf("Surface current density", "Surface charge density", "Voltage", "Potential"), "Surface current density"),
        q(155, "Perfect Conductors", "Inside a perfect conductor, electric field intensity is", listOf("1 V/m", "Infinite", "Zero", "377 V/m"), "Zero"),
        q(156, "Perfect Conductors", "Inside a perfect conductor, electromagnetic waves", listOf("Propagate freely", "Attenuate partially", "Do not exist", "Increase in amplitude"), "Do not exist"),
        q(157, "Waveguides", "A waveguide is primarily used to", listOf("Store energy", "Guide electromagnetic waves", "Generate voltage", "Measure current"), "Guide electromagnetic waves"),
        q(158, "Waveguides", "Rectangular waveguides are commonly used at", listOf("Low frequencies", "Microwave frequencies", "DC", "Audio frequencies"), "Microwave frequencies"),
        q(159, "Waveguides", "The dominant mode in a rectangular waveguide is", listOf("TM11", "TE10", "TEM", "TM10"), "TE10"),
        q(160, "Waveguides", "The cutoff frequency of a waveguide determines", listOf("Power loss", "Minimum operating frequency", "Maximum voltage", "Current rating"), "Minimum operating frequency"),
        q(161, "Waveguide Modes", "TE mode stands for", listOf("Transverse Electric", "Total Electric", "Transverse Energy", "Terminal Electric"), "Transverse Electric"),
        q(162, "Waveguide Modes", "TM mode stands for", listOf("Transverse Magnetic", "Total Magnetic", "Terminal Magnetic", "Thermal Magnetic"), "Transverse Magnetic"),
        q(163, "Waveguide Modes", "TEM mode implies", listOf("Ez=0 and Hz=0", "Ez≠0 only", "Hz≠0 only", "Ez=Hz≠0"), "Ez=0 and Hz=0"),
        q(164, "Waveguide Modes", "TEM mode cannot propagate in a hollow rectangular waveguide because", listOf("No dielectric", "Single conductor structure", "Low frequency", "High attenuation"), "Single conductor structure"),
        q(165, "Waveguide Modes", "TE10 mode has", listOf("One half-wave variation along broad dimension", "No variation", "Two variations", "Three variations"), "One half-wave variation along broad dimension"),
        q(166, "Numerical - Waveguide", "For a rectangular waveguide, dominant mode is", listOf("TE10", "TE11", "TM11", "TEM"), "TE10"),
        q(167, "Numerical - Waveguide", "If operating frequency is below cutoff frequency, the wave", listOf("Propagates", "Amplifies", "Attenuates exponentially", "Becomes TEM"), "Attenuates exponentially"),
        q(168, "Numerical - Waveguide", "Wave impedance in a waveguide depends on", listOf("Frequency", "Mode", "Medium", "All of these"), "All of these"),
        q(169, "Transmission Lines vs Waveguides", "TEM waves can propagate in", listOf("Coaxial cable", "Rectangular waveguide", "Circular hollow waveguide", "Single conductor guide"), "Coaxial cable"),
        q(170, "Transmission Lines vs Waveguides", "Coaxial cable supports", listOf("TEM mode", "Only TE mode", "Only TM mode", "No propagation"), "TEM mode"),
        q(171, "Poynting Theorem", "Poynting theorem represents conservation of", listOf("Charge", "Energy", "Mass", "Momentum"), "Energy"),
        q(172, "Poynting Theorem", "The net power leaving a closed surface is obtained from", listOf("Surface integral of Poynting vector", "Electric potential", "Current density", "Charge density"), "Surface integral of Poynting vector"),
        q(173, "Radiation", "Electromagnetic radiation is produced by", listOf("Static charges", "Accelerated charges", "Constant resistance", "Constant inductance"), "Accelerated charges"),
        q(174, "Radiation", "An antenna converts", listOf("Mechanical energy to heat", "Guided waves to radiated waves", "Heat to light", "Current to resistance"), "Guided waves to radiated waves"),
        q(175, "Radiation", "Far-field region is also called", listOf("Reactive region", "Radiation zone", "Static zone", "Cutoff zone"), "Radiation zone"),
        q(176, "Numerical", "For E = 754 V/m in free space, H equals", listOf("0.5 A/m", "1 A/m", "2 A/m", "4 A/m"), "2 A/m"),
        q(177, "Numerical", "For E = 377 V/m and H = 1 A/m, power density is", listOf("188.5 W/m²", "377 W/m²", "754 W/m²", "1 W/m²"), "377 W/m²"),
        q(178, "Numerical", "A 600 MHz wave in free space has wavelength", listOf("0.5 m", "1 m", "2 m", "5 m"), "0.5 m"),
        q(179, "Numerical", "A 150 MHz wave in free space has wavelength", listOf("0.5 m", "1 m", "2 m", "4 m"), "2 m"),
        q(180, "Numerical", "For λ = 0.3 m, frequency in free space is", listOf("100 MHz", "500 MHz", "1 GHz", "3 GHz"), "1 GHz"),
        q(181, "Advanced Maxwell Equations", "The divergence of magnetic flux density is", listOf("1", "-1", "0", "ρv"), "0"),
        q(182, "Advanced Maxwell Equations", "The existence of EM waves is due to coupling between", listOf("Voltage and current", "E and H fields", "Resistance and inductance", "Charge and flux"), "E and H fields"),
        q(183, "Advanced Maxwell Equations", "Displacement current density is proportional to", listOf("∂D/∂t", "∂B/∂t", "ρv", "J"), "∂D/∂t"),
        q(184, "Advanced Maxwell Equations", "Faraday's law is associated with", listOf("Electrostatic field", "Electromagnetic induction", "Capacitance", "Reluctance"), "Electromagnetic induction"),
        q(185, "Advanced Maxwell Equations", "Ampere-Maxwell law relates magnetic field to", listOf("Charge only", "Current and displacement current", "Voltage only", "Potential only"), "Current and displacement current"),
        q(186, "GATE Theory", "The intrinsic impedance of free space is approximately", listOf("50 Ω", "75 Ω", "377 Ω", "1000 Ω"), "377 Ω"),
        q(187, "GATE Theory", "Skin depth decreases with increase in", listOf("Frequency", "Resistivity", "Wavelength", "Capacitance"), "Frequency"),
        q(188, "GATE Theory", "For a perfect dielectric, conductivity is", listOf("Infinite", "Zero", "1 S/m", "377 S/m"), "Zero"),
        q(189, "GATE Theory", "In a lossless medium, attenuation constant is", listOf("1", "β", "0", "∞"), "0"),
        q(190, "GATE Theory", "The propagation constant is generally", listOf("α+jβ", "R+jX", "σ+jω", "E+jH"), "α+jβ"),
        q(191, "Mixed Numerical", "If H = 5 A/m in free space, B is approximately", listOf("2π×10^-7 T", "4π×10^-7 T", "20π×10^-7 T", "10π×10^-7 T"), "20π×10^-7 T"),
        q(192, "Mixed Numerical", "A capacitor of 1 μF charged to 100 V stores energy", listOf("0.005 J", "0.01 J", "0.05 J", "0.1 J"), "0.005 J"),
        q(193, "Mixed Numerical", "An inductor of 4 H carrying 2 A stores energy", listOf("4 J", "8 J", "16 J", "32 J"), "8 J"),
        q(194, "Mixed Numerical", "For Z0 = 50 Ω and ZL = 50 Ω, VSWR is", listOf("0", "1", "2", "∞"), "1"),
        q(195, "Mixed Numerical", "For Z0 = 50 Ω and open-circuit load, VSWR is", listOf("0", "1", "2", "∞"), "∞"),
        q(196, "Mixed Numerical", "For a matched line, reflected power is", listOf("Zero", "Half", "Equal to incident", "Infinite"), "Zero"),
        q(197, "Mixed Numerical", "A λ/2 transmission line repeats the load impedance at its input", listOf("True", "False", "Only for lossy line", "Only for open circuit"), "True"),
        q(198, "Mixed Numerical", "The quarter-wave transformer is mainly used for", listOf("Amplification", "Oscillation", "Impedance matching", "Filtering"), "Impedance matching"),
        q(199, "Comprehensive GATE Theory", "The complete theory of electromagnetic fields is based on", listOf("Ohm's law", "Kirchhoff's laws", "Maxwell's equations", "Thevenin theorem"), "Maxwell's equations"),
        q(200, "Comprehensive GATE Theory", "The dominant mode in a rectangular waveguide is", listOf("TM11", "TE10", "TEM", "TM10"), "TE10")
    )
}
