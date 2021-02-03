package com.fiuba.bookbnb.ui.fragments.home

import android.util.Log
import com.fiuba.bookbnb.domain.publish.PublishData
import com.fiuba.bookbnb.networking.NetworkViewModel
import com.fiuba.bookbnb.ui.navigation.NavigationManager
import retrofit2.Call
import retrofit2.Response

class SearchViewModel : NetworkViewModel<List<PublishData>>() {

    var searchResults : List<PublishData>? = null

    override fun execute(call: Call<List<PublishData>>) {
        Log.i(TAG, "Searching...")
        executeCallback(call)
    }

    override fun onSuccessful(response: Response<List<PublishData>>) {
        Log.i(TAG, "Search successfully")
        searchResults = response.body()
        NavigationManager.moveForward(SearchFragmentDirections.actionSearchFragmentToSearchResultsFragment())
    }

    override fun onFailure(response: Response<List<PublishData>>) {
        Log.e(TAG, "Search error")
    }

    companion object {
        private const val TAG = "SEARCH"
    }

}