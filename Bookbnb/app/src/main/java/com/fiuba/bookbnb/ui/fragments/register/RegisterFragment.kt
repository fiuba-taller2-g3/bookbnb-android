package com.fiuba.bookbnb.ui.fragments.register

import android.os.Bundle
import android.view.View
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.ui.fragments.form.FormFragment
import com.fiuba.bookbnb.ui.utils.KeyboardType
import com.fiuba.bookbnb.forms.inputFields.InputFieldModule

class RegisterFragment : FormFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun getTitle() = R.string.register_title

    override fun getSubtitle() = R.string.register_subtitle

    override fun getButtonText() = R.string.register_title

    override fun initFields() {
//        putField(NAME, InputFieldModule(requireContext(), getString(R.string.name_text_field)))
//        putField(SURNAME, InputFieldModule(requireContext(), getString(R.string.surname_text_field)))
//        putField(BIRTH_DATE, InputFieldModule(requireContext(), getString(R.string.birthday_text_field)))
//        putField(EMAIL, InputFieldModule(requireContext(), getString(R.string.email_text_field)))
//        putField(PASS, InputFieldModule(requireContext(), getString(R.string.pass_text_field), KeyboardType.ALPHANUMERIC_PASSWORD))
//        putField(PROFILE_TYPE, InputFieldModule(requireContext(), getString(R.string.profile_type_text_field)))
    }

    companion object {
        private const val NAME = "NAME"
        private const val SURNAME = "SURNAME"
        private const val BIRTH_DATE = "BIRTHDATE"
        private const val EMAIL = "EMAIL"
        private const val PASS = "PASS"
        private const val PROFILE_TYPE = "TYPE"
    }

}