package com.fiuba.bookbnb.ui.fragments.profile

import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.user.UserData
import com.fiuba.bookbnb.forms.InputField
import com.fiuba.bookbnb.ui.fragments.form.FormFragment
import com.fiuba.bookbnb.user.UserManager

class ProfileEditFragment : FormFragment<ProfileEditViewModel>() {

    override fun getTitle() = R.string.edit_info_profile_title

    override fun getSubtitle() = R.string.edit_info_profile_text

    override fun getButtonText() = R.string.edit_info_profile_button

    override fun initFields() {
        with(UserManager.getUserInfo().getUserData()) {
            addInputField(InputField.NAME, R.string.name_field_label, initialContent = name)
            addInputField(InputField.SURNAME, R.string.surname_field_label, initialContent = surname)
            addInputField(InputField.EMAIL, R.string.email_field_label, initialContent = email)
            addInputField(InputField.BIRTHDATE, R.string.birthdate_field_label, initialContent = birthDate)
            // TODO: El profile type hay que sacarlo
            addInputField(InputField.PROFILE_TYPE, R.string.profile_type_field_label)
        }
    }

    override fun proceedLoading() = viewModel.update(getUserData())

    override fun getViewModelClass(): Class<ProfileEditViewModel> = ProfileEditViewModel::class.java

    override fun proceedSuccess() {
        UserManager.updateUser(getUserData())
    }

    private fun getUserData(): UserData = UserData(getFieldContent(InputField.NAME), getFieldContent(InputField.SURNAME),
        getFieldContent(InputField.EMAIL), getFieldContent(InputField.PASSWORD), getFieldContent(InputField.PROFILE_TYPE),
        getFieldContent(InputField.GENDER), getFieldContent(InputField.PHONE_NUMBER), getFieldContent(InputField.BIRTHDATE),
        UserManager.getUserInfo().getUserId().toString())

}