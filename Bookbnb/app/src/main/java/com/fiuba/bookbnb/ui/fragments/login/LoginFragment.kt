package com.fiuba.bookbnb.ui.fragments.login

import android.app.AlertDialog
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.login.LoginRequest
import com.fiuba.bookbnb.forms.InputField
import com.fiuba.bookbnb.repository.LoadingStatus
import com.fiuba.bookbnb.ui.fragments.form.FormFragment
import com.fiuba.bookbnb.ui.utils.AdditionalContentForm
import kotlinx.android.synthetic.main.bookbnb_form.*
import org.apache.commons.lang3.StringUtils


class LoginFragment : FormFragment() {

    private lateinit var viewModel : LoginViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        buildAdditionalContainer()
        setViewModelObserver()
    }

    private fun setViewModelObserver() {
        viewModel.loadingStatus.observe(viewLifecycleOwner) { loginStatus ->
            when (loginStatus) {
                LoadingStatus.SUCCESS -> showDialog()
                LoadingStatus.FAILURE -> showDialog()
                LoadingStatus.LOADING -> showLoading(true)
                LoadingStatus.ERROR -> showDialog()
                else -> {}
            }
        }
    }

    private fun showDialog() {
        showLoading(false)
        AlertDialog.Builder(context).run {
            setMessage(viewModel.getMessageResponse())
        }.show()
        viewModel.hideLoading()
    }

    private fun getLoginRequest() = LoginRequest(getFieldContent(InputField.EMAIL), getFieldContent(InputField.PASSWORD))

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
        addInputField(InputField.PASSWORD, R.string.pass_field_label)
    }

    override fun proceedLoading() = viewModel.login(getLoginRequest())

}