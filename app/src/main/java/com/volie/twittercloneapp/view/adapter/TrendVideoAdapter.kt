package com.volie.twittercloneapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.volie.twittercloneapp.databinding.TrendVideoItemBinding
import com.volie.twittercloneapp.model.TrendVideo

class TrendVideoAdapter :
    ListAdapter<TrendVideo, TrendVideoAdapter.TrendVideoAdapterViewHolder>(
        TrendVideoItemCallBack()
    ) {

    inner class TrendVideoAdapterViewHolder(private val binding: TrendVideoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrendVideoAdapter.TrendVideoAdapterViewHolder {
        val binding =
            TrendVideoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrendVideoAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: TrendVideoAdapter.TrendVideoAdapterViewHolder,
        position: Int
    ) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

}

private class TrendVideoItemCallBack : DiffUtil.ItemCallback<TrendVideo>() {
    override fun areItemsTheSame(oldItem: TrendVideo, newItem: TrendVideo): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: TrendVideo, newItem: TrendVideo): Boolean {
        return oldItem == newItem
    }
}