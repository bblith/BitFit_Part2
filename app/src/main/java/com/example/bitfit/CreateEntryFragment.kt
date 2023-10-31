package com.example.bitfit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreateEntryFragment : Fragment() {

    private lateinit var database: AppDatabase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_entry, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = AppDatabase.getInstance(requireContext())

        val titleEditText = view.findViewById<EditText>(R.id.detail_title)
        val bodyEditText = view.findViewById<EditText>(R.id.detail_body)
        val enterButton = view.findViewById<Button>(R.id.enterButton)

        enterButton.setOnClickListener {
            val title = titleEditText.text.toString().trim()
            val body = bodyEditText.text.toString().trim()

            if (title.isNotEmpty() && body.isNotEmpty()) {
                val entry = EntryEntity(title = title, body = body)

                CoroutineScope(Dispatchers.IO).launch {
                    database.entryDao().insert(entry)
                    withContext(Dispatchers.Main) {
                        requireActivity().onBackPressed()
                    }
                }
            }
        }
        val summaryButton = view.findViewById<Button>(R.id.summaryButton)
        summaryButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SummaryFragment())
                .addToBackStack(null)
                .commit()
        }

    }


}
