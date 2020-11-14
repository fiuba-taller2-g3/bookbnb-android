package com.fiuba.bookbnb.ui.fragments.register

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.forms.inputFields.EditTextInputFieldItem
import com.fiuba.bookbnb.forms.validators.DefaultInputValidator
import com.fiuba.bookbnb.forms.validators.EmailInputValidator
import com.fiuba.bookbnb.forms.validators.PassInputValidator
import com.fiuba.bookbnb.forms.validators.PostFixDefaultMsgValidator
import com.fiuba.bookbnb.ui.fragments.form.FormFragment
import com.fiuba.bookbnb.ui.utils.KeyboardType

class RegisterFragment : FormFragment() {

    private lateinit var viewModel : RegisterViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)
    }

    override fun getTitle() = R.string.register_title

    override fun getSubtitle() = R.string.register_subtitle

    override fun getButtonText() = R.string.register_title

    override fun initFields() {
        addInputField(NAME, R.string.name_field_label, EditTextInputFieldItem(requireContext(), DefaultInputValidator(PostFixDefaultMsgValidator.POSTFIX_NAME.msg)))
        addInputField(SURNAME, R.string.surname_field_label, EditTextInputFieldItem(requireContext(), PassInputValidator(PostFixDefaultMsgValidator.POSTFIX_SURNAME.msg), KeyboardType.ALPHANUMERIC_PASSWORD))
        addInputField(BIRTH_DATE, R.string.birthday_field_label, EditTextInputFieldItem(requireContext(), PassInputValidator(PostFixDefaultMsgValidator.POSTFIX_BIRTHDAY.msg), KeyboardType.ALPHANUMERIC_PASSWORD))
        addInputField(EMAIL, R.string.email_field_label, EditTextInputFieldItem(requireContext(), PassInputValidator(PostFixDefaultMsgValidator.POSTFIX_EMAIL.msg), KeyboardType.ALPHANUMERIC_PASSWORD))
        addInputField(PASS, R.string.pass_field_label, EditTextInputFieldItem(requireContext(), PassInputValidator(PostFixDefaultMsgValidator.POSTFIX_PASSWORD.msg), KeyboardType.ALPHANUMERIC_PASSWORD))
    }

    override fun proceedLoading() = viewModel.register()

    companion object {
        private const val NAME = "NAME"
        private const val SURNAME = "SURNAME"
        private const val BIRTH_DATE = "BIRTHDATE"
        private const val EMAIL = "EMAIL"
        private const val PASS = "PASS"
        private const val PROFILE_TYPE = "TYPE"
    }

}