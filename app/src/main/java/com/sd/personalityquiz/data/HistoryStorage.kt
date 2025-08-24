package com.sd.personalityquiz.data

import android.content.Context
import com.sd.personalityquiz.model.QuizAttempt
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object HistoryStorage {
    private const val PREFS_NAME = "quiz_history_prefs"
    private const val KEY_ATTEMPTS = "quiz_attempts"

    private val gson = Gson()

    fun saveAttempt(context: Context, attempt: QuizAttempt) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val existing = getAttempts(context).toMutableList()
        existing.add(0, attempt)
        val json = gson.toJson(existing)
        prefs.edit().putString(KEY_ATTEMPTS, json).apply()
    }

    fun getAttempts(context: Context): List<QuizAttempt> {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(KEY_ATTEMPTS, null) ?: return emptyList()
        val type = object : TypeToken<List<QuizAttempt>>() {}.type
        return gson.fromJson(json, type)
    }
}
