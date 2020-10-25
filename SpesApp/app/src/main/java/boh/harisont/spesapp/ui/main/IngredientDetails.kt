package boh.harisont.spesapp.ui.main

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import boh.harisont.spesapp.R
import boh.harisont.spesapp.db.ingredient.IngredientEntity
import boh.harisont.spesapp.utils.dateFormat
import kotlinx.android.synthetic.main.activity_ingredient_details.*
import java.util.*

class IngredientDetails : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private lateinit var ingrViewModel: IngredientViewModel
    private lateinit var ingr: IngredientEntity

    override fun onDateSet(p0: DatePicker?, y: Int, m: Int, d: Int) {
        use_before_d.setText("$d/${m + 1}/$y")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // set layout & title
        setContentView(R.layout.activity_ingredient_details)
        title = resources.getString(R.string.details)

        // get name of ingredient to display, query the db and show it
        ingr = intent.getSerializableExtra("INGR") as IngredientEntity
        ingr_name_d.setText(ingr.name, TextView.BufferType.EDITABLE)
        val useBefore =
            if (ingr.useBefore != null) dateFormat.format(ingr.useBefore!!)
            else getString(R.string.no_use_before)
        use_before_d.text = useBefore
        category_d.setText(ingr.category, TextView.BufferType.EDITABLE)

        // override onClick for date
        val cal = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            this,
            this@IngredientDetails,
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH))
        use_before_d.setOnClickListener {
            datePickerDialog.show()
        }

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
            R.id.save_d -> {
                ingrViewModel.update(
                    ingr.name,
                    ingr_name_d.text.toString(),
                    category_d.text.toString(),
                    dateFormat.parse(use_before_d.text.toString())
                )
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}