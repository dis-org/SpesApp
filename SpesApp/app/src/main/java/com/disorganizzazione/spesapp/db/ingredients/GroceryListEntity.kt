package com.disorganizzazione.spesapp.db.ingredients

import androidx.room.ColumnInfo
import androidx.room.Entity
import org.jetbrains.annotations.NotNull

/* Classe che rappresenta la tabella "LISTA DELLA SPESA" (mostrata nell'activity principale assieme a "DISPENSA").
*  Class representing table "GROCERY LIST" (shown in the main activity alongside "IN STORAGE"). */

@Entity
class GroceryListEntity : IngredientEntity() {
    @ColumnInfo @NotNull
    var bought: Boolean = false
}