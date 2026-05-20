package com.example.far_apps.Home.pertemuan_10

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TenthTabsAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DesaFragment()
            1 -> HomestayFragment()
            2 -> PariwisataFragment()
            else -> throw IllegalStateException("Posisi tidak valid")
        }
    }
}