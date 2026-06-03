package com.example.data

object SignalsAndSystemsQuestions {

    val questions: List<GateQuestion> by lazy {
        group1 + group2 + group3 + group4
    }

    private val group1 = listOf(
        q(1, "Signal Classification", "Which of the following continuous-time signals is an energy signal?", listOf("u(t)", "sin(t)", "e^(-t)u(t)", "1"), "e^(-t)u(t)"),
        q(2, "Signal Classification", "The signal x(t)=cos(10t) is", listOf("Energy signal", "Power signal", "Neither energy nor power signal", "Aperiodic signal"), "Power signal"),
        q(3, "Signal Classification", "The fundamental period of x(t)=sin(4t)+cos(6t) is", listOf("π", "2π", "π/2", "2π/3"), "π"),
        q(4, "Signal Classification", "Which signal is odd?", listOf("cos(t)", "e^(-t)", "sin(t)", "t²"), "sin(t)"),
        q(5, "Signal Classification", "A signal satisfying x(t)=x(-t) is called", listOf("Odd", "Periodic", "Even", "Energy"), "Even"),
        q(6, "Basic Signal Operations", "Time reversal of x(t) is represented by", listOf("x(t-1)", "x(-t)", "x(2t)", "x(t+1)"), "x(-t)"),
        q(7, "Basic Signal Operations", "The signal x(2t) represents", listOf("Time expansion", "Amplitude scaling", "Time compression", "Time shifting"), "Time compression"),
        q(8, "Basic Signal Operations", "The signal x(t-3) is obtained by", listOf("Advancing by 3 units", "Delaying by 3 units", "Compressing by 3", "Reflecting"), "Delaying by 3 units"),
        q(9, "Basic Signal Operations", "Amplitude scaling by factor 5 produces", listOf("x(t/5)", "5x(t)", "x(t)+5", "x(t-5)"), "5x(t)"),
        q(10, "Basic Signal Operations", "The operation x(-(t-2)) corresponds to", listOf("Shift then reverse", "Reverse then shift", "Scale then reverse", "Differentiate"), "Shift then reverse"),
        q(11, "Continuous-Time Systems", "A system is memoryless if output depends on", listOf("Past inputs only", "Present input only", "Future input only", "Past and future inputs"), "Present input only"),
        q(12, "Continuous-Time Systems", "Which system is linear?", listOf("y(t)=x²(t)", "y(t)=3x(t)", "y(t)=|x(t)|", "y(t)=sin(x(t))"), "y(t)=3x(t)"),
        q(13, "Continuous-Time Systems", "A causal system depends on", listOf("Future inputs", "Present and past inputs", "Only future outputs", "Random variables"), "Present and past inputs"),
        q(14, "Continuous-Time Systems", "The system y(t)=x(t)+2 is", listOf("Linear", "Time invariant", "Both linear and TI", "Nonlinear"), "Nonlinear"),
        q(15, "Continuous-Time Systems", "A system is stable if every bounded input produces", listOf("Unbounded output", "Bounded output", "Zero output", "Periodic output"), "Bounded output"),
        q(16, "Discrete-Time Systems", "Which sequence is causal?", listOf("δ[n+1]", "u[-n]", "u[n]", "δ[n+2]"), "u[n]"),
        q(17, "Discrete-Time Systems", "The sequence δ[n] is called", listOf("Step sequence", "Impulse sequence", "Ramp sequence", "Exponential sequence"), "Impulse sequence"),
        q(18, "Discrete-Time Systems", "u[n]-u[n-1] equals", listOf("δ[n]", "δ[n-1]", "u[n]", "0"), "δ[n]"),
        q(19, "Discrete-Time Systems", "The sequence a^n u[n] is stable when", listOf("|a|<1", "|a|>1", "a=2", "a=-2"), "|a|<1"),
        q(20, "Discrete-Time Systems", "The DT signal cos(πn) equals", listOf("1", "(-1)^n", "0", "n"), "(-1)^n"),
        q(21, "LTI Systems", "The output of an LTI system is obtained using", listOf("Differentiation", "Convolution", "Integration only", "Sampling"), "Convolution"),
        q(22, "LTI Systems", "The response of an LTI system to δ(t) is called", listOf("Step response", "Natural response", "Impulse response", "Frequency response"), "Impulse response"),
        q(23, "LTI Systems", "For a causal LTI system, h(t)=0 for", listOf("t>0", "t=0", "t<0", "all t"), "t<0"),
        q(24, "Convolution", "δ(t)*x(t) equals", listOf("0", "δ(t)", "x(t)", "x²(t)"), "x(t)"),
        q(25, "Convolution", "Convolution operation is", listOf("Commutative", "Non-commutative", "Anti-commutative", "None"), "Commutative"),
        q(26, "Convolution", "If x(t)=u(t) and h(t)=u(t), then y(t)=x*h is", listOf("u(t)", "tu(t)", "t²u(t)", "δ(t)"), "tu(t)"),
        q(27, "Convolution", "The convolution of δ(t-2) with x(t) is", listOf("x(t)", "x(t+2)", "x(t-2)", "0"), "x(t-2)"),
        q(28, "Convolution", "If x[n]=δ[n] and h[n]=u[n], then y[n] equals", listOf("δ[n]", "u[n]", "0", "n"), "u[n]"),
        q(29, "Properties of LTI Systems", "An LTI system is BIBO stable if", listOf("∫|h(t)|dt < ∞", "h(t)=0", "h(t)=δ(t)", "h(t)=u(t)"), "∫|h(t)|dt < ∞"),
        q(30, "Properties of LTI Systems", "An LTI system is causal if", listOf("h(t)=0 for t<0", "h(t)=0 for t>0", "h(t)=1", "h(t)=δ(t)"), "h(t)=0 for t<0"),
        q(31, "Fourier Series", "Fourier series represents", listOf("Aperiodic signals", "Periodic signals", "Random signals", "Impulse signals"), "Periodic signals"),
        q(32, "Fourier Series", "The DC component of a Fourier series is", listOf("a0", "a1", "b1", "an"), "a0"),
        q(33, "Fourier Series", "For an odd signal, Fourier series contains", listOf("Cosine terms only", "Sine terms only", "DC only", "No terms"), "Sine terms only"),
        q(34, "Fourier Series", "For an even signal, Fourier series contains", listOf("Sine terms only", "Cosine terms only", "No harmonics", "Only imaginary coefficients"), "Cosine terms only"),
        q(35, "Fourier Series", "Parseval's theorem relates", listOf("Energy and Fourier coefficients", "Power and ROC", "Convolution and multiplication", "Sampling and aliasing"), "Energy and Fourier coefficients"),
        q(36, "CTFT", "The Fourier transform of δ(t) is", listOf("0", "1", "δ(ω)", "jω"), "1"),
        q(37, "CTFT", "The Fourier transform of 1 is", listOf("1", "2π δ(ω)", "jω", "ω"), "2π δ(ω)"),
        q(38, "CTFT", "Time shifting in time domain results in", listOf("Magnitude scaling", "Phase shift", "Differentiation", "Integration"), "Phase shift"),
        q(39, "CTFT", "Convolution in time domain corresponds to", listOf("Division in frequency domain", "Addition in frequency domain", "Multiplication in frequency domain", "Differentiation"), "Multiplication in frequency domain"),
        q(40, "CTFT", "The Fourier transform of x(-t) is", listOf("X(-ω)", "X(ω)", "jX(ω)", "ωX(ω)"), "X(-ω)"),
        q(41, "DTFT", "The DTFT is periodic with period", listOf("π", "2π", "4π", "1"), "2π"),
        q(42, "DTFT", "The DTFT of δ[n] is", listOf("1", "0", "δ(ω)", "ω"), "1"),
        q(43, "DTFT", "Frequency shifting in DTFT is achieved by", listOf("Multiplication by exponential", "Differentiation", "Integration", "Convolution"), "Multiplication by exponential"),
        q(44, "Sampling Theorem", "Nyquist sampling rate equals", listOf("fm", "2fm", "4fm", "fm/2"), "2fm"),
        q(45, "Sampling Theorem", "Aliasing occurs when", listOf("fs > 2fm", "fs = 2fm", "fs < 2fm", "fs = 4fm"), "fs < 2fm"),
        q(46, "Sampling Theorem", "The Nyquist frequency is", listOf("fs", "fs/2", "2fs", "fs/4"), "fs/2"),
        q(47, "Numerical - Sampling", "A signal bandlimited to 5 kHz is sampled without aliasing. Minimum sampling frequency is", listOf("2.5 kHz", "5 kHz", "10 kHz", "20 kHz"), "10 kHz"),
        q(48, "Numerical - Signal Energy", "The energy of x(t)=e^(-t)u(t) is", listOf("1/2", "1", "2", "∞"), "1/2"),
        q(49, "Numerical - Convolution", "If x(t)=δ(t-1) and h(t)=δ(t-2), then x*h equals", listOf("δ(t-3)", "δ(t-1)", "δ(t-2)", "δ(t)"), "δ(t-3)"),
        q(50, "Numerical - Fourier Series", "The fundamental frequency of a periodic signal having period T=0.5 s is", listOf("1 Hz", "2 Hz", "4 Hz", "0.5 Hz"), "2 Hz")
    )

