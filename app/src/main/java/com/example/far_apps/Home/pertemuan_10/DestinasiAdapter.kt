package com.example.far_apps.Home.pertemuan_10

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.far_apps.databinding.ItemDestinasiWisataBinding  // ← ganti nama binding

class DestinasiAdapter(
    private val destinasiList: List<DestinasiWisataModel>
) : RecyclerView.Adapter<DestinasiAdapter.DestinasiViewHolder>() {

    inner class DestinasiViewHolder(val binding: ItemDestinasiWisataBinding)  // ← ganti
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinasiViewHolder {
        val binding = ItemDestinasiWisataBinding.inflate(  // ← ganti
            LayoutInflater.from(parent.context), parent, false
        )
        return DestinasiViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DestinasiViewHolder, position: Int) {
        val item = destinasiList[position]
        with(holder.binding) {
            tvDestinasiName.text = item.name
            tvDestinasiDesc.text = item.description

            Glide.with(holder.itemView.context)
                .load(item.imageUrl)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.ic_menu_report_image)
                .into(imgDestinasi)

            root.setOnClickListener {
                Toast.makeText(holder.itemView.context, "📍 ${item.name}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int = destinasiList.size
}