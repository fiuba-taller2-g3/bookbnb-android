package com.fiuba.bookbnb.ui.fragments.footerbar

import android.content.Context
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.ui.navigation.NavigationManager

class FooterBarMenuSearchItem(context: Context) : FooterBarMenuItem(context, R.string.footer_bar_search_menu, R.drawable.ic_search) {

    override val buttonType: FooterBarButtons
        get() = FooterBarButtons.SEARCH

    override fun setButtonListener() {
        super.setButtonListener()
        NavigationManager.popBackStack()
    }
}