package com.fiuba.bookbnb.ui.fragments.publish

import android.util.Log
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.publish.PublishResponse
import com.fiuba.bookbnb.networking.NetworkViewModel
import com.fiuba.bookbnb.ui.fragments.register.RegisterFragmentDirections
import com.fiuba.bookbnb.ui.navigation.NavigationManager
import retrofit2.Call
import retrofit2.Response

class PublishViewModel : NetworkViewModel<PublishResponse>() {

    override fun execute(call: Call<PublishResponse>) {
        Log.i(TAG, "Publishing...")
        executeCallback(call)
    }

    // Es conveniente que los textos almacenados en msgResponse vengan del backend

    override fun onSuccessful(response: Response<PublishResponse>) {
        msgResponse = "Publicación satisfactoria"
        Log.i(TAG, "Post successfully")
        NavigationManager.moveForwardWithPopUpTo(
            PublishStayFinishStepFormFragmentDirections.actionPublishStayFinishStepFormFragmentToConfirmationFragment(
                R.drawable.ic_checked, msgResponse, R.string.publish_confirmed_description), R.id.profileMenuFragment)
    }

    override fun onFailure(response: Response<PublishResponse>) {
        msgResponse = "Se produjo un error al realizar la publicación"
        Log.e(TAG, "Publish error: $msgResponse")
    }

    companion object {
        private const val TAG = "PUBLISH"
    }

}