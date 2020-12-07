package com.fiuba.bookbnb

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.facebook.stetho.Stetho
import com.fiuba.bookbnb.ui.ShareViewModel
import com.fiuba.bookbnb.ui.fragments.footerbar.FooterBarMenuItem
import com.fiuba.bookbnb.ui.navigation.NavigationManager
import com.fiuba.bookbnb.ui.navigation.NavigationUpdate
import kotlinx.android.synthetic.main.bookbnb_activity_main.*

class MainActivity : AppCompatActivity() {

    private val navController by lazy { findNavController(R.id.app_navigation_container) }
    private val sharedViewModel by viewModels<ShareViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Stetho.initializeWithDefaults(this)
        setContentView(R.layout.bookbnb_activity_main)
        initAppBar()
        initNavigation()
        initFooterBarMenu()
        checkToolbar()
        checkFooterBarMenu()
    }

    private fun checkToolbar() {
        sharedViewModel.toolbarVisible.observe(this) { isToolbarVisible ->
            app_toolbar.isVisible = isToolbarVisible
        }
    }

    private fun checkFooterBarMenu() {
        sharedViewModel.footerBarMenu.observe(this) { isFooterBarMenuVisible ->
            footer_bar_menu.isVisible = isFooterBarMenuVisible
        }
    }

    private fun initFooterBarMenu() {
        with(footer_bar_menu) {
            addView(FooterBarMenuItem(this@MainActivity, R.string.footer_bar_search_menu, R.drawable.ic_search))
            addView(FooterBarMenuItem(this@MainActivity, R.string.footer_bar_favorites_menu, R.drawable.ic_favorites))
            addView(FooterBarMenuItem(this@MainActivity, R.string.footer_bar_messages_menu, R.drawable.ic_messages))
            addView(FooterBarMenuItem(this@MainActivity, R.string.footer_bar_profile_menu, R.drawable.ic_profile))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return if (sharedViewModel.fragmentHaveNetwork()) {
            sharedViewModel.closeFragment()
            true
        } else navController.navigateUp()
    }

    override fun onBackPressed() {
        if (sharedViewModel.fragmentHaveNetwork()) {
            sharedViewModel.closeFragment()
        } else super.onBackPressed()
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
                is NavigationUpdate.Action -> with(navigationUpdate) { navController.navigate(directions, buildNavigationOptions(popUpTo, popUpToInclusive)) }
                is NavigationUpdate.Dialog -> navController.navigate(navigationUpdate.creator())
                is NavigationUpdate.PopBackStack -> navController.popBackStack()
            }
        }
    }

    private fun buildNavigationOptions(popUpTo: Int?, popUpToInclusive: Boolean): NavOptions {
        val navOptions = NavOptions.Builder().run {
            setEnterAnim(R.anim.slide_in_right)
            setExitAnim(R.anim.slide_out_left)
            setPopEnterAnim(R.anim.slide_in_left)
            setPopExitAnim(R.anim.slide_out_right)
        }

        popUpTo?.let { navOptions.setPopUpTo(it, popUpToInclusive) }

        return navOptions.build()
    }
}