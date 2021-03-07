package com.fiuba.bookbnb.ui.fragments.myPosts

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.publish.PublishData
import com.fiuba.bookbnb.networking.NetworkModule
import com.fiuba.bookbnb.ui.fragments.BaseFragment
import com.fiuba.bookbnb.user.UserManager
import retrofit2.Call

class MyPostsLoadingFragment : BaseFragment(R.layout.bookbnb_loading_fragment) {

    private val loadingMyPostsViewModel by activityViewModels<LoadingMyPostsViewModel>()

    override val shouldShowToolbar: Boolean
        get() = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingMyPostsViewModel.execute(
            NetworkModule.buildRetrofitClient().getUserPosts(
                UserManager.getUserInfo().getUserId(), UserManager.getUserInfo().getToken()
            ).also { networkManagerViewModel.setCancelCurrentRunningCallReference { cancelRequest(it) } }
        )
    }

    private fun cancelRequest(call: Call<List<PublishData>>) {
        call.cancel()
        loadingMyPostsViewModel.hideLoading()
    }

}