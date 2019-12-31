package com.disorganizzazione.spesapp.db

import androidx.room.*

/* Interfaccia per le operazioni sulla tabella "DISPENSA".
*  Interface for the operations on the "IN STORAGE" table. */

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

}