package com.disorganizzazione.spesapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.disorganizzazione.spesapp.utils.Converters

/* Classe base per l'intero database. Note: DAO stands for Data Access Object.
*  Basic class for the entire database. NB: DAO sta per Data Access Object. */

@Database(entities = [StorageEntity::class, GroceryListEntity::class, RecipesEntity::class], version = 7)
@TypeConverters(Converters::class)
abstract class SpesAppDB : RoomDatabase() {
    abstract fun storageDAO() : StorageDAO
}