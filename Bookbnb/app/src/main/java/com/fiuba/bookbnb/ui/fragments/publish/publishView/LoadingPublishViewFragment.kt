package com.fiuba.bookbnb.ui.fragments.publish.publishView

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.user.UserData
import com.fiuba.bookbnb.networking.NetworkModule
import com.fiuba.bookbnb.ui.fragments.BaseFragment
import com.fiuba.bookbnb.user.UserManager
import retrofit2.Call

class LoadingPublishViewFragment : BaseFragment(R.layout.bookbnb_loading_fragment) {

    private val loadingPublishViewModel by viewModels<LoadingPublishViewModel>()
    private val navArguments by navArgs<LoadingPublishViewFragmentArgs>()
    private val publishData by lazy { navArguments.publishData }

    override val shouldShowToolbar: Boolean
        get() = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val token = UserManager.getUserInfo().getToken()
        loadingPublishViewModel.loadPublishView(NetworkModule.buildRetrofitClient().getUser(publishData.userId, token).also {
            networkManagerViewModel.setCancelCurrentRunningCallReference{ cancelRequest(it) } }, publishData)
    }

    private fun cancelRequest(call: Call<UserData>) {
        call.cancel()
        loadingPublishViewModel.hideLoading()
    }

    override fun shouldClearInputsWhenBackPressed(): Boolean = false

}