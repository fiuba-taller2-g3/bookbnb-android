package com.fiuba.bookbnb

object RegistrationToken {
    private var registrationToken: String? = null

    fun setToken(token: String) {
        registrationToken = token
    }

    fun getToken() = registrationToken
}
