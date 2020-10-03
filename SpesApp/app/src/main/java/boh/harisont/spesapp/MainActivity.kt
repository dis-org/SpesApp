package boh.harisont.spesapp

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
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
            Toast.makeText(this, R.string.coming_soon, Toast.LENGTH_LONG).show()
        }

        query_btn.setOnClickListener {
            Toast.makeText(this, R.string.coming_soon, Toast.LENGTH_LONG).show()
        }

        cookbook_btn.setOnClickListener {
            Toast.makeText(this, R.string.coming_soon, Toast.LENGTH_LONG).show()
        }

    }
}