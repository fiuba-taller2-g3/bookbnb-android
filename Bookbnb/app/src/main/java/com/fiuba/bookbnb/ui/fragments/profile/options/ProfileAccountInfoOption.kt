package com.fiuba.bookbnb.ui.fragments.profile.options

import android.content.Context
import com.fiuba.bookbnb.ui.fragments.profile.ProfileButtonMenu
import com.fiuba.bookbnb.ui.fragments.profile.ProfileMenuFragmentDirections
import com.fiuba.bookbnb.ui.navigation.NavigationManager
import kotlinx.android.synthetic.main.bookbnb_button_profile_menu.view.*

class ProfileAccountInfoOption(context: Context, labelRes: Int, img: Int) : ProfileButtonMenu(context, labelRes, img) {

    init {
        loadAction()
    }

    override fun loadAction() {
        profile_option_container.setOnClickListener {
            NavigationManager.moveForward(ProfileMenuFragmentDirections.actionProfileMenuFragmentToProfileEditFragment())
        }
    }

}