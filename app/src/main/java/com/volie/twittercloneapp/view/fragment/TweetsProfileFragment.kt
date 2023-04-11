package com.volie.twittercloneapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.volie.twittercloneapp.databinding.FragmentTweetsProfileBinding
import com.volie.twittercloneapp.view.adapter.FeedAdapter
import com.volie.twittercloneapp.view.fragment.viewmodel.TweetsProfileViewModel

class TweetsProfileFragment : Fragment() {
    private var _mBinding: FragmentTweetsProfileBinding? = null
    private val mBinding get() = _mBinding!!
    private val mViewModel: TweetsProfileViewModel by viewModels()
    private val mAdapter by lazy {
        FeedAdapter {
            val action = ProfileFragmentDirections.actionProfileFragmentToPostDetailsFragment(it)
            findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentTweetsProfileBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.rvTweetsProfile.adapter = mAdapter
        mViewModel.getTweets()
        observeTweets()
    }

    private fun observeTweets() {
        mViewModel.tweets.observe(viewLifecycleOwner) {
            mAdapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mBinding = null
    }
}
