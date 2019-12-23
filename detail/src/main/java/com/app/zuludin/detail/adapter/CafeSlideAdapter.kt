package com.app.zuludin.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.app.zuludin.common.loadUrlImage
import com.app.zuludin.data.model.detail.PhotosItem
import com.app.zuludin.detail.R
import kotlinx.android.synthetic.main.item_cafe_slide.view.*

class CafeSlideAdapter(private val urlList: List<PhotosItem>) : PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int = urlList.size

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context)
            .inflate(R.layout.item_cafe_slide, container, false)

        view.cafe_image.loadUrlImage(urlList[position].photo?.url)

        container.addView(view)
        return view
    }
}