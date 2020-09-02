package boh.harisont.spesapp.ui.main

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import boh.harisont.spesapp.R
import boh.harisont.spesapp.db.ingredient.IngredientEntity
import kotlinx.android.synthetic.main.fragment_main.view.*


/**
 * Fragment used in both tabs of the main activity,
 * containing a recycler view showing a list of ingredients.
 */

class MainActivityFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private lateinit var ingrViewModel: IngredientViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }

        ingrViewModel = ViewModelProvider(this).get(IngredientViewModel::class.java)
        when (pageViewModel.getIndex()) {
            1 -> ingrViewModel.selectGroceryList()?.observe(
                this,
                Observer<List<IngredientEntity>> {
                    // TODO: update RecyclerView
                })
            2 -> ingrViewModel.selectStorageList()?.observe(
                this,
                Observer<List<IngredientEntity>> {
                    // TODO: update RecyclerView
                })
            else -> println(R.string.never_shown)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // associate the fragment to its layout, set the layout manager for individual ingredients
        val fragmentLayout = inflater.inflate(R.layout.fragment_main, container, false)
        fragmentLayout.ingr_recycler_view.layoutManager = LinearLayoutManager(activity)
        /* TODO:
        // set the event listener for the + button (there is one per fragment!)
        fragmentLayout.fab.setOnClickListener {
            val dialog = AddIngredientDialogFragment()
            val dialogArgs = Bundle()
            dialogArgs.putInt("tab",pageViewModel.getIndex()!!)
            dialog.arguments = dialogArgs
            dialog.show(fragmentManager, "add_ingr_dialog")
        }
        */
        return fragmentLayout
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