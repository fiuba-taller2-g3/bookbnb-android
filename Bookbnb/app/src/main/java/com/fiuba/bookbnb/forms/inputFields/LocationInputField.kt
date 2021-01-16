package com.fiuba.bookbnb.forms.inputFields

import android.content.Context
import android.view.LayoutInflater
import com.fiuba.bookbnb.R

class LocationInputField(context: Context) : AbstractInputFieldItem(context) {

    init {
        LayoutInflater.from(context).inflate(R.layout.bookbnb_map_location_input_field, this)
        setLayoutParams()
    }

    override fun enableInput() {
        // Do Nothing
    }

    override fun disableInput() {
        // Do Nothing
    }

    override fun isValidated(): Boolean = true

}