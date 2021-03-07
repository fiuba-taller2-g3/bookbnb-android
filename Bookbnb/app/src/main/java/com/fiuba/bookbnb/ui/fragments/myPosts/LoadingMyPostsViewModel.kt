package com.fiuba.bookbnb.ui.fragments.myPosts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fiuba.bookbnb.domain.publish.PublishData
import com.fiuba.bookbnb.networking.NetworkViewModel
import retrofit2.Call
import retrofit2.Response

class LoadingMyPostsViewModel : NetworkViewModel<List<PublishData>>()  {

    var postsResults : List<PublishData>? = null

    private val mutablePostsLoadedSuccessfully = MutableLiveData<Boolean>()
    val postsLoadedSuccessfully: LiveData<Boolean>
        get() = mutablePostsLoadedSuccessfully

    init {
        mutablePostsLoadedSuccessfully.value = false
    }

    override fun execute(call: Call<List<PublishData>>) {
        Log.i(TAG, "Getting posts...")
        executeCallback(call)
    }

    override fun onSuccessful(response: Response<List<PublishData>>) {
        Log.i(TAG, "Posts loaded successfully")
        postsResults = response.body()
        mutablePostsLoadedSuccessfully.value = true
        mutablePostsLoadedSuccessfully.value = false
    }

    override fun onFailure(response: Response<List<PublishData>>) {
        // Do nothing
    }

    companion object {
        const val TAG = "MY_POSTS"
    }

}