package com.volie.twittercloneapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.volie.twittercloneapp.databinding.FragmentLogInBinding

class LogInFragment : Fragment() {
    private var _mBinding: FragmentLogInBinding? = null
    private val mBinding get() = _mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentLogInBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.ivBack.setOnClickListener {
            val action = LogInFragmentDirections.actionLogInFragmentToAccountFragment()
            findNavController().navigate(action)
        }

        mBinding.btnNext.setOnClickListener {
            with(mBinding) {
                btnNext.hint = "Log In"
                outlinedTextFieldPassword.visibility = View.VISIBLE
            }
        }


        mBinding.btnForgotPassword.setOnClickListener {
            with(mBinding) {
                tvTitle.text = "Find your Twitter account"
                tvFindAccount.visibility = View.VISIBLE
                outlinedTextFieldPassword.visibility = View.GONE
                btnForgotPassword.visibility = View.GONE
                btnNext.visibility = View.GONE
                btnNextFindAccount.visibility = View.VISIBLE
            }
        }

        mBinding.btnNextFindAccount.setOnClickListener {
            with(mBinding) {
                tvTitle.text = "Where should we send a confirmation code?"
                tvFindAccount.visibility = View.VISIBLE
                tvFindAccount.text =
                    "Before you can change your password, we need to make sure it's really you."
                radioGroup.visibility = View.VISIBLE
                outlinedTextFieldPassword.visibility = View.GONE
                btnForgotPassword.visibility = View.GONE
                btnCancel.visibility = View.VISIBLE
                btnNext.visibility = View.GONE
                outlinedTextFieldName.visibility = View.GONE
                tvFindAccountTitle.visibility = View.VISIBLE
                tvContactSupport.visibility = View.VISIBLE
            }
        }

        mBinding.btnCancel.setOnClickListener {
            with(mBinding) {
                tvTitle.text = "Log in to Twitter"
                tvFindAccount.visibility = View.GONE
                outlinedTextFieldPassword.visibility = View.GONE
                btnNext.visibility = View.VISIBLE
                radioGroup.visibility = View.GONE
                btnNextFindAccount.visibility = View.GONE
                btnCancel.visibility = View.GONE
                outlinedTextFieldName.visibility = View.VISIBLE
                tvFindAccountTitle.visibility = View.GONE
                tvContactSupport.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mBinding = null
    }
}