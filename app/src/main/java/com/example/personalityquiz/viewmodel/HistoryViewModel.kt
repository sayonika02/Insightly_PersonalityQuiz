package com.example.personalityquiz.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.personalityquiz.data.QuizResultRepository
import com.example.personalityquiz.data.local.AppDatabase
import com.example.personalityquiz.data.local.Converters
import com.example.personalityquiz.data.local.QuizResult
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class HistoryItem(
    val id: Int,
    val dateMillis: Long,
    val totalScore: Int,
    val personality: String,
    val categoryScores: Map<String, Int>
)

class HistoryViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = QuizResultRepository(AppDatabase.getInstance(app))

    val history: StateFlow<List<HistoryItem>> =
        repo.getAllResults()
            .map { list -> list.map { it.toItem() } }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun saveResult(
        totalScore: Int,
        personality: String,
        categoryScores: Map<String, Int>
    ) {
        viewModelScope.launch {
            repo.saveResult(
                dateMillis = System.currentTimeMillis(),
                totalScore = totalScore,
                personality = personality,
                categoryScores = categoryScores
            )
        }
    }

    private fun QuizResult.toItem() = HistoryItem(
        id = id,
        dateMillis = dateMillis,
        totalScore = totalScore,
        personality = personality,
        categoryScores = Converters.jsonToMap(categoryScoresJson)
    )
}
