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
import androidx.navigation.findNavController
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.login.LoginRequest
import com.fiuba.bookbnb.forms.InputField
import com.fiuba.bookbnb.ui.fragments.form.FormFragment
import com.fiuba.bookbnb.ui.utils.AdditionalContentForm
import com.fiuba.bookbnb.ui.utils.KeyboardType
import kotlinx.android.synthetic.main.bookbnb_form_fragment.*
import org.apache.commons.lang3.StringUtils

class LoginFragment : FormFragment<LoginViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buildAdditionalContainer()
    }

    private fun buildAdditionalContainer() {
        val notRegisterText = AdditionalContentForm(requireContext(), buildNotRegisterText()).also {
            it.setOnClickListener { view -> view.findNavController().navigate(R.id.action_loginFragment_to_registerFragment) }
        }

        additional_container.isVisible = true
        additional_container.addView(notRegisterText)
        additional_container.addView(AdditionalContentForm(requireContext(), buildForgottenPassText()))
    }

    private fun buildNotRegisterText(): SpannableStringBuilder {
        return SpannableStringBuilder().apply {
            append(getString(R.string.text_question_not_register) + StringUtils.SPACE)
            append(buildTextStyled(getString(R.string.text_register)))
        }
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