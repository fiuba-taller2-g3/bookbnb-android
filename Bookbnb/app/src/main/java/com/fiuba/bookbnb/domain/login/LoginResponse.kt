package com.fiuba.bookbnb.domain.login

import java.io.Serializable

data class LoginResponse(val apiToken: String?, val error: String?) : Serializable