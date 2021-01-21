package com.fiuba.bookbnb.ui.fragments.home

import android.util.Log
import com.fiuba.bookbnb.domain.publish.PublishData
import com.fiuba.bookbnb.networking.NetworkViewModel
import retrofit2.Call
import retrofit2.Response

class SearchViewModel : NetworkViewModel<List<PublishData>>() {

    override fun execute(call: Call<List<PublishData>>) {
        Log.i(TAG, "Searching...")
        executeCallback(call)
    }

    override fun onSuccessful(response: Response<List<PublishData>>) {
        Log.i(TAG, "Search successfully")
    }

    override fun onFailure(response: Response<List<PublishData>>) {
        Log.e(TAG, "Search error")
    }

    companion object {
        private const val TAG = "SEARCH"
    }

}