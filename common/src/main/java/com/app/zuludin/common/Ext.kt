package com.app.zuludin.common

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.widget.ImageView
import android.widget.TextView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import java.net.URLDecoder

fun String.urlDecode(): String = URLDecoder.decode(this, "utf8")

fun ImageView.loadUrlImage(url: String?) {
    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()

    Glide.with(context).load(url).placeholder(circularProgressDrawable).into(this)
}

fun TextView.changeRatingColor(color: String) {
//    setTextColor(Color.parseColor("#${color.toString()}"))
    val drawable = compoundDrawables[0].mutate()
    drawable.colorFilter =
        PorterDuffColorFilter(Color.parseColor("#$color"), PorterDuff.Mode.SRC_IN)
}