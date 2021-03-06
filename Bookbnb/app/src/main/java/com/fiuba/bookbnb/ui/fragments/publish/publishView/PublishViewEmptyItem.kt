package com.fiuba.bookbnb.ui.fragments.publish.publishView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import com.fiuba.bookbnb.R
import kotlinx.android.synthetic.main.bookbnb_publish_view_empty_item_text.view.*

class PublishViewEmptyItem @JvmOverloads constructor(context: Context,
                                                     text: String,
                                                     attrs: AttributeSet? = null,
                                                     defStyleAttr: Int = 0)
    : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.bookbnb_publish_view_empty_item_text, this)
        bedroom_empty_text.text = text
        setLayoutParams()
    }

    private fun setLayoutParams() {
        layoutParams = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }
}