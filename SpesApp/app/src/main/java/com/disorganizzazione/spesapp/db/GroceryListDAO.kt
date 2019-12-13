package com.disorganizzazione.spesapp.db

import androidx.room.*

/* Interfaccia per le operazioni sulla tabella "LISTA DELLA SPESA".
*  Interface for the operations on the "GROCERY LIST" table. */

@Dao
interface GroceryListDAO {
    // nota: MySQL non ha i booleani
    // note: MySQL does not have booleans

    @Query("SELECT * FROM GroceryListEntity")
    fun selectAllGroceryList(): List<GroceryListEntity>

    // non sono sicura di quale sia la migliore strategia
    // not sure what the best strategy is
    // TODO: scegliere strategia/decide on strategy (ABORT?!)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGroceryList(item: GroceryListEntity)

    @Delete
    fun deleteGroceryList(item: GroceryListEntity)
}