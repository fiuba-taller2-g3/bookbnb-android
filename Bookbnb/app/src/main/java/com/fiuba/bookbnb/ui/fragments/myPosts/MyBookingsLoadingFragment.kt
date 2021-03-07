package com.fiuba.bookbnb.ui.fragments.myPosts

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.booking.BookingResponse
import com.fiuba.bookbnb.networking.NetworkModule
import com.fiuba.bookbnb.ui.fragments.BaseFragment
import com.fiuba.bookbnb.user.UserManager
import retrofit2.Call

class MyBookingsLoadingFragment : BaseFragment(R.layout.bookbnb_loading_fragment) {

    private val loadingHostBookingsViewModel by activityViewModels<LoadingHostBookingsViewModel>()

    override val shouldShowToolbar: Boolean
        get() = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingHostBookingsViewModel.execute(
            NetworkModule.buildRetrofitClient().pendingBookingsHost(
                UserManager.getUserInfo().getUserId(), PENDING_STATUS, UserManager.getUserInfo().getToken()
            ).also { networkManagerViewModel.setCancelCurrentRunningCallReference { cancelRequest(it) } }
        )
    }

    private fun cancelRequest(call: Call<List<BookingResponse>>) {
        call.cancel()
        loadingHostBookingsViewModel.hideLoading()
    }

    companion object {
        private const val PENDING_STATUS = "pending"
    }

}