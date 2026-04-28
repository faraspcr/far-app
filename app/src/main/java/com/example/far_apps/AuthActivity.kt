package com.example.far_apps

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.far_apps.databinding.ActivityAuthBinding
import com.example.far_apps.pertemuan_3.WelcomeActivity

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val username = binding.inputUsername.text.toString().trim()
            val password = binding.inputPassword.text.toString().trim()

            if (username == password && username.isNotEmpty()) {
                // SIMPAN LOGIN
                getSharedPreferences("user_pref", MODE_PRIVATE).edit()
                    .putBoolean("isLogin", true)
                    .apply()

                // LANGSUNG KE WELCOME ATAU MAIN?
                // Pilih salah satu:
//
//                // OPSI 1: KE WELCOME DULU
//                val intent = Intent(this, WelcomeActivity::class.java)
//                intent.putExtra("USERNAME", username)
//                startActivity(intent)
//                finish()

//                 OPSI 2: LANGSUNG KE MAIN (HAPUS YANG OPSI 1, PAKAI INI)
                 val intent = Intent(this, MainActivity::class.java)
                 intent.putExtra("USERNAME", username)
                 startActivity(intent)
                 finish()

            } else {
                AlertDialog.Builder(this)
                    .setTitle("Login Gagal")
                    .setMessage("Username & Password harus sama dan tidak boleh kosong!")
                    .setPositiveButton("OK", null)
                    .show()
            }
        }
    }
}