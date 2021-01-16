package com.fiuba.bookbnb.ui.fragments.publish

import com.fiuba.bookbnb.ui.fragments.form.FormListFragment
import com.fiuba.bookbnb.ui.fragments.form.adapters.FormAdapter
import com.fiuba.bookbnb.ui.fragments.form.adapters.ServicesFormAdapter
import com.fiuba.bookbnb.ui.fragments.form.data.FormItemData
import com.fiuba.bookbnb.ui.fragments.form.data.HeaderFormData
import com.fiuba.bookbnb.ui.fragments.form.data.InputViewType
import com.fiuba.bookbnb.ui.fragments.form.services.Services
import com.fiuba.bookbnb.ui.fragments.form.services.ServicesItemBuilder

abstract class PublishStayServicesAbstractStepFormFragment : FormListFragment(), ServicesFormAdapter.UpdateItemListener {

    private val servicesItemBuilder by lazy { ServicesItemBuilder(requireContext()) }

    override fun getFormAdapter(): FormAdapter {
        return ServicesFormAdapter(getFormItems(), HeaderFormData(getString(getTitleTextRes()), getString(getSubtitleTextRes())), this)
    }

    override fun getFormItems(): List<FormItemData> {
        val formInputItems = ArrayList<FormItemData>()
        formInputItems.add(FormItemData(InputViewType.HEADER))
        getServices().forEach { service ->
            formInputItems.add(servicesItemBuilder.build(service, formViewModel.getValueFromService(service)))
        }

        return formViewModel.loadServicesItems(formInputItems)
    }

    override fun shouldClearInputsWhenBackPressed(): Boolean = false

    override fun update(service: Services, value: Boolean) {
        formViewModel.updateServiceItem(service, value)
    }

    protected abstract fun getServices() : List<Services>

}