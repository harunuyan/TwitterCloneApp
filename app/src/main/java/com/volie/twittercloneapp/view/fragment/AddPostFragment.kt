package com.volie.twittercloneapp.view.fragment

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.volie.twittercloneapp.R
import com.volie.twittercloneapp.databinding.FragmentAddPostBinding
import com.volie.twittercloneapp.model.Tweet
import com.volie.twittercloneapp.model.User
import com.volie.twittercloneapp.view.fragment.viewmodel.AddPostViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class AddPostFragment : Fragment() {
    private var _mBinding: FragmentAddPostBinding? = null
    private val mBinding get() = _mBinding!!
    private val mViewModel: AddPostViewModel by viewModels()
    private val user = FirebaseAuth.getInstance().currentUser
    private val storageRef = Firebase.storage.reference
    var data: Uri? = null
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>
    private val myActivityResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                data = it.data?.data
                mBinding.imgAddMedia.setImageURI(data)
                mBinding.cardAddMedia.visibility = View.VISIBLE
            }
        }

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

        requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
                if (isGranted) {
                    openGallery()
                } else {
                    Toast.makeText(requireContext(), "Depolama izni reddedildi", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        mBinding.btnPost.setOnClickListener {
            val postText = mBinding.etAddPost.text.toString()
            uploadTweet(postText)
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

        mBinding.imgAddMediaGallery.setOnClickListener {
            requestReadExternalStoragePermission()
        }

        Glide.with(requireContext())
            .load(user?.photoUrl)
            .into(mBinding.imgProfile)
    }

    private fun requestReadExternalStoragePermission() {
        // İzin verilmiş mi kontrol edilir
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // İzin verilmemişse izin istenir
            requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        } else {
            // İzin zaten verilmiş, galeriye erişim kodları buraya yazılabilir
            openGallery()
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        myActivityResult.launch(intent)
    }

    private fun uploadTweet(text: String) {
        if (data == null) {
            val user = User(
                id = user!!.uid,
                name = user.displayName!!,
                nickname = "@${user.displayName!!}".lowercase().replace(" ", ""),
                profileImageUrl = user.photoUrl.toString(),
                tweet = Tweet(
                    id = user.uid,
                    text = mBinding.etAddPost.text.toString(),
                    createdAt = System.currentTimeMillis().toString()
                )
            )
            mViewModel.addPostToFirebase(user)
        } else {
            val imageRef = storageRef.child("images/${UUID.randomUUID()}")
            val uploadTask = imageRef.putFile(data!!)
            uploadTask.addOnSuccessListener {
                imageRef.downloadUrl.addOnSuccessListener { uri ->
                    val user = User(
                        id = user!!.uid,
                        name = user.displayName!!,
                        nickname = "@${user.displayName!!}".lowercase().split(" ").joinToString(""),
                        profileImageUrl = user.photoUrl.toString(),
                        tweet = Tweet(
                            id = user.uid,
                            text = text,
                            postImage = uri.toString(),
                            createdAt = System.currentTimeMillis().toString()
                        )
                    )
                    mViewModel.addPostToFirebase(user)
                }
            }.addOnFailureListener {
                Toast.makeText(requireContext(), "Hata: ${it.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mBinding = null
    }
}