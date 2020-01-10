package com.disorganizzazione.spesapp.db.ingredients

import androidx.room.*

/* Interfaccia per le operazioni sulla tabella "LISTA DELLA SPESA".
*  Interface for the operations on the "GROCERY LIST" table. */

@Dao
interface GroceryListDAO {
    // nota: MySQL non ha i booleani
    // note: MySQL does not have booleans

    @Query("SELECT * FROM GroceryListEntity")
    fun selectAllInGroceryList(): List<GroceryListEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertInGroceryList(item: GroceryListEntity)

    @Delete
    fun deleteFromGroceryList(item: GroceryListEntity)
    // nota: questa funzione non verrà (quasi) mai chiamata se non accompagnata da una chiamata di insertInStorage
    // note: this function will (almost) never be called if not together with a call to insertInStorage()
}