package com.app.zuludin.home.view

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.agrawalsuneet.squareloaderspack.loaders.WaveLoader
import com.app.zuludin.data.model.Categories
import com.app.zuludin.data.model.City
import com.app.zuludin.data.model.MoreData
import com.app.zuludin.data.model.RestaurantsItem
import com.app.zuludin.data.utils.Resource
import com.app.zuludin.home.R
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
    fun setHomeRecycler(recyclerView: RecyclerView, items: Resource<List<RestaurantsItem>>?) {
        with(recyclerView.adapter as HomeLayoutAdapter) {
            items?.data?.let { addItemList(addData(it)) }
        }
    }

    private fun addData(restaurants: List<RestaurantsItem>): List<Any> {
        val cities = listOf(
            City("Jakarta", "74", R.drawable.monas),
            City("London", "61", R.drawable.london),
            City("Kuala Lumpur", "88", R.drawable.kuala_lumpur),
            City("Singapore", "52", R.drawable.singapore),
            City("New York", "280", R.drawable.new_york),
            City("Melbourne", "259", R.drawable.melbourne)
        )

        val category = listOf(
            Categories("5", "Takeaway", R.drawable.ic_takeaway),
            Categories("6", "Cafes", R.drawable.ic_cafes),
            Categories("8", "Breakfast", R.drawable.ic_breakfast),
            Categories("9", "Lunch", R.drawable.ic_lunch),
            Categories("10", "Dinner", R.drawable.ic_dinner),
            Categories("11", "Pubs", R.drawable.ic_pubs)
        )

        return listOf(
            "Popular City",
            MoreData(cities, true),
            "Category",
            MoreData(category, true),
            "Featured Restaurant",
            MoreData(restaurants, false)
        )
    }
}