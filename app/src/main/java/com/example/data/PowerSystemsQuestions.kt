package com.example.data

object PowerSystemsQuestions {

    val questions: List<GateQuestion> by lazy {
        listOf(
            q(1, "Per Unit System", "The primary advantage of the per-unit system is:", listOf("Reduction of frequency", "Simplification of calculations", "Increase in power factor", "Reduction in losses"), "Simplification of calculations"),
            q(2, "Per Unit System", "In a per-unit system, impedance base is equal to:", listOf("Vbase/Ibase", "Vbase×Ibase", "Vbase²/Sbase", "Sbase²/Vbase"), "Vbase²/Sbase"),
            q(3, "Power System Components", "A synchronous generator is primarily used for:", listOf("Power generation", "Voltage regulation", "Power factor correction", "Load shedding"), "Power generation"),
            q(4, "Power System Components", "The rotor of a turbo-generator is generally:", listOf("Salient pole", "Cylindrical", "Split phase", "Squirrel cage"), "Cylindrical"),
            q(5, "Transmission Lines", "The inductance of a transmission line depends primarily on:", listOf("Conductor spacing", "Voltage level", "Frequency only", "Power factor"), "Conductor spacing"),
            q(6, "Transmission Lines", "Transposition of conductors is done to:", listOf("Reduce resistance", "Reduce line losses", "Balance inductance and capacitance", "Increase current rating"), "Balance inductance and capacitance"),
            q(7, "Transmission Lines", "The skin effect increases with:", listOf("Decrease in frequency", "Increase in frequency", "Decrease in conductor diameter", "Decrease in current"), "Increase in frequency"),
            q(8, "Transmission Lines", "The proximity effect is caused due to:", listOf("Earth resistance", "Magnetic field of nearby conductors", "Corona loss", "Dielectric loss"), "Magnetic field of nearby conductors"),
            q(9, "Transmission Line Performance", "The Ferranti effect occurs in:", listOf("Short lines", "Loaded lines", "Lightly loaded long lines", "DC lines"), "Lightly loaded long lines"),
            q(10, "Transmission Line Performance", "Surge impedance loading is defined for:", listOf("Underground cables", "Transmission lines", "Transformers", "Generators"), "Transmission lines"),
            q(11, "Corona", "Corona loss increases with:", listOf("Increase in conductor diameter", "Decrease in voltage", "Increase in operating voltage", "Decrease in frequency"), "Increase in operating voltage"),
            q(12, "Corona", "Corona produces:", listOf("Audible noise", "Reduced insulation", "Ozone formation", "All of the above"), "All of the above"),
            q(13, "Insulators", "String efficiency is always:", listOf("Greater than 100%", "Equal to 100%", "Less than or equal to 100%", "Zero"), "Less than or equal to 100%"),
            q(14, "Insulators", "The most commonly used insulator in transmission lines is:", listOf("Pin type", "Suspension type", "Strain type", "Shackle type"), "Suspension type"),
            q(15, "Underground Cables", "The insulation resistance of a cable is inversely proportional to:", listOf("Length", "Diameter", "Voltage", "Frequency"), "Length"),
            q(16, "Underground Cables", "Capacitance of underground cables is generally:", listOf("Negligible", "High", "Zero", "Negative"), "High"),
            q(17, "Distribution Systems", "A radial distribution system is:", listOf("Most reliable", "Least expensive", "Most complex", "Always meshed"), "Least expensive"),
            q(18, "Distribution Systems", "Ring main distribution system offers:", listOf("Lower reliability", "Higher reliability", "No redundancy", "No voltage drop"), "Higher reliability"),
            q(19, "Network Analysis", "Ybus matrix is generally:", listOf("Dense", "Sparse", "Diagonal", "Singular"), "Sparse"),
            q(20, "Network Analysis", "The order of Ybus matrix equals:", listOf("Number of generators", "Number of lines", "Number of buses", "Number of loads"), "Number of buses"),
            q(21, "Load Flow", "A slack bus is specified by:", listOf("P and Q", "P and V", "Q and V", "V and delta"), "V and delta"),
            q(22, "Load Flow", "Newton-Raphson load flow converges:", listOf("Linearly", "Quadratically", "Logarithmically", "Randomly"), "Quadratically"),
            q(23, "Load Flow", "Gauss-Seidel method is:", listOf("Faster than NR", "Slower than NR", "Independent of initial values", "Not iterative"), "Slower than NR"),
            q(24, "Symmetrical Components", "The operator a is equal to:", listOf("1∠120°", "1∠240°", "1∠90°", "1∠180°"), "1∠120°"),
            q(25, "Symmetrical Components", "Positive sequence components represent:", listOf("Normal balanced operation", "Ground fault", "Negative phase sequence", "Zero sequence only"), "Normal balanced operation"),
            q(51, "Symmetrical Fault Analysis", "A balanced three-phase fault is analyzed using which sequence network?", listOf("Positive sequence only", "Negative sequence only", "Zero sequence only", "All sequence networks"), "Positive sequence only"),
            q(52, "Symmetrical Fault Analysis", "The fault level at a bus is inversely proportional to:", listOf("System voltage", "Source impedance", "Power factor", "Frequency"), "Source impedance"),
            q(53, "Unsymmetrical Fault Analysis", "An L-G fault involves:", listOf("One phase and ground", "Two phases", "Three phases", "Two phases and ground"), "One phase and ground"),
            q(54, "Unsymmetrical Fault Analysis", "For a single line-to-ground fault, the sequence networks are connected:", listOf("In parallel", "In series", "Open circuit", "Delta connection"), "In series"),
            q(55, "Unsymmetrical Fault Analysis", "A line-to-line fault does not involve:", listOf("Positive sequence", "Negative sequence", "Zero sequence", "Current flow"), "Zero sequence"),
            q(56, "Power System Stability", "Rotor angle stability is mainly concerned with:", listOf("Voltage magnitude", "Frequency only", "Synchronism among generators", "Load flow"), "Synchronism among generators"),
            q(57, "Power System Stability", "Steady-state stability limit is proportional to:", listOf("EV/X", "EVX", "X/EV", "E+V"), "EV/X"),
            q(58, "Power System Stability", "The equal area criterion is applicable to:", listOf("Multi-machine systems only", "Single machine infinite bus system", "Distribution systems", "DC systems"), "Single machine infinite bus system"),
            q(59, "Swing Equation", "The swing equation relates rotor angle with:", listOf("Voltage", "Power imbalance", "Current", "Frequency only"), "Power imbalance"),
            q(60, "Swing Equation", "The inertia constant H is expressed in:", listOf("MW", "MJ/MVA", "Ohms", "kV"), "MJ/MVA"),
            q(61, "Economic Operation", "Economic load dispatch aims to:", listOf("Maximize losses", "Minimize generation cost", "Increase voltage", "Reduce frequency"), "Minimize generation cost"),
            q(62, "Economic Operation", "At optimum dispatch, incremental costs of generators are:", listOf("Zero", "Different", "Equal", "Negative"), "Equal"),
            q(63, "Economic Operation", "Transmission losses are included using:", listOf("ABCD constants", "B-coefficients", "Ybus", "Zbus"), "B-coefficients"),
            q(64, "Unit Commitment", "Unit commitment determines:", listOf("Generator voltage", "Generator speed", "ON/OFF status of units", "Transformer taps"), "ON/OFF status of units"),
            q(65, "Unit Commitment", "Spinning reserve refers to:", listOf("Disconnected generators", "Available additional generation", "Reactive power reserve", "Battery storage"), "Available additional generation"),
            q(66, "Hydrothermal Scheduling", "Hydrothermal scheduling aims at:", listOf("Water conservation and cost optimization", "Voltage control", "Frequency measurement", "Fault analysis"), "Water conservation and cost optimization"),
            q(67, "Protection", "The primary objective of protection is:", listOf("Increase losses", "Isolate faulty section", "Reduce voltage", "Improve power factor"), "Isolate faulty section"),
            q(68, "Protection", "A protection system should be:", listOf("Slow", "Selective and reliable", "Expensive", "Manual"), "Selective and reliable"),
            q(69, "Relays", "An overcurrent relay operates when:", listOf("Voltage exceeds limit", "Current exceeds preset value", "Frequency decreases", "Power factor increases"), "Current exceeds preset value"),
            q(70, "Relays", "Distance relays measure:", listOf("Resistance only", "Impedance to fault", "Current only", "Voltage only"), "Impedance to fault"),
            q(71, "Relays", "Mho relay is inherently suitable for:", listOf("Transformer protection", "Transmission line protection", "Motor starting", "Load shedding"), "Transmission line protection"),
            q(72, "Relays", "Differential protection is based on:", listOf("Current comparison", "Voltage comparison", "Frequency comparison", "Power factor comparison"), "Current comparison"),
            q(73, "Circuit Breakers", "The function of a circuit breaker is to:", listOf("Generate power", "Interrupt fault current", "Measure energy", "Control voltage"), "Interrupt fault current"),
            q(74, "Circuit Breakers", "Arc extinction in SF6 breakers occurs due to:", listOf("High dielectric strength", "Low pressure", "High resistance", "Low conductivity"), "High dielectric strength"),
            q(75, "Circuit Breakers", "Vacuum circuit breakers are commonly used for:", listOf("LV and MV systems", "Ultra HV only", "DC systems only", "Generation only"), "LV and MV systems"),
            q(76, "Travelling Waves", "Travelling waves are caused by:", listOf("Load flow", "Switching and lightning surges", "Power factor correction", "Economic dispatch"), "Switching and lightning surges"),
            q(77, "Travelling Waves", "Surge impedance of a line is:", listOf("√(L/C)", "L/C", "LC", "1/LC"), "√(L/C)"),
            q(78, "Travelling Waves", "Reflection coefficient depends on:", listOf("Frequency only", "Impedance mismatch", "Voltage only", "Current only"), "Impedance mismatch"),
            q(79, "HVDC Transmission", "HVDC transmission is preferred for:", listOf("Very short distances", "Long distance bulk power transfer", "Low voltage systems", "Distribution networks"), "Long distance bulk power transfer"),
            q(80, "HVDC Transmission", "A major advantage of HVDC is:", listOf("No converter stations", "Lower stability limit issues", "Higher reactive power generation", "Higher corona loss"), "Lower stability limit issues"),
            q(81, "FACTS", "FACTS devices are used mainly for:", listOf("Power flow control", "Power generation", "Energy metering", "Load forecasting"), "Power flow control"),
            q(82, "FACTS", "SVC stands for:", listOf("Static VAR Compensator", "Series Voltage Controller", "Synchronous Voltage Converter", "Static Voltage Chopper"), "Static VAR Compensator"),
            q(83, "FACTS", "STATCOM behaves as a:", listOf("Voltage source converter", "Current transformer", "Rectifier only", "Induction machine"), "Voltage source converter"),
            q(84, "Power Quality", "Voltage sag is a:", listOf("Temporary voltage reduction", "Frequency increase", "Current interruption", "Power factor improvement"), "Temporary voltage reduction"),
            q(85, "Power Quality", "Harmonics are generated mainly by:", listOf("Linear loads", "Nonlinear loads", "Transformers only", "Transmission lines"), "Nonlinear loads"),
            q(86, "Power Quality", "THD stands for:", listOf("Total Harmonic Distortion", "Total Heat Dissipation", "Transmission Harmonic Density", "Thermal Harmonic Deviation"), "Total Harmonic Distortion"),
            q(87, "Smart Grid", "A smart grid enables:", listOf("One-way communication", "Two-way communication", "No communication", "Manual operation only"), "Two-way communication"),
            q(88, "Smart Grid", "AMI stands for:", listOf("Advanced Metering Infrastructure", "Automatic Machine Interface", "Advanced Monitoring Instrument", "Automated Meter Integration"), "Advanced Metering Infrastructure"),
            q(89, "Renewable Energy Integration", "The major challenge of solar PV integration is:", listOf("High inertia", "Intermittency", "Excessive fuel cost", "Mechanical losses"), "Intermittency"),
            q(90, "Renewable Energy Integration", "Wind energy output depends mainly on:", listOf("Temperature only", "Wind speed", "Voltage level", "Power factor"), "Wind speed"),
            q(91, "SCADA", "SCADA stands for:", listOf("Supervisory Control and Data Acquisition", "System Control and Distribution Automation", "Smart Communication Data Access", "Supervisory Communication Device Architecture"), "Supervisory Control and Data Acquisition"),
            q(92, "SCADA", "SCADA is mainly used for:", listOf("Remote monitoring and control", "Power generation only", "Load dispatch only", "Transformer design"), "Remote monitoring and control"),
            q(93, "Energy Management System", "EMS is primarily used for:", listOf("System operation optimization", "Generator manufacturing", "Cable testing", "Relay design"), "System operation optimization"),
            q(94, "Load Frequency Control", "The objective of LFC is to maintain:", listOf("Frequency and tie-line power", "Voltage only", "Current only", "Power factor only"), "Frequency and tie-line power"),
            q(95, "Load Frequency Control", "Governor action is associated with:", listOf("Frequency regulation", "Voltage regulation", "Reactive power compensation", "Protection"), "Frequency regulation"),
            q(96, "Voltage Control", "Reactive power primarily affects:", listOf("Frequency", "Voltage magnitude", "Rotor speed", "Load angle"), "Voltage magnitude"),
            q(97, "Voltage Control", "Shunt capacitors are used to:", listOf("Absorb reactive power", "Supply reactive power", "Increase resistance", "Reduce frequency"), "Supply reactive power"),
            q(98, "Voltage Control", "Tap changing transformers are used for:", listOf("Voltage regulation", "Frequency control", "Protection", "Fault calculation"), "Voltage regulation"),
            q(99, "Power System Components", "A transformer operates on the principle of:", listOf("Electromagnetic induction", "Electrostatic induction", "Piezoelectric effect", "Hall effect"), "Electromagnetic induction"),
            q(100, "Power System Components", "The efficiency of a power transformer is maximum when:", listOf("Copper loss equals iron loss", "Copper loss is zero", "Iron loss is zero", "Load is zero"), "Copper loss equals iron loss"),
            q(101, "Transmission Lines", "The GMR of a conductor is primarily used in calculating:", listOf("Resistance", "Inductance", "Power factor", "Voltage regulation"), "Inductance"),
            q(102, "Transmission Lines", "Bundled conductors are mainly used to:", listOf("Increase resistance", "Reduce corona loss", "Increase inductance", "Reduce current capacity"), "Reduce corona loss"),
            q(103, "Transmission Line Performance", "Voltage regulation of a transmission line is generally expressed in:", listOf("Amperes", "Watts", "Percentage", "Ohms"), "Percentage"),
            q(104, "Transmission Line Performance", "The ABCD constants of a transmission line satisfy:", listOf("AD - BC = 1", "AD + BC = 1", "AB - CD = 1", "A + D = BC"), "AD - BC = 1"),
            q(105, "Corona", "Corona inception voltage depends on:", listOf("Conductor diameter", "Atmospheric conditions", "Spacing between conductors", "All of the above"), "All of the above"),
            q(106, "Insulators", "The voltage distribution across suspension insulator discs is:", listOf("Uniform", "Non-uniform", "Zero", "Sinusoidal"), "Non-uniform"),
            q(107, "Underground Cables", "The dielectric stress in a cable is maximum at:", listOf("Outer sheath", "Conductor surface", "Mid insulation", "Earth surface"), "Conductor surface"),
            q(108, "Distribution Systems", "The most economical distribution voltage for urban areas is generally:", listOf("11 kV", "33 kV", "132 kV", "400 kV"), "11 kV"),
            q(109, "Load Flow", "A PV bus is specified by:", listOf("P and V", "Q and V", "P and Q", "δ and Q"), "P and V"),
            q(110, "Load Flow", "Fast decoupled load flow is based on the assumption that:", listOf("P is strongly coupled with V", "Q is strongly coupled with δ", "P-δ and Q-V couplings dominate", "No coupling exists"), "P-δ and Q-V couplings dominate"),
            q(111, "Network Analysis", "Zbus matrix is obtained by:", listOf("Differentiating Ybus", "Inverting Ybus", "Multiplying Ybus", "Transposing Ybus"), "Inverting Ybus"),
            q(112, "Symmetrical Components", "For a balanced system, negative sequence components are:", listOf("Maximum", "Equal to positive sequence", "Zero", "Infinite"), "Zero"),
            q(113, "Fault Analysis", "The most severe fault in a power system is usually:", listOf("L-G fault", "L-L fault", "L-L-G fault", "Three-phase fault"), "Three-phase fault"),
            q(114, "Fault Analysis", "Fault current magnitude depends on:", listOf("Source impedance", "Fault type", "System voltage", "All of the above"), "All of the above"),
            q(115, "Power System Stability", "Transient stability concerns system behavior following:", listOf("Small disturbances", "Large disturbances", "Normal operation", "Economic dispatch"), "Large disturbances"),
            q(116, "Power System Stability", "Critical clearing angle is associated with:", listOf("Economic operation", "Voltage regulation", "Transient stability", "Load forecasting"), "Transient stability"),
            q(117, "Load Frequency Control", "The area control error (ACE) is used in:", listOf("AVR", "AGC", "Protection", "HVDC"), "AGC"),
            q(118, "Load Frequency Control", "The speed regulation parameter R of a governor is called:", listOf("Droop", "Gain", "Time constant", "Sensitivity"), "Droop"),
            q(119, "Economic Operation", "Penalty factor is used when:", listOf("Transmission losses are neglected", "Transmission losses are considered", "No generators exist", "Frequency varies"), "Transmission losses are considered"),
            q(120, "Economic Operation", "Lambda iteration method is used for:", listOf("Load flow", "Protection", "Economic dispatch", "Fault analysis"), "Economic dispatch"),
            q(121, "Protection", "Backup protection operates when:", listOf("Primary protection fails", "Voltage rises", "Load decreases", "Frequency increases"), "Primary protection fails"),
            q(122, "Relays", "The operating time of an IDMT relay decreases with:", listOf("Decrease in fault current", "Increase in fault current", "Decrease in voltage", "Increase in frequency"), "Increase in fault current"),
            q(123, "Relays", "Buchholz relay is used for protection of:", listOf("Transmission line", "Transformer", "Generator stator", "Busbar"), "Transformer"),
            q(124, "Relays", "Directional relays are used when:", listOf("Power can flow in both directions", "Only one source exists", "Voltage is constant", "No faults occur"), "Power can flow in both directions"),
            q(125, "Circuit Breakers", "Restriking voltage appears across breaker contacts after:", listOf("Current interruption", "Load increase", "Voltage drop", "Frequency rise"), "Current interruption"),
            q(126, "Circuit Breakers", "Current chopping is commonly associated with:", listOf("Oil circuit breaker", "Vacuum circuit breaker", "Fuse", "Alternator"), "Vacuum circuit breaker"),
            q(127, "Travelling Waves", "Lightning arresters are installed to:", listOf("Increase voltage", "Protect equipment from surges", "Reduce power factor", "Increase losses"), "Protect equipment from surges"),
            q(128, "Travelling Waves", "The velocity of travelling waves on a transmission line is approximately:", listOf("Speed of sound", "Speed of light", "100 m/s", "1000 m/s"), "Speed of light"),
            q(129, "HVDC Transmission", "A monopolar HVDC system uses:", listOf("One conductor", "Two conductors of opposite polarity", "Three conductors", "Four conductors"), "One conductor"),
            q(130, "HVDC Transmission", "The converter station of an HVDC system contains:", listOf("Transformers and converters", "Only generators", "Only capacitors", "Only relays"), "Transformers and converters"),
            q(131, "FACTS", "TCSC stands for:", listOf("Thyristor Controlled Series Capacitor", "Transformer Controlled Series Capacitor", "Transient Controlled System Capacitor", "Thyristor Controlled Shunt Capacitor"), "Thyristor Controlled Series Capacitor"),
            q(132, "FACTS", "UPFC can control:", listOf("Voltage", "Impedance", "Phase angle", "All of the above"), "All of the above"),
            q(133, "Power Quality", "Voltage swell refers to:", listOf("Temporary increase in RMS voltage", "Permanent voltage increase", "Frequency deviation", "Current interruption"), "Temporary increase in RMS voltage"),
            q(134, "Power Quality", "Flicker is mainly caused by:", listOf("Constant loads", "Rapid load fluctuations", "DC loads", "Transformers"), "Rapid load fluctuations"),
            q(135, "Smart Grid", "Demand response programs help in:", listOf("Load management", "Increasing losses", "Reducing generation", "Reducing protection"), "Load management"),
            q(136, "Smart Grid", "Smart meters provide:", listOf("Real-time consumption data", "Only monthly readings", "No communication", "Only voltage data"), "Real-time consumption data"),
            q(137, "Renewable Energy Integration", "Grid-connected solar PV systems require:", listOf("Rectifiers", "Inverters", "Alternators", "Relays only"), "Inverters"),
            q(138, "Renewable Energy Integration", "MPPT is used in:", listOf("Thermal plants", "Hydro plants", "Solar PV systems", "Nuclear plants"), "Solar PV systems"),
            q(139, "SCADA", "RTU stands for:", listOf("Remote Terminal Unit", "Relay Testing Unit", "Real Time Utility", "Remote Transfer Unit"), "Remote Terminal Unit"),
            q(140, "SCADA", "SCADA communication commonly uses:", listOf("Communication networks", "Mechanical links", "Hydraulic systems", "Manual operation"), "Communication networks"),
            q(141, "Energy Management", "State estimation is a function of:", listOf("EMS", "Transformer", "Circuit breaker", "Generator rotor"), "EMS"),
            q(142, "Energy Management", "Contingency analysis is used to evaluate:", listOf("System security", "Transformer efficiency", "Generator design", "Cable insulation"), "System security"),
            q(143, "Voltage Control", "An under-excited synchronous motor behaves as:", listOf("Capacitor", "Inductor", "Resistor", "Battery"), "Inductor"),
            q(144, "Voltage Control", "A synchronous condenser is used for:", listOf("Reactive power compensation", "Fault current limitation", "Economic dispatch", "Load forecasting"), "Reactive power compensation"),
            q(145, "Power System Components", "Generator excitation primarily controls:", listOf("Frequency", "Voltage and reactive power", "Real power only", "Rotor inertia"), "Voltage and reactive power"),
            q(146, "Power System Components", "A step-up transformer is used at generating stations to:", listOf("Reduce transmission losses", "Increase current", "Reduce frequency", "Increase harmonics"), "Reduce transmission losses"),
            q(147, "Per Unit System", "Per-unit quantities are independent of:", listOf("Chosen base values", "System ratings", "Units used", "Voltage level"), "Units used"),
            q(148, "Per Unit System", "The per-unit impedance of a transformer remains approximately:", listOf("Constant on either side", "Zero", "Infinite", "Frequency dependent only"), "Constant on either side"),
            q(149, "Transmission Lines", "Characteristic impedance is most important in:", listOf("Travelling wave analysis", "Economic dispatch", "Load forecasting", "Voltage control"), "Travelling wave analysis"),
            q(150, "Transmission Lines", "The nominal π model is commonly used for:", listOf("Short transmission lines", "Medium transmission lines", "Generators", "Distribution transformers"), "Medium transmission lines"),
            q(151, "Transmission Lines", "The nominal T model of a transmission line represents:", listOf("Distributed parameters exactly", "Medium transmission line approximation", "Short transmission line", "HVDC line"), "Medium transmission line approximation"),
            q(152, "Transmission Line Performance", "The receiving-end voltage becomes greater than sending-end voltage during Ferranti effect due to:", listOf("Line resistance", "Line charging current", "Load current", "Corona loss"), "Line charging current"),
            q(153, "Transmission Line Performance", "Surge impedance loading corresponds to the condition where:", listOf("Reactive power generated equals reactive power absorbed", "Voltage regulation is zero", "Current is zero", "Power factor is unity"), "Reactive power generated equals reactive power absorbed"),
            q(154, "Corona", "Corona loss is maximum during:", listOf("Dry weather", "Rainy weather", "Winter", "Low humidity"), "Rainy weather"),
            q(155, "Insulators", "Guard rings are used in suspension insulators to:", listOf("Reduce conductor resistance", "Improve string efficiency", "Increase losses", "Reduce voltage level"), "Improve string efficiency"),
            q(156, "Underground Cables", "The capacitance of a cable increases with:", listOf("Increase in insulation thickness", "Decrease in conductor radius", "Increase in conductor radius", "Decrease in dielectric constant"), "Increase in conductor radius"),
            q(157, "Distribution Systems", "A ring-main system offers better reliability because:", listOf("Only one supply path exists", "Multiple supply paths exist", "Voltage is always constant", "Losses are zero"), "Multiple supply paths exist"),
            q(158, "Load Flow", "The Jacobian matrix is used in:", listOf("Gauss-Seidel method", "Newton-Raphson method", "Zbus method", "Economic dispatch"), "Newton-Raphson method"),
            q(159, "Load Flow", "The slack bus compensates for:", listOf("Reactive power only", "Transmission losses and mismatch", "Voltage drop only", "Frequency deviation"), "Transmission losses and mismatch"),
            q(160, "Network Analysis", "Ybus matrix is generally:", listOf("Symmetric", "Antisymmetric", "Singular always", "Diagonal only"), "Symmetric"),
            q(161, "Symmetrical Components", "Zero-sequence currents require:", listOf("Return path through neutral/ground", "Positive sequence only", "Balanced load", "Delta connection only"), "Return path through neutral/ground"),
            q(162, "Fault Analysis", "The least severe fault is generally:", listOf("Three-phase fault", "Double line-to-ground fault", "Line-to-line fault", "Open conductor fault"), "Open conductor fault"),
            q(163, "Power System Stability", "The equal area criterion is based on:", listOf("Energy balance", "Voltage balance", "Current balance", "Reactive power balance"), "Energy balance"),
            q(164, "Swing Equation", "Swing equation is a:", listOf("First-order equation", "Second-order nonlinear equation", "Linear algebraic equation", "Transfer function"), "Second-order nonlinear equation"),
            q(165, "Load Frequency Control", "In an interconnected power system, tie-line power depends on:", listOf("Frequency only", "Voltage only", "Rotor angle difference", "Resistance"), "Rotor angle difference"),
            q(166, "Economic Operation", "The coordination equation in economic dispatch is obtained from:", listOf("Lagrange multiplier method", "Gauss elimination", "LU decomposition", "KCL"), "Lagrange multiplier method"),
            q(167, "Unit Commitment", "Priority list method is commonly used in:", listOf("Load flow", "Unit commitment", "Protection", "Fault analysis"), "Unit commitment"),
            q(168, "Hydrothermal Scheduling", "Water value concept is important in:", listOf("Hydrothermal scheduling", "Load forecasting", "Protection", "Voltage control"), "Hydrothermal scheduling"),
            q(169, "Protection", "Selectivity in protection means:", listOf("Fast operation", "Operating only for the faulted section", "Low cost", "High voltage"), "Operating only for the faulted section"),
            q(170, "Relays", "Percentage differential protection is widely used for:", listOf("Transmission lines", "Transformers", "Loads", "Capacitors"), "Transformers"),
            q(171, "Circuit Breakers", "The rated breaking current of a circuit breaker is specified in:", listOf("RMS value", "Peak value", "Average value", "Instantaneous value"), "RMS value"),
            q(172, "Circuit Breakers", "SF6 gas is preferred because of its:", listOf("Low dielectric strength", "Excellent arc quenching property", "High resistance", "Low density"), "Excellent arc quenching property"),
            q(173, "Travelling Waves", "A surge arrester is connected:", listOf("In series with the line", "Between line and ground", "Across generator only", "Across load only"), "Between line and ground"),
            q(174, "Travelling Waves", "The surge impedance of an overhead line is approximately:", listOf("10 Ω", "50 Ω", "300–500 Ω", "5000 Ω"), "300–500 Ω"),
            q(175, "HVDC Transmission", "HVDC transmission eliminates:", listOf("Line resistance", "Reactive power flow in line", "Power transfer", "Converter losses"), "Reactive power flow in line"),
            q(176, "HVDC Transmission", "LCC-HVDC uses:", listOf("IGBTs", "GTOs", "Thyristors", "BJTs"), "Thyristors"),
            q(177, "FACTS", "STATCOM is connected:", listOf("In series", "In shunt", "In cascade", "In delta"), "In shunt"),
            q(178, "FACTS", "UPFC combines the features of:", listOf("SVC and TCSC", "STATCOM and SSSC", "AVR and Governor", "Relay and Breaker"), "STATCOM and SSSC"),
            q(179, "Power Quality", "Voltage interruption is characterized by:", listOf("Increase in frequency", "Near-zero voltage", "Harmonic distortion", "High power factor"), "Near-zero voltage"),
            q(180, "Power Quality", "Active filters are mainly used for:", listOf("Harmonic mitigation", "Power generation", "Voltage transformation", "Fault detection"), "Harmonic mitigation"),
            q(181, "Smart Grid", "Wide Area Measurement Systems primarily use:", listOf("PMUs", "CTs", "PTs", "Relays"), "PMUs"),
            q(182, "Smart Grid", "PMU stands for:", listOf("Power Monitoring Unit", "Phasor Measurement Unit", "Phase Monitoring Utility", "Power Management Unit"), "Phasor Measurement Unit"),
            q(183, "Renewable Energy Integration", "The capacity factor of a wind plant is generally:", listOf("0%", "100%", "Less than 100%", "More than 100%"), "Less than 100%"),
            q(184, "Renewable Energy Integration", "Grid-forming inverters are important for:", listOf("System stability", "Increasing losses", "Reducing voltage", "Protection only"), "System stability"),
            q(185, "SCADA", "SCADA systems improve:", listOf("Situational awareness", "Transformer core losses", "Conductor resistance", "Corona loss"), "Situational awareness"),
            q(186, "SCADA", "Telemetry in SCADA refers to:", listOf("Remote measurement transmission", "Relay coordination", "Power generation", "Fault clearing"), "Remote measurement transmission"),
            q(187, "Energy Management System", "Optimal power flow is an application of:", listOf("EMS", "Protection", "Circuit breakers", "Travelling waves"), "EMS"),
            q(188, "Energy Management System", "Security-constrained OPF considers:", listOf("Economic and security constraints", "Only economic objectives", "Only voltage limits", "Only frequency limits"), "Economic and security constraints"),
            q(189, "Voltage Control", "AVR stands for:", listOf("Automatic Voltage Regulator", "Advanced Voltage Relay", "Automatic VAR Reducer", "Advanced Voltage Reactor"), "Automatic Voltage Regulator"),
            q(190, "Voltage Control", "Reactive power compensation improves:", listOf("Voltage profile", "Frequency regulation", "Rotor inertia", "Fault current"), "Voltage profile"),
            q(191, "Power System Components", "A CT is used to:", listOf("Measure high current safely", "Measure voltage", "Generate power", "Store energy"), "Measure high current safely"),
            q(192, "Power System Components", "A PT is used to:", listOf("Measure voltage safely", "Measure frequency", "Control power factor", "Store energy"), "Measure voltage safely"),
            q(193, "Per Unit System", "The per-unit value is obtained by:", listOf("Actual value ÷ Base value", "Actual value × Base value", "Base value ÷ Actual value", "Actual value² ÷ Base value"), "Actual value ÷ Base value"),
            q(194, "Per Unit System", "A major advantage of per-unit representation is:", listOf("Simplifies transformer calculations", "Eliminates faults", "Reduces losses", "Improves efficiency"), "Simplifies transformer calculations"),
            q(195, "Transmission Lines", "The resistance of a conductor increases with:", listOf("Increase in temperature", "Decrease in temperature", "Decrease in length", "Increase in diameter"), "Increase in temperature"),
            q(196, "Transmission Lines", "The charging current of a transmission line is due to:", listOf("Capacitance", "Resistance", "Inductance", "Conductance"), "Capacitance"),
            q(197, "Load Flow", "The primary purpose of load flow analysis is to determine:", listOf("Bus voltages and power flows", "Fault currents only", "Relay settings", "Generator design"), "Bus voltages and power flows"),
            q(198, "Fault Analysis", "Fault studies are necessary for selecting:", listOf("Circuit breaker ratings", "Transformer color", "Conductor material only", "Tower height"), "Circuit breaker ratings"),
            q(199, "Protection", "Protection coordination ensures:", listOf("Minimum outage area", "Maximum outage area", "Higher losses", "Voltage collapse"), "Minimum outage area"),
            q(200, "Smart Grid", "The ultimate objective of a smart grid is:", listOf("Reliable, efficient and intelligent power delivery", "Higher losses", "Manual operation", "Reduced monitoring"), "Reliable, efficient and intelligent power delivery")
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
            "Economic Operation", "Unit Commitment", "Hydrothermal Scheduling",
            "Load Frequency Control", "Renewable Energy Integration", "Smart Grid",
            "SCADA", "Energy Management System", "Energy Management" -> "ps_generation_econ"

            "Transmission Lines", "Transmission Line Performance", "Underground Cables",
            "Insulators", "Corona", "Distribution Systems", "Travelling Waves",
            "HVDC Transmission", "FACTS", "Power Quality", "Voltage Control" -> "ps_transmission"

            "Per Unit System", "Power System Components", "Network Analysis",
            "Load Flow", "Symmetrical Components", "Symmetrical Fault Analysis",
            "Unsymmetrical Fault Analysis", "Fault Analysis" -> "ps_flow_stability"

            "Power System Stability", "Swing Equation", "Protection",
            "Relays", "Circuit Breakers" -> "ps_faults"

            else -> "ps_generation_econ"
        }

        val subtopicId = when (topicId) {
            "ps_generation_econ" -> "ps_econ_dispatch"
            "ps_transmission" -> "ps_trans_lines"
            "ps_flow_stability" -> "ps_flow_faults"
            "ps_faults" -> "ps_prot_stability"
            else -> "ps_econ_dispatch"
        }

        val correctIdx = options.indexOf(answer).let { if (it == -1) 0 else it }

        val difficulty = when (id % 3) {
            0 -> "Easy"
            1 -> "Medium"
            else -> "Hard"
        }

        val formulas = when (topicId) {
            "ps_generation_econ" -> "dC_i / dP_i = λ, P_total = Σ P_i"
            "ps_transmission" -> "L = 2*10^-7 * ln(GMD/GMR), C = 2*π*ε / ln(D/r)"
            "ps_flow_stability" -> "Y_bus, Z_bus = Y_bus^-1, I_fault = 3*E_a / (Z1 + Z2 + Z0)"
            "ps_faults" -> "M * d^2δ/dt^2 = P_m - P_e"
            else -> ""
        }

        val explanationText = "Based on fundamental $subdomain concepts. The correct choice is: '$answer'. " +
            when (topicId) {
                "ps_generation_econ" -> "Covers power generation mechanisms, cost curves, unit commitment constraints, AGC, and optimal hydrothermal scheduling."
                "ps_transmission" -> "Deals with overhead parameter modeling (GMR/GMD), capacitance, insulators, cables, power controllers (FACTS), and wave transmission."
                "ps_flow_stability" -> "Addresses network formulation, power flow equations (GS/NR), symmetrical components transformations, and fault current computations."
                "ps_faults" -> "Evaluates transient rotor stability, Protection systems, protective relay settings, and circuit breaker arc-extinction characteristics."
                else -> ""
            }

        return GateQuestion(
            id = "ps_q_$id",
            subjectId = "power_systems",
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
            shortcutTricks = "Examine boundaries, dimensions, and the physical constraints of components.",
            relatedConcepts = "Power Systems engineering, grid operation, $subdomain",
            difficulty = difficulty
        )
    }
}
