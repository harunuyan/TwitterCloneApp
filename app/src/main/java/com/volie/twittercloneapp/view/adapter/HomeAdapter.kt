package com.volie.twittercloneapp.view.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.volie.twittercloneapp.R
import com.volie.twittercloneapp.databinding.HomeItemBinding
import com.volie.twittercloneapp.model.User
import java.text.SimpleDateFormat
import java.util.*

class HomeAdapter(
    val onItemClick: (user: User) -> Unit
) : ListAdapter<User, HomeAdapter.HomeViewHolder>(
    HomeItemCallback()
) {
    inner class HomeViewHolder(private val binding: HomeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val user = currentList[position]
            with(binding) {
                tvNickname.text = user.nickname
                tvUsername.text = user.name
                tvPostText.text = user.tweet?.text
                tvPostTime.text = timeDifferenceInMinutes(user.tweet?.createdAt!!.toLong())
                tvPostRetweet.text = user.tweet.retweetCount.toString()
                tvPostLike.text = user.tweet.favoriteCount.toString()
                Glide.with(root.context)
                    .load(user.profileImageUrl)
                    .into(ivProfilePhoto)
                if (user.tweet.favorited) {
                    ivPostLike.setImageResource(R.drawable.ic_liked)
                } else {
                    ivPostLike.setImageResource(R.drawable.ic_like)
                }
                if (user.tweet.retweeted) {
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

    fun timeDifferenceInMinutes(givenTime: Long): String {
        val systemTime = System.currentTimeMillis()
        val difference = systemTime - givenTime
        return when {
            difference < 60 * 1000 -> "now" // up to 1 minute
            difference < 60 * 60 * 1000 -> "${difference / (60 * 1000)}m" // up to 60 minutes
            difference < 24 * 60 * 60 * 1000 -> "${difference / (60 * 60 * 1000)}h" // up to 24 hours
            difference < 7 * 24 * 60 * 60 * 1000 -> "${difference / (24 * 60 * 60 * 1000)}d" // up to 7 days
            else -> SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(Date(givenTime))
        }
    }
}

private class HomeItemCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

}