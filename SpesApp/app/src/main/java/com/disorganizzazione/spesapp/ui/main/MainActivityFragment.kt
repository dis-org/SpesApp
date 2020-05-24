package com.disorganizzazione.spesapp.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.disorganizzazione.spesapp.R
import com.disorganizzazione.spesapp.db.SpesAppDB
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlin.concurrent.thread


/**
 * Fragment used in both tabs of the main activity,
 * containing a recycler view showing a list of ingredients.
 */

class MainActivityFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel

    private lateinit var adapter: IngredientAdapter

    private fun selectAllAndUpdate() {
        /**
         * Performs SELECT * on the db table corresponding to the current tab/fragment
         * and updates the GUI.
         */
        val db = SpesAppDB.getInstance(activity!!.applicationContext)
        // create a list of ingredients by querying the db
        // this MUST happen on a secondary thread as the main one is to be used for UI updates
        thread {
            val ingredientList = when (pageViewModel.getIndex()) {
                // in the future, we really should use a boolean instead of ints.
                // For the moment, I think it's easier to remember that 1 is tab1 and 2 is tab2
                1 -> db?.groceryListDAO()?.selectAllInGroceryList() ?: emptyList()
                2 -> db?.storageDAO()?.selectAllInStorage() ?: emptyList()
                else -> emptyList()
                }
            // on the UI thread, but AFTER the list is created, the list is fed to the adapter
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
        // associate the fragment to its layout, set the layout manager for individual ingredients
        val fragmentLayout = inflater.inflate(R.layout.fragment_main, container, false)
        fragmentLayout.ingr_recycler_view.layoutManager = LinearLayoutManager(activity)

        selectAllAndUpdate()

        // set the event listener for the + button (there is one per fragment!)
        fragmentLayout.fab.setOnClickListener {
            val dialog = AddIngredientDialogFragment()
            val dialogArgs = Bundle()
            dialogArgs.putInt("tab",pageViewModel.getIndex()!!)
            dialog.arguments = dialogArgs
            dialog.show(fragmentManager, "add_ingr_dialog")
        }
        return fragmentLayout
    }

    // queries and UI updates need to be performed every time the fragments becomes visible again
    override fun onResume() {
        super.onResume()
        selectAllAndUpdate()
    }

    // a very hacky way to update the UI after quick add
    override fun onDetach() {
        super.onDetach()
        selectAllAndUpdate()
    }

    // auto-generated tabs magic
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