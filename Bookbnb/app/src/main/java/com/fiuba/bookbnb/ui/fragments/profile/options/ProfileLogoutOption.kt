package com.fiuba.bookbnb.ui.fragments.profile.options

import android.content.Context
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.ui.fragments.profile.ProfileButtonMenu
import com.fiuba.bookbnb.ui.fragments.profile.ProfileMenuFragmentDirections
import com.fiuba.bookbnb.ui.navigation.NavigationManager
import com.fiuba.bookbnb.user.UserManager

class ProfileLogoutOption(context: Context, labelRes: Int, img: Int) : ProfileButtonMenu(context, labelRes, img) {

    override fun loadAction() {
        NavigationManager.moveForwardWithPopUpTo(ProfileMenuFragmentDirections.actionProfileMenuFragmentToStartLoginFragment(), R.id.homeFragment)
        UserManager.logout()
    }

}