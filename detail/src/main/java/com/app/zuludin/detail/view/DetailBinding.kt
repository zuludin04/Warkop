package com.app.zuludin.detail.view

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.agrawalsuneet.squareloaderspack.loaders.WaveLoader
import com.app.zuludin.data.model.detail.DetailCafeResponse
import com.app.zuludin.data.model.review.UserReviewsItem
import com.app.zuludin.data.utils.Resource
import com.app.zuludin.detail.R
import com.app.zuludin.detail.adapter.ReviewAdapter

object DetailBinding {
    @BindingAdapter("app:showLoadingProgress")
    @JvmStatic
    fun showLoadingProgress(waveLoader: WaveLoader, status: Resource.Status?) {
        status?.let {
            waveLoader.visibility = if (it == Resource.Status.LOADING) View.VISIBLE else View.GONE
        }
    }

    @BindingAdapter("app:averageCost")
    @JvmStatic
    fun setAverageCost(textView: TextView, detail: DetailCafeResponse?) {
        detail?.let {
            val average = textView.context.getString(R.string.cost, it.currency, it.averageCostForTwo.toString())
            textView.text = average
        }
    }

    @BindingAdapter("app:restaurantReviews")
    @JvmStatic
    fun setRestaurantReviews(recyclerView: RecyclerView, source: Resource<List<UserReviewsItem>>?) {
        source?.data?.let {
            with(recyclerView.adapter as ReviewAdapter) {
                addItems(it)
            }
        }
    }
}