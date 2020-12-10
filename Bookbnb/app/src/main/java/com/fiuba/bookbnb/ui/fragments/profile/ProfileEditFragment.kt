package com.fiuba.bookbnb.ui.fragments.profile

import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.misc.MsgResponse
import com.fiuba.bookbnb.domain.user.UserData
import com.fiuba.bookbnb.forms.InputField
import com.fiuba.bookbnb.networking.NetworkModule
import com.fiuba.bookbnb.ui.fragments.form.FormFragment
import com.fiuba.bookbnb.user.UserManager

class ProfileEditFragment : FormFragment<ProfileEditViewModel, MsgResponse>() {

    override fun getTitle() = R.string.edit_info_profile_title

    override fun getSubtitle() = R.string.edit_info_profile_text

    override fun getButtonText() = R.string.edit_info_profile_button

    override fun initFields() {
        with(UserManager.getUserInfo().getUserData()) {
            addInputField(InputField.NAME, R.string.name_field_label, initialContent = name)
            addInputField(InputField.SURNAME, R.string.surname_field_label, initialContent = surname)
            addInputField(InputField.EMAIL, R.string.email_field_label, initialContent = email)
            addInputField(InputField.BIRTHDATE, R.string.birthdate_field_label, initialContent = birthDate)
        }
    }

    override fun proceedLoading() {
        viewModel.update(with(UserManager.getUserInfo()) {
            NetworkModule.buildRetrofitClient().updateUser(getUserId(), getToken(), getUserData()).also {
                currentRunningCall = it
            }
        })
    }

    override fun getViewModelClass(): Class<ProfileEditViewModel> = ProfileEditViewModel::class.java

    override fun proceedSuccess() {
        UserManager.updateUser(getUserData())
    }

    private fun getUserData(): UserData = UserData(getFieldContent(InputField.NAME), getFieldContent(InputField.SURNAME),
        getFieldContent(InputField.EMAIL), getFieldContent(InputField.PASSWORD), getFieldContent(InputField.GENDER),
        getFieldContent(InputField.PHONE_NUMBER), getFieldContent(InputField.BIRTHDATE), UserManager.getUserInfo().getUserId())

}