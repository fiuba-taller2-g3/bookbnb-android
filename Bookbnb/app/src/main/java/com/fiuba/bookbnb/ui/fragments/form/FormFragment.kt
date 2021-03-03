package com.fiuba.bookbnb.ui.fragments.form

import android.os.Bundle
import android.view.View
import androidx.core.view.forEach
import androidx.core.view.isVisible
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.forms.FormInputData
import com.fiuba.bookbnb.forms.FormInputType
import com.fiuba.bookbnb.forms.InputFieldModule
import com.fiuba.bookbnb.forms.InputItemsManager
import kotlinx.android.synthetic.main.bookbnb_button.*
import kotlinx.android.synthetic.main.bookbnb_form_fragment.*
import kotlinx.android.synthetic.main.bookbnb_form_header.*
import java.util.*

abstract class FormFragment : FormBaseFragment(R.layout.bookbnb_form_fragment) {

    private val inputItemsManager by lazy { InputItemsManager(requireContext(), ::storeInputContent) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHeaderContent(getTitleTextRes(), getSubtitleTextRes())
        initInputs()
        initButtonListener()
    }

    protected open fun setHeaderContent(titleRes: Int, subtitleRes: Int, vararg titleArgs: String) {
        bkbnb_form_title.text = getString(titleRes, *titleArgs)
        with(bkbnb_form_subtitle) {
            isVisible = getString(subtitleRes).isNotEmpty()
            if (isVisible) { text = getString(subtitleRes) }
        }
    }

    private fun initInputs() {
        formViewModel.loadInputItems(getInputList()).forEach { inputItem ->
            val inputData = getInputList().firstOrNull { it.inputField == inputItem.key }
            input_fields_container.addView(inputItemsManager.getInputModule(FormInputData(inputItem.key, inputItem.value, inputData?.minDate, inputData?.maxDate)))
        }
    }

    private fun initButtonListener() {
        button.setOnClickListener {
            var isFormsValidated = true
            input_fields_container.forEach { inputFieldModuleItem ->
                if (!(inputFieldModuleItem as InputFieldModule).getInputFieldItem().isValidated() && isFormsValidated) isFormsValidated = false
            }
            if (isFormsValidated) setActionEventButton() else jumpToFirstInputFieldInvalid()
        }
    }

    private fun jumpToFirstInputFieldInvalid() {
        for (i in 0 until input_fields_container.childCount) {
            if (!(input_fields_container.getChildAt(i) as InputFieldModule).getInputFieldItem().isValidated()) {
                form_scroll_view.post { form_scroll_view.smoothScrollTo(0, getPositionOfInputField(i)) }
                break
            }
        }
    }

    private fun getPositionOfInputField(index: Int) = with(input_fields_container) { top + getChildAt(index).top }

    private fun storeInputContent(inputData: FormInputData) {
        formViewModel.updateInputItem(inputData)
    }

    protected abstract fun getInputList() : List<FormInputData>

}