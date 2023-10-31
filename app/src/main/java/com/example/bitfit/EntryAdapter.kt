package com.example.bitfit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EntryAdapter(private val entries: List<EntryEntity>):
    RecyclerView.Adapter<EntryAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val entry_title: TextView
        val entry_body: TextView

        init {
            entry_title = itemView.findViewById(R.id.entry_title)
            entry_body = itemView.findViewById(R.id.entry_body)
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.entry_item, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entry = entries.get(position)
        holder.entry_title.text = entry.title
        holder.entry_body.text = entry.body
    }

    override fun getItemCount(): Int {
        return entries.size
    }




    }


