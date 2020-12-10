package com.fiuba.bookbnb.ui.fragments.footerbar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.fiuba.bookbnb.R
import kotlinx.android.synthetic.main.bookbnb_footerbar_menu_item.view.*

abstract class FooterBarMenuItem @JvmOverloads constructor(context: Context, labelRes: Int, imgRes: Int, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : LinearLayout(context, attrs, defStyleAttr) {

    private var isActive = false

    init {
        LayoutInflater.from(context).inflate(R.layout.bookbnb_footerbar_menu_item, this)
        setLayoutParams()
        footer_bar_menu_item.text = context.getString(labelRes)
        footer_bar_menu_img.setImageResource(imgRes)
        footer_bar_menu_button.setOnClickListener { if (!isActive) setButtonListener() }
    }

    private fun setLayoutParams() {
        layoutParams = LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            1f
        )
    }

    fun activeButton() {
        isActive = true
        footer_bar_menu_img.setColorFilter(ContextCompat.getColor(context, R.color.colorRed))
        footer_bar_menu_item.setTextColor(ContextCompat.getColor(context, R.color.colorFooterBarMenuTextButtonSelected))
    }

    fun disableButton() {
        isActive = false
        footer_bar_menu_img.setColorFilter(ContextCompat.getColor(context, R.color.colorDescription))
        footer_bar_menu_item.setTextColor(ContextCompat.getColor(context, R.color.colorDescription))
    }

    protected abstract fun setButtonListener()

}