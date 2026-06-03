package com.example.data

object EngineeringMathematicsQuestions {

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
        val mappedSubdomain = when (subdomain) {
            "GATE Level Numerical", "Mixed GATE Level" -> {
                when {
                    qLower.contains("determinant") || qLower.contains("eigenvalues") || qLower.contains("eigenvalue") || qLower.contains("trace") || qLower.contains("matrix") || qLower.contains("rank") || qLower.contains("inverse") -> "Linear Algebra"
                    qLower.contains("∫") || qLower.contains("lim") || qLower.contains("derivative") || qLower.contains("divergence") || qLower.contains("curl") || qLower.contains("gradient") || qLower.contains("integrating") || qLower.contains("integral") || qLower.contains("maximum") -> "Calculus"
                    qLower.contains("dy/dx") || qLower.contains("solution of") -> "Differential Equations"
                    qLower.contains("modulus") || qLower.contains("principal argument") || qLower.contains("singularity") || qLower.contains("conjugate") || qLower.contains("residue") || qLower.contains("pole") || qLower.contains("j^") || qLower.contains("e^(j") -> "Complex Variables"
                    qLower.contains("probability") || qLower.contains("distribution") || qLower.contains("mean") || qLower.contains("variance") || qLower.contains("expected value") || qLower.contains("deviation") || qLower.contains("die") -> "Probability and Statistics"
                    else -> "Calculus"
                }
            }
            "Advanced Linear Algebra" -> "Linear Algebra"
            "Advanced Calculus" -> "Advanced Calculus"
            "Vector Calculus" -> "Vector Calculus"
            else -> subdomain
        }

        val (topicId, subtopicId) = when (mappedSubdomain) {
            "Linear Algebra" -> {
                val isEigen = qLower.contains("eigenvalue") || qLower.contains("eigenvalues") || qLower.contains("trace") || qLower.contains("characteristic")
                val subId = if (isEigen) "math_la_eigenvalues" else "math_la_matrix_algebra"
                Pair("math_la", subId)
            }
            "Calculus", "Advanced Calculus" -> {
                val isMulti = qLower.contains("partial") || qLower.contains("jacobian") || qLower.contains("hessian") || qLower.contains("chain rule")
                val subId = if (isMulti) "math_calc_multivariable" else "math_calc_limits_differential"
                Pair("math_calc", subId)
            }
            "Vector Calculus" -> {
                Pair("math_calc", "math_calc_vector")
            }
            "Differential Equations" -> {
                val isHigher = qLower.contains("roots") || qLower.contains("auxiliary") || qLower.contains("complementary") || qLower.contains("wronskian") || qLower.contains("cf") || qLower.contains("repeated") || qLower.contains("second order") || qLower.contains("y''") || qLower.contains("y'''")
                val subId = if (isHigher) "math_de_higher_order" else "math_de_first_order"
                Pair("math_de", subId)
            }
            "Complex Variables" -> {
                val isCauchyResidue = qLower.contains("residue") || qLower.contains("integral") || qLower.contains("cauchy") || qLower.contains("pole") || qLower.contains("poles") || qLower.contains("singularity") || qLower.contains("contour")
                val subId = if (isCauchyResidue) "math_complex_cauchy_residue" else "math_complex_algebra_analytic"
                Pair("math_complex", subId)
            }
            "Probability and Statistics" -> {
                val isDistribution = qLower.contains("pdf") || qLower.contains("cdf") || qLower.contains("normal") || qLower.contains("binomial") || qLower.contains("poisson") || qLower.contains("distribution") || qLower.contains("expected value") || qLower.contains("mean") || qLower.contains("variance") || qLower.contains("deviation") || qLower.contains("gaussian")
                val subId = if (isDistribution) "math_probability_distributions" else "math_probability_basics"
                Pair("math_probability", subId)
            }
            "Numerical Methods" -> {
                val isIntegration = qLower.contains("trapezoidal") || qLower.contains("simpson") || qLower.contains("lagrange") || qLower.contains("integration") || qLower.contains("finite difference") || qLower.contains("integral")
                val subId = if (isIntegration) "math_numerical_integration" else "math_numerical_root_finding"
                Pair("math_numerical", subId)
            }
            else -> Pair("math_calc", "math_calc_limits_differential")
        }

        val correctIdx = options.indexOf(answer).let { if (it == -1) 0 else it }
        val difficulty = when (id % 3) {
            0 -> "Easy"
            1 -> "Medium"
            else -> "Hard"
        }

