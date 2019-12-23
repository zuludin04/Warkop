package com.app.zuludin.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.zuludin.detail.R
import kotlinx.android.synthetic.main.item_facility.view.*

class FacilityAdapter(private val facilities: MutableList<String>) :
    RecyclerView.Adapter<FacilityViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacilityViewHolder =
        FacilityViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_facility,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = facilities.size

    override fun onBindViewHolder(holder: FacilityViewHolder, position: Int) {
        holder.bind(facilities[position])
    }

    fun addFacilities(list: List<String>?) {
        list?.let { facilities.addAll(it) }
        notifyDataSetChanged()
    }
}

class FacilityViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(facility: String) {
        itemView.facility_text.text = facility
    }
}