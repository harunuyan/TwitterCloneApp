package com.volie.twittercloneapp.view.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.volie.twittercloneapp.R
import com.volie.twittercloneapp.databinding.HomeItemBinding
import com.volie.twittercloneapp.model.Home

class HomeAdapter(
    val onItemClick: (home: Home) -> Unit
) : ListAdapter<Home, HomeAdapter.HomeViewHolder>(
    BaseItemCallback()
) {
    inner class HomeViewHolder(private val binding: HomeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val item = currentList[position]
            with(binding) {
                if (item.postImage == null) {
                    ivPostPhoto.visibility = View.GONE
                } else {
                    ivPostPhoto.visibility = View.VISIBLE
                    Glide.with(root.context)
                        .load(item.postImage)
                        .into(ivPostPhoto)
                }
                Glide.with(root.context)
                    .load(item.profileImage)
                    .into(ivProfilePhoto)
                tvUsername.text = item.username
                tvNickname.text = item.nickname
                tvPostText.text = item.postText
                tvPostTime.text = item.time
                tvPostRetweet.text = item.retweet
                tvPostLike.text = item.like.toString()
                tvPostComment.text = item.comment
                tvPostViews.text = item.view
                root.setOnClickListener {
                    onItemClick(item)
                }
                ivPostLike.setOnClickListener {
                    if (item.isLiked) {
                        tvPostLike.text = (item.like - 1).toString()
                        ivPostLike.setImageResource(R.drawable.ic_like)
                        currentList[position].isLiked = false
                    } else {
                        tvPostLike.text = (item.like + 1).toString()
                        ivPostLike.setImageResource(R.drawable.ic_liked)
                        currentList[position].isLiked = true
                    }
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

class BaseItemCallback : DiffUtil.ItemCallback<Home>() {
    override fun areItemsTheSame(oldItem: Home, newItem: Home): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Home, newItem: Home): Boolean {
        return oldItem == newItem
    }

}