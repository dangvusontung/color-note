package sontung.dangvu.colornote.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import sontung.dangvu.colornote.R
import sontung.dangvu.colornote.database.Note
import sontung.dangvu.colornote.database.NoteDatabase
import sontung.dangvu.colornote.ui.main.NoteViewModel
import sontung.dangvu.colornote.ui.main.ViewNoteViewModelFactory

private const val TAG = "NoteDetailFragment"
class NoteDetailFragment : Fragment() {

    lateinit var viewModel: NoteViewModel

    lateinit var noteTitle: EditText
    lateinit var noteContent: EditText

    val args: NoteDetailFragmentArgs by navArgs()
    private val editingNote = args.editingNote

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_note_detail, container, false)
        val application = requireNotNull(this.activity).application
        val noteDao = NoteDatabase.getInstance(application).noteDao
        val viewModelFactory = ViewNoteViewModelFactory(noteDao, application)
        viewModel =
            ViewModelProvider(requireActivity(), viewModelFactory).get(NoteViewModel::class.java)

        noteTitle = view.findViewById(R.id.note_title)
        noteContent = view.findViewById(R.id.note_content)



        editingNote?.let {
            noteTitle.setText(it.title)
            noteContent.setText(it.content)
        }


        return view
    }

    private fun saveNewNote() {
        if (noteTitle.text.toString().isNotEmpty() && noteContent.text.toString().isNotEmpty()) {
            val note = Note(noteTitle.text.toString(), noteContent.text.toString())
            GlobalScope.launch {
                viewModel.addNote(note)
            }
        }
    }

    private fun saveNote() {
        editingNote!!.title = noteTitle.text.toString()
        editingNote.content = noteContent.text.toString()

        GlobalScope.launch {
            viewModel.updateNote(editingNote)
        }
    }

}
