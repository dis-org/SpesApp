package boh.harisont.spesapp.db.ingredient

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import boh.harisont.spesapp.db.IngrName
import org.jetbrains.annotations.NotNull
import java.io.Serializable
import java.util.*

/**
 * Ingredient entity
 */

@Entity
class IngredientEntity(
    @PrimaryKey var name: IngrName,

    // categories are user-defined, so they're just strings
    @ColumnInfo var category : String? = null,

    @ColumnInfo var useBefore: Date? = null,

    // determines in which list the ingredient is shown
    @ColumnInfo @NotNull var bought: Boolean = false,

    // if an item is checked, it means it has been bought (resp. consumed)
    @ColumnInfo @NotNull var checked: Boolean = false
): Serializable