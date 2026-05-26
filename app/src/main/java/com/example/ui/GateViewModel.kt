package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import org.json.JSONArray

class GateViewModel(application: Application) : AndroidViewModel(application) {

    val repository = GateRepository(application)
    private var timerJob: Job? = null
    private var chatMessageJob: Job? = null

    // --- Core State Flows from Database ---
    val userStats: StateFlow<UserStatsEntity> = repository.userStats
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UserStatsEntity())

    val allProgress: StateFlow<List<SubtopicProgressEntity>> = repository.allProgress
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val bookmarkedQuestions: StateFlow<List<SavedQuestionEntity>> = repository.bookmarkedQuestions
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val mistakeNotebookQuestions: StateFlow<List<SavedQuestionEntity>> = repository.mistakeNotebookQuestions
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // --- Interactive State for Active Studies ---
    private val _selectedSubtopic = MutableStateFlow<Subtopic?>(null)
    val selectedSubtopic: StateFlow<Subtopic?> = _selectedSubtopic.asStateFlow()

    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex: StateFlow<Int> = _currentQuestionIndex.asStateFlow()

    // Question Attempt Tracking (MCQ, MSQ, NAT)
    private val _selectedMcqOption = MutableStateFlow<Int?>(null)
    val selectedMcqOption: StateFlow<Int?> = _selectedMcqOption.asStateFlow()

    private val _selectedMsqOptions = MutableStateFlow<Set<Int>>(emptySet())
    val selectedMsqOptions: StateFlow<Set<Int>> = _selectedMsqOptions.asStateFlow()

    private val _natAnswerInput = MutableStateFlow("")
    val natAnswerInput: StateFlow<String> = _natAnswerInput.asStateFlow()

    private val _isAnswerSubmitted = MutableStateFlow(false)
    val isAnswerSubmitted: StateFlow<Boolean> = _isAnswerSubmitted.asStateFlow()

    private val _isAnswerCorrect = MutableStateFlow<Boolean?>(null)
    val isAnswerCorrect: StateFlow<Boolean?> = _isAnswerCorrect.asStateFlow()

    private val _aiResultFeedback = MutableStateFlow<String>("")
    val aiResultFeedback: StateFlow<String> = _aiResultFeedback.asStateFlow()

    private val _isLoadingAiFeedback = MutableStateFlow(false)
    val isLoadingAiFeedback: StateFlow<Boolean> = _isLoadingAiFeedback.asStateFlow()

    // --- AI Doubt Solver Chat ---
    private val _chatMessages = MutableStateFlow<List<DoubtMessageEntity>>(emptyList())
    val chatMessages: StateFlow<List<DoubtMessageEntity>> = _chatMessages.asStateFlow()

    private val _isChatLoading = MutableStateFlow(false)
    val isChatLoading: StateFlow<Boolean> = _isChatLoading.asStateFlow()

    // --- CBT Mock Test Controller State ---
    private val _cbtTimerSeconds = MutableStateFlow(1800) // 30-minute default quiz
    val cbtTimerSeconds: StateFlow<Int> = _cbtTimerSeconds.asStateFlow()

    private val _isCbtRunning = MutableStateFlow(false)
    val isCbtRunning: StateFlow<Boolean> = _isCbtRunning.asStateFlow()

    private val _cbtQuestions = MutableStateFlow<List<GateQuestion>>(emptyList())
    val cbtQuestions: StateFlow<List<GateQuestion>> = _cbtQuestions.asStateFlow()

    private val _cbtUserAnswers = MutableStateFlow<Map<String, String>>(emptyMap()) // map of questionId to representation
    val cbtUserAnswers: StateFlow<Map<String, String>> = _cbtUserAnswers.asStateFlow()

    private val _cbtFinalScore = MutableStateFlow<Double?>(null)
    val cbtFinalScore: StateFlow<Double?> = _cbtFinalScore.asStateFlow()

    private val _cbtSelectedSubjectId = MutableStateFlow<String?>(null)
    val cbtSelectedSubjectId: StateFlow<String?> = _cbtSelectedSubjectId.asStateFlow()

    private val _cbtSubjectScores = MutableStateFlow<List<CbtSubjectScore>>(emptyList())
    val cbtSubjectScores: StateFlow<List<CbtSubjectScore>> = _cbtSubjectScores.asStateFlow()

    // --- Dynamic Custom Question Generator State ---
    private val _customQuestions = MutableStateFlow<Map<String, List<GateQuestion>>>(emptyMap())
    val customQuestions: StateFlow<Map<String, List<GateQuestion>>> = _customQuestions.asStateFlow()

    private val _isGeneratingQuestions = MutableStateFlow(false)
    val isGeneratingQuestions: StateFlow<Boolean> = _isGeneratingQuestions.asStateFlow()

    fun generateCustomQuestions(subjectId: String, topicId: String, subtopicId: String, subtopicName: String) {
        if (_isGeneratingQuestions.value) return
        _isGeneratingQuestions.value = true
        viewModelScope.launch {
            val questions = GeminiService.generateQuestionsForSubtopic(subjectId, topicId, subtopicId, subtopicName)
            if (questions.isNotEmpty()) {
                val current = _customQuestions.value.toMutableMap()
                current[subtopicId] = questions
                _customQuestions.value = current
                _currentQuestionIndex.value = 0
                resetQuestionInputs()
            }
            _isGeneratingQuestions.value = false
        }
    }

    init {
        // Daily login/active triggers
        viewModelScope.launch {
            repository.checkAndUpdateStreak()
        }
        // Background active study timer to increment study minutes
        viewModelScope.launch {
            while (true) {
                kotlinx.coroutines.delay(60000)
                repository.addTimeSpent(1)
            }
        }
    }

    // --- Customizable Attempt Configuration State ---
    private val _isConfiguringAttempt = MutableStateFlow(true)
    val isConfiguringAttempt: StateFlow<Boolean> = _isConfiguringAttempt.asStateFlow()

    private val _customAttemptQuestions = MutableStateFlow<List<GateQuestion>>(emptyList())
    val customAttemptQuestions: StateFlow<List<GateQuestion>> = _customAttemptQuestions.asStateFlow()

    fun startCustomAttempt(questions: List<GateQuestion>) {
        _customAttemptQuestions.value = questions
        _isConfiguringAttempt.value = false
        _currentQuestionIndex.value = 0
        resetQuestionInputs()
    }

    fun exitAttemptConfiguration() {
        _isConfiguringAttempt.value = true
    }

    fun getTopicsForSubject(subjectId: String): List<Topic> {
        return repository.getSubjectById(subjectId)?.topics ?: emptyList()
    }

    fun getOrGenerateQuestionsForSubtopic(sub: Subtopic): List<GateQuestion> {
        val totalPreset = sub.pyqs + sub.practiceQuestions + sub.mockQuiz
        val custom = _customQuestions.value[sub.id] ?: emptyList()
        if (custom.isEmpty() && totalPreset.size < 100) {
            val neededCount = 100 - totalPreset.size
            val generated = ProceduralQuestionGenerator.generateQuestions(
                subjectId = sub.subjectId,
                topicId = sub.topicId,
                subtopicId = sub.id,
                subtopicName = sub.name,
                count = neededCount
            )
            val current = _customQuestions.value.toMutableMap()
            current[sub.id] = generated
            _customQuestions.value = current
            return totalPreset + generated
        }
        return totalPreset + custom
    }

    // --- Setup Navigation & Subject Contexts ---
    fun selectSubtopic(subjectId: String, topicId: String, subtopicId: String) {
        val sub = repository.getSubtopicById(subjectId, topicId, subtopicId)
        _selectedSubtopic.value = sub
        _currentQuestionIndex.value = 0
        _isConfiguringAttempt.value = true
        resetQuestionInputs()

        chatMessageJob?.cancel()
        // Fetch persists chat messages for this subtopic
        if (sub != null) {
            chatMessageJob = viewModelScope.launch {
                repository.getMessagesForSubtopic(sub.id).collect {
                    _chatMessages.value = it
                }
            }

            // Immediately and automatically generate enough questions to reach 100 for all subtopics
            val totalPreset = sub.pyqs.size + sub.practiceQuestions.size + sub.mockQuiz.size
            val custom = _customQuestions.value[sub.id] ?: emptyList()
            val targetCount = 100
            if (custom.isEmpty() && totalPreset < targetCount) {
                val neededCount = targetCount - totalPreset
                val generated = ProceduralQuestionGenerator.generateQuestions(
                    subjectId = subjectId,
                    topicId = topicId,
                    subtopicId = sub.id,
                    subtopicName = sub.name,
                    count = neededCount
                )
                val current = _customQuestions.value.toMutableMap()
                current[sub.id] = generated
                _customQuestions.value = current
            }
        }
    }

    // --- Question Attempt Actions ---
    fun selectMcqOption(index: Int) {
        _selectedMcqOption.value = index
    }

    fun toggleMsqOption(index: Int) {
        val current = _selectedMsqOptions.value
        if (current.contains(index)) {
            _selectedMsqOptions.value = current - index
        } else {
            _selectedMsqOptions.value = current + index
        }
    }

    fun setNatAnswerInput(value: String) {
        _natAnswerInput.value = value
    }

    fun submitAnswer(question: GateQuestion) {
        if (_isAnswerSubmitted.value) return

        var correct = false
        var answerRep = ""

        when (question.questionType) {
            QuestionType.MCQ -> {
                val selected = _selectedMcqOption.value
                val correctIndex = question.correctOptions?.firstOrNull() ?: -1
                correct = selected == correctIndex
                answerRep = selected?.let { question.options?.getOrNull(it) } ?: "No Answer"
            }
            QuestionType.MSQ -> {
                val selectedSet = _selectedMsqOptions.value
                val correctSet = question.correctOptions?.toSet() ?: emptySet()
                correct = selectedSet == correctSet
                answerRep = selectedSet.joinToString { question.options?.getOrNull(it) ?: "" }
            }
            QuestionType.NAT -> {
                val inp = _natAnswerInput.value.toDoubleOrNull()
                val range = question.correctNumericalRange
                correct = inp != null && range != null && inp in range
                answerRep = _natAnswerInput.value
            }
        }

        _isAnswerCorrect.value = correct
        _isAnswerSubmitted.value = true

        viewModelScope.launch {
            // Reward XP for correct, add wrong attempt to mistake notebook!
            if (correct) {
                repository.addXp(20)
                repository.updateProgress(
                    subtopicId = question.subtopicId,
                    subjectId = question.subjectId,
                    topicId = question.topicId,
                    isCompleted = true,
                    scorePercent = 100,
                    timeSpentAddSeconds = 30,
                    questionsSolvedAdd = 1
                )
            } else {
                repository.addWrongAnswerToMistakes(question, "User provided incorrect answer: $answerRep")
                repository.updateProgress(
                    subtopicId = question.subtopicId,
                    subjectId = question.subjectId,
                    topicId = question.topicId,
                    isCompleted = false,
                    scorePercent = 0,
                    timeSpentAddSeconds = 30,
                    questionsSolvedAdd = 1
                )
            }

            // Fetch AI analyzer feedback on wrong or correct answers!
            _isLoadingAiFeedback.value = true
            val correctRep = when (question.questionType) {
                QuestionType.MCQ -> question.correctOptions?.firstOrNull()?.let { question.options?.getOrNull(it) } ?: ""
                QuestionType.MSQ -> question.correctOptions?.joinToString { question.options?.getOrNull(it) ?: "" } ?: ""
                QuestionType.NAT -> question.correctNumericalRange?.toString() ?: ""
            }
            val explanationFeedback = GeminiService.analyzeAttemptResult(
                questionText = question.questionText,
                correctAnswer = correctRep,
                userAnswer = answerRep,
                isCorrect = correct,
                explanation = question.explanation
            )
            _aiResultFeedback.value = explanationFeedback
            _isLoadingAiFeedback.value = false
        }
    }

    fun nextQuestion(questionsList: List<GateQuestion>) {
        if (_currentQuestionIndex.value < questionsList.size - 1) {
            _currentQuestionIndex.value += 1
            resetQuestionInputs()
        }
    }

    fun prevQuestion() {
        if (_currentQuestionIndex.value > 0) {
            _currentQuestionIndex.value -= 1
            resetQuestionInputs()
        }
    }

    private fun resetQuestionInputs() {
        _selectedMcqOption.value = null
        _selectedMsqOptions.value = emptySet()
        _natAnswerInput.value = ""
        _isAnswerSubmitted.value = false
        _isAnswerCorrect.value = null
        _aiResultFeedback.value = ""
    }

    // --- Bookmarking Actions ---
    fun toggleBookmark(question: GateQuestion, bookmarked: Boolean) {
        viewModelScope.launch {
            repository.toggleBookmark(question, bookmarked)
        }
    }

    // --- AI Doubt Support Chat ---
    fun sendChatMessage(subtopicId: String, text: String) {
        if (text.isBlank()) return

        viewModelScope.launch {
            // Save user message to database
            repository.insertMessage(subtopicId, "user", text)

            _isChatLoading.value = true
            // Read all history up to now to provide rich conversational context to Gemini API
            val currentHistory = _chatMessages.value
            val currentSub = _selectedSubtopic.value
            val contextName = currentSub?.name ?: "GATE Syllabus Concepts"

            val response = GeminiService.generateSolution(contextName, text, currentHistory)

            // Save AI reply to database
            repository.insertMessage(subtopicId, "model", response)
            _isChatLoading.value = false

            // Add XP for learning!
            repository.addXp(5)
        }
    }

    fun clearChatHistory(subtopicId: String) {
        viewModelScope.launch {
            repository.clearChatHistory(subtopicId)
        }
    }

    // --- CBT Mock Test System Platform ---
    fun selectCbtSubject(subjectId: String?) {
        _cbtSelectedSubjectId.value = subjectId
    }

    fun getCbtSubjectName(subjectId: String?): String {
        if (subjectId == null) return "Complete All-Syllabus Mock Test"
        return repository.subjects.find { it.id == subjectId }?.name ?: "Subject Mock Test"
    }

    fun launchMockTest(subjectId: String? = null) {
        _cbtSelectedSubjectId.value = subjectId
        val questionsPool = mutableListOf<GateQuestion>()
        
        if (subjectId != null) {
            val subject = repository.subjects.find { it.id == subjectId }
            if (subject != null) {
                // Collect preloaded
                subject.topics.forEach { topic ->
                    topic.subtopics.forEach { subtopic ->
                        questionsPool.addAll(subtopic.pyqs)
                        questionsPool.addAll(subtopic.practiceQuestions)
                        questionsPool.addAll(subtopic.mockQuiz)
                    }
                }
                
                // Pad with procedural questions from subtopics of this subject
                var index = 0
                while (questionsPool.size < 100 && index < 200) {
                    for (topic in subject.topics) {
                        for (subtopic in topic.subtopics) {
                            val pqs = ProceduralQuestionGenerator.generateQuestions(
                                subjectId = subject.id,
                                topicId = topic.id,
                                subtopicId = subtopic.id,
                                subtopicName = subtopic.name,
                                count = 10
                            )
                            questionsPool.addAll(pqs)
                            if (questionsPool.size >= 120) break
                        }
                        if (questionsPool.size >= 120) break
                    }
                    index++
                }
            }
        } else {
            // All Subjects combined
            val allPreloaded = repository.subjects.flatMap { subject ->
                subject.topics.flatMap { topic ->
                    topic.subtopics.flatMap { subtopic ->
                        subtopic.pyqs + subtopic.practiceQuestions + subtopic.mockQuiz
                    }
                }
            }
            questionsPool.addAll(allPreloaded)
            
            // Pad with procedural questions from all subjects
            var index = 0
            while (questionsPool.size < 150 && index < 200) {
                for (subject in repository.subjects.shuffled()) {
                    for (topic in subject.topics) {
                        for (subtopic in topic.subtopics) {
                            val pqs = ProceduralQuestionGenerator.generateQuestions(
                                subjectId = subject.id,
                                topicId = topic.id,
                                subtopicId = subtopic.id,
                                subtopicName = subtopic.name,
                                count = 10
                            )
                            questionsPool.addAll(pqs)
                            if (questionsPool.size >= 200) break
                        }
                        if (questionsPool.size >= 200) break
                    }
                    if (questionsPool.size >= 200) break
                }
                index++
            }
        }
        
        // Take exactly 50 distinct questions and shuffle them
        val finalQuestions = questionsPool.distinctBy { it.id }.shuffled().take(50)

        _cbtQuestions.value = finalQuestions
        _cbtUserAnswers.value = emptyMap()
        _cbtFinalScore.value = null
        // GATE standard exam format: 50 questions / 90 minutes (5400 seconds)
        _cbtTimerSeconds.value = 5400
        _isCbtRunning.value = true

        startCbtTimerJob()
    }

    private fun startCbtTimerJob() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (_isCbtRunning.value && _cbtTimerSeconds.value > 0) {
                delay(1000)
                tickCbtTimer()
            }
        }
    }

    fun setCbtAnswer(questionId: String, answer: String) {
        val current = _cbtUserAnswers.value.toMutableMap()
        current[questionId] = answer
        _cbtUserAnswers.value = current
    }

    fun tickCbtTimer() {
        if (!_isCbtRunning.value) return
        if (_cbtTimerSeconds.value > 0) {
            _cbtTimerSeconds.value -= 1
            if (_cbtTimerSeconds.value == 0) {
                submitCbtTest()
            }
        } else {
            submitCbtTest()
        }
    }

    fun submitCbtTest() {
        if (!_isCbtRunning.value) return
        _isCbtRunning.value = false
        
        val job = timerJob
        timerJob = null
        if (job != null && job.isActive) {
            job.cancel()
        }

        val questions = _cbtQuestions.value
        val answers = _cbtUserAnswers.value

        val scoresMap = mutableMapOf<String, Double>()
        val correctCountMap = mutableMapOf<String, Int>()
        val wrongMcqCountMap = mutableMapOf<String, Int>()
        val totalCountMap = mutableMapOf<String, Int>()

        for (q in questions) {
            totalCountMap[q.subjectId] = (totalCountMap[q.subjectId] ?: 0) + 1
            if (!scoresMap.containsKey(q.subjectId)) {
                scoresMap[q.subjectId] = 0.0
                correctCountMap[q.subjectId] = 0
                wrongMcqCountMap[q.subjectId] = 0
            }
        }

        var totalScore = 0.0
        for (q in questions) {
            val userAns = answers[q.id]
            if (userAns == null || userAns.trim().isEmpty()) {
                continue
            }
            val correct = evaluateAnswerCorrectness(q, userAns)
            if (correct) {
                totalScore += 1.0
                scoresMap[q.subjectId] = (scoresMap[q.subjectId] ?: 0.0) + 1.0
                correctCountMap[q.subjectId] = (correctCountMap[q.subjectId] ?: 0) + 1
            } else {
                if (q.questionType == QuestionType.MCQ) {
                    totalScore -= 0.33
                    scoresMap[q.subjectId] = (scoresMap[q.subjectId] ?: 0.0) - 0.33
                    wrongMcqCountMap[q.subjectId] = (wrongMcqCountMap[q.subjectId] ?: 0) + 1
                }
            }
        }

        val breakdowns = totalCountMap.map { (subjId, total) ->
            val correct = correctCountMap[subjId] ?: 0
            val wrongMcq = wrongMcqCountMap[subjId] ?: 0
            val subScore = scoresMap[subjId] ?: 0.0
            val subjName = repository.subjects.find { it.id == subjId }?.name ?: "Syllabus Category"
            CbtSubjectScore(
                subjectId = subjId,
                subjectName = subjName,
                totalQuestions = total,
                correctQuestions = correct,
                wrongMcqs = wrongMcq,
                subjectScore = subScore
            )
        }

        _cbtSubjectScores.value = breakdowns
        _cbtFinalScore.value = totalScore

        viewModelScope.launch {
            repository.addXp(120)
            repository.addTimeSpent(15)
        }
    }

    fun evaluateAnswerCorrectness(question: GateQuestion, answerText: String): Boolean {
        return try {
            when (question.questionType) {
                QuestionType.MCQ -> {
                    val correctIdx = question.correctOptions?.firstOrNull() ?: -1
                    val chosenIdx = answerText.toIntOrNull()
                    chosenIdx == correctIdx
                }
                QuestionType.MSQ -> {
                    // Split chosen index integers
                    val chosenSet = answerText.split(",").mapNotNull { it.trim().toIntOrNull() }.toSet()
                    val correctSet = question.correctOptions?.toSet() ?: emptySet()
                    chosenSet == correctSet
                }
                QuestionType.NAT -> {
                    val floatVal = answerText.toDoubleOrNull()
                    val range = question.correctNumericalRange
                    floatVal != null && range != null && floatVal in range
                }
            }
        } catch (e: Exception) {
            false
        }
    }
}

data class CbtSubjectScore(
    val subjectId: String,
    val subjectName: String,
    val totalQuestions: Int,
    val correctQuestions: Int,
    val wrongMcqs: Int,
    val subjectScore: Double
)
