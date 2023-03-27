package com.volie.twittercloneapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.volie.twittercloneapp.databinding.FragmentNotificationBinding
import com.volie.twittercloneapp.view.adapter.NotificationsViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationFragment : Fragment() {
    private var _mBinding: FragmentNotificationBinding? = null
    private val mBinding get() = _mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentNotificationBinding.inflate(inflater, container, false)
        setupViewPager()
        return mBinding.root
    }

    private fun setupViewPager() {
        mBinding.viewPagerNotifications.adapter = NotificationsViewPagerAdapter(requireActivity())
        TabLayoutMediator(
            mBinding.tabLayoutNotifications,
            mBinding.viewPagerNotifications
        ) { tab, position ->
            when (position) {
                0 -> tab.text = "All"
                1 -> tab.text = "Verified"
                2 -> tab.text = "Mentions"
            }
        }.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}