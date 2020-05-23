package com.disorganizzazione.spesapp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.ViewPager
import com.disorganizzazione.spesapp.R
import com.disorganizzazione.spesapp.db.SpesAppDB
import com.google.android.material.tabs.TabLayout


/**
 * The main activity.
 * It contains the interface for the two tabs containing the lists, but not the lists themselves
 * (cf. MainActivityFragment).
 */

class MainActivity : AppCompatActivity(), AddIngredientDialogFragment.AddIngredientDialogListener {

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

    // implement the interface defined in AddIngredientDialogFragment to update UI after adding
    override fun onDialogQuickAddClick(dialog: DialogFragment) {
        // TODO: only update the fragment that needs to be updated
        val frags: List<Fragment> = supportFragmentManager.fragments
        for (frag in frags) {
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            ft.detach(frag)
            ft.attach(frag)
            ft.commit()
        }
    }
}