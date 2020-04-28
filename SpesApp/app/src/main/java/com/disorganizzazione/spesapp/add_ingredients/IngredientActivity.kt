package com.disorganizzazione.spesapp.add_ingredients

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.disorganizzazione.spesapp.R
import com.disorganizzazione.spesapp.utils.getContent
import com.disorganizzazione.spesapp.db.SpesAppDB
import com.disorganizzazione.spesapp.db.ingredients.GroceryListEntity
import com.disorganizzazione.spesapp.db.ingredients.IngredientEntity
import com.disorganizzazione.spesapp.db.ingredients.StorageEntity
import com.disorganizzazione.spesapp.utils.dateFormat
import kotlinx.android.synthetic.main.activity_add_ingredient.*
import java.util.*
import kotlin.concurrent.thread


/**
 * Ingredient details activity. It allows the user to view and modify all fields.
 * TODO: rewrite both the code and the layout according to new prototype
 */

class IngredientActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    override fun onDateSet(p0: DatePicker?, y: Int, m: Int, d: Int) {
        exp_et.setText("$d/${m + 1}/$y")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ingredient)
        var db = SpesAppDB.getInstance(this)

        fun setIngredientFields(ingr: IngredientEntity): Boolean {
            /**
             * Take in an ingredient and set its fields (excepts "done") according to the data in
             * the various EditTexts.
             */
            val quant = quant_et.getContent()
            val useBefore = exp_et.getContent()
            ingr.name = name_et.getContent() ?: return false
            ingr.quantity = if (quant != null) Pair(quant.toFloat(),unit_et.getContent()) else null
            ingr.category = cat_et.getContent()
            if (ingr is StorageEntity) {
                ingr.useBefore = if (useBefore != null) dateFormat.parse(useBefore) else null
            }
            return true
        }

        // gets the tab number. Could just use a boolean, but for the moment it's like this for
        // clarity
        when (intent.getIntExtra("tab",0)) {
            1 -> {
                setTitle(R.string.add_grocery)
                exp_et.visibility = View.GONE
                add_btn.setOnClickListener {
                    val item = GroceryListEntity()
                    if (setIngredientFields(item)) {
                        thread { db?.groceryListDAO()?.insertInGroceryList(item) }
                        Toast.makeText(this, R.string.add_success, Toast.LENGTH_LONG).show()
                        this.finish()
                    }
                    else Toast.makeText(this, R.string.add_failure, Toast.LENGTH_LONG).show()
                }
            }
            2 -> {
                setTitle(R.string.add_storage)

                val cal = Calendar.getInstance()
                val datePickerDialog = DatePickerDialog(this,
                    this@IngredientActivity,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH))
                exp_et.setOnClickListener {
                    datePickerDialog.show()
                }

                add_btn.setOnClickListener {
                    val item = StorageEntity()
                    if (setIngredientFields(item)) {
                        thread { db?.storageDAO()?.insertInStorage(item) }
                        Toast.makeText(this, R.string.add_success, Toast.LENGTH_LONG).show()
                        this.finish()
                    }
                    else Toast.makeText(this, R.string.add_failure, Toast.LENGTH_LONG).show()
                }
            }
            else -> error(R.string.never_shown)
        }
    }
}
