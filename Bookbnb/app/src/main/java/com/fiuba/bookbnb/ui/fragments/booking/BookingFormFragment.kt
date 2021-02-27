package com.fiuba.bookbnb.ui.fragments.booking

import androidx.navigation.fragment.navArgs
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.booking.BookingRequest
import com.fiuba.bookbnb.domain.misc.MsgResponse
import com.fiuba.bookbnb.forms.FormInputData
import com.fiuba.bookbnb.forms.FormInputType
import com.fiuba.bookbnb.networking.NetworkModule
import com.fiuba.bookbnb.ui.fragments.form.FormWithNetworkStatusFragment
import com.fiuba.bookbnb.user.UserManager
import com.fiuba.bookbnb.utils.DateUtils
import retrofit2.Call

class BookingFormFragment : FormWithNetworkStatusFragment<BookingViewModel, MsgResponse>() {

    private val navArguments by navArgs<BookingFormFragmentArgs>()
    private val publishData by lazy { navArguments.publishData }

    override fun getTitleTextRes(): Int = R.string.booking_title_text
    override fun getSubtitleTextRes(): Int = R.string.booking_subtitle_text
    override fun getButtonTextRes(): Int = R.string.booking_button_text

    override fun shouldClearInputsWhenBackPressed(): Boolean = false

    override fun getInputList(): List<FormInputData> {
        val startDate = DateUtils.getDateOutputFormat().parse(publishData.availabilityDates.startDate)
        val endDate = DateUtils.getDateOutputFormat().parse(publishData.availabilityDates.endDate)

        return listOf(
            FormInputData(FormInputType.START_DATE, DateUtils.getDateOutputFormat().format(startDate!!)),
            FormInputData(FormInputType.END_DATE, DateUtils.getDateOutputFormat().format(endDate!!))
        )
    }

    private fun getBookingRequest() = with(formViewModel) {
        BookingRequest(
            UserManager.getUserInfo().getUserId(),
            publishData.id!!,
            getContentFromItem(FormInputType.START_DATE),
            getContentFromItem(FormInputType.END_DATE),
            UserManager.getUserInfo().getUserData().walletId
        )
    }

    override fun getViewModelClass(): Class<BookingViewModel> = BookingViewModel::class.java

    override fun call(): Call<MsgResponse> = NetworkModule.buildRetrofitClient().purchase(getBookingRequest(), UserManager.getUserInfo().getToken())

}