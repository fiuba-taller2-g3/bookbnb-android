package com.fiuba.bookbnb.ui.fragments.login

import android.os.Bundle
import android.view.View
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.ui.fragments.BaseFragment
import com.fiuba.bookbnb.ui.navigation.NavigationManager
import com.fiuba.bookbnb.user.UserManager

class StartProfileFragment : BaseFragment(R.layout.bookbnb_loading_fragment) {

    override val shouldShowToolbar: Boolean
        get() = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        UserManager.facebookLoginIsProcessingLiveData.observe(viewLifecycleOwner) { isProcessing ->
            if (!isProcessing) checkUserLogged()
        }
    }

    private fun checkUserLogged() {
        if (UserManager.isUserLogged()) {
            NavigationManager.moveForwardWithPopUpTo(StartProfileFragmentDirections.actionStartProfileFragmentToProfileMenuFragment(), R.id.homeFragment)
        } else NavigationManager.moveForwardWithPopUpTo(StartProfileFragmentDirections.actionStartProfileFragmentToStartLoginFragment(), R.id.homeFragment)
    }
}