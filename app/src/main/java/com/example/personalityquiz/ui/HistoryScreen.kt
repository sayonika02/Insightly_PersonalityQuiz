package com.example.personalityquiz.ui

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.personalityquiz.data.HistoryStorage
import com.example.personalityquiz.model.QuizAttempt

@Composable
fun HistoryScreen(
    context: Context,
    onBack: () -> Unit
) {
    val attempts = HistoryStorage.getAttempts(context)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Quiz History",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(16.dp))

        if (attempts.isEmpty()) {
            Text(
                "No past attempts yet.",
                style = MaterialTheme.typography.bodyMedium
            )
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(attempts) { attempt ->
                    AttemptCard(attempt)
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onBack,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Back")
        }
    }
}

@Composable
fun AttemptCard(attempt: QuizAttempt) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Date: ${attempt.date}", style = MaterialTheme.typography.bodyLarge)
            Text("Result: ${attempt.result}", style = MaterialTheme.typography.bodyMedium)
            Spacer(Modifier.height(8.dp))
            if (attempt.categoryScores.isNotEmpty()) {
                Text("Category Breakdown:", style = MaterialTheme.typography.labelMedium)
                Spacer(Modifier.height(4.dp))
                attempt.categoryScores.forEach { (k, v) ->
                    Text("• $k: $v", style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}
