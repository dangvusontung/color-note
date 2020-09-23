package sontung.dangvu.colornote.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import sontung.dangvu.colornote.R
import sontung.dangvu.colornote.database.Note

//class NoteAdapter() : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
//
//    private var notes = listOf<Note>()
//    set(value) {
//        field = value
//        notifyDataSetChanged()
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
//        return NoteViewHolder(view)
//    }
//
//    override fun getItemCount() = notes.size
//
//    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
//        val note = notes[position]
//        holder.bindData(note)
//    }
//
     class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noteTitle = itemView.findViewById<TextView>(R.id.note_title)
        val noteContent = itemView.findViewById<TextView>(R.id.note_content)

        fun bindData(note: Note) {
            noteTitle.text = note.title
            noteContent.text = note.content
        }
    }
//
//    fun setList(newList : List<Note>) {
//        notes = newList
//        notifyDataSetChanged()
//    }
//}


class NoteAdapter : ListAdapter<Note, NoteViewHolder>(NoteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        holder.bindData(note)
    }

}

class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {

    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.timeCreated == newItem.timeCreated
    }


    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }

}

