package com.disorganizzazione.spesapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.disorganizzazione.spesapp.IngredientEntity
import com.disorganizzazione.spesapp.IngredientName

/* Classe che rappresenta la tabella "LISTA DELLA SPESA" (mostrata nell'activity principale assieme a "DISPENSA").
*  Class representing table "GROCERY LIST" (shown in the main activity alongside "IN STORAGE"). */

@Entity
class GroceryListEntity : IngredientEntity()