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

class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val noteTitle = itemView.findViewById<TextView>(R.id.note_title)
    val noteContent = itemView.findViewById<TextView>(R.id.note_content)

    fun bindData(note: Note) {
        noteTitle.text = note.title
        noteContent.text = note.content
    }
}

class NoteAdapter(private val listener: OnNoteSelectedListener) :
    ListAdapter<Note, NoteViewHolder>(NoteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        holder.bindData(note)
        holder.itemView.setOnClickListener {
            listener.onNoteClicked(note)
        }
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

interface OnNoteSelectedListener {
    fun onNoteClicked(note: Note)
}

