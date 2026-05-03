package com.example.far_apps.Home.pertemuan_6

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.far_apps.AuthActivity
import com.example.far_apps.MainActivity
import com.example.far_apps.R

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Jeda 2000ms (2 detik)
        Handler(Looper.getMainLooper()).postDelayed({

            // CEK SHAREDPREFERENCES
            val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
            val isLogin = sharedPref.getBoolean("isLogin", false)

            if (isLogin) {
                // Jika sudah login, langsung ke Dashboard Utama
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                // Jika belum login, arahkan ke halaman Login (Auth)
                val intent = Intent(this, AuthActivity::class.java)
                startActivity(intent)
            }

            // Tutup Splash agar tidak bisa di-back
            finish()

        }, 2000) // 2 DETIK
    }
}