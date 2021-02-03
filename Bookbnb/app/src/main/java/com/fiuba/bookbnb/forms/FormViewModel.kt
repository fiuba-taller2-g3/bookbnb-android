package com.fiuba.bookbnb.forms

import android.location.Address
import androidx.lifecycle.ViewModel
import com.fiuba.bookbnb.domain.publish.BedDistribution
import com.fiuba.bookbnb.ui.fragments.form.data.BedDistributionFormItemData
import com.fiuba.bookbnb.ui.fragments.form.data.FormItemData
import com.fiuba.bookbnb.ui.fragments.form.data.PhotoFormItemData
import com.fiuba.bookbnb.ui.fragments.form.data.ServicesFormItemData
import com.fiuba.bookbnb.ui.fragments.form.services.Services
import java.util.*

class FormViewModel : ViewModel() {

    private val formInputItems by lazy { EnumMap<FormInputType, String>(FormInputType::class.java) }
    private val bedDistributionItems by lazy { ArrayList<BedDistributionFormItemData>() }
    private val services by lazy { EnumMap<Services, Boolean>(Services::class.java) }
    val photosUrls by lazy { ArrayList<PhotoFormItemData>() }

    var locationInfo : Address? = null

    private fun setInputItems(inputItems: List<FormInputData>) {
        inputItems.forEach { inputItem ->
            if (!formInputItems.containsKey(inputItem.inputField)) {
                formInputItems[inputItem.inputField] = inputItem.content
            }
        }
    }

    fun loadInputItems(inputItems: List<FormInputData>): Map<FormInputType, String> {
        setInputItems(inputItems)
        val inputKeys = mutableListOf<FormInputType>().also { list ->
            inputItems.forEach { list.add(it.inputField) }
        }
        return formInputItems.filterKeys { inputItem -> inputKeys.any { it == inputItem } }
    }

    fun loadBedDistributionItems(formItems: List<FormItemData>): List<FormItemData> {
        formItems.forEach { item ->
            if (item is BedDistributionFormItemData) {
                if (bedDistributionItems.size < item.bedDistribution.roomNumber) {
                    bedDistributionItems.add(item)
                }
            }
        }
        val bedroomsQuantity = getBedroomsQuantity()
        return formItems.subList(0, bedroomsQuantity + 1)
    }

    fun loadServicesItems(formItems: List<FormItemData>): List<FormItemData> {
        formItems.forEach { item ->
            if (item is ServicesFormItemData) {
                if (!services.containsKey(item.service)) {
                    services[item.service] = item.isChecked
                }
            }
        }

        return formItems
    }

    fun getValueFromService(service: Services) : Boolean = services[service] ?: false

    fun getContentFromItem(formInputType: FormInputType) = formInputItems[formInputType] ?: throw Exception("Error: Input $formInputType is not found!")

    fun updateInputItem(inputData: FormInputData) {
        formInputItems.replace(inputData.inputField, inputData.content)
    }

    fun updateBedInputItem(index: Int, bedDistributionFormItemData: BedDistributionFormItemData) {
        bedDistributionItems[index] = bedDistributionFormItemData
    }

    fun updateServiceItem(service: Services, value: Boolean) {
        services[service] = value
    }

    fun getBedDistributionItem(index: Int) = bedDistributionItems[index]

    fun loadBedDistributionContent(roomNumber: Int) : BedDistribution {
        val index = roomNumber-1
        return if (index < bedDistributionItems.size) {
            bedDistributionItems[index].bedDistribution
        } else BedDistribution(roomNumber)
    }

    fun clearInputs() {
        formInputItems.clear()
        bedDistributionItems.clear()
        services.clear()
        photosUrls.clear()
        locationInfo = null
    }

    fun clearInput(formInputType: FormInputType) = formInputItems.remove(formInputType)

    private fun getBedroomsQuantity() = formInputItems[FormInputType.BEDROOM_QUANTITY]?.toInt() ?: throw Exception("Error: Input ${FormInputType.BEDROOM_QUANTITY.name} is not found!")

    fun isBedroomsQuantityEmpty() = getBedroomsQuantity() == 0

}