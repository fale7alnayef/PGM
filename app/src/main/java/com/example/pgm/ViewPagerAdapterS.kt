package com.example.pgm

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter


  class ViewPagerAdapterS(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {


    override fun getItemCount(): Int {
        return 2
    }

      override fun createFragment(position: Int): Fragment {
          var fragment: Fragment? = null
          when(position)
          {
              0 -> {fragment = VSFragment()}
              1 -> {fragment = IVSFragment()}

          }

          return fragment!!
      }


  }