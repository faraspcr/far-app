package com.example.far_apps.TripNotes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.far_apps.data.entity.TripNotesEntity
import com.example.far_apps.databinding.ItemTripNoteBinding
import java.text.SimpleDateFormat
import java.util.*

class TripNotesAdapter(
    private var listTripNotes: List<TripNotesEntity>,
    private val onEditClick: (TripNotesEntity) -> Unit,
    private val onDeleteClick: (TripNotesEntity) -> Unit
) : RecyclerView.Adapter<TripNotesAdapter.TripNotesViewHolder>() {

    inner class TripNotesViewHolder(private val binding: ItemTripNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tripNotes: TripNotesEntity) {
            binding.tvJudul.text = tripNotes.judul
            binding.tvTempatWisata.text = "📍 ${tripNotes.tempatWisata}"
            binding.tvTips.text = "💡 ${tripNotes.tips}"

            val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
            binding.tvTanggal.text = "📅 ${dateFormat.format(Date(tripNotes.tanggalKunjungan))}"

            binding.ratingBar.rating = tripNotes.rating.toFloat()

            binding.btnEdit.setOnClickListener { onEditClick(tripNotes) }
            binding.btnDelete.setOnClickListener { onDeleteClick(tripNotes) }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripNotesViewHolder {
        val binding = ItemTripNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TripNotesViewHolder(binding)
    }
    override fun onBindViewHolder(holder: TripNotesViewHolder, position: Int) {
        holder.bind(listTripNotes[position])
    }
    override fun getItemCount(): Int = listTripNotes.size

    fun updateData(newList: List<TripNotesEntity>) {
        listTripNotes = newList
        notifyDataSetChanged()
    }
}