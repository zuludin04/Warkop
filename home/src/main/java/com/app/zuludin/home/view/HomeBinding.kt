package com.app.zuludin.home.view

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.agrawalsuneet.squareloaderspack.loaders.WaveLoader
import com.app.zuludin.data.utils.Resource
import com.app.zuludin.home.adapter.HomeLayoutAdapter

object HomeBinding {

    @BindingAdapter("app:showProgress")
    @JvmStatic
    fun setShowProgress(waveLoader: WaveLoader, status: Resource.Status?) {
        status?.let {
            waveLoader.visibility = if (it == Resource.Status.LOADING) View.VISIBLE else View.GONE
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