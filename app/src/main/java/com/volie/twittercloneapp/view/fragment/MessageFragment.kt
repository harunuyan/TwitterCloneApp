package com.volie.twittercloneapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.volie.twittercloneapp.databinding.FragmentMessageBinding
import com.volie.twittercloneapp.view.MainActivity
import com.volie.twittercloneapp.view.adapter.MessageAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MessageFragment : Fragment() {
    private var _mBinding: FragmentMessageBinding? = null
    private val mBinding get() = _mBinding!!
    private val mAdapter by lazy {
        MessageAdapter()
    }
    private val firebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentMessageBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.rvMessages.adapter = mAdapter

        mBinding.ivProfilePhoto.setOnClickListener {
            val mActivity = activity as MainActivity
            mActivity.mBinding.drawerLayout.open()
        }

        Glide.with(requireContext())
            .load(firebaseAuth.currentUser?.photoUrl)
            .into(mBinding.ivProfilePhoto)
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}