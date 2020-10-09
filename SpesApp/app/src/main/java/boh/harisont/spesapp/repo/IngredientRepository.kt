package boh.harisont.spesapp.repo

import android.app.Application
import androidx.lifecycle.LiveData
import boh.harisont.spesapp.db.IngrName
import boh.harisont.spesapp.db.SpesAppDB
import boh.harisont.spesapp.db.ingredient.IngredientEntity
import kotlin.concurrent.thread

/**
 * Ingredient repository, yet another layer of abstraction between the DB and the VM.
 * DB is instantiated here.
 */

class IngredientRepository(application: Application) {
        val db = SpesAppDB.getInstance(application)
        private val ingrDao = db?.ingredientDao()
        private val groceryList = ingrDao?.selectGroceryList()
        private val storageList = ingrDao?.selectStorageList()

    fun selectGroceryList(): LiveData<List<IngredientEntity>>? {
        return groceryList
    }

    fun selectStorageList(): LiveData<List<IngredientEntity>>? {
        return storageList
    }

    // TODO: solve the 10000 problems that will undoubtedly arise
    fun select(name: IngrName): IngredientEntity {
        var ingr = IngredientEntity("Ingredient")
        thread {
            ingr = ingrDao?.select(name)!!
        }
        return ingr
    }

    fun insert(ingr: IngredientEntity) {
        thread { ingrDao?.insert(ingr) }
    }

    fun delete(ingr: IngredientEntity) {
        thread { ingrDao?.delete(ingr) }
    }

    fun check(ingr: IngredientEntity, truth: Boolean) {
        thread { ingrDao?.check(ingr.name, truth) }
    }

    fun deleteAllConsumed() {
        thread { ingrDao?.deleteAllConsumed() }
    }

    fun moveBoughtToStorage() {
        thread { ingrDao?.moveBoughtToStorage() }
    }
}