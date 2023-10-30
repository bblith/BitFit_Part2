package com.example.bitfit

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var database: AppDatabase
    private lateinit var entryRv: RecyclerView
    lateinit var entries: List<EntryEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = AppDatabase.getInstance(this)
        entryRv = findViewById(R.id.entryRV)


        // Observe changes in the database and update the RecyclerView accordingly
        database.entryDao().getAll().observe(this, { dbEntries ->
            val adapter = EntryAdapter(dbEntries)
            entryRv.adapter = adapter
        })

        entryRv.layoutManager = LinearLayoutManager(this)

        findViewById<Button>(R.id.newEntryButton).setOnClickListener {
            showEntryDetailDialog()
        }
    }


    private fun showEntryDetailDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.entry_detail)

        val titleEditText = dialog.findViewById<EditText>(R.id.detail_title)
        val bodyEditText = dialog.findViewById<EditText>(R.id.detail_body)
        val enterButton = dialog.findViewById<Button>(R.id.enterButton)

        enterButton.setOnClickListener {
            val title = titleEditText.text.toString().trim()
            val body = bodyEditText.text.toString().trim()

            if (title.isNotEmpty() && body.isNotEmpty()) {
                val entry = EntryEntity(title = title, body = body)

                // Use coroutines to perform database operation on a background thread
                CoroutineScope(Dispatchers.IO).launch {
                    database.entryDao().insert(entry)
                }

                dialog.dismiss()
            }
        }

        dialog.show()
    }


}