package com.disorganizzazione.spesapp.add_ingredients

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import com.disorganizzazione.spesapp.R
import com.disorganizzazione.spesapp.db.SpesAppDB
import com.disorganizzazione.spesapp.db.ingredients.GroceryListEntity
import com.disorganizzazione.spesapp.db.ingredients.IngredientEntity
import com.disorganizzazione.spesapp.db.ingredients.StorageDAO
import com.disorganizzazione.spesapp.db.ingredients.StorageEntity
import kotlinx.android.synthetic.main.activity_add_ingredient.*
import kotlin.concurrent.thread

class AddIngredientActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ingredient)
        var db = SpesAppDB.getInstance(this)

        fun addButton(): Button {
            val addButton = Button(this)
            addButton.setText(R.string.add_btn)
            main_layout.addView(addButton)
            return addButton
        }

        fun setIngredientFields(ingr: IngredientEntity): IngredientEntity {
            TODO()
        }

        // riceve il numero della tab. Potremmo anche usare un booleano
        // gets the tab number. We may just use a boolean instead of an int
        when (intent.getIntExtra("tab",0)) {
            1 -> {
                setTitle(R.string.add_grocery)

                addButton().setOnClickListener {
                    // TODO: manage all fields and abstract where possible (setIngredientFields)
                    val item = GroceryListEntity()
                    item.name = name_et.text.toString()
                    thread { db?.groceryListDAO()?.insertInGroceryList(item) }
                }
            }
            2 -> {
                setTitle(R.string.add_storage)

                // aggiunge campo per data di scadenza
                // adds field for expiry date
                val dateEditText = EditText(this)
                dateEditText.setHint(R.string.use_bf)
                dateEditText.inputType = InputType.TYPE_DATETIME_VARIATION_DATE

                // TODO: add

                main_layout.addView(dateEditText)
                addButton().setOnClickListener {
                    // thread { db?.storageDAO()?.insertInStorage(item) }
                }
            }
            else -> error(R.string.never_shown)
        }
    }
}
