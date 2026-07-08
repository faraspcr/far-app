package com.example.far_apps

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.far_apps.About.AboutFragment
import com.example.far_apps.Destinasi.DestinasiFragment
import com.example.far_apps.Home.HomeFragment
import com.example.far_apps.Profile.ProfileFragment
import com.example.far_apps.TripNotes.TripNotesFragment
import com.example.far_apps.databinding.ActivityBaseBinding

class BaseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBaseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Cek apakah dari notifikasi
        val openFragment = intent.getStringExtra("open_fragment")

        if (openFragment == "destinasi") {
            // Langsung buka DestinasiFragment
            replaceFragment(DestinasiFragment())
            // Set selected item di bottom nav
            binding.bottomNavView.selectedItemId = R.id.destinasi
        } else {
            // Default ke HomeFragment
            replaceFragment(HomeFragment())
        }

        binding.bottomNavView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.destinasi -> {
                    replaceFragment(DestinasiFragment())
                    true
                }
                R.id.tripNotes -> {
                    replaceFragment(TripNotesFragment())
                    true
                }
                R.id.about -> {
                    replaceFragment(AboutFragment())
                    true
                }
                R.id.profile -> {
                    replaceFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }
}