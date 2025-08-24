package com.sd.personalityquiz.model

data class QuizAttempt(
    val date: String,                       //"2025-08-23"
    val categoryScores: Map<String, Int>,   //map {"Logic"=8, "Creativity"=6}
    val result: String
)
