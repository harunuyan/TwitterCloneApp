package com.volie.twittercloneapp.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.volie.twittercloneapp.view.fragment.LikesProfileFragment
import com.volie.twittercloneapp.view.fragment.MediaProfileFragment
import com.volie.twittercloneapp.view.fragment.TweetsProfileFragment
import com.volie.twittercloneapp.view.fragment.TweetsRepliesProfileFragment

class ProfileViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TweetsProfileFragment()
            1 -> TweetsRepliesProfileFragment()
            2 -> MediaProfileFragment()
            3 -> LikesProfileFragment()
            else -> TweetsProfileFragment()
        }
    }
}