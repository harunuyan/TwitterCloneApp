package com.volie.twittercloneapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth
import com.volie.twittercloneapp.R
import com.volie.twittercloneapp.databinding.FragmentAddPostBinding
import com.volie.twittercloneapp.model.Tweet
import com.volie.twittercloneapp.model.User
import com.volie.twittercloneapp.view.fragment.viewmodel.AddPostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddPostFragment : Fragment() {
    private var _mBinding: FragmentAddPostBinding? = null
    private val mBinding get() = _mBinding!!
    private val mViewModel: AddPostViewModel by viewModels()
    private val user = FirebaseAuth.getInstance().currentUser

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentAddPostBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.btnPost.setOnClickListener {
            uploadTweet()
            val action = AddPostFragmentDirections.actionAddPostFragmentToHomeFragment()
            findNavController().navigate(action)
        }

        mBinding.ivBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        mBinding.btnPublicCircle.setOnClickListener {
            val bottomSheetDialog =
                BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)
            val bottomSheetView = LayoutInflater.from(requireContext())
                .inflate(R.layout.layout_bottom_sheet_choose_audience, mBinding.root, false)
            bottomSheetDialog.setContentView(bottomSheetView)
            bottomSheetDialog.show()
        }

        Glide.with(requireContext())
            .load(user?.photoUrl)
            .into(mBinding.imgProfile)
    }

    private fun uploadTweet() {
        val tweet = Tweet(
            id = user!!.uid,
            text = mBinding.etAddPost.text.toString(),
            createdAt = System.currentTimeMillis().toString(),
            user = User(
                id = user.uid,
                name = user.displayName!!,
                nickname = "@${user.displayName!!}".lowercase().split(" ").joinToString(""),
                profileImageUrl = user.photoUrl.toString()
            )
        )
        mViewModel.addPostToFirebase(tweet)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mBinding = null
    }
}