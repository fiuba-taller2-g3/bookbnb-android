package com.fiuba.bookbnb.ui.fragments.myBookings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.ui.fragments.BaseFragment
import kotlinx.android.synthetic.main.bookbnb_my_posts_fragment.*

class MyBookingsFragment : BaseFragment(R.layout.bookbnb_my_bookings_fragment) {

    private val loadingBookingPostRequestsViewModel by activityViewModels<LoadingBookingPostRequestsViewModel>()
    private val loadingMyBookingsViewModel by activityViewModels<LoadingMyBookingsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        generateBookingsPendingData()

        posts_list.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
//            adapter = MyPostsAdapter(loadingMyPostsViewModel.postsResults!!)
//            isVisible = loadingMyPostsViewModel.postsResults!!.isNotEmpty()
        }

//        setEmptyPostText(R.string.my_bookings_empty_text)
    }

    private fun generateBookingsPendingData() {
        //loadingMyBookingsViewModel.bookingsResults!!.flatMap {  }
    }

}