package com.example.far_apps.pertemuan_2

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.far_apps.R
import kotlin.math.PI

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle("Kalkulator Bangun Ruang")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val inputJari = findViewById<EditText>(R.id.inputJari)
        val inputTinggi = findViewById<EditText>(R.id.inputTinggi)
        val btnLuas = findViewById<Button>(R.id.btnLuas)
        val btnVolume = findViewById<Button>(R.id.btnVolume)
        val tvHasil = findViewById<TextView>(R.id.tvHasil)

        btnLuas.setOnClickListener {
            val r = inputJari.text.toString().toDoubleOrNull() ?: 0.0
            val hasil = PI * r * r
            tvHasil.text = "Luas Lingkaran: %.2f".format(hasil)
        }

        btnVolume.setOnClickListener {
            val r = inputJari.text.toString().toDoubleOrNull() ?: 0.0
            val t = inputTinggi.text.toString().toDoubleOrNull() ?: 0.0
            val hasil = PI * r * r * t
            tvHasil.text = "Volume Tabung: %.2f".format(hasil)
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