package com.disorganizzazione.spesapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.disorganizzazione.spesapp.IngredientName
import com.disorganizzazione.spesapp.UnitOfMeasurement

/**
 * Tabella astratta di ingredienti,
 * utile per poter avere un modo di costruire liste polimorfiche quando si mostrano gli ingredienti nelle RecyclerView etc.
 * Abstract table of ingredients,
 * useful to have the possibility of having polymorphic lists when showing the items in RecyclerViews etc.
 */

@Entity
abstract class IngredientEntity {

    @PrimaryKey
    var name: IngredientName = ""

    @ColumnInfo
    var quantity: Pair<Float,UnitOfMeasurement?>? = null

    // le categorie sono scelte dall'utente, per cui sono semplici stringhe
    // categories will be user-defined, so they're just strings
    @ColumnInfo
    var category : String? = null

}