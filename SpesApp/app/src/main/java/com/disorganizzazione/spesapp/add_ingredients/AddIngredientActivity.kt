package com.disorganizzazione.spesapp.add_ingredients

import android.R.attr.startYear
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.InputType
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

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ingredient)
        var db = SpesAppDB.getInstance(this)


        fun addButton(): Button {
            /**
             * Aggiunge il bottone "ADD" all'interfaccia grafica.
             * Questo va fatto dinamicamente (credo) perché il suo posizionamento varia a seconda
             * della tabella su cui si lavora (storage ha un campo in più).
             * Adds the "ADD" button to the GUI.
             * This (probably) must be done dynamically because its position varies
             * according to the table we are working on (storage has one column more).
             */
            val addButton = Button(this)
            addButton.setText(R.string.add_btn)
            main_layout.addView(addButton)
            return addButton
        }

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
        }

        // riceve il numero della tab. Potremmo anche usare un booleano
        // gets the tab number. We may just use a boolean instead of an int
        when (intent.getIntExtra("tab",0)) {
            1 -> {
                setTitle(R.string.add_grocery)

                addButton().setOnClickListener {
                    val item = GroceryListEntity()
                    setIngredientFields(item)
                    thread { db?.groceryListDAO()?.insertInGroceryList(item) }
                }
            }
            2 -> {
                setTitle(R.string.add_storage)

                /// TODO: function
                // aggiunge campo per data di scadenza
                // adds field for expiry date
                val dateEditText = EditText(this)
                dateEditText.setHint(R.string.use_bf)
                dateEditText.inputType = InputType.TYPE_DATETIME_VARIATION_DATE
                main_layout.addView(dateEditText)

                val cal = Calendar.getInstance()
                val y = cal.get(Calendar.YEAR)
                val m = cal.get(Calendar.MONTH)
                val d = cal.get(Calendar.DAY_OF_MONTH)
                val datePickerDialog = DatePickerDialog(this, this@AddIngredientActivity, y, m, d)

                dateEditText.setOnClickListener {
                    // TODO: prevent keyboard from showing
                    datePickerDialog.show()
                }

                addButton().setOnClickListener {
                    val item = StorageEntity()
                    setIngredientFields(item)
                    //TODO once format is decided: item.useBefore = dateEditText.text.toString()
                    println("DATE: ${dateEditText.text}")
                    thread { db?.storageDAO()?.insertInStorage(item) }
                }
            }
            else -> error(R.string.never_shown)
        }
    }
}
