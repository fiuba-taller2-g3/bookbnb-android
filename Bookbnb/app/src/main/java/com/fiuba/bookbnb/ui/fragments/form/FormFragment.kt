package com.fiuba.bookbnb.ui.fragments.form

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.forms.InputField
import com.fiuba.bookbnb.forms.InputFieldBuilder
import com.fiuba.bookbnb.forms.inputFields.AbstractInputFieldItem
import com.fiuba.bookbnb.forms.InputFieldModule
import com.fiuba.bookbnb.ui.utils.KeyboardType
import kotlinx.android.synthetic.main.bookbnb_form.*
import org.apache.commons.lang3.StringUtils
import java.util.*

abstract class FormFragment : Fragment(R.layout.bookbnb_form) {

    private val fields by lazy { EnumMap<InputField, AbstractInputFieldItem>(InputField::class.java)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bkbnb_form_title.text = getString(getTitle())
        bkbnb_form_subtitle.text = getString(getSubtitle())
        form_button.text = getString(getButtonText())

        initFields()
        setButtonListener()
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

    private fun addInputField(fieldId: InputField, labelResource: Int, abstractInputFieldItem: AbstractInputFieldItem) {
        fields[fieldId] = abstractInputFieldItem.also {
            input_fields_container.addView(InputFieldModule(requireContext(), getString(labelResource), it))
        }
    }

    protected fun addDefaultInputField(fieldId: InputField, labelResource: Int, keyboardType: KeyboardType) {
        return addInputField(fieldId, labelResource, InputFieldBuilder.defaultBuild(requireContext(), fieldId, keyboardType))
    }

    protected fun addInputField(fieldId: InputField, labelResource: Int) = addInputField(fieldId, labelResource, InputFieldBuilder.build(requireContext(), fieldId))

    private fun setButtonListener() {
        form_button.setOnClickListener {
            var isFormsValidated = true
            fields.values.forEach { field -> if (!field.isValidated() && isFormsValidated) isFormsValidated = false }
            if (isFormsValidated) proceedLoading()
        }
    }

    protected fun getFieldContent(fieldId: InputField) = fields[fieldId]?.getContentField() ?: StringUtils.EMPTY

    protected abstract fun getTitle() : Int

    protected abstract fun getSubtitle() : Int

    protected abstract fun getButtonText() : Int

    protected abstract fun initFields()

    protected abstract fun proceedLoading()

}