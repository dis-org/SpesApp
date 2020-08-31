package boh.harisont.spesapp

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import boh.harisont.spesapp.db.SpesAppDB
import boh.harisont.spesapp.ui.main.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

/**
 * Main activity,
 * where the DB is created,
 * containing the interface for the two tabs containing the lists
 * and for the bottom navbar
 */

class MainActivity : AppCompatActivity() {

    var db: SpesAppDB? = null

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

        // get an instance of the DB
        db = SpesAppDB.getInstance(this)

        // set listeners for navbar
        commit_btn.setOnClickListener {
            // TODO: commit db transaction and update (mutable?) list, cf. legacy code
            Toast.makeText(this, R.string.coming_soon, Toast.LENGTH_LONG).show()
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