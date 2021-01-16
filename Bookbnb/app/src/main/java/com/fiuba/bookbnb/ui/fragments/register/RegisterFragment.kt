package com.fiuba.bookbnb.ui.fragments.register

import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.misc.MsgResponse
import com.fiuba.bookbnb.domain.user.UserData
import com.fiuba.bookbnb.forms.FormInputData
import com.fiuba.bookbnb.forms.FormInputType
import com.fiuba.bookbnb.networking.NetworkModule
import com.fiuba.bookbnb.ui.fragments.form.FormWithNetworkStatusFragment
import org.apache.commons.lang3.StringUtils
import retrofit2.Call

class RegisterFragment : FormWithNetworkStatusFragment<RegisterViewModel, MsgResponse>() {

    override fun getTitleTextRes(): Int = R.string.register_title
    override fun getSubtitleTextRes(): Int = R.string.register_subtitle
    override fun getButtonTextRes(): Int = R.string.register_title

    override fun getInputList(): List<FormInputData> {
        return listOf(
            FormInputData(FormInputType.NAME),
            FormInputData(FormInputType.SURNAME),
            FormInputData(FormInputType.EMAIL),
            FormInputData(FormInputType.PASSWORD),
            FormInputData(FormInputType.BIRTH_DATE),
            FormInputData(FormInputType.GENDER)
        )
    }

    private fun getRequest(): UserData = with(formViewModel) {
        UserData(getContentFromItem(FormInputType.NAME),
            getContentFromItem(FormInputType.SURNAME),
            getContentFromItem(FormInputType.EMAIL),
            getContentFromItem(FormInputType.PASSWORD),
            getContentFromItem(FormInputType.GENDER),
            StringUtils.EMPTY,
            getContentFromItem(FormInputType.BIRTH_DATE),
            StringUtils.EMPTY,
            StringUtils.EMPTY)
    }

    override fun getViewModelClass(): Class<RegisterViewModel> = RegisterViewModel::class.java
    override fun call(): Call<MsgResponse> = NetworkModule.buildRetrofitClient().register(getRequest())

}