package com.app.zuludin.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.app.zuludin.common.loadUrlImage
import com.app.zuludin.data.model.Categories
import com.app.zuludin.data.model.City
import com.app.zuludin.data.model.RestaurantsItem
import com.app.zuludin.home.R
import kotlinx.android.synthetic.main.item_cafe.view.*
import kotlinx.android.synthetic.main.item_category.view.*
import kotlinx.android.synthetic.main.item_city.view.*

class MoreAdapter(
    private val items: List<Any>,
    private val listener: (city: City?, category: Categories?, restaurant: RestaurantsItem?) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_CITY -> CityViewHolder(
                inflateLayout(R.layout.item_city, parent)
            )
            ITEM_CATEGORY -> CategoryViewHolder(
                inflateLayout(
                    R.layout.item_category,
                    parent
                )
            )
            ITEM_RESTAURANT -> FeatureViewHolder(
                inflateLayout(R.layout.item_cafe, parent)
            )
            else -> throw IllegalArgumentException("Undefined itemViewType")
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            ITEM_CITY -> {
                val viewHolder = holder as CityViewHolder
                val city = items[position] as City
                viewHolder.bind(city)
                viewHolder.itemView.setOnClickListener { listener(city, null, null) }
            }
            ITEM_CATEGORY -> {
                val viewHolder = holder as CategoryViewHolder
                val category = items[position] as Categories
                viewHolder.bind(category)
                viewHolder.itemView.setOnClickListener { listener(null, category, null) }
            }
            ITEM_RESTAURANT -> {
                val viewHolder = holder as FeatureViewHolder
                val res = items[position] as RestaurantsItem
                viewHolder.bind(res)
                viewHolder.itemView.setOnClickListener { listener(null, null, res) }
            }
            else -> throw IllegalArgumentException("Undefined itemViewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is Categories -> ITEM_CATEGORY
            is City -> ITEM_CITY
            is RestaurantsItem -> ITEM_RESTAURANT
            else -> throw IllegalArgumentException("Undefined itemViewType")
        }
    }

    private fun inflateLayout(@LayoutRes layoutId: Int, parent: ViewGroup): View {
        return LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
    }

    companion object {
        private const val ITEM_CITY = 0
        private const val ITEM_CATEGORY = 1
        private const val ITEM_RESTAURANT = 2
    }
}

class CityViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(city: City) {
        itemView.city_name.text = city.name
        itemView.city_image.setImageResource(city.image)
    }
}

class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(category: Categories) {
        itemView.category_name.text = category.name
        itemView.category_icon.setImageResource(category.icon)
    }
}

class FeatureViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(restaurant: RestaurantsItem) {
        restaurant.restaurant?.let {
            itemView.cafe_image.loadUrlImage(it.thumb)
            itemView.cafe_name.text = it.name
            itemView.cafe_location.text = it.location?.address
            itemView.cafe_cuisine.text = it.cuisines
        }
    }
}