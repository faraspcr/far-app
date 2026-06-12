package com.example.far_apps.Destinasi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.far_apps.data.entity.DestinasiEntity
import com.example.far_apps.databinding.ItemDestinasiBinding

class DestinasiRoomAdapter(
    private var listDestinasi: List<DestinasiEntity>,
    private val onEditClick: (DestinasiEntity) -> Unit,
    private val onDeleteClick: (DestinasiEntity) -> Unit
) : RecyclerView.Adapter<DestinasiRoomAdapter.DestinasiViewHolder>() {

    inner class DestinasiViewHolder(private val binding: ItemDestinasiBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(destinasi: DestinasiEntity) {
            binding.textSender.text = destinasi.namaDestinasi
            binding.textMessage.text = destinasi.deskripsi
            binding.tvLokasi.text = "📍 ${destinasi.lokasi}"
            binding.tvHargaTiket.text = "💰 ${destinasi.hargaTiket}"

            // Set gambar dari drawable
            if (destinasi.gambarResId != 0) {
                binding.avatarImg.setImageResource(destinasi.gambarResId)
            } else {
                binding.avatarImg.setImageResource(android.R.drawable.ic_menu_gallery)
            }

            binding.btnEdit.setOnClickListener { onEditClick(destinasi) }
            binding.btnDelete.setOnClickListener { onDeleteClick(destinasi) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinasiViewHolder {
        val binding = ItemDestinasiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DestinasiViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DestinasiViewHolder, position: Int) {
        holder.bind(listDestinasi[position])
    }

    override fun getItemCount(): Int = listDestinasi.size

    fun updateData(newList: List<DestinasiEntity>) {
        listDestinasi = newList
        notifyDataSetChanged()
    }
}