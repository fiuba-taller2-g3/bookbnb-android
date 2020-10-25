package com.fiuba.bookbnb.domain.login

import java.io.Serializable

data class LoginRequest(val email: String, val password: String) : Serializable
