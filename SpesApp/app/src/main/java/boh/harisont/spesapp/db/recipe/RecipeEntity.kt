package boh.harisont.spesapp.db.recipe

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import boh.harisont.spesapp.db.IngrName
import boh.harisont.spesapp.db.MealName
import org.jetbrains.annotations.NotNull

@Entity
class RecipeEntity(
    @PrimaryKey var name : MealName = "",
    @ColumnInfo @NotNull var ingrs : List<IngrName> = emptyList(),
    @ColumnInfo var descr : String? = null
)