    private val group2 = listOf(
        q(51, "Laplace Transform", "The Laplace transform of δ(t) is", listOf("0", "1", "s", "1/s"), "1"),
        q(52, "Laplace Transform", "The Laplace transform of u(t) is", listOf("1/s", "s", "1/(s+1)", "δ(s)"), "1/s"),
        q(53, "Laplace Transform", "The Laplace transform of e^(-at)u(t) is", listOf("1/(s-a)", "1/(s+a)", "s/(s+a)", "a/(s+a)"), "1/(s+a)"),
        q(54, "Laplace Transform", "The Laplace transform of t·u(t) is", listOf("1/s", "1/s²", "s²", "2/s³"), "1/s²"),
        q(55, "Laplace Transform", "The Laplace transform of sin(at)u(t) is", listOf("a/(s²+a²)", "s/(s²+a²)", "1/(s+a)", "a/(s+a)"), "a/(s²+a²)"),
        q(56, "Laplace Transform", "The Laplace transform of cos(at)u(t) is", listOf("a/(s²+a²)", "s/(s²+a²)", "1/(s+a)", "s/(s+a)"), "s/(s²+a²)"),
        q(57, "Laplace Transform Properties", "Differentiation in time domain corresponds to", listOf("Division by s", "Multiplication by s", "Convolution", "Integration"), "Multiplication by s"),
        q(58, "Laplace Transform Properties", "Integration in time domain corresponds to", listOf("Multiplication by s", "Division by s", "Differentiation", "Time shift"), "Division by s"),
        q(59, "ROC", "The ROC of a right-sided signal lies", listOf("To the left of leftmost pole", "Between poles", "To the right of rightmost pole", "Entire s-plane"), "To the right of rightmost pole"),
        q(60, "ROC", "The ROC of a stable continuous-time system must include", listOf("s = 1", "s = -1", "jω-axis", "Origin only"), "jω-axis"),
        q(61, "ROC", "ROC never contains", listOf("Origin", "Poles", "Zeros", "Imaginary axis"), "Poles"),
        q(62, "Inverse Laplace Transform", "The inverse Laplace transform of 1/s is", listOf("δ(t)", "u(t)", "e^(-t)", "t"), "u(t)"),
        q(63, "Inverse Laplace Transform", "The inverse Laplace transform of 1/(s+2) is", listOf("e^(2t)u(t)", "e^(-2t)u(t)", "2e^(-t)u(t)", "u(t)"), "e^(-2t)u(t)"),
        q(64, "Inverse Laplace Transform", "The inverse Laplace transform of 1/s² is", listOf("u(t)", "δ(t)", "t u(t)", "e^(-t)"), "t u(t)"),
        q(65, "System Analysis using Laplace", "Transfer function is defined as", listOf("Y(s)X(s)", "X(s)/Y(s)", "Y(s)/X(s)", "sY(s)"), "Y(s)/X(s)"),
        q(66, "System Analysis using Laplace", "The poles of a transfer function determine", listOf("Input", "Stability", "Sampling frequency", "Amplitude"), "Stability"),
        q(67, "System Analysis using Laplace", "A system is stable if all poles lie", listOf("Right half plane", "On imaginary axis", "Left half plane", "At origin"), "Left half plane"),
        q(68, "Numerical - Laplace", "The Laplace transform of 3e^(-2t)u(t) is", listOf("3/(s+2)", "1/(s+2)", "3/(s-2)", "s/(s+2)"), "3/(s+2)"),
        q(69, "Numerical - Laplace", "The Laplace transform of 5δ(t) is", listOf("0", "5", "1/5", "s"), "5"),
        q(70, "Numerical - Laplace", "The inverse Laplace transform of 4/s is", listOf("4u(t)", "u(t)", "4δ(t)", "4e^(-t)"), "4u(t)"),
        q(71, "Z-Transform", "The Z-transform of δ[n] is", listOf("0", "1", "z", "1/z"), "1"),
        q(72, "Z-Transform", "The Z-transform of u[n] is", listOf("1/(1-z⁻¹)", "1-z⁻¹", "z", "1"), "1/(1-z⁻¹)"),
        q(73, "Z-Transform", "The Z-transform of aⁿu[n] is", listOf("1/(1-az⁻¹)", "1/(1-z⁻¹)", "1/(z-a)", "z/(z+1)"), "1/(1-az⁻¹)"),
        q(74, "Z-Transform Properties", "Time shifting by one sample corresponds to", listOf("Multiplication by z", "Multiplication by z⁻¹", "Division by z²", "Addition of z"), "Multiplication by z⁻¹"),
        q(75, "ROC of Z-Transform", "For a right-sided sequence, ROC lies", listOf("Inside innermost pole", "Outside outermost pole", "Between poles", "Entire z-plane"), "Outside outermost pole"),
        q(76, "ROC of Z-Transform", "For stability, ROC must include", listOf("Origin", "Unit circle", "Pole", "z = 0"), "Unit circle"),
        q(77, "ROC of Z-Transform", "ROC cannot contain", listOf("Unit circle", "Origin", "Poles", "Infinity"), "Poles"),
        q(78, "Inverse Z-Transform", "The inverse Z-transform of 1 is", listOf("δ[n]", "u[n]", "n", "aⁿ"), "δ[n]"),
        q(79, "Inverse Z-Transform", "The inverse Z-transform of 1/(1-0.5z⁻¹) is", listOf("(0.5)ⁿu[n]", "2ⁿu[n]", "δ[n]", "u[n]"), "(0.5)ⁿu[n]"),
        q(80, "Difference Equations", "The order of a difference equation is determined by", listOf("Input amplitude", "Highest delay term", "Sampling frequency", "Gain"), "Highest delay term"),
        q(81, "Difference Equations", "A first-order difference equation contains", listOf("One delayed sample", "Two delayed samples", "Three delayed samples", "No delay"), "One delayed sample"),
        q(82, "Difference Equations", "The system y[n]=x[n]+x[n−1] is", listOf("Nonlinear", "LTI", "Time varying", "Unstable"), "LTI"),
        q(83, "Causality", "A causal discrete-time system cannot depend on", listOf("Present input", "Past input", "Future input", "Past output"), "Future input"),
        q(84, "Causality", "The impulse response of a causal DT system satisfies", listOf("h[n]=0 for n<0", "h[n]=0 for n>0", "h[n]=1", "h[n]=δ[n]"), "h[n]=0 for n<0"),
        q(85, "Stability", "A DT LTI system is BIBO stable if", listOf("Σ|h[n]| < ∞", "Σh[n]=0", "h[n]=1", "h[n]=δ[n]"), "Σ|h[n]| < ∞"),
        q(86, "Stability", "A pole at z = 1.2 indicates that the system is", listOf("Stable", "Marginally stable", "Unstable", "Causal"), "Unstable"),
        q(87, "Numerical - Z Transform", "For x[n]=(0.25)^n u[n], ROC is", listOf("|z|<0.25", "|z|>0.25", "|z|<1", "All z"), "|z|>0.25"),
        q(88, "Numerical - Z Transform", "The pole of X(z)=1/(1-0.5z⁻¹) is located at", listOf("0.5", "-0.5", "1", "2"), "0.5"),
        q(89, "Numerical - Stability", "A CT system with pole at s=-3 is", listOf("Unstable", "Stable", "Marginally stable", "Noncausal"), "Stable"),
        q(90, "Numerical - Stability", "A CT system with pole at s=+2 is", listOf("Stable", "Marginally stable", "Unstable", "Causal"), "Unstable"),
        q(91, "Numerical - Inverse Laplace", "Inverse Laplace of 2/(s+3) is", listOf("2e^(-3t)u(t)", "3e^(-2t)u(t)", "2u(t)", "e^(-3t)"), "2e^(-3t)u(t)"),
        q(92, "Numerical - Inverse Laplace", "Inverse Laplace of 1/(s+5)^2 is", listOf("e^(-5t)", "te^(-5t)u(t)", "5e^(-t)", "t²u(t)"), "te^(-5t)u(t)"),
        q(93, "Numerical - Transfer Function", "The transfer function with impulse response e^(-2t)u(t) is", listOf("1/(s+2)", "1/(s-2)", "s+2", "s/(s+2)"), "1/(s+2)"),
        q(94, "Numerical - Transfer Function", "The pole of H(s)=1/(s+4) is", listOf("-4", "4", "0", "1"), "-4"),
        q(95, "Numerical - ROC", "The ROC of e^(-2t)u(t) is", listOf("Re(s)>-2", "Re(s)<-2", "Re(s)>2", "All s"), "Re(s)>-2"),
        q(96, "Numerical - Difference Equation", "For y[n]=0.5y[n−1]+x[n], the order of the system is", listOf("0", "1", "2", "3"), "1"),
        q(97, "Numerical - Difference Equation", "The system y[n]=x[n]+2x[n−1] is", listOf("Linear", "Nonlinear", "Time varying", "Unstable"), "Linear"),
        q(98, "Numerical - Causality", "The system y[n]=x[n+1] is", listOf("Causal", "Stable", "Noncausal", "LTI"), "Noncausal"),
        q(99, "Numerical - Stability", "The impulse response h[n]=(1.2)^n u[n] corresponds to a", listOf("Stable system", "Unstable system", "Memoryless system", "Static system"), "Unstable system"),
        q(100, "Numerical - Z Transform", "The Z-transform of δ[n−2] is", listOf("z²", "z⁻²", "1", "z⁻¹"), "z⁻²")
    )

