package com.disorganizzazione.spesapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Tabella astratta di ingredienti,
 * utile per poter avere un modo di costruire liste polimorfiche quando si mostrano gli ingredienti nelle RecyclerView etc.
 * Abstract table of ingredients,
 * useful to have the possibility of having polymorphic lists when showing the items in RecyclerViews etc.
 */

@Entity
abstract class IngredientEntity {

    @PrimaryKey
    var name : IngredientName = ""

    // TODO: cambiare nome e tipo in qualcosa di più flessibile (String?)
    // TODO: change name and type to something more flexible (String?)
    @ColumnInfo
    var portions : Int? = null

}