package com.disorganizzazione.spesapp.ui.main

import android.content.Context
import com.disorganizzazione.spesapp.R
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.disorganizzazione.spesapp.db.SpesAppDB
import com.disorganizzazione.spesapp.db.ingredients.GroceryListEntity
import kotlinx.android.synthetic.main.grocery_list_row.view.*
import kotlin.concurrent.thread


/**
 * Crea i ViewHolder (nel numero minimo indispensabile) e gli associa i dati.
 * It creates ViewHolders (as little as possible) and binds the data.
 */

class GroceryListAdapter(private val ingredientList: List<GroceryListEntity>, context: Context?): RecyclerView.Adapter<IngredientViewHolder>() {

    private val ctx = context

    // crea una nuova riga (non si sa bene come) e restituisce il corrispondente ViewHolder
    // it creates a new row (no one knows how) and returns the corresponding ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.grocery_list_row, parent, false)
        return IngredientViewHolder(row)
    }

    override fun getItemCount(): Int {
        return ingredientList.size
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, i: Int) {
        holder.view.ingr_name.text = ingredientList[i].name

        val pair = ingredientList[i].quantity
        holder.view.quant.text = "${pair?.first ?: ""} ${pair?.second ?: ""}"
        holder.view.check_box.isChecked = ingredientList[i].bought
        holder.view.check_box.setOnClickListener {
            var db = SpesAppDB.getInstance(ctx!!)
            val ingrName = holder.view.ingr_name.text.toString()
            val bought = holder.view.check_box.isChecked
            thread {
                db?.groceryListDAO()?.tick(ingrName, bought)
            }
        }
    }
}