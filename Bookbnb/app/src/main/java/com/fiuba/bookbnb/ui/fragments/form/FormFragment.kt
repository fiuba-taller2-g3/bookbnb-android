package com.fiuba.bookbnb.ui.fragments.form

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.ui.utils.TextInputField
import kotlinx.android.synthetic.main.bookbnb_form.*
import org.apache.commons.lang3.StringUtils

abstract class FormFragment : Fragment(R.layout.bookbnb_form) {

    private val fields by lazy { HashMap<String, TextInputField>() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bkbnb_form_title.text = getString(getTitle())
        bkbnb_form_subtitle.text = getString(getSubtitle())

        initFields()
    }

    protected fun putField(fieldId: String, textInputField: TextInputField ) {
        fields[fieldId] = textInputField.also { input_fields_container.addView(it) }
    }

    protected fun getFieldContent(fieldId: String) = fields[fieldId]?.getContentField() ?: StringUtils.EMPTY

    protected fun showLoading(enabled: Boolean) {
        progress_login.visibility = getLoadingVisibility(enabled)
        form_button.isEnabled = !enabled
        form_button.setTextColor(getTextColorButton(enabled))
        form_button.setBackgroundColor(getBackgroundColorButton(enabled))
        fields.values.forEach { it.setInputFieldStatus(!enabled) }
    }

    private fun getLoadingVisibility(showLoading: Boolean) = if (showLoading) View.VISIBLE else View.INVISIBLE

    private fun getTextColorButton(showLoading: Boolean) = ContextCompat.getColor(requireContext(), if (!showLoading) R.color.colorWhite else R.color.colorTextButtonDisabled)

    private fun getBackgroundColorButton(showLoading: Boolean) = ContextCompat.getColor(requireContext(), if (!showLoading) R.color.colorButton else R.color.colorBackgroundButtonDisabled)

    protected abstract fun getTitle() : Int

    protected abstract fun getSubtitle() : Int

    protected abstract fun initFields()

}