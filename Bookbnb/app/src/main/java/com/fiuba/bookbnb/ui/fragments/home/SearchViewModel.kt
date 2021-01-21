package com.fiuba.bookbnb.ui.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fiuba.bookbnb.domain.publish.PublishData
import com.fiuba.bookbnb.networking.NetworkModule
import com.fiuba.bookbnb.networking.NetworkViewModel
import com.fiuba.bookbnb.repository.LoadingStatus
import com.fiuba.bookbnb.user.UserManager
import org.apache.commons.lang3.StringUtils
import retrofit2.Call
import retrofit2.Response

class SearchViewModel : NetworkViewModel<List<PublishData>>() {

    private val mutableStatusLiveData = MutableLiveData<LoadingStatus>()
    val statusLiveData : LiveData<LoadingStatus>
        get() = mutableStatusLiveData

    override fun onSuccessful(response: Response<List<PublishData>>) {
        TODO("Not yet implemented")
    }

    override fun onFailure(response: Response<List<PublishData>>) {
        TODO("Not yet implemented")
    }

    override fun execute(call: Call<List<PublishData>>) {
        TODO("Not yet implemented")
    }

}