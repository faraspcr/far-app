package com.example.far_apps.Destinasi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.far_apps.Model.MessageModel
import com.example.far_apps.databinding.ItemDestinasiBinding
import com.google.android.material.snackbar.Snackbar

class DestinasiListAdapter(
    context: Context,
    private val destinations: List<MessageModel>
) : ArrayAdapter<MessageModel>(context, 0, destinations) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemDestinasiBinding.inflate(LayoutInflater.from(context), parent, false)
        val view = binding.root
        val data = destinations[position]

        // Load gambar dari drawable berdasarkan nama file
        val drawableId = context.resources.getIdentifier(data.avatarUrl, "drawable", context.packageName)

        if (drawableId != 0) {
            // Jika gambar ditemukan di drawable
            Glide.with(context)
                .load(drawableId)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.ic_menu_report_image)
                .into(binding.avatarImg)
        } else {
            // Jika gambar tidak ditemukan, pakai default
            binding.avatarImg.setImageResource(android.R.drawable.ic_menu_gallery)
        }

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