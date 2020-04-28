package com.disorganizzazione.spesapp.db.ingredients

import androidx.room.*
import com.disorganizzazione.spesapp.db.IngredientName

/**
 * DAO for the grocery list table.
 */

@Dao
interface GroceryListDAO {

    @Query("SELECT * FROM GroceryListEntity ORDER BY category")
    fun selectAllInGroceryList(): List<GroceryListEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertInGroceryList(item: GroceryListEntity)

    @Delete
    fun deleteFromGroceryList(item: GroceryListEntity)

    // updates field "done" of a record
    @Query("UPDATE GroceryListEntity set done = :truth WHERE name = :ingrName")
    fun tick(ingrName: IngredientName, truth: Boolean)
}