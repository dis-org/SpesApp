package boh.harisont.spesapp.ui.main

import android.app.AlertDialog
import android.app.Dialog
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import boh.harisont.spesapp.R
import boh.harisont.spesapp.db.SpesAppDB
import boh.harisont.spesapp.db.ingredient.IngredientEntity
import boh.harisont.spesapp.utils.getContent
import kotlinx.android.synthetic.main.dialog_add_ingredient.*
import kotlin.concurrent.thread

/**
 * Dialog for quickly adding ingredients (name only).
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
        // TODO: see if a wrapper class can be used instead of doing this mess
        val db = SpesAppDB.getInstance(activity!!.applicationContext)
        // get tab number to know which "list" the item belongs to
        val tab = arguments?.getInt("tab")
        val textField = dialog?.new_ingr_name
        val quickAddBtn = dialog?.quickadd_btn
        quickAddBtn?.setOnClickListener {
            val ingrName = textField?.getContent()
            if (ingrName != null) {
                // set bought according to tab number (definitely hacky)
                val newIngr = IngredientEntity(ingrName, bought = tab != 1)
                thread {
                    try {
                        db?.ingredientDao()?.insert(newIngr)
                    } catch (e: SQLiteConstraintException) {
                        activity!!.runOnUiThread {
                            Toast.makeText(
                                activity,
                                // TODO: debug wrong error message shown
                                R.string.add_failure_duplicate,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
                dialog?.dismiss()
                Toast.makeText(activity, R.string.add_success, Toast.LENGTH_LONG).show()
            }
            else Toast.makeText(activity, R.string.add_failure_no_key, Toast.LENGTH_LONG).show()
        }
    }
}