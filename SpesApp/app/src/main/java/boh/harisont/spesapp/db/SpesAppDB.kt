package boh.harisont.spesapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import boh.harisont.spesapp.db.ingredient.IngredientDao
import boh.harisont.spesapp.db.ingredient.IngredientEntity
import boh.harisont.spesapp.db.recipe.RecipeDao
import boh.harisont.spesapp.db.recipe.RecipeEntity
import kotlin.concurrent.thread

/**
 * The full database
 */

// if the DB changes, update version and provide a migration strategy
@Database(entities = [IngredientEntity::class, RecipeEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class SpesAppDB: RoomDatabase() {
    abstract fun ingredientDao(): IngredientDao
    abstract fun recipeDao(): RecipeDao

    // to make the DB a singleton (I guess, I clearly copy-pasted it)
    companion object {
        private var INSTANCE: SpesAppDB? = null

        // populate the db for testing purposes
        // TODO: remove if useless
        private val CALLBACK = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                thread {
                    INSTANCE?.ingredientDao()?.insert(IngredientEntity("latte"))
                }
            }
        }

        fun getInstance(context: Context): SpesAppDB? {
            if (INSTANCE == null) {
                synchronized(SpesAppDB::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        SpesAppDB::class.java,
                        // fallbackToDestructiveMigration destroys the db if you make bad changes
                        "SpesAppDB").fallbackToDestructiveMigration()
                        .addCallback(CALLBACK)
                        .build()
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