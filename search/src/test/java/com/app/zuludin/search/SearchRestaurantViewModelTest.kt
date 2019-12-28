package com.app.zuludin.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.test.filters.SmallTest
import com.app.zuludin.common.Event
import com.app.zuludin.data.AppDispatchers
import com.app.zuludin.data.model.Restaurant
import com.app.zuludin.data.model.RestaurantsItem
import com.app.zuludin.data.utils.Resource
import io.mockk.coEvery
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
@SmallTest
class SearchRestaurantViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: SearchRestaurantViewModel
    private lateinit var getSearchRestaurantUseCase: GetSearchRestaurantUseCase
    private val dispatchers = AppDispatchers(Dispatchers.Unconfined, Dispatchers.Unconfined)

    private val restaurants = listOf(
        RestaurantsItem(Restaurant(id = "1", name = "Cafe One")),
        RestaurantsItem(Restaurant(id = "2", name = "Cafe Two")),
        RestaurantsItem(Restaurant(id = "3", name = "Cafe Three"))
    )

    @Before
    fun setupSearchRestaurantTest() {
        getSearchRestaurantUseCase = mockk()
    }

    @Test
    fun `load restaurant result based on query`() {
        val observer = mockk<Observer<Resource<List<RestaurantsItem>>>>(relaxed = true)
        val result = Resource.success(restaurants)

        coEvery { getSearchRestaurantUseCase("cafe") } returns MutableLiveData<Resource<List<RestaurantsItem>>>().apply {
            value = result
        }

        viewModel = SearchRestaurantViewModel(getSearchRestaurantUseCase, dispatchers)
        viewModel.restaurants.observeForever(observer)
        viewModel.searchRestaurant("cafe")

        verify {
            observer.onChanged(result)
        }

        confirmVerified(observer)
    }

    @Test
    fun `trying search restaurant but resulting error result`() {
        val observer = mockk<Observer<Resource<List<RestaurantsItem>>>>(relaxed = true)
        val observerSnackBar = mockk<Observer<Event<String>>>(relaxed = true)
        val result = Resource.error(Exception("Error"), null)

        coEvery { getSearchRestaurantUseCase(any()) } returns MutableLiveData<Resource<List<RestaurantsItem>>>().apply {
            value = result
        }

        viewModel = SearchRestaurantViewModel(getSearchRestaurantUseCase, dispatchers)
        viewModel.restaurants.observeForever(observer)
        viewModel.errorMessage.observeForever(observerSnackBar)
        viewModel.searchRestaurant("")

        verify {
            observer.onChanged(result)
            observerSnackBar.onChanged(viewModel.errorMessage.value)
        }

        confirmVerified(observer)
    }
}