package boh.harisont.spesapp.db.ingredient

import androidx.room.*
import boh.harisont.spesapp.db.IngrName

/**
 * DAO for the ingredient table (grocery list + in storage list)
 */

@Dao
interface IngredientDao {

    // SELECT (there's no booleans in SQLite hence the 0s and 1s)
    @Query("SELECT * FROM IngredientEntity WHERE bought = 0 ORDER BY category")
    fun selectGroceryList(): MutableList<IngredientEntity>

    @Query("SELECT * FROM IngredientEntity WHERE bought = 1 ORDER BY useBefore")
    fun selectStorageList(): MutableList<IngredientEntity>

    // INSERT/DELETE
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(ingr: IngredientEntity)

    @Delete
    fun delete(ingr: IngredientEntity)

    // UPDATE
    @Query("UPDATE IngredientEntity set checked = :truth WHERE name = :name")
    fun check(name: IngrName, truth: Boolean)

    // TODO: add more update operations
}