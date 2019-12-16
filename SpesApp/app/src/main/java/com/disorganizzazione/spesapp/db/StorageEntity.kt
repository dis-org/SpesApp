package com.disorganizzazione.spesapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.disorganizzazione.spesapp.IngredientName
import org.jetbrains.annotations.NotNull
import java.util.*

/* Classe che rappresenta la tabella "DISPENSA" (mostrata nell'activity principale assieme a "LISTA DELLA SPESA").
*  Class representing table "IN STORAGE" (shown in the main activity alongside "GROCERY LIST"). */

@Entity
class StorageEntity {
    @PrimaryKey
    var name : IngredientName = ""

    // Data di scadenza: è un Long perché SQLite non ha manco le date...
    // Expiration date: it is a Long because SQLite does not even have dates...
    @ColumnInfo
    var useBefore : Date? = null

    // TODO: cambiare nome e tipo in qualcosa di più flessibile (String?)
    // TODO: change name and type to something more flexible (String?)
    @ColumnInfo
    var portions : Int? = null
}