package com.disorganizzazione.spesapp.utils

import androidx.room.TypeConverter
import java.util.*

/* Convertitori Date - Long */
/* Date - Long converters */

// Grazie a tinmegali: https://gist.github.com/tinmegali/d4a477785f01e57066915a44543db6ed
// Thanks to tinmegali: https://gist.github.com/tinmegali/d4a477785f01e57066915a44543db6ed

class DateConverters {
    @TypeConverter
    fun TimestampToDate(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}