package com.volie.twittercloneapp.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.volie.twittercloneapp.view.fragment.AllNotificationsFragment
import com.volie.twittercloneapp.view.fragment.MentionNotificationsFragment
import com.volie.twittercloneapp.view.fragment.VerifiedNotificationsFragment

class NotificationsViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AllNotificationsFragment()
            1 -> VerifiedNotificationsFragment()
            2 -> MentionNotificationsFragment()
            else -> AllNotificationsFragment()
        }
    }
}