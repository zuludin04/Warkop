package com.app.zuludin.home.navigation

import android.os.Bundle
import androidx.navigation.NavDirections
import com.app.zuludin.home.R

class HomeNavigation private constructor() {
    private data class CafeListLayout(val body: String, val title: String, val type: String) :
        NavDirections {
        override fun getActionId(): Int {
            return R.id.action_homeFragment_to_cafeListFragment
        }

        override fun getArguments(): Bundle {
            val args = Bundle()
            args.putString("city", this.body)
            args.putString("type", this.type)
            args.putString("pageTitle", this.title)
            return args
        }
    }

    companion object {
        fun listLayout(city: String, title: String, type: String): NavDirections =
            CafeListLayout(city, title, type)
    }
}