package com.example.far_apps.Home.pertemuan_13

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.far_apps.databinding.ActivityQrGeneratorBinding
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter

class QrGeneratorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQrGeneratorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQrGeneratorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Generate QR Code"
            setDisplayHomeAsUpEnabled(true)
        }

        binding.btnGenerate.setOnClickListener {
            val teks = binding.etTeksQr.text.toString().trim()
            if (teks.isEmpty()) {
                Toast.makeText(this, "Masukkan teks dulu!", Toast.LENGTH_SHORT).show()
            } else {
                val bitmap = generateQrCode(teks)
                if (bitmap != null) {
                    binding.ivQrResult.setImageBitmap(bitmap)
                    binding.ivQrResult.visibility = View.VISIBLE
                } else {
                    Toast.makeText(this, "Gagal membuat QR Code", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun generateQrCode(text: String, size: Int = 512): Bitmap? {
        return try {
            val writer = QRCodeWriter()
            val bitMatrix = writer.encode(text, BarcodeFormat.QR_CODE, size, size)
            val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565)
            for (x in 0 until size) {
                for (y in 0 until size) {
                    bitmap.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                }
            }
            bitmap
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}