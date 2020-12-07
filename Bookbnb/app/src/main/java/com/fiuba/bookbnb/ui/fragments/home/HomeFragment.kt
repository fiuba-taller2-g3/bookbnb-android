package com.fiuba.bookbnb.ui.fragments.home

import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.ui.fragments.BaseFragment
import com.fiuba.bookbnb.ui.navigation.NavigationManager

class HomeFragment : BaseFragment(R.layout.bookbnb_home_fragment) {

    override val shouldShowToolbar: Boolean
        get() = false

    override val shouldShowFooterBarMenu: Boolean
        get() = true

    init {
        // TODO: Cuando se empiece a trabajar en la home se puede eliminar esta transición
        NavigationManager.moveForward(HomeFragmentDirections.actionHomeFragmentToNavLoginGraph())
    }

}