package com.disorganizzazione.spesapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

/* Classe che rappresenta la tabella "INGREDIENTI" (mostrata nell'activity principale).
*  Class representing the "INGREDIENTS" table (shown in the main activity). */

@Entity
class IngredientEntity {
    @PrimaryKey
    var name : String = ""

    // i veri livelli di priorità sono 1,2,3, quindi forse meglio usare un'enum
    // 1,2,3 are the actual levels of priority so maybe an enum would be better
    @ColumnInfo
    var priority : Int? = 0

    // 0 sta per "non specificato", non 0 porzioni
    // 0 means unspecified, not 0 portions
    @ColumnInfo
    var portions : Int? = 0

    @ColumnInfo @NotNull
    var inStorage : Boolean = false
}