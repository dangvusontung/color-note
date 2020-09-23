package sontung.dangvu.colornote.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_view_note.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import sontung.dangvu.colornote.R
import sontung.dangvu.colornote.database.Note
import sontung.dangvu.colornote.database.NoteDatabase

const val TAG = "ViewNoteFragment"
class ViewNoteFragment : Fragment() {

    private lateinit var btnAdd : FloatingActionButton
    private lateinit var viewModel : ViewNoteViewModel
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var rcNotes : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_view_note, container, false)

        btnAdd = view.findViewById(R.id.add)
        btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_viewNoteFragment_to_noteDetailFragment)
//            addNote()
        }

        val application = requireNotNull(this.activity).application

        val noteDao = NoteDatabase.getInstance(application).noteDao
        val viewModelFactory = ViewNoteViewModelFactory(noteDao, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ViewNoteViewModel::class.java)

        val listNote = viewModel.getAllNotes()

        listNote.observe(this.viewLifecycleOwner, Observer {
            Log.d(TAG, "onCreateView: $it")
            noteAdapter.submitList(it)
        })

        noteAdapter = NoteAdapter()
        rcNotes = view.findViewById(R.id.note_list)
        rcNotes.apply {
            adapter = noteAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        return view
    }

}
