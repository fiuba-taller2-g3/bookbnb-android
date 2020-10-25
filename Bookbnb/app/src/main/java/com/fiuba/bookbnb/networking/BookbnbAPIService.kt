package com.fiuba.bookbnb.networking

import com.fiuba.bookbnb.domain.login.LoginRequest
import com.fiuba.bookbnb.domain.login.LoginResponse
import retrofit2.http.*

interface BookbnbAPIService {

    @POST("users/login")
    fun login(@Body loginRequest: LoginRequest) : LoginResponse
}