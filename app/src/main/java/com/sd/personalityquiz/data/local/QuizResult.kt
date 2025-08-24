package com.sd.personalityquiz.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz_results")
data class QuizResult(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val dateMillis: Long,
    val totalScore: Int,
    val personality: String,
    val categoryScoresJson: String
)
