package com.volie.twittercloneapp.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.volie.twittercloneapp.view.fragment.FollowingFeedFragment
import com.volie.twittercloneapp.view.fragment.ForYouFeedFragment

class FeedViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ForYouFeedFragment()
            1 -> FollowingFeedFragment()
            else -> ForYouFeedFragment()
        }
    }

}