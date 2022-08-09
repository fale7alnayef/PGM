package com.example.pgm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ViewSubscriptionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_subscriptions)
        val viewPager = findViewById<ViewPager2>(R.id.viewpagers)
        val tabLayout = findViewById<TabLayout>(R.id.tabss)
        viewPager.adapter = ViewPagerAdapterS(supportFragmentManager, lifecycle)
        val tabTitles = arrayOf("Active Subscription", "Inactive Subscription")
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }


}


