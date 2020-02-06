package com.disorganizzazione.spesapp.ui.main

import android.content.Context
import com.disorganizzazione.spesapp.R
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.disorganizzazione.spesapp.db.SpesAppDB
import com.disorganizzazione.spesapp.db.ingredients.GroceryListEntity
import com.disorganizzazione.spesapp.db.ingredients.IngredientEntity
import com.disorganizzazione.spesapp.db.ingredients.StorageEntity
import com.disorganizzazione.spesapp.utils.dateFormat
import kotlinx.android.synthetic.main.grocery_list_row.view.*
import kotlinx.android.synthetic.main.grocery_list_row.view.ingr_name
import kotlinx.android.synthetic.main.grocery_list_row.view.quant
import kotlinx.android.synthetic.main.storage_list_row.view.*
import kotlin.concurrent.thread


/**
 * Crea i ViewHolder (nel numero minimo indispensabile) e gli associa i dati.
 * It creates ViewHolders (as little as possible) and binds the data.
 */

class IngredientAdapter(private val ingredientList: MutableList<IngredientEntity>, context: Context?): RecyclerView.Adapter<IngredientViewHolder>() {

    private val ctx = context

    // crea una nuova riga (non si sa bene come) e restituisce il corrispondente ViewHolder
    // it creates a new row (no one knows how) and returns the corresponding ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val row = when (ingredientList[0] is GroceryListEntity) { // a bit too hacky. TODO: find a better way
            true ->
                LayoutInflater.from(parent.context).inflate(R.layout.grocery_list_row, parent, false)
            false -> LayoutInflater.from(parent.context).inflate(R.layout.storage_list_row, parent, false)
        }
        return IngredientViewHolder(row)
    }

    override fun getItemCount(): Int {
        return ingredientList.size
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, i: Int) {

        // get current ingredient
        val ingredient =  ingredientList[i]

        // display name
        holder.view.ingr_name.text = ingredient.name

        // display quantity
        val pair = ingredient.quantity
        holder.view.quant.text = "${pair?.first ?: ""} ${pair?.second ?: ""}"

        // manage checkbox (GroceryListEntities only)
        if (ingredient is GroceryListEntity) {
            holder.view.check_box.isChecked = ingredient.bought

            holder.view.check_box.setOnClickListener {
                var db = SpesAppDB.getInstance(ctx!!)
                val ingrName = holder.view.ingr_name.text.toString()
                val bought = holder.view.check_box.isChecked
                thread {
                    db?.groceryListDAO()?.tick(ingrName, bought)
                }
            }
        }

        // display expiration day (StorageEntities only)
        if (ingredient is StorageEntity) {
            val date = ingredient.useBefore
            if (date != null) {
                holder.view.use_bf.text = "${dateFormat.format(ingredient.useBefore ?: "")}"
            }
        }
    }

    fun removeIngredient(i: Int) {
        val ingredient = ingredientList[i]
        ingredientList.removeAt(i)
        notifyDataSetChanged()
        var db = SpesAppDB.getInstance(ctx!!)
        if (ingredient is GroceryListEntity)
            thread {
                db?.groceryListDAO()?.deleteFromGroceryList(ingredient)
            }
        else if (ingredient is StorageEntity)
            thread {
                db?.storageDAO()?.deleteFromStorage(ingredient)
            }
    }
}