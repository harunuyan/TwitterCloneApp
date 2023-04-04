package com.volie.twittercloneapp.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.volie.twittercloneapp.R
import com.volie.twittercloneapp.databinding.FragmentTrendsBinding
import com.volie.twittercloneapp.view.MainActivity
import com.volie.twittercloneapp.view.adapter.TrendAdapter
import com.volie.twittercloneapp.view.adapter.TrendVideoAdapter
import com.volie.twittercloneapp.view.fragment.viewmodel.TrendsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrendsFragment : Fragment() {
    private var _mBinding: FragmentTrendsBinding? = null
    private val mBinding get() = _mBinding!!
    private val mViewModel: TrendsViewModel by viewModels()
    private val mAdapterVideos by lazy {
        TrendVideoAdapter()
    }
    private val mAdapterTrends by lazy {
        TrendAdapter()
    }
    private val firebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private var onBack = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentTrendsBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.fabTrends.setOnClickListener {
            val action = TrendsFragmentDirections.actionTrendsFragmentToAddPostFragment()
            findNavController().navigate(action)
        }

        mBinding.rvTrendsForYou.adapter = mAdapterTrends
        mBinding.rvVideosForYou.adapter = mAdapterVideos

        Glide.with(requireContext())
            .load(firebaseAuth.currentUser?.photoUrl)
            .into(mBinding.ivProfile)

        mBinding.ivProfile.setOnClickListener {
            val mActivity = requireActivity() as MainActivity
            mActivity.mBinding.drawerLayout.open()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (onBack) {
                    onBack = false
                    showView()
                } else {
                    isEnabled = false
                    findNavController().navigateUp()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun showView() {
        with(mBinding) {
            tvShowMore.visibility = View.VISIBLE
            rvVideosForYou.visibility = View.VISIBLE
            tvTrendsForYou.visibility = View.VISIBLE
            tvVideosForYou.visibility = View.VISIBLE
            tvVideosForYouSubtext.visibility = View.VISIBLE
            ivProfile.visibility = View.VISIBLE
            divider2.visibility = View.VISIBLE
            divider.visibility = View.VISIBLE
            tvSearch.visibility = View.VISIBLE
            ivSettings.visibility = View.VISIBLE
            fabTrends.setImageResource(R.drawable.ic_tweet)
        }
        with((activity as MainActivity)) {
            mBinding.toolbar.visibility = View.GONE
            mBinding.bottomNavigationView.visibility = View.VISIBLE
        }
    }

    private fun hideView() {
        with(mBinding) {
            tvShowMore.visibility = View.GONE
            rvVideosForYou.visibility = View.GONE
            tvTrendsForYou.visibility = View.GONE
            tvVideosForYou.visibility = View.GONE
            tvVideosForYouSubtext.visibility = View.GONE
            ivProfile.visibility = View.GONE
            divider2.visibility = View.GONE
            divider.visibility = View.GONE
            tvSearch.visibility = View.GONE
            ivSettings.visibility = View.GONE
            fabTrends.setImageResource(R.drawable.ic_trend)
        }
        with((activity as MainActivity)) {
            mBinding.toolbar.visibility = View.VISIBLE
            mBinding.bottomNavigationView.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}