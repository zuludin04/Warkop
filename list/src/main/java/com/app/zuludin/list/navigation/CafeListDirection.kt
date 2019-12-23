package com.app.zuludin.list.navigation

import android.os.Bundle
import androidx.navigation.NavDirections
import com.app.zuludin.list.R

class CafeListDirection private constructor() {
    private data class CafeDetailLayout(val detail: String, val title: String?) : NavDirections {
        override fun getActionId(): Int {
            return R.id.action_cafeListFragment_to_cafeDetailFragment
        }

        override fun getArguments(): Bundle {
            val args = Bundle()
            args.putString("detailCafe", this.detail)
            args.putString("detailTitle", this.title)
            return args
        }
    }

    companion object {
        fun detailLayout(detail: String, title: String?): NavDirections =
            CafeDetailLayout(detail, title)
    }
}