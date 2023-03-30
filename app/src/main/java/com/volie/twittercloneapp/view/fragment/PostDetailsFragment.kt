package com.volie.twittercloneapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.volie.twittercloneapp.databinding.FragmentPostDetailsBinding
import com.volie.twittercloneapp.view.adapter.PostDetailsAdapter
import com.volie.twittercloneapp.view.fragment.viewmodel.PostDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostDetailsFragment : Fragment() {
    private var _mBinding: FragmentPostDetailsBinding? = null
    private val mBinding get() = _mBinding!!
    private lateinit var mArgs: PostDetailsFragmentArgs
    private val mViewModel: PostDetailsViewModel by viewModels()
    private val mAdapter by lazy {
        PostDetailsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentPostDetailsBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            mArgs = PostDetailsFragmentArgs.fromBundle(it)
        }
        mBinding.rvPostDetailComments.adapter = mAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mBinding = null
    }
}