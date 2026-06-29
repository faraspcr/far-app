package com.example.far_apps.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.far_apps.BaseActivity

class ReminderReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // Ambil data dari intent
        val title = intent.getStringExtra("title") ?: "Pengingat Bina Desa"
        val message = intent.getStringExtra("message") ?: "Waktunya melakukan sesuatu!"
        val targetClassName = intent.getStringExtra("target_activity")

        // Buat Intent ke target activity
        val targetIntent = if (!targetClassName.isNullOrEmpty()) {
            try {
                val clazz = Class.forName(targetClassName)
                Intent(context, clazz).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
            } catch (e: Exception) {
                // Fallback ke BaseActivity jika class tidak ditemukan
                Intent(context, BaseActivity::class.java)
            }
        } else {
            // Default ke BaseActivity
            Intent(context, BaseActivity::class.java)
        }

        // Tampilkan notifikasi
        NotificationHelper.showNotification(
            context = context,
            title = title,
            message = message,
            intent = targetIntent
        )
    }
}