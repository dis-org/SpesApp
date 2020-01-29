package com.disorganizzazione.spesapp.db.ingredients

import androidx.room.ColumnInfo
import androidx.room.Entity
import java.util.*

/* Classe che rappresenta la tabella "DISPENSA" (mostrata nell'activity principale assieme a "LISTA DELLA SPESA").
*  Class representing table "IN STORAGE" (shown in the main activity alongside "GROCERY LIST"). */

@Entity
class StorageEntity : IngredientEntity() {

    // Data di scadenza: è un Long perché SQLite non ha manco le date...
    // Expiration date: it is a Long because SQLite does not even have dates...
    @ColumnInfo
    var useBefore: Date? = null
}