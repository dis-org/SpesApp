package boh.harisont.spesapp.db

import androidx.room.TypeConverter
import java.util.*

class Converters {
    // timestamp/date
    @TypeConverter
    fun timestampToDate(stamp: Long?): Date? {
        return if (stamp == null) null else Date(stamp)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    // comma-separated string / list of ingredient names
    @TypeConverter
    fun stringToIngredients(str: String): List<IngrName> {
        return str.split(',')
    }

    @TypeConverter
    fun ingredientsToString(list: List<IngrName>): String {
        return list.joinToString(",")
    }
}