        val formulas = when (mappedSubdomain) {
            "Linear Algebra" -> "det(A - λI) = 0, Trace(A) = sum(λ_i), det(A) = prod(λ_i)"
            "Calculus", "Advanced Calculus", "Vector Calculus" -> "lim_{x->0} (sin x)/x = 1, d(x^n)/dx = n x^(n-1), Grad(f) = ∇f, Div(F) = ∇•F"
            "Differential Equations" -> "Order of DE = highest derivative, Degree = power of highest derivative, dy/dx + Py = Q -> IF = e^(∫Pdx)"
            "Complex Variables" -> "j^2 = -1, |a + jb| = √(a^2 + b^2), e^(jθ) = cos θ + j sin θ, ∮f(z)dz = 2πj ∑Res"
            "Probability and Statistics" -> "P(Sure) = 1, P(Impossible) = 0, Var(C) = 0, E[X] = sum(x P(x))"
            "Numerical Methods" -> "x_{n+1} = x_n - f(x_n)/f'(x_n) [Newton-Raphson], Trapezoidal Rule = (h/2)(y0 + yn + 2∑y_mid)"
            else -> "Standard mathematical formulation"
        }

        return GateQuestion(
            id = "em_$id",
            subjectId = "engineering_math",
            topicId = topicId,
            subtopicId = subtopicId,
            year = 2020 + (id % 7),
            questionText = question,
            questionType = QuestionType.MCQ,
            options = options,
            correctOptions = listOf(correctIdx),
            correctNumericalRange = null,
            explanation = "Using core rules of $mappedSubdomain. The correct option is '$answer'.",
            formulasUsed = formulas,
            shortcutTricks = "Analyze symmetry, dimension constraints, or substitute small integer values to verify correctness.",
            relatedConcepts = "$mappedSubdomain, Engineering Mathematics",
            difficulty = difficulty
        )
    }

    private val list1 = listOf(
        q(1, "Linear Algebra", "The determinant of the matrix [[1,2],[3,4]] is", listOf("-2", "2", "10", "14"), "-2"),
        q(2, "Linear Algebra", "The rank of an identity matrix of order 4 is", listOf("0", "1", "2", "4"), "4"),
        q(3, "Linear Algebra", "If λ is an eigenvalue of matrix A, then det(A−λI) is", listOf("0", "1", "λ", "det(A)"), "0"),
        q(4, "Linear Algebra", "The trace of matrix [[2,0],[0,5]] is", listOf("7", "10", "3", "5"), "7"),
        q(5, "Linear Algebra", "For a singular matrix, determinant is", listOf("1", "-1", "0", "Undefined"), "0"),
        q(6, "Linear Algebra", "The eigenvalues of an upper triangular matrix are", listOf("Diagonal elements", "Row sums", "Column sums", "Zero"), "Diagonal elements"),
        q(7, "Linear Algebra", "If A is invertible, then det(A⁻¹) equals", listOf("det(A)", "1/det(A)", "0", "-det(A)"), "1/det(A)"),
        q(8, "Linear Algebra", "The matrix satisfying Aᵀ=A is called", listOf("Skew symmetric", "Orthogonal", "Symmetric", "Singular"), "Symmetric"),
        q(9, "Linear Algebra", "The determinant of a diagonal matrix is", listOf("Sum of diagonal elements", "Product of diagonal elements", "Zero", "One"), "Product of diagonal elements"),
        q(10, "Linear Algebra", "If A is orthogonal, then AᵀA equals", listOf("0", "A", "I", "A²"), "I"),
        q(11, "Calculus", "lim(x→0) (sin x)/x equals", listOf("0", "1", "∞", "-1"), "1"),
        q(12, "Calculus", "The derivative of x³ is", listOf("x²", "2x", "3x²", "3x"), "3x²"),
        q(13, "Calculus", "The integral of 1/x dx is", listOf("x", "ln|x|+C", "1/x²", "eˣ"), "ln|x|+C"),
        q(14, "Calculus", "The derivative of eˣ is", listOf("0", "eˣ", "xeˣ", "1"), "eˣ"),
        q(15, "Calculus", "∫₀¹ x² dx equals", listOf("1/2", "1/3", "1", "2"), "1/3"),
        q(16, "Calculus", "The derivative of ln(x) is", listOf("ln(x)", "1/x", "x", "eˣ"), "1/x"),
        q(17, "Calculus", "The maximum value of sin(x) is", listOf("0", "1", "-1", "∞"), "1"),
        q(18, "Calculus", "The partial derivative of f(x,y)=x²y+y² with respect to x is", listOf("2xy", "x²", "2y", "y²"), "2xy"),
        q(19, "Calculus", "The derivative of cos(x) is", listOf("sin(x)", "-sin(x)", "cos(x)", "-cos(x)"), "-sin(x)"),
        q(20, "Calculus", "The value of ∫₀^π sin(x)dx is", listOf("0", "1", "2", "π"), "2"),
        q(21, "Differential Equations", "The order of d²y/dx² + 3dy/dx + y = 0 is", listOf("1", "2", "3", "0"), "2"),
        q(22, "Differential Equations", "The degree of (d²y/dx²)² + dy/dx = 0 is", listOf("1", "2", "3", "4"), "2"),
        q(23, "Differential Equations", "Solution of dy/dx = 0 is", listOf("y=C", "y=x", "y=x²", "y=eˣ"), "y=C"),
        q(24, "Differential Equations", "The auxiliary equation of y''+4y=0 is", listOf("m²+4=0", "m+4=0", "m²-4=0", "m²=0"), "m²+4=0"),
        q(25, "Differential Equations", "The solution of dy/dx = y is", listOf("eˣ", "Ceˣ", "Cx", "x²"), "Ceˣ"),
        q(26, "Differential Equations", "A differential equation containing only first derivative is called", listOf("First order", "Second order", "Linear", "Homogeneous"), "First order"),
        q(27, "Differential Equations", "The integrating factor of dy/dx + y = 0 is", listOf("eˣ", "e⁻ˣ", "x", "1/x"), "eˣ"),
        q(28, "Differential Equations", "The solution of y''=0 is", listOf("C₁x+C₂", "eˣ", "sinx", "cosx"), "C₁x+C₂"),
        q(29, "Differential Equations", "For y''+y=0, the roots are", listOf("±j", "±1", "0", "2"), "±j"),
        q(30, "Differential Equations", "The equation dy/dx + P(x)y = Q(x) is called", listOf("Linear DE", "Bernoulli", "Exact", "Homogeneous"), "Linear DE"),
        q(31, "Complex Variables", "The imaginary unit j satisfies", listOf("j=1", "j²=-1", "j²=1", "j=0"), "j²=-1"),
        q(32, "Complex Variables", "Magnitude of 3+j4 is", listOf("3", "4", "5", "7"), "5"),
        q(33, "Complex Variables", "The conjugate of 2+j3 is", listOf("2-j3", "-2+j3", "-2-j3", "2+j3"), "2-j3"),
        q(34, "Complex Variables", "A function satisfying Cauchy-Riemann equations is", listOf("Analytic", "Real", "Odd", "Periodic"), "Analytic"),
        q(35, "Complex Variables", "The modulus of j is", listOf("0", "1", "-1", "j"), "1"),
        q(36, "Complex Variables", "e^(jπ) equals", listOf("1", "-1", "j", "0"), "-1"),
        q(37, "Complex Variables", "The real part of 5-j2 is", listOf("-2", "2", "5", "7"), "5"),
        q(38, "Complex Variables", "The imaginary part of 5-j2 is", listOf("-2", "2", "5", "7"), "-2"),
        q(39, "Complex Variables", "The argument of 1+j is", listOf("π/4", "π/2", "π", "0"), "π/4"),
        q(40, "Complex Variables", "The residue theorem is used for", listOf("Contour integration", "Differentiation", "Matrix inversion", "Probability"), "Contour integration"),
        q(41, "Probability and Statistics", "Probability of a sure event is", listOf("0", "0.5", "1", "∞"), "1"),
        q(42, "Probability and Statistics", "Probability of an impossible event is", listOf("0", "1", "0.5", "-1"), "0"),
        q(43, "Probability and Statistics", "Mean of a constant c is", listOf("0", "1", "c", "∞"), "c"),
        q(44, "Probability and Statistics", "Variance of a constant is", listOf("0", "1", "c", "∞"), "0"),
        q(45, "Probability and Statistics", "The sum of all probabilities in a sample space is", listOf("0", "1", "2", "∞"), "1"),
        q(46, "Probability and Statistics", "The standard deviation is the square root of", listOf("Mean", "Variance", "Median", "Mode"), "Variance"),
        q(47, "Probability and Statistics", "Bayes theorem is related to", listOf("Conditional probability", "Matrices", "Differentiation", "Integration"), "Conditional probability"),
        q(48, "Probability and Statistics", "For a fair coin, probability of head is", listOf("0", "1", "0.5", "0.25"), "0.5"),
        q(49, "Probability and Statistics", "Expected value of a random variable is also called", listOf("Mean", "Variance", "Mode", "Median"), "Mean"),
        q(50, "Probability and Statistics", "A random variable taking countable values is", listOf("Discrete", "Continuous", "Uniform", "Gaussian"), "Discrete")
    )

    private val list2 = listOf(
        q(51, "Linear Algebra", "If the eigenvalues of a 2×2 matrix are 3 and 5, then its trace is", listOf("8", "15", "2", "0"), "8"),
        q(52, "Linear Algebra", "If the eigenvalues of a matrix are 2 and 4, then its determinant is", listOf("6", "8", "4", "2"), "8"),
        q(53, "Linear Algebra", "The rank of a matrix cannot exceed", listOf("Number of rows", "Number of columns", "Minimum of rows and columns", "Maximum of rows and columns"), "Minimum of rows and columns"),
        q(54, "Linear Algebra", "A matrix whose determinant is non-zero is called", listOf("Singular", "Orthogonal", "Non-singular", "Symmetric"), "Non-singular"),
        q(55, "Linear Algebra", "For any square matrix A, det(Aᵀ) is equal to", listOf("0", "det(A)", "-det(A)", "1"), "det(A)"),
        q(56, "Linear Algebra", "If A² = A, then A is called", listOf("Nilpotent", "Idempotent", "Orthogonal", "Singular"), "Idempotent"),
        q(57, "Linear Algebra", "If A² = 0, then A is", listOf("Orthogonal", "Nilpotent", "Identity", "Diagonal"), "Nilpotent"),
        q(58, "Linear Algebra", "The inverse of an orthogonal matrix A is", listOf("A", "A²", "Aᵀ", "-A"), "Aᵀ"),
        q(59, "Linear Algebra", "A matrix equal to its negative transpose is called", listOf("Symmetric", "Orthogonal", "Skew-symmetric", "Singular"), "Skew-symmetric"),
        q(60, "Linear Algebra", "The characteristic equation of matrix A is obtained from", listOf("det(A+λI)=0", "det(A−λI)=0", "A=0", "A²=0"), "det(A−λI)=0"),
        q(61, "Calculus", "The derivative of tan(x) is", listOf("sec²x", "cosec²x", "tan²x", "sinx"), "sec²x"),
        q(62, "Calculus", "The integral of cos(x) dx is", listOf("sin(x)+C", "-sin(x)+C", "cos(x)+C", "-cos(x)+C"), "sin(x)+C"),
        q(63, "Calculus", "The derivative of xⁿ is", listOf("nxⁿ⁻¹", "xⁿ⁻¹", "n+x", "nx"), "nxⁿ⁻¹"),
        q(64, "Calculus", "The value of lim(x→∞) 1/x is", listOf("0", "1", "∞", "-∞"), "0"),
        q(65, "Calculus", "∫eˣ dx equals", listOf("xeˣ", "eˣ+C", "lnx", "1/eˣ"), "eˣ+C"),
        q(66, "Calculus", "The second derivative of x³ is", listOf("3x²", "6x", "6", "x"), "6x"),
        q(67, "Calculus", "The gradient of a scalar field is a", listOf("Scalar", "Vector", "Matrix", "Tensor"), "Vector"),
        q(68, "Calculus", "At a maximum point of f(x), the first derivative is", listOf("1", "0", "-1", "∞"), "0"),
        q(69, "Calculus", "The Maclaurin series is a Taylor series expanded about", listOf("x=1", "x=∞", "x=0", "x=-1"), "x=0"),
        q(70, "Calculus", "The derivative of ln(sin x) is", listOf("tan x", "cot x", "sec x", "cosec x"), "cot x"),
        q(71, "Differential Equations", "The complementary function depends on", listOf("Homogeneous equation", "Particular solution", "Boundary condition", "Initial condition"), "Homogeneous equation"),
        q(72, "Differential Equations", "The solution of y' + 2y = 0 is", listOf("Ce²ˣ", "Ce⁻²ˣ", "2Ceˣ", "Cx²"), "Ce⁻²ˣ"),
        q(73, "Differential Equations", "The roots of m²−5m+6=0 are", listOf("2,3", "1,6", "-2,-3", "0,6"), "2,3"),
        q(74, "Differential Equations", "The Wronskian is used to test", listOf("Continuity", "Linear independence", "Convergence", "Orthogonality"), "Linear independence"),
        q(75, "Differential Equations", "The integrating factor of dy/dx + 3y = x is", listOf("e³ˣ", "e⁻³ˣ", "3eˣ", "x³"), "e³ˣ"),
        q(76, "Differential Equations", "The equation y''+9y=0 has roots", listOf("±3j", "±3", "0,9", "1,9"), "±3j"),
        q(77, "Differential Equations", "A homogeneous first-order differential equation can be solved using", listOf("y=vx", "Laplace only", "Fourier only", "Newton method"), "y=vx"),
        q(78, "Differential Equations", "The order of y''' + y'' + y = 0 is", listOf("1", "2", "3", "4"), "3"),
        q(79, "Differential Equations", "The solution of dy/dx = x is", listOf("x²/2 + C", "2x + C", "lnx + C", "eˣ"), "x²/2 + C"),
        q(80, "Differential Equations", "For repeated roots m=a, the CF contains", listOf("e^(ax)", "xe^(ax)", "Both e^(ax) and xe^(ax)", "sin(ax)"), "Both e^(ax) and xe^(ax)"),
        q(81, "Complex Variables", "The modulus of 1+j is", listOf("1", "√2", "2", "0"), "√2"),
        q(82, "Complex Variables", "The polar form of a complex number is represented using", listOf("r∠θ", "x+y", "lnz", "det(z)"), "r∠θ"),
        q(83, "Complex Variables", "The argument of -1 is", listOf("0", "π", "π/2", "-π/2"), "π"),
        q(84, "Complex Variables", "If z=a+jb, then Re(z) equals", listOf("a", "b", "ab", "j"), "a"),
        q(85, "Complex Variables", "The function f(z)=z² is", listOf("Analytic", "Non-analytic", "Constant", "Undefined"), "Analytic"),
        q(86, "Complex Variables", "The residue at a simple pole is the coefficient of", listOf("(z-a)^(-1)", "(z-a)", "(z-a)^2", "z"), "(z-a)^(-1)"),
        q(87, "Complex Variables", "Euler's formula is", listOf("e^(jθ)=cosθ+jsinθ", "e^θ=sinθ", "j²=1", "cosθ=j"), "e^(jθ)=cosθ+jsinθ"),
        q(88, "Complex Variables", "The principal value of arg(j) is", listOf("0", "π/2", "π", "3π/2"), "π/2"),
        q(89, "Complex Variables", "The conjugate of z=a+jb is", listOf("a-jb", "-a+jb", "-a-jb", "jb-a"), "a-jb"),
        q(90, "Complex Variables", "The imaginary part of 7+j9 is", listOf("7", "9", "16", "-9"), "9"),
        q(91, "Probability and Statistics", "The mean of a fair die outcome is", listOf("3.5", "3", "4", "6"), "3.5"),
        q(92, "Probability and Statistics", "If events A and B are independent, P(A∩B)=", listOf("P(A)+P(B)", "P(A)P(B)", "1", "0"), "P(A)P(B)"),
        q(93, "Probability and Statistics", "The variance of a Bernoulli variable with parameter p is", listOf("p", "1-p", "p(1-p)", "p²"), "p(1-p)"),
        q(94, "Probability and Statistics", "A Gaussian distribution is completely specified by", listOf("Mean and variance", "Mode only", "Median only", "Range only"), "Mean and variance"),
        q(95, "Probability and Statistics", "The total area under a PDF curve is", listOf("0", "1", "∞", "0.5"), "1"),
        q(96, "Probability and Statistics", "For a standard normal distribution, mean equals", listOf("0", "1", "-1", "0.5"), "0"),
        q(97, "Probability and Statistics", "For a standard normal distribution, variance equals", listOf("0", "1", "2", "0.5"), "1"),
        q(98, "Probability and Statistics", "The cumulative distribution function is generally denoted by", listOf("F(x)", "f(x)", "P(x)", "M(x)"), "F(x)"),
        q(99, "Probability and Statistics", "If P(A)=0.4 and P(B)=0.5 for independent events, then P(A∩B) is", listOf("0.9", "0.2", "0.4", "0.5"), "0.2"),
        q(100, "Probability and Statistics", "The expected value operator is denoted by", listOf("E[ ]", "V[ ]", "P[ ]", "F[ ]"), "E[ ]")
    )

    private val list3 = listOf(
        q(101, "Numerical Methods", "The Newton-Raphson method is primarily used for", listOf("Integration", "Root finding", "Differentiation", "Interpolation"), "Root finding"),
        q(102, "Numerical Methods", "The iteration formula in Newton-Raphson method is", listOf("xₙ₊₁=xₙ−f(xₙ)/f'(xₙ)", "xₙ₊₁=f(xₙ)", "xₙ₊₁=xₙ+f(xₙ)", "xₙ₊₁=xₙ²"), "xₙ₊₁=xₙ−f(xₙ)/f'(xₙ)"),
        q(103, "Numerical Methods", "The bisection method requires", listOf("Derivative", "Initial interval with sign change", "Matrix inverse", "Taylor series"), "Initial interval with sign change"),
        q(104, "Numerical Methods", "The order of convergence of Newton-Raphson method is", listOf("1", "2", "3", "4"), "2"),
        q(105, "Numerical Methods", "The trapezoidal rule is used for", listOf("Root finding", "Numerical integration", "Interpolation", "Matrix factorization"), "Numerical integration"),
        q(106, "Numerical Methods", "Simpson's 1/3 rule requires the number of intervals to be", listOf("Odd", "Even", "Prime", "Zero"), "Even"),
        q(107, "Numerical Methods", "The error in trapezoidal rule is proportional to", listOf("h²", "h³", "h⁴", "h"), "h²"),
        q(108, "Numerical Methods", "Interpolation estimates values", listOf("Outside given range", "Within given range", "At infinity", "Only at endpoints"), "Within given range"),
        q(109, "Numerical Methods", "Lagrange interpolation is based on", listOf("Polynomial fitting", "Fourier series", "Laplace transform", "Matrix inversion"), "Polynomial fitting"),
        q(110, "Numerical Methods", "The secant method does not require", listOf("Initial guesses", "Function values", "Derivative", "Iterations"), "Derivative"),
        q(111, "Vector Calculus", "The gradient of a scalar field is a", listOf("Scalar", "Vector", "Matrix", "Tensor"), "Vector"),
        q(112, "Vector Calculus", "The divergence of a vector field is a", listOf("Vector", "Scalar", "Tensor", "Matrix"), "Scalar"),
        q(113, "Vector Calculus", "The curl of a vector field is a", listOf("Scalar", "Vector", "Constant", "Matrix"), "Vector"),
        q(114, "Vector Calculus", "The divergence of ∇×A is always", listOf("1", "-1", "0", "∞"), "0"),
        q(115, "Vector Calculus", "The curl of ∇φ is always", listOf("0", "1", "-1", "φ"), "0"),
        q(116, "Vector Calculus", "Gauss divergence theorem relates volume integral to", listOf("Line integral", "Surface integral", "Double integral", "Series"), "Surface integral"),
        q(117, "Vector Calculus", "Stokes theorem relates surface integral to", listOf("Line integral", "Volume integral", "Triple integral", "Determinant"), "Line integral"),
        q(118, "Vector Calculus", "The operator ∇ is called", listOf("Laplacian", "Del", "Gradient", "Curl"), "Del"),
        q(119, "Vector Calculus", "The Laplacian operator is denoted by", listOf("∇", "∇²", "∂", "Δx"), "∇²"),
        q(120, "Vector Calculus", "A vector field with zero curl is called", listOf("Irrotational", "Solenoidal", "Orthogonal", "Singular"), "Irrotational"),
        q(121, "Advanced Linear Algebra", "If A has eigenvalues 1, 2, and 3, then det(A) equals", listOf("5", "6", "9", "1"), "6"),
        q(122, "Advanced Linear Algebra", "The sum of eigenvalues of a matrix equals", listOf("Determinant", "Rank", "Trace", "Norm"), "Trace"),
        q(123, "Advanced Linear Algebra", "A matrix is diagonalizable if it has", listOf("Enough linearly independent eigenvectors", "Zero determinant", "Rank 1", "Repeated rows"), "Enough linearly independent eigenvectors"),
        q(124, "Advanced Linear Algebra", "The transpose of a product AB is", listOf("AᵀBᵀ", "BᵀAᵀ", "AB", "BA"), "BᵀAᵀ"),
        q(125, "Advanced Linear Algebra", "For a Hermitian matrix, eigenvalues are", listOf("Imaginary", "Complex", "Real", "Zero"), "Real"),
        q(126, "Advanced Linear Algebra", "If A is orthogonal, then |det(A)| equals", listOf("0", "1", "2", "∞"), "1"),
        q(127, "Advanced Linear Algebra", "The rank of a full-rank 5×5 matrix is", listOf("1", "3", "4", "5"), "5"),
        q(128, "Advanced Linear Algebra", "The nullity of an invertible matrix is", listOf("0", "1", "n", "∞"), "0"),
        q(129, "Advanced Linear Algebra", "The characteristic polynomial of a 2×2 matrix is of degree", listOf("1", "2", "3", "4"), "2"),
        q(130, "Advanced Linear Algebra", "The inverse of a diagonal matrix is obtained by", listOf("Transposing", "Reciprocating diagonal elements", "Negating entries", "Squaring entries"), "Reciprocating diagonal elements"),
        q(131, "Advanced Calculus", "The Taylor series of eˣ about x=0 begins with", listOf("1+x+x²/2!", "x+x²", "1+x²", "eˣ"), "1+x+x²/2!"),
        q(132, "Advanced Calculus", "The radius of convergence of the series for eˣ is", listOf("0", "1", "∞", "2"), "∞"),
        q(133, "Advanced Calculus", "If f'(x)=0 at x=a, then x=a is a", listOf("Critical point", "Asymptote", "Pole", "Singularity"), "Critical point"),
        q(134, "Advanced Calculus", "The Jacobian is used in", listOf("Coordinate transformation", "Matrix inversion", "Probability only", "Interpolation"), "Coordinate transformation"),
        q(135, "Advanced Calculus", "The derivative of sin⁻¹(x) is", listOf("1/√(1−x²)", "√(1−x²)", "1/(1+x²)", "cosx"), "1/√(1−x²)"),
        q(136, "Advanced Calculus", "The derivative of tan⁻¹(x) is", listOf("1/(1+x²)", "1/(1−x²)", "sec²x", "cosec²x"), "1/(1+x²)"),
        q(137, "Advanced Calculus", "A function is continuous if", listOf("Limit exists and equals function value", "Derivative exists", "Integral exists", "It is bounded"), "Limit exists and equals function value"),
        q(138, "Advanced Calculus", "The integral ∫₀¹ x dx equals", listOf("1/2", "1", "2", "0"), "1/2"),
        q(139, "Advanced Calculus", "The Hessian matrix contains", listOf("First derivatives", "Second-order partial derivatives", "Eigenvalues", "Integrals"), "Second-order partial derivatives"),
        q(140, "Advanced Calculus", "The chain rule is used for", listOf("Composite functions", "Matrices", "Series only", "Probability"), "Composite functions"),
        q(141, "GATE Level Numerical", "If A=[[2,0],[0,3]], the determinant of A is", listOf("5", "6", "1", "0"), "6"),
        q(142, "GATE Level Numerical", "The eigenvalues of [[4,0],[0,7]] are", listOf("4,7", "11,0", "28,1", "0,0"), "4,7"),
        q(143, "GATE Level Numerical", "The value of ∫₀¹ 2x dx is", listOf("1", "2", "0.5", "4"), "1"),
        q(144, "GATE Level Numerical", "If P(A)=0.6 and P(B)=0.5 are independent, P(A∩B) is", listOf("0.3", "1.1", "0.6", "0.5"), "0.3"),
        q(145, "GATE Level Numerical", "The solution of dy/dx = 3 is", listOf("3x+C", "x³+C", "e³ˣ", "3+C"), "3x+C"),
        q(146, "GATE Level Numerical", "The modulus of 3+j4 is", listOf("3", "4", "5", "7"), "5"),
        q(147, "GATE Level Numerical", "The divergence of F=xi+yj+zk is", listOf("1", "2", "3", "0"), "3"),
        q(148, "GATE Level Numerical", "The trace of [[5,0],[0,2]] is", listOf("10", "7", "5", "2"), "7"),
        q(149, "GATE Level Numerical", "The value of lim(x→0)(1−cosx)/x² is", listOf("0", "1/2", "1", "2"), "1/2"),
        q(150, "GATE Level Numerical", "For a standard normal distribution, mean and variance are", listOf("0,1", "1,0", "1,1", "0,0"), "0,1")
    )

    private val list4 = listOf(
        q(151, "Complex Variables", "The value of j^4 is", listOf("1", "-1", "j", "-j"), "1"),
        q(152, "Complex Variables", "The principal argument of (−j) is", listOf("−π/2", "π/2", "π", "0"), "−π/2"),
        q(153, "Complex Variables", "The singularity of f(z)=1/(z−2) is located at", listOf("0", "1", "2", "-2"), "2"),
        q(154, "Complex Variables", "The residue of 1/(z−1) at z=1 is", listOf("0", "1", "-1", "∞"), "1"),
        q(155, "Complex Variables", "If z = 1+j√3, then |z| equals", listOf("1", "2", "√3", "3"), "2"),
        q(156, "Complex Variables", "The function f(z)=constant is", listOf("Analytic", "Non-analytic", "Singular", "Periodic"), "Analytic"),
        q(157, "Complex Variables", "e^(jπ/2) equals", listOf("1", "-1", "j", "-j"), "j"),
        q(158, "Complex Variables", "The conjugate of (4−j5) is", listOf("4+j5", "-4+j5", "-4-j5", "5+j4"), "4+j5"),
        q(159, "Complex Variables", "The real part of e^(jθ) is", listOf("sinθ", "cosθ", "tanθ", "1"), "cosθ"),
        q(160, "Complex Variables", "A pole of order 2 is called a", listOf("Simple pole", "Double pole", "Removable singularity", "Essential singularity"), "Double pole"),
        q(161, "Probability and Statistics", "For mutually exclusive events A and B, P(A∩B) is", listOf("0", "1", "P(A)", "P(B)"), "0"),
        q(162, "Probability and Statistics", "The mean of a Poisson distribution is λ. Its variance is", listOf("1", "λ²", "λ", "√λ"), "λ"),
        q(163, "Probability and Statistics", "The probability of getting at least one head in two coin tosses is", listOf("1/4", "1/2", "3/4", "1"), "3/4"),
        q(164, "Probability and Statistics", "The variance of a standard normal random variable is", listOf("0", "1", "2", "∞"), "1"),
        q(165, "Probability and Statistics", "The median of a symmetric normal distribution equals its", listOf("Variance", "Mean", "Range", "Skewness"), "Mean"),
        q(166, "Probability and Statistics", "The PDF of a continuous random variable is always", listOf("Negative", "Non-negative", "Integer", "Complex"), "Non-negative"),
        q(167, "Probability and Statistics", "The expected value of a constant c is", listOf("0", "1", "c", "c²"), "c"),
        q(168, "Probability and Statistics", "The binomial distribution depends on", listOf("n and p", "μ and σ", "λ only", "Mean only"), "n and p"),
        q(169, "Probability and Statistics", "The standard deviation is always", listOf("Negative", "Non-negative", "Complex", "Imaginary"), "Non-negative"),
        q(170, "Probability and Statistics", "If P(A)=1, then A is a", listOf("Impossible event", "Sure event", "Random event", "Mutually exclusive event"), "Sure event"),
        q(171, "Numerical Methods", "The bisection method converges", listOf("Linearly", "Quadratically", "Cubically", "Exponentially"), "Linearly"),
        q(172, "Numerical Methods", "The Newton-Raphson method requires evaluation of", listOf("Integral", "Derivative", "Fourier transform", "Eigenvalue"), "Derivative"),
        q(173, "Numerical Methods", "Simpson's 1/3 rule is exact for polynomials up to degree", listOf("1", "2", "3", "4"), "3"),
        q(174, "Numerical Methods", "Interpolation estimates values", listOf("Inside the data range", "Outside the data range", "At infinity", "Only at origin"), "Inside the data range"),
        q(175, "Numerical Methods", "The false-position method is also known as", listOf("Regula-Falsi", "Euler method", "Runge-Kutta", "Gauss-Seidel"), "Regula-Falsi"),
        q(176, "Numerical Methods", "The secant method uses", listOf("One initial guess", "Two initial guesses", "Three initial guesses", "No initial guess"), "Two initial guesses"),
        q(177, "Numerical Methods", "The trapezoidal rule approximates area using", listOf("Rectangles", "Trapezoids", "Triangles", "Circles"), "Trapezoids"),
        q(178, "Numerical Methods", "The convergence rate of Newton-Raphson is", listOf("Linear", "Quadratic", "Cubic", "Logarithmic"), "Quadratic"),
        q(179, "Numerical Methods", "Lagrange interpolation polynomial passes through", listOf("All given data points", "Only first point", "Only last point", "Midpoint only"), "All given data points"),
        q(180, "Numerical Methods", "The finite difference method is commonly used for", listOf("Differential equations", "Complex analysis", "Probability", "Vector spaces"), "Differential equations"),
        q(181, "Vector Calculus", "A vector field with zero divergence is called", listOf("Irrotational", "Solenoidal", "Conservative", "Orthogonal"), "Solenoidal"),
        q(182, "Vector Calculus", "The divergence of F = xi + yj + zk is", listOf("1", "2", "3", "0"), "3"),
        q(183, "Vector Calculus", "The curl of F = xi + yj + zk is", listOf("0", "1", "2", "3"), "0"),
        q(184, "Vector Calculus", "The Laplacian of a scalar field is a", listOf("Scalar", "Vector", "Matrix", "Tensor"), "Scalar"),
        q(185, "Vector Calculus", "Gauss theorem converts a volume integral into a", listOf("Line integral", "Surface integral", "Double integral", "Series"), "Surface integral"),
        q(186, "Vector Calculus", "Stokes theorem relates circulation around a closed path to", listOf("Surface integral of curl", "Volume integral", "Gradient", "Divergence"), "Surface integral of curl"),
        q(187, "Vector Calculus", "The gradient points in the direction of", listOf("Maximum decrease", "Maximum increase", "Zero change", "Average change"), "Maximum increase"),
        q(188, "Vector Calculus", "The divergence of curl(F) is", listOf("1", "-1", "0", "∞"), "0"),
        q(189, "Vector Calculus", "The curl of gradient(φ) is", listOf("1", "-1", "0", "φ"), "0"),
        q(190, "Vector Calculus", "The unit normal vector is used in", listOf("Surface integrals", "Probability", "Matrices", "Interpolation"), "Surface integrals"),
        q(191, "Mixed GATE Level", "The determinant of a triangular matrix equals", listOf("Sum of diagonal elements", "Product of diagonal elements", "Zero", "Rank"), "Product of diagonal elements"),
        q(192, "Mixed GATE Level", "The rank of a 3×3 identity matrix is", listOf("1", "2", "3", "0"), "3"),
        q(193, "Mixed GATE Level", "The value of ∫₀¹ x² dx is", listOf("1/2", "1/3", "1/4", "1"), "1/3"),
        q(194, "Mixed GATE Level", "The solution of dy/dx = 2x is", listOf("x²+C", "2x+C", "x+C", "e^(2x)"), "x²+C"),
        q(195, "Mixed GATE Level", "If eigenvalues are 2 and 5, the trace is", listOf("7", "10", "3", "25"), "7"),
        q(196, "Mixed GATE Level", "The probability of getting an even number on a fair die is", listOf("1/6", "1/3", "1/2", "2/3"), "1/2"),
        q(197, "Mixed GATE Level", "The modulus of (−3+j4) is", listOf("3", "4", "5", "7"), "5"),
        q(198, "Mixed GATE Level", "The derivative of e^(2x) is", listOf("e^(2x)", "2e^(2x)", "x e^(2x)", "2x"), "2e^(2x)"),
        q(199, "Mixed GATE Level", "The inverse of a non-singular matrix always", listOf("Exists", "Does not exist", "Is zero", "Is singular"), "Exists"),
        q(200, "Mixed GATE Level", "The value of lim(x→0) (sin x)/x is", listOf("0", "1", "∞", "-1"), "1")
    )
}
