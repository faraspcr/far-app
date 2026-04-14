package com.example.far_apps.pertemuan_4

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.far_apps.databinding.ActivityFocusHealthBinding

class FocusHealthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFocusHealthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFocusHealthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil username dari intent (dari LoginActivity)
        val username = intent.getStringExtra("USERNAME") ?: "User"
        binding.tvUserName.text = "$username ✨"

        // Tombol back
        binding.btnBack.setOnClickListener {
            finish()
        }

        // Tombol Meditate
        binding.btnMeditate.setOnClickListener {
            Toast.makeText(this, "Let's meditate 🧘", Toast.LENGTH_SHORT).show()
        }

        // Tombol Journal
        binding.btnJournal.setOnClickListener {
            Toast.makeText(this, "Open journal 📝", Toast.LENGTH_SHORT).show()
        }
    }
}