package com.app.zuludin.list

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

    @Test
    fun `error when trying load restaurant list`() {
        val observer = mockk<Observer<Resource<List<RestaurantsItem>>>>(relaxed = true)
        val observerSnackBar = mockk<Observer<Event<String>>>(relaxed = true)
        val observerErrorLayout = mockk<Observer<Boolean>>(relaxed = true)
        val result = Resource.error(Exception("Error"), null)

        coEvery { getCafeByCityUseCase(any(), any(), any()) } returns MutableLiveData<Resource<List<RestaurantsItem>>>().apply {
            value = result
        }

        viewModel = CafeListViewModel(getCafeByCityUseCase, dispatchers)
        viewModel.cafes.observeForever(observer)
        viewModel.errorMessage.observeForever(observerSnackBar)
        viewModel.error.observeForever(observerErrorLayout)
        viewModel.loadCafes("", "", "")

        verify {
            observer.onChanged(result) // get error result
            observerErrorLayout.onChanged(viewModel.error.value) // show error layout so user can refresh the page
            observerSnackBar.onChanged(viewModel.errorMessage.value) // show snackBar error message
        }

        confirmVerified(observer)
    }
}