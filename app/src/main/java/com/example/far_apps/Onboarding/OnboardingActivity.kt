package com.example.far_apps.Onboarding

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.far_apps.databinding.ActivityOnboardingBinding
import com.example.far_apps.Onboarding.Onboarding1Fragment
import com.example.far_apps.Onboarding.Onboarding2Fragment
import com.example.far_apps.Onboarding.Onboarding3Fragment
import com.example.far_apps.Onboarding.OnboardingFragmentAdapter
import com.example.far_apps.AuthActivity
class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup ViewPager dengan adapter
        val fragmentsList = listOf(
            Onboarding1Fragment(),
            Onboarding2Fragment(),
            Onboarding3Fragment()
        )
        val adapter = OnboardingFragmentAdapter(this, fragmentsList)
        binding.onboardingViewPager.adapter = adapter

        // Hubungkan dotIndicator dengan ViewPager
        binding.dotIndicator.attachTo(binding.onboardingViewPager)

        // Tampilkan tombol Ayo Mulai di slide terakhir
        binding.onboardingViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == 2) {
                    binding.btnAyoMulai.visibility = View.VISIBLE
                } else {
                    binding.btnAyoMulai.visibility = View.GONE
                }
            }
        })
        binding.btnAyoMulai.setOnClickListener {
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        }
    }
}