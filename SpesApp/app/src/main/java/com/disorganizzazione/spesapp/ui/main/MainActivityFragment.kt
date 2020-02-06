package com.disorganizzazione.spesapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.disorganizzazione.spesapp.add_ingredients.AddIngredientActivity
import com.disorganizzazione.spesapp.R
import com.disorganizzazione.spesapp.db.SpesAppDB
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlin.concurrent.thread

/**
 * Un fragment usato in entrambe le schede dell'attività principale, contenente una recycler view in cui è mostrata una
 * lista di ingredienti.
 * A fragment used in both tabs of the main activity, containing a recycler view showing a list of ingredients.
 */

class MainActivityFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel

    private lateinit var adapter: IngredientAdapter

    private fun selectAllAndUpdate() {
        /**
         * Esegue le "select all" sulla giusta tabella a seconda del fragment in uso e aggiorna la GUI.
         * La query deve essere fatta su un thread secondario perché sennò ad Android je pia male.
         * Performs the "select alls" on the right table, depending on the current fragment, and updates the GUI.
         * The query must be done on a secondary thread cause otherwise Android complains it might take too long.
         */
        val db = SpesAppDB.getInstance(activity!!.applicationContext)
        thread {
            val ingredientList = when (pageViewModel.getIndex()) {
                1 -> db?.groceryListDAO()?.selectAllInGroceryList() ?: emptyList()
                2 -> db?.storageDAO()?.selectAllInStorage() ?: emptyList()
                else -> emptyList()
                }
            activity!!.runOnUiThread {
                adapter = IngredientAdapter(ingredientList.toMutableList(), context)
                ingr_recycler_view.adapter = adapter

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // associa il layout del fragment al fragment
        // it associates the fragment layout to the fragment
        val fragmentLayout = inflater.inflate(R.layout.fragment_main, container, false)

        // setta il layout manager responsabile di mostrare i singoli elementi della lista
        // sets the layout manager responsible for showing the individual elements of the list
        fragmentLayout.ingr_recycler_view.layoutManager = LinearLayoutManager(activity)

        selectAllAndUpdate()
        // event listener per il bottone +
        // event listener for the + button
        fragmentLayout.fab.setOnClickListener {
            val intent = Intent(activity!!.applicationContext, AddIngredientActivity::class.java)
                // comunica alla nuova activity da quale fragment è stata aperta
                // tells the new activity which fragment if was opened from
                .putExtra("tab",pageViewModel.getIndex())
            startActivity(intent)
        }

        // servirà solo come esempio se vorremo permettere rotazioni dello schermo
        // only useful as an example if we want to allow screen rotations
        /*val textView: TextView = root.findViewById(R.id.section_label)
        pageViewModel.text.observe(this, Observer<String> {
            textView.text = it
        })*/

        return fragmentLayout
    }

    override fun onResume() {
        super.onResume()
        selectAllAndUpdate()
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        super.onContextItemSelected(item)
        // as for now there is only one case (delete), so no need to use a when with item id cases
        adapter.removeIngredient(item!!.groupId)

        return true
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): MainActivityFragment {
            return MainActivityFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}