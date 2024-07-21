package com.example.demolinkchecker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.demolinkchecker.R
import com.example.demolinkchecker.data.local.LinkModel

class LinkAdapter :
    ListAdapter<LinkModel, LinkAdapter.LinkViewHolder>(ComparatorDiffUtil()) {
    class ComparatorDiffUtil : DiffUtil.ItemCallback<LinkModel>() {
        override fun areItemsTheSame(
            oldItem: LinkModel,
            newItem: LinkModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: LinkModel,
            newItem: LinkModel
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinkViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.website_link_item, parent, false)
        return LinkViewHolder(view)
    }

    override fun onBindViewHolder(holder: LinkViewHolder, position: Int) {
        val website = getItem(position)
        holder.bind(website)
    }

    inner class LinkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(link: LinkModel) {
            itemView.findViewById<TextView>(R.id.websiteName).text = link.name
            itemView.findViewById<TextView>(R.id.websiteStatus).text = if (link.status) "Online" else "Offline"
        }
    }
}
