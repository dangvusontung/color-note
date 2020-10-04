package sontung.dangvu.colornote.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import sontung.dangvu.colornote.R
import sontung.dangvu.colornote.database.Note
import sontung.dangvu.colornote.database.NoteDatabase
import sontung.dangvu.colornote.ui.main.NoteViewModel
import sontung.dangvu.colornote.ui.main.ViewNoteViewModelFactory
import kotlin.properties.Delegates

class NoteDetailFragment : Fragment() {

    lateinit var viewModel: NoteViewModel

    lateinit var noteTitle: EditText
    lateinit var noteContent: EditText

    private val args: NoteDetailFragmentArgs by navArgs()
    private lateinit var editingNote: Note
    private var isNewNote by Delegates.notNull<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args.editingNote?.let {
            editingNote = it
        }

        isNewNote = args.isNewNote
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        requireActivity().onBackPressedDispatcher.addCallback(backPressedCallback)

        val view = inflater.inflate(R.layout.fragment_note_detail, container, false)
        val application = requireNotNull(this.activity).application
        val noteDao = NoteDatabase.getInstance(application).noteDao
        val viewModelFactory = ViewNoteViewModelFactory(noteDao, application)
        viewModel =
            ViewModelProvider(requireActivity(), viewModelFactory).get(NoteViewModel::class.java)

        noteTitle = view.findViewById(R.id.note_title)
        noteContent = view.findViewById(R.id.note_content)

        editingNote.let {
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
        editingNote.let {
            it.title = noteTitle.text.toString()
            it.content = noteContent.text.toString()

            GlobalScope.launch {
                viewModel.updateNote(it)
            }
        }
    }

    private val backPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (isNewNote) {
                saveNewNote()
            } else {
                saveNote()
            }
            findNavController().popBackStack()
        }
    }
}
