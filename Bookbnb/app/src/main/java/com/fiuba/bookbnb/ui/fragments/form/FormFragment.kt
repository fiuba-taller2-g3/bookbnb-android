package com.fiuba.bookbnb.ui.fragments.form

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProviders
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.forms.InputField
import com.fiuba.bookbnb.forms.InputFieldBuilder
import com.fiuba.bookbnb.forms.InputFieldModule
import com.fiuba.bookbnb.forms.inputFields.AbstractInputFieldItem
import com.fiuba.bookbnb.repository.LoadingStatus
import com.fiuba.bookbnb.ui.fragments.BaseFragment
import com.fiuba.bookbnb.ui.utils.KeyboardType
import kotlinx.android.synthetic.main.bookbnb_form_fragment.*
import org.apache.commons.lang3.StringUtils
import java.util.*

abstract class FormFragment<T : FormViewModel> : BaseFragment(R.layout.bookbnb_form_fragment) {

    private val fields by lazy { EnumMap<InputField, AbstractInputFieldItem>(InputField::class.java)}
    protected lateinit var viewModel : T

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bkbnb_form_title.text = getString(getTitle())
        bkbnb_form_subtitle.text = getString(getSubtitle())
        form_button.text = getString(getButtonText())

        viewModel = ViewModelProviders.of(this).get(getViewModelClass())

        initFields()
        setButtonListener()
        setViewModelObserver()
    }

    private fun setViewModelObserver() {
        viewModel.loadingStatus.observe(viewLifecycleOwner) { loadStatus ->
            when (loadStatus) {
                LoadingStatus.SUCCESS -> {
                    showLoading(false)
                    proceedSuccess()
                }
                LoadingStatus.FAILURE -> showDialog()
                LoadingStatus.LOADING -> showLoading(true)
                LoadingStatus.ERROR -> showDialog()
                else -> {}
            }
        }
    }

    private fun showDialog() {
        showLoading(false)
        AlertDialog.Builder(context).run {
            setMessage(viewModel.getMessageResponse())
        }.show()
        viewModel.hideLoading()
    }

    private fun setButtonLoading(loadingEnabled: Boolean) {
        progress.visibility = if (loadingEnabled) View.VISIBLE else View.INVISIBLE
        form_button.text = if (progress.isVisible) StringUtils.EMPTY else getString(getButtonText())
        form_button.isEnabled = !loadingEnabled
    }

    private fun showLoading(loadingEnabled: Boolean) {
        setButtonLoading(loadingEnabled)
        for (i in 0 until additional_container.childCount) {
            additional_container.getChildAt(i).isEnabled = !loadingEnabled
        }
        fields.values.forEach { field -> if (loadingEnabled) field.disableInput() else field.enableInput() }
    }

    private fun addInputField(fieldId: InputField, labelResource: Int, abstractInputFieldItem: AbstractInputFieldItem, descriptionRes: Int?) {
        fields[fieldId] = abstractInputFieldItem.also {
            input_fields_container.addView(InputFieldModule(requireContext(), getString(labelResource), it, descriptionRes))
        }
    }

    protected fun addDefaultInputField(fieldId: InputField, labelResource: Int, keyboardType: KeyboardType, descriptionRes: Int? = null, initialContent: String = StringUtils.EMPTY) {
        return addInputField(fieldId, labelResource, InputFieldBuilder.defaultBuild(requireContext(), fieldId, keyboardType, initialContent), descriptionRes)
    }

    protected fun addInputField(fieldId: InputField, labelResource: Int, descriptionRes: Int? = null, initialContent: String = StringUtils.EMPTY) {
        addInputField(fieldId, labelResource, InputFieldBuilder.build(requireContext(), fieldId, initialContent), descriptionRes)
    }

    private fun setButtonListener() {
        form_button.setOnClickListener {
            var isFormsValidated = true
            fields.values.forEach { field -> if (!field.isValidated() && isFormsValidated) isFormsValidated = false }
            if (isFormsValidated) proceedLoading() else jumpToFirstInputFieldInvalid()
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

    private fun getPositionOfInputField(index: Int) = with(input_fields_container) {top + getChildAt(index).top }

    protected fun getFieldContent(fieldId: InputField) = fields[fieldId]?.getContentField() ?: StringUtils.EMPTY

    protected open fun proceedSuccess() {
        // Do nothing
    }

    protected abstract fun getTitle() : Int

    protected abstract fun getSubtitle() : Int

    protected abstract fun getButtonText() : Int

    protected abstract fun initFields()

    protected abstract fun proceedLoading()

    protected abstract fun getViewModelClass() : Class<T>

}