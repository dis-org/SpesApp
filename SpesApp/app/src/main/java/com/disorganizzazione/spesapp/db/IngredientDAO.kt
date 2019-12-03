package com.disorganizzazione.spesapp.db

import androidx.room.*

/* Interfaccia per le operazioni sul db degli ingredienti.
*  Interface for the operations on the ingredients database. */

@Dao
interface IngredientDAO {

    // nota: MySQL non ha i booleani
    // note: MySQL does not have booleans

    @Query("SELECT * FROM IngredientEntity WHERE inStorage == 1")
    fun selectInStorage(): List<IngredientEntity>

    @Query("SELECT * FROM IngredientEntity WHERE inStorage == 0")
    fun selectShoppingList(): List<IngredientEntity>

    // non sono sicura di quale sia la migliore strategia
    // not sure what the best strategy is
    // TODO: scelgiere strategia/decide on strategy (ABORT?!)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIngredient(ingredient: IngredientEntity)

    @Delete
    fun deleteIngredient(ingredient: IngredientEntity)

    // TODO: altre query/more queries

    // NB: quando un ingrediente viene comperato, va aggiunto alla dispensa automaticamente.
    // Non so se sia meglio farlo con una query apposita che modifica la tabella o componendo 2 query.

    // note: when an ingredient is bought, it has to be added to storage automatically.
    // Idk if this should be achieved via a new query or composing 2 queries.
}