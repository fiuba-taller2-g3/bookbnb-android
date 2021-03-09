package com.fiuba.bookbnb.ui.fragments.footerbar

import android.content.Context
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.ui.navigation.NavigationManager

class FooterBarMenuProfileItem(context: Context) : FooterBarMenuItem(context, R.string.footer_bar_profile_menu, R.drawable.ic_profile) {

    override fun setButtonListener() {
        NavigationManager.moveGlobalToWithPopUpTo(R.id.startProfile, R.id.homeFragment)
    }
}