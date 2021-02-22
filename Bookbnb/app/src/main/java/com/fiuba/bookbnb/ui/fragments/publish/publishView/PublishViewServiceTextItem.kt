package com.fiuba.bookbnb.ui.fragments.publish.publishView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.isVisible
import com.fiuba.bookbnb.R
import kotlinx.android.synthetic.main.bookbnb_publish_view_service_item.view.*

class PublishViewServiceTextItem @JvmOverloads constructor(context: Context,
                                                           serviceName: String,
                                                           serviceDescription: String?,
                                                           attrs: AttributeSet? = null,
                                                           defStyleAttr: Int = 0)
    : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.bookbnb_publish_view_service_item, this)
        setLayoutParams()
        service_name.text = serviceName
        service_description.isVisible = serviceDescription != null
        service_description.text = serviceDescription
    }

    private fun setLayoutParams() {
        layoutParams = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        orientation = VERTICAL
    }
}