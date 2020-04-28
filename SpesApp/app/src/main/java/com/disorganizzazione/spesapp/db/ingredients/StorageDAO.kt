package com.disorganizzazione.spesapp.db.ingredients

import androidx.room.*
import com.disorganizzazione.spesapp.db.IngredientName

/**
 * DAO for the ingredients table.
 */

@Dao
interface StorageDAO {

    // nota: MySQL non ha i booleani
    // note: MySQL does not have booleans

    @Query("SELECT * FROM StorageEntity ORDER BY useBefore")
    fun selectAllInStorage(): List<StorageEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertInStorage(item: StorageEntity)

    @Delete
    fun deleteFromStorage(item: StorageEntity)

    // updates field "done" of a record
    @Query("UPDATE StorageEntity set done = :truth WHERE name = :ingrName")
    fun tick(ingrName: IngredientName, truth: Boolean)
}