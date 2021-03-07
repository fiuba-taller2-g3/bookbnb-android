package com.fiuba.bookbnb.ui.fragments.myPosts

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.ui.fragments.BaseFragment
import kotlinx.android.synthetic.main.bookbnb_my_posts_fragment.*

class MyPostsFragment : BaseFragment(R.layout.bookbnb_my_posts_fragment) {

    private val loadingMyPostsViewModel by activityViewModels<LoadingMyPostsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        posts_list.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = MyPostsAdapter(loadingMyPostsViewModel.searchResults!!)
        }
    }

}