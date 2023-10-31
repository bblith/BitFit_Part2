package com.example.bitfit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EntriesFragment : Fragment() {

    private lateinit var database: AppDatabase
    private lateinit var entryRv: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_entries, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = AppDatabase.getInstance(requireContext())
        entryRv = view.findViewById(R.id.entryRV)

        database.entryDao().getAll().observe(viewLifecycleOwner, { dbEntries ->
            val adapter = EntryAdapter(dbEntries)
            entryRv.adapter = adapter
        })


        entryRv.layoutManager = LinearLayoutManager(requireContext())
    }
}
