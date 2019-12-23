package com.app.zuludin.list

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
class CafeListViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var getCafeByCityUseCase: GetCafeByCityUseCase
    private lateinit var viewModel: CafeListViewModel
    private val dispatchers = AppDispatchers(Dispatchers.Unconfined, Dispatchers.Unconfined)

    private val cafes = listOf(
        RestaurantsItem(Restaurant(id = "1", name = "Cafe One")),
        RestaurantsItem(Restaurant(id = "2", name = "Cafe Two")),
        RestaurantsItem(Restaurant(id = "3", name = "Cafe Three"))
    )

    @Before
    fun setupCafeListTest() {
        getCafeByCityUseCase = mockk()
    }

    @Test
    fun `load cafe list by city id`() {
        val observer = mockk<Observer<Resource<List<RestaurantsItem>>>>(relaxed = true)
        val result = Resource.success(cafes)

        coEvery { getCafeByCityUseCase("74", "city", "8") } returns MutableLiveData<Resource<List<RestaurantsItem>>>().apply {
            value = result
        }

        viewModel = CafeListViewModel(getCafeByCityUseCase, dispatchers)
        viewModel.cafes.observeForever(observer)
        viewModel.loadCafes("74", "city", "8")

        verify {
            observer.onChanged(result)
        }

        confirmVerified(observer)
    }
}