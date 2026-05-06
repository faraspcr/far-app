package com.example.far_apps

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.far_apps.databinding.ActivityAuthBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val username = binding.inputUsername.text.toString().trim()
            val password = binding.inputPassword.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                showErrorDialog("Username dan Password tidak boleh kosong!")
                return@setOnClickListener
            }

            // Cek rule login
            if (isValidLogin(username, password)) {
                // Simpan status login
                getSharedPreferences("user_pref", MODE_PRIVATE).edit()
                    .putBoolean("isLogin", true)
                    .putString("current_username", username)
                    .apply()

                // Langsung ke BaseActivity
                val intent = Intent(this, BaseActivity::class.java)
                intent.putExtra("USERNAME", username)
                startActivity(intent)
                finish()
            } else {
                showErrorDialog("Username atau Password salah!")
            }
        }

        // Tombol registrasi
        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun isValidLogin(username: String, password: String): Boolean {
        // RULE 1: username = password
        if (username == password) {
            return true
        }

        // RULE 2: cocok dengan data registrasi
        val userPrefs = getSharedPreferences("user_pref", MODE_PRIVATE)
        val registeredUsername = userPrefs.getString("registered_username", "") ?: ""
        val registeredPassword = userPrefs.getString("registered_password", "") ?: ""

        return username == registeredUsername && password == registeredPassword
    }

    private fun showErrorDialog(message: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Login Gagal")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }
}