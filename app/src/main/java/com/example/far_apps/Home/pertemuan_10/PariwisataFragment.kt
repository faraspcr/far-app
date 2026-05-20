package com.example.far_apps.Home.pertemuan_10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.far_apps.databinding.FragmentPariwisataBinding

class PariwisataFragment : Fragment() {

    private var _binding: FragmentPariwisataBinding? = null
    private val binding get() = _binding!!

    // DATA DESTINASI WISATA (dari materi kamu)
    private val destinasiList = listOf(
        DestinasiWisataModel("Air Terjun Gajah Beru", "Air terjun eksotis dengan pemandangan alam yang memukau", "https://picsum.photos/id/43/400/300"),
        DestinasiWisataModel("Bukit Karst", "Perbukitan karst yang indah untuk spot foto", "https://picsum.photos/id/104/400/300"),
        DestinasiWisataModel("Desa Wisata Pentingsari", "Homestay dan belajar budaya Jawa", "https://picsum.photos/id/15/400/300"),
        DestinasiWisataModel("Pantai Ngobaran", "Pantai dengan keunikan budaya dan religi", "https://picsum.photos/id/10/400/300"),
        DestinasiWisataModel("Gunung Api Purba Nglanggeran", "Wisata edukasi geologi dan camping", "https://picsum.photos/id/29/400/300"),
        DestinasiWisataModel("Kebun Teh Kemuning", "Hamparan kebun teh dengan udara sejuk", "https://picsum.photos/id/66/400/300"),
        DestinasiWisataModel("Kampung Batik Laweyan", "Belajar dan membeli batik khas Solo", "https://picsum.photos/id/20/400/300"),
        DestinasiWisataModel("Taman Nasional Baluran", "Savana tropis ala Afrika di Jawa Timur", "https://picsum.photos/id/58/400/300")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPariwisataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = DestinasiAdapter(destinasiList)

        binding.rvPariwisata.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            this.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}