package com.fiuba.bookbnb.ui.fragments.footerbar

import android.content.Context
import com.fiuba.bookbnb.R

class FooterBarMenuProfileItem(context: Context) : FooterBarMenuItem(context, R.string.footer_bar_profile_menu, R.drawable.ic_profile) {

    override val buttonType: FooterBarButtons
        get() = FooterBarButtons.PROFILE
}