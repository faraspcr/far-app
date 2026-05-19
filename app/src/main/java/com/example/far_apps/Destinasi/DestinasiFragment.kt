package com.example.far_apps.Destinasi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.far_apps.Model.MessageModel
import com.example.far_apps.databinding.FragmentDestinasiBinding

class DestinasiFragment : Fragment() {

    private var _binding: FragmentDestinasiBinding? = null
    private val binding get() = _binding!!

    // Data destinasi wisata
    private val destinationList = listOf(
        MessageModel("Air Terjun Gajah Beru", "Air terjun eksotis dengan pemandangan alam yang memukau", "airterjun"),
        MessageModel("Bukit Karst", "Perbukitan karst yang indah untuk spot foto", "bukitkaret"),
        MessageModel("Desa Wisata Pentingsari", "Homestay dan belajar budaya Jawa", "wisatapentingsari"),
        MessageModel("Pantai Ngobaran", "Pantai dengan keunikan budaya dan religi", "pantaingobaran"),
        MessageModel("Gunung Api Purba Nglanggeran", "Wisata edukasi geologi dan camping", "nglanggeran"),
        MessageModel("Kebun Teh Kemuning", "Hamparan kebun teh dengan udara sejuk", "kebuntehkemuning"),
        MessageModel("Kampung Batik Laweyan", "Belajar dan membeli batik khas Solo", "kampungbatiklaweyan"),
        MessageModel("Taman Nasional Baluran", "Savana tropis ala Afrika di Jawa Timur", "tamannasionalbaluran"),
        MessageModel("Kawah Ijen", "Blue fire dan pemandangan kawah yang menakjubkan", "kawahijen"),
        MessageModel("Pulau Derawan", "Spot snorkeling dan diving terbaik", "pulauderawan")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDestinasiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TOOLBAR
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Destinasi Wisata Desa"
        }

        // Set adapter ListView dengan CustomAdapter
        val adapter = DestinasiListAdapter(requireContext(), destinationList)
        binding.listViewDestinasi.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}