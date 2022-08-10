package com.example.pgm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ViewTraineesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_trainees)
        val viewPager = findViewById<ViewPager2>(R.id.viewpagert)
        val tabLayout = findViewById<TabLayout>(R.id.tabst)
        viewPager.adapter = ViewPagerAdapterT(supportFragmentManager, lifecycle)
        val tabTitles = arrayOf("Normal", "Private")
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }
}