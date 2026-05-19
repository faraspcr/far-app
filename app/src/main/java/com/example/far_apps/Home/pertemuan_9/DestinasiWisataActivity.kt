package com.example.far_apps.Home.pertemuan_9

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.far_apps.Model.MessageModel
import com.example.far_apps.databinding.ActivityDestinasiWisataBinding

class DestinasiWisataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDestinasiWisataBinding

    private val destinationList = listOf(
        MessageModel("Air Terjun Gajah Beru", "Air terjun eksotis dengan pemandangan alam yang memukau", "https://avatar.iran.liara.run/public/1"),
        MessageModel("Bukit Karst", "Perbukitan karst yang indah untuk spot foto", "https://avatar.iran.liara.run/public/2"),
        MessageModel("Desa Wisata Pentingsari", "Homestay dan belajar budaya Jawa", "https://avatar.iran.liara.run/public/3"),
        MessageModel("Pantai Ngobaran", "Pantai dengan keunikan budaya dan religi", "https://avatar.iran.liara.run/public/4"),
        MessageModel("Gunung Api Purba Nglanggeran", "Wisata edukasi geologi dan camping", "https://avatar.iran.liara.run/public/5"),
        MessageModel("Kebun Teh Kemuning", "Hamparan kebun teh dengan udara sejuk", "https://avatar.iran.liara.run/public/6"),
        MessageModel("Kampung Batik Laweyan", "Belajar dan membeli batik khas Solo", "https://avatar.iran.liara.run/public/7"),
        MessageModel("Taman Nasional Baluran", "Savana tropis ala Afrika di Jawa Timur", "https://avatar.iran.liara.run/public/8"),
        MessageModel("Kawah Ijen", "Blue fire dan pemandangan kawah yang menakjubkan", "https://avatar.iran.liara.run/public/9"),
        MessageModel("Pulau Derawan", "Spot snorkeling dan diving terbaik", "https://avatar.iran.liara.run/public/10")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDestinasiWisataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Daftar Destinasi Wisata"

        // Set adapter
        val adapter = DestinasiAdapter(this, destinationList)
        binding.listViewDestinasi.adapter = adapter
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}