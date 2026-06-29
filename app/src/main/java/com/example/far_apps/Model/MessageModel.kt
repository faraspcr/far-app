package com.example.far_apps.Model

data class MessageModel(
    val senderName: String,
    val messageText: String,
    val avatarUrl: String,
    val lokasi: String = "",    // ← TAMBAHKAN (dengan default value)
    val harga: String = ""      // ← TAMBAHKAN (dengan default value)
)