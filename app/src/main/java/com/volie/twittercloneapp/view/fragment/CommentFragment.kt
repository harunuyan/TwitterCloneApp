package com.volie.twittercloneapp.view.fragment

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.volie.twittercloneapp.R
import com.volie.twittercloneapp.databinding.FragmentCommentBinding
import com.volie.twittercloneapp.view.fragment.viewmodel.CommentViewModel

class CommentFragment : Fragment() {
    private var _mBinding: FragmentCommentBinding? = null
    private val mBinding get() = _mBinding!!
    private val mViewModel: CommentViewModel by viewModels()
    private lateinit var args: CommentFragmentArgs
    private val firebaseAuth = FirebaseAuth.getInstance()
    var data: Uri? = null
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>
    private val myActivityResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                data = it.data?.data
                with(mBinding) {
                    imgAddMediaComment.setImageURI(data)
                    cardAddMediaComment.visibility = View.VISIBLE
                }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentCommentBinding.inflate(inflater, container, false)
        with(mBinding) {
        }
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments.let {
            args = CommentFragmentArgs.fromBundle(it!!)
        }

        getTweet()

        mBinding.ivBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        mBinding.imgAddImage.setOnClickListener {
            requestReadExternalStoragePermission()
        }
    }

    private fun getTweet() {
        val user = firebaseAuth.currentUser
        val tweet = args.comment.tweet
        with(mBinding) {
            if (tweet?.postImage != null) {
                Glide.with(requireContext()).load(tweet.postImage)
                    .into(imgAddMediaCmd)
                cardAddMedia.visibility = View.VISIBLE
            }
            tvNicknameComment.text = args.comment.nickname
            tvUsernameComment.text = args.comment.name
            if (tweet?.text != null) {
                tvPostText.text = tweet.text
                tvPostText.visibility = View.VISIBLE
            }

            tvReplyingToComment.text = SpannableStringBuilder()
                .append("Replying to ")
                .append(
                    args.comment.nickname,
                    ForegroundColorSpan(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.twitter_blue
                        )
                    ),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            Glide.with(requireContext()).load(args.comment.profileImageUrl)
                .into(imgProfileTweet)
            Glide.with(requireContext()).load(user!!.photoUrl).into(imgProfileComment)
        }
    }

    private fun requestReadExternalStoragePermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        } else {
            openGallery()
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        myActivityResult.launch(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mBinding = null
    }
}