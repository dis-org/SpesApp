package com.disorganizzazione.spesapp.db.ingredients

import android.database.sqlite.SQLiteConstraintException
import com.disorganizzazione.spesapp.db.SpesAppDB

fun moveToStorage(db: SpesAppDB?, ingr: GroceryListEntity) {
    db?.groceryListDAO()?.deleteFromGroceryList(ingr)
    val se = ingr.toStorageEntity()
    se.done = false
    try {
        db?.storageDAO()?.insertInStorage(se)
    } catch (e: SQLiteConstraintException) {
        // I don't think the user needs to know about duplicates
        println("Skipped ${se.name} because it was in storage already")
    }
}

fun commitTransactions(db: SpesAppDB?) {
    val groceryList = db?.groceryListDAO()?.selectAllInGroceryList() ?: emptyList()
    groceryList.map { ingr -> if (ingr.done) moveToStorage(db, ingr) }
    val storageList = db?.storageDAO()?.selectAllInStorage() ?: emptyList()
    storageList.map { ingr -> if (ingr.done) db?.storageDAO()?.deleteFromStorage(ingr) }
}