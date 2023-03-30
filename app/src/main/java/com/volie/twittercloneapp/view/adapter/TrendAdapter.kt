package com.volie.twittercloneapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.volie.twittercloneapp.databinding.TrendItemBinding
import com.volie.twittercloneapp.model.Trend

class TrendAdapter : ListAdapter<Trend, TrendAdapter.TrendViewHolder>(TrendItemCallBack()) {

    // Sublist
    fun subList(list: List<Trend>) {
        val newList = list.subList(0, 5)
        super.submitList(newList)
    }


    inner class TrendViewHolder(private val binding: TrendItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendViewHolder {
        val binding = TrendItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrendViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}

private class TrendItemCallBack : DiffUtil.ItemCallback<Trend>() {
    override fun areItemsTheSame(oldItem: Trend, newItem: Trend): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Trend, newItem: Trend): Boolean {
        return oldItem == newItem
    }
}