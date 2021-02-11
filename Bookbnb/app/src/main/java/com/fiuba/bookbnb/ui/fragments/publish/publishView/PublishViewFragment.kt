package com.fiuba.bookbnb.ui.fragments.publish.publishView

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import androidx.viewpager.widget.ViewPager
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.ui.fragments.BaseFragment
import kotlinx.android.synthetic.main.bookbnb_publish_view_fragment.*

class PublishViewFragment : BaseFragment(R.layout.bookbnb_publish_view_fragment) {

    private val navArguments by navArgs<PublishViewFragmentArgs>()
    private val publishData by lazy { navArguments.publishData }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view_pager.adapter = ViewPagerAdapter(publishData.images)
        setCurrentPositionImageText(view_pager.currentItem)

        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                // Do nothing
            }

            override fun onPageSelected(position: Int) =  setCurrentPositionImageText(position)

            override fun onPageScrollStateChanged(state: Int) {
                // Do nothing
            }

        })
    }

    @SuppressLint("SetTextI18n")
    private fun setCurrentPositionImageText(position: Int) {
        img_number.text = "${position + 1}/${publishData.images.size}"
    }

    override fun shouldClearInputsWhenBackPressed(): Boolean = false

}