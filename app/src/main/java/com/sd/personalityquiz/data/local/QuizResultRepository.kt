package com.sd.personalityquiz.data

import com.sd.personalityquiz.data.local.AppDatabase
import com.sd.personalityquiz.data.local.Converters
import com.sd.personalityquiz.data.local.QuizResult
import kotlinx.coroutines.flow.Flow

class QuizResultRepository(private val db: AppDatabase) {

    fun getAllResults(): Flow<List<QuizResult>> = db.quizResultDao().getAll()

    suspend fun saveResult(
        dateMillis: Long,
        totalScore: Int,
        personality: String,
        categoryScores: Map<String, Int>
    ) {
        val json = Converters.mapToJson(categoryScores)
        val result = QuizResult(
            dateMillis = dateMillis,
            totalScore = totalScore,
            personality = personality,
            categoryScoresJson = json
        )
        db.quizResultDao().insert(result)
    }
}
