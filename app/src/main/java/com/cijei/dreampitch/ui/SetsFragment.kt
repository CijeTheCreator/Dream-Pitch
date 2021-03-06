package com.cijei.dreampitch.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cijei.dreampitch.R
import com.cijei.dreampitch.adapters.SetsAdapter
import com.cijei.dreampitch.data.Set
import com.cijei.dreampitch.mock.MockMatches
import com.cijei.dreampitch.mock.MockSets
import com.google.android.material.snackbar.Snackbar

class SetsFragment(private var setz: ArrayList<Set>?): Fragment() {

    private lateinit var adapter: SetsAdapter
    private lateinit var sets: ArrayList<Set>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sets_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MockMatches().getMatchesByDate()

        val setsHandler = MockSets()
        sets = setsHandler.getSets()

        if (setz != null) {
            sets = setz as ArrayList<Set>
        }


        adapter = SetsAdapter(sets, this.requireContext())
        val layoutManager = LinearLayoutManager(this.context)
        val recyclerView = view.findViewById<RecyclerView>(R.id.setsRecyclerView)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, layoutManager.orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)


        if (arguments != null) {
            val setz = ArrayList<Set>()
            val keys = arguments?.get("Keys") as ArrayList<String>
            for (key in keys) {
                val set = arguments?.get(key) as Set
                setz.add(set)
            }
            adapter.addSet(setz)
            sets = setz
            Snackbar.make(view.findViewById(R.id.add_sets_button), "${setz[setz.size - 1].teamName} Added", Snackbar.LENGTH_SHORT).show()

        }

        //Add Button Functionality
        val addButton = view.findViewById<Button>(R.id.add_sets_button)
        addButton.setOnClickListener {
//            Snackbar.make(addButton, "Add Set", Snackbar.LENGTH_SHORT).show()
            val bundle = Bundle()

            val keys = ArrayList<String>()
            for (set in sets) {
                keys.add(set.teamName)
            }
            bundle.putStringArrayList("Keys", keys)


            for (set in sets) {
                bundle.putParcelable(set.teamName, set)
            }
            val i = Intent(this@SetsFragment.context, SelectPlayerActivity::class.java)
            i.putExtras(bundle)
            startActivity(i)

        }

        //Remove Button Functionality
        val removeButton = view.findViewById<Button>(R.id.remove_sets_button)
        removeButton.setOnClickListener {
            Snackbar.make(removeButton, "Remove Set", Snackbar.LENGTH_SHORT).show()
            val removeSetsFragment = RemoveSetsFragment(sets)
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.timer_fragment, removeSetsFragment)
            transaction?.commit()
        }
    }
}