package com.fiuba.bookbnb.ui.fragments.login

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.login.LoginRequest
import com.fiuba.bookbnb.repository.LoadingStatus
import com.fiuba.bookbnb.ui.fragments.form.FormFragment
import com.fiuba.bookbnb.ui.utils.KeyboardType
import com.fiuba.bookbnb.ui.utils.TextInputField
import kotlinx.android.synthetic.main.bookbnb_form.*


class LoginFragment : FormFragment() {

    private lateinit var viewModel : LoginViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        setViewModelObserver()
        setButtonLoginListener()
        setButtonRegisterListener()
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

    private fun setButtonLoginListener() {
        form_button.setOnClickListener {
            viewModel.login(getLoginRequest())
        }
    }

    private fun setButtonRegisterListener() {
        login_register_text.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun showDialog() {
        showLoading(false)
        AlertDialog.Builder(context).run {
            setMessage(viewModel.getMsgResponse())
        }.show()
    }

    private fun getLoginRequest() = LoginRequest(getFieldContent(EMAIL), getFieldContent(PASS))

    override fun getTitle() = R.string.login_title

    override fun getSubtitle() = R.string.login_subtitle

    override fun initFields() {
        putField(EMAIL, TextInputField(requireContext(), getString(R.string.email_text_field)))
        putField(PASS, TextInputField(requireContext(), getString(R.string.pass_text_field), KeyboardType.ALPHANUMERIC_PASSWORD))
    }

    companion object {
        private const val EMAIL = "EMAIL"
        private const val PASS = "PASS"
    }
}