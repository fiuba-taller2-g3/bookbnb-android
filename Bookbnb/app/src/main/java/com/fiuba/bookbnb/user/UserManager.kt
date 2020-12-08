package com.fiuba.bookbnb.user

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.facebook.GraphResponse
import com.facebook.Profile
import com.facebook.login.LoginManager
import com.fiuba.bookbnb.domain.user.UserData
import com.fiuba.bookbnb.repository.UserLoggedInData
import com.fiuba.bookbnb.utils.DateUtils
import org.apache.commons.lang3.StringUtils
import org.json.JSONObject

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
            val request = GraphRequest.newMeRequest(currentToken) { obj, _ ->
                val birthday = DateUtils.convertFacebookFormatToStandard(obj.getString(BIRTHDAY))
                val userData = UserData(it.firstName, it.lastName, obj.getString(EMAIL), null, "", "", birthday, currentToken.userId)
                val userInfo = with(currentToken) { UserInfo(userId, userId, expires, userData) }
                setUserInfo(userInfo)
            }

            Bundle().run {
                putString(FIELDS, "${BIRTHDAY + COMMA}${EMAIL + COMMA}${LOCATION}")
                request.parameters = this
                request.executeAsync()
            }
        }
    }

    private fun cleanUser() {
        mutableUserLoggedInLiveData.value = UserLoggedInData(StringUtils.EMPTY)
    }

    private fun throwUserNotFoundException(): Nothing = throw Exception("No logged in user found")

    const val FIELDS = "fields"
    const val EMAIL = "email"
    private const val BIRTHDAY = "birthday"
    const val LOCATION = "hometown"
    private const val COMMA = ","
}