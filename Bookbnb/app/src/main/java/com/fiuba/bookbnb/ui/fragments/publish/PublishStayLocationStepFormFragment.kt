package com.fiuba.bookbnb.ui.fragments.publish

import android.app.AlertDialog
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.View
import androidx.core.view.forEach
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.forms.FormInputData
import com.fiuba.bookbnb.forms.FormInputType
import com.fiuba.bookbnb.forms.InputFieldModule
import com.fiuba.bookbnb.repository.LoadingStatus
import com.fiuba.bookbnb.ui.fragments.form.FormFragment
import com.fiuba.bookbnb.ui.navigation.NavigationManager
import kotlinx.android.synthetic.main.bookbnb_button.*
import kotlinx.android.synthetic.main.bookbnb_form_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class PublishStayLocationStepFormFragment : FormFragment() {

    private val loadingStatusMutable = MutableLiveData<LoadingStatus>()
    private val loadingStatus : LiveData<LoadingStatus>
        get() = loadingStatusMutable

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingStatusMutable.value = LoadingStatus.HIDE
        loadingStatus.observe(viewLifecycleOwner) { status ->
            when(status) {
                LoadingStatus.LOADING -> setInputsState(true)
                LoadingStatus.FAILURE -> {
                    AlertDialog.Builder(context).setMessage(getString(R.string.invalid_location_text)).show()
                    loadingStatusMutable.value = LoadingStatus.HIDE
                }
                LoadingStatus.SUCCESS -> {
                    NavigationManager.moveForward(PublishStayLocationStepFormFragmentDirections.actionPublishStayLocationStepFormFragmentToPublishStayMapConfirmationStepFormFragment())
                    loadingStatusMutable.value = LoadingStatus.HIDE
                }
                else -> { setInputsState(false) }
            }
        }
    }

    private fun setInputsState(isLoading: Boolean) {
        button.isEnabled = !isLoading
        setButtonText(if (isLoading) R.string.check_location_button_text else getButtonTextRes())
        input_fields_container.forEach { inputFieldModuleItem ->
            with((inputFieldModuleItem as InputFieldModule).getInputFieldItem()) { if (isLoading) disableInput() else enableInput() }
        }
    }

    override fun getInputList(): List<FormInputData> {
        return listOf(
            FormInputData(FormInputType.ADDRESS),
            FormInputData(FormInputType.CITY),
            FormInputData(FormInputType.COUNTRY),
            FormInputData(FormInputType.APARTMENT)
        )
    }

    override fun shouldClearInputsWhenBackPressed(): Boolean = false

    private fun isAddressLocationValid(): Boolean {
        val address = formViewModel.getContentFromItem(FormInputType.ADDRESS)
        val city = formViewModel.getContentFromItem(FormInputType.CITY)
        val country = formViewModel.getContentFromItem(FormInputType.COUNTRY)
        val location = "{$address, $city, $country}"

        return setLocationFromAddress(location)?.let {
            formViewModel.locationInfo = it
            true
        } ?: false
    }

    private fun setLocationFromAddress(strAddress: String): Address? {
        try {
            val address = Geocoder(context).getFromLocationName(strAddress, MAX_RESULTS)
            return if (address != null && address.isNotEmpty()) {
                address.first()
            } else null
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return null
    }

    override fun setActionEventButton() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            loadingStatusMutable.postValue(LoadingStatus.LOADING)
            if (!isAddressLocationValid()) loadingStatusMutable.postValue(LoadingStatus.FAILURE)
            else loadingStatusMutable.postValue(LoadingStatus.SUCCESS)
        }
    }

    override fun getTitleTextRes(): Int = R.string.publish_location_title
    override fun getSubtitleTextRes(): Int = R.string.publish_location_subtitle
    override fun getButtonTextRes(): Int = R.string.next_button_text

    companion object {
        private const val MAX_RESULTS = 5
    }

}