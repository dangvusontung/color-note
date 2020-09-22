package sontung.dangvu.colornote.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_view_note.*

import sontung.dangvu.colornote.R

const val TAG = "ViewNoteFragment"
class ViewNoteFragment : Fragment() {

    private lateinit var btnAdd : FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_view_note, container, false)

        btnAdd = view.findViewById(R.id.add)
        btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_viewNoteFragment_to_noteDetailFragment)
        }

        return view
    }

}
