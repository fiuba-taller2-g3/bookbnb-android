package com.fiuba.bookbnb.ui.fragments.publish.publishView

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.ui.fragments.BaseFragment
import kotlinx.android.synthetic.main.bookbnb_publish_view_fragment.*

class PublishViewFragment : BaseFragment(R.layout.bookbnb_publish_view_fragment) {

    private val navArguments by navArgs<PublishViewFragmentArgs>()
    private val publishData by lazy { navArguments.publishData }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view_pager.adapter = ViewPagerAdapter(publishData.images)
    }

    override fun shouldClearInputsWhenBackPressed(): Boolean = false

}