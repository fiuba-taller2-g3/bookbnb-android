package com.fiuba.bookbnb.networking

import com.fiuba.bookbnb.domain.login.LoginRequest
import com.fiuba.bookbnb.domain.login.LoginResponse
import com.fiuba.bookbnb.domain.register.RegisterRequest
import com.fiuba.bookbnb.domain.register.RegisterResponse
import retrofit2.Call
import retrofit2.http.*

interface BookbnbAPIService {

    @POST("users/login")
    fun login(@Body loginRequest: LoginRequest) : Call<LoginResponse>

    @POST("users")
    fun register(@Body registerRequest: RegisterRequest) : Call<RegisterResponse>
}