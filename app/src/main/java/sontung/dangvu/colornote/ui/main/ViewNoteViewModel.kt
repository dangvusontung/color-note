package sontung.dangvu.colornote.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import sontung.dangvu.colornote.database.Note
import sontung.dangvu.colornote.database.NoteDao

class ViewNoteViewModel(
    private val dao : NoteDao,
    val app: Application
) : AndroidViewModel(app) {

    fun getAllNotes() : LiveData<List<Note>> {
        return dao.getAllNotes()
    }

    suspend fun addNote(note: Note) {
        dao.insert(note)
    }

}