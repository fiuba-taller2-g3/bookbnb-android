package com.fiuba.bookbnb.ui.fragments.chat

import android.os.Bundle
import android.view.View
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.ui.navigation.NavigationManager
import kotlinx.android.synthetic.main.bookbnb_chat_login_fragment.*

class ChatLoginFragment : ChatBaseFragment(R.layout.bookbnb_chat_login_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login_button.setOnClickListener {
            NavigationManager.moveGlobalToWithPopUpTo(R.id.startProfile, R.id.homeFragment)
        }
    }

}