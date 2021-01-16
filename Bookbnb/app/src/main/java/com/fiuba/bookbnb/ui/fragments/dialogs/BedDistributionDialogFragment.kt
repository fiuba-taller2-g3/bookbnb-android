package com.fiuba.bookbnb.ui.fragments.dialogs

import android.content.DialogInterface
import android.widget.LinearLayout
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.forms.FormInputData
import com.fiuba.bookbnb.forms.FormInputType
import com.fiuba.bookbnb.forms.FormViewModel
import com.fiuba.bookbnb.forms.InputItemsManager
import com.fiuba.bookbnb.ui.fragments.form.data.BedDistributionFormItemData
import java.io.Serializable
import java.util.*

class BedDistributionDialogFragment : AbstractDialogFragment() {

    private val navArguments by navArgs<BedDistributionDialogFragmentArgs>()
    private val roomNumber by lazy { navArguments.room }
    private val singleBeds by lazy { navArguments.singleBeds }
    private val doubleBeds by lazy { navArguments.doubleBeds }
    private val cribs by lazy { navArguments.cribs }
    private val updateAdapterListener by lazy { navArguments.updateAdapterListener }

    private val formViewModel : FormViewModel by activityViewModels()
    private val inputItemsManager by lazy { InputItemsManager(requireContext(), ::storeInputContent) }

    override fun getDialogTitleRes(): Int = R.string.publish_bed_distribution_dialog_title

    override fun getItemList(): List<LinearLayout> {
        val list = ArrayList<LinearLayout>()
        formViewModel.loadInputItems(getInputList()).forEach { inputItem ->
            list.add(inputItemsManager.getInputModule(FormInputData(inputItem.key, inputItem.value)))
        }
        return list
    }

    private fun getInputList(): List<FormInputData> {
        return listOf(
            FormInputData(FormInputType.SINGLE_BEDS, singleBeds.toString()),
            FormInputData(FormInputType.DOUBLE_BEDS, doubleBeds.toString()),
            FormInputData(FormInputType.CRIBS, cribs.toString())
        )
    }

    override fun setAcceptFunction() {
        getInputList().forEach { updateBedInput(it.inputField) }
        dismiss()
    }

    private fun updateBedInput(formInputType: FormInputType) {
        val index = roomNumber - 1
        val bedContent = formViewModel.getContentFromItem(formInputType).toInt()
        val bedDistributionItemData = formViewModel.getBedDistributionItem(index).bedDistribution
        when(formInputType) {
            FormInputType.SINGLE_BEDS -> bedDistributionItemData.singleBeds = bedContent
            FormInputType.DOUBLE_BEDS -> bedDistributionItemData.doubleBeds = bedContent
            FormInputType.CRIBS -> bedDistributionItemData.cribs = bedContent
            else -> throw Exception("Error: The input $formInputType is not a bed type!")
        }

        BedDistributionFormItemData(bedDistributionItemData).run {
            formViewModel.updateBedInputItem(index, this)
            updateAdapterListener.update(roomNumber, this)
        }
    }

    private fun storeInputContent(inputData: FormInputData) {
        formViewModel.updateInputItem(inputData)
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        getInputList().forEach {
            formViewModel.clearInput(it.inputField)
        }
    }

    interface UpdateAdapterListener : Serializable {
        fun update(position: Int, bedDistributionFormItemData: BedDistributionFormItemData)
    }

}