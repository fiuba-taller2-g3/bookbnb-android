package com.fiuba.bookbnb.ui.fragments.form.adapters

import android.annotation.SuppressLint
import android.view.View
import androidx.core.view.isVisible
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.publish.BedDistribution
import com.fiuba.bookbnb.ui.fragments.dialogs.BedDistributionDialogFragment
import com.fiuba.bookbnb.ui.fragments.dialogs.BedDistributionDialogFragmentDirections
import com.fiuba.bookbnb.ui.fragments.form.data.BedDistributionFormItemData
import com.fiuba.bookbnb.ui.fragments.form.data.FormItemData
import com.fiuba.bookbnb.ui.fragments.form.data.HeaderFormData
import com.fiuba.bookbnb.ui.navigation.NavigationManager
import kotlinx.android.synthetic.main.bookbnb_bed_distribution_item.view.*
import org.apache.commons.lang3.StringUtils

class BedDistributionFormAdapter(private var formItems: List<FormItemData>, headerFormData: HeaderFormData)
    : FormAdapter(formItems, headerFormData, R.layout.bookbnb_bed_distribution_item), BedDistributionDialogFragment.UpdateAdapterListener {

    override fun bindInputView(position: Int, itemView: View) {
        val formItem = formItems[position] as BedDistributionFormItemData
        with(itemView) {
            bed_distribution_title.text = context.getString(R.string.publish_bed_distribution_item_label, formItem.bedDistribution.roomNumber)
            link_text.text = context.getString(getLinkText(this))

            val bedDistributionItemData = formItem.bedDistribution
            loadDescription(this, bedDistributionItemData)

            setOnClickListener {
                NavigationManager.showDialog {
                    BedDistributionDialogFragmentDirections.showBedDistributionDialogFragment(
                        bedDistributionItemData.roomNumber,
                        bedDistributionItemData.singleBeds,
                        bedDistributionItemData.doubleBeds,
                        bedDistributionItemData.cribs,
                        this@BedDistributionFormAdapter)
                }
            }
        }
    }

    private fun loadDescription(itemView: View, formItem: BedDistribution) {
        with(itemView) {
            bed_distribution_subtitle.text = StringUtils.EMPTY
            buildItemDescription(itemView, R.string.publish_single_bed_label, formItem.singleBeds)
            buildItemDescription(itemView, R.string.publish_double_bed_label, formItem.doubleBeds)
            buildItemDescription(itemView, R.string.publish_cribs_bed_label, formItem.cribs)
            bed_distribution_subtitle.isVisible = bed_distribution_subtitle.text.isNotEmpty()
            link_text.text = context.getString(getLinkText(itemView))
        }
    }

    @SuppressLint("SetTextI18n")
    private fun buildItemDescription(itemView: View, stringRes: Int, number: Int) {
        with(itemView) {
            val lineFeed = if (bed_distribution_subtitle.text.isEmpty()) StringUtils.EMPTY else StringUtils.LF
            val descriptionText = if (number == 0) StringUtils.EMPTY else "$lineFeed${context.getString(stringRes)}: $number"
            bed_distribution_subtitle.text = bed_distribution_subtitle.text.toString() + descriptionText
        }
    }

    private fun getLinkText(itemView: View) = if (itemView.bed_distribution_subtitle.text.isEmpty()) {
        R.string.publish_bed_distribution_add_beds_link_text } else { R.string.publish_bed_distribution_edit_link_text }

    override fun update(position: Int, bedDistributionFormItemData: BedDistributionFormItemData) {
        val formItemsUpdated = formItems.toMutableList()
        formItemsUpdated[position] = bedDistributionFormItemData
        formItems = formItemsUpdated
        notifyDataSetChanged()
    }

}