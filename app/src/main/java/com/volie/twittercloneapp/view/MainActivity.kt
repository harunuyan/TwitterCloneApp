package com.volie.twittercloneapp.view

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.volie.twittercloneapp.R
import com.volie.twittercloneapp.databinding.ActivityMainBinding
import com.volie.twittercloneapp.databinding.HeaderNavigationDrawerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    var _mBinding: ActivityMainBinding? = null
    val mBinding get() = _mBinding!!
    private lateinit var navController: NavController
    private val firebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackPressedDispatcher.onBackPressed()
            }
        }

        this.onBackPressedDispatcher.addCallback(this, callback)

        setUserHeaderData()

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

        mBinding.navigationView.itemIconTintList = null
    }

    private fun setupDrawerLayout() {
        val appBarConfiguration = AppBarConfiguration(navController.graph, mBinding.drawerLayout)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        mBinding.navigationView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)

        mBinding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.profileFragment -> {
                    navController.navigate(R.id.profileFragment)
                }
            }
            mBinding.drawerLayout.close()
            true
        }

    }

    private fun setUserHeaderData() {
        val headerView = mBinding.navigationView.getHeaderView(0)
        val hBinding = HeaderNavigationDrawerBinding.bind(headerView)

        val user = firebaseAuth.currentUser
        hBinding.tvNameDrawer.text = user?.displayName
        hBinding.tvNicknameDrawer.text = "@${user?.displayName?.lowercase()?.replace(" ", "")}"
        Glide.with(this)
            .load(user?.photoUrl)
            .into(hBinding.ivProfileDrawer)

        hBinding.root.setOnClickListener {
            navController.navigate(R.id.profileFragment)
            mBinding.drawerLayout.close()
        }
    }

    private fun setupBottomNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.addPostFragment -> {
                    mBinding.bottomNavigationView.visibility = View.GONE
                    mBinding.toolbar.visibility = View.GONE
                }
                R.id.postDetailsFragment -> {
                    mBinding.bottomNavigationView.visibility = View.VISIBLE
                    mBinding.toolbar.visibility = View.VISIBLE
                }

                R.id.accountFragment -> {
                    mBinding.bottomNavigationView.visibility = View.GONE
                    mBinding.toolbar.visibility = View.GONE
                    mBinding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                }

                R.id.signInFragment -> {
                    mBinding.bottomNavigationView.visibility = View.GONE
                    mBinding.toolbar.visibility = View.GONE
                    mBinding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                }

                R.id.logInFragment -> {
                    mBinding.bottomNavigationView.visibility = View.GONE
                    mBinding.toolbar.visibility = View.GONE
                    mBinding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                }

                R.id.commentFragment -> {
                    mBinding.bottomNavigationView.visibility = View.GONE
                    mBinding.toolbar.visibility = View.GONE
                    mBinding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                }

                R.id.trendsFragment -> {
                    mBinding.toolbar.visibility = View.GONE
                    mBinding.bottomNavigationView.visibility = View.VISIBLE
                }

                R.id.messageFragment -> {
                    mBinding.toolbar.visibility = View.GONE
                }

                R.id.profileFragment -> {
                    mBinding.toolbar.visibility = View.GONE
                    mBinding.bottomNavigationView.visibility = View.GONE
                }

                R.id.feedFragment -> {
                    mBinding.toolbar.visibility = View.GONE
                    mBinding.bottomNavigationView.visibility = View.VISIBLE
                }
                R.id.splashScreenFragment -> {
                    mBinding.toolbar.visibility = View.GONE
                    mBinding.bottomNavigationView.visibility = View.GONE
                    mBinding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                }

                else -> {
                    mBinding.toolbar.visibility = View.VISIBLE
                    mBinding.bottomNavigationView.visibility = View.VISIBLE
                    mBinding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                }
            }
        }
        mBinding.bottomNavigationView.setupWithNavController(navController)
        setupActionBarWithNavController(navController)
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