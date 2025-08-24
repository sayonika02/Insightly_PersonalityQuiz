package com.sd.personalityquiz.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Converters {
    private val gson = Gson()

    @TypeConverter
    @JvmStatic
    fun mapToJson(map: Map<String, Int>): String = gson.toJson(map)

    @TypeConverter
    @JvmStatic
    fun jsonToMap(json: String): Map<String, Int> {
        val type = object : TypeToken<Map<String, Int>>() {}.type
        return gson.fromJson(json, type) ?: emptyMap()
    }
}
