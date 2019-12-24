package com.app.zuludin.list.view

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.app.zuludin.data.model.RestaurantsItem
import com.app.zuludin.data.utils.Resource
import com.app.zuludin.list.adapter.CafeListAdapter

object ListBinding {

    @BindingAdapter("app:recyclerRestaurant")
    @JvmStatic
    fun loadRecyclerRestaurant(
        recyclerView: RecyclerView,
        resource: Resource<List<RestaurantsItem>>?
    ) {
        with(recyclerView.adapter as CafeListAdapter) {
            resource?.data?.let { addItems(it) }
        }
    }

    @BindingAdapter("app:showRefreshProgress")
    @JvmStatic
    fun showRefreshProgress(view: SwipeRefreshLayout, status: Resource.Status?) {
        status?.let {
            view.isRefreshing = it == Resource.Status.LOADING
        }
    }
}