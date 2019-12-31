package com.disorganizzazione.spesapp.utils

import androidx.room.TypeConverter
import com.disorganizzazione.spesapp.IngredientName
import com.disorganizzazione.spesapp.UnitOfMeasurement
import java.util.*

/* Convertitori di tipo per il database (non necessario per booleani) */
/* Type converters for the database (not necessary for booleans) */

class Converters {
    @TypeConverter
    fun timestampToDate(stamp: Long?): Date? {
        return if (stamp == null) null else Date(stamp)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun stringToIngredients(str: String?): List<IngredientName>? {
        return null // TODO
    }

    @TypeConverter
    fun ingredientsToString(list: List<IngredientName>?): String? {
        return null // TODO
      
    fun stringToQuantityPair(string: String): Pair<Float,UnitOfMeasurement?>? {
        val splitted = string.split(',', limit = 2)
        val number = splitted[0].toFloatOrNull()
        if (number != null)
            return Pair(number,splitted[1])
        else return null
    }

    @TypeConverter
    fun quantityPairToString(quant: Pair<Float,UnitOfMeasurement?>?): String? {
        if (quant != null) return "${quant.first},${quant?.second ?: ""}"
        return null
    }
}