    private val group3 = listOf(
        q(101, "Fourier Transform Properties", "Time shifting of x(t) by t0 results in which factor in the frequency domain?", listOf("e^{-jωt0}", "e^{jωt0}", "jω", "1/jω"), "e^{-jωt0}"),
        q(102, "Fourier Transform Properties", "Differentiation in time domain corresponds to", listOf("jωX(ω)", "X(ω)/jω", "ω²X(ω)", "X(-ω)"), "jωX(ω)"),
        q(103, "Fourier Transform Properties", "Integration in time domain corresponds to", listOf("jωX(ω)", "X(ω)/(jω)", "ωX(ω)", "X(-ω)"), "X(ω)/(jω)"),
        q(104, "Fourier Transform Properties", "If x(t) is real and even, then X(ω) is", listOf("Imaginary and odd", "Real and even", "Real and odd", "Complex"), "Real and even"),
        q(105, "Fourier Transform Properties", "Multiplication in time domain corresponds to", listOf("Addition in frequency domain", "Convolution in frequency domain", "Differentiation", "Time reversal"), "Convolution in frequency domain"),
        q(106, "DTFT", "The DTFT of a finite-duration sequence is", listOf("Always periodic", "Aperiodic", "Infinite", "Discrete"), "Always periodic"),
        q(107, "DTFT", "The DTFT of δ[n-k] is", listOf("e^{-jωk}", "e^{jωk}", "δ(ω-k)", "1"), "e^{-jωk}"),
        q(108, "DTFT", "The inverse DTFT reconstructs the sequence from", listOf("Laplace domain", "Z-domain", "Frequency domain", "State space"), "Frequency domain"),
        q(109, "DTFT", "For a real sequence x[n], X(ω) satisfies", listOf("X(ω)=X(-ω)", "X(ω)=X*(-ω)", "X(ω)=0", "X(ω)=1"), "X(ω)=X*(-ω)"),
        q(110, "DTFT", "The DTFT is periodic with fundamental period", listOf("π", "2π", "4π", "1"), "2π"),
        q(111, "Advanced Convolution", "Convolution of two finite-duration signals produces a signal whose length is", listOf("N+M-1", "N+M", "NM", "N-M"), "N+M-1"),
        q(112, "Advanced Convolution", "Convolution is associative because", listOf("(x*h1)*h2=x*(h1*h2)", "x*h=h*x", "x*(h+h)=xh", "None"), "(x*h1)*h2=x*(h1*h2)"),
        q(113, "Advanced Convolution", "The convolution of two causal signals is", listOf("Anticausal", "Causal", "Noncausal", "Unstable"), "Causal"),
        q(114, "Advanced Convolution", "If x(t)=δ(t), then x(t)*h(t) equals", listOf("0", "δ(t)", "h(t)", "dh/dt"), "h(t)"),
        q(115, "Advanced Convolution", "The output of an LTI system is determined completely by", listOf("Input only", "Impulse response only", "Input and impulse response", "Initial conditions only"), "Input and impulse response"),
        q(116, "State Space Representation", "The state equation is generally written as", listOf("ẋ=Ax+Bu", "y=Ax", "u=Ax", "x=Ay"), "ẋ=Ax+Bu"),
        q(117, "State Space Representation", "The output equation is", listOf("y=Cx+Du", "x=Cy", "u=Cx", "ẋ=Cx"), "y=Cx+Du"),
        q(118, "State Space Representation", "The order of a state-space model equals", listOf("Number of inputs", "Number of outputs", "Number of state variables", "Number of poles only"), "Number of state variables"),
        q(119, "State Space Representation", "Eigenvalues of matrix A correspond to", listOf("Zeros", "Poles", "Inputs", "Outputs"), "Poles"),
        q(120, "State Space Representation", "A state-space realization is minimal if it is", listOf("Stable", "Controllable and Observable", "Time invariant", "Linear only"), "Controllable and Observable"),
        q(121, "Signal Flow Graph", "Mason's gain formula is used to find", listOf("Frequency response", "Transfer function", "Impulse response", "Convolution"), "Transfer function"),
        q(122, "Signal Flow Graph", "A loop in a signal flow graph starts and ends at", listOf("Different nodes", "Same node", "Output node", "Input node"), "Same node"),
        q(123, "Signal Flow Graph", "Non-touching loops are loops that", listOf("Share nodes", "Do not share nodes", "Share branches", "Have unity gain"), "Do not share nodes"),
        q(124, "Signal Flow Graph", "Forward paths in Mason's formula are", listOf("Closed paths", "Paths from input to output", "Loops", "Feedback paths only"), "Paths from input to output"),
        q(125, "Signal Flow Graph", "The determinant Δ in Mason's formula includes", listOf("Loop gains", "Input gain", "Output gain", "Sampling gain"), "Loop gains"),
        q(126, "Stability Analysis", "A continuous-time LTI system is stable if all poles lie", listOf("Right-half plane", "Left-half plane", "Origin", "Imaginary axis only"), "Left-half plane"),
        q(127, "Stability Analysis", "A discrete-time system is stable if all poles lie", listOf("Outside unit circle", "On unit circle", "Inside unit circle", "At origin only"), "Inside unit circle"),
        q(128, "Stability Analysis", "A pole on the imaginary axis may lead to", listOf("Asymptotic stability", "Marginal stability", "Instability always", "No response"), "Marginal stability"),
        q(129, "Stability Analysis", "For BIBO stability, impulse response must be", listOf("Absolutely integrable", "Periodic", "Odd", "Constant"), "Absolutely integrable"),
        q(130, "Stability Analysis", "The ROC of a stable DT system includes", listOf("Unit circle", "Origin only", "Infinity only", "Pole"), "Unit circle"),
        q(131, "Causality", "A causal system cannot anticipate", listOf("Past input", "Present input", "Future input", "Output"), "Future input"),
        q(132, "Causality", "A right-sided impulse response implies", listOf("Causality", "Instability", "Anticausality", "Periodicity"), "Causality"),
        q(133, "Causality", "For a causal CT system, ROC is", listOf("Left of leftmost pole", "Right of rightmost pole", "Between poles", "Entire plane"), "Right of rightmost pole"),
        q(134, "Causality", "A system with y(t)=x(t+1) is", listOf("Causal", "Noncausal", "Stable", "Memoryless"), "Noncausal"),
        q(135, "Causality", "Memoryless systems are always", listOf("Causal", "Unstable", "Nonlinear", "Periodic"), "Causal"),
        q(136, "Numerical - Fourier Transform", "The Fourier transform of δ(t-3) is", listOf("e^{-j3ω}", "e^{j3ω}", "δ(ω-3)", "1"), "e^{-j3ω}"),
        q(137, "Numerical - Fourier Transform", "The Fourier transform of 2δ(t) is", listOf("1", "2", "δ(ω)", "2δ(ω)"), "2"),
        q(138, "Numerical - Convolution", "If x[n]={1,1} and h[n]={1,1}, the convolution result is", listOf("{1,2,1}", "{1,1}", "{2,2}", "{1,2}"), "{1,2,1}"),
        q(139, "Numerical - DTFT", "The DTFT of δ[n-1] is", listOf("e^{-jω}", "e^{jω}", "1", "ω"), "e^{-jω}"),
        q(140, "Numerical - Stability", "A DT pole at z=0.5 indicates the system is", listOf("Stable", "Unstable", "Marginally stable", "Noncausal"), "Stable"),
        q(141, "Numerical - Stability", "A DT pole at z=1.5 indicates the system is", listOf("Stable", "Marginally stable", "Unstable", "Causal"), "Unstable"),
        q(142, "Numerical - State Space", "For a second-order system, the minimum number of state variables required is", listOf("1", "2", "3", "4"), "2"),
        q(143, "Numerical - Laplace", "The inverse Laplace transform of 1/(s+1)^2 is", listOf("te^{-t}u(t)", "e^{-t}u(t)", "u(t)", "δ(t)"), "te^{-t}u(t)"),
        q(144, "Numerical - Fourier Series", "The fundamental frequency of a signal with period 0.25 s is", listOf("2 Hz", "4 Hz", "8 Hz", "0.25 Hz"), "4 Hz"),
        q(145, "Numerical - Sampling", "A signal bandlimited to 8 kHz requires a minimum sampling frequency of", listOf("8 kHz", "12 kHz", "16 kHz", "20 kHz"), "16 kHz"),
        q(146, "Numerical - Z Transform", "The ROC of x[n]=(0.8)^n u[n] is", listOf("|z|>0.8", "|z|<0.8", "|z|>1", "|z|<1"), "|z|>0.8"),
        q(147, "Numerical - Z Transform", "The pole of X(z)=1/(1-0.8z^{-1}) is", listOf("0.8", "-0.8", "1.25", "0"), "0.8"),
        q(148, "Numerical - Convolution", "The convolution of δ[n] with any sequence x[n] is", listOf("0", "δ[n]", "x[n]", "x[n-1]"), "x[n]"),
        q(149, "Numerical - Fourier Transform", "The Fourier transform of δ(t+2) is", listOf("e^{j2ω}", "e^{-j2ω}", "δ(ω+2)", "1"), "e^{j2ω}"),
        q(150, "Numerical - System Properties", "The system y(t)=5x(t) is", listOf("Linear and Time Invariant", "Nonlinear", "Time Varying", "Unstable"), "Linear and Time Invariant")
    )

