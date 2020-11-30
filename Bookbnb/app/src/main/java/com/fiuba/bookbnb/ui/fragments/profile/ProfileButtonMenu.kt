package com.fiuba.bookbnb.ui.fragments.profile

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import com.fiuba.bookbnb.R
import kotlinx.android.synthetic.main.bookbnb_button_profile_menu.view.*

abstract class ProfileButtonMenu(context: Context, labelRes: Int, img: Int, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.bookbnb_button_profile_menu, this)
        layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        profile_option_text.text = context.getString(labelRes)
        profile_option_img.setImageResource(img)
    }

    protected abstract fun loadAction()

}