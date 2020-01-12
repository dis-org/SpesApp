package com.disorganizzazione.spesapp.add_ingredients

import android.R.attr.startYear
import android.app.DatePickerDialog
import android.opengl.Visibility
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.disorganizzazione.spesapp.R
import com.disorganizzazione.spesapp.db.SpesAppDB
import com.disorganizzazione.spesapp.db.ingredients.GroceryListEntity
import com.disorganizzazione.spesapp.db.ingredients.IngredientEntity
import com.disorganizzazione.spesapp.db.ingredients.StorageEntity
import kotlinx.android.synthetic.main.activity_add_ingredient.*
import java.util.*
import kotlin.concurrent.thread


/**
 * Activity che permette di aggiungere elementi al database degli ingredienti.
 * Activity to add elements to the ingredients database.
 */

class AddIngredientActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    override fun onDateSet(p0: DatePicker?, y: Int, m: Int, d: Int) {
        exp_et.setText("$d/${m + 1}/$y")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ingredient)
        var db = SpesAppDB.getInstance(this)

        fun setIngredientFields(ingr: IngredientEntity) {
            /**
             * Prende in input un ingrediente e ne setta i campi a seconda dei dati
             * contenuti nelle varie EditText.
             * Takes in an ingredient and sets its fields according to the data in
             * the various EditTexts.
             */
            ingr.name = name_et.text.toString()
            ingr.quantity = Pair(quant_et.text.toString().toFloat(),unit_et.text.toString())
            ingr.category = cat_et.text.toString()
            if (ingr is StorageEntity) {
                ingr.useBefore = TODO()
            }
        }

        // riceve il numero della tab. Potremmo anche usare un booleano
        // gets the tab number. We may just use a boolean instead of an int
        when (intent.getIntExtra("tab",0)) {
            1 -> {
                setTitle(R.string.add_grocery)
                exp_et.visibility = View.GONE
                add_btn.setOnClickListener {
                    val item = GroceryListEntity()
                    setIngredientFields(item)
                    thread { db?.groceryListDAO()?.insertInGroceryList(item) }
                }
            }
            2 -> {
                setTitle(R.string.add_storage)

                val cal = Calendar.getInstance()
                val datePickerDialog = DatePickerDialog(this,
                    this@AddIngredientActivity,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH))
                exp_et.setOnClickListener {
                    datePickerDialog.show()
                }

                add_btn.setOnClickListener {
                    val item = StorageEntity()
                    setIngredientFields(item)
                    thread { db?.storageDAO()?.insertInStorage(item) }
                }
            }
            else -> error(R.string.never_shown)
        }
    }
}
