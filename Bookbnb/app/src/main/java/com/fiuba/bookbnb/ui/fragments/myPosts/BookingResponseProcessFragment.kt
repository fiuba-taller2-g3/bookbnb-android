package com.fiuba.bookbnb.ui.fragments.myPosts

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.misc.MsgResponse
import com.fiuba.bookbnb.networking.NetworkModule
import com.fiuba.bookbnb.ui.fragments.BaseFragment
import com.fiuba.bookbnb.user.UserManager
import retrofit2.Call

class BookingResponseProcessFragment : BaseFragment(R.layout.bookbnb_booking_request_process) {

    private val navArguments by navArgs<BookingResponseProcessFragmentArgs>()
    private val isAcceptedRequest by lazy { navArguments.isAcceptedRequest }
    private val bookingResponse by lazy { navArguments.bookingResponse }

    private val bookingResponseProcessViewModel by viewModels<BookingResponseProcessViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bookingResponseProcessViewModel.execute(getEndpoint())
    }

    private fun getEndpoint() : Call<MsgResponse> {
        return with(NetworkModule.buildRetrofitClient()) {
            if (isAcceptedRequest) acceptBooking(bookingResponse, UserManager.getUserInfo().getToken())
            else rejectBooking(bookingResponse, UserManager.getUserInfo().getToken())
        }
    }

}