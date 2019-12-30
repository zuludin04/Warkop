package com.app.zuludin.detail.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.zuludin.common.loadUrlImage
import com.app.zuludin.data.model.review.UserReviewsItem
import com.app.zuludin.detail.R
import kotlinx.android.synthetic.main.item_review.view.*

class ReviewAdapter(private val reviews: MutableList<UserReviewsItem>) : RecyclerView.Adapter<ReviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        return ReviewViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false))
    }

    override fun getItemCount(): Int = reviews.size

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(reviews[position])
    }

    fun addItems(list: List<UserReviewsItem>) {
        reviews.addAll(list)
        notifyDataSetChanged()
    }
}

class ReviewViewHolder(view: View): RecyclerView.ViewHolder(view) {
    fun bind(review: UserReviewsItem) {
        review.review?.let {
            itemView.review_name.text = it.user?.name

            val comment = if (it.reviewText != null) it.reviewText else "-"

            itemView.review_comment.text = comment
            itemView.review_rating.text = "${it.rating}"
            itemView.review_rating.setBackgroundColor(Color.parseColor("#${it.ratingColor}"))
            itemView.review_time.text = it.reviewTimeFriendly
            itemView.review_image.loadUrlImage(it.user?.profileImage)
        }
    }
}