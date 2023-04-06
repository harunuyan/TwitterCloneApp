package com.volie.twittercloneapp.view.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.volie.twittercloneapp.R
import com.volie.twittercloneapp.databinding.HomeItemBinding
import com.volie.twittercloneapp.model.Tweet

class HomeAdapter(
    val onItemClick: (tweet: Tweet) -> Unit
) : ListAdapter<Tweet, HomeAdapter.HomeViewHolder>(
    HomeItemCallback()
) {
    inner class HomeViewHolder(private val binding: HomeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val tweet = currentList[position]
            with(binding) {
                tvNickname.text = tweet.user?.nickname
                tvUsername.text = tweet.user?.name
                tvPostText.text = tweet.text
                tvPostTime.text = tweet.createdAt
                tvPostRetweet.text = tweet.retweetCount.toString()
                tvPostLike.text = tweet.favoriteCount.toString()
                Glide.with(root.context)
                    .load(tweet.user?.profileImageUrl)
                    .into(ivProfilePhoto)
                if (tweet.favorited) {
                    ivPostLike.setImageResource(R.drawable.ic_liked)
                } else {
                    ivPostLike.setImageResource(R.drawable.ic_like)
                }
                if (tweet.retweeted) {
                    ivPostRetweet.setImageResource(R.drawable.ic_retweeted)
                } else {
                    ivPostRetweet.setImageResource(R.drawable.ic_retweet)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = HomeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

}

private class HomeItemCallback : DiffUtil.ItemCallback<Tweet>() {
    override fun areItemsTheSame(oldItem: Tweet, newItem: Tweet): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Tweet, newItem: Tweet): Boolean {
        return oldItem == newItem
    }

}