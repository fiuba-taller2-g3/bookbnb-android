package com.fiuba.bookbnb.ui.fragments.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.facebook.*
import com.facebook.login.LoginResult
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.ui.fragments.BaseFragment
import com.fiuba.bookbnb.ui.fragments.footerbar.FooterBarButtons
import com.fiuba.bookbnb.ui.navigation.NavigationManager
import kotlinx.android.synthetic.main.bookbnb_button.*
import kotlinx.android.synthetic.main.bookbnb_start_login_fragment.*

class StartLoginFragment : BaseFragment(R.layout.bookbnb_start_login_fragment) {

    override val shouldShowFooterBarMenu: Boolean
        get() = true

    private val callbackManager by lazy { CallbackManager.Factory.create() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button.text = getString(R.string.start_login_email_text)
        button.setOnClickListener { NavigationManager.moveForward(StartLoginFragmentDirections.actionStartLoginFragmentToLoginFragment()) }
        not_register_text.setOnClickListener { NavigationManager.moveForward(StartLoginFragmentDirections.actionStartLoginFragmentToRegisterFragment()) }
        facebook_login_custom_button.setOnClickListener { facebook_login_button.performClick() }
        onFacebookLoginResult()
    }

    private fun onFacebookLoginResult() {
        facebook_login_button.fragment = this
        facebook_login_button.setPermissions(listOf(EMAIL));
        facebook_login_button.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {

            override fun onSuccess(result: LoginResult) {
                Log.i("LOGIN", result.accessToken.token)
                Log.i("LOGIN", result.accessToken.userId)
                val profileTracker = object : ProfileTracker() {
                    override fun onCurrentProfileChanged(oldProfile: Profile?, currentProfile: Profile?) {
                        this.stopTracking()
                        Profile.setCurrentProfile(currentProfile)
                        currentProfile?.let {
                            Log.i("LOGIN", it.firstName)
                            Log.i("LOGIN", it.lastName)
                        }
                    }
                }
                profileTracker.startTracking()
            }

            override fun onCancel() {
                // Do Nothing
            }

            override fun onError(error: FacebookException) {
                // Do Nothing
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onResume() {
        super.onResume()
        sharedViewModel.setOption(FooterBarButtons.PROFILE)
    }

    companion object {
        private const val EMAIL = "email"
    }
}