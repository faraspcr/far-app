package com.example.far_apps.Home.pertemuan_4

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.far_apps.R
import com.example.far_apps.databinding.ActivityFocusHealthBinding

class FocusHealthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFocusHealthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFocusHealthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle("Fokus & Hidup Sehat")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val username = intent.getStringExtra("USERNAME") ?: "User"
        binding.tvUserName.text = "$username ✨"

        binding.btnMeditate.setOnClickListener {
            Toast.makeText(this, "Let's meditate 🧘", Toast.LENGTH_SHORT).show()
        }

        binding.btnJournal.setOnClickListener {
            Toast.makeText(this, "Open journal 📝", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}