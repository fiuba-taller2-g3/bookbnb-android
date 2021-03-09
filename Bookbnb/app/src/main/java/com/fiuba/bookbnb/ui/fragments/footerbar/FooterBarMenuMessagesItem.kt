package com.fiuba.bookbnb.ui.fragments.footerbar

import android.content.Context
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.ui.navigation.NavigationManager
import com.fiuba.bookbnb.user.UserManager

class FooterBarMenuMessagesItem(context: Context) : FooterBarMenuItem(context, R.string.footer_bar_messages_menu, R.drawable.ic_messages) {

    override fun setButtonListener() {
        if (UserManager.isUserLogged()) NavigationManager.moveGlobalToWithPopUpTo(R.id.startChatList, R.id.homeFragment)
        else NavigationManager.moveGlobalToWithPopUpTo(R.id.startChatLogin, R.id.homeFragment)
    }
}