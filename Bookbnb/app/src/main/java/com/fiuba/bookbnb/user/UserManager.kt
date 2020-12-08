package com.fiuba.bookbnb.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.facebook.AccessToken
import com.facebook.Profile
import com.facebook.login.LoginManager
import com.fiuba.bookbnb.domain.user.UserData
import com.fiuba.bookbnb.repository.UserLoggedInData
import org.apache.commons.lang3.StringUtils

object UserManager {

    private var userLoggedInfo: UserInfo? = null

    private val mutableUserLoggedInLiveData = MutableLiveData<UserLoggedInData>()
    val userLoggedInLiveData: LiveData<UserLoggedInData>
        get() = mutableUserLoggedInLiveData

    init {
        cleanUser()
    }

    fun isUserLogged() = (userLoggedInfo != null)

    fun setUserInfo(userInfo: UserInfo) {
        userLoggedInfo = userInfo
        mutableUserLoggedInLiveData.value = UserLoggedInData(userInfo.getUserData().name)
    }

    fun getUserInfo() = userLoggedInfo ?: throwUserNotFoundException()

    fun updateUser(userData: UserData) {
        userLoggedInfo?.let { user ->
            user.updateUserData(userData)
            mutableUserLoggedInLiveData.value = UserLoggedInData(user.getUserData().name)
        } ?: throwUserNotFoundException()
    }

    fun logout() {
        LoginManager.getInstance().logOut()
        userLoggedInfo = null
        cleanUser()
    }

    fun loginWithFacebook() {
        Profile.getCurrentProfile()?.let {
            val currentToken = AccessToken.getCurrentAccessToken()
            val userData = UserData(it.firstName, it.lastName, "", null, "", "", "", "", currentToken.userId)
            val userInfo = with(currentToken) { UserInfo(userId, userId, expires, userData) }
            setUserInfo(userInfo)
        }
    }

    private fun cleanUser() {
        mutableUserLoggedInLiveData.value = UserLoggedInData(StringUtils.EMPTY)
    }

    private fun throwUserNotFoundException(): Nothing = throw Exception("No logged in user found")
}