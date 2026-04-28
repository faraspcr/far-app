package com.example.far_apps.pertemuan_4

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.far_apps.R
import com.example.far_apps.databinding.ActivityMeditationBinding

class MeditationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMeditationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMeditationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle("Meditasi")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val username = intent.getStringExtra("USERNAME") ?: "User"
        binding.tvTitle.text = "Meditation"
        binding.tvDescription.text = "Hi $username, take a moment to breathe and relax. Find your inner peace with our guided meditation sessions."
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}