    private val group4 = listOf(
        q(151, "Parseval's Theorem", "Parseval's theorem relates the signal energy in", listOf("Time and frequency domains", "Time and z domains", "Laplace and z domains", "State and output domains"), "Time and frequency domains"),
        q(152, "Parseval's Theorem", "The energy of a signal can be computed from its Fourier transform using", listOf("Nyquist theorem", "Parseval's theorem", "Mason's formula", "Final value theorem"), "Parseval's theorem"),
        q(153, "Energy Spectral Density", "Energy Spectral Density (ESD) is defined for", listOf("Energy signals", "Power signals", "Periodic signals only", "Random signals only"), "Energy signals"),
        q(154, "Energy Spectral Density", "The Energy Spectral Density is proportional to", listOf("|X(ω)|", "|X(ω)|²", "X(ω)", "1/X(ω)"), "|X(ω)|²"),
        q(155, "Power Spectral Density", "Power Spectral Density is commonly used for", listOf("Energy signals", "Power signals", "Impulse signals", "Finite-duration signals"), "Power signals"),
        q(156, "Sampling and Reconstruction", "The ideal reconstruction filter is", listOf("High-pass filter", "Band-pass filter", "Low-pass filter", "Differentiator"), "Low-pass filter"),
        q(157, "Sampling and Reconstruction", "Aliasing can be reduced by using", listOf("Differentiator", "Anti-aliasing filter", "Integrator", "Amplifier"), "Anti-aliasing filter"),
        q(158, "Sampling and Reconstruction", "The sinc function appears in", listOf("Ideal reconstruction", "State equations", "Difference equations", "ROC analysis"), "Ideal reconstruction"),
        q(159, "State Transition Matrix", "The state transition matrix is denoted by", listOf("Φ(t)", "H(s)", "X(z)", "Δ"), "Φ(t)"),
        q(160, "State Transition Matrix", "For an LTI system, the state transition matrix is", listOf("e^(At)", "At", "A+t", "1/(s-A)"), "e^(At)"),
        q(161, "Controllability", "A system is controllable if every state can be reached by", listOf("Input signal", "Output signal", "Impulse response", "ROC"), "Input signal"),
        q(162, "Controllability", "The controllability matrix is formed using", listOf("[B AB A²B ...]", "[C CA]", "[A B C]", "[A² B²]"), "[B AB A²B ...]"),
        q(163, "Observability", "A system is observable if", listOf("States can be determined from outputs", "Inputs can be determined", "Poles are stable", "Zeros are minimum phase"), "States can be determined from outputs"),
        q(164, "Observability", "The observability matrix is formed using", listOf("[C; CA; CA²; ...]", "[B AB]", "[A B]", "[A C]"), "[C; CA; CA²; ...]"),
        q(165, "Final Value Theorem", "The final value theorem is valid only if", listOf("System is stable", "System is nonlinear", "ROC contains all poles", "System is time varying"), "System is stable"),
        q(166, "Initial Value Theorem", "The initial value theorem determines", listOf("x(0+)", "x(∞)", "ROC", "Pole locations"), "x(0+)"),
        q(167, "Numerical - Sampling", "A signal bandlimited to 15 kHz is sampled without aliasing. Minimum sampling frequency is", listOf("15 kHz", "20 kHz", "30 kHz", "60 kHz"), "30 kHz"),
        q(168, "Numerical - Fourier Transform", "The Fourier transform of 3δ(t) is", listOf("δ(ω)", "3", "3δ(ω)", "ω"), "3"),
        q(169, "Numerical - Laplace", "The Laplace transform of 2u(t) is", listOf("1/s", "2/s", "s/2", "2s"), "2/s"),
        q(170, "Numerical - Laplace", "The inverse Laplace transform of 1/(s+4) is", listOf("e^(4t)u(t)", "e^(-4t)u(t)", "4u(t)", "δ(t)"), "e^(-4t)u(t)"),
        q(171, "Numerical - Z Transform", "The Z-transform of δ[n-3] is", listOf("z⁻³", "z³", "1", "z⁻¹"), "z⁻³"),
        q(172, "Numerical - ROC", "For x[n]=(0.4)^n u[n], ROC is", listOf("|z|>0.4", "|z|<0.4", "|z|>1", "|z|<1"), "|z|>0.4"),
        q(173, "Numerical - Stability", "A DT system with pole at z=0.2 is", listOf("Stable", "Unstable", "Marginally stable", "Noncausal"), "Stable"),
        q(174, "Numerical - Stability", "A DT system with pole at z=-1.3 is", listOf("Stable", "Marginally stable", "Unstable", "Causal"), "Unstable"),
        q(175, "Numerical - Fourier Series", "The fundamental frequency of a signal having period 0.1 s is", listOf("5 Hz", "10 Hz", "20 Hz", "100 Hz"), "10 Hz"),
        q(176, "Numerical - Convolution", "The convolution of δ(t-1) with δ(t-2) is", listOf("δ(t-1)", "δ(t-2)", "δ(t-3)", "0"), "δ(t-3)"),
        q(177, "Numerical - State Space", "A third-order system requires at least how many state variables?", listOf("1", "2", "3", "4"), "3"),
        q(178, "Numerical - Controllability", "For a second-order system, full controllability requires controllability matrix rank", listOf("0", "1", "2", "3"), "2"),
        q(179, "Numerical - Observability", "For a third-order system, full observability requires observability matrix rank", listOf("1", "2", "3", "4"), "3"),
        q(180, "Numerical - DTFT", "The DTFT of δ[n] is", listOf("0", "1", "ω", "δ(ω)"), "1"),
        q(181, "Advanced GATE Concept", "For a stable and causal CT system, ROC lies", listOf("Left of all poles", "Right of rightmost pole and includes jω-axis", "Between poles only", "Entire s-plane"), "Right of rightmost pole and includes jω-axis"),
        q(182, "Advanced GATE Concept", "A minimum-phase system has all zeros in", listOf("Right-half plane", "Left-half plane", "Imaginary axis", "Origin only"), "Left-half plane"),
        q(183, "Advanced GATE Concept", "A DT minimum-phase system has all zeros", listOf("Outside unit circle", "Inside unit circle", "On unit circle", "At z=0 only"), "Inside unit circle"),
        q(184, "Advanced GATE Concept", "The frequency response is obtained by evaluating H(s) at", listOf("s=0", "s=jω", "s=1", "s=-1"), "s=jω"),
        q(185, "Advanced GATE Concept", "The frequency response of a DT system is obtained by evaluating H(z) on", listOf("Real axis", "Unit circle", "Imaginary axis", "Origin"), "Unit circle"),
        q(186, "Mixed Revision", "The convolution theorem relates convolution in time domain to", listOf("Differentiation", "Multiplication in frequency domain", "Integration", "Sampling"), "Multiplication in frequency domain"),
        q(187, "Mixed Revision", "The impulse response completely characterizes", listOf("Any nonlinear system", "Any LTI system", "Any unstable system", "Any time-varying system"), "Any LTI system"),
        q(188, "Mixed Revision", "The Fourier transform of an energy signal always exists if", listOf("Signal is absolutely integrable", "Signal is periodic", "Signal is random", "Signal is constant"), "Signal is absolutely integrable"),
        q(189, "Mixed Revision", "A periodic signal generally has", listOf("Finite energy", "Infinite energy and finite power", "Zero power", "Zero energy"), "Infinite energy and finite power"),
        q(190, "Mixed Revision", "The unit impulse is the derivative of", listOf("Ramp", "Step", "Parabola", "Exponential"), "Step"),
        q(191, "Mixed Revision", "The derivative of a ramp function is", listOf("Impulse", "Step", "Parabola", "Zero"), "Step"),
        q(192, "Mixed Revision", "A memoryless system output depends on", listOf("Past inputs", "Future inputs", "Present input only", "Past outputs"), "Present input only"),
        q(193, "Mixed Revision", "The Fourier transform of a real even signal is", listOf("Real even", "Imaginary odd", "Complex odd", "Purely imaginary"), "Real even"),
        q(194, "Mixed Revision", "The Fourier transform of a real odd signal is", listOf("Real even", "Imaginary odd", "Real odd", "Complex even"), "Imaginary odd"),
        q(195, "Mixed Revision", "A causal and stable DT system must have poles", listOf("Inside unit circle", "Outside unit circle", "On unit circle", "At z=1"), "Inside unit circle"),
        q(196, "Mixed Revision", "The Laplace transform converts differential equations into", listOf("Integral equations", "Algebraic equations", "Difference equations", "State equations"), "Algebraic equations"),
        q(197, "Mixed Revision", "The Z-transform converts difference equations into", listOf("Differential equations", "Algebraic equations", "Integral equations", "State equations"), "Algebraic equations"),
        q(198, "Mixed Revision", "The region of convergence of a Z-transform depends on", listOf("Poles and sequence type", "Zeros only", "Sampling frequency only", "Input amplitude"), "Poles and sequence type"),
        q(199, "Mixed Revision", "The ROC of a finite-duration right-sided sequence is", listOf("Entire z-plane except z=0", "Inside unit circle", "Outside unit circle", "No ROC"), "Entire z-plane except z=0"),
        q(200, "Mixed Revision", "For an LTI system, the output is given by", listOf("Addition", "Differentiation", "Convolution of input and impulse response", "Sampling"), "Convolution of input and impulse response")
    )

