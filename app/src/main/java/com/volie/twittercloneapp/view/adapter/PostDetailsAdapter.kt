package com.volie.twittercloneapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.volie.twittercloneapp.databinding.HomeItemBinding
import com.volie.twittercloneapp.model.Home

class PostDetailsAdapter :
    ListAdapter<Home, PostDetailsAdapter.PostDetailsViewHolder>(DetailsItemCallback()) {

    inner class PostDetailsViewHolder(private val binding: HomeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostDetailsViewHolder {
        val binding =
            HomeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostDetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostDetailsViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}

private class DetailsItemCallback : DiffUtil.ItemCallback<Home>() {
    override fun areItemsTheSame(oldItem: Home, newItem: Home): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Home, newItem: Home): Boolean {
        return oldItem == newItem
    }
}
