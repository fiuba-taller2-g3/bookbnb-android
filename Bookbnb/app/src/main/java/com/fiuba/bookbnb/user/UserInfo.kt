package com.fiuba.bookbnb.user

import com.fiuba.bookbnb.domain.user.UserData
import java.util.*

class UserInfo(private val token: String, private val id: String, private val exp: Date, private var userData: UserData) {

    fun getUserId() = id
    fun getExpirationDate() = exp
    fun getToken() = token
    fun getUserData() = userData

    fun updateUserData(userDataUpdated: UserData) {
        userData = userDataUpdated
    }

    // TODO: Faltaría implementar para que el sistema se deslogué cuando expira el token
}