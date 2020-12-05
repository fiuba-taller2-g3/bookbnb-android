package com.fiuba.bookbnb.ui.fragments.login

import android.os.Bundle
import android.view.View
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.misc.ButtonItem
import com.fiuba.bookbnb.ui.fragments.BaseFragment
import com.fiuba.bookbnb.ui.navigation.NavigationManager
import kotlinx.android.synthetic.main.bookbnb_button.*
import kotlinx.android.synthetic.main.bookbnb_start_login_fragment.*

class StartLoginFragment : BaseFragment(R.layout.bookbnb_start_login_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button.text = getString(R.string.start_login_email_text)
        button.setOnClickListener { NavigationManager.moveForward(StartLoginFragmentDirections.actionStartLoginFragmentToLoginFragment()) }
        not_register_text.setOnClickListener { NavigationManager.moveForward(StartLoginFragmentDirections.actionStartLoginFragmentToRegisterFragment()) }
        buttons_container.addView(ButtonItem(requireContext(), "Iniciar con Facebook", R.drawable.facebook_button_ripple))
    }
}