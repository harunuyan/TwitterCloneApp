package com.volie.twittercloneapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.volie.twittercloneapp.databinding.FragmentMessageBinding
import com.volie.twittercloneapp.view.adapter.MessageAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MessageFragment : Fragment() {
    private var _mBinding: FragmentMessageBinding? = null
    private val mBinding get() = _mBinding!!
    private val mViewModel: MessageViewModel by viewModels()
    private val mAdapter by lazy {
        MessageAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentMessageBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.rvMessages.adapter = mAdapter
        mViewModel.getMessage(requireContext())
        observeLiveData()
    }

    private fun observeLiveData() {
        mViewModel.listMessage.observe(viewLifecycleOwner) {
            mAdapter.submitList(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}