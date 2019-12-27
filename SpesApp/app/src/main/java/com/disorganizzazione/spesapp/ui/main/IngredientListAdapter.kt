package com.disorganizzazione.spesapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.disorganizzazione.spesapp.IngredientEntity
import com.disorganizzazione.spesapp.R
import kotlinx.android.synthetic.main.ingredient_row.view.*

/**
 * Crea i ViewHolder (nel numero minimo indispensabile) e gli associa i dati.
 * It creates ViewHolders (as little as possible) and binds the data.
 */

class IngredientListAdapter(private val ingredientList: List<IngredientEntity>): RecyclerView.Adapter<IngredientViewHolder>() {

    // crea una nuova riga (non si sa bene come) e restituisce il corrispondente ViewHolder
    // it creates a new row (no one knows how) and returns the corresponding ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.ingredient_row, parent, false)
        return IngredientViewHolder(row)
    }

    override fun getItemCount(): Int {
        return ingredientList.size
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, i: Int) {
        holder.view.ingredientName.text = ingredientList[i].name
        // TODO: update as soon as the row layout is more interesting
    }
}