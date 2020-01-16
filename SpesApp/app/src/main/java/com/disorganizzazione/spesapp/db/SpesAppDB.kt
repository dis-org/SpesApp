package com.disorganizzazione.spesapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.disorganizzazione.spesapp.db.ingredients.GroceryListDAO
import com.disorganizzazione.spesapp.db.ingredients.GroceryListEntity
import com.disorganizzazione.spesapp.db.ingredients.StorageDAO
import com.disorganizzazione.spesapp.db.ingredients.StorageEntity
import com.disorganizzazione.spesapp.db.recipes.RecipesDAO
import com.disorganizzazione.spesapp.db.recipes.RecipesEntity

/* Classe base per l'intero database. Note: DAO stands for Data Access Object.
*  Basic class for the entire database. NB: DAO sta per Data Access Object. */

@Database(entities = [StorageEntity::class, GroceryListEntity::class, RecipesEntity::class], version = 8)
@TypeConverters(Converters::class)
abstract class SpesAppDB : RoomDatabase() {
    abstract fun storageDAO(): StorageDAO
    abstract fun groceryListDAO(): GroceryListDAO
    abstract fun recipesDAO(): RecipesDAO

    // penso che serva per creare il database come singleton
    // this is to make the database a singleton, I think
    companion object {
        private var INSTANCE: SpesAppDB? = null

        fun getInstance(context: Context): SpesAppDB? {
            if (INSTANCE == null) {
                synchronized(SpesAppDB::class) {
                    // TODO: manage fallBackToDestructiveMigration() when the database design is "definitive"
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        SpesAppDB::class.java,
                        "SpesAppDB").fallbackToDestructiveMigration().build()
                    println("DATABASE created")
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}