package com.fiuba.bookbnb.ui.fragments.myBookings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.booking.BookingResponse
import com.fiuba.bookbnb.domain.publish.PublishData
import com.fiuba.bookbnb.networking.NetworkModule
import com.fiuba.bookbnb.ui.fragments.BaseFragment
import com.fiuba.bookbnb.user.UserManager
import retrofit2.Call
import java.util.*

class MyBookingsLoadingFragment : BaseFragment(R.layout.bookbnb_loading_fragment) {

    private val loadingMyBookingsViewModel by activityViewModels<LoadingMyBookingsViewModel>()
    private val loadingBookingPostRequestsViewModel by activityViewModels<LoadingBookingPostRequestsViewModel>()

    override val shouldShowToolbar: Boolean
        get() = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingMyBookingsViewModel.execute(
            NetworkModule.buildRetrofitClient().bookingsGuest(
                UserManager.getUserInfo().getUserId(), UserManager.getUserInfo().getToken()
            ).also { networkManagerViewModel.setCancelCurrentRunningCallReference { cancelMyBookingsRequest(it) } }
        )

        loadingMyBookingsViewModel.bookingsLoadedSuccessfully.observe(viewLifecycleOwner) { postsIsLoaded ->
            if (postsIsLoaded) {
                val postsId = StringJoiner(",");
                loadingMyBookingsViewModel.bookingsResults?.let {
                    it.forEach { bookingResponse -> postsId.add(bookingResponse.postId)}
                }
                loadingBookingPostRequestsViewModel.execute(
                    NetworkModule.buildRetrofitClient().getPosts(postsId.toString(), UserManager.getUserInfo().getToken())
                        .also { networkManagerViewModel.setCancelCurrentRunningCallReference { cancelBookingsPostRequest(it) } }
                )
            }
        }
    }

    private fun cancelMyBookingsRequest(call: Call<List<BookingResponse>>) {
        call.cancel()
        loadingMyBookingsViewModel.hideLoading()
    }

    private fun cancelBookingsPostRequest(call: Call<List<PublishData>>) {
        call.cancel()
        loadingBookingPostRequestsViewModel.hideLoading()
    }

}