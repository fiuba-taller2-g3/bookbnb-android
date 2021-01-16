package com.fiuba.bookbnb.utils

import android.animation.ValueAnimator
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation

object AnimUtils {

    fun fadeIn(mSec: Long) = AlphaAnimation(0.0f, 1.0f).also { it.duration = mSec }

    fun expandLayout(view: View) {
        val widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        val heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        view.measure(widthSpec, heightSpec)
        val animator = slideAnimator(0, view.measuredHeight, view)
        animator.start()
    }

    fun collapseLayout(view: View) {
        val finalHeight: Int = view.height
        val animator = slideAnimator(finalHeight, 0, view)
        animator.start()
    }

    private fun slideAnimator(start: Int, end: Int, view: View): ValueAnimator {
        val animator = ValueAnimator.ofInt(start, end)
        animator.addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Int
            val layoutParams: ViewGroup.LayoutParams = view.layoutParams
            layoutParams.height = value
            view.layoutParams = layoutParams
        }
        return animator
    }
}