package com.example.evenementerapp.ui.home.recyclerEvents

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView

class RecyclerEventsAdapter internal constructor(context: Context): RecyclerView.Adapter<RecyclerEventsAdapter.ViewHolderData>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var repos = emptyList<RepoProperty>() // Cached copy of words

    inner class ViewHolderData(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.repoName)
        val description: TextView = itemView.findViewById(R.id.repoDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderData {
        val itemView = inflater.inflate(R.layout.list_repos_recycler, parent, false)
        return ViewHolderData(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderData, position: Int) {
        val current = repos[position]
        holder.name.text = current.name
        holder.description.text = current.description
        holder.itemView.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("${current.htmlUrl}"))
            holder.itemView.context.startActivity(intent)
        }
    }

    internal fun setRepos(repos: List<RepoProperty>) {
        this.repos = repos
        notifyDataSetChanged()
    }

    override fun getItemCount() = repos.size
}