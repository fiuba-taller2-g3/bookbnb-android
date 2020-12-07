package com.fiuba.bookbnb.ui.fragments.footerbar

import android.content.Context
import com.fiuba.bookbnb.R

class FooterBarMenuFavoritesItem(context: Context) : FooterBarMenuItem(context, R.string.footer_bar_favorites_menu, R.drawable.ic_favorites) {

    override val buttonType: FooterBarButtons
        get() = FooterBarButtons.FAVOURITES
}