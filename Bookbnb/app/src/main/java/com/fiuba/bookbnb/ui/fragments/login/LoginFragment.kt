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
import com.fiuba.bookbnb.domain.login.LoginResponse
import com.fiuba.bookbnb.forms.FormInputData
import com.fiuba.bookbnb.forms.FormInputType
import com.fiuba.bookbnb.networking.NetworkModule
import com.fiuba.bookbnb.ui.fragments.form.FormWithNetworkStatusFragment
import kotlinx.android.synthetic.main.bookbnb_form_fragment.*
import retrofit2.Call

class LoginFragment : FormWithNetworkStatusFragment<LoginViewModel, LoginResponse>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        additional_text.isVisible = true
        additional_text.text = buildForgottenPassText()
    }

    override fun getTitleTextRes(): Int = R.string.login_title
    override fun getSubtitleTextRes(): Int = R.string.login_subtitle
    override fun getButtonTextRes(): Int = R.string.login_text_button

    override fun getInputList(): List<FormInputData> {
        return listOf(
            FormInputData(FormInputType.EMAIL),
            FormInputData(FormInputType.PASSWORD_LOGIN)
        )
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

    private fun getRequest(): LoginRequest = with(formViewModel) {
        LoginRequest(getContentFromItem(FormInputType.EMAIL), getContentFromItem(FormInputType.PASSWORD_LOGIN))
    }

    override fun getViewModelClass() = LoginViewModel::class.java
    override fun call(): Call<LoginResponse> = NetworkModule.buildRetrofitClient().login(getRequest())

}