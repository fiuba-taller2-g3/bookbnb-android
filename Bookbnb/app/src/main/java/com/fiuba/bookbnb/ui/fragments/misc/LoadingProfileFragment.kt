package com.fiuba.bookbnb.ui.fragments.misc

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.auth0.android.jwt.JWT
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.user.UserData
import com.fiuba.bookbnb.networking.NetworkModule
import com.fiuba.bookbnb.ui.fragments.NetworkFragment

class LoadingProfileFragment : NetworkFragment<UserData>(R.layout.bookbnb_loading_fragment) {

    private val navArguments by navArgs<LoadingProfileFragmentArgs>()
    private val token by lazy { navArguments.token }
    private val loadingProfileViewModel by viewModels<LoadingProfileViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginUser(token)
    }

    private fun loginUser(token: String) {
        Log.i(LoadingProfileViewModel.TAG, "Decoding token...")
        val decode = JWT(token)
        decode.claims.let { claim ->
            val id = claim["id"]?.asInt()
            val exp = decode.expiresAt
            loadingProfileViewModel.loadUserData(NetworkModule.buildRetrofitClient().getUser(id!!, token), id, token, exp!!)
        }
    }
}