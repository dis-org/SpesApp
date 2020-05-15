package com.disorganizzazione.spesapp.ui.main

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.disorganizzazione.spesapp.R
import com.disorganizzazione.spesapp.utils.getContent
import com.disorganizzazione.spesapp.db.SpesAppDB
import com.disorganizzazione.spesapp.db.ingredients.GroceryListEntity
import com.disorganizzazione.spesapp.db.ingredients.StorageEntity
import kotlinx.android.synthetic.main.dialog_add_ingredient.*
import kotlinx.android.synthetic.main.ingredient.*
import kotlin.concurrent.thread

class AddIngredientDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val db = SpesAppDB.getInstance(activity!!.applicationContext)
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val tab = arguments?.getInt("tab")
            // set the layout for the dialog
            builder.setView(inflater.inflate(R.layout.dialog_add_ingredient, null))
                // set the button(s)
                .setPositiveButton(R.string.add_btn,
                    DialogInterface.OnClickListener { _,_ ->
                        // perform add to db query
                        when (tab) {
                            1 -> {
                                val ingredient = GroceryListEntity()
                                ingredient.name = new_ingr_name.getContent() ?: ""
                                thread {
                                    db?.groceryListDAO()?.insertInGroceryList(ingredient)
                                }
                            }
                            2 -> {
                                val ingredient = StorageEntity()
                                ingredient.name = new_ingr_name.getContent() ?: ""
                                thread {
                                    db?.storageDAO()?.insertInStorage(ingredient)
                                }
                            }
                        }
                    })
                // no negative button
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}