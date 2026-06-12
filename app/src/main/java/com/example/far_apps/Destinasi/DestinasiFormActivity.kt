package com.example.far_apps.Destinasi

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.far_apps.R
import com.example.far_apps.data.AppDatabase
import com.example.far_apps.data.entity.DestinasiEntity
import com.example.far_apps.databinding.ActivityDestinasiFormBinding
import kotlinx.coroutines.launch

class DestinasiFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDestinasiFormBinding
    private lateinit var db: AppDatabase
    private var destinasiId: Int = 0
    private var isEditMode: Boolean = false
    private var selectedGambarResId: Int = R.drawable.ic_placeholder

    private val gambarList = listOf(
        "Pilih Gambar" to 0,
        "Air Terjun" to R.drawable.airterjun,
        "Bukit Karst" to R.drawable.bukitkaret,
        "Kawah Ijen" to R.drawable.kawahijen,
        "Pantai Ngobaran" to R.drawable.pantaingobaran,
        "Taman Nasional Baluran" to R.drawable.tamannasionalbaluran,
        "Wisata Pentingsari" to R.drawable.wisatapentingsari,
        "Placeholder" to R.drawable.ic_placeholder
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDestinasiFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        db = AppDatabase.getInstance(this)

        destinasiId = intent.getIntExtra("DESTINASI_ID", 0)
        if (destinasiId > 0) {
            isEditMode = true
            supportActionBar?.title = "Edit Destinasi"
            loadDestinasiData()
        } else {
            supportActionBar?.title = "Tambah Destinasi"
        }

        setupSpinnerGambar()
        binding.btnSave.setOnClickListener { saveDestinasi() }
    }

    private fun setupSpinnerGambar() {
        val spinnerItems = gambarList.map { it.first }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerItems)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerGambar.adapter = adapter

        binding.spinnerGambar.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                selectedGambarResId = gambarList[position].second
                if (selectedGambarResId != 0) {
                    binding.ivPreviewGambar.setImageResource(selectedGambarResId)
                    binding.ivPreviewGambar.visibility = android.view.View.VISIBLE
                } else {
                    binding.ivPreviewGambar.visibility = android.view.View.GONE
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }
    }

    private fun loadDestinasiData() {
        lifecycleScope.launch {
            val destinasi = db.destinasiDao().getAll()
            val data = destinasi.find { it.id == destinasiId }
            data?.let {
                binding.etNamaDestinasi.setText(it.namaDestinasi)
                binding.etDeskripsi.setText(it.deskripsi)
                binding.etLokasi.setText(it.lokasi)
                binding.etHargaTiket.setText(it.hargaTiket)
                selectedGambarResId = it.gambarResId
                val position = gambarList.indexOfFirst { img -> img.second == selectedGambarResId }
                if (position >= 0) binding.spinnerGambar.setSelection(position)
            }
        }
    }

    private fun saveDestinasi() {
        val namaDestinasi = binding.etNamaDestinasi.text.toString().trim()
        val deskripsi = binding.etDeskripsi.text.toString().trim()
        val lokasi = binding.etLokasi.text.toString().trim()
        val hargaTiket = binding.etHargaTiket.text.toString().trim()

        if (namaDestinasi.isEmpty() || deskripsi.isEmpty() || lokasi.isEmpty() || hargaTiket.isEmpty()) {
            Toast.makeText(this, "Isi semua kolom!", Toast.LENGTH_SHORT).show()
            return
        }

        if (selectedGambarResId == 0) {
            Toast.makeText(this, "Pilih gambar!", Toast.LENGTH_SHORT).show()
            return
        }

        lifecycleScope.launch {
            if (isEditMode) {
                val destinasi = DestinasiEntity(
                    id = destinasiId,
                    namaDestinasi = namaDestinasi,
                    deskripsi = deskripsi,
                    lokasi = lokasi,
                    hargaTiket = hargaTiket,
                    gambarResId = selectedGambarResId
                )
                db.destinasiDao().update(destinasi)
                Toast.makeText(this@DestinasiFormActivity, "Destinasi berhasil diupdate!", Toast.LENGTH_SHORT).show()
            } else {
                val destinasi = DestinasiEntity(
                    namaDestinasi = namaDestinasi,
                    deskripsi = deskripsi,
                    lokasi = lokasi,
                    hargaTiket = hargaTiket,
                    gambarResId = selectedGambarResId
                )
                db.destinasiDao().insert(destinasi)
                Toast.makeText(this@DestinasiFormActivity, "Destinasi berhasil ditambahkan!", Toast.LENGTH_SHORT).show()
            }
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}