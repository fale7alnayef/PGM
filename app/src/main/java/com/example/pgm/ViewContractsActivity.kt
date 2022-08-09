package com.example.pgm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ViewContractsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_contracts)
        val viewPager = findViewById<ViewPager2>(R.id.viewpagerc)
        val tabLayout = findViewById<TabLayout>(R.id.tabsc)
        viewPager.adapter = ViewPagerAdapterC(supportFragmentManager, lifecycle)
        val tabTitles = arrayOf("Active Contracts", "Inactive Contracts")
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

    }
}