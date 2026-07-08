package com.example.far_apps.Home.pertemuan_13

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.far_apps.databinding.ActivityCameraCaptureBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class CameraCaptureActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCameraCaptureBinding
    private var photoUri: Uri? = null

    private val cameraPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) openCamera()
        else Toast.makeText(this, "Izin kamera ditolak", Toast.LENGTH_SHORT).show()
    }

    private val takePictureLauncher = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && photoUri != null) {
            binding.ivPreview.setImageURI(photoUri)
            binding.ivPreview.visibility = View.VISIBLE
            Toast.makeText(this, "Foto berhasil disimpan ke galeri", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Gagal mengambil foto", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraCaptureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Ambil Foto"
            setDisplayHomeAsUpEnabled(true)
        }

        binding.btnAmbilFoto.setOnClickListener { checkCameraPermissionAndOpen() }
    }

    private fun checkCameraPermissionAndOpen() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_GRANTED
        ) {
            openCamera()
        } else {
            cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    private fun openCamera() {
        val photoFile = createImageFile()
        photoUri = FileProvider.getUriForFile(this, "$packageName.fileprovider", photoFile)
        takePictureLauncher.launch(photoUri)
    }

    private fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir = getExternalFilesDir("Pictures/BinaDesa")
        if (storageDir != null && !storageDir.exists()) storageDir.mkdirs()
        return File(storageDir, "IMG_$timeStamp.jpg")
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}