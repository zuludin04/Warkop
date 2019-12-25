package com.app.zuludin.detail.view

import android.view.View
import androidx.databinding.BindingAdapter
import com.agrawalsuneet.squareloaderspack.loaders.WaveLoader
import com.app.zuludin.data.utils.Resource

object DetailBinding {
    @BindingAdapter("app:showLoadingProgress")
    @JvmStatic
    fun showLoadingProgress(waveLoader: WaveLoader, status: Resource.Status?) {
        status?.let {
            waveLoader.visibility = if (it == Resource.Status.LOADING) View.VISIBLE else View.GONE
        }
    }
}