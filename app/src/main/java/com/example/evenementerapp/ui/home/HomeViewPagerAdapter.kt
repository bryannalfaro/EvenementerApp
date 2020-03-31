package com.example.evenementerapp.ui.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.evenementerapp.ui.home.recyclerEvents.RecyclerEventsFragment

class HomeViewPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {

    companion object {
        private const val ARG_OBJECT = "object"
    }

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        val fragment = RecyclerEventsFragment()
        fragment.arguments = Bundle().apply {
            putInt(ARG_OBJECT, position + 1)
        }
        return fragment
    }
}