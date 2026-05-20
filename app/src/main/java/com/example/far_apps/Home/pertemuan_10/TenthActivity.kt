package com.example.far_apps.Home.pertemuan_10

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayoutMediator
import com.example.far_apps.R
import com.example.far_apps.databinding.ActivityTenthBinding

class TenthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTenthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTenthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Pariwisata Desa"
            setDisplayHomeAsUpEnabled(true)
        }

        val adapter = TenthTabsAdapter(this)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Desa"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_desa)
                }
                1 -> {
                    tab.text = "Homestay"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_homestay)
                }
                2 -> {
                    tab.text = "Pariwisata"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_pariwisata)
                }
            }
        }.attach()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}