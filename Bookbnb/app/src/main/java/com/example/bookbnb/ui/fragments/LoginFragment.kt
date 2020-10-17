package com.example.bookbnb.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.bookbnb.R
import com.example.bookbnb.ui.utils.TextInputField
import kotlinx.android.synthetic.main.bookbnb_login.*

class LoginFragment : Fragment(R.layout.bookbnb_login) {

    @SuppressLint("InflateParams")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        input_fields_container.apply {
            addView(TextInputField(context, getString(R.string.email_text_field)))
            addView(TextInputField(context, getString(R.string.pass_text_field)))
        }
    }
}