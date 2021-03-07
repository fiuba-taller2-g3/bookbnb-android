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
    private val loadingBookingRequestsViewModel by activityViewModels<LoadingBookingRequestsViewModel>()

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
                var postsId = StringJoiner(",");
                loadingMyBookingsViewModel.bookingsResults?.let {
                    it.forEach { bookingResponse -> postsId.add(bookingResponse.postId)}
                }
                loadingBookingRequestsViewModel.execute(
                    NetworkModule.buildRetrofitClient().getPosts(postsId.toString(), UserManager.getUserInfo().getToken())
                        .also { networkManagerViewModel.setCancelCurrentRunningCallReference { cancelBookingsRequest(it) } }
                )
            }
        }
    }

    private fun cancelMyBookingsRequest(call: Call<List<BookingResponse>>) {
        call.cancel()
        loadingMyBookingsViewModel.hideLoading()
    }

    private fun cancelBookingsRequest(call: Call<List<PublishData>>) {
        call.cancel()
        loadingBookingRequestsViewModel.hideLoading()
    }

}