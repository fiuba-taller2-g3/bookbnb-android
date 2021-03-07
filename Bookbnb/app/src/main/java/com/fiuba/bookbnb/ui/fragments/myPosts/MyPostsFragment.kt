package com.fiuba.bookbnb.ui.fragments.myPosts

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.ui.fragments.BaseFragment
import kotlinx.android.synthetic.main.bookbnb_my_posts_fragment.*

class MyPostsFragment : BaseFragment(R.layout.bookbnb_my_posts_fragment) {

    private val loadingMyPostsViewModel by activityViewModels<LoadingMyPostsViewModel>()
    private val loadingHostBookingsViewModel by activityViewModels<LoadingHostBookingsViewModel>()

    private val bookingsPendingData : ArrayList<BookingPendingData> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        generateBookingsPendingData()

        posts_list.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = MyPostsAdapter(loadingMyPostsViewModel.postsResults!!)
        }

        loadTabListeners()
    }

    private fun generateBookingsPendingData() {
        loadingHostBookingsViewModel.bookingResults!!.flatMap { bookingResponse ->
            loadingMyPostsViewModel.postsResults!!.filter { publishData ->
                bookingResponse.postId == publishData.id
            }.also { publishDataList ->
                publishDataList.firstOrNull()?.let { bookingsPendingData.add(BookingPendingData(it, bookingResponse)) }
            }
        }
    }

    private fun loadTabListeners() {
        my_posts_tab.setOnClickListener {
            posts_list.adapter = MyPostsAdapter(loadingMyPostsViewModel.postsResults!!)
        }

        bookings_tab.setOnClickListener {
            posts_list.adapter = MyBookingsAdapter(bookingsPendingData)
        }
    }

}