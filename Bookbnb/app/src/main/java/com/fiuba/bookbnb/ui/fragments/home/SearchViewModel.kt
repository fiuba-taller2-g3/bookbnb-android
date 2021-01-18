package com.fiuba.bookbnb.ui.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fiuba.bookbnb.repository.LoadingStatus

class SearchViewModel : ViewModel() {

    private val mutableStatusLiveData = MutableLiveData<LoadingStatus>()
    val statusLiveData : LiveData<LoadingStatus>
        get() = mutableStatusLiveData

    fun search() {
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


}