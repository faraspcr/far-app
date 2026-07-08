package com.example.far_apps.Home.pertemuan_13

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.far_apps.databinding.ActivityThirteenthBinding

class ThirteenthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirteenthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirteenthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Kamera & QR Code"
            setDisplayHomeAsUpEnabled(true)
        }

        binding.btnBukaKamera.setOnClickListener {
            startActivity(Intent(this, CameraCaptureActivity::class.java))
        }

        binding.btnGenerateQr.setOnClickListener {
            startActivity(Intent(this, QrGeneratorActivity::class.java))
        }

        binding.btnScanQr.setOnClickListener {
            startActivity(Intent(this, QrScannerActivity::class.java))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}