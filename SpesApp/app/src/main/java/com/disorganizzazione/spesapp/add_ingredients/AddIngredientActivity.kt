package com.disorganizzazione.spesapp.add_ingredients

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import com.disorganizzazione.spesapp.R
import kotlinx.android.synthetic.main.activity_add_ingredient.*

class AddIngredientActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ingredient)

        // riceve il numero della tab. Potremmo anche usare un booleano
        // gets the tab number. We may just use a boolean instead of an int

        when (intent.getIntExtra("tab",0)) {
            1 -> {
                setTitle(R.string.add_grocery)
            }
            2 -> {
                setTitle(R.string.add_storage)
                val dateEditText = EditText(this)
                dateEditText.setHint(R.string.use_bf)
                dateEditText.inputType = InputType.TYPE_DATETIME_VARIATION_DATE
                linear_layout.addView(dateEditText)
            }
            else -> error(R.string.never_shown)
        }
    }
}
