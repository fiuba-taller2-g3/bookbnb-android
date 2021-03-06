package com.fiuba.bookbnb.ui.fragments.dialogs

import android.view.View
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.ui.fragments.form.services.ServicesItemBuilder
import com.fiuba.bookbnb.ui.fragments.form.services.ServicesUtils
import com.fiuba.bookbnb.ui.fragments.publish.publishView.ServiceDialogViewType
import com.fiuba.bookbnb.ui.fragments.publish.publishView.ServiceItemData
import com.fiuba.bookbnb.ui.fragments.publish.publishView.ServicesAdapter
import kotlinx.android.synthetic.main.bookbnb_services_list_dialog_fragment.view.*

class ServicesListDialogFragment : DialogBaseFragment() {

    private val navArguments by navArgs<ServicesListDialogFragmentArgs>()
    private val services by lazy { navArguments.publishData.services }

    private val servicesItemBuilder by lazy { ServicesItemBuilder(requireContext()) }

    override fun getBodyView(): View? {
        val servicesListView = View.inflate(context, R.layout.bookbnb_services_list_dialog_fragment, null)

        with(servicesListView) {
            services_list.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                adapter = ServicesAdapter(buildServiceItems())
            }

            close_button.setOnClickListener { dismiss() }
        }

        return servicesListView
    }

    private fun buildServiceItems(): List<ServiceItemData> {
        val serviceItems = ArrayList<ServiceItemData>()
        val includedServices = services.filter { it.value }
        val notIncludedServices = services.filter { !it.value }

        serviceItems.add(ServiceItemData(ServiceDialogViewType.TITLE, getString(R.string.publish_view_services_title)))
        ServicesUtils.getStandardServices().forEach { service ->
            includedServices[service]?.let {
                val serviceData = servicesItemBuilder.build(service, it)
                serviceItems.add(ServiceItemData(ServiceDialogViewType.ITEM, serviceData.label, serviceData.description))
            }
        }

        serviceItems.add(ServiceItemData(ServiceDialogViewType.TITLE, getString(R.string.publish_stay_security_title)))
        ServicesUtils.getSecurityServices().forEach { service ->
            includedServices[service]?.let {
                val serviceData = servicesItemBuilder.build(service, it)
                serviceItems.add(ServiceItemData(ServiceDialogViewType.ITEM, serviceData.label, serviceData.description))
            }
        }

        serviceItems.add(ServiceItemData(ServiceDialogViewType.TITLE, getString(R.string.publish_view_installations_title)))
        ServicesUtils.getInstallationsServices().forEach { service ->
            includedServices[service]?.let {
                val serviceData = servicesItemBuilder.build(service, it)
                serviceItems.add(ServiceItemData(ServiceDialogViewType.ITEM, serviceData.label, serviceData.description))
            }
        }

        if (notIncludedServices.isNotEmpty()) {
            serviceItems.add(ServiceItemData(ServiceDialogViewType.TITLE, getString(R.string.publish_view_services_not_included_title)))
            notIncludedServices.keys.forEach {
                val serviceData = servicesItemBuilder.build(it)
                serviceItems.add(ServiceItemData(ServiceDialogViewType.ITEM, serviceData.label, serviceData.description))
            }
        }

        return serviceItems.toList()
    }

}