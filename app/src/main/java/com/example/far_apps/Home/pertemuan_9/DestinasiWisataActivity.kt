package com.example.far_apps.Home.pertemuan_9

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.far_apps.data.AppDatabase
import com.example.far_apps.databinding.ActivityDestinasiWisataBinding
import kotlinx.coroutines.launch

class DestinasiWisataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDestinasiWisataBinding
    private lateinit var db: AppDatabase
    private lateinit var adapter: DestinasiWisataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDestinasiWisataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Daftar Destinasi Wisata"

        db = AppDatabase.getInstance(this)

        adapter = DestinasiWisataAdapter()
        binding.rvDestinasi.layoutManager = LinearLayoutManager(this)
        binding.rvDestinasi.adapter = adapter

        loadData()
    }

    private fun loadData() {
        lifecycleScope.launch {
            val data = db.destinasiDao().getAll()
            adapter.submitList(data)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}