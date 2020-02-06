package com.disorganizzazione.spesapp.ui.main

import android.view.ContextMenu
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.disorganizzazione.spesapp.R

class IngredientViewHolder(val view: View): RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {

    init {
        view.setOnCreateContextMenuListener(this)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        view: View?,
        info: ContextMenu.ContextMenuInfo?
    ) {
        menu?.add(R.string.delete)
    }
}