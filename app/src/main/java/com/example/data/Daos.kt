package com.example.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DoubtMessageDao {
    @Query("SELECT * FROM doubt_messages WHERE subtopicId = :subtopicId ORDER BY timestamp ASC")
    fun getMessagesForSubtopic(subtopicId: String): Flow<List<DoubtMessageEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: DoubtMessageEntity)

    @Query("DELETE FROM doubt_messages WHERE subtopicId = :subtopicId")
    suspend fun clearHistory(subtopicId: String)
}

@Dao
interface SubtopicProgressDao {
    @Query("SELECT * FROM subtopic_progress")
    fun getAllProgress(): Flow<List<SubtopicProgressEntity>>

    @Query("SELECT * FROM subtopic_progress WHERE subtopicId = :subtopicId")
    suspend fun getProgressForSubtopic(subtopicId: String): SubtopicProgressEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProgress(progress: SubtopicProgressEntity)
}

@Dao
interface SavedQuestionDao {
    @Query("SELECT * FROM saved_questions WHERE isMistakeNotebook = 0")
    fun getBookmarks(): Flow<List<SavedQuestionEntity>>

    @Query("SELECT * FROM saved_questions WHERE isMistakeNotebook = 1")
    fun getMistakeNotebook(): Flow<List<SavedQuestionEntity>>

    @Query("SELECT * FROM saved_questions WHERE questionId = :questionId")
    suspend fun getSavedQuestion(questionId: String): SavedQuestionEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveQuestion(question: SavedQuestionEntity)

    @Delete
    suspend fun removeQuestion(question: SavedQuestionEntity)

    @Query("DELETE FROM saved_questions WHERE questionId = :questionId AND isMistakeNotebook = :isMistake")
    suspend fun deleteById(questionId: String, isMistake: Boolean)
}

@Dao
interface UserStatsDao {
    @Query("SELECT * FROM user_stats WHERE id = 1")
    fun getUserStatsFlow(): Flow<UserStatsEntity?>

    @Query("SELECT * FROM user_stats WHERE id = 1")
    suspend fun getUserStats(): UserStatsEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUserStats(stats: UserStatsEntity)
}
