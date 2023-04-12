package com.volie.twittercloneapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.volie.twittercloneapp.databinding.FragmentRequestPermissionBinding

class RequestPermissionFragment : Fragment() {
    private var _mbinding: FragmentRequestPermissionBinding? = null
    private val mBinding get() = _mbinding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mbinding = FragmentRequestPermissionBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mbinding = null
    }
}