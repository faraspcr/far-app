package com.example.far_apps.pertemuan_3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.far_apps.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("USERNAME") ?: "User"
        binding.tvUserName.text = "Hi $username!"

        setupGetStartedButton()
    }

    private fun setupGetStartedButton() {
        binding.btnGetStarted.setOnClickListener {
            finish()
        }
    }
}