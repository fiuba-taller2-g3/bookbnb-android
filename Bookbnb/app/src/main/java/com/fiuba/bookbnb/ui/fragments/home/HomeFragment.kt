package com.fiuba.bookbnb.ui.fragments.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.publish.PublishResponse
import com.fiuba.bookbnb.networking.NetworkModule
import com.fiuba.bookbnb.ui.fragments.BaseFragment
import com.fiuba.bookbnb.ui.fragments.footerbar.FooterBarButtons
import com.fiuba.bookbnb.ui.recyclerView.ContextMenuAdapter
import com.fiuba.bookbnb.user.UserManager
import kotlinx.android.synthetic.main.bookbnb_button.*
import kotlinx.android.synthetic.main.bookbnb_context_menu_dialog_fragment.view.*
import kotlinx.android.synthetic.main.bookbnb_home_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : BaseFragment(R.layout.bookbnb_home_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkpoint3Fix()
        publish_list.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun checkpoint3Fix() {
        button.text = "Actualizar lista"
        button.setOnClickListener {
            NetworkModule.buildRetrofitClient().getPosts(UserManager.getUserInfo().getToken()).enqueue(object : Callback<List<PublishResponse>> {
                override fun onResponse(call: Call<List<PublishResponse>>, response: Response<List<PublishResponse>>) {
                    response.body()?.let { publish_list.adapter = StayPostsAdapter(it) }
                }

                override fun onFailure(call: Call<List<PublishResponse>>, t: Throwable) {
                    // Do Nothing
                }
            })
        }
    }

    override val shouldShowToolbar: Boolean
        get() = false

    override val shouldShowFooterBarMenu: Boolean
        get() = true

    override fun onResume() {
        super.onResume()
        sharedViewModel.setOption(FooterBarButtons.SEARCH)
    }

}