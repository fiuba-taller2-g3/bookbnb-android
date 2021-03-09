package com.fiuba.bookbnb.ui.fragments.publish.publishView

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import androidx.viewpager.widget.ViewPager
import com.fiuba.bookbnb.FirebaseChat
import com.fiuba.bookbnb.GuestAndHost
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.ui.fragments.BaseFragment
import com.fiuba.bookbnb.ui.fragments.dialogs.ServicesListDialogFragmentDirections
import com.fiuba.bookbnb.ui.fragments.form.services.ServicesItemBuilder
import com.fiuba.bookbnb.ui.fragments.profile.ProfileMenuFragmentDirections
import com.fiuba.bookbnb.ui.navigation.NavigationManager
import com.fiuba.bookbnb.user.UserManager
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.bookbnb_chat.*
import kotlinx.android.synthetic.main.bookbnb_publish_view_fragment.*
import java.util.*

class PublishViewFragment : BaseFragment(R.layout.bookbnb_publish_view_fragment), OnMapReadyCallback {

    private val navArguments by navArgs<PublishViewFragmentArgs>()
    private val publishData by lazy { navArguments.publishData }
    private val userData by lazy { navArguments.userData }

    private val servicesItemBuilder by lazy { ServicesItemBuilder(requireContext()) }
    private lateinit var mMap : GoogleMap

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
        host_name.setOnClickListener {
            NavigationManager.moveForward(PublishViewFragmentDirections.actionPublishViewFragmentToViewProfileFragmentFromPublish(userData))
        }
        btn_send_message.text = "Enviar mensaje privado al anfitrión"
        stay_type.text = "${publishData.type.capitalize(Locale.ROOT)} \u2022 ${publishData.availabilityType}"
        stay_summary.text = buildStaySummary()
        stay_description.text = publishData.description
        buildBedsDistribution()
        buildServices()
        setUpMap()
        location_description.text = publish_location.text
        price.text = getString(R.string.stay_post_price_text, publishData.price)
        btn_booking.setOnClickListener {
            NavigationManager.moveForward(PublishViewFragmentDirections.actionPublishViewFragmentToBookingSelectDateFormFragment(publishData))
        }
        GuestAndHost.setGuest(UserManager.getUserInfo().getUserId())
        GuestAndHost.setHost(publishData.userId)
        btn_send_message.setOnClickListener {
            GuestAndHost.setGuest(UserManager.getUserInfo().getUserId())
            GuestAndHost.setHost(publishData.userId)
            NavigationManager.moveForward(PublishViewFragmentDirections.startChat(FirebaseChat())) // TODO agregar firebase chat
        }
    }

    private fun buildBedsDistribution() {
        if (publishData.bedsDistribution.isNotEmpty()) {
            publishData.bedsDistribution.forEach { bedDistribution ->
                stay_beds_distribution_list.addView(PublishViewBedDistributionItem(requireContext(), bedDistribution))
            }
        } else stay_beds_distribution_list.addView(PublishViewEmptyItem(requireContext(), getString(R.string.publish_view_bed_distribution_empty_text)))
    }

    private fun buildServices() {
        val servicesList = publishData.services.filterValues { it }.keys

        if (servicesList.isNotEmpty()) {
            servicesList.take(SERVICES_LIST_MAX_LIMIT).forEach { service ->
                val serviceData = servicesItemBuilder.build(service)
                stay_services_list.addView(PublishViewServiceTextItem(requireContext(), serviceData.label))
            }

            btn_services.text = getString(R.string.publish_view_services_button_text, servicesList.size)
            btn_services.setOnClickListener {
                NavigationManager.showDialog { ServicesListDialogFragmentDirections.showServicesListDialog(publishData) }
            }
        } else stay_services_list.addView(PublishViewEmptyItem(requireContext(), getString(R.string.publish_view_services_empty_text)))

        btn_services.isVisible = servicesList.isNotEmpty()
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

    private fun setUpMap() {
        val supportMapFragment = childFragmentManager.findFragmentById(R.id.stay_map) as SupportMapFragment
        supportMapFragment.getMapAsync(this)
    }

    private fun isSingular(number: Int) = number == 1

    override fun shouldClearInputsWhenBackPressed(): Boolean = false

    override fun onMapReady(map: GoogleMap) {
        mMap = map
        mMap.uiSettings.setAllGesturesEnabled(false)
        loadLatLng()
    }

    private fun loadLatLng() {
        val latLng = LatLng(publishData.location.lat!!.toDouble(), publishData.location.lng!!.toDouble())
        val cameraPosition: CameraPosition = CameraPosition.Builder().target(latLng).zoom(17.0f).build()
        val cameraUpdate: CameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition)
        mMap.addMarker(MarkerOptions().position(latLng))
        mMap.moveCamera(cameraUpdate)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity?.supportFragmentManager?.apply {
            findFragmentById(R.id.stay_map)?.let { mapFragment ->
                beginTransaction().apply {
                    remove(mapFragment)
                    commit()
                }
            }
        }
    }

    companion object {
        private const val SERVICES_LIST_MAX_LIMIT = 5
    }

}