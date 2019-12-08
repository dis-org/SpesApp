package com.disorganizzazione.spesapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.disorganizzazione.spesapp.utils.DateConverters

/* Classe base per l'intero database. Note: DAO stands for Data Access Object.
*  Basic class for the entire database. NB: DAO sta per Data Access Object. */

@Database(entities = [IngredientEntity::class], version = 1)
@TypeConverters(DateConverters::class)
abstract class SpesAppDB : RoomDatabase() {
    abstract fun ingredientDAO() : IngredientDAO
}