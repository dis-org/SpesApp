package com.disorganizzazione.spesapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.disorganizzazione.spesapp.AddIngredientActivity
import com.disorganizzazione.spesapp.db.IngredientEntity
import com.disorganizzazione.spesapp.R
import com.disorganizzazione.spesapp.db.SpesAppDB
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    // definito qui perché sia visibile, anche se il database viene creato nella main activity.
    // Ci deve essere un modo migliore.
    // this is defined here so it's visible, even though the database is created in the main activity.
    // There must be a better way.
    private var db: SpesAppDB? = null

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

        var db = SpesAppDB.getInstance(activity!!.applicationContext)
        var ingredientList: List<IngredientEntity>? = emptyList()

        // esegue le "select all" sulla giusta tabella a seconda del fragment in uso.
        // deve essere su un thread secondario perché sennò Android si preoccupa che la cosa prenda troppo tempo.
        // performs the "select alls" on the right table depending on the current fragment.
        // must be done on a secondary thread cause otherwise Android complains it might take too long.
        thread {
            when (pageViewModel.getIndex()) {
                1 -> ingredientList = db?.groceryListDAO()?.selectAllInGroceryList()
                2 -> ingredientList = db?.storageDAO()?.selectAllInStorage()
            }
                // lo si scrive dentro il thread secondario per mantenere la sequenzialità. Credo.
                // we do this from inside the secondary thread so to preserve sequentiality. I think.
                activity!!.runOnUiThread {
                    ingr_recycler_view.adapter = IngredientListAdapter(ingredientList!!)
                }
        }

        // event listener per il bottone +
        // event listener for the + button
        fragmentLayout.fab.setOnClickListener {view ->
            startActivity(Intent(activity!!.applicationContext, AddIngredientActivity::class.java))
        }

        // servirà solo come esempio se vorremo permettere rotazioni dello schermo
        // only useful as an example if we want to allow screen rotations
        /*val textView: TextView = root.findViewById(R.id.section_label)
        pageViewModel.text.observe(this, Observer<String> {
            textView.text = it
        })*/

        return fragmentLayout
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