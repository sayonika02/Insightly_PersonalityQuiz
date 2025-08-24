package com.example.personalityquiz.model

data class Question(
    val text: String,
    val options: List<String>,
    val scores: List<Int>,
    val category: String
)
