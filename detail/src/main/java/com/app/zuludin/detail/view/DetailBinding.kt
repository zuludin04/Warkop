package com.app.zuludin.detail.view

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.app.zuludin.data.utils.Resource

object DetailBinding {
    @BindingAdapter("app:showLoadingProgress")
    @JvmStatic
    fun showLoadingProgress(view: SwipeRefreshLayout, status: Resource.Status?) {
        status?.let {
            view.isRefreshing = it == Resource.Status.LOADING
        }
    }
}