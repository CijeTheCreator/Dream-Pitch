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
import com.cijei.dreampitch.databinding.SetsFragmentBinding
import com.cijei.dreampitch.mock.MockSets
import com.google.android.material.snackbar.Snackbar

class SetsFragment: Fragment() {

    private lateinit var adapter: SetsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sets_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val stuff = arguments
        val set: Set?
        if (stuff != null) {
            set = stuff.getParcelable("AnyStringorKey")
        } else {
            set = Set() //Temporary Solution
        }

        val setsHandler = MockSets()
        val sets = setsHandler.getSets()
        if (set != null) {
            sets.add(set)
        }

        adapter = SetsAdapter(sets)
        val layoutManager = LinearLayoutManager(this.context)
        val recyclerView = view.findViewById<RecyclerView>(R.id.setsRecyclerView)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, layoutManager.orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)

        //Add Button Functionality
        val addButton = view.findViewById<Button>(R.id.add_sets_button)
        addButton.setOnClickListener {
//            Snackbar.make(addButton, "Add Set", Snackbar.LENGTH_SHORT).show()
            val i = Intent(this@SetsFragment.context, SelectPlayerActivity::class.java)
            startActivity(i)

        }

        //Remove Button Functionality
        val removeButton = view.findViewById<Button>(R.id.remove_sets_button)
        removeButton.setOnClickListener {
            Snackbar.make(removeButton, "Remove Set", Snackbar.LENGTH_SHORT).show()
        }
    }
}