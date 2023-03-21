package com.volie.twittercloneapp.view

import android.os.Bundle
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
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

        onBackPressedDispatcher.addCallback(this) {
            if (mBinding.drawerLayout.isOpen) {
                mBinding.drawerLayout.close()
            } else {
                navController.navigateUp() || kotlin.run {
                    finish()
                    true
                }
            }
        }
        setSupportActionBar(mBinding.toolbar)
        setupBottomNavigation()
        setupDrawerLayout()
    }

    private fun setupDrawerLayout() {
        val appBarConfiguration = AppBarConfiguration(navController.graph, mBinding.drawerLayout)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        mBinding.navigationView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun setupBottomNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        mBinding.bottomNavigationView.setupWithNavController(navController)
        setupActionBarWithNavController(navController)
    }

    private fun setStatusAndNavBarColor() {
        val window = window
        val color = SurfaceColors.SURFACE_2.getColor(this)
        window!!.statusBarColor = color
        window.navigationBarColor = color
    }

    override fun onBackPressed() {
        super.getOnBackPressedDispatcher().onBackPressed()
    }

    override fun onSupportNavigateUp(): Boolean {
        if (mBinding.drawerLayout.isOpen) {
            mBinding.drawerLayout.close()
            return true
        }
        return NavigationUI.navigateUp(navController, mBinding.drawerLayout)
    }


    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}