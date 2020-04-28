package com.disorganizzazione.spesapp.db.recipes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.disorganizzazione.spesapp.db.IngredientName
import org.jetbrains.annotations.NotNull

@Entity
class RecipesEntity {
    @PrimaryKey
    var title : String = ""

    // TODO: look into external keys, if necessary
    @ColumnInfo @NotNull
    var ingredients : List<IngredientName> = emptyList()

    // Steps of the recipe, or whatever the user wants to write about it
    @ColumnInfo
    var description : String? = null

}