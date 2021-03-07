package com.fiuba.bookbnb.ui.fragments.profile.options

import android.content.Context
import com.fiuba.bookbnb.ui.fragments.profile.ProfileButtonMenu
import com.fiuba.bookbnb.ui.fragments.profile.ProfileMenuFragmentDirections
import com.fiuba.bookbnb.ui.navigation.NavigationManager

class ProfileMyPosts(context: Context, labelRes: Int, img: Int) : ProfileButtonMenu(context, labelRes, img) {

    override fun loadAction() {
        NavigationManager.moveForward(ProfileMenuFragmentDirections.actionProfileMenuFragmentToMyPostsLoadingFragment())
    }
}