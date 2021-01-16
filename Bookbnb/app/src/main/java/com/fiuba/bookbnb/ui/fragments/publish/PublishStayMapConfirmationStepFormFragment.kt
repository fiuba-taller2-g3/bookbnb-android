package com.fiuba.bookbnb.ui.fragments.publish

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.forms.FormInputData
import com.fiuba.bookbnb.forms.FormInputType
import com.fiuba.bookbnb.ui.fragments.form.FormFragment
import com.fiuba.bookbnb.ui.navigation.NavigationManager
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.bookbnb_map_location_input_field.*
import org.apache.commons.lang3.StringUtils

class PublishStayMapConfirmationStepFormFragment : FormFragment(), OnMapReadyCallback {

    private lateinit var mMap : GoogleMap

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadAddressInputs()
        setUpMap()
    }

    private fun loadAddressInputs() {
        with(formViewModel) {
            "${getAddressName()} ${getAddressNumber()}".let {
                if (it.isNotBlank()) {
                    updateInputItem(FormInputData(FormInputType.ADDRESS, it))
                    address_text.text = getString(R.string.publish_address_detail_label, it)
                    address_text.isVisible = true
                }
            }
            locationInfo?.adminArea?.let {
                updateInputItem(FormInputData(FormInputType.CITY, it))
                city_text.text = getString(R.string.publish_city_detail_label, it)
                city_text.isVisible = true
            }
            locationInfo?.countryName?.let {
                updateInputItem(FormInputData(FormInputType.COUNTRY, it))
                country_text.text = getString(R.string.publish_country_detail_label, it)
                country_text.isVisible = true
            }
            locationInfo?.postalCode?.let {
                zip_code_text.text = getString(R.string.publish_zip_code_detail_label, it)
                zip_code_text.isVisible = true
            }
            locationInfo?.subLocality?.let {
                locality_text.text = getString(R.string.publish_locality_detail_label, it)
                locality_text.isVisible = true
            }
            formViewModel.getContentFromItem(FormInputType.APARTMENT).let {
                if (it.isNotEmpty()) {
                    apartment_text.text = getString(R.string.publish_apartment_detail_label, it)
                    apartment_text.isVisible = true
                }
            }
        }
    }

    private fun getAddressName() = formViewModel.locationInfo?.thoroughfare ?: StringUtils.EMPTY

    private fun getAddressNumber() = formViewModel.locationInfo?.subThoroughfare ?: StringUtils.EMPTY

    private fun setUpMap() {
        val supportMapFragment = activity?.supportFragmentManager?.findFragmentById(R.id.map) as SupportMapFragment
        supportMapFragment.getMapAsync(this)
    }

    private fun loadLatLng() {
        formViewModel.locationInfo?.let {
            val latLng = LatLng(it.latitude, it.longitude)
            val cameraPosition: CameraPosition = CameraPosition.Builder().target(latLng).zoom(17.0f).build()
            val cameraUpdate: CameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition)
            mMap.addMarker(MarkerOptions().position(latLng))
            mMap.moveCamera(cameraUpdate)
        }
    }

    override fun onMapReady(map: GoogleMap) {
        mMap = map
        mMap.uiSettings.setAllGesturesEnabled(false)
        loadLatLng()
    }

    override fun getInputList(): List<FormInputData> {
        return listOf(
            FormInputData(FormInputType.LOCATION)
        )
    }

    override fun setActionEventButton() {
        NavigationManager.moveForward(PublishStayMapConfirmationStepFormFragmentDirections.actionPublishStayMapConfirmationStepFormFragmentToPublishStayDescriptionFormFragment())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity?.supportFragmentManager?.apply {
            findFragmentById(R.id.map)?.let { mapFragment ->
                beginTransaction().apply {
                    remove(mapFragment)
                    commit()
                }
            }
        }
    }

    override fun shouldClearInputsWhenBackPressed(): Boolean = false

    override fun getTitleTextRes(): Int = R.string.publish_map_location_title
    override fun getSubtitleTextRes(): Int = R.string.publish_map_location_subtitle
    override fun getButtonTextRes(): Int = R.string.next_button_text

}