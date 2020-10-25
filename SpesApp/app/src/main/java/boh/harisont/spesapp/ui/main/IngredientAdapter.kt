package boh.harisont.spesapp.ui.main

import android.app.Application
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import boh.harisont.spesapp.db.SpesAppDB
import boh.harisont.spesapp.R
import boh.harisont.spesapp.db.ingredient.IngredientEntity
import kotlinx.android.synthetic.main.ingredient_item.view.*
import java.util.*
import kotlin.concurrent.thread


/**
 * Creates the IngredientViewHolders and binds the data.
 */

class IngredientAdapter(ctx: Context): RecyclerView.Adapter<IngredientAdapter.IngredientHolder>() {
    private var ingrList = emptyList<IngredientEntity>()
    private val ctx = ctx

    class IngredientHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val checkBox: CheckBox = itemView.check_box
        val ingrName: TextView = itemView.ingr_name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientHolder {
        // set individual ingredient layout
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.ingredient_item, parent, false)
        return IngredientHolder(itemView)
    }

    override fun onBindViewHolder(holder: IngredientHolder, i: Int) {
        // put data into the views (name + checkbox)
        val ingr = ingrList[i]
        holder.ingrName.text = ingr.name
        val useBefore = ingr.useBefore
        if (useBefore != null) {
            when {
                useBefore < Date() ->
                    holder.ingrName.setTextColor(ContextCompat.getColor(this.ctx, R.color.expired))
                useBefore == Date() ->
                    holder.ingrName.setTextColor(ContextCompat.getColor(this.ctx, R.color.expiring))
                else ->
                    holder.ingrName.setTextColor(ContextCompat.getColor(this.ctx, R.color.notExpiring))
            }
        }
        holder.checkBox.isChecked = ingr.checked
        // no idea why my intuition worked but for once it did
        holder.checkBox.setOnClickListener {
            IngredientViewModel(application = Application()).check(ingr, !ingr.checked)
        }
        holder.ingrName.setOnClickListener {
            val intent = Intent(ctx, IngredientDetails::class.java).apply {
                putExtra("INGR", ingr)
            }
            startActivity(ctx,intent,null)
        }
    }

    override fun getItemCount(): Int {
        return ingrList.size
    }

    fun setIngredients(ingrList: List<IngredientEntity>) {
        this.ingrList = ingrList
        notifyDataSetChanged() // can do better with more granular (item.specific) notify methods
    }
}