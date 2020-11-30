package com.fiuba.bookbnb.ui.fragments.login

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.view.View
import androidx.core.view.isVisible
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.login.LoginRequest
import com.fiuba.bookbnb.forms.InputField
import com.fiuba.bookbnb.ui.fragments.form.FormFragment
import com.fiuba.bookbnb.ui.utils.KeyboardType
import kotlinx.android.synthetic.main.bookbnb_form_fragment.*

class LoginFragment : FormFragment<LoginViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        additional_text.isVisible = true
        additional_text.text = buildForgottenPassText()
    }

    private fun buildForgottenPassText(): SpannableStringBuilder {
        return SpannableStringBuilder().apply {
            append(buildTextStyled(getString(R.string.text_forgotten_pass)))
        }
    }

    private fun buildTextStyled(text: String): SpannableString {
        return SpannableString(text).apply {
            arrayListOf(UnderlineSpan(), StyleSpan(Typeface.BOLD)).forEach { styleSpan ->
                setSpan(styleSpan, 0, length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
            }
        }
    }

    override fun getTitle() = R.string.login_title

    override fun getSubtitle() = R.string.login_subtitle

    override fun getButtonText(): Int = R.string.login_text_button

    override fun initFields() {
        addInputField(InputField.EMAIL, R.string.email_field_label)
        addDefaultInputField(InputField.PASSWORD, R.string.pass_field_label, KeyboardType.ALPHANUMERIC_PASSWORD)
    }

    override fun proceedLoading() {
        val request = LoginRequest(getFieldContent(InputField.EMAIL), getFieldContent(InputField.PASSWORD))
        viewModel.login(request)
    }

    override fun getViewModelClass() = LoginViewModel::class.java

}