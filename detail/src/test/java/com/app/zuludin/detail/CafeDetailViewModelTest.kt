package com.app.zuludin.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.test.filters.SmallTest
import com.app.zuludin.common.Event
import com.app.zuludin.data.AppDispatchers
import com.app.zuludin.data.model.detail.DetailCafeResponse
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
class CafeDetailViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var getDetailCafeUseCase: GetDetailCafeUseCase
    private lateinit var getRestaurantReviewUseCase: GetRestaurantReviewUseCase

    private lateinit var viewModel: CafeDetailViewModel
    private val dispatchers = AppDispatchers(Dispatchers.Unconfined, Dispatchers.Unconfined)

    private val detailCafe = DetailCafeResponse(id = "1", name = "Cafe Permata")

    @Before
    fun setupDetailCafeTest() {
        getDetailCafeUseCase = mockk()
        getRestaurantReviewUseCase = mockk()
        viewModel = CafeDetailViewModel(getDetailCafeUseCase, getRestaurantReviewUseCase, dispatchers)
    }

    @Test
    fun `load detail cafe API by selected cafe ID`() {
        val observer = mockk<Observer<DetailCafeResponse>>(relaxed = true)
        val result = Resource.success(detailCafe)

        coEvery { getDetailCafeUseCase("123") } returns MutableLiveData<Resource<DetailCafeResponse>>().apply {
            value = result
        }

        viewModel.cafe.observeForever(observer)
        viewModel.loadCafeDetail("123")

        verify {
            observer.onChanged(result.data)
        }

        confirmVerified(observer)
    }

    @Test
    fun `error when loading detail restaurant`() {
        val observer = mockk<Observer<DetailCafeResponse>>(relaxed = true)
        val observerSnackBar = mockk<Observer<Event<String>>>(relaxed = true)
        val observerErrorLayout = mockk<Observer<Boolean>>(relaxed = true)
        val result = Resource.error(Exception("Error"), null)

        coEvery { getDetailCafeUseCase(any()) } returns MutableLiveData<Resource<DetailCafeResponse>>().apply {
            value = result
        }

        viewModel.cafe.observeForever(observer)
        viewModel.error.observeForever(observerErrorLayout)
        viewModel.errorMessage.observeForever(observerSnackBar)
        viewModel.loadCafeDetail("3121")

        verify {
            observer.onChanged(result.data)
            observerSnackBar.onChanged(viewModel.errorMessage.value)
            observerErrorLayout.onChanged(viewModel.error.value)
        }

        confirmVerified(observer)
    }
}