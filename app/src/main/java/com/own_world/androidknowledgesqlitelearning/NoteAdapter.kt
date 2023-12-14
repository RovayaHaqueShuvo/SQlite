package com.own_world.androidknowledgesqlitelearning

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(private var notes : List<DataClass>, context: Context):
    RecyclerView.Adapter<NoteAdapter.NoteviewHolder>() {

        class NoteviewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

            val titletxt = itemView.findViewById<TextView>(R.id.Title_txt)
            var contenttxt = itemView.findViewById<TextView>(R.id.Description_txt)

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteviewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteviewHolder(view)
    }

    override fun getItemCount(): Int = notes.size


    override fun onBindViewHolder(holder: NoteviewHolder, position: Int) {
        val note = notes[position]
        holder.titletxt.text = note.title
        holder.contenttxt.text = note.content

    }

    fun refreshData(newNotes:List<DataClass> ){
        notes = newNotes
        notifyDataSetChanged()
    }
}