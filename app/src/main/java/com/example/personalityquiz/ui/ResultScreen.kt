package com.example.personalityquiz.ui

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.personalityquiz.data.HistoryStorage
import com.example.personalityquiz.model.QuizAttempt
import java.time.LocalDate

@Composable
fun ResultScreen(
    score: Int,
    categoryScores: Map<String, Int>,
    onRestart: () -> Unit,
    onHistory: () -> Unit
) {
    val context = LocalContext.current

    val personality = when (score) {
        in 0..7 -> "Introverted"
        in 8..12 -> "Balanced / Ambivert"
        in 13..18 -> "Social / Extroverted"
        else -> "Highly Outgoing"
    }

    saveAttemptOnce(context, categoryScores, personality)

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Your Score: $score", style = MaterialTheme.typography.headlineMedium)
        Text("Personality: $personality", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))

        if (categoryScores.isNotEmpty()) {
            Text("Category Breakdown:", style = MaterialTheme.typography.titleSmall)
            categoryScores.forEach { (k, v) -> Text("- $k: $v") }
        }

        Spacer(Modifier.height(24.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = onRestart) { Text("Restart") }
            Button(onClick = onHistory) { Text("History") }
        }
    }
}

@Composable
private fun saveAttemptOnce(
    context: Context,
    categoryScores: Map<String, Int>,
    personality: String
) {
    androidx.compose.runtime.LaunchedEffect(Unit) {
        val attempt = QuizAttempt(
            date = LocalDate.now().toString(),
            categoryScores = categoryScores,
            result = personality
        )
        HistoryStorage.saveAttempt(context, attempt)
    }
}
