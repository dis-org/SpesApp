package com.disorganizzazione.spesapp

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.disorganizzazione.spesapp.db.StorageEntity
import com.disorganizzazione.spesapp.db.SpesAppDB
import com.disorganizzazione.spesapp.ui.main.SectionsPagerAdapter

class MainActivity : AppCompatActivity() {

    var db: SpesAppDB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        db = SpesAppDB.getInstance(this)

        // TEST
        // TODO: cancellare/delete
        Thread {
            var testIngr1 = StorageEntity()
            testIngr1.name = "Patate"
            db?.storageDAO()?.insertInStorage(testIngr1)
        }.start()

        Thread {
            var testIngr2 = StorageEntity()
            testIngr2.name = "Uova"
            db?.storageDAO()?.insertInStorage(testIngr2)
        }.start()
    }
}