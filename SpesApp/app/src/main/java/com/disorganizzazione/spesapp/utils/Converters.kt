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
    fun stringToIngredients(str: String): List<IngredientName> {
        return str.split(',')
    }

    @TypeConverter
    fun ingredientsToString(list: List<IngredientName>): String {
        return list.joinToString(",")
    }

    @TypeConverter
    fun stringToQuantityPair(str: String?): Pair<Float,UnitOfMeasurement?>? {
        if (str != null) {
            val spl= str.split(' ', limit = 2)
            val num = spl[0].toFloatOrNull()
            if (num != null)
                return Pair(num, spl[1])
        }
        return null
    }

    @TypeConverter
    fun quantityPairToString(qnt: Pair<Float,UnitOfMeasurement?>?): String? {
        if (qnt != null)
            return "${qnt.first} ${qnt.second ?: ""}"
        return null
    }
}