package com.fiuba.bookbnb.ui.fragments.login

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.login.LoginRequest
import com.fiuba.bookbnb.repository.LoadingStatus
import com.fiuba.bookbnb.ui.utils.KeyboardType
import com.fiuba.bookbnb.ui.utils.TextInputField
import kotlinx.android.synthetic.main.bookbnb_login.*


class LoginFragment : Fragment(R.layout.bookbnb_login) {

    private lateinit var viewModel : LoginViewModel
    private val emailInputField by lazy { TextInputField(requireContext(), getString(R.string.email_text_field)) }
    private val passwordInputField by lazy { TextInputField(requireContext(), getString(R.string.pass_text_field), KeyboardType.ALPHANUMERIC_PASSWORD) }

    @SuppressLint("InflateParams")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        input_fields_container.apply {
            addView(emailInputField)
            addView(passwordInputField)
        }

        setViewModelObserver()
        setButtonLoginListener()
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

    private fun showLoading(enabled: Boolean) {
        progress_login.visibility = getLoadingVisibility(enabled)
        login_button.isEnabled = !enabled
        login_button.setTextColor(getTextColorButton(enabled))
        login_button.setBackgroundColor(getBackgroundColorButton(enabled))
        emailInputField.setInputFieldStatus(!enabled)
        passwordInputField.setInputFieldStatus(!enabled)
    }

    private fun setButtonLoginListener() {
        login_button.setOnClickListener {
            viewModel.login(getLoginRequest())
        }
    }

    private fun showDialog() {
        showLoading(false)
        AlertDialog.Builder(context).run {
            setMessage(viewModel.getMsgResponse())
        }.show()
    }

    private fun getLoginRequest() = LoginRequest(emailInputField.getContentField(), passwordInputField.getContentField())

    private fun getLoadingVisibility(showLoading: Boolean) = if (showLoading) View.VISIBLE else View.INVISIBLE

    private fun getTextColorButton(showLoading: Boolean) = ContextCompat.getColor(requireContext(), if (!showLoading) R.color.colorWhite else R.color.colorTextButtonDisabled)

    private fun getBackgroundColorButton(showLoading: Boolean) = ContextCompat.getColor(requireContext(), if (!showLoading) R.color.colorButton else R.color.colorBackgroundButtonDisabled)
}