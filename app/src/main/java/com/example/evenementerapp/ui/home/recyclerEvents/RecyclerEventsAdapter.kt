package com.example.evenementerapp.ui.home.recyclerEvents

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.evenementerapp.R
import com.example.evenementerapp.network.objects.Event

class RecyclerEventsAdapter internal constructor(context: Context): RecyclerView.Adapter<RecyclerEventsAdapter.ViewHolderData>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var events = emptyList<Event>() // Lista vacia de eventos

    inner class ViewHolderData(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val layout: ConstraintLayout = itemView.findViewById(R.id.layoutCardEvent)
        val title: TextView = itemView.findViewById(R.id.prueba)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderData {
        val itemView = inflater.inflate(R.layout.list_recycler_events, parent, false)
        return ViewHolderData(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderData, position: Int) {
        val current = events[position]
        holder.title.text = current.title
        holder.layout.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_eventFragment)
        }
    }

    internal fun setEvents(events: List<Event>) {
        this.events = events
        notifyDataSetChanged()
    }

    override fun getItemCount() = events.size
}