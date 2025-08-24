package com.example.personalityquiz.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizResultDao {
    @Insert
    suspend fun insert(result: QuizResult)

    @Query("SELECT * FROM quiz_results ORDER BY dateMillis DESC")
    fun getAll(): Flow<List<QuizResult>>
}
