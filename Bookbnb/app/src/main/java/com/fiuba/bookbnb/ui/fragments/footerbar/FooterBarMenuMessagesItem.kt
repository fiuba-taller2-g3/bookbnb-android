package com.fiuba.bookbnb.ui.fragments.footerbar

import android.content.Context
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.ui.navigation.NavigationManager

class FooterBarMenuMessagesItem(context: Context) : FooterBarMenuItem(context, R.string.footer_bar_messages_menu, R.drawable.ic_messages) {

    override fun setButtonListener() {
        NavigationManager.moveGlobalTo(R.id.seeChatList)
    }
}