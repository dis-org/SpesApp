package com.disorganizzazione.spesapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.disorganizzazione.spesapp.R
import com.disorganizzazione.spesapp.db.ingredients.StorageEntity
import com.disorganizzazione.spesapp.utils.dateFormat
import kotlinx.android.synthetic.main.grocery_list_row.view.ingr_name
import kotlinx.android.synthetic.main.grocery_list_row.view.quant
import kotlinx.android.synthetic.main.storage_list_row.view.*

/**
 * Crea i ViewHolder (nel numero minimo indispensabile) e gli associa i dati.
 * It creates ViewHolders (as little as possible) and binds the data.
 */

class StorageListAdapter(private val ingredientList: List<StorageEntity>): RecyclerView.Adapter<IngredientViewHolder>() {

    // crea una nuova riga (non si sa bene come) e restituisce il corrispondente ViewHolder
    // it creates a new row (no one knows how) and returns the corresponding ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.storage_list_row, parent, false)
        return IngredientViewHolder(row)
    }

    override fun getItemCount(): Int {
        return ingredientList.size
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, i: Int) {
        holder.view.ingr_name.text = ingredientList[i].name

        val pair = ingredientList[i].quantity
        holder.view.quant.text = "${pair?.first ?: ""} ${pair?.second ?: ""}"

        val date = ingredientList[i].useBefore
        if (date != null) {
            holder.view.use_bf.text = "${dateFormat.format(ingredientList[i].useBefore)}"
        }
    }
}