package com.fiuba.bookbnb.ui.fragments.home

import android.app.AlertDialog
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.publish.PublishData
import com.fiuba.bookbnb.forms.FormInputData
import com.fiuba.bookbnb.forms.FormInputType
import com.fiuba.bookbnb.networking.NetworkModule
import com.fiuba.bookbnb.repository.LoadingStatus
import com.fiuba.bookbnb.ui.fragments.form.FormWithNetworkStatusFragment
import com.fiuba.bookbnb.user.UserManager
import com.fiuba.bookbnb.utils.DateUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import java.io.IOException
import java.util.*

class SearchFragment : FormWithNetworkStatusFragment<SearchViewModel, List<PublishData>>() {

    private val searchResultsViewModel by activityViewModels<SearchResultsViewModel>()

    private val checkLocationMutable = MutableLiveData<LoadingStatus>()
    private val checkLocation : LiveData<LoadingStatus>
        get() = checkLocationMutable

    override val isNetworkRequired: Boolean
        get() = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkLocationMutable.value = LoadingStatus.HIDE
        checkLocation.observe(viewLifecycleOwner) { status ->
            when(status) {
                LoadingStatus.LOADING -> showLoading(true)
                LoadingStatus.FAILURE -> {
                    AlertDialog.Builder(context).setMessage(getString(R.string.invalid_location_text)).show()
                    checkLocationMutable.value = LoadingStatus.HIDE
                }
                LoadingStatus.SUCCESS -> {
                    super.setActionEventButton()
                }
                else -> { showLoading(false) }
            }
        }
    }

    override fun onSuccessStatus(cleanInputs: Boolean) {
        networkViewModel.searchResults?.let{ searchResultsViewModel.setResults(it) }
        super.onSuccessStatus(false)
    }

    override fun onDestroy() {
        checkLocationMutable.value = LoadingStatus.HIDE
        super.onDestroy()
    }

    override fun getTitleTextRes(): Int = R.string.search_title
    override fun getSubtitleTextRes(): Int = R.string.search_subtitle
    override fun getButtonTextRes(): Int = R.string.search_button

    override fun getInputList(): List<FormInputData> {
        return listOf(
            FormInputData(FormInputType.STAY_TYPE_SEARCH),
            FormInputData(FormInputType.SEARCH_CITY),
            FormInputData(FormInputType.SEARCH_START_DATE),
            FormInputData(FormInputType.SEARCH_END_DATE),
            FormInputData(FormInputType.MIN_PRICE),
            FormInputData(FormInputType.MAX_PRICE)
        )
    }

    override fun setActionEventButton() {
        if (isSearchFormValidated()) {
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                checkLocationMutable.postValue(LoadingStatus.LOADING)
                if (!isAddressLocationValid()) checkLocationMutable.postValue(LoadingStatus.FAILURE)
                else checkLocationMutable.postValue(LoadingStatus.SUCCESS)
            }
        }
    }

    private fun isSearchFormValidated() : Boolean {
        if (!isPriceRangeValidated()) return false
        if (!isDateRangeValidated()) return false

        return true
    }

    private fun isPriceRangeValidated() : Boolean {
        val minPrice = formViewModel.getContentFromItem(FormInputType.MIN_PRICE)
        val maxPrice = formViewModel.getContentFromItem(FormInputType.MAX_PRICE)
        if (minPrice.isNotEmpty() && maxPrice.isNotEmpty()) {
            return (minPrice.toInt() < maxPrice.toInt()).also { isMaxPriceGreaterThanMinPrice ->
                if (!isMaxPriceGreaterThanMinPrice) showMessage("El precio máximo debe ser mayor al precio mínimo. Revise y corrija los precios.")
            }
        }

        return true
    }

    private fun isDateRangeValidated() : Boolean {
        val initialDateContentField = formViewModel.getContentFromItem(FormInputType.SEARCH_START_DATE)
        val endDateContentField = formViewModel.getContentFromItem(FormInputType.SEARCH_END_DATE)
        if (initialDateContentField.isNotEmpty() && endDateContentField.isNotEmpty()) {
            val initialDate = DateUtils.getDateOutputFormat().parse(initialDateContentField)
            val endDate = DateUtils.getDateOutputFormat().parse(endDateContentField)
            return endDate.after(initialDate).also { isEndDateAfterOfInitialDate ->
                if (!isEndDateAfterOfInitialDate) showMessage("La fecha final es menor que la inicial. Revise y corrija las fechas.")
            }
        }

        if (initialDateContentField.isEmpty() && endDateContentField.isNotEmpty()) {
            showMessage("Introduzca la fecha inicial.")
            return false
        }

        if (initialDateContentField.isNotEmpty() && endDateContentField.isEmpty()) {
            showMessage("Introduzca la fecha final.")
            return false
        }

        return true
    }

    private fun showMessage(msg: String) = AlertDialog.Builder(context).setMessage(msg).show()

    private fun getInputContent(formInputType: FormInputType) : String? {
        val content = formViewModel.getContentFromItem(formInputType)
        return if (content.isNotEmpty()) content else null
    }

    private fun isAddressLocationValid(): Boolean {
        val city = formViewModel.getContentFromItem(FormInputType.SEARCH_CITY)

        if (city.isNotEmpty()) {
            return setLocationFromAddress(city)?.let {
                formViewModel.locationInfo = it
                true
            } ?: false
        }

        formViewModel.clearLocationInfo()
        return true
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

    override fun getViewModelClass(): Class<SearchViewModel> = SearchViewModel::class.java

    override fun call(): Call<List<PublishData>> = NetworkModule.buildRetrofitClient().getPosts(
        formViewModel.getContentFromItem(FormInputType.STAY_TYPE_SEARCH).toLowerCase(Locale.ROOT),
        formViewModel.locationInfo?.latitude?.toString(),
        formViewModel.locationInfo?.longitude?.toString(),
        getInputContent(FormInputType.MIN_PRICE),
        getInputContent(FormInputType.MAX_PRICE),
        getInputContent(FormInputType.SEARCH_START_DATE),
        getInputContent(FormInputType.SEARCH_END_DATE),
        UserManager.getUserInfo().getUserId(),
        UserManager.getUserInfo().getToken())

    companion object {
        private const val MAX_RESULTS = 5
    }

}