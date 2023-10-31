package com.example.bitfit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SummaryFragment : Fragment() {

    private lateinit var database: AppDatabase
    private lateinit var summaryText: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_summary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = AppDatabase.getInstance(requireContext())
        summaryText = view.findViewById(R.id.summaryText)

        // Fetch and display the summary
        updateSummary()
    }

    private fun updateSummary() {
        CoroutineScope(Dispatchers.IO).launch {
            val totalEntries = database.entryDao().getCount()  // Fetch total entries count
            withContext(Dispatchers.Main) {
                summaryText.text = "You have entered $totalEntries entries."
            }
        }
    }
}
