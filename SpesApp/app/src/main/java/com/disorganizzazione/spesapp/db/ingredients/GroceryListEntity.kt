package com.disorganizzazione.spesapp.db.ingredients

import androidx.room.Entity

/**
 * Class modeling ingredients to be bought.
 */

@Entity
class GroceryListEntity : IngredientEntity() {

    fun toStorageEntity(): StorageEntity {
        val se = StorageEntity()
        se.name = this.name
        se.quantity = this.quantity
        se.category = this.category
        se.done = this.done
        se.useBefore = null
        return se
    }
}