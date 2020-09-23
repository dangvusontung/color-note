package sontung.dangvu.colornote.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import sontung.dangvu.colornote.R
import sontung.dangvu.colornote.database.Note
import sontung.dangvu.colornote.database.NoteDatabase

const val TAG = "ViewNoteFragment"
class ViewNoteFragment : Fragment() {

    private lateinit var btnAdd: FloatingActionButton
    private lateinit var viewModel: NoteViewModel
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var rcNotes : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_view_note, container, false)

        btnAdd = view.findViewById(R.id.add)
        btnAdd.setOnClickListener {
            moveToDetailFragment(null)
        }

        val application = requireNotNull(this.activity).application
        val noteDao = NoteDatabase.getInstance(application).noteDao
        val viewModelFactory = ViewNoteViewModelFactory(noteDao, application)
        viewModel =
            ViewModelProvider(requireActivity(), viewModelFactory).get(NoteViewModel::class.java)
        val listNote = viewModel.getAllNotes()

        listNote.observe(this.viewLifecycleOwner, Observer {
            Log.d(TAG, "onCreateView: $it")
            noteAdapter.submitList(it)
        })

        noteAdapter = NoteAdapter(onNoteSelectedListener)
        rcNotes = view.findViewById(R.id.note_list)
        rcNotes.apply {
            adapter = noteAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        return view
    }

    private val onNoteSelectedListener = object : OnNoteSelectedListener {
        override fun onNoteClicked(note: Note) {
            moveToDetailFragment(note)
        }
    }

    private fun moveToDetailFragment(note: Note?) {
        val action = ViewNoteFragmentDirections.actionViewNoteFragmentToNoteDetailFragment(note)
        findNavController().navigate(action)
    }

}
