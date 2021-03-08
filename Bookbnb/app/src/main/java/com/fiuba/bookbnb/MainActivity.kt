package com.fiuba.bookbnb

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.facebook.stetho.Stetho
import com.fiuba.bookbnb.forms.FormViewModel
import com.fiuba.bookbnb.networking.NetworkManagerViewModel
import com.fiuba.bookbnb.ui.ShareViewModel
import com.fiuba.bookbnb.ui.fragments.footerbar.*
import com.fiuba.bookbnb.ui.navigation.NavigationManager
import com.fiuba.bookbnb.ui.navigation.NavigationUpdate
import com.fiuba.bookbnb.user.UserManager
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.bookbnb_activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val navController by lazy { findNavController(R.id.app_navigation_container) }
    private val sharedViewModel by viewModels<ShareViewModel>()
    private val formViewModel by viewModels<FormViewModel>()
    private val networkManagerViewModel by viewModels<NetworkManagerViewModel>()
    private val footerBarButtons by lazy { EnumMap<FooterBarButtons, FooterBarMenuItem>(FooterBarButtons::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Stetho.initializeWithDefaults(this)
        setContentView(R.layout.bookbnb_activity_main)
        initAppBar()
        initNavigation()
        initFooterBarMenu()
        checkToolbar()
        checkFooterBarMenu()
        checkFooterBarMenuOptionSelected()
        UserManager.loginWithFacebook()
        FirebaseInstanceId.getInstance().getToken()
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
        addFooterBarButton(FooterBarButtons.SEARCH)
        addFooterBarButton(FooterBarButtons.FAVOURITES)
        addFooterBarButton(FooterBarButtons.MESSAGES)
        addFooterBarButton(FooterBarButtons.PROFILE)
    }

    private fun addFooterBarButton(idButton: FooterBarButtons) {
        footerBarButtons[idButton] = getFooterBarButtonItem(idButton).also { footer_bar_menu.addView(it) }
    }

    private fun getFooterBarButtonItem(idButton: FooterBarButtons) : FooterBarMenuItem {
        return when(idButton) {
            FooterBarButtons.SEARCH -> FooterBarMenuSearchItem(this@MainActivity)
            FooterBarButtons.FAVOURITES -> FooterBarMenuFavoritesItem(this@MainActivity)
            FooterBarButtons.MESSAGES -> FooterBarMenuMessagesItem(this@MainActivity)
            FooterBarButtons.PROFILE -> FooterBarMenuProfileItem(this@MainActivity)
        }
    }

    private fun checkFooterBarMenuOptionSelected() {
        sharedViewModel.footerBarMenuOptionSelected.observe(this) { option ->
            disableAllFooterBarButtons()
            footerBarButtons[option]?.activeButton()
        }
    }

    private fun disableAllFooterBarButtons() {
        footerBarButtons.values.forEach {
            it.disableButton()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        checkClearInputs()
        networkManagerViewModel.cancelCurrentRunningCall()
        return navController.navigateUp()
    }

    override fun onBackPressed() {
        checkClearInputs()
        networkManagerViewModel.cancelCurrentRunningCall()
        super.onBackPressed()
    }

    private fun checkClearInputs() {
        if (sharedViewModel.shouldClearInputsWhenBackPressed) formViewModel.clearInputs()
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
                is NavigationUpdate.GlobalAction -> with(navigationUpdate) { navController.navigate(action, null, buildNavigationOptions(popUpTo, popUpToInclusive)) }
                is NavigationUpdate.Action -> with(navigationUpdate) { navController.navigate(directions, buildNavigationOptions(popUpTo, popUpToInclusive)) }
                is NavigationUpdate.Dialog -> navController.navigate(navigationUpdate.creator())
                is NavigationUpdate.PopBackStack -> navController.popBackStack()
                else -> {}
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

    override fun onDestroy() {
        NavigationManager.cleanAction()
        super.onDestroy()
    }
}