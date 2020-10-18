package boh.harisont.spesapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import boh.harisont.spesapp.R
import boh.harisont.spesapp.db.ingredient.IngredientEntity
import kotlinx.android.synthetic.main.activity_ingredient_details.*

class IngredientDetails : AppCompatActivity() {

    private lateinit var ingrViewModel: IngredientViewModel
    private lateinit var ingr: IngredientEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // set layout & title
        setContentView(R.layout.activity_ingredient_details)
        title = resources.getString(R.string.details)

        // get name of ingredient to display, query the db and show it
        ingr = intent.getSerializableExtra("INGR") as IngredientEntity
        ingr_name_d.setText(ingr.name, TextView.BufferType.EDITABLE)
        val useBefore = if (ingr.useBefore != null) ingr.useBefore.toString() else ""
        use_before_d.setText(useBefore, TextView.BufferType.EDITABLE)
        category_d.setText(ingr.category, TextView.BufferType.EDITABLE)

        ingrViewModel = ViewModelProvider(this).get(IngredientViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_appbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.delete_d -> {
                ingrViewModel.delete(ingr)
                Toast.makeText(application, R.string.delete_success,Toast.LENGTH_LONG).show()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}