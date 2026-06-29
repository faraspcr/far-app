package com.example.far_apps.Home.pertemuan_9

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.far_apps.data.entity.DestinasiEntity
import com.example.far_apps.databinding.ItemDestinasiWisataBinding

class DestinasiWisataAdapter : RecyclerView.Adapter<DestinasiWisataAdapter.ViewHolder>() {

    private var dataList = mutableListOf<DestinasiEntity>()

    fun submitList(list: List<DestinasiEntity>) {
        dataList = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDestinasiWisataBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.binding.tvDestinasiName.text = data.namaDestinasi
        holder.binding.tvDestinasiDesc.text = data.deskripsi

        Glide.with(holder.itemView.context)
            .load(data.gambarResId)
            .placeholder(com.example.far_apps.R.drawable.ic_placeholder)
            .into(holder.binding.imgDestinasi)
    }

    override fun getItemCount(): Int = dataList.size

    class ViewHolder(val binding: ItemDestinasiWisataBinding) :
        RecyclerView.ViewHolder(binding.root)
}