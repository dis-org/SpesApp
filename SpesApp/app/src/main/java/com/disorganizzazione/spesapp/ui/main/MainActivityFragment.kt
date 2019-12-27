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

    // l'overriding di onCreate servirà solo se vorremo permettere rotazioni dello schermo
    // overriding onCreate will only be useful if we want to allow screen rotations
    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }*/

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

        thread {
            // TODO: select the right list in the right tab
            val ingredientList = db?.storageDAO()?.selectAllInStorage()
            if (ingredientList != null)
                activity!!.runOnUiThread {
                    ingr_recycler_view.adapter = IngredientListAdapter(ingredientList)
                }
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