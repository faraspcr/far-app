package com.example.far_apps.Home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.far_apps.Home.pertemuan_2.SecondActivity
import com.example.far_apps.Home.pertemuan_4.FocusHealthActivity
import com.example.far_apps.Home.pertemuan_4.MeditationActivity
import com.example.far_apps.Home.pertemuan_5.WebViewActivity
import com.example.far_apps.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TOOLBAR
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Pariwisata Desa"
        }

        // Ambil username dari intent
        val username = requireActivity().intent.getStringExtra("USERNAME") ?: "Admin"
        binding.tvGreeting.text = "Selamat Datang $username!"

        // MENU 1: KALKULATOR BANGUN RUANG
        binding.btnSecondActivity.setOnClickListener {
            startActivity(Intent(requireContext(), SecondActivity::class.java))
        }

        // MENU 2: MEDITASI
        binding.btnMeditation.setOnClickListener {
            val intent = Intent(requireContext(), MeditationActivity::class.java)
            intent.putExtra("USERNAME", username)
            startActivity(intent)
        }

        // MENU 3: FOKUS & HIDUP SEHAT
        binding.btnFocusHealth.setOnClickListener {
            val intent = Intent(requireContext(), FocusHealthActivity::class.java)
            intent.putExtra("USERNAME", username)
            startActivity(intent)
        }

        // MENU 4: WEBSITE BINA DESA
        binding.btnWebView.setOnClickListener {
            startActivity(Intent(requireContext(), WebViewActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}