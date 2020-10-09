package boh.harisont.spesapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import boh.harisont.spesapp.R
import kotlinx.android.synthetic.main.activity_ingredient_details.*

class IngredientDetails : AppCompatActivity() {

    private lateinit var ingrViewModel: IngredientViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // set layout & title
        setContentView(R.layout.activity_ingredient_details)
        title = resources.getString(R.string.details)

        // get name of ingredient to display, query the db and show it
        val ingrName = intent.getStringExtra("INGR_NAME")
        ingrViewModel = ViewModelProvider(this).get(IngredientViewModel::class.java)
        var ingr = ingrViewModel.select(ingrName!!)
        ingr_name_d.setText(ingr.name, TextView.BufferType.EDITABLE)
        use_before_d.setText(ingr.useBefore.toString(), TextView.BufferType.EDITABLE)
        category_d.setText(ingr.category, TextView.BufferType.EDITABLE)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_appbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.close_d) {
            // TODO: when
        }
        return super.onOptionsItemSelected(item)
    }
}