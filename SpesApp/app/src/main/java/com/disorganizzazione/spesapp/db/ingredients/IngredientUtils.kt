package com.disorganizzazione.spesapp.db.ingredients

import com.disorganizzazione.spesapp.db.SpesAppDB

fun moveToStorage(db: SpesAppDB?, ingr: GroceryListEntity) {
    db?.groceryListDAO()?.deleteFromGroceryList(ingr)
    val se = ingr.toStorageEntity()
    se.done = false
    db?.storageDAO()?.insertInStorage(se)
}

fun commitTransactions(db: SpesAppDB?) {
    val groceryList = db?.groceryListDAO()?.selectAllInGroceryList() ?: emptyList()
    for (ingr in groceryList) {
        if (ingr.done)
            moveToStorage(db,ingr)
    }
    val storageList = db?.storageDAO()?.selectAllInStorage() ?: emptyList()
    for (ingr in storageList) {
        if (ingr.done)
            db?.storageDAO()?.deleteFromStorage(ingr)
    }
}