package com.fiuba.bookbnb.ui.fragments.myBookings

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.ui.fragments.BaseFragment
import com.fiuba.bookbnb.ui.fragments.myPosts.BookingPendingData
import com.fiuba.bookbnb.ui.fragments.myPosts.MyBookingsAdapter
import kotlinx.android.synthetic.main.bookbnb_my_bookings_fragment.*


class MyBookingsFragment : BaseFragment(R.layout.bookbnb_my_bookings_fragment) {

    private val loadingBookingPostRequestsViewModel by activityViewModels<LoadingBookingPostRequestsViewModel>()
    private val loadingMyBookingsViewModel by activityViewModels<LoadingMyBookingsViewModel>()

    private val bookingsPendingData : ArrayList<BookingPendingData> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        generateBookingsPendingData()

        my_bookings_container.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = MyBookingsAdapter(bookingsPendingData, false)
            isVisible = bookingsPendingData.isNotEmpty()
        }

        empty_bookings_text.text = getString(R.string.my_bookings_empty_text)
    }

    private fun generateBookingsPendingData() {
        loadingMyBookingsViewModel.bookingsResults!!.flatMap { bookingResponse ->
            loadingBookingPostRequestsViewModel.bookingRequestsResults!!.filter { publishData ->
                bookingResponse.postId == publishData.id
            }.also { publishDataList ->
                publishDataList.firstOrNull()?.let { bookingsPendingData.add(BookingPendingData(it, bookingResponse)) }
            }
        }
    }

}