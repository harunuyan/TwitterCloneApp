package com.volie.twittercloneapp.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.volie.twittercloneapp.view.fragment.FollowingHomeFragment
import com.volie.twittercloneapp.view.fragment.ForYouHomeFragment

class HomeViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ForYouHomeFragment()
            1 -> FollowingHomeFragment()
            else -> ForYouHomeFragment()
        }
    }

}