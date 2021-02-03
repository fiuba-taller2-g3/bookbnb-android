package com.fiuba.bookbnb.ui.fragments.publish

import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.publish.*
import com.fiuba.bookbnb.forms.FormInputData
import com.fiuba.bookbnb.forms.FormInputType.*
import com.fiuba.bookbnb.networking.NetworkModule
import com.fiuba.bookbnb.ui.fragments.form.FormWithNetworkStatusFragment
import com.fiuba.bookbnb.ui.fragments.form.services.Services.*
import com.fiuba.bookbnb.user.UserManager
import com.fiuba.bookbnb.utils.DateUtils
import retrofit2.Call
import java.util.*
import kotlin.collections.ArrayList

class PublishStayFinishStepFormFragment : FormWithNetworkStatusFragment<PublishViewModel, PublishData>() {

    override fun getTitleTextRes(): Int = R.string.publish_ready_to_finish_title
    override fun getSubtitleTextRes(): Int = R.string.publish_ready_to_finish_subtitle
    override fun getButtonTextRes(): Int = R.string.publish_text_button

    override fun getInputList(): List<FormInputData> = listOf()

    override fun shouldClearInputsWhenBackPressed(): Boolean = false

    private fun getRequest(): PublishData {
        val imagesUrl = ArrayList<String>().also { imageList ->
            formViewModel.photosUrls.forEach { imageList.add(it.imgUrl) }
        }

        val bedsDistribution = ArrayList<BedDistribution>().also { imageList ->
            for (i in 0 until formViewModel.getContentFromItem(BEDROOM_QUANTITY).toInt()) {
                imageList.add(formViewModel.getBedDistributionItem(i).bedDistribution)
            }
        }

        return with(formViewModel) {
            PublishData(
                null,
                false,
                UserManager.getUserInfo().getUserId(),
                getContentFromItem(STAY_TITLE),
                getContentFromItem(STAY_DESCRIPTION),
                AvailabilityDates(getContentFromItem(START_DATE), getContentFromItem(END_DATE)),
                getContentFromItem(PRICE),
                imagesUrl,
                getContentFromItem(AVAILABILITY_TYPE),
                getContentFromItem(STAY_TYPE),
                getContentFromItem(GUEST_QUANTITY),
                getContentFromItem(BED_QUANTITY),
                getContentFromItem(BEDROOM_QUANTITY),
                getContentFromItem(BATHROOM_QUANTITY),
                bedsDistribution,
                Location(getContentFromItem(COUNTRY),
                    getContentFromItem(CITY),
                    getContentFromItem(ADDRESS),
                    locationInfo?.postalCode,
                    getContentFromItem(APARTMENT),
                    formViewModel.locationInfo?.latitude.toString(),
                    formViewModel.locationInfo?.longitude.toString()),
                Services(getValueFromService(BASIC_ELEMENTS),
                    getValueFromService(WIFI),
                    getValueFromService(SHAMPOO),
                    getValueFromService(TV),
                    getValueFromService(CABLE_TV),
                    getValueFromService(HEATING),
                    getValueFromService(AIR_CONDITIONING),
                    getValueFromService(FANS),
                    getValueFromService(DESK),
                    getValueFromService(BREAKFAST),
                    getValueFromService(FIREPLACE),
                    getValueFromService(GRIDDLE),
                    getValueFromService(HAIR_DRYER),
                    getValueFromService(PETS),
                    getValueFromService(PRIVATE_ENTRANCE),
                    getValueFromService(FRIDGE),
                    getValueFromService(OVEN),
                    getValueFromService(MICROWAVE),
                    getValueFromService(COFFEE_MAKER)),
                Security(getValueFromService(KIT),
                    getValueFromService(SMOKE_DETECTOR),
                    getValueFromService(FIRE_EXTINGUISHER),
                    getValueFromService(SAFETY_INSTRUCTIONS_SHEET),
                    getValueFromService(LOOK_BEDROOM_DOOR)),
                Installations(getValueFromService(PRIVATE_LOUNGE),
                    getValueFromService(SWIMMING_POOL),
                    getValueFromService(KITCHEN),
                    getValueFromService(WASHING_MACHINE),
                    getValueFromService(PARKING),
                    getValueFromService(LIFT),
                    getValueFromService(JACUZZI),
                    getValueFromService(GYM),
                    getValueFromService(COURTYARD),
                    getValueFromService(TERRACE)),
                DateUtils.getDateOutputFormat().format(Date().time),
                UserManager.getUserInfo().getUserData().walletId)
        }
    }

    override fun getViewModelClass(): Class<PublishViewModel> = PublishViewModel::class.java
    override fun call(): Call<PublishData> = NetworkModule.buildRetrofitClient().publish(getRequest(), UserManager.getUserInfo().getToken())

}