    private fun q(
        id: Int,
        subdomain: String,
        question: String,
        options: List<String>,
        answer: String
    ): GateQuestion {
        val subtopicId = when (subdomain) {
            "Signal Classification", "Basic Signal Operations", "Continuous-Time Systems", "Discrete-Time Systems", 
            "LTI Systems", "Convolution", "Properties of LTI Systems", "Numerical - Signal Energy", 
            "Numerical - Convolution", "Causality", "Stability", "Numerical - Stability", "Numerical - Causality", 
            "Advanced Convolution", "State Space Representation", "Signal Flow Graph", "Stability Analysis", 
            "Numerical - State Space", "Numerical - System Properties", "State Transition Matrix", 
            "Controllability", "Observability", "Numerical - Controllability", "Numerical - Observability" -> "sig_lti_convolution"

            "Fourier Series", "CTFT", "DTFT", "Sampling Theorem", "Numerical - Sampling", "Numerical - Fourier Series", 
            "Laplace Transform", "Laplace Transform Properties", "ROC", "Inverse Laplace Transform", 
            "System Analysis using Laplace", "Numerical - Laplace", "Z-Transform", "Z-Transform Properties", 
            "ROC of Z-Transform", "Inverse Z-Transform", "Difference Equations", "Numerical - Z Transform", 
            "Numerical - Inverse Laplace", "Numerical - Transfer Function", "Numerical - ROC", 
            "Numerical - Difference Equation", "Fourier Transform Properties", "Numerical - Fourier Transform", 
            "Numerical - DTFT", "Parseval's Theorem", "Energy Spectral Density", "Power Spectral Density", 
            "Sampling and Reconstruction", "Final Value Theorem", "Initial Value Theorem", "Advanced GATE Concept", 
            "Mixed Revision" -> "sig_sampling_rate"

            else -> "sig_lti_convolution"
        }

        val topicId = if (subtopicId == "sig_lti_convolution") "sig_lti" else "sig_transforms"
        val correctIdx = options.indexOf(answer).let { if (it == -1) 0 else it }

        val difficulty = when (id % 3) {
            0 -> "Easy"
            1 -> "Medium"
            else -> "Hard"
        }

        val formulas = when (subdomain) {
            "Signal Classification" -> "E = ∫ |x(t)|² dt, P = lim (1/T) ∫ |x(t)|² dt"
            "Basic Signal Operations" -> "x(at - b)"
            "Convolution", "Advanced Convolution", "Numerical - Convolution" -> "y(t) = x(t) * h(t)"
            "Fourier Series" -> "x(t) = a0 + ∑ (an cos(nω0t) + bn sin(nω0t))"
            "Laplace Transform", "Laplace Transform Properties", "ROC", "Inverse Laplace Transform", "System Analysis using Laplace", "Numerical - Laplace", "Numerical - Inverse Laplace", "Numerical - Transfer Function", "Numerical - ROC" -> "X(s) = ∫ x(t) e^{-st} dt"
            "Z-Transform", "Z-Transform Properties", "ROC of Z-Transform", "Inverse Z-Transform", "Difference Equations", "Numerical - Z Transform", "Numerical - Difference Equation" -> "X(z) = ∑ x[n] z^{-n}"
            "Sampling Theorem", "Numerical - Sampling", "Sampling and Reconstruction" -> "f_s >= 2 f_max"
            "Signal Flow Graph" -> "T = ∑ P_k Δ_k / Δ"
            "State Space Representation", "State Transition Matrix", "Controllability", "Observability" -> "ẋ = Ax + Bu, y = Cx + Du"
            else -> "Standard transform and system analysis formulas"
        }

        val explanationText = "Based on $subdomain principles. Correct match: '$answer'."

        return GateQuestion(
            id = "sig_q_$id",
            subjectId = "signals_systems",
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
            shortcutTricks = "Examine boundaries, poles, symmetry rules, or standard transform patterns.",
            relatedConcepts = "Signals & Systems, $subdomain",
            difficulty = difficulty
        )
    }
}
