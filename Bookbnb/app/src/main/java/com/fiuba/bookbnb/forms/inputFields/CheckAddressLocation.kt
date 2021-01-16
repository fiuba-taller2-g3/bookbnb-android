package com.fiuba.bookbnb.forms.inputFields

import android.content.Context
import android.location.Geocoder
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fiuba.bookbnb.repository.LoadingStatus
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.bookbnb_map_location_input_field.view.*
import org.apache.commons.lang3.StringUtils
import java.io.IOException

class CheckAddressLocation(context: Context) : AbstractInputFieldItem(context) {

    private val mutableStatusLiveData = MutableLiveData<LoadingStatus>()
    private val statusLiveData : LiveData<LoadingStatus>
        get() = mutableStatusLiveData

    private var isValidated = false

    fun setLocationFromAddress(strAddress: String): LatLng? {
        try {
            mutableStatusLiveData.value = LoadingStatus.LOADING
            val address = Geocoder(context).getFromLocationName(strAddress, MAX_RESULTS)
            return if (address != null && address.isNotEmpty()) {
                val location = address.first()
                mutableStatusLiveData.value = LoadingStatus.SUCCESS
                LatLng(location.latitude, location.longitude)
            } else {
                mutableStatusLiveData.value = LoadingStatus.ERROR
                null
            }
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        mutableStatusLiveData.value = LoadingStatus.ERROR
        return null
    }

    private fun checkValidation() {
        statusLiveData.observeForever { status ->
            when(status) {
                LoadingStatus.SUCCESS -> map.isVisible = true
                else -> map.isVisible = false
            }
        }
    }

    override fun enableInput() {
        // Do Nothing
    }

    override fun disableInput() {
        // Do Nothing
    }

    override fun isValidated(): Boolean = true

    companion object {
        private const val MAX_RESULTS = 5
    }

}