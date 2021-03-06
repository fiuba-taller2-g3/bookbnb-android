package com.fiuba.bookbnb.ui.fragments.booking

import android.os.Bundle
import android.view.View
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
import kotlinx.android.synthetic.main.bookbnb_booking_info_field.*
import retrofit2.Call
import java.time.Duration
import java.util.concurrent.TimeUnit
import kotlin.math.abs

class BookingConfirmFormFragment : FormWithNetworkStatusFragment<BookingViewModel, MsgResponse>() {

    private val navArguments by navArgs<BookingConfirmFormFragmentArgs>()
    private val publishData by lazy { navArguments.publishData }

    override fun getTitleTextRes(): Int = R.string.booking_confirm_title_text
    override fun getSubtitleTextRes(): Int = R.string.booking_confirm_subtitle_text
    override fun getButtonTextRes(): Int = R.string.booking_button_text

    override fun shouldClearInputsWhenBackPressed(): Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val startDate = DateUtils.getDateOutputFormat().parse(formViewModel.getContentFromItem(FormInputType.BOOKING_START_DATE))
        val endDate = DateUtils.getDateOutputFormat().parse(formViewModel.getContentFromItem(FormInputType.BOOKING_END_DATE))

        val diffInMilliseconds = abs(endDate!!.time - startDate!!.time)
        val nightCounts = TimeUnit.DAYS.convert(diffInMilliseconds, TimeUnit.MILLISECONDS)
        val totalPrice = publishData.price.toDouble() * nightCounts

        init_date_text.text = getString(R.string.purchase_init_date, DateUtils.getDateFormat().format(startDate))
        finish_date_text.text = getString(R.string.purchase_finish_date, DateUtils.getDateFormat().format(endDate))
        night_count_text.text = getString(R.string.purchase_night_counts, nightCounts)
        total_price_text.text = getString(R.string.purchase_total_price, totalPrice.toString())
    }

    override fun getInputList(): List<FormInputData> {
        return listOf(
            FormInputData(FormInputType.BOOKING_INFO)
        )
    }

    private fun getBookingRequest() = with(formViewModel) {
        BookingRequest(
            UserManager.getUserInfo().getUserId(),
            publishData.id!!,
            getContentFromItem(FormInputType.BOOKING_START_DATE),
            getContentFromItem(FormInputType.BOOKING_END_DATE),
            UserManager.getUserInfo().getUserData().walletId
        )
    }

    override fun getViewModelClass(): Class<BookingViewModel> = BookingViewModel::class.java

    override fun call(): Call<MsgResponse> = NetworkModule.buildRetrofitClient().purchase(getBookingRequest(), UserManager.getUserInfo().getToken())

}