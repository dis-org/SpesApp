package com.disorganizzazione.spesapp.ui.main

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.disorganizzazione.spesapp.R
import com.disorganizzazione.spesapp.db.SpesAppDB
import com.disorganizzazione.spesapp.db.ingredients.GroceryListEntity
import com.disorganizzazione.spesapp.db.ingredients.StorageEntity
import com.disorganizzazione.spesapp.utils.getContent
import kotlin.concurrent.thread

/**
 * Dialog for adding ingredients by name.
 * The DialogFragment class sucks so do read my comments before editing this file.
 */

class AddIngredientDialogFragment : DialogFragment() {

    // this is called onCreateDialog but it actually CREATES the dialog
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val inflater = requireActivity().layoutInflater
        // set layout
        builder.setView(inflater.inflate(R.layout.dialog_add_ingredient, null))
        // build dialog
        return builder.create()
    }

    // this is called onStart but it actually runs as soon as the dialog is SHOWN
    override fun onStart() {
        super.onStart()
        val db = SpesAppDB.getInstance(activity!!.applicationContext)
        // get tab number to know which table to work on
        val tab = arguments?.getInt("tab")
        // find the view corresponding to the text field (no idea why one NEEDS findViewById here)
        val textField = dialog.findViewById(R.id.new_ingr_name) as EditText?
        // find the custom button
        val quickAddBtn = dialog.findViewById(R.id.quickadd_btn) as Button?
        quickAddBtn?.setOnClickListener {
            val ingrName = textField?.getContent()
            if (ingrName != null) {
                when (tab) {
                    1 -> {
                        val newIngr = GroceryListEntity()
                        newIngr.name = ingrName
                        thread {
                            db?.groceryListDAO()?.insertInGroceryList(newIngr)
                        }
                    }
                    2 -> {
                        val newIngr = StorageEntity()
                        newIngr.name = ingrName
                        thread {
                            db?.storageDAO()?.insertInStorage(newIngr)
                        }
                    }
                }
                dialog.dismiss()
                Toast.makeText(activity, R.string.add_success, Toast.LENGTH_LONG).show()
            }
            else Toast.makeText(activity, R.string.add_failure, Toast.LENGTH_LONG).show()

        }
    }

}