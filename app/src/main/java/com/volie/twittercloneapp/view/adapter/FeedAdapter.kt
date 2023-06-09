package com.volie.twittercloneapp.view.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.volie.twittercloneapp.R
import com.volie.twittercloneapp.databinding.FeedItemBinding
import com.volie.twittercloneapp.model.User
import java.text.SimpleDateFormat
import java.util.*

class FeedAdapter(
    val onItemClick: (user: User) -> Unit
) : ListAdapter<User, FeedAdapter.FeedViewHolder>(
    FeedItemCallback()
) {
    inner class FeedViewHolder(private val binding: FeedItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val user = currentList[position]
            with(binding) {
                tvUsername.text = user.nickname
                tvFullName.text = user.name
                tvPostText.text = user.tweet?.text
                tvPostTime.text = timeDifferenceInMinutes(user.tweet?.createdAt!!.toLong())
                tvPostRetweet.text = user.tweet.retweetCount.toString()
                tvPostLike.text = user.tweet.favoriteCount.toString()
                if (ivPostPhoto != null) {
                    Glide.with(root.context)
                        .load(user.tweet.postImage)
                        .into(ivPostPhoto)
                    cvPostPhoto.visibility = View.VISIBLE
                }
                Glide.with(root.context)
                    .load(user.profileImageUrl)
                    .into(ivProfilePhoto)
                ivPostLike.setOnClickListener {
                    user.tweet.favorited = !user.tweet.favorited
                    if (user.tweet.favorited) {
                        ivPostLike.setImageResource(R.drawable.ic_liked)
                        tvPostLike.text = (tvPostLike.text.toString().toInt() + 1).toString()
                    } else {
                        ivPostLike.setImageResource(R.drawable.ic_like)
                        tvPostLike.text = (tvPostLike.text.toString().toInt() - 1).toString()
                    }
                }
                ivPostRetweet.setOnClickListener {
                    if (user.tweet.retweeted) {
                        ivPostRetweet.setImageResource(R.drawable.ic_retweeted)
                    } else {
                        ivPostRetweet.setImageResource(R.drawable.ic_retweet)
                    }
                }
                root.setOnClickListener {
                    onItemClick(user)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val binding = FeedItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FeedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
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

private class FeedItemCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

}