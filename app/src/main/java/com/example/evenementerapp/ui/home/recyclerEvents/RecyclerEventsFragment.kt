package com.example.evenementerapp.ui.home.recyclerEvents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.evenementerapp.R
import com.example.evenementerapp.databinding.FragmentRecyclerEventsBinding
import com.example.evenementerapp.network.objects.Event
import kotlinx.android.synthetic.main.fragment_recycler_events.*

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
        recyclerEventsViewModel.events.observe(this.viewLifecycleOwner, Observer {
            Toast.makeText(context, it.get(0).title, Toast.LENGTH_SHORT).show()
            adapter.setEvents(it)
        })
        binding.fabCreateEvent.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_createEventFragment)
        }
        // returning
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            recyclerEventsViewModel.setFragmentNumber(getInt(ARG_OBJECT))
            if (getInt(ARG_OBJECT) == 3) {
                fabCreateEvent.visibility = View.VISIBLE
            } else {
                fabCreateEvent.visibility = View.GONE
            }
        }
    }
}