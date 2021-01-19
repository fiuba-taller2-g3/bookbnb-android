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

    fun search() {
        NetworkModule.buildRetrofitClient().getPosts(null, null, null, null, null, UserManager.getUserInfo().getUserId(), UserManager.getUserInfo().getToken())
//        NetworkModule.buildRetrofitClient().getPosts(UserManager.getUserInfo().getToken()).enqueue(object :
//            Callback<List<PublishResponse>> {
//            override fun onResponse(call: Call<List<PublishResponse>>, response: Response<List<PublishResponse>>) {
//
//            }
//
//            override fun onFailure(call: Call<List<PublishResponse>>, t: Throwable) {
//                // Do Nothing
//            }
//        })
    }

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