package com.example.evenementerapp.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.evenementerapp.R
import com.example.evenementerapp.databinding.FragmentHomeBinding
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private val adapter by lazy { HomeViewPagerAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Set Variables
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater, R.layout.fragment_home, container, false)
        binding.model = homeViewModel
        // Setting adapter to pager
        binding.viewPager.adapter = adapter
        // Setting pager to tabs
        val tabLayoutMediator = TabLayoutMediator(binding.tabLayout, binding.viewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position) {
                    0 -> {
                        tab.text = "PUBLICS"
                        val badge: BadgeDrawable = tab.orCreateBadge
                        badge.backgroundColor = Color.RED
                        badge.number = 1
                        badge.isVisible = true
                    }
                    1 -> {
                        tab.text = "MY EVENTS"
                    }
                    2 -> {
                        tab.text = "ORGANIZED"
                    }
                }
            })
        tabLayoutMediator.attach()
        // Listeners
        // returning
        return binding.root
    }

}
