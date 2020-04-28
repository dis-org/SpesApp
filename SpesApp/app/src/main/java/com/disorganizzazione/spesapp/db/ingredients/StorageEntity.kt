package com.disorganizzazione.spesapp.db.ingredients

import androidx.room.ColumnInfo
import androidx.room.Entity
import java.util.*

/**
 * Class modeling ingredients in storage.
 */

@Entity
class StorageEntity : IngredientEntity() {

    // expiration date: it is a Long because SQLite does not have dates, hence the converter
    @ColumnInfo
    var useBefore: Date? = null
}