package com.app.zuludin.list.adapter

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.zuludin.common.loadUrlImage
import com.app.zuludin.data.model.Restaurant
import com.app.zuludin.data.model.RestaurantsItem
import com.app.zuludin.list.R
import com.app.zuludin.list.databinding.ItemRestaurantBinding

class CafeListAdapter(
    private val cafes: MutableList<RestaurantsItem>,
    private val listener: (cafeId: String, title: String) -> Unit
) : RecyclerView.Adapter<CafeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CafeViewHolder {
        val binding: ItemRestaurantBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_restaurant,
            parent,
            false
        )
        return CafeViewHolder(binding)
    }

    override fun getItemCount(): Int = cafes.size

    override fun onBindViewHolder(holder: CafeViewHolder, position: Int) {
        holder.bind(cafes[position].restaurant, listener)
    }

    fun addItems(list: List<RestaurantsItem>?) {
        list?.let { cafes.addAll(it) }
        notifyDataSetChanged()
    }
}

class CafeViewHolder(private val binding: ItemRestaurantBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(restaurant: Restaurant?, listener: (cafeId: String, title: String) -> Unit) {
        with(binding) {
            cafe = restaurant
            executePendingBindings()
        }
        binding.cafeImage.loadUrlImage(restaurant?.thumb)
        binding.cafeRating.setTextColor(Color.parseColor("#${restaurant?.userRating?.ratingColor}"))
        val drawable = binding.cafeRating.compoundDrawables[0].mutate()
        drawable.colorFilter = PorterDuffColorFilter(
            Color.parseColor("#${restaurant?.userRating?.ratingColor}"),
            PorterDuff.Mode.SRC_IN
        )
        itemView.setOnClickListener {
            restaurant?.id?.let { resId ->
                listener(
                    resId,
                    restaurant.name.toString()
                )
            }
        }
    }
}