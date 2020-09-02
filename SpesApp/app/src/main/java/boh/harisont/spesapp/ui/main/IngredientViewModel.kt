package boh.harisont.spesapp.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import boh.harisont.spesapp.db.ingredient.IngredientEntity
import boh.harisont.spesapp.repo.IngredientRepository

/**
 * View model for the ingredient list(s).
 * It holds the data and prepares it for the GUI handling configuration changes such as rotations.
 */

// extend AndroidViewModel instead of just ViewModel bc we need the application context
class IngredientViewModel(application: Application) : AndroidViewModel(application) {
    private val ingrRepo = IngredientRepository(application)
    private val groceryList = ingrRepo.selectGroceryList()
    private val storageList = ingrRepo.selectStorageList()

    fun selectGroceryList(): LiveData<List<IngredientEntity>>? {
        return groceryList
    }

    fun selectStorageList(): LiveData<List<IngredientEntity>>? {
        return storageList
    }

    fun insert(ingr: IngredientEntity) {
        ingrRepo.insert(ingr)
    }

    fun delete(ingr: IngredientEntity) {
        ingrRepo.delete(ingr)
    }

    fun check(ingr: IngredientEntity, truth: Boolean) {
        ingrRepo.check(ingr, truth)
    }

}