package com.disorganizzazione.spesapp.ui.main

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.disorganizzazione.spesapp.R

class AddIngredientDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater;
            // set the layout for the dialog
            builder.setView(inflater.inflate(R.layout.dialog_add_ingredient, null))
                // set the button(s)
                .setPositiveButton(R.string.add_btn,
                    DialogInterface.OnClickListener { dialog, id ->
                        // perform add to db query
                    })
                // no negative button
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
