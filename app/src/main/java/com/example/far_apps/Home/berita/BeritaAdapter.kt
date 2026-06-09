package com.example.far_apps.Home.berita

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.far_apps.R
import com.example.far_apps.data.model.Article
import com.example.far_apps.databinding.ItemBeritaBinding
import java.text.SimpleDateFormat
import java.util.*

class BeritaAdapter(
    private val items: List<Article>
) : RecyclerView.Adapter<BeritaAdapter.BeritaViewHolder>() {
    inner class BeritaViewHolder(val binding: ItemBeritaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Article) {
            binding.tvJudul.text = item.title
            binding.tvDeskripsi.text = item.description ?: "Tidak ada deskripsi"
            binding.tvTanggal.text = formatDate(item.publishedAt)

            // LOAD GAMBAR DENGAN GLIDE
            val imageUrl = item.urlToImage
            if (imageUrl != null && imageUrl.isNotEmpty()) {
                Glide.with(binding.root.context)
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_placeholder)
                    .into(binding.imgBerita)
            } else {
                binding.imgBerita.setImageResource(R.drawable.ic_placeholder)
            }

            // Klik untuk buka URL berita
            binding.root.setOnClickListener {
                try {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.url))
                    binding.root.context.startActivity(intent)
                } catch (e: Exception) {
                    Toast.makeText(
                        binding.root.context,
                        "Tidak bisa membuka link",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        private fun formatDate(dateString: String): String {
            return try {
                val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
                inputFormat.timeZone = TimeZone.getTimeZone("UTC")
                val date = inputFormat.parse(dateString)
                val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                if (date != null) outputFormat.format(date) else dateString.take(10)
            } catch (e: Exception) {
                dateString.take(10)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeritaViewHolder {
        val binding = ItemBeritaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BeritaViewHolder(binding)
    }


    override fun onBindViewHolder(holder: BeritaViewHolder, position: Int) {
        holder.bind(items[position])
    }


    override fun getItemCount(): Int = items.size
}

