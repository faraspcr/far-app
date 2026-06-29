package com.example.far_apps.Home.pertemuan_9

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.far_apps.Model.MessageModel
import com.example.far_apps.R
import com.google.android.material.snackbar.Snackbar

class DestinasiAdapter(
    context: Context,
    private val destinations: List<MessageModel>
) : ArrayAdapter<MessageModel>(context, 0, destinations) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_destinasi_wisata, parent, false)

        val data = destinations[position]

        val imgDestinasi = view.findViewById<ImageView>(R.id.imgDestinasi)
        val tvDestinasiName = view.findViewById<TextView>(R.id.tvDestinasiName)
        val tvDestinasiDesc = view.findViewById<TextView>(R.id.tvDestinasiDesc)

        // Load gambar dari drawable
        val imageResId = context.resources.getIdentifier(
            data.avatarUrl,
            "drawable",
            context.packageName
        )

        if (imageResId != 0) {
            Glide.with(context)
                .load(imageResId)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .into(imgDestinasi)
        } else {
            // Kalo gak ada, pake placeholder
            Glide.with(context)
                .load(R.drawable.ic_placeholder)
                .into(imgDestinasi)
        }

        tvDestinasiName.text = data.senderName
        tvDestinasiDesc.text = data.messageText

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