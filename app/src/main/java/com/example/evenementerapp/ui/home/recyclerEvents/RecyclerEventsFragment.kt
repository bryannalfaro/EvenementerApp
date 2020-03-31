package com.example.evenementerapp.ui.home.recyclerEvents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.evenementerapp.R
import com.example.evenementerapp.databinding.FragmentRecyclerEventsBinding
import com.example.evenementerapp.network.objects.Event
import com.example.evenementerapp.ui.home.HomeViewPagerAdapter

class RecyclerEventsFragment: Fragment() {

    companion object {
        private const val ARG_OBJECT = "object"
    }

    private lateinit var recyclerEventsViewModel: RecyclerEventsViewModel
    private lateinit var binding: FragmentRecyclerEventsBinding

    private lateinit var recycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Set Variables
        recyclerEventsViewModel =
            ViewModelProviders.of(this).get(RecyclerEventsViewModel::class.java)
        binding = DataBindingUtil.inflate<FragmentRecyclerEventsBinding>(
            inflater, R.layout.fragment_recycler_events, container, false)
        // Getting elements
        binding.model = recyclerEventsViewModel
        recycler = binding.recyclerEvents
        var adapter = RecyclerEventsAdapter(context!!)
        // Setting elements
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(context)
        // Listeners
        var events = ArrayList<Event>()
        events.add(Event("nuevo"))
        events.add(Event("probando"))
        events.add(Event("otro"))
        adapter.setEvents(events)
        // returning
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            //prueba.text = "Fragmento " + getInt(ARG_OBJECT).toString()
        }
    }
}