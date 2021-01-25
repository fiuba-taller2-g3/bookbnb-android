package com.fiuba.bookbnb.ui.fragments.profile

import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.misc.MsgResponse
import com.fiuba.bookbnb.domain.user.UserData
import com.fiuba.bookbnb.forms.FormInputData
import com.fiuba.bookbnb.forms.FormInputType
import com.fiuba.bookbnb.networking.NetworkModule
import com.fiuba.bookbnb.ui.fragments.form.FormWithNetworkStatusFragment
import com.fiuba.bookbnb.user.UserManager
import retrofit2.Call

class ProfileEditFragment : FormWithNetworkStatusFragment<ProfileEditViewModel, MsgResponse>() {

    override fun getTitleTextRes(): Int = R.string.edit_info_profile_title
    override fun getSubtitleTextRes(): Int = R.string.edit_info_profile_text
    override fun getButtonTextRes(): Int = R.string.edit_info_profile_button

    override fun getInputList(): List<FormInputData> {
        return with(UserManager.getUserInfo().getUserData()) {
            listOf(
                FormInputData(FormInputType.NAME, name),
                FormInputData(FormInputType.SURNAME, surname),
                FormInputData(FormInputType.EMAIL, email),
                FormInputData(FormInputType.PHONE_NUMBER, phoneNumber),
                FormInputData(FormInputType.BIRTH_DATE_EDIT, birthDate),
                FormInputData(FormInputType.GENDER, gender)
            )
        }
    }

    private fun getRequest(): UserData = with(formViewModel) {
        UserData(getContentFromItem(FormInputType.NAME),
            getContentFromItem(FormInputType.SURNAME),
            getContentFromItem(FormInputType.EMAIL),
            null,
            getContentFromItem(FormInputType.GENDER),
            getContentFromItem(FormInputType.PHONE_NUMBER),
            getContentFromItem(FormInputType.BIRTH_DATE_EDIT),
            UserManager.getUserInfo().getUserId(),
            UserManager.getUserInfo().getUserData().walletId)
    }

    override fun onSuccessStatus(cleanInputs: Boolean) {
        UserManager.updateUser(getRequest())
        super.onSuccessStatus(cleanInputs)
    }

    override fun getViewModelClass(): Class<ProfileEditViewModel> = ProfileEditViewModel::class.java
    override fun call(): Call<MsgResponse> = with(UserManager.getUserInfo()) {
        NetworkModule.buildRetrofitClient().updateUser(getUserId(), getToken(), getRequest())
    }

}