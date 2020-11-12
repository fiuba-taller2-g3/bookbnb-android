package com.fiuba.bookbnb.forms.inputFields

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.bookbnb_text_input_field_item.view.*

abstract class AbstractInputFieldItem @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : ConstraintLayout(context, attrs, defStyleAttr) {

    protected fun setLayoutParams() {
        layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    abstract fun enableInput()
    abstract fun disableInput()
    abstract fun isValidated() : Boolean

    fun getContentField() = edit_text.text.toString()

}