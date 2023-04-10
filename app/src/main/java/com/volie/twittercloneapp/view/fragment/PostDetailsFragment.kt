package com.volie.twittercloneapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.volie.twittercloneapp.databinding.FragmentPostDetailsBinding
import com.volie.twittercloneapp.view.adapter.PostDetailsAdapter
import com.volie.twittercloneapp.view.fragment.viewmodel.PostDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostDetailsFragment : Fragment() {
    private var _mBinding: FragmentPostDetailsBinding? = null
    private val mBinding get() = _mBinding!!
    private val mViewModel: PostDetailsViewModel by viewModels()
    private lateinit var args: PostDetailsFragmentArgs
    private val mAdapter by lazy {
        PostDetailsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentPostDetailsBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            args = PostDetailsFragmentArgs.fromBundle(it)
        }
        mBinding.rvPostDetailComments.adapter = mAdapter
        showDetails()

        mBinding.ivPostComment.setOnClickListener {
            val action =
                PostDetailsFragmentDirections.actionPostDetailsFragmentToCommentFragment(args.user)
            findNavController().navigate(action)
        }
    }

    private fun showDetails() {
        val user = args.user
        with(mBinding) {
            tvNickname.text = user.nickname
            tvUsername.text = user.name
            tvPostText.text = user.tweet?.text
            tvPostLike.text = user.tweet?.favoriteCount.toString()
            tvPostRetweet.text = user.tweet?.retweetCount.toString()
            tvPostQuote.text = user.tweet?.quoteCount.toString()
            tvPostTimeView.text = mViewModel.formatMillis(user.tweet?.createdAt?.toLong()!!)
            if (user.tweet.postImage != null) {
                Glide.with(root.context)
                    .load(user.tweet.postImage)
                    .into(ivPostPhoto)
                mBinding.cvPostPhoto.visibility = View.VISIBLE
            } else {
                mBinding.cvPostPhoto.visibility = View.GONE
            }
            Glide.with(root.context)
                .load(user.profileImageUrl)
                .into(ivProfilePhoto)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mBinding = null
    }
}