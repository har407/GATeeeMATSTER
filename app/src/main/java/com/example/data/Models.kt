package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

// --- Domain Models for Syllabus Structure ---

data class Subject(
    val id: String,
    val name: String,
    val iconName: String,
    val topics: List<Topic>
)

data class Topic(
    val id: String,
    val subjectId: String,
    val name: String,
    val subtopics: List<Subtopic>
)

data class Subtopic(
    val id: String,
    val topicId: String,
    val subjectId: String,
    val name: String,
    val theory: TheoryContent,
    val formulaSheet: List<FormulaItem>,
    val pyqs: List<GateQuestion>,
    val practiceQuestions: List<GateQuestion>,
    val mockQuiz: List<GateQuestion>
)

data class TheoryContent(
    val title: String,
    val synopsis: String,
    val detailedBullets: List<String>,
    val keyInsight: String
)

data class FormulaItem(
    val name: String,
    val expression: String,
    val description: String,
    val applicationTrick: String
)

enum class QuestionType {
    MCQ, // Multiple Choice Question
    MSQ, // Multiple Select Question
    NAT  // Numerical Answer Type
}

data class GateQuestion(
    val id: String,
    val subjectId: String,
    val topicId: String,
    val subtopicId: String,
    val year: Int,
    val questionText: String,
    val questionType: QuestionType,
    val options: List<String>? = null, // Empty for NAT
    val correctOptions: List<Int>? = null, // Option indexes (0-based) for MCQ/MSQ
    val correctNumericalRange: ClosedRange<Double>? = null, // for NAT
    val explanation: String,
    val formulasUsed: String,
    val shortcutTricks: String,
    val relatedConcepts: String,
    val difficulty: String // Easy, Medium, Hard
)

// --- Room Entities for Local Persistence ---

@Entity(tableName = "doubt_messages")
data class DoubtMessageEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val subtopicId: String,
    val role: String, // "user" or "model"
    val text: String,
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "subtopic_progress")
data class SubtopicProgressEntity(
    @PrimaryKey val subtopicId: String,
    val subjectId: String,
    val topicId: String,
    val isCompleted: Boolean,
    val scorePercent: Int = 0,
    val timeSpentSeconds: Long = 0,
    val questionsAttempted: Int = 0,
    val lastStudiedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "saved_questions")
data class SavedQuestionEntity(
    @PrimaryKey val questionId: String,
    val subjectId: String,
    val topicId: String,
    val subtopicId: String,
    val questionText: String,
    val questionType: String, // "MCQ", "MSQ", "NAT"
    val optionsJson: String, // Serialized list
    val correctOptionsJson: String,
    val explanation: String,
    val isMistakeNotebook: Boolean = false, // If true, saved under "My Mistakes" after wrong ans
    val userNotes: String = "",
    val savedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "user_stats")
data class UserStatsEntity(
    @PrimaryKey val id: Int = 1,
    val streak: Int = 1,
    val totalXp: Int = 100,
    val lastActiveDay: Long = System.currentTimeMillis(),
    val timeSpentMinutes: Int = 125
)
