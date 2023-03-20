package com.volie.twittercloneapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.elevation.SurfaceColors
import com.volie.twittercloneapp.R
import com.volie.twittercloneapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _mBinding: ActivityMainBinding? = null
    private val mBinding get() = _mBinding!!
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _mBinding = ActivityMainBinding.inflate(layoutInflater)
        setStatusAndNavBarColor()
        setContentView(mBinding.root)
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        mBinding.bottomNavigationView.setupWithNavController(navController)
    }

    fun setStatusAndNavBarColor() {
        val window = window
        val color = SurfaceColors.SURFACE_2.getColor(this)
        window!!.statusBarColor = color
        window.navigationBarColor = color
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}