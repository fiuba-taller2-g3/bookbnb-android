package com.fiuba.bookbnb.ui.fragments.publish.publishView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.fiuba.bookbnb.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.bookbnb_publish_img_item.view.*
import java.util.*
import kotlin.collections.ArrayList


class ViewPagerAdapter(private val images: ArrayList<String>) : PagerAdapter() {

    override fun getCount() = images.size

    override fun isViewFromObject(view: View, `object`: Any) = view === `object` as LinearLayout

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView = LayoutInflater.from(container.context).inflate(R.layout.bookbnb_publish_img_item, container, false)

        with(itemView) {
            Picasso.get()
                .load(images[position])
                .placeholder(R.drawable.ic_photoimgdefault)
                .error(R.drawable.ic_photoimgdefault)
                .into(publish_view_img)
        }

        Objects.requireNonNull(container).addView(itemView);

        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout?)
    }

}