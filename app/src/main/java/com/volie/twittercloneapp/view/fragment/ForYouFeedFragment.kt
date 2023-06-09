package com.volie.twittercloneapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.volie.twittercloneapp.databinding.FragmentForYouBinding
import com.volie.twittercloneapp.view.adapter.FeedAdapter
import com.volie.twittercloneapp.view.fragment.viewmodel.ForYouViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForYouFeedFragment : Fragment() {

    private var _mBinding: FragmentForYouBinding? = null
    private val mBinding get() = _mBinding!!
    private val mViewModel: ForYouViewModel by viewModels()
    private val mAdapter by lazy {
        FeedAdapter {
            val action = FeedFragmentDirections.actionFeedFragmentToPostDetailsFragment(it)
            findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentForYouBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.rvForYou.adapter = mAdapter

        observeTweets()
        mViewModel.getTweets()
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
