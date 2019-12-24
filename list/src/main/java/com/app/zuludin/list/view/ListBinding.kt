package com.app.zuludin.list.view

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.agrawalsuneet.squareloaderspack.loaders.WaveLoader
import com.app.zuludin.data.model.RestaurantsItem
import com.app.zuludin.data.utils.Resource
import com.app.zuludin.list.adapter.CafeListAdapter

object ListBinding {
    @BindingAdapter("app:showLoadingProgress")
    @JvmStatic
    fun showLoadingProgress(view: WaveLoader, status: Resource.Status?) {
        status?.let {
            view.visibility = if (it == Resource.Status.LOADING) View.VISIBLE else View.GONE
        }
    }

    @BindingAdapter("app:recyclerRestaurant")
    @JvmStatic
    fun loadRecyclerRestaurant(recyclerView: RecyclerView, resource: Resource<List<RestaurantsItem>>?) {
        with(recyclerView.adapter as CafeListAdapter) {
            resource?.data?.let { addItems(it) }
        }
    }
}