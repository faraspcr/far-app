package com.example.far_apps.TripNotes

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.far_apps.data.AppDatabase
import com.example.far_apps.data.entity.TripNotesEntity
import com.example.far_apps.databinding.FragmentTripNotesBinding
import kotlinx.coroutines.launch

class TripNotesFragment : Fragment() {

    private var _binding: FragmentTripNotesBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: AppDatabase
    private lateinit var adapter: TripNotesAdapter
    private val tripNotesList = mutableListOf<TripNotesEntity>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTripNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Trip Notes"
        }

        // Inisialisasi DB
        db = AppDatabase.getInstance(requireContext())

        // Setup RecyclerView
        adapter = TripNotesAdapter(
            tripNotesList,
            { tripNotes -> editTripNotes(tripNotes) },
            { tripNotes -> confirmDelete(tripNotes) }
        )
        binding.rvTripNotes.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTripNotes.adapter = adapter

        // Setup FAB
        binding.fabAddTripNotes.setOnClickListener {
            startActivity(Intent(requireContext(), TripNotesFormActivity::class.java))
        }

        // Load data
        loadTripNotes()
    }

    private fun loadTripNotes() {
        lifecycleScope.launch {
            val data = db.tripNotesDao().getAll()
            tripNotesList.clear()
            tripNotesList.addAll(data)
            adapter.updateData(tripNotesList)
        }
    }

    private fun editTripNotes(tripNotes: TripNotesEntity) {
        val intent = Intent(requireContext(), TripNotesFormActivity::class.java)
        intent.putExtra("TRIP_NOTES_ID", tripNotes.id)
        startActivity(intent)
    }

    private fun confirmDelete(tripNotes: TripNotesEntity) {
        AlertDialog.Builder(requireContext())
            .setTitle("Hapus Trip Notes")
            .setMessage("Apakah kamu yakin ingin menghapus '${tripNotes.judul}'?")
            .setPositiveButton("Ya") { _, _ ->
                deleteTripNotes(tripNotes)
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    private fun deleteTripNotes(tripNotes: TripNotesEntity) {
        lifecycleScope.launch {
            db.tripNotesDao().delete(tripNotes)
            loadTripNotes()
        }
    }

    override fun onResume() {
        super.onResume()
        loadTripNotes()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}