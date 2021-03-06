package com.fiuba.bookbnb.ui.fragments.publish.publishView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.publish.BedDistribution
import kotlinx.android.synthetic.main.bookbnb_publish_view_bed_distribution_item.view.*
import java.util.*

class PublishViewBedDistributionItem @JvmOverloads constructor(context: Context,
                                                               private val bedDistribution: BedDistribution,
                                                               attrs: AttributeSet? = null,
                                                               defStyleAttr: Int = 0)
    : LinearLayout(context, attrs, defStyleAttr) {

    private val bedroomDescription = StringJoiner(", ")

    init {
        LayoutInflater.from(context).inflate(R.layout.bookbnb_publish_view_bed_distribution_item, this)
        setLayoutParams()
        bedroom_title.text = context.getString(R.string.publish_bed_distribution_item_label, bedDistribution.roomNumber)
        bedroom_description.text = buildBedroomDescription()
    }

    private fun buildBedroomDescription(): String {
        buildSingleBedText()
        buildDoubleBedText()
        buildCribsText()

        return bedroomDescription.toString()
    }

    private fun buildSingleBedText() {
        return buildBedTypeText(bedDistribution.singleBeds, R.string.publish_view_single_bed_singular_label, R.string.publish_view_single_bed_plural_label)
    }

    private fun buildDoubleBedText() {
        return buildBedTypeText(bedDistribution.doubleBeds, R.string.publish_view_double_bed_singular_label, R.string.publish_view_double_bed_plural_label)
    }

    private fun buildCribsText() {
        return buildBedTypeText(bedDistribution.cribs, R.string.publish_view_cribs_bed_singular_label, R.string.publish_view_cribs_bed_plural_label)
    }

    private fun buildBedTypeText(number: Int, singularStringRes: Int, pluralStringRes: Int) {
        if (haveBeds(number)) {
            bedroomDescription.add(context.getString(if (isSingular(number)) singularStringRes else pluralStringRes, number))
        }
    }

    private fun isSingular(number: Int) = number == 1

    private fun haveBeds(number: Int) = number > 0

    private fun setLayoutParams() {
        layoutParams = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }
}