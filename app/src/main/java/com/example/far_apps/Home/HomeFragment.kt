package com.example.far_apps.Home

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.far_apps.AuthActivity
import com.example.far_apps.Home.berita.BeritaAdapter
import com.example.far_apps.Home.pertemuan_10.TenthActivity
import com.example.far_apps.Home.pertemuan_13.CameraCaptureActivity
import com.example.far_apps.Home.pertemuan_2.SecondActivity
import com.example.far_apps.Home.pertemuan_4.FocusHealthActivity
import com.example.far_apps.Home.pertemuan_4.MeditationActivity
import com.example.far_apps.Home.pertemuan_5.WebViewActivity
import com.example.far_apps.Home.pertemuan_9.DestinasiWisataActivity
import com.example.far_apps.data.api.CatFactApiClient
import com.example.far_apps.data.model.Article
import com.example.far_apps.data.model.Source
import com.example.far_apps.databinding.FragmentHomeBinding
import com.example.far_apps.utils.PermissionHelper
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    // Launcher untuk permission notifikasi
    private val notificationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Toast.makeText(requireContext(), "Notifikasi diizinkan", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Notifikasi ditolak", Toast.LENGTH_SHORT).show()
        }
    }

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

        // Ambil username dari intent atau SharedPreferences
        val username = requireActivity().intent.getStringExtra("USERNAME")
            ?: requireContext().getSharedPreferences("user_pref", android.content.Context.MODE_PRIVATE)
                .getString("USERNAME", "Admin")
        binding.tvGreeting.text = "Selamat Datang $username!"

        // Setup semua tombol
        setupButtons()

        // Load Fakta Kucing
        loadCatFact()

        // Load Berita
        loadBerita()

        // Tombol refresh fakta kucing
        binding.btnRefreshCatFact.setOnClickListener {
            loadCatFact()
        }

        // Cek permission notifikasi untuk Android 13+
        if (PermissionHelper.isNotificationPermissionRequired()) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            if (!PermissionHelper.hasPermission(requireContext(), permission)) {
                PermissionHelper.requestPermission(notificationPermissionLauncher, permission)
            }
        }
    }

    private fun setupButtons() {
        // MENU 1: KALKULATOR BANGUN RUANG
        binding.btnSecondActivity.setOnClickListener {
            startActivity(Intent(requireContext(), SecondActivity::class.java))
        }

        // MENU 2: MEDITASI
        binding.btnMeditation.setOnClickListener {
            val intent = Intent(requireContext(), MeditationActivity::class.java)
            val username =
                binding.tvGreeting.text.toString().replace("Selamat Datang ", "").replace("!", "")
            intent.putExtra("USERNAME", username)
            startActivity(intent)
        }

        // MENU 3: FOKUS & HIDUP SEHAT
        binding.btnFocusHealth.setOnClickListener {
            val intent = Intent(requireContext(), FocusHealthActivity::class.java)
            val username =
                binding.tvGreeting.text.toString().replace("Selamat Datang ", "").replace("!", "")
            intent.putExtra("USERNAME", username)
            startActivity(intent)
        }

        // MENU 4: WEBSITE BINA DESA
        binding.btnWebView.setOnClickListener {
            startActivity(Intent(requireContext(), WebViewActivity::class.java))
        }

        // MENU 5: PERTEMUAN 9 - MATERIAL DESIGN
        binding.btnPertemuan9.setOnClickListener {
            val intent = Intent(
                requireContext(),
                com.example.far_apps.Home.pertemuan_9.NinthActivity::class.java
            )
            startActivity(intent)
        }

        // MENU 6: PERTEMUAN 10 - TABLAYOUT & RECYCLERVIEW
        binding.btnPertemuan10.setOnClickListener {
            val intent = Intent(requireContext(), TenthActivity::class.java)
            startActivity(intent)
        }
        // MENU 7: PERTEMUAN 13
        binding.btnPertemuan13.setOnClickListener {
            val intent = Intent(requireContext(), com.example.far_apps.Home.pertemuan_13.ThirteenthActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadCatFact() {
        lifecycleScope.launch {
            try {
                val response = CatFactApiClient.apiService.getCatFact()
                if (_binding != null) {
                    binding.tvCatFact.text = "\"${response.fact}\""
                    binding.tvCatFact.visibility = View.VISIBLE
                }
            } catch (e: Exception) {
                e.printStackTrace()
                if (_binding != null) {
                    binding.tvCatFact.text = "Gagal memuat fakta kucing. Periksa koneksi internet."
                    binding.tvCatFact.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun loadBerita() {
        binding.progressBar.visibility = View.VISIBLE

        val mockArticles = listOf(
            Article(
                source = Source(null, "Republika.co.id"),
                author = "Republika",
                title = "Desa Wisata Penglipuran Bali Masuk 10 Desa Terindah di Dunia",
                description = "Desa adat yang masih sangat lestari dengan arsitektur rumah khas Bali ini berhasil masuk daftar desa terindah.",
                url = "https://www.republika.co.id/penglipuran-bali",
                urlToImage = "https://picsum.photos/id/169/200/150",
                publishedAt = "2026-06-08T07:30:00Z",
                content = null
            ),
            Article(
                source = Source(null, "Tempo.co"),
                author = "Tempo",
                title = "Naik Jeep Menikmati Sunrise di Desa Wisata Bromo Tengger Semeru",
                description = "Paket wisata jeep sunrise di Desa Ngadas, Probolinggo kini menjadi favorit wisatawan.",
                url = "https://www.tempo.co/wisata-jeep-bromo",
                urlToImage = "https://picsum.photos/id/15/200/150",
                publishedAt = "2026-06-07T19:20:00Z",
                content = null
            ),
            Article(
                source = Source(null, "Jawa Pos"),
                author = "Jawa Pos",
                title = "Desa Wisata Ketep Pass Magelang, Spot Foto dengan Latar Gunung Merapi",
                description = "Objek wisata edukasi dengan teleskop canggih untuk melihat aktivitas Gunung Merapi dan Merbabu.",
                url = "https://www.jawapos.com/ketep-pass-magelang",
                urlToImage = "https://picsum.photos/id/96/200/150",
                publishedAt = "2026-06-07T10:15:00Z",
                content = null
            ),
            Article(
                source = Source(null, "Koran Sindo"),
                author = "Sindo News",
                title = "Camping Ground Hits di Desa Wisata Cikole Lembang",
                description = "Area perkemahan dengan pemandangan hutan pinus dan udara sejuk jadi incaran wisatawan akhir pekan.",
                url = "https://www.koransindo.com/camping-cikole",
                urlToImage = "https://picsum.photos/id/104/200/150",
                publishedAt = "2026-06-06T14:45:00Z",
                content = null
            ),
            Article(
                source = Source(null, "Medcom.id"),
                author = "Medcom",
                title = "Desa Wisata Wae Rebo Flores, Desa di Atas Awan yang Mendunia",
                description = "Desa tradisional dengan rumah adat berbentuk kerucut ini berada di ketinggian 1.200 mdpl.",
                url = "https://www.medcom.id/wae-rebo-flores",
                urlToImage = "https://picsum.photos/id/96/200/150",
                publishedAt = "2026-06-05T09:00:00Z",
                content = null
            ),
            Article(
                source = Source(null, "Liputan6.com"),
                author = "Liputan6",
                title = "Taman Bunga Desa Wisata Lembang Park Zoo, Hits Instagramable",
                description = "Kombinasi kebun binatang mini dan taman bunga warna-warni dengan latar pegunungan.",
                url = "https://www.liputan6.com/lembang-park-zoo",
                urlToImage = "https://picsum.photos/id/106/200/150",
                publishedAt = "2026-06-04T11:30:00Z",
                content = null
            )
        )

        val adapter = BeritaAdapter(mockArticles)
        binding.rvBerita.layoutManager = LinearLayoutManager(requireContext())
        binding.rvBerita.adapter = adapter
        binding.progressBar.visibility = View.GONE

        Toast.makeText(requireContext(), "Berhasil memuat ${mockArticles.size} destinasi wisata desa", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}