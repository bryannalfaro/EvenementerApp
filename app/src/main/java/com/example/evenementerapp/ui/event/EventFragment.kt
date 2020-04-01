package com.example.evenementerapp.ui.event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.evenementerapp.R
import com.example.evenementerapp.databinding.FragmentEventBinding

class EventFragment : Fragment() {

    private lateinit var eventViewModel: EventViewModel
    private lateinit var binding: FragmentEventBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Set Variables
        eventViewModel =
            ViewModelProviders.of(this).get(EventViewModel::class.java)
        binding = DataBindingUtil.inflate<FragmentEventBinding>(
            inflater, R.layout.fragment_event, container, false)
        binding.model = eventViewModel
        // Listeners
        binding.button2.setOnClickListener {
            it.findNavController().navigate(R.id.action_eventFragment_to_homeFragment)
        }
        // returning
        return binding.root
    }
}
