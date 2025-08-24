package com.sd.personalityquiz.data

import android.content.Context
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "quiz_prefs")

class DataStoreManager(private val context: Context) {

    companion object {
        private val LAST_SCORE_KEY = intPreferencesKey("last_score")
    }

    //save last score
    suspend fun saveScore(score: Int) {
        context.dataStore.edit { prefs ->
            prefs[LAST_SCORE_KEY] = score
        }
    }

    val lastScore: Flow<Int> = context.dataStore.data
        .map { prefs ->
            prefs[LAST_SCORE_KEY] ?: 0  //default=0 if not set
        }
}
