package com.fiuba.bookbnb.ui.fragments.myPosts

import android.util.Log
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.publish.PublishData
import com.fiuba.bookbnb.networking.NetworkViewModel
import com.fiuba.bookbnb.ui.navigation.NavigationManager
import retrofit2.Call
import retrofit2.Response

class LoadingMyPostsViewModel : NetworkViewModel<List<PublishData>>()  {

    var searchResults : List<PublishData>? = null

    override fun execute(call: Call<List<PublishData>>) {
        Log.i(TAG, "Getting posts...")
        executeCallback(call)
    }

    override fun onSuccessful(response: Response<List<PublishData>>) {
        Log.i(TAG, "Posts loaded successfully")
        searchResults = response.body()
        response.body()?.let {
            NavigationManager.moveForwardWithPopUpTo(MyPostsLoadingFragmentDirections.actionMyPostsLoadingFragmentToMyPostsFragment(), R.id.profileMenuFragment)
        }
    }

    override fun onFailure(response: Response<List<PublishData>>) {
        // Do nothing
    }

    companion object {
        const val TAG = "MY_POSTS"
    }

}