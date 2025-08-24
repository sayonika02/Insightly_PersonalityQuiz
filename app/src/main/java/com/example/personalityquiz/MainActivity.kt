package com.example.personalityquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.personalityquiz.data.QuizRepository
import com.example.personalityquiz.ui.HistoryScreen
import com.example.personalityquiz.ui.QuizScreen
import com.example.personalityquiz.ui.ResultScreen
import com.example.personalityquiz.ui.theme.PersonalityQuizTheme
import com.example.personalityquiz.viewmodel.HistoryViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PersonalityQuizTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val vm: HistoryViewModel = viewModel()

                    val questions = remember { QuizRepository.getQuestions() }

                    var screen by remember { mutableStateOf("quiz") }
                    var finalScore by remember { mutableIntStateOf(0) }
                    var finalCategoryScores by remember { mutableStateOf<Map<String, Int>>(emptyMap()) }

                    when (screen) {
                        "quiz" -> QuizScreen(
                            questions = questions,
                            onQuizComplete = { score, categoryScores ->
                                finalScore = score
                                finalCategoryScores = categoryScores
                                screen = "result"
                            }
                        )

                        "result" -> {
                            val personality = when (finalScore) {
                                in 0..7 -> "Introverted"
                                in 8..12 -> "Balanced / Ambivert"
                                in 13..18 -> "Social / Extroverted"
                                else -> "Highly Outgoing"
                            }

                            LaunchedEffect(finalScore, finalCategoryScores) {
                                vm.saveResult(
                                    totalScore = finalScore,
                                    personality = personality,
                                    categoryScores = finalCategoryScores
                                )
                            }
                            ResultScreen(
                                score = finalScore,
                                categoryScores = finalCategoryScores,
                                onRestart = {
                                    screen = "quiz"
                                    finalScore = 0
                                    finalCategoryScores = emptyMap()
                                },
                                onHistory = { screen = "history" }
                            )
                        }

                        "history" -> HistoryScreen(
                            context = this,
                            onBack = { screen = "result" }
                        )
                    }
                }
            }
        }
    }
}
