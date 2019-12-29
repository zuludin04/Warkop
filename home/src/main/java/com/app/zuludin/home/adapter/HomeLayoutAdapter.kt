package com.app.zuludin.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.zuludin.common.EqualSpacingItemDecoration
import com.app.zuludin.data.model.Categories
import com.app.zuludin.data.model.City
import com.app.zuludin.data.model.MoreData
import com.app.zuludin.data.model.RestaurantsItem
import com.app.zuludin.home.R
import kotlinx.android.synthetic.main.item_header.view.*
import kotlinx.android.synthetic.main.item_recycler.view.*

class HomeLayoutAdapter(
    private val items: MutableList<Any>,
    private val listener: (city: City?, category: Categories?, restaurant: RestaurantsItem?) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val recyclerPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_HEADER -> HeaderViewHolder(
                inflateLayout(R.layout.item_header, parent)
            )
            ITEM_MORE -> MoreViewHolder(
                inflateLayout(
                    R.layout.item_recycler,
                    parent
                )
            )
            else -> throw IllegalArgumentException("Undefined itemViewType")
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            ITEM_HEADER -> {
                val viewHolder = holder as HeaderViewHolder
                viewHolder.bind(items[position] as String)
            }
            ITEM_MORE -> {
                val viewHolder = holder as MoreViewHolder
                viewHolder.bind(items[position] as MoreData, listener)
                viewHolder.itemView.recycler_more.setRecycledViewPool(recyclerPool)
            }
            else -> throw IllegalArgumentException("Undefined itemViewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is String -> ITEM_HEADER
            is MoreData -> ITEM_MORE
            else -> throw IllegalArgumentException("Undefined itemViewType")
        }
    }

    private fun inflateLayout(@LayoutRes layoutId: Int, parent: ViewGroup): View {
        return LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
    }

    fun addItemList(list: List<Any>?) {
        list?.let { items.addAll(it) }
        notifyDataSetChanged()
    }

    companion object {
        private const val ITEM_HEADER = 0
        private const val ITEM_MORE = 1
    }
}

class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(title: String) {
        itemView.header_text.text = title
    }
}

class MoreViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {
    fun bind(
        more: MoreData,
        listener: (city: City?, category: Categories?, restaurant: RestaurantsItem?) -> Unit
    ) {
        val itemDecoration = EqualSpacingItemDecoration(12, EqualSpacingItemDecoration.VERTICAL)
        itemView.recycler_more.apply {
            layoutManager = if (more.isHorizontal) LinearLayoutManager(
                itemView.context,
                LinearLayoutManager.HORIZONTAL,
                false
            ) else LinearLayoutManager(itemView.context)
            if (!more.isHorizontal) addItemDecoration(itemDecoration)
            setHasFixedSize(true)
            adapter = MoreAdapter(more.items, listener)
        }
    }
}