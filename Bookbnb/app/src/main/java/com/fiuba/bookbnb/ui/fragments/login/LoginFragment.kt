package com.fiuba.bookbnb.ui.fragments.login

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.login.LoginRequest
import com.fiuba.bookbnb.domain.login.LoginResponse
import com.fiuba.bookbnb.forms.FieldsId
import com.fiuba.bookbnb.networking.NetworkModule
import com.fiuba.bookbnb.ui.utils.KeyboardType
import com.fiuba.bookbnb.ui.utils.TextInputField
import com.google.gson.Gson
import kotlinx.android.synthetic.main.bookbnb_login.*
import org.apache.commons.lang3.StringUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginFragment : Fragment(R.layout.bookbnb_login) {

    private lateinit var viewModel : LoginViewModel

    @SuppressLint("InflateParams")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        input_fields_container.apply {
            addView(TextInputField(context, getString(R.string.email_text_field), FieldsId.EMAIL))
            addView(TextInputField(context, getString(R.string.pass_text_field), FieldsId.PASSWORD, KeyboardType.ALPHANUMERIC_PASSWORD))
        }

        setButtonLoginListener()
    }

    private fun setButtonLoginListener() {
        login_button.setOnClickListener {
            val loginResponse = NetworkModule.buildRetrofitClient().login(getLoginRequest())
            progress_login.visibility = View.VISIBLE
            login_button.isEnabled = false
            login_button.setTextColor(resources.getColor(R.color.colorTextButtonDisabled))
            login_button.setBackgroundColor(resources.getColor(R.color.colorBackgroundButtonDisabled))

            loginResponse.enqueue(object : Callback<LoginResponse> {

                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    enableLoginComponents()

                    val message = if (response.isSuccessful) response.body()?.msg else
                        response.errorBody()?.let { Gson().fromJson(it.string(), LoginResponse::class.java) }?.msg

                    AlertDialog.Builder(context).run {
                        setMessage(message)
                    }.show()
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    enableLoginComponents()
                    AlertDialog.Builder(context).run {
                        setMessage(t.message)
                    }.show()
                }
            })
        }
    }

    private fun getLoginRequest(): LoginRequest {
        val fields = HashMap<FieldsId, String>()
        for (i in 0 until input_fields_container.childCount) {
            val textInputField = input_fields_container.getChildAt(i) as TextInputField
            fields[textInputField.getFieldId()] = textInputField.getContentField()
            textInputField.disableEditText()
        }

        return LoginRequest(
            getContent(fields[FieldsId.EMAIL]),
            getContent(fields[FieldsId.PASSWORD])
        )
    }

    private fun enableLoginComponents() {
        for (i in 0 until input_fields_container.childCount) {
            (input_fields_container.getChildAt(i) as TextInputField).enableEditText()
        }
        progress_login.visibility = View.INVISIBLE
        login_button.isEnabled = true
        login_button.setTextColor(resources.getColor(R.color.colorWhite))
        login_button.setBackgroundColor(resources.getColor(R.color.colorButton))
    }

    private fun getContent(content: String?): String = content ?: StringUtils.EMPTY
}