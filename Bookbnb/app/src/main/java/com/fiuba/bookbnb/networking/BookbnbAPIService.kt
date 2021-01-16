package com.fiuba.bookbnb.networking

import com.fiuba.bookbnb.domain.booking.BookingRequest
import com.fiuba.bookbnb.domain.login.LoginRequest
import com.fiuba.bookbnb.domain.login.LoginResponse
import com.fiuba.bookbnb.domain.misc.MsgResponse
import com.fiuba.bookbnb.domain.publish.PublishRequest
import com.fiuba.bookbnb.domain.publish.PublishResponse
import com.fiuba.bookbnb.domain.user.UserData
import retrofit2.Call
import retrofit2.http.*

interface BookbnbAPIService {

    @POST("users/login")
    fun login(@Body loginRequest: LoginRequest) : Call<LoginResponse>

    @POST("users")
    fun register(@Body userData: UserData) : Call<MsgResponse>

    @POST("posts")
    fun publish(@Body userData: PublishRequest, @Header(API_TOKEN) apiToken: String) : Call<PublishResponse>

    @GET("users/{${USER_ID}}")
    fun getUser(@Path(USER_ID) id: String, @Header(API_TOKEN) apiToken: String) : Call<UserData>

    @PUT("users/{${USER_ID}}")
    fun updateUser(@Path(USER_ID) id: String, @Header(API_TOKEN) apiToken: String, @Body userData: UserData) : Call<MsgResponse>

    @GET("posts")
    fun getPosts(@Header(API_TOKEN) apiToken: String) : Call<List<PublishResponse>>

    @POST("bookings")
    fun purchase(@Body userData: BookingRequest, @Header(API_TOKEN) apiToken: String) : Call<MsgResponse>

    companion object {
        private const val USER_ID = "id"
        private const val API_TOKEN = "API_TOKEN"
    }
}