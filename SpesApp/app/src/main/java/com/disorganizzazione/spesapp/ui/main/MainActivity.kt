package com.disorganizzazione.spesapp.ui.main

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.disorganizzazione.spesapp.R
import com.disorganizzazione.spesapp.db.SpesAppDB


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
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        // get an instance of the SQL database
        db = SpesAppDB.getInstance(this)
    }
}