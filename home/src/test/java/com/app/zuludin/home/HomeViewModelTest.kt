package com.app.zuludin.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.test.filters.SmallTest
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
class HomeViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: HomeViewModel
    private lateinit var getHomeDataUseCase: GetHomeDataUseCase
    private val dispatchers = AppDispatchers(Dispatchers.Unconfined, Dispatchers.Unconfined)

    private val restaurants = listOf(
        RestaurantsItem(Restaurant(id = "1", name = "Cafe One")),
        RestaurantsItem(Restaurant(id = "2", name = "Cafe Two")),
        RestaurantsItem(Restaurant(id = "3", name = "Cafe Three"))
    )

    @Before
    fun setupHomeTest() {
        getHomeDataUseCase = mockk()
    }

    @Test
    fun `load home data layout`() {
        val observer = mockk<Observer<Resource<List<RestaurantsItem>>>>(relaxed = true)
        val result = Resource.success(restaurants)

        coEvery { getHomeDataUseCase() } returns MutableLiveData<Resource<List<RestaurantsItem>>>().apply {
            value = result
        }

        viewModel = HomeViewModel(getHomeDataUseCase, dispatchers)
        viewModel.restaurant.observeForever(observer)

        verify {
            observer.onChanged(result)
        }

        confirmVerified(observer)
    }
}