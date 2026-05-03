package com.example.far_apps

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.far_apps.databinding.ActivityMainBinding
import com.example.far_apps.Home.pertemuan_2.SecondActivity
import com.example.far_apps.Home.pertemuan_4.FocusHealthActivity
import com.example.far_apps.Home.pertemuan_4.MeditationActivity
import com.example.far_apps.Home.pertemuan_5.WebViewActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // SETUP TOOLBAR
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setTitle("Dashboard Bina Desa")

        val username = intent.getStringExtra("USERNAME") ?: "Admin"
        binding.tvGreeting.text = "Hi $username!"

        // 1. KALKULATOR (tetap ada)
        binding.btnBangunRuang.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }

        // 2. MEDITASI (tetap ada)
        binding.btnMeditation.setOnClickListener {
            val intent = Intent(this, MeditationActivity::class.java)
            intent.putExtra("USERNAME", username)
            startActivity(intent)
        }

        // 3. FOKUS & HIDUP SEHAT (tetap ada)
        binding.btnFocusHealth.setOnClickListener {
            val intent = Intent(this, FocusHealthActivity::class.java)
            intent.putExtra("USERNAME", username)
            startActivity(intent)
        }

        // 4. WEB VIEW BINA DESA (TAMBAHAN)
        binding.btnWebView.setOnClickListener {
            startActivity(Intent(this, WebViewActivity::class.java))
        }

        // 5. LOGOUT
        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Yakin ingin keluar dari Bina Desa?")
                .setPositiveButton("Ya") { _, _ ->
                    val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
                    sharedPref.edit().clear().apply()
                    startActivity(Intent(this, AuthActivity::class.java))
                    finish()
                }
                .setNegativeButton("Tidak", null)
                .show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                Toast.makeText(this, "Pencarian", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_settings -> {
                Toast.makeText(this, "Pengaturan", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}