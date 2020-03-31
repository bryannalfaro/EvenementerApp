package com.example.evenementerapp.ui.home.organized

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.evenementerapp.R
import com.example.evenementerapp.databinding.FragmentOrganizedBinding

class OrganizedFragment: Fragment() {

    private lateinit var organizedViewModel: OrganizedViewModel
    private lateinit var binding: FragmentOrganizedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Set Variables
        organizedViewModel =
            ViewModelProviders.of(this).get(OrganizedViewModel::class.java)
        binding = DataBindingUtil.inflate<FragmentOrganizedBinding>(
            inflater, R.layout.fragment_organized, container, false)
        binding.model = organizedViewModel
        // Getting elements
        // Listeners
        // returning
        return binding.root
    }
}