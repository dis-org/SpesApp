package com.disorganizzazione.spesapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.disorganizzazione.spesapp.IngredientEntity
import com.disorganizzazione.spesapp.R
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*

/**
 * Un fragment usato in entrambe le schede dell'attività principale, contenente una recycler view in cui è mostrata una
 * lista di ingredienti.
 * A fragment used in both tabs of the main activity, containing a recycler view showing a list of ingredients.
 */

class MainActivityFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel

    // l'overriding di onCreate servirà solo se vorremo permettere rotazioni dello schermo
    // overriding onCreate will only be useful if we want to allow screen rotations
    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        lateinit var recyclerView: RecyclerView
        lateinit var viewAdapter: RecyclerView.Adapter<*>
        lateinit var viewManager: RecyclerView.LayoutManager

        // seleziona il layout manager responsabile di mostrare i singoli elementi
        // selects the layout manager responsible for showing the individual elements
        viewManager = LinearLayoutManager(activity)

        // seleziona l'adapter responsabile di creare le view e associarle ai dati del db
        // selects the adapter responsible for creating the views and binding them to the data in the db
        val ingredientList = listOf<IngredientEntity>()
        viewAdapter = IngredientListAdapter(ingredientList)

        // applica il viewManager e l'adapter alla RecyclerView del frammento
        // applies the viewManager and the adapter to the fragment's RecyclerView
        recyclerView = ingr_recycler_view.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }

        // servirà solo come esempio se vorremo permettere rotazioni dello schermo
        // only useful as an example if we want to allow screen rotations
        /*val textView: TextView = root.findViewById(R.id.section_label)
        pageViewModel.text.observe(this, Observer<String> {
            textView.text = it
        })*/
        return recyclerView
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