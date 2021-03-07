package com.fiuba.bookbnb.networking

import com.fiuba.bookbnb.domain.misc.TokenData
import com.fiuba.bookbnb.domain.booking.BookingRequest
import com.fiuba.bookbnb.domain.booking.BookingResponse
import com.fiuba.bookbnb.domain.login.LoginRequest
import com.fiuba.bookbnb.domain.login.LoginResponse
import com.fiuba.bookbnb.domain.misc.MsgResponse
import com.fiuba.bookbnb.domain.publish.PublishData
import com.fiuba.bookbnb.domain.user.UserData
import retrofit2.Call
import retrofit2.http.*

interface BookbnbAPIService {

    @POST("users/login")
    fun login(@Body loginRequest: LoginRequest) : Call<LoginResponse>

    @POST("users")
    fun register(@Body userData: UserData) : Call<MsgResponse>

    @POST("posts")
    fun publish(@Body userData: PublishData, @Header(API_TOKEN) apiToken: String) : Call<PublishData>

    @GET("users/{${USER_ID}}")
    fun getUser(@Path(USER_ID) id: String, @Header(API_TOKEN) apiToken: String) : Call<UserData>

    @PUT("users/{${USER_ID}}")
    fun updateUser(@Path(USER_ID) id: String, @Header(API_TOKEN) apiToken: String, @Body userData: UserData) : Call<MsgResponse>

    @GET("posts")
    fun getPosts(@Query(TYPE) type: String, @Query(LAT) lat: String?, @Query(LNG) lng: String?,
                 @Query(MIN_PRICE) minPrice: String?, @Query(MAX_PRICE) maxPrice: String?,
                 @Query(BEGIN_DATE) beginDate: String?, @Query(END_DATE) endDate: String?,
                 @Query(HIDE_USER_ID) hideUserId: String, @Header(API_TOKEN) apiToken: String) : Call<List<PublishData>>

    @GET("posts")
    fun getUserPosts(@Query(HOST_USER_ID) userId: String, @Header(API_TOKEN) apiToken: String) : Call<List<PublishData>>

    @GET("posts")
    fun getPosts(@Query(POSTS_ID) postsId: String, @Header(API_TOKEN) apiToken: String) : Call<List<PublishData>>

    @POST("bookings")
    fun purchase(@Body userData: BookingRequest, @Header(API_TOKEN) apiToken: String) : Call<MsgResponse>

    @POST("tokens")
    fun registerToken(@Body tokenData: TokenData) : Call<MsgResponse>

    @GET("bookings")
    fun pendingBookingsHost(@Query(HOST_USER_ID) hostUserId: String, @Query(STATUS) status: String, @Header(API_TOKEN) apiToken: String) : Call<List<BookingResponse>>

    @GET("bookings")
    fun bookingsGuest(@Query(GUEST_USER_ID) guestUserId: String, @Header(API_TOKEN) apiToken: String) : Call<List<BookingResponse>>

    @POST("acceptance")
    fun acceptBooking(@Body bookingData: BookingResponse, @Header(API_TOKEN) apiToken: String) : Call<MsgResponse>

    @POST("/rejectance")
    fun rejectBooking(@Body bookingData: BookingResponse, @Header(API_TOKEN) apiToken: String) : Call<MsgResponse>

    companion object {
        private const val USER_ID = "id"
        private const val API_TOKEN = "API_TOKEN"
        private const val TYPE = "type"
        private const val MIN_PRICE = "minPrice"
        private const val MAX_PRICE = "maxPrice"
        private const val BEGIN_DATE = "beginDate"
        private const val END_DATE = "endDate"
        private const val HIDE_USER_ID = "hide_user_id"
        private const val LAT = "lat"
        private const val LNG = "lng"
        private const val HOST_USER_ID = "user_id"
        private const val GUEST_USER_ID = "guest_user_id"
        private const val STATUS = "status"
        private const val POSTS_ID= "posts_id"
    }
}