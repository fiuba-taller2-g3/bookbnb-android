package com.fiuba.bookbnb.ui.fragments.login

import android.util.Log
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.user.UserData
import com.fiuba.bookbnb.networking.NetworkViewModel
import com.fiuba.bookbnb.ui.navigation.NavigationManager
import com.fiuba.bookbnb.user.UserInfo
import com.fiuba.bookbnb.user.UserManager
import retrofit2.Call
import retrofit2.Response
import java.util.*

class LoadingProfileViewModel : NetworkViewModel(), NetworkViewModel.CallResponse<UserData> {

    private var userInfoDecoded : UserInfoDecoded? = null

    fun loadUserData(call: Call<UserData>, userId: Int, token: String, exp: Date) {
        userInfoDecoded = UserInfoDecoded(userId, token, exp)
        Log.i(TAG, "Getting logged user information...")
        executeCallback(call, this)
    }

    override fun onSuccessful(response: Response<UserData>) {
        response.body()?.let {
            userInfoDecoded?.let { userInfo ->
                Log.i(TAG, "Getting logged successfully with id: ${userInfo.id}")
                UserManager.setUserInfo(UserInfo(userInfo.token, userInfo.id, userInfo.exp, it))
                NavigationManager.moveForwardWithPopUpTo(LoadingProfileFragmentDirections.actionLoadingFragmentToProfileMenuFragment(), R.id.homeFragment)
            }
        }
    }

    override fun onFailure(response: Response<UserData>) {
        // Do Nothing
    }

    companion object {
        const val TAG = "TOKEN"
    }

    data class UserInfoDecoded(val id: Int, val token: String, val exp: Date)

}