package com.disorganizzazione.spesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class AddIngredientActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ingredient)

        // riceve il numero della tab. Potremmo anche usare un booleano
        // gets the tab number. We may just use a boolean instead of an int

        when (intent.getIntExtra("tab",0)) {
            1 -> setTitle(R.string.add_grocery)
            2 -> setTitle(R.string.add_storage)
            else -> error(R.string.never_shown)
        }
    }
}
