package com.fiuba.bookbnb.ui.fragments.form

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.forms.inputFields.AbstractInputFieldItem
import kotlinx.android.synthetic.main.bookbnb_form.*
import org.apache.commons.lang3.StringUtils

abstract class FormFragment : Fragment(R.layout.bookbnb_form) {

    protected val fields by lazy { HashMap<String, AbstractInputFieldItem>() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bkbnb_form_title.text = getString(getTitle())
        bkbnb_form_subtitle.text = getString(getSubtitle())
        form_button.text = getString(getButtonText())

        initFields()
    }

    private fun setButtonLoading(loadingEnabled: Boolean) {
        progress.visibility = if (loadingEnabled) View.VISIBLE else View.INVISIBLE
        form_button.text = if (progress.isVisible) StringUtils.EMPTY else getString(getButtonText())
        form_button.isEnabled = !loadingEnabled
    }

    protected fun showLoading(loadingEnabled: Boolean) {
        setButtonLoading(loadingEnabled)
        for (i in 0 until additional_container.childCount) {
            additional_container.getChildAt(i).isEnabled = !loadingEnabled
        }
        fields.values.forEach { field -> if (loadingEnabled) field.disableInput() else field.enableInput() }
    }

    protected fun getFieldContent(fieldId: String) = fields[fieldId]?.getContentField() ?: StringUtils.EMPTY

    protected abstract fun getTitle() : Int

    protected abstract fun getSubtitle() : Int

    protected abstract fun getButtonText() : Int

    protected abstract fun initFields()

}