package com.fiuba.bookbnb.ui.fragments.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.login.LoginRequest
import com.fiuba.bookbnb.domain.login.LoginResponse
import com.fiuba.bookbnb.forms.FieldsId
import com.fiuba.bookbnb.networking.NetworkModule
import com.fiuba.bookbnb.ui.utils.KeyboardType
import com.fiuba.bookbnb.ui.utils.TextInputField
import kotlinx.android.synthetic.main.bookbnb_login.*
import org.apache.commons.lang3.StringUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment(R.layout.bookbnb_login) {

    @SuppressLint("InflateParams")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        input_fields_container.apply {
            addView(TextInputField(context, getString(R.string.email_text_field), FieldsId.EMAIL))
            addView(TextInputField(context, getString(R.string.pass_text_field), FieldsId.PASSWORD, KeyboardType.ALPHANUMERIC_PASSWORD))
        }

        setButtonLoginListener()
    }

    private fun setButtonLoginListener() {
        login_button.setOnClickListener {
            val loginResponse = NetworkModule.buildRetrofitClient().login(getLoginRequest())
            loginResponse.enqueue(object : Callback<LoginResponse> {

                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    val msg = response.body()?.msg
                    print(msg)
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    // Log error here since request failed
                }

            })
        }
    }

    private fun getLoginRequest(): LoginRequest {
        val fields = HashMap<FieldsId, String>()
        for (i in 0 until input_fields_container.childCount) {
            val textInputField = input_fields_container.getChildAt(i) as TextInputField
            fields[textInputField.getFieldId()] = textInputField.getContentField()
        }

        return LoginRequest(getContent(fields[FieldsId.EMAIL]), getContent(fields[FieldsId.PASSWORD]))
    }

    private fun getContent(content: String?): String = content ?: StringUtils.EMPTY
}