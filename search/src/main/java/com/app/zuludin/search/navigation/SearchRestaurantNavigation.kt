package com.app.zuludin.search.navigation

import android.os.Bundle
import androidx.navigation.NavDirections
import com.app.zuludin.search.R

class SearchRestaurantNavigation private constructor() {
    private data class ToDetail(val resId: String?, val title: String?): NavDirections {
        override fun getArguments(): Bundle {
            val args = Bundle()
            args.putString("detailCafe", this.resId)
            args.putString("detailTitle", this.title)
            return args
        }

        override fun getActionId(): Int = R.id.detailFromSearch
    }

    companion object {
        fun detailLayout(resId: String?, title: String?): NavDirections = ToDetail(resId, title)
    }
}