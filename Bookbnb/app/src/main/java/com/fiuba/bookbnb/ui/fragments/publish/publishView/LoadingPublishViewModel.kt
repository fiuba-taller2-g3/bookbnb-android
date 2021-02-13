package com.fiuba.bookbnb.ui.fragments.publish.publishView

import android.util.Log
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.publish.PublishData
import com.fiuba.bookbnb.domain.user.UserData
import com.fiuba.bookbnb.networking.NetworkViewModel
import com.fiuba.bookbnb.ui.navigation.NavigationManager
import retrofit2.Call
import retrofit2.Response

class LoadingPublishViewModel : NetworkViewModel<UserData>() {

    private var publishData : PublishData? = null

    fun loadPublishView(call: Call<UserData>, publishData: PublishData) {
        this.publishData = publishData
        execute(call)
    }

    override fun execute(call: Call<UserData>) {
        Log.i(TAG, "Getting logged user information...")
        executeCallback(call)
    }

    override fun onSuccessful(response: Response<UserData>) {
        Log.i(TAG, "Getting logged successfully")
        response.body()?.let {
            NavigationManager.moveForwardWithPopUpTo(
                LoadingPublishViewFragmentDirections.actionLoadingPublishViewFragmentToPublishViewFragment(publishData!!, it), R.id.searchResultsFragment)
        }
    }

    override fun onFailure(response: Response<UserData>) {
        // Do Nothing
    }

    companion object {
        const val TAG = "USER"
    }

}