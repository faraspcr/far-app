package com.example.far_apps

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.far_apps.databinding.ActivityVerificationBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class VerificationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVerificationBinding
    private lateinit var timer: CountDownTimer
    private var expectedOtp = ""  // 4 digit terakhir no HP
    private var registeredPhoneNumber = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil nomor handphone
        val prefs = getSharedPreferences("temp_registration", MODE_PRIVATE)
        registeredPhoneNumber = prefs.getString("phone", "") ?: ""

        if (registeredPhoneNumber.isEmpty()) {
            MaterialAlertDialogBuilder(this)
                .setTitle("Error")
                .setMessage("Data registrasi tidak ditemukan. Silakan registrasi ulang.")
                .setPositiveButton("OK") { _, _ ->
                    startActivity(Intent(this, RegisterActivity::class.java))
                    finish()
                }
                .setCancelable(false)
                .show()
            return
        }

        // AMBIL 4 DIGIT TERAKHIR DARI NO HP
        expectedOtp = getLastFourDigits(registeredPhoneNumber)

        startTimer()
        showOtpDialog(expectedOtp)

        binding.btnVerify.setOnClickListener {
            val inputOtp = binding.inputOtp.text.toString().trim()

            when {
                inputOtp.isEmpty() -> {
                    showErrorDialog("Kode OTP tidak boleh kosong!")
                }
                inputOtp == expectedOtp -> {
                    saveUserToSharedPreferences()
                    Toast.makeText(this, "Verifikasi Berhasil!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, AuthActivity::class.java))
                    finish()
                }
                else -> {
                    showErrorDialog("Kode OTP yang Anda masukkan salah!")
                }
            }
        }

        binding.btnResendOtp.setOnClickListener {
            showOtpDialog(expectedOtp)
            restartTimer()
        }
    }

    // FUNGSI AMBIL 4 DIGIT TERAKHIR
    private fun getLastFourDigits(phoneNumber: String): String {
        return if (phoneNumber.length >= 4) {
            phoneNumber.takeLast(4)
        } else {
            phoneNumber  // kalau kurang dari 4 digit, ya pakai semua
        }
    }

    private fun showErrorDialog(message: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Verifikasi Gagal")
            .setMessage(message)
            .setPositiveButton("Coba Lagi", null)
            .show()
    }

    private fun saveUserToSharedPreferences() {
        val tempPrefs = getSharedPreferences("temp_registration", MODE_PRIVATE)

        val name = tempPrefs.getString("name", "") ?: ""
        val phone = tempPrefs.getString("phone", "") ?: ""
        val username = tempPrefs.getString("username", "") ?: ""
        val password = tempPrefs.getString("password", "") ?: ""

        val userPrefs = getSharedPreferences("user_pref", MODE_PRIVATE)
        userPrefs.edit()
            .putString("registered_name", name)
            .putString("registered_phone", phone)
            .putString("registered_username", username)
            .putString("registered_password", password)
            .putBoolean("hasRegistered", true)
            .apply()

        tempPrefs.edit().clear().apply()
    }

    private fun showOtpDialog(otp: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Kode OTP Anda")
            .setMessage("Kode OTP: $otp\n\n(4 digit terakhir dari No. Handphone Anda)")
            .setPositiveButton("OK", null)
            .show()
    }

    private fun startTimer() {
        timer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.tvTimer.text = "Kirim ulang dalam: ${millisUntilFinished / 1000} detik"
                binding.btnResendOtp.isEnabled = false
            }

            override fun onFinish() {
                binding.tvTimer.text = "Kode OTP sudah kadaluarsa"
                binding.btnResendOtp.isEnabled = true
            }
        }.start()
    }

    private fun restartTimer() {
        timer.cancel()
        startTimer()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::timer.isInitialized) timer.cancel()
    }
}