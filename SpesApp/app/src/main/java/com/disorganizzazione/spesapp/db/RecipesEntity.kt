package com.disorganizzazione.spesapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.disorganizzazione.spesapp.IngredientName
import org.jetbrains.annotations.NotNull

@Entity
class RecipesEntity {
    @PrimaryKey
    var title : String = ""

    @ColumnInfo @NotNull
    var ingredients : List<IngredientName> = emptyList()

    // "Passi" della ricetta
    // Steps of the recipe
    @ColumnInfo
    var description : String? = null

}