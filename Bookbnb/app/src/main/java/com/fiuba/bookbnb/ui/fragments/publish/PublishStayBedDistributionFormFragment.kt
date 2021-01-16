package com.fiuba.bookbnb.ui.fragments.publish

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.ui.fragments.form.FormListFragment
import com.fiuba.bookbnb.ui.fragments.form.adapters.BedDistributionFormAdapter
import com.fiuba.bookbnb.ui.fragments.form.adapters.FormAdapter
import com.fiuba.bookbnb.ui.fragments.form.data.BedDistributionFormItemData
import com.fiuba.bookbnb.ui.fragments.form.data.FormItemData
import com.fiuba.bookbnb.ui.fragments.form.data.HeaderFormData
import com.fiuba.bookbnb.ui.fragments.form.data.InputViewType
import com.fiuba.bookbnb.ui.fragments.publish.PublishInputUtils.MAX_LIMIT_BEDROOMS
import com.fiuba.bookbnb.ui.navigation.NavigationManager
import kotlinx.android.synthetic.main.bookbnb_form_list_fragment.*

class PublishStayBedDistributionFormFragment : FormListFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        empty_list_text.text = getString(R.string.publish_bed_distribution_empty_text)
        empty_list_text.isVisible = formViewModel.isBedroomsQuantityEmpty()
    }

    override fun getFormAdapter(): FormAdapter {
        return BedDistributionFormAdapter(getFormItems(), HeaderFormData(getString(getTitleTextRes()), getString(getSubtitleTextRes())))
    }

    override fun getFormItems(): List<FormItemData> {
        val formInputItems = ArrayList<FormItemData>()
        formInputItems.add(FormItemData(InputViewType.HEADER))
        for (i in 1 until MAX_LIMIT_BEDROOMS + 1) {
            formInputItems.add(BedDistributionFormItemData(formViewModel.loadBedDistributionContent(i)))
        }

        return formViewModel.loadBedDistributionItems(formInputItems)
    }

    override fun getTitleTextRes(): Int = R.string.publish_bed_distribution_title
    override fun getSubtitleTextRes(): Int = R.string.publish_bed_distribution_subtitle
    override fun getButtonTextRes(): Int = R.string.next_button_text

    override fun setActionEventButton() {
        NavigationManager.moveForward(PublishStayBedDistributionFormFragmentDirections.actionPublishStayBedDistributionFormFragmentToPublishStayLocationStepFormFragment())
    }

}