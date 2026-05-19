package com.example.far_apps.Home.pertemuan_9

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.far_apps.databinding.ActivityNinthBinding
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar

class NinthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNinthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNinthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar dengan tombol back
        setSupportActionBar(binding.toolbarNinth)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // 1. Chip Group Listener
        binding.chipGroupFilter.setOnCheckedStateChangeListener { group, checkedIds ->
            if (checkedIds.isNotEmpty()) {
                val selectedChip = group.findViewById<Chip>(checkedIds[0])
                Toast.makeText(this, "Filter: ${selectedChip.text}", Toast.LENGTH_SHORT).show()
            }
        }

        // 2. Tombol Login (Email & Password)
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            when {
                email.isEmpty() -> binding.tilEmail.error = "Email tidak boleh kosong"
                password.isEmpty() -> binding.tilPassword.error = "Password tidak boleh kosong"
                else -> {
                    binding.tilEmail.error = null
                    binding.tilPassword.error = null
                    Snackbar.make(binding.root, "Login berhasil! Selamat datang", Snackbar.LENGTH_LONG).show()

                    // Kosongkan form
                    binding.etEmail.text?.clear()
                    binding.etPassword.text?.clear()
                }
            }
        }

        // 3. Tombol Lihat Destinasi
        binding.btnLihatDestinasi.setOnClickListener {
            val intent = Intent(this, DestinasiWisataActivity::class.java)
            startActivity(intent)
        }

        // 4. GridLayout Menu Kotak-Kotak
        setupGridMenu()
    }

    private fun setupGridMenu() {
        binding.menuDestinasi.setOnClickListener {
            Toast.makeText(this, "Menu Destinasi Wisata", Toast.LENGTH_SHORT).show()
        }
        binding.menuHomestay.setOnClickListener {
            Toast.makeText(this, "Menu Homestay Desa", Toast.LENGTH_SHORT).show()
        }
        binding.menuEvent.setOnClickListener {
            Toast.makeText(this, "Menu Event Desa", Toast.LENGTH_SHORT).show()
        }
        binding.menuKuliner.setOnClickListener {
            Toast.makeText(this, "Menu Kuliner Khas", Toast.LENGTH_SHORT).show()
        }
        binding.menuOlehOleh.setOnClickListener {
            Toast.makeText(this, "Menu Oleh-oleh", Toast.LENGTH_SHORT).show()
        }
        binding.menuTransport.setOnClickListener {
            Toast.makeText(this, "Menu Transportasi", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}