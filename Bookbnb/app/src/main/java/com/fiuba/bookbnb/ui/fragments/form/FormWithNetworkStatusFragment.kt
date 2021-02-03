package com.fiuba.bookbnb.ui.fragments.form

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.core.view.forEach
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProviders
import com.fiuba.bookbnb.forms.InputFieldModule
import com.fiuba.bookbnb.networking.NetworkViewModel
import com.fiuba.bookbnb.repository.LoadingStatus
import kotlinx.android.synthetic.main.bookbnb_button.*
import kotlinx.android.synthetic.main.bookbnb_form_fragment.*
import org.apache.commons.lang3.StringUtils
import retrofit2.Call

abstract class FormWithNetworkStatusFragment<T : NetworkViewModel<S>, S: Any> : FormFragment() {

    protected lateinit var networkViewModel : T

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        networkViewModel = ViewModelProviders.of(this).get(getViewModelClass())
        setViewModelObserver()
    }

    override fun setActionEventButton() {
        networkViewModel.execute(call().also { networkManagerViewModel.setCancelCurrentRunningCallReference{ cancelRequest(it) } })
    }

    private fun cancelRequest(call: Call<S>) {
        call.cancel()
        networkViewModel.hideLoading()
    }

    private fun setViewModelObserver() {
        networkViewModel.loadingStatus.observe(viewLifecycleOwner) { loadStatus ->
            when (loadStatus) {
                LoadingStatus.SUCCESS -> onSuccessStatus(true)
                LoadingStatus.FAILURE -> showDialog()
                LoadingStatus.LOADING -> showLoading(true)
                LoadingStatus.ERROR -> showDialog()
                else -> showLoading(false)
            }
        }
    }

    private fun showDialog() {
        showLoading(false)
        AlertDialog.Builder(context).run {
            setMessage(networkViewModel.getMessageResponse())
        }.show()
        networkViewModel.hideLoading()
    }

    protected fun showLoading(loadingEnabled: Boolean) {
        setButtonLoading(loadingEnabled)
        additional_text.isEnabled = !loadingEnabled
        input_fields_container.forEach { inputFieldModuleItem ->
            with((inputFieldModuleItem as InputFieldModule).getInputFieldItem()) { if (loadingEnabled) disableInput() else enableInput() }
        }
    }

    private fun setButtonLoading(loadingEnabled: Boolean) {
        progress.visibility = if (loadingEnabled) View.VISIBLE else View.INVISIBLE
        button.text = if (progress.isVisible) StringUtils.EMPTY else getString(getButtonTextRes())
        button.isEnabled = !loadingEnabled
    }

    protected open fun onSuccessStatus(cleanInputs: Boolean) {
        showLoading(false)
        if (cleanInputs) formViewModel.clearInputs()
        networkManagerViewModel.clearCancelRunningCallReference()
    }

    protected abstract fun getViewModelClass() : Class<T>
    protected abstract fun call() : Call<S>

}