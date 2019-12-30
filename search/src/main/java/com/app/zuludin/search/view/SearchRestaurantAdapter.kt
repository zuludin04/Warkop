package com.app.zuludin.search.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.zuludin.common.loadUrlImage
import com.app.zuludin.data.model.RestaurantsItem
import com.app.zuludin.search.R
import kotlinx.android.synthetic.main.item_restaurant_result.view.*

class SearchRestaurantAdapter(
    private val items: MutableList<RestaurantsItem>,
    private val listener: (restaurant: RestaurantsItem) -> Unit
) : RecyclerView.Adapter<SearchViewModel>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewModel {
        return SearchViewModel(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_restaurant_result,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SearchViewModel, position: Int) {
        holder.bind(items[position], listener)
    }

    fun addItems(list: List<RestaurantsItem>) {
        items.addAll(list)
        notifyDataSetChanged()
    }
}

class SearchViewModel(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(restaurant: RestaurantsItem, listener: (restaurant: RestaurantsItem) -> Unit) {
        itemView.result_image.loadUrlImage(restaurant.restaurant?.thumb)
        itemView.result_title.text = restaurant.restaurant?.name
        itemView.setOnClickListener { listener(restaurant) }
    }
}