package boh.harisont.spesapp.repo

import android.app.Application
import androidx.lifecycle.LiveData
import boh.harisont.spesapp.db.SpesAppDB
import boh.harisont.spesapp.db.ingredient.IngredientDao
import boh.harisont.spesapp.db.ingredient.IngredientEntity
import kotlin.concurrent.thread

class IngredientRepository(
    application: Application,
    private var groceryList: LiveData<List<IngredientEntity>>?,
    private var storageList: LiveData<List<IngredientEntity>>?,
    private var ingrDao: IngredientDao?) {
    init {
        val db = SpesAppDB.getInstance(application)
        ingrDao = db?.ingredientDao()
        groceryList = ingrDao?.selectGroceryList()
        storageList = ingrDao?.selectStorageList()
    }

    fun selectGroceryList(): LiveData<List<IngredientEntity>>? {
        return groceryList
    }

    fun selectStorageList(): LiveData<List<IngredientEntity>>? {
        return storageList
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

}