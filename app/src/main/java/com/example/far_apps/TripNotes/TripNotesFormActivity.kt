package com.example.far_apps.TripNotes

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.far_apps.data.AppDatabase
import com.example.far_apps.data.entity.TripNotesEntity
import com.example.far_apps.databinding.ActivityTripNotesFormBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class TripNotesFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTripNotesFormBinding
    private lateinit var db: AppDatabase
    private var tripNotesId: Int = 0
    private var isEditMode: Boolean = false
    private var selectedDate: Long = System.currentTimeMillis()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTripNotesFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        db = AppDatabase.getInstance(this)

        tripNotesId = intent.getIntExtra("TRIP_NOTES_ID", 0)
        if (tripNotesId > 0) {
            isEditMode = true
            supportActionBar?.title = "Edit Trip Notes"
            loadTripNotesData()
        } else {
            supportActionBar?.title = "Tambah Trip Notes"
        }

        setupDatePicker()
        binding.btnSave.setOnClickListener { saveTripNotes() }
    }

    private fun setupDatePicker() {
        updateDateDisplay()
        binding.btnPilihTanggal.setOnClickListener {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = selectedDate
            DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    val newCalendar = Calendar.getInstance()
                    newCalendar.set(year, month, dayOfMonth)
                    selectedDate = newCalendar.timeInMillis
                    updateDateDisplay()
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun updateDateDisplay() {
        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
        binding.tvTanggal.text = "Tanggal: ${dateFormat.format(Date(selectedDate))}"
    }

    private fun loadTripNotesData() {
        lifecycleScope.launch {
            val tripNotes = db.tripNotesDao().getAll()
            val data = tripNotes.find { it.id == tripNotesId }
            data?.let {
                binding.etJudul.setText(it.judul)
                binding.etIsiCatatan.setText(it.isiCatatan)
                binding.etTempatWisata.setText(it.tempatWisata)
                binding.etTips.setText(it.tips)
                binding.ratingBar.rating = it.rating.toFloat()
                selectedDate = it.tanggalKunjungan
                updateDateDisplay()
            }
        }
    }

    private fun saveTripNotes() {
        val judul = binding.etJudul.text.toString().trim()
        val isiCatatan = binding.etIsiCatatan.text.toString().trim()
        val tempatWisata = binding.etTempatWisata.text.toString().trim()
        val tips = binding.etTips.text.toString().trim()
        val rating = binding.ratingBar.rating.toInt()

        if (judul.isEmpty() || isiCatatan.isEmpty() || tempatWisata.isEmpty()) {
            Toast.makeText(this, "Isi judul, isi catatan, dan tempat wisata!", Toast.LENGTH_SHORT).show()
            return
        }

        lifecycleScope.launch {
            if (isEditMode) {
                val tripNotes = TripNotesEntity(
                    id = tripNotesId,
                    judul = judul,
                    isiCatatan = isiCatatan,
                    tempatWisata = tempatWisata,
                    tanggalKunjungan = selectedDate,
                    rating = rating,
                    tips = tips
                )
                db.tripNotesDao().update(tripNotes)
                Toast.makeText(this@TripNotesFormActivity, "Trip Notes berhasil diupdate!", Toast.LENGTH_SHORT).show()
            } else {
                val tripNotes = TripNotesEntity(
                    judul = judul,
                    isiCatatan = isiCatatan,
                    tempatWisata = tempatWisata,
                    tanggalKunjungan = selectedDate,
                    rating = rating,
                    tips = tips
                )
                db.tripNotesDao().insert(tripNotes)
                Toast.makeText(this@TripNotesFormActivity, "Trip Notes berhasil ditambahkan!", Toast.LENGTH_SHORT).show()
            }
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}