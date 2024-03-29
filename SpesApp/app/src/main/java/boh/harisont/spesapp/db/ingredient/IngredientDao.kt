package boh.harisont.spesapp.db.ingredient

import androidx.lifecycle.LiveData
import androidx.room.*
import boh.harisont.spesapp.db.IngrName
import java.util.*

/**
 * DAO for the ingredient table (grocery list + in storage list)
 */

@Dao
interface IngredientDao {

    // SELECT
    // there's no booleans in SQLite hence the 0s and 1s
    // LiveData makes the tables observable
    @Query("SELECT * FROM IngredientEntity WHERE bought = 0 ORDER BY category")
    fun selectGroceryList(): LiveData<List<IngredientEntity>>

    @Query("SELECT * FROM IngredientEntity WHERE bought = 1 ORDER BY CASE WHEN useBefore IS NULL THEN 1 ELSE 0 END, useBefore ASC")
    fun selectStorageList(): LiveData<List<IngredientEntity>>

    // INSERT/DELETE
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(ingr: IngredientEntity)

    @Delete
    fun delete(ingr: IngredientEntity)

    // UPDATE
    @Query("UPDATE IngredientEntity set checked = :truth WHERE name = :name")
    fun check(name: IngrName, truth: Boolean)

    @Query("DELETE FROM IngredientEntity WHERE bought = 1 AND checked = 1")
    fun deleteAllConsumed()

    @Query("UPDATE IngredientEntity set bought = 1, checked = 0 WHERE bought = 0 AND checked = 1")
    fun moveBoughtToStorage()

    @Query("UPDATE IngredientEntity set name = :newName, category = :category, useBefore = :useBefore WHERE name = :oldName")
    fun update(oldName: IngrName, newName: IngrName, category: String?, useBefore: Date?)
}