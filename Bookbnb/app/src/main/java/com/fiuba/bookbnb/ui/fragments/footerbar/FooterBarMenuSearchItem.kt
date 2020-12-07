package com.fiuba.bookbnb.ui.fragments.footerbar

import android.content.Context
import com.fiuba.bookbnb.R

class FooterBarMenuSearchItem(context: Context) : FooterBarMenuItem(context, R.string.footer_bar_search_menu, R.drawable.ic_search) {

    override val buttonType: FooterBarButtons
        get() = FooterBarButtons.SEARCH
}