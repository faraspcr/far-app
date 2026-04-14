package com.example.far_apps

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.far_apps.databinding.ActivityMainBinding
import com.example.far_apps.pertemuan_2.SecondActivity
import com.example.far_apps.pertemuan_3.LoginActivity
import com.example.far_apps.pertemuan_4.MeditationActivity
import com.example.far_apps.pertemuan_4.FocusHealthActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var username: String = "User"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        username = intent.getStringExtra("USERNAME") ?: "User"
        binding.tvGreeting.text = "Hi $username!"
        setupButtons()
    }
    private fun setupButtons() {
        binding.btnBangunRuang.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }

        binding.btnMeditation.setOnClickListener {
            val intent = Intent(this, MeditationActivity::class.java)
            intent.putExtra("USERNAME", username)
            startActivity(intent)
        }

        binding.btnFocusHealth.setOnClickListener {
            val intent = Intent(this, FocusHealthActivity::class.java)
            intent.putExtra("USERNAME", username)
            startActivity(intent)
        }

        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah Anda yakin ingin logout?")
                .setPositiveButton("Ya") { _, _ ->
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                }
                .setNegativeButton("Tidak") { _, _ ->
                    Snackbar.make(binding.root, "Logout dibatalkan", Snackbar.LENGTH_SHORT).show()
                }
                .show()
        }
    }
}