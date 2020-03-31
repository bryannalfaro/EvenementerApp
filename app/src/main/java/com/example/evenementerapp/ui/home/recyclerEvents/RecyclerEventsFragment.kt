package com.example.evenementerapp.ui.home.recyclerEvents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.evenementerapp.R
import com.example.evenementerapp.databinding.FragmentRecyclerEventsBinding
import kotlinx.android.synthetic.main.fragment_recycler_events.*

class RecyclerEventsFragment: Fragment() {

    companion object {
        private const val ARG_OBJECT = "object"
    }

    private lateinit var recyclerEventsViewModel: RecyclerEventsViewModel
    private lateinit var binding: FragmentRecyclerEventsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Set Variables
        recyclerEventsViewModel =
            ViewModelProviders.of(this).get(RecyclerEventsViewModel::class.java)
        binding = DataBindingUtil.inflate<FragmentRecyclerEventsBinding>(
            inflater, R.layout.fragment_recycler_events, container, false)
        binding.model = recyclerEventsViewModel
        // Getting elements
        // Listeners
        // returning
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            textView.text = "Fragmento " + getInt(ARG_OBJECT).toString()
        }
    }
}