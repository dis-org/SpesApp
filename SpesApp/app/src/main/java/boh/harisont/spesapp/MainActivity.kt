package boh.harisont.spesapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.core.view.get
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import boh.harisont.spesapp.db.ingredient.IngredientEntity
import boh.harisont.spesapp.ui.main.IngredientAdapter
import boh.harisont.spesapp.ui.main.IngredientViewModel
import boh.harisont.spesapp.ui.main.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Main activity,
 * where the DB is created,
 * containing the interface for the two tabs containing the lists
 * and for the bottom navbar
 */

class MainActivity : AppCompatActivity() {

    private lateinit var ingrViewModel: IngredientViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // set layout
        setContentView(R.layout.activity_main)

        // setup tabs
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        // get ingredient view model to be able to query ingr db from the bottom navbar buttons
        ingrViewModel = ViewModelProvider(this).get(IngredientViewModel::class.java)

        // set listeners for navbar
        commit_btn.setOnClickListener {
            ingrViewModel.deleteAllConsumed()
            ingrViewModel.moveBoughtToStorage()
        }

        share_btn.setOnClickListener {
            ingrViewModel.selectGroceryList()?.observe(
                this,
                Observer<List<IngredientEntity>> {
                    // I'm a functional programmer, am I not?
                    val msg = it
                        .map { ingr -> "-" + ingr.name } // transform into list item
                        .fold(
                            resources.getString(R.string.share_message),
                            {ingr1,ingr2 -> ingr1 + "\n" + ingr2}) // separate items
                    val sendIntent: Intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, msg)
                        type = "text/plain"
                    }
                    startActivity(sendIntent)
                })
        }

        query_btn.setOnClickListener {
            Toast.makeText(this, R.string.coming_soon, Toast.LENGTH_LONG).show()
        }

        cookbook_btn.setOnClickListener {
            Toast.makeText(this, R.string.coming_soon, Toast.LENGTH_LONG).show()
        }

    }
}