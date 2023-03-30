package com.volie.twittercloneapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.volie.twittercloneapp.databinding.MessageItemBinding
import com.volie.twittercloneapp.model.Message

class MessageAdapter :
    ListAdapter<Message, MessageAdapter.MessageAdapterViewHolder>(MessageItemCallBack()) {
    inner class MessageAdapterViewHolder(private val binding: MessageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageAdapterViewHolder {
        val binding = MessageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessageAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessageAdapterViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}

private class MessageItemCallBack : DiffUtil.ItemCallback<Message>() {
    override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem == newItem
    }

}
