package com.volie.twittercloneapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.volie.twittercloneapp.R
import com.volie.twittercloneapp.databinding.FragmentFeedBinding
import com.volie.twittercloneapp.view.MainActivity
import com.volie.twittercloneapp.view.adapter.FeedViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedFragment : Fragment() {
    private var _mBinding: FragmentFeedBinding? = null
    private val mBinding get() = _mBinding!!
    private val firebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().window.statusBarColor =
            ContextCompat.getColor(requireContext(), R.color.grayS)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentFeedBinding.inflate(inflater, container, false)
        setupViewPager()
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.fabFeed.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToAddPostFragment()
            findNavController().navigate(action)
        }

        Glide.with(requireContext())
            .load(firebaseAuth.currentUser?.photoUrl)
            .into(mBinding.ivProfilePhoto)

        mBinding.ivProfilePhoto.setOnClickListener {
            val mActivity = requireActivity() as MainActivity
            mActivity.mBinding.drawerLayout.open()
        }
    }

    private fun setupViewPager() {
        mBinding.viewPagerFeed.adapter = FeedViewPagerAdapter(requireActivity())
        TabLayoutMediator(mBinding.tabLayoutFeed, mBinding.viewPagerFeed) { tab, position ->
            when (position) {
                0 -> tab.text = "For you"
                1 -> tab.text = "Following"
            }
        }.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}