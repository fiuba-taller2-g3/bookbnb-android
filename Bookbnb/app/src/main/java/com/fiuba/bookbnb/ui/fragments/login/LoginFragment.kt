package com.fiuba.bookbnb.ui.fragments.login

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.login.LoginRequest
import com.fiuba.bookbnb.domain.login.LoginResponse
import com.fiuba.bookbnb.networking.NetworkModule
import com.fiuba.bookbnb.ui.utils.KeyboardType
import com.fiuba.bookbnb.ui.utils.TextInputField
import com.google.gson.Gson
import kotlinx.android.synthetic.main.bookbnb_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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
        viewModel.showLoading.observe(viewLifecycleOwner) { isLoadingEnabled ->
            progress_login.visibility = getLoadingVisibility(isLoadingEnabled)
            login_button.isEnabled = !isLoadingEnabled
            login_button.setTextColor(getTextColorButton(isLoadingEnabled))
            login_button.setBackgroundColor(getBackgroundColorButton(isLoadingEnabled))
            emailInputField.setInputFieldStatus(!isLoadingEnabled)
            passwordInputField.setInputFieldStatus(!isLoadingEnabled)
        }
    }

    private fun setButtonLoginListener() {
        login_button.setOnClickListener {
            viewModel.showLoading()
            val loginResponse = NetworkModule.buildRetrofitClient().login(getLoginRequest())

            loginResponse.enqueue(object : Callback<LoginResponse> {

                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    viewModel.hideLoading()
                    val message = if (response.isSuccessful) response.body()?.msg else
                        response.errorBody()?.let { Gson().fromJson(it.string(), LoginResponse::class.java) }?.msg

                    AlertDialog.Builder(context).run {
                        setMessage(message)
                    }.show()
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    viewModel.hideLoading()
                    AlertDialog.Builder(context).run {
                        setMessage(t.message)
                    }.show()
                }
            })
        }
    }

    private fun getLoginRequest() = LoginRequest(emailInputField.getContentField(), passwordInputField.getContentField())

    private fun getLoadingVisibility(showLoading: Boolean) = if (showLoading) View.VISIBLE else View.INVISIBLE

    private fun getTextColorButton(showLoading: Boolean) = ContextCompat.getColor(requireContext(), if (!showLoading) R.color.colorWhite else R.color.colorTextButtonDisabled)

    private fun getBackgroundColorButton(showLoading: Boolean) = ContextCompat.getColor(requireContext(), if (!showLoading) R.color.colorButton else R.color.colorBackgroundButtonDisabled)
}