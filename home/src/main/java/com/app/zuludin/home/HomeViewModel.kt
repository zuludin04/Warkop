package com.app.zuludin.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.zuludin.common.base.BaseViewModel
import com.app.zuludin.common.Event
import com.app.zuludin.data.AppDispatchers
import com.app.zuludin.data.model.Categories
import com.app.zuludin.data.model.City
import com.app.zuludin.data.model.RestaurantsItem
import com.app.zuludin.data.utils.Resource
import com.app.zuludin.data.model.MoreData
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val getHomeDataUseCase: GetHomeDataUseCase,
    private val dispatchers: AppDispatchers
) : BaseViewModel() {

    private val featuredRestaurant = MediatorLiveData<Resource<List<Any>>>()
    val restaurant: LiveData<Resource<List<Any>>> get() = featuredRestaurant
    private var restaurantSource: LiveData<Resource<List<RestaurantsItem>>> = MutableLiveData()

    private val statusLoading = MutableLiveData<Resource.Status>()
    val loading: LiveData<Resource.Status> get() = statusLoading

    private val errorLayout = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> get() = errorLayout

    init {
        loadHomeFeatured()
    }

    fun refreshLayout() = loadHomeFeatured()

    private fun loadHomeFeatured() = viewModelScope.launch(dispatchers.main) {
        featuredRestaurant.removeSource(restaurantSource)

        withContext(dispatchers.io) {
            restaurantSource = getHomeDataUseCase()
        }

        featuredRestaurant.addSource(restaurantSource) {
            featuredRestaurant.value =
                Resource.success(it.data?.let { restaurants -> addData(restaurants) })
            statusLoading.value = it.status
            errorLayout.value = it.status == Resource.Status.ERROR
            if (it.status == Resource.Status.ERROR) _snackBarError.value =
                Event(it.error?.message.toString())
        }
    }

    private fun addData(restaurants: List<RestaurantsItem>): List<Any> {
        val cities = listOf(
            City("Jakarta", "74", R.drawable.monas),
            City("London", "61", R.drawable.london),
            City("Kuala Lumpur", "88", R.drawable.kuala_lumpur),
            City("Singapore", "52", R.drawable.singapore),
            City("New York", "280", R.drawable.new_york),
            City("Melbourne", "259", R.drawable.melbourne)
        )

        val category = listOf(
            Categories("5", "Takeaway", R.drawable.ic_takeaway),
            Categories("6", "Cafes", R.drawable.ic_cafes),
            Categories("8", "Breakfast", R.drawable.ic_breakfast),
            Categories("9", "Lunch", R.drawable.ic_lunch),
            Categories("10", "Dinner", R.drawable.ic_dinner),
            Categories("11", "Pubs", R.drawable.ic_pubs)
        )

        return listOf(
            "Popular City",
            MoreData(cities, true),
            "Category",
            MoreData(category, true),
            "Featured Restaurant",
            MoreData(restaurants, false)
        )
    }
}