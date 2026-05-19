package com.example.far_apps.Home.pertemuan_9

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.far_apps.Model.MessageModel
import com.example.far_apps.databinding.ItemDestinasiBinding
import com.google.android.material.snackbar.Snackbar

class DestinasiAdapter(
    context: Context,
    private val destinations: List<MessageModel>
) : ArrayAdapter<MessageModel>(context, 0, destinations) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemDestinasiBinding.inflate(LayoutInflater.from(context), parent, false)
        val view = binding.root
        val data = destinations[position]

        // Load gambar dengan Glide
        Glide.with(context)
            .load(data.avatarUrl)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.ic_menu_report_image)
            .into(binding.avatarImg)

        binding.textSender.text = data.senderName
        binding.textMessage.text = data.messageText

        // OnClick item
        view.setOnClickListener {
            Snackbar.make(
                parent,
                "📍 ${data.senderName} - ${data.messageText}",
                Snackbar.LENGTH_SHORT
            ).show()
        }

        return view
    }
}