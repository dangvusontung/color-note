package sontung.dangvu.colornote.ui.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import sontung.dangvu.colornote.database.NoteDao
import java.lang.IllegalArgumentException

class ViewNoteViewModelFactory(
    private  val dao : NoteDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewNoteViewModel::class.java)) {
            return ViewNoteViewModel(dao, application) as T
        }

        throw IllegalArgumentException("unknown view model")
    }
}