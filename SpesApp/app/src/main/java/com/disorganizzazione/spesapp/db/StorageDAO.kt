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

    // non sono sicura di quale sia la migliore strategia
    // not sure what the best strategy is
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInStorage(item: StorageEntity)

    @Delete
    fun deleteFromStorage(item: StorageEntity)

}