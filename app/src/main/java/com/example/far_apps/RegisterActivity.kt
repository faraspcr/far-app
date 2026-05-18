package com.example.far_apps

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.far_apps.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            val name = binding.inputName.text.toString().trim()
            val phone = binding.inputPhone.text.toString().trim()
            val username = binding.inputUsername.text.toString().trim()
            val password = binding.inputPassword.text.toString().trim()

            if (validateInput(name, phone, username, password)) {
                // Bersihkan nomor HP (hanya angka)
                val cleanedPhone = phone.replace(Regex("[^0-9]"), "")

                // Simpan data user sementara
                getSharedPreferences("temp_registration", MODE_PRIVATE).edit()
                    .putString("name", name)
                    .putString("phone", cleanedPhone)
                    .putString("username", username)
                    .putString("password", password)
                    .apply()

                // Pindah ke halaman Verifikasi OTP
                val intent = Intent(this, VerificationActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun validateInput(name: String, phone: String, username: String, password: String): Boolean {
        val cleanedPhone = phone.replace(Regex("[^0-9]"), "")

        if (name.isEmpty()) {
            showError("Nama tidak boleh kosong")
            return false
        }
        if (cleanedPhone.isEmpty()) {
            showError("No. Handphone tidak boleh kosong")
            return false
        }
        if (cleanedPhone.length < 10) {
            showError("No. Handphone minimal 10 digit")
            return false
        }
        if (username.isEmpty()) {
            showError("Username tidak boleh kosong")
            return false
        }
        if (password.isEmpty()) {
            showError("Password tidak boleh kosong")
            return false
        }
        if (password.length < 4) {
            showError("Password minimal 4 karakter")
            return false
        }
        return true
    }

    private fun showError(message: String) {
        AlertDialog.Builder(this)
            .setTitle("Registrasi Gagal")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }
}