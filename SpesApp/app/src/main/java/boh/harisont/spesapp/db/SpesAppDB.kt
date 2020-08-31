package boh.harisont.spesapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import boh.harisont.spesapp.db.ingredient.IngredientDao
import boh.harisont.spesapp.db.ingredient.IngredientEntity
import boh.harisont.spesapp.db.recipe.RecipeDao
import boh.harisont.spesapp.db.recipe.RecipeEntity

/**
 * The full database
 */

@Database(entities = [IngredientEntity::class, RecipeEntity::class],version = 1)
@TypeConverters(Converters::class)
abstract class SpesAppDB: RoomDatabase() {
    abstract fun IngredientDao(): IngredientDao
    abstract fun RecipeDao(): RecipeDao

    // to make the DB a singleton (I guess, I clearly copy-pasted it)
    companion object {
        private var INSTANCE: SpesAppDB? = null

        fun getInstance(context: Context): SpesAppDB? {
            if (INSTANCE == null) {
                synchronized(SpesAppDB::class) {
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