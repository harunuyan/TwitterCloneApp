package com.volie.twittercloneapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.volie.twittercloneapp.R
import com.volie.twittercloneapp.databinding.FeedItemBinding
import com.volie.twittercloneapp.model.User

class PostDetailsAdapter :
    ListAdapter<User, PostDetailsAdapter.PostDetailsViewHolder>(DetailsItemCallback()) {

    inner class PostDetailsViewHolder(private val binding: FeedItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val item = currentList[position]
            with(binding) {
                tvPostText.text = item.tweet?.text
                tvPostLike.text = item.tweet?.favoriteCount.toString()
                tvPostRetweet.text = item.tweet?.retweetCount.toString()
                tvFullName.text = item.name
                tvUsername.text = item.nickname
                tvPostComment.text = item.tweet?.replyTo?.text
                tvPostViews.text = item.tweet?.viewCount.toString()
                tvPostTime.text = item.tweet?.createdAt
                Glide.with(root.context)
                    .load(item.profileImageUrl)
                    .into(ivProfilePhoto)
                Glide.with(root.context)
                    .load(item.tweet?.postImage)
                    .into(ivPostPhoto)
                ivPostLike.setOnClickListener {
                    if (item.tweet!!.favorited) {
                        ivPostLike.setImageResource(R.drawable.ic_liked)
                        tvPostLike.text = (tvPostLike.text.toString().toInt() + 1).toString()
                    } else {
                        ivPostLike.setImageResource(R.drawable.ic_like)
                        tvPostLike.text = (tvPostLike.text.toString().toInt() - 1).toString()
                    }
                }
                ivPostRetweet.setOnClickListener {
                    if (item.tweet!!.retweeted) {
                        ivPostRetweet.setImageResource(R.drawable.ic_retweeted)
                    } else {
                        ivPostRetweet.setImageResource(R.drawable.ic_retweet)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostDetailsViewHolder {
        val binding =
            FeedItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostDetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostDetailsViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}

private class DetailsItemCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}
