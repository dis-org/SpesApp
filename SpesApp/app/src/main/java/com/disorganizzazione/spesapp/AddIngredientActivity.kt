package com.disorganizzazione.spesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class AddIngredientActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ingredient)
        val tab = intent.getIntExtra("tab",0)
        println("Coming here from tab $tab")
    }
}
