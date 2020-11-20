package com.fiuba.bookbnb.ui.fragments.register

import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.register.RegisterRequest
import com.fiuba.bookbnb.forms.InputField
import com.fiuba.bookbnb.ui.fragments.form.FormFragment

class RegisterFragment : FormFragment<RegisterViewModel>() {

    override fun getTitle() = R.string.register_title

    override fun getSubtitle() = R.string.register_subtitle

    override fun getButtonText() = R.string.register_title

    override fun initFields() {
        addInputField(InputField.NAME, R.string.name_field_label)
        addInputField(InputField.SURNAME, R.string.surname_field_label)
        addInputField(InputField.EMAIL, R.string.email_field_label, R.string.email_register_description_text)
        addInputField(InputField.PASSWORD, R.string.pass_field_label)
        addInputField(InputField.BIRTHDATE, R.string.birthdate_field_label, R.string.birthdate_register_description_text)
        addInputField(InputField.PROFILE_TYPE, R.string.profile_type_field_label)
    }

    override fun proceedLoading() {
        val request = RegisterRequest(getFieldContent(InputField.NAME), getFieldContent(InputField.SURNAME), getFieldContent(InputField.EMAIL),
            getFieldContent(InputField.PASSWORD), getFieldContent(InputField.PROFILE_TYPE), getFieldContent(InputField.GENDER),
            getFieldContent(InputField.PHONE_NUMBER), getFieldContent(InputField.BIRTHDATE))
        viewModel.register(request)
    }

    override fun getViewModelClass(): Class<RegisterViewModel> = RegisterViewModel::class.java

}