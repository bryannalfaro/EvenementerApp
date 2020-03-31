package com.example.evenementerapp.ui.home.myEvents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.evenementerapp.R
import com.example.evenementerapp.databinding.FragmentMyEventsBinding

class MyEventsFragment : Fragment() {

    private lateinit var myEventsViewModel: MyEventsViewModel
    private lateinit var binding: FragmentMyEventsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Set Variables
        myEventsViewModel =
            ViewModelProviders.of(this).get(MyEventsViewModel::class.java)
        binding = DataBindingUtil.inflate<FragmentMyEventsBinding>(
            inflater, R.layout.fragment_my_events, container, false)
        binding.model = myEventsViewModel
        // Getting elements
        // Listeners
        // returning
        return binding.root
    }

}
