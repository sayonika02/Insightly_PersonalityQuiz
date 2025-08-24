package com.example.personalityquiz.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.personalityquiz.model.Question

@Composable
fun QuizScreen(
    questions: List<Question>,
    onQuizComplete: (totalScore: Int, categoryScores: Map<String, Int>) -> Unit
) {
    var index by remember { mutableIntStateOf(0) }
    var total by remember { mutableIntStateOf(0) }
    val categoryTotals = remember { mutableStateMapOf<String, Int>() }

    val q = questions[index]

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Question ${index + 1} / ${questions.size} (${q.category})",
            style = MaterialTheme.typography.titleMedium)

        Spacer(Modifier.height(12.dp))
        Text(q.text, style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))

        q.options.forEachIndexed { i, optionText ->
            Button(
                onClick = {
                    val s = q.scores[i]
                    total += s
                    categoryTotals[q.category] = (categoryTotals[q.category] ?: 0) + s

                    if (index < questions.lastIndex) {
                        index++
                    } else {
                        onQuizComplete(total, categoryTotals.toMap())
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)
            ) { Text(optionText) }
        }
    }
}
