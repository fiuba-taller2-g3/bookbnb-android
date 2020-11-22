package com.fiuba.bookbnb

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import com.facebook.stetho.Stetho
import com.fiuba.bookbnb.ui.ShareViewModel
import com.fiuba.bookbnb.ui.navigation.NavigationManager
import com.fiuba.bookbnb.ui.navigation.NavigationUpdate
import com.fiuba.bookbnb.utils.AnimUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val navController by lazy { findNavController(R.id.app_navigation_container) }
    private val sharedViewModel by viewModels<ShareViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Stetho.initializeWithDefaults(this)
        setContentView(R.layout.activity_main)
        initAppBar()
        initNavigation()
        checkToolbar()
    }

    private fun checkToolbar() {
        sharedViewModel.toolbarVisible.observe(this) { isToolbarVisible ->
            app_toolbar.isVisible = isToolbarVisible
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
    private fun initAppBar() {
        setSupportActionBar(app_toolbar as Toolbar)
        supportActionBar?.apply {
            setHomeButtonEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }
    }

    private fun initNavigation() {
        NavigationManager.navigationLiveData.observe(this) { navigationUpdate ->
            when (navigationUpdate) {
                is NavigationUpdate.GlobalAction -> navController.navigate(navigationUpdate.action)
                is NavigationUpdate.Action -> navController.navigate(navigationUpdate.directions, AnimUtils.slideFragments())
                is NavigationUpdate.Dialog -> navController.navigate(navigationUpdate.creator())
            }
        }
    }
}