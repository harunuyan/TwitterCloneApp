package com.volie.twittercloneapp.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.volie.twittercloneapp.R
import com.volie.twittercloneapp.databinding.FragmentAccountBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountFragment : Fragment() {
    private var _mBinding: FragmentAccountBinding? = null
    private val mBinding get() = _mBinding!!
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resultLauncher()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentAccountBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.btnLoginGoogle.setOnClickListener {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()
            googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
            googleSignInClient.signInIntent

            signInWithGoogle()
        }

        mBinding.btnCreateAccount.setOnClickListener {
            val action = AccountFragmentDirections.actionAccountFragmentToSignInFragment()
            findNavController().navigate(action)
        }

        mBinding.tvLogIn.setOnClickListener {
            val action = AccountFragmentDirections.actionAccountFragmentToLogInFragment()
            findNavController().navigate(action)
        }
    }

    private fun resultLauncher() {
        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)
                    firebaseAuthWithGoogle(account.idToken!!)
                } catch (e: ApiException) {
                    // Google Sign In failed, update UI appropriately
                    Toast.makeText(requireContext(), "Failed*1", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        activityResultLauncher.launch(signInIntent)
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        auth = FirebaseAuth.getInstance()
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener(requireActivity()) {
            if (it.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                val action = AccountFragmentDirections.actionAccountFragmentToHomeFragment()
                findNavController().navigate(action)
                Toast.makeText(requireContext(), "Succesfully", Toast.LENGTH_SHORT).show()
            } else {
                // If sign in fails, display a message to the user
                Toast.makeText(requireContext(), "Failed*2", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mBinding = null
    }
}