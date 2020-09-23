package sontung.dangvu.colornote.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import sontung.dangvu.colornote.database.Note
import sontung.dangvu.colornote.database.NoteDao

class NoteViewModel(
    private val dao: NoteDao,
    val app: Application
) : AndroidViewModel(app) {

    fun getAllNotes(): LiveData<List<Note>> {
        return dao.getAllNotes()
    }

    suspend fun addNote(note: Note) {
        dao.insert(note)
    }

    suspend fun deleteNote(note: Note) {
        dao.delete(note)
    }

    suspend fun updateNote(note: Note) {
        dao.update(note)
    }

}