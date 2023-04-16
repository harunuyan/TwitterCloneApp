package com.volie.twittercloneapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.volie.twittercloneapp.databinding.FragmentProfileBinding
import com.volie.twittercloneapp.util.DateUtils
import com.volie.twittercloneapp.view.adapter.ProfileViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private var _mBinding: FragmentProfileBinding? = null
    private val mBinding get() = _mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentProfileBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            with(mBinding) {
                Glide.with(requireContext())
                    .load(user.photoUrl)
                    .into(ivProfile)
                tvUsernameProfile.text = user.displayName
                tvNicknameProfile.text = "@${
                    user
                        .displayName
                        ?.lowercase()
                        ?.replace(" ", "")
                }"

                tvJoinedProfile.text =
                    "Joined ${DateUtils.getJoinedDate(System.currentTimeMillis())}"
            }
        }
        mBinding.fabTweetProfile.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToAddPostFragment()
            findNavController().navigate(action)
        }
        setupViewPager()

        mBinding.ivProfileHeader.setOnClickListener {
            val action =
                ProfileFragmentDirections.actionProfileFragmentToProfileHeaderImageFragment()
            findNavController().navigate(action)
        }

        mBinding.ivProfile.setOnClickListener {
            val action =
                ProfileFragmentDirections.actionProfileFragmentToProfileImageFragment()
            findNavController().navigate(action)
        }
    }

    private fun setupViewPager() {
        mBinding.vpProfile.adapter = ProfileViewPagerAdapter(requireActivity())
        TabLayoutMediator(mBinding.tabLayoutProfile, mBinding.vpProfile) { tab, position ->
            when (position) {
                0 -> tab.text = "Tweets"
                1 -> tab.text = "Tweets & replies"
                2 -> tab.text = "Media"
                3 -> tab.text = "Likes"
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mBinding = null
    }
}