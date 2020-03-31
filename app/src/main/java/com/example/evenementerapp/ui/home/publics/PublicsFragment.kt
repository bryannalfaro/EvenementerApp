package com.example.evenementerapp.ui.home.publics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.evenementerapp.R
import com.example.evenementerapp.databinding.FragmentPublicsBinding

class PublicsFragment: Fragment() {

    private lateinit var publicsViewModel: PublicsViewModel
    private lateinit var binding: FragmentPublicsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Set Variables
        publicsViewModel =
            ViewModelProviders.of(this).get(PublicsViewModel::class.java)
        binding = DataBindingUtil.inflate<FragmentPublicsBinding>(
            inflater, R.layout.fragment_publics, container, false)
        binding.model = publicsViewModel
        // Getting elements
        // Listeners
        // returning
        return binding.root
    }
}