package com.fiuba.bookbnb.ui.fragments.publish.publishView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import com.fiuba.bookbnb.R

class PublishViewBedDistributionEmptyItem @JvmOverloads constructor(context: Context,
                                                                    attrs: AttributeSet? = null,
                                                                    defStyleAttr: Int = 0)
    : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.bookbnb_publish_view_bed_distribution_item, this)
        setLayoutParams()
    }

    private fun setLayoutParams() {
        layoutParams = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }
}