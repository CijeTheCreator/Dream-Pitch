package com.cijei.dreampitch.ui

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
import com.cijei.dreampitch.adapters.RemoveSetsAdapter
import com.cijei.dreampitch.data.Set
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDate
import java.time.LocalDateTime

class RemoveSetsFragment(private var setz: ArrayList<Set>): Fragment() {

    private lateinit var database: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.remove_set_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        database = FirebaseDatabase.getInstance().getReference("Sets")
        val recyclerView = view.findViewById<RecyclerView>(R.id.remove_sets_recycler_view)
        val doneButton = view.findViewById<Button>(R.id.done_button)
        val adapter = RemoveSetsAdapter(setz)
        recyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(this.context)
        recyclerView.layoutManager = layoutManager
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, layoutManager.orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)

        doneButton.setOnClickListener {
            val setsToRemove = adapter.selectedSets
            val date = LocalDateTime.now()
            for (set in setsToRemove) {
                setz.remove(set)
                database.child("${date.year} ${date.month} ${date.dayOfMonth}").child(set.teamName).removeValue()
            }
            val setsFragment = SetsFragment(setz)
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.timer_fragment, setsFragment)
            transaction?.commit()
            Snackbar.make(doneButton, "${setsToRemove.size} sets removed", Snackbar.LENGTH_SHORT).show()

        }
    }
}