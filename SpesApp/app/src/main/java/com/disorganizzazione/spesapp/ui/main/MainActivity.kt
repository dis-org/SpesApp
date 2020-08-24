package com.disorganizzazione.spesapp.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.ViewPager
import com.disorganizzazione.spesapp.R
import com.disorganizzazione.spesapp.db.SpesAppDB
import com.disorganizzazione.spesapp.db.ingredients.commitTransactions
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread


/**
 * The main activity.
 * It contains the interface for the two tabs containing the lists, but not the lists themselves
 * (cf. MainActivityFragment).
 */

class MainActivity : AppCompatActivity() {

    var db: SpesAppDB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // set the layout
        setContentView(R.layout.activity_main)

        // tabs staff (mostly auto-generated)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = view_pager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = tabs
        tabs.setupWithViewPager(viewPager)

        // get an instance of the SQL database
        db = SpesAppDB.getInstance(this)

        // set onClickListeners for the bottom navbar buttons
        commit_btn.setOnClickListener {
            thread {
                commitTransactions(db)
            }
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