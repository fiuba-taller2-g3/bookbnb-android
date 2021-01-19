package com.fiuba.bookbnb.ui.fragments.home

import android.os.Bundle
import android.view.View
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.ui.fragments.BaseFragment
import com.fiuba.bookbnb.ui.fragments.footerbar.FooterBarButtons
import com.fiuba.bookbnb.ui.navigation.NavigationManager
import com.fiuba.bookbnb.user.UserManager
import kotlinx.android.synthetic.main.bookbnb_home_fragment.*

class HomeFragment : BaseFragment(R.layout.bookbnb_home_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bkbnb_form_title.setText(getTitle())
        bkbnb_form_subtitle.setText(getSubtitle())
        start_button.text = getButtonText()

        start_button.setOnClickListener {
            if (UserManager.isUserLogged()) NavigationManager.moveForward(HomeFragmentDirections.actionHomeFragmentToSearchFragment())
            else NavigationManager.moveGlobalTo(R.id.startProfile)
        }

    }

    private fun getTitle() : Int {
        return if (UserManager.isUserLogged()) R.string.home_search_title else R.string.home_search_title_not_logged
    }

    private fun getSubtitle() : Int {
        return if (UserManager.isUserLogged()) R.string.home_search_subtitle else R.string.home_search_subtitle_not_logged
    }

    private fun getButtonText() : String {
        return getString(if (UserManager.isUserLogged()) R.string.home_search_button else R.string.login_title)
    }

    override val shouldShowToolbar: Boolean
        get() = false

    override val shouldShowFooterBarMenu: Boolean
        get() = true

    override fun onResume() {
        super.onResume()
        sharedViewModel.setOption(FooterBarButtons.SEARCH)
    }

}