package com.example.far_apps.Destinasi

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
import com.example.far_apps.data.entity.DestinasiEntity
import com.example.far_apps.databinding.FragmentDestinasiBinding
import kotlinx.coroutines.launch

class DestinasiFragment : Fragment() {

    private var _binding: FragmentDestinasiBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: AppDatabase
    private lateinit var adapter: DestinasiRoomAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDestinasiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TOOLBAR
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Destinasi Wisata Desa"
        }

        // Inisialisasi DB
        db = AppDatabase.getInstance(requireContext())

        // Setup RecyclerView
        adapter = DestinasiRoomAdapter(
            emptyList(),
            { destinasi -> editDestinasi(destinasi) },
            { destinasi -> confirmDelete(destinasi) }
        )
        binding.rvDestinasi.layoutManager = LinearLayoutManager(requireContext())
        binding.rvDestinasi.adapter = adapter

        // Setup FAB
        binding.fabAddDestinasi.setOnClickListener {
            startActivity(Intent(requireContext(), DestinasiFormActivity::class.java))
        }

        // Load data
        loadDestinasi()
    }
    private fun loadDestinasi() {
        lifecycleScope.launch {
            val data = db.destinasiDao().getAll()
            adapter.updateData(data)
        }
    }

    private fun editDestinasi(destinasi: DestinasiEntity) {
        val intent = Intent(requireContext(), DestinasiFormActivity::class.java)
        intent.putExtra("DESTINASI_ID", destinasi.id)
        startActivity(intent)
    }

    private fun confirmDelete(destinasi: DestinasiEntity) {
        AlertDialog.Builder(requireContext())
            .setTitle("Hapus Destinasi")
            .setMessage("Apakah kamu yakin ingin menghapus ${destinasi.namaDestinasi}?")
            .setPositiveButton("Ya") { _, _ ->
                deleteDestinasi(destinasi)
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    private fun deleteDestinasi(destinasi: DestinasiEntity) {
        lifecycleScope.launch {
            db.destinasiDao().delete(destinasi)
            loadDestinasi()
        }
    }
    override fun onResume() {
        super.onResume()
        loadDestinasi()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}