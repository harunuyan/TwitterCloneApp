package com.volie.twittercloneapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.volie.twittercloneapp.R
import com.volie.twittercloneapp.databinding.FragmentPostDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostDetailsFragment : Fragment() {
    private var _mBinding: FragmentPostDetailsBinding? = null
    private val mBinding get() = _mBinding!!
    private lateinit var mArgs: PostDetailsFragmentArgs

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
            mArgs = PostDetailsFragmentArgs.fromBundle(it)
        }
        setDetails()
    }

    private fun setDetails() {
        val args = mArgs.home
        if (args != null) {
            with(mBinding) {
                ivPostPhoto.load(args.postImage)
                ivProfilePhoto.load(args.profileImage)
                if (args.isLiked) {
                    ivPostLike.setImageResource(R.drawable.ic_liked)
                } else {
                    ivPostLike.setImageResource(R.drawable.ic_like)
                }
                tvUsername.text = args.username
                tvNickname.text = args.nickname
                tvPostQuote.text = args.quote
                tvPostText.text = args.postText
                tvPostTimeView.text = args.time
            }
            return
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mBinding = null
    }
}