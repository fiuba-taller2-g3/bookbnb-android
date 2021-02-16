package com.fiuba.bookbnb.ui.fragments.publish.publishView

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import androidx.viewpager.widget.ViewPager
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.ui.fragments.BaseFragment
import kotlinx.android.synthetic.main.bookbnb_publish_img_item.view.*
import kotlinx.android.synthetic.main.bookbnb_publish_view_fragment.*
import java.util.*

class PublishViewFragment : BaseFragment(R.layout.bookbnb_publish_view_fragment) {

    private val navArguments by navArgs<PublishViewFragmentArgs>()
    private val publishData by lazy { navArguments.publishData }
    private val userData by lazy { navArguments.userData }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view_pager.adapter = ViewPagerAdapter(publishData.images)
        if (publishData.images.isEmpty()) view_pager.setBackgroundResource(R.drawable.ic_photoimgdefault)
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

        publish_title.text = publishData.title
        publish_location.text = with(publishData.location) { "${city}, $country" }
        host_name.text = getString(R.string.publish_view_host_name, "${userData.name} ${userData.surname}")
        stay_type.text = "${publishData.type} \u2022 ${publishData.availabilityType}"
        stay_summary.text = buildStaySummary()
        stay_description.text = publishData.description
        buildBedsDistribution()
        buildServices()
    }

    private fun buildBedsDistribution() {
        if (publishData.bedsDistribution.isNotEmpty()) {
            publishData.bedsDistribution.forEach { bedDistribution ->
                stay_beds_distribution_list.addView(PublishViewBedDistributionItem(requireContext(), bedDistribution))
            }
        } else stay_beds_distribution_list.addView(PublishViewBedDistributionEmptyItem(requireContext()))
    }

    private fun buildServices() {
        publishData.services
        publishData.security
        publishData.installations
    }

    @SuppressLint("SetTextI18n")
    private fun setCurrentPositionImageText(position: Int) {
        img_number.text = "${position + 1}/${publishData.images.size}"
        img_number.isVisible = publishData.images.isNotEmpty()
    }

    private fun buildStaySummary(): String {
        val staySummary = StringJoiner("  •  ")
        staySummary.apply {
            add(buildGuestText())
            add(buildBedroomText())
            add(buildBedsText())
            add(buildBathroomText())
        }

        return staySummary.toString()
    }

    private fun buildGuestText() = getString(
        if (isSingular(publishData.guests.toInt())) R.string.publish_view_guest_singular_label else R.string.publish_view_guest_plural_label, publishData.guests
    )

    private fun buildBedsText() = getString(
        if (isSingular(publishData.beds.toInt())) R.string.publish_view_beds_singular_label else R.string.publish_view_beds_plural_label, publishData.beds
    )

    private fun buildBathroomText() = getString(
        if (isSingular(publishData.bathrooms.toInt())) R.string.publish_view_bathroom_singular_label else R.string.publish_view_bathroom_plural_label, publishData.bathrooms
    )

    private fun buildBedroomText() = getString(
        if (isSingular(publishData.bedrooms.toInt())) R.string.publish_view_bedroom_singular_label else R.string.publish_view_bedroom_plural_label, publishData.bedrooms
    )

    private fun isSingular(number: Int) = number == 1

    override fun shouldClearInputsWhenBackPressed(): Boolean = false

}