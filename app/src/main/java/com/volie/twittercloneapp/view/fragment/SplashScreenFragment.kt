package com.volie.twittercloneapp.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.volie.twittercloneapp.R
import com.volie.twittercloneapp.databinding.FragmentSplashScreenBinding

@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : Fragment() {
    private var _mBinding: FragmentSplashScreenBinding? = null
    private val mBinding get() = _mBinding!!
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().window.statusBarColor =
            ContextCompat.getColor(requireContext(), R.color.twitter_blue_splash_screen)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Google hesap doğrulama kütüphanesi için yapılandırma ayarlarını yapın
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)

        Handler(Looper.getMainLooper()).postDelayed({
            checkLoggedIn()
        }, 1000)
    }

    override fun onPause() {
        super.onPause()
        requireActivity().setTheme(R.style.Theme_TwitterCloneApp)
    }

    private fun checkLoggedIn() {
        // Kullanıcının daha önce giriş yapmış olup olmadığını kontrol edin
        val account = GoogleSignIn.getLastSignedInAccount(requireContext())
        if (account != null) {
            // Giriş yapılmışsa ana sayfaya yönlendirin
            val action = SplashScreenFragmentDirections.actionSplashScreenFragmentToFeedFragment()
            findNavController().navigate(action)
        } else {
            val action =
                SplashScreenFragmentDirections.actionSplashScreenFragmentToAccountFragment()
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mBinding = null
    }
}