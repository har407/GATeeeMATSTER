package com.example.data

object PowerElectronicsQuestions {

    val questions: List<GateQuestion> by lazy {
        listOf(
            q(1, "Power Diodes", "A power diode differs from a signal diode primarily due to its ability to handle:", listOf("Higher frequency", "Higher voltage and current", "Lower power", "Lower temperature"), "Higher voltage and current"),
            q(2, "Power Diodes", "The reverse recovery time of a power diode affects:", listOf("Efficiency at high frequency", "Transformer rating", "Power factor only", "Load resistance"), "Efficiency at high frequency"),
            q(3, "SCR", "An SCR can be turned ON by:", listOf("Applying negative gate current", "Applying positive gate current", "Removing gate connection", "Reducing anode voltage"), "Applying positive gate current"),
            q(4, "SCR", "The minimum anode current required to keep an SCR in conduction is called:", listOf("Latching current", "Holding current", "Peak current", "Gate current"), "Holding current"),
            q(5, "SCR", "The two-transistor analogy of an SCR consists of:", listOf("Two NPN transistors", "Two PNP transistors", "One PNP and one NPN transistor", "One MOSFET and one BJT"), "One PNP and one NPN transistor"),
            q(6, "TRIAC", "A TRIAC can conduct in:", listOf("One direction only", "Both directions", "Reverse direction only", "Gate circuit only"), "Both directions"),
            q(7, "DIAC", "A DIAC is commonly used for triggering:", listOf("MOSFET", "IGBT", "TRIAC", "GTO"), "TRIAC"),
            q(8, "GTO", "A GTO can be turned OFF by:", listOf("Removing anode voltage", "Applying negative gate current", "Increasing load current", "Applying positive gate current"), "Applying negative gate current"),
            q(9, "MOSFET", "A power MOSFET is a:", listOf("Current-controlled device", "Voltage-controlled device", "Frequency-controlled device", "Power-controlled device"), "Voltage-controlled device"),
            q(10, "MOSFET", "The input impedance of a MOSFET is generally:", listOf("Very low", "Moderate", "Very high", "Zero"), "Very high"),
            q(11, "IGBT", "An IGBT combines features of:", listOf("SCR and TRIAC", "MOSFET and BJT", "Diode and SCR", "GTO and TRIAC"), "MOSFET and BJT"),
            q(12, "IGBT", "Compared to MOSFETs, IGBTs are preferred for:", listOf("Very high-frequency applications", "High-power applications", "Low-voltage logic circuits", "RF applications"), "High-power applications"),
            q(13, "Device Characteristics", "The safe operating area (SOA) specifies:", listOf("Allowable operating limits", "Trigger angle", "Switching frequency", "Commutation time"), "Allowable operating limits"),
            q(14, "Device Characteristics", "The dv/dt rating of an SCR indicates:", listOf("Maximum current rise rate", "Maximum voltage rise rate", "Switching frequency", "Power loss"), "Maximum voltage rise rate"),
            q(15, "Device Characteristics", "The di/dt rating of an SCR limits:", listOf("Rate of voltage rise", "Rate of current rise", "Gate current", "Load power"), "Rate of current rise"),
            q(16, "Triggering Circuits", "The firing angle of an SCR is measured from:", listOf("Current zero crossing", "Voltage zero crossing", "Peak voltage", "Peak current"), "Voltage zero crossing"),
            q(17, "SCR Commutation", "Commutation of an SCR means:", listOf("Turning ON the SCR", "Turning OFF the SCR", "Increasing current", "Reducing voltage"), "Turning OFF the SCR"),
            q(18, "SCR Commutation", "Natural commutation occurs in:", listOf("AC circuits", "DC circuits", "Battery systems", "Choppers only"), "AC circuits"),
            q(19, "SCR Commutation", "Forced commutation is generally required in:", listOf("AC circuits", "DC circuits", "Transmission lines", "Transformers"), "DC circuits"),
            q(20, "Single Phase Controlled Rectifier", "A single-phase half-wave controlled rectifier uses:", listOf("One SCR", "Two SCRs", "Four SCRs", "Six SCRs"), "One SCR"),
            q(21, "Single Phase Controlled Rectifier", "Increasing firing angle α in a controlled rectifier generally:", listOf("Increases average output voltage", "Decreases average output voltage", "Does not affect output voltage", "Increases frequency"), "Decreases average output voltage"),
            q(22, "Single Phase Controlled Rectifier", "For α = 0°, a fully controlled rectifier behaves like:", listOf("An inverter", "An uncontrolled rectifier", "A chopper", "A cycloconverter"), "An uncontrolled rectifier"),
            q(23, "Three Phase Controlled Rectifier", "A three-phase full converter typically uses:", listOf("2 SCRs", "4 SCRs", "6 SCRs", "12 SCRs"), "6 SCRs"),
            q(24, "Three Phase Controlled Rectifier", "The ripple frequency of a 3-phase full converter is:", listOf("f", "2f", "3f", "6f"), "6f"),
            q(25, "Dual Converters", "A dual converter consists of:", listOf("Two controlled rectifiers connected back-to-back", "Two transformers", "Two choppers", "Two inverters"), "Two controlled rectifiers connected back-to-back"),
            q(26, "Choppers", "A DC chopper is a device that converts:", listOf("AC to DC", "DC to AC", "Fixed DC to variable DC", "AC to AC"), "Fixed DC to variable DC"),
            q(27, "Choppers", "The duty cycle of a chopper is defined as:", listOf("Toff/Ton", "Ton/(Ton+Toff)", "Ton×Toff", "Toff−Ton"), "Ton/(Ton+Toff)"),
            q(28, "Buck Converter", "A buck converter is used to:", listOf("Increase DC voltage", "Decrease DC voltage", "Convert DC to AC", "Increase frequency"), "Decrease DC voltage"),
            q(29, "Buck Converter", "For an ideal buck converter operating in CCM, the output voltage is:", listOf("D × Vin", "Vin/D", "Vin/(1-D)", "-D × Vin"), "D × Vin"),
            q(30, "Boost Converter", "A boost converter is used to:", listOf("Reduce voltage", "Increase voltage", "Invert voltage", "Rectify voltage"), "Increase voltage"),
            q(31, "Boost Converter", "For an ideal boost converter, output voltage is:", listOf("D × Vin", "Vin/(1-D)", "Vin × (1-D)", "-Vin"), "Vin/(1-D)"),
            q(32, "Buck-Boost Converter", "The output voltage polarity of an ideal buck-boost converter is:", listOf("Same as input", "Opposite to input", "Always zero", "AC"), "Opposite to input"),
            q(33, "Buck-Boost Converter", "The voltage conversion ratio of an ideal buck-boost converter is:", listOf("D/(1-D)", "-D/(1-D)", "1/(1-D)", "D"), "-D/(1-D)"),
            q(34, "Cuk Converter", "A Cuk converter provides:", listOf("Only step-up operation", "Only step-down operation", "Step-up and step-down operation", "AC output"), "Step-up and step-down operation"),
            q(35, "SEPIC Converter", "The major advantage of a SEPIC converter is:", listOf("Output voltage polarity reversal", "Non-inverted output voltage", "No inductors required", "No switching device required"), "Non-inverted output voltage"),
            q(36, "Choppers", "Type-A chopper operates in:", listOf("First quadrant", "Second quadrant", "Third quadrant", "Fourth quadrant"), "First quadrant"),
            q(37, "Choppers", "A Type-B chopper is also known as:", listOf("Step-down chopper", "Step-up chopper", "Two-quadrant chopper", "Bridge chopper"), "Step-up chopper"),
            q(38, "Choppers", "Regenerative braking in DC drives commonly uses:", listOf("Type-A chopper", "Type-B chopper", "Type-E chopper", "Half-wave rectifier"), "Type-B chopper"),
            q(39, "DC Motor Drives", "Speed control of a DC motor using a chopper is achieved by varying:", listOf("Frequency only", "Duty cycle", "Armature resistance only", "Field winding turns"), "Duty cycle"),
            q(40, "DC Motor Drives", "Regenerative braking returns energy to:", listOf("Load", "Motor shaft", "Supply source", "Ground"), "Supply source"),
            q(41, "DC Motor Drives", "A four-quadrant DC drive can operate in:", listOf("One quadrant only", "Two quadrants only", "All four quadrants", "Forward motoring only"), "All four quadrants"),
            q(42, "Choppers", "Continuous conduction mode (CCM) implies that:", listOf("Inductor current never becomes zero", "Switch is always ON", "Output voltage is zero", "Current is discontinuous"), "Inductor current never becomes zero"),
            q(43, "Choppers", "Discontinuous conduction mode (DCM) occurs when:", listOf("Inductor current falls to zero", "Output voltage becomes negative", "Switch never turns OFF", "Duty cycle is 100%"), "Inductor current falls to zero"),
            q(44, "Buck Converter", "The freewheeling diode in a buck converter provides a path for:", listOf("Input current", "Inductor current", "Gate current", "Leakage current"), "Inductor current"),
            q(45, "Boost Converter", "Energy storage in a boost converter primarily occurs in:", listOf("Capacitor", "Resistor", "Inductor", "Transformer"), "Inductor"),
            q(46, "Buck-Boost Converter", "The buck-boost converter can provide:", listOf("Only lower output voltage", "Only higher output voltage", "Higher or lower output voltage magnitude", "Only AC voltage"), "Higher or lower output voltage magnitude"),
            q(47, "Device Protection", "A snubber circuit is mainly used to:", listOf("Increase output power", "Protect switching devices", "Increase frequency", "Reduce load resistance"), "Protect switching devices"),
            q(48, "Device Protection", "An RC snubber across an SCR helps limit:", listOf("di/dt", "dv/dt", "Power factor", "Frequency"), "dv/dt"),
            q(49, "Gate Drive Circuits", "Gate isolation in power electronic converters is commonly achieved using:", listOf("Transformers or optocouplers", "Resistors only", "Capacitors only", "Inductors only"), "Transformers or optocouplers"),
            q(50, "Thermal Management", "Heat sinks are used in power electronic devices to:", listOf("Increase switching speed", "Reduce device temperature", "Increase gate current", "Reduce output voltage"), "Reduce device temperature"),
            q(51, "Voltage Source Inverter (VSI)", "A Voltage Source Inverter is supplied from a:", listOf("Constant current source", "Constant voltage source", "Variable frequency source", "AC source"), "Constant voltage source"),
            q(52, "Voltage Source Inverter (VSI)", "The DC-link element in a VSI is predominantly:", listOf("Inductor", "Capacitor", "Resistor", "Transformer"), "Capacitor"),
            q(53, "Current Source Inverter (CSI)", "A Current Source Inverter is supplied from a:", listOf("Constant current source", "Constant voltage source", "Variable voltage source", "AC source"), "Constant current source"),
            q(54, "Current Source Inverter (CSI)", "The DC-link element in a CSI is predominantly:", listOf("Capacitor", "Inductor", "Resistor", "Diode"), "Inductor"),
            q(55, "Single Phase Inverter", "A single-phase half-bridge inverter requires:", listOf("1 switch", "2 switches", "4 switches", "6 switches"), "2 switches"),
            q(56, "Single Phase Inverter", "A single-phase full-bridge inverter requires:", listOf("2 switches", "3 switches", "4 switches", "6 switches"), "4 switches"),
            q(57, "Single Phase Inverter", "The output waveform of a square-wave inverter is rich in:", listOf("DC component", "Harmonics", "Noise only", "Reactive power only"), "Harmonics"),
            q(58, "Single Phase Inverter", "For a square-wave inverter, the fundamental frequency is determined by:", listOf("Input voltage", "Switching sequence", "Load resistance", "Heat sink size"), "Switching sequence"),
            q(59, "Three Phase Inverter", "A three-phase bridge inverter uses:", listOf("3 switches", "4 switches", "6 switches", "12 switches"), "6 switches"),
            q(60, "Three Phase Inverter", "In a six-step inverter, each switch conducts for:", listOf("60°", "90°", "120°", "180°"), "180°"),
            q(61, "Three Phase Inverter", "The phase displacement between output voltages of a three-phase inverter is:", listOf("60°", "90°", "120°", "180°"), "120°"),
            q(62, "PWM Techniques", "Pulse Width Modulation is mainly used to:", listOf("Reduce switching losses completely", "Control output voltage and reduce harmonics", "Increase DC voltage", "Eliminate switching devices"), "Control output voltage and reduce harmonics"),
            q(63, "PWM Techniques", "In PWM control, output voltage is controlled by varying:", listOf("Input current", "Pulse width", "Load resistance", "Transformer ratio"), "Pulse width"),
            q(64, "SPWM", "SPWM stands for:", listOf("Series Pulse Width Modulation", "Sinusoidal Pulse Width Modulation", "Selective Pulse Width Modulation", "Switching Pulse Width Modulation"), "Sinusoidal Pulse Width Modulation"),
            q(65, "SPWM", "In SPWM, the reference signal is generally:", listOf("Square wave", "Sawtooth wave", "Sinusoidal wave", "DC signal"), "Sinusoidal wave"),
            q(66, "SPWM", "The carrier signal in SPWM is usually:", listOf("Sinusoidal", "Triangular", "DC", "Rectangular"), "Triangular"),
            q(67, "SPWM", "The modulation index in SPWM is defined as:", listOf("Carrier amplitude / Reference amplitude", "Reference amplitude / Carrier amplitude", "Carrier frequency / Reference frequency", "Reference frequency / Carrier frequency"), "Reference amplitude / Carrier amplitude"),
            q(68, "SPWM", "Increasing modulation index generally increases:", listOf("Fundamental output voltage", "Switch resistance", "Heat sink temperature only", "DC-link current only"), "Fundamental output voltage"),
            q(69, "SVPWM", "SVPWM stands for:", listOf("Space Vector Pulse Width Modulation", "Switch Vector Pulse Width Modulation", "Sinusoidal Vector Pulse Width Modulation", "Space Voltage Pulse Width Modulation"), "Space Vector Pulse Width Modulation"),
            q(70, "SVPWM", "Compared to SPWM, SVPWM provides:", listOf("Lower DC bus utilization", "Higher DC bus utilization", "No voltage control", "Lower efficiency always"), "Higher DC bus utilization"),
            q(71, "SVPWM", "The space vector representation is mainly applicable to:", listOf("Single-phase converters", "Three-phase converters", "Rectifiers only", "DC choppers"), "Three-phase converters"),
            q(72, "Inverter Harmonics", "The dominant harmonics in a square-wave inverter output are generally:", listOf("Even harmonics", "Triplen harmonics only", "Odd harmonics", "No harmonics"), "Odd harmonics"),
            q(73, "Inverter Harmonics", "PWM techniques shift harmonic energy toward:", listOf("Lower frequencies", "DC component", "Higher frequencies", "Zero frequency"), "Higher frequencies"),
            q(74, "Dead Time", "Dead time is introduced in inverter switching to prevent:", listOf("Overmodulation", "Shoot-through", "Harmonics", "Power factor reduction"), "Shoot-through"),
            q(75, "Dead Time", "Shoot-through in a VSI occurs when:", listOf("Both switches in the same leg conduct simultaneously", "All switches are OFF", "Load current becomes zero", "Input voltage is removed"), "Both switches in the same leg conduct simultaneously"),
            q(76, "AC Voltage Controllers", "An AC voltage controller is primarily used to control:", listOf("Frequency", "RMS output voltage", "Power factor only", "DC voltage"), "RMS output voltage"),
            q(77, "AC Voltage Controllers", "The most common devices used in single-phase AC voltage controllers are:", listOf("IGBTs only", "MOSFETs only", "SCRs or TRIACs", "BJTs only"), "SCRs or TRIACs"),
            q(78, "AC Voltage Controllers", "Phase-angle control in AC voltage controllers varies:", listOf("Supply frequency", "Firing angle", "Load resistance", "Input voltage magnitude"), "Firing angle"),
            q(79, "AC Voltage Controllers", "Integral cycle control is mainly suitable for:", listOf("Motor drives", "Heating loads", "UPS systems", "Battery chargers"), "Heating loads"),
            q(80, "AC Voltage Controllers", "Increasing the firing angle in an AC voltage controller generally:", listOf("Increases output voltage", "Decreases output voltage", "Does not affect output voltage", "Doubles frequency"), "Decreases output voltage"),
            q(81, "Cycloconverters", "A cycloconverter converts:", listOf("DC to AC", "AC to DC", "AC to AC at different frequency", "DC to DC"), "AC to AC at different frequency"),
            q(82, "Cycloconverters", "Cycloconverters are mainly used in:", listOf("High-speed drives", "Low-speed high-power drives", "Lighting systems", "Battery chargers"), "Low-speed high-power drives"),
            q(83, "Cycloconverters", "A cycloconverter directly converts:", listOf("AC to AC without DC link", "AC to DC", "DC to AC", "DC to DC"), "AC to AC without DC link"),
            q(84, "Cycloconverters", "The output frequency of a cycloconverter is usually:", listOf("Higher than input frequency", "Equal to input frequency", "Lower than input frequency", "Zero"), "Lower than input frequency"),
            q(85, "Cycloconverters", "Cycloconverters generally employ:", listOf("SCRs", "BJTs", "JFETs", "Vacuum tubes"), "SCRs"),
            q(86, "Resonant Converters", "Resonant converters are mainly used to achieve:", listOf("Hard switching", "Soft switching", "Mechanical switching", "Manual switching"), "Soft switching"),
            q(87, "Resonant Converters", "Zero Voltage Switching (ZVS) means switching occurs at:", listOf("Zero current", "Zero power", "Zero voltage", "Maximum current"), "Zero voltage"),
            q(88, "Resonant Converters", "Zero Current Switching (ZCS) means switching occurs at:", listOf("Zero voltage", "Zero current", "Maximum voltage", "Rated voltage"), "Zero current"),
            q(89, "Resonant Converters", "The resonant tank circuit consists primarily of:", listOf("R and C", "R and L", "L and C", "R only"), "L and C"),
            q(90, "SMPS", "SMPS stands for:", listOf("Switched Mode Power Supply", "Single Mode Power Supply", "Switched Motor Power System", "Static Mode Power Supply"), "Switched Mode Power Supply"),
            q(91, "SMPS", "The major advantage of an SMPS is:", listOf("Large size", "Low efficiency", "High efficiency", "Low frequency operation"), "High efficiency"),
            q(92, "SMPS", "A flyback converter is commonly used in:", listOf("Low-power isolated supplies", "Transmission systems", "HVDC stations", "Synchronous generators"), "Low-power isolated supplies"),
            q(93, "SMPS", "A forward converter transfers energy to load:", listOf("Only when switch is OFF", "Only when switch is ON", "Never", "Only during resonance"), "Only when switch is ON"),
            q(94, "Power Quality", "A major cause of harmonics in power systems is:", listOf("Linear loads", "Nonlinear loads", "Transmission towers", "Capacitor banks only"), "Nonlinear loads"),
            q(95, "Power Quality", "Total Harmonic Distortion (THD) is a measure of:", listOf("Voltage regulation", "Power factor", "Waveform distortion", "Frequency deviation"), "Waveform distortion"),
            q(96, "Power Quality", "PWM techniques help reduce:", listOf("Fundamental component", "Harmonic distortion", "DC-link voltage", "Load current"), "Harmonic distortion"),
            q(97, "Power Factor Correction", "Power factor correction circuits are used to:", listOf("Reduce efficiency", "Improve input power factor", "Increase harmonics", "Reduce output voltage"), "Improve input power factor"),
            q(98, "Power Factor Correction", "An ideal power factor is:", listOf("0", "0.5", "1", "2"), "1"),
            q(99, "Converter Performance", "Converter efficiency is defined as:", listOf("Input power / Output power", "Output power / Input power", "Losses / Input power", "Output voltage / Input voltage"), "Output power / Input power"),
            q(100, "Converter Performance", "An ideal power electronic converter has efficiency of:", listOf("0%", "50%", "80%", "100%"), "100%"),
            q(101, "Advanced Rectifiers", "A fully controlled bridge rectifier uses:", listOf("4 diodes", "2 SCRs and 2 diodes", "4 SCRs", "1 SCR"), "4 SCRs"),
            q(102, "Advanced Rectifiers", "For a single-phase full converter, inversion operation is possible when firing angle α is:", listOf("< 90°", "= 90°", "> 90°", "= 0°"), "> 90°"),
            q(103, "Advanced Rectifiers", "The displacement factor of a controlled rectifier decreases with:", listOf("Decrease in firing angle", "Increase in firing angle", "Increase in load resistance", "Decrease in source voltage"), "Increase in firing angle"),
            q(104, "Advanced Rectifiers", "Source inductance in a converter causes:", listOf("Commutation overlap", "Reduction in frequency", "Increase in load resistance", "Elimination of harmonics"), "Commutation overlap"),
            q(105, "Advanced Rectifiers", "The overlap angle in a converter increases with:", listOf("Decrease in current", "Increase in source inductance", "Decrease in voltage", "Decrease in firing angle"), "Increase in source inductance"),
            q(106, "HVDC Converters", "Line Commutated Converters (LCC) primarily use:", listOf("MOSFETs", "IGBTs", "SCRs", "BJTs"), "SCRs"),
            q(107, "HVDC Converters", "LCC-HVDC requires:", listOf("Strong AC system", "Battery bank", "DC generator", "Mechanical commutation"), "Strong AC system"),
            q(108, "HVDC Converters", "VSC-HVDC commonly employs:", listOf("SCRs", "IGBTs", "TRIACs", "DIACs"), "IGBTs"),
            q(109, "HVDC Converters", "A major advantage of VSC-HVDC is:", listOf("Requires strong grid always", "Independent reactive power control", "No switching losses", "No DC link"), "Independent reactive power control"),
            q(110, "HVDC Converters", "The extinction angle in HVDC converters is associated with:", listOf("SCR turn-off margin", "Gate current", "Load resistance", "Switching frequency"), "SCR turn-off margin"),
            q(111, "FACTS Converters", "STATCOM is based on:", listOf("Voltage source converter", "Current source converter", "Cycloconverter", "Rectifier only"), "Voltage source converter"),
            q(112, "FACTS Converters", "SSSC stands for:", listOf("Static Series Synchronous Compensator", "Static Shunt Synchronous Controller", "Series Switching System Controller", "Static Source Synchronous Converter"), "Static Series Synchronous Compensator"),
            q(113, "FACTS Converters", "UPFC combines the functionality of:", listOf("STATCOM and SSSC", "Rectifier and Chopper", "Cycloconverter and Inverter", "SCR and TRIAC"), "STATCOM and SSSC"),
            q(114, "FACTS Converters", "FACTS controllers are mainly used for:", listOf("Transmission system control", "Motor manufacturing", "Battery charging only", "Generator design"), "Transmission system control"),
            q(115, "FACTS Converters", "STATCOM provides:", listOf("Real power only", "Reactive power support", "Mechanical power", "DC generation"), "Reactive power support"),
            q(116, "Active Front End Converters", "An Active Front End (AFE) converter enables:", listOf("Bidirectional power flow", "Only rectification", "Only inversion", "Mechanical switching"), "Bidirectional power flow"),
            q(117, "Active Front End Converters", "AFE converters are widely used in:", listOf("Modern variable speed drives", "Incandescent lamps", "Transformers only", "Relay circuits"), "Modern variable speed drives"),
            q(118, "Active Front End Converters", "The input current waveform of an AFE is designed to be:", listOf("Highly distorted", "Sinusoidal", "Triangular", "Square"), "Sinusoidal"),
            q(119, "Multilevel Inverters", "A major advantage of multilevel inverters is:", listOf("Higher harmonic distortion", "Reduced harmonic content", "Lower voltage capability", "No switching devices"), "Reduced harmonic content"),
            q(120, "Multilevel Inverters", "A diode-clamped inverter is also called:", listOf("NPC inverter", "CSI inverter", "Buck inverter", "Flyback inverter"), "NPC inverter"),
            q(121, "Multilevel Inverters", "A cascaded H-bridge inverter requires:", listOf("Single DC source only", "Multiple isolated DC sources", "No DC source", "Only AC input"), "Multiple isolated DC sources"),
            q(122, "Multilevel Inverters", "Increasing the number of voltage levels generally:", listOf("Increases THD", "Reduces THD", "Eliminates output voltage", "Reduces efficiency to zero"), "Reduces THD"),
            q(123, "Converter Analysis", "For an ideal buck converter operating in CCM with D = 0.6 and Vin = 100 V, the output voltage is:", listOf("40 V", "60 V", "100 V", "166.7 V"), "60 V"),
            q(124, "Converter Analysis", "For an ideal boost converter with D = 0.5 and Vin = 100 V, the output voltage is:", listOf("50 V", "100 V", "150 V", "200 V"), "200 V"),
            q(125, "Converter Analysis", "For an ideal buck-boost converter with D = 0.4 and Vin = 120 V, the magnitude of output voltage is:", listOf("40 V", "60 V", "80 V", "120 V"), "80 V"),
            q(126, "DC Drives", "The speed of a separately excited DC motor is primarily controlled by varying:", listOf("Armature voltage", "Rotor resistance", "Supply frequency", "Pole pitch"), "Armature voltage"),
            q(127, "DC Drives", "Armature voltage control provides speed control mainly:", listOf("Above base speed", "Below base speed", "At synchronous speed only", "At zero speed only"), "Below base speed"),
            q(128, "DC Drives", "Field weakening control in DC motors is used for operation:", listOf("Below base speed", "Above base speed", "At standstill", "At zero torque"), "Above base speed"),
            q(129, "DC Drives", "A dual converter drive enables:", listOf("Single quadrant operation", "Two-quadrant operation", "Four-quadrant operation", "Zero quadrant operation"), "Four-quadrant operation"),
            q(130, "DC Drives", "Regenerative braking occurs when power flows:", listOf("From source to motor", "From motor to source", "From source to load only", "From motor to ground"), "From motor to source"),
            q(131, "Induction Motor Drives", "The most common method of induction motor speed control is:", listOf("Rotor resistance control", "Pole changing", "V/f control", "Armature voltage control"), "V/f control"),
            q(132, "Induction Motor Drives", "Constant V/f control maintains:", listOf("Rotor resistance", "Air-gap flux approximately constant", "Slip equal to one", "Power factor equal to one"), "Air-gap flux approximately constant"),
            q(133, "Induction Motor Drives", "A Variable Frequency Drive (VFD) mainly controls:", listOf("Input current only", "Voltage and frequency", "Rotor resistance only", "Load torque only"), "Voltage and frequency"),
            q(134, "Induction Motor Drives", "PWM in induction motor drives is primarily used to:", listOf("Control inverter output voltage", "Increase line frequency", "Reduce motor poles", "Eliminate slip"), "Control inverter output voltage"),
            q(135, "Induction Motor Drives", "Vector control of induction motors provides independent control of:", listOf("Voltage and frequency", "Flux and torque", "Speed and poles", "Current and resistance"), "Flux and torque"),
            q(136, "Synchronous Motor Drives", "Permanent Magnet Synchronous Motors (PMSM) are widely used because of:", listOf("Low efficiency", "High power density", "High slip", "Rotor copper losses"), "High power density"),
            q(137, "Synchronous Motor Drives", "Electronic commutation is essential in:", listOf("PMSM drives", "Transformers", "Transmission lines", "Circuit breakers"), "PMSM drives"),
            q(138, "BLDC Drives", "BLDC stands for:", listOf("Brushless DC Motor", "Balanced DC Motor", "Bipolar DC Machine", "Boost DC Motor"), "Brushless DC Motor"),
            q(139, "BLDC Drives", "Rotor position information in BLDC drives is commonly obtained using:", listOf("Hall sensors", "PTs", "CTs", "SCRs"), "Hall sensors"),
            q(140, "Electric Vehicle Drives", "The traction inverter in an electric vehicle converts:", listOf("AC to DC", "DC to AC", "AC to AC", "DC to DC only"), "DC to AC"),
            q(141, "Electric Vehicle Drives", "Regenerative braking in EVs improves:", listOf("Battery charging and efficiency", "Vehicle weight", "Motor losses", "Tire wear"), "Battery charging and efficiency"),
            q(142, "Battery Chargers", "A battery charger is essentially a:", listOf("Controlled power converter", "Transformer only", "Inductor only", "Capacitor only"), "Controlled power converter"),
            q(143, "Battery Chargers", "Constant-current charging is mainly used to:", listOf("Control charging current", "Control output frequency", "Reduce battery voltage", "Increase harmonics"), "Control charging current"),
            q(144, "Battery Chargers", "Fast chargers generally require:", listOf("Higher charging power", "Lower charging power", "No converter", "No control system"), "Higher charging power"),
            q(145, "Power Electronic Drives", "A closed-loop drive system uses feedback primarily for:", listOf("Improved regulation", "Increasing losses", "Reducing efficiency", "Eliminating sensors"), "Improved regulation"),
            q(146, "Power Electronic Drives", "Current control in motor drives helps in:", listOf("Torque regulation", "Changing pole number", "Reducing voltage only", "Changing rotor diameter"), "Torque regulation"),
            q(147, "Converter Analysis", "An ideal buck converter with Vin = 200 V and D = 0.25 produces an output voltage of:", listOf("25 V", "50 V", "75 V", "100 V"), "50 V"),
            q(148, "Converter Analysis", "For an ideal boost converter with Vin = 50 V and D = 0.6, the output voltage is:", listOf("80 V", "100 V", "125 V", "150 V"), "125 V"),
            q(149, "Converter Analysis", "For an ideal buck-boost converter with Vin = 100 V and D = 0.5, the magnitude of output voltage is:", listOf("50 V", "100 V", "150 V", "200 V"), "100 V"),
            q(150, "Converter Analysis", "A converter delivering 900 W output while drawing 1000 W input has an efficiency of:", listOf("80%", "85%", "90%", "95%"), "90%"),
            q(151, "Advanced PWM", "Third harmonic injection PWM is used to:", listOf("Reduce switching frequency", "Increase DC bus utilization", "Reduce DC voltage", "Eliminate inverter switches"), "Increase DC bus utilization"),
            q(152, "Advanced PWM", "Selective Harmonic Elimination (SHE) PWM is mainly used to:", listOf("Increase switching losses", "Eliminate specific harmonics", "Reduce output voltage", "Increase load current"), "Eliminate specific harmonics"),
            q(153, "Advanced PWM", "Overmodulation in SPWM occurs when modulation index is:", listOf("Zero", "Less than 1", "Greater than 1", "Equal to frequency ratio"), "Greater than 1"),
            q(154, "Advanced PWM", "Carrier frequency in PWM primarily affects:", listOf("Output DC level", "Harmonic spectrum", "Input voltage", "Load resistance"), "Harmonic spectrum"),
            q(155, "Advanced PWM", "A higher switching frequency generally results in:", listOf("Lower switching losses", "Higher switching losses", "No losses", "Zero harmonics"), "Higher switching losses"),
            q(156, "Matrix Converters", "A matrix converter directly converts:", listOf("DC to AC", "AC to DC", "AC to AC without DC link", "DC to DC"), "AC to AC without DC link"),
            q(157, "Matrix Converters", "A three-phase matrix converter typically uses:", listOf("6 bidirectional switches", "9 bidirectional switches", "12 SCRs", "18 diodes"), "9 bidirectional switches"),
            q(158, "Matrix Converters", "A key advantage of matrix converters is:", listOf("No bulky DC-link capacitor", "Zero switching devices", "Mechanical commutation", "Only fixed frequency output"), "No bulky DC-link capacitor"),
            q(159, "Multilevel Inverters", "The commonly used multilevel inverter topologies include:", listOf("NPC, Flying Capacitor, Cascaded H-Bridge", "Buck, Boost, Buck-Boost", "SCR, TRIAC, DIAC", "VSI, CSI, Cycloconverter"), "NPC, Flying Capacitor, Cascaded H-Bridge"),
            q(160, "Multilevel Inverters", "Flying capacitor multilevel inverters use:", listOf("Clamping diodes only", "Floating capacitors", "Transformers", "Current sources"), "Floating capacitors"),
            q(161, "Multilevel Inverters", "Increasing the number of levels in a multilevel inverter generally:", listOf("Increases output distortion", "Reduces output distortion", "Eliminates switching", "Reduces voltage capability"), "Reduces output distortion"),
            q(162, "Wide Bandgap Devices", "SiC stands for:", listOf("Silicon Carbon", "Silicon Carbide", "Silicon Ceramic", "Silicon Compound"), "Silicon Carbide"),
            q(163, "Wide Bandgap Devices", "GaN stands for:", listOf("Gallium Nitride", "Germanium Nitride", "Gallium Nickel", "Germanium Nickel"), "Gallium Nitride"),
            q(164, "Wide Bandgap Devices", "Compared to silicon devices, SiC devices offer:", listOf("Lower temperature capability", "Higher switching speed", "Higher losses", "Lower breakdown voltage"), "Higher switching speed"),
            q(165, "Wide Bandgap Devices", "A major advantage of GaN devices is:", listOf("High-frequency operation", "Mechanical switching", "Low breakdown strength", "Large gate current"), "High-frequency operation"),
            q(166, "EMI and EMC", "EMI stands for:", listOf("Electromagnetic Interference", "Electrical Motor Interface", "Electronic Machine Integration", "Electromagnetic Insulation"), "Electromagnetic Interference"),
            q(167, "EMI and EMC", "EMC stands for:", listOf("Electronic Motor Control", "Electromagnetic Compatibility", "Electrical Machine Converter", "Electromagnetic Coupling"), "Electromagnetic Compatibility"),
            q(168, "EMI and EMC", "High dv/dt switching commonly causes:", listOf("EMI issues", "Reduced efficiency only", "Lower switching speed", "Reduced voltage stress"), "EMI issues"),
            q(169, "EMI and EMC", "EMI filters are primarily used to:", listOf("Increase harmonics", "Suppress conducted noise", "Increase current ripple", "Reduce output power"), "Suppress conducted noise"),
            q(170, "Thermal Design", "Thermal resistance is analogous to:", listOf("Electrical resistance", "Electrical capacitance", "Electrical inductance", "Electrical frequency"), "Electrical resistance"),
            q(171, "Thermal Design", "Junction temperature of a device should generally be:", listOf("Above rated limit", "Within specified limit", "Equal to ambient temperature", "Independent of losses"), "Within specified limit"),
            q(172, "Protection Circuits", "A crowbar circuit is mainly used for:", listOf("Overvoltage protection", "Frequency control", "Power factor correction", "Speed control"), "Overvoltage protection"),
            q(173, "Protection Circuits", "Fast-acting semiconductor fuses are used to protect against:", listOf("Overcurrent", "Undervoltage", "Overfrequency", "Harmonics"), "Overcurrent"),
            q(174, "Converter Analysis", "If the modulation index of an SPWM inverter is doubled (within linear range), the fundamental output voltage approximately:", listOf("Halves", "Remains constant", "Doubles", "Becomes zero"), "Doubles"),
            q(175, "Converter Analysis", "For a VSI, dead-time is introduced mainly to avoid:", listOf("Harmonics", "Shoot-through fault", "Voltage ripple", "Current ripple"), "Shoot-through fault"),
            q(176, "Resonant Converters", "The primary objective of resonant converters is to achieve:", listOf("Hard switching", "Soft switching", "Mechanical commutation", "Line commutation"), "Soft switching"),
            q(177, "Resonant Converters", "An LLC converter is a type of:", listOf("Resonant converter", "Cycloconverter", "AC voltage controller", "Matrix converter"), "Resonant converter"),
            q(178, "Soft Switching", "Soft switching reduces:", listOf("Switching losses", "Output voltage", "Input frequency", "Load current"), "Switching losses"),
            q(179, "Soft Switching", "Zero Voltage Switching (ZVS) is particularly beneficial for:", listOf("High-frequency converters", "Transformers", "Circuit breakers", "Transmission lines"), "High-frequency converters"),
            q(180, "Soft Switching", "Zero Current Switching (ZCS) minimizes switching stress during:", listOf("Current commutation", "Voltage regulation", "Power factor correction", "Rectification"), "Current commutation"),
            q(181, "Solar PV Converters", "The output of a photovoltaic panel is:", listOf("AC", "DC", "Pulsating AC", "Variable frequency AC"), "DC"),
            q(182, "Solar PV Converters", "A grid-connected PV system requires:", listOf("Rectifier", "Inverter", "Cycloconverter", "AC voltage controller"), "Inverter"),
            q(183, "MPPT", "MPPT stands for:", listOf("Maximum Power Point Tracking", "Minimum Power Point Tracking", "Maximum Phase Power Transfer", "Motor Power Point Tracking"), "Maximum Power Point Tracking"),
            q(184, "MPPT", "The purpose of MPPT in PV systems is to:", listOf("Reduce panel voltage", "Extract maximum available power", "Increase harmonics", "Reduce sunlight"), "Extract maximum available power"),
            q(185, "MPPT", "Perturb and Observe (P&O) is a commonly used:", listOf("PWM method", "MPPT algorithm", "Rectifier topology", "Motor control technique"), "MPPT algorithm"),
            q(186, "Grid Connected Inverters", "A grid-connected inverter must synchronize with the grid:", listOf("Voltage only", "Frequency only", "Voltage, frequency and phase", "Current only"), "Voltage, frequency and phase"),
            q(187, "Grid Connected Inverters", "A Phase Locked Loop (PLL) is commonly used for:", listOf("Thermal protection", "Grid synchronization", "Current limiting", "Rectification"), "Grid synchronization"),
            q(188, "Grid Connected Inverters", "Anti-islanding protection is required in:", listOf("Standalone generators", "Grid-connected inverters", "Transformers", "AC voltage controllers"), "Grid-connected inverters"),
            q(189, "Microgrids", "A microgrid may operate in:", listOf("Grid-connected mode only", "Islanded mode only", "Both grid-connected and islanded modes", "DC mode only"), "Both grid-connected and islanded modes"),
            q(190, "Microgrids", "Energy storage systems in microgrids are commonly based on:", listOf("Batteries", "Transformers", "Circuit breakers", "Insulators"), "Batteries"),
            q(191, "Microgrids", "Bidirectional converters are important in microgrids because they:", listOf("Allow two-way power flow", "Reduce voltage to zero", "Remove batteries", "Eliminate harmonics completely"), "Allow two-way power flow"),
            q(192, "Converter Analysis", "An ideal boost converter with Vin = 120 V and D = 0.25 has output voltage:", listOf("120 V", "140 V", "160 V", "180 V"), "160 V"),
            q(193, "Converter Analysis", "An ideal buck converter with Vin = 240 V and D = 0.75 produces:", listOf("60 V", "120 V", "180 V", "240 V"), "180 V"),
            q(194, "Converter Analysis", "For a converter with output power 950 W and losses 50 W, efficiency is:", listOf("90%", "95%", "97.5%", "98%"), "95%"),
            q(195, "Power Quality", "Active power filters are used primarily to:", listOf("Increase harmonics", "Mitigate harmonics", "Reduce switching frequency", "Increase losses"), "Mitigate harmonics"),
            q(196, "Power Quality", "A shunt active filter mainly compensates:", listOf("Current harmonics", "Voltage harmonics only", "Frequency deviation", "Rotor losses"), "Current harmonics"),
            q(197, "Wide Bandgap Devices", "SiC MOSFETs are preferred in high-voltage applications because of their:", listOf("Low breakdown voltage", "High breakdown strength", "Mechanical commutation", "Large gate current"), "High breakdown strength"),
            q(198, "Wide Bandgap Devices", "GaN devices are especially suitable for:", listOf("Very high-frequency converters", "Mechanical relays", "Power transformers", "Circuit breakers"), "Very high-frequency converters"),
            q(199, "Emerging Topics", "Solid-State Transformers (SSTs) are based on:", listOf("High-frequency power electronic converters", "Mechanical transformers only", "Rotating machines", "Cycloconverters only"), "High-frequency power electronic converters"),
            q(200, "Emerging Topics", "The increasing adoption of renewable energy systems has significantly increased the importance of:", listOf("Power electronic converters", "Vacuum tubes", "Mechanical commutators", "Electromechanical relays"), "Power electronic converters")
        )
    }

    private fun q(
        id: Int,
        subdomain: String,
        question: String,
        options: List<String>,
        answer: String
    ): GateQuestion {
        val topicId = when (subdomain) {
            "Power Diodes", "SCR", "TRIAC", "DIAC", "GTO", "MOSFET", "IGBT", "Device Characteristics",
            "Triggering Circuits", "SCR Commutation", "Device Protection", "Gate Drive Circuits",
            "Thermal Management", "Thermal Design", "Protection Circuits", "Wide Bandgap Devices", "EMI and EMC" -> "pe_devices"

            "Single Phase Controlled Rectifier", "Three Phase Controlled Rectifier", "Dual Converters",
            "Choppers", "Buck Converter", "Boost Converter", "Buck-Boost Converter", "Cuk Converter", "SEPIC Converter",
            "Resonant Converters", "SMPS", "Advanced Rectifiers", "Active Front End Converters", "Soft Switching", 
            "Solar PV Converters", "MPPT", "Converter Performance", "Converter Analysis" -> "pe_converters"

            "Voltage Source Inverter (VSI)", "Current Source Inverter (CSI)", "Single Phase Inverter", "Three Phase Inverter",
            "PWM Techniques", "SPWM", "SVPWM", "Inverter Harmonics", "Dead Time", "AC Voltage Controllers", "Cycloconverters",
            "DC Motor Drives", "DC Drives", "Induction Motor Drives", "Synchronous Motor Drives", "BLDC Drives",
            "Electric Vehicle Drives", "Battery Chargers", "Power Electronic Drives", "HVDC Converters", "FACTS Converters",
            "Multilevel Inverters", "Advanced PWM", "Matrix Converters", "Power Quality", "Harmonics", "Power Factor Correction",
            "Grid Connected Inverters", "Microgrids", "Emerging Topics" -> "pe_drives"

            else -> "pe_devices"
        }

        val subtopicId = when (topicId) {
            "pe_devices" -> "pe_device_char"
            "pe_converters" -> "pe_conv_buck_boost"
            "pe_drives" -> "pe_drives_char"
            else -> "pe_device_char"
        }

        val correctIdx = options.indexOf(answer).let { if (it == -1) 0 else it }

        val difficulty = when (id % 3) {
            0 -> "Easy"
            1 -> "Medium"
            else -> "Hard"
        }

        val formulas = when (topicId) {
            "pe_devices" -> "I_A > I_L (Latch), I_A < I_H (Hold)"
            "pe_converters" -> "V_o = D * V_in, V_o = V_in / (1 - D)"
            "pe_drives" -> "V_rms = sqrt(2/3) * V_dc"
            else -> ""
        }

        val explanationText = "Based on $subdomain principles. Correct matches: '$answer'. " +
            when (topicId) {
                "pe_devices" -> "Covers static turn-on / latching, holding limits, voltage triggering dynamics, thermal management, and modern wide-bandgap switching elements."
                "pe_converters" -> "Covers average computations for controllable rectifiers, switching regulator duty cycle configurations, buck/boost converters, and soft-switching resonant topologies."
                "pe_drives" -> "Covers Voltage Source Inverters (VSI 120/180 conduction configurations), sinusoidal and space vector PWM modulators, and variable speed electric motor drive control systems."
                else -> ""
            }

        return GateQuestion(
            id = "pe_q_$id",
            subjectId = "power_electronics",
            topicId = topicId,
            subtopicId = subtopicId,
            year = 2020 + (id % 7),
            questionText = question,
            questionType = QuestionType.MCQ,
            options = options,
            correctOptions = listOf(correctIdx),
            correctNumericalRange = null,
            explanation = explanationText,
            formulasUsed = formulas,
            shortcutTricks = "Analyze limits, physical topologies, and check the standard performance formulas.",
            relatedConcepts = "Power Electronics engineering, switching conversion, $subdomain",
            difficulty = difficulty
        )
    }
}
