package com.fiuba.bookbnb.ui.fragments.myPosts

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
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
            isVisible = loadingMyPostsViewModel.postsResults!!.isNotEmpty()
        }

        setEmptyPostText(R.string.my_posts_empty_posts_text)

        loadTabListeners()
        setTabSelect(my_posts_tab)
    }

    private fun setEmptyPostText(stringRes: Int) {
        empty_posts_text.isVisible = !posts_list.isVisible
        empty_posts_text.text = getString(stringRes)
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
            posts_list.isVisible = loadingMyPostsViewModel.postsResults!!.isNotEmpty()
            setEmptyPostText(R.string.my_posts_empty_posts_text)
            setTabSelect(my_posts_tab)
            setTabDeselect(bookings_tab)
        }

        bookings_tab.setOnClickListener {
            posts_list.adapter = MyBookingsAdapter(bookingsPendingData)
            posts_list.isVisible = loadingHostBookingsViewModel.bookingResults!!.isNotEmpty()
            setEmptyPostText(R.string.my_posts_empty_bookings_text)
            setTabSelect(bookings_tab)
            setTabDeselect(my_posts_tab)
        }
    }

    private fun setTabSelect(tabView: TextView) {
        tabView.setBackgroundResource(R.color.colorRed)
        tabView.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorWhite))
    }

    private fun setTabDeselect(tabView: TextView) {
        tabView.setBackgroundResource(0)
        tabView.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorTitle))
    }

}