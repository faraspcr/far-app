package com.example.far_apps.pertemuan_4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.far_apps.databinding.ActivityMeditationBinding

class MeditationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMeditationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMeditationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("USERNAME") ?: "User"
        binding.tvTitle.text = "Meditation"
        binding.tvDescription.text = "Hi $username, take a moment to breathe and relax. Find your inner peace with our guided meditation sessions."

        // Tombol back
        binding.btnBack.setOnClickListener {
            finish() // Kembali ke halaman sebelumnya
        }
    }
}