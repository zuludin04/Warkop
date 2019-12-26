package com.app.zuludin.search.view

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.agrawalsuneet.squareloaderspack.loaders.WaveLoader
import com.app.zuludin.data.model.RestaurantsItem
import com.app.zuludin.data.utils.Resource

object SearchBinding {
    @BindingAdapter("app:showProgress")
    @JvmStatic
    fun setShowProgress(wave: WaveLoader, status: Resource.Status?) {
        status?.let {
            wave.visibility = if (it == Resource.Status.LOADING) View.VISIBLE else View.GONE
        }
    }

    @BindingAdapter("app:searchItems")
    @JvmStatic
    fun setSearchItems(recyclerView: RecyclerView, items: Resource<List<RestaurantsItem>>?) {
        with(recyclerView.adapter as SearchRestaurantAdapter) {
            items?.data?.let { addItems(it) }
        }
    }
}