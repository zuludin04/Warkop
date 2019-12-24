package com.app.zuludin.home.view

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.app.zuludin.data.utils.Resource
import com.app.zuludin.home.adapter.HomeLayoutAdapter

object HomeBinding {

    @BindingAdapter("app:showProgress")
    @JvmStatic
    fun setShowProgress(swipeRefreshLayout: SwipeRefreshLayout, status: Resource.Status?) {
        status?.let {
            swipeRefreshLayout.isRefreshing = it == Resource.Status.LOADING
        }
    }

    @BindingAdapter("app:homeRecycler")
    @JvmStatic
    fun setHomeRecycler(recyclerView: RecyclerView, items: Resource<List<Any>>?) {
        with(recyclerView.adapter as HomeLayoutAdapter) {
            items?.data?.let { addItemList(it) }
        }
    }
}