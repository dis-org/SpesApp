package com.disorganizzazione.spesapp.db.ingredients

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.disorganizzazione.spesapp.db.IngredientName
import com.disorganizzazione.spesapp.db.UnitOfMeasurement
import org.jetbrains.annotations.NotNull

/**
 * Abstract class representing ingredients (to buy and bought, these are the common fields).
 * Because polymorphism is lovely.
 */

@Entity
abstract class IngredientEntity {

    @PrimaryKey
    var name: IngredientName = ""

    @ColumnInfo
    var quantity: Pair<Float, UnitOfMeasurement?>? = null

    // categories are user-defined, so they're just strings
    @ColumnInfo
    var category : String? = null

    // for items in storage, this tells us whether the ingredient has been consumed or not
    // for items in the grocery list, it tells us whether they've been bought
    @ColumnInfo @NotNull
    var done: Boolean = false

    // just for debugging, can be overridden if necessary
    open fun display() {
        println("name: ${this.name}")
        println("quantity: ${this.quantity?.first ?: ""} ${this.quantity?.second ?: ""}")
        println("category: ${this.category ?: ""}")
    }

}