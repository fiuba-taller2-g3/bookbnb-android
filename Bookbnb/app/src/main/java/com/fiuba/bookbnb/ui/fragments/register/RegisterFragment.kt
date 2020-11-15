package com.fiuba.bookbnb.ui.fragments.register

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.forms.InputField
import com.fiuba.bookbnb.ui.fragments.form.FormFragment

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
        addInputField(InputField.NAME, R.string.name_field_label)
        addInputField(InputField.SURNAME, R.string.surname_field_label)
        addInputField(InputField.EMAIL, R.string.email_field_label)
        addInputField(InputField.PASSWORD, R.string.pass_field_label)
        addInputField(InputField.PROFILE_TYPE, R.string.profile_type_field_label)
    }

    override fun proceedLoading() = viewModel.register()

}