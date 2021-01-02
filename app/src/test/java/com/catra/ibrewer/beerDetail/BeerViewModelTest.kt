package com.catra.ibrewer.beerDetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.catra.ibrewer.TestProviderContext
import com.catra.ibrewer.beer
import com.catra.ibrewer.ui.main.pages.details.BeerUseCase
import com.catra.ibrewer.ui.main.pages.details.BeerViewModel
import com.catra.ibrewer.utils.FlowState
import com.catra.models.Beer
import io.mockk.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest

class BeerViewModelTest : KoinTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val usecase = mockk<BeerUseCase>(relaxed = true)
    private lateinit var viewModel: BeerViewModel

    @Before
    fun setup() {
        viewModel = BeerViewModel(1, usecase, TestProviderContext())
    }

    @Test
    fun `If fetch beer is calling must set loading state`() {

        coEvery {
            usecase.fetchBeer(
                id =1,
                onSuccess = { },
                onError = { }
            )
        } just Runs

        viewModel.fetchBeer(1)

        assert(viewModel.beerLiveData.value?.status == FlowState.Status.LOADING)
    }

    @Test
    fun `If fetch beer details is call with error should return Flow Error state`() {
        val resultMock = Throwable("error")
        coEvery {
            usecase.fetchBeer(
                id = 1,
                onSuccess = any(),
                onError = captureLambda()
            )
        } coAnswers {
            lambda<((Throwable) -> Unit)>().invoke(resultMock)
        }

        viewModel.fetchBeer(1)

        assert(viewModel.beerLiveData.value?.error?.message == "error")
        assert(viewModel.beerLiveData.value?.error == resultMock)
        assert(viewModel.beerLiveData.value?.status == FlowState.Status.ERROR)
    }

    @Test
    fun `If fetch beer details is call with success should return Success State`() {

        coEvery {
            usecase.fetchBeer(
                id = 1,
                onSuccess = captureLambda(),
                onError = any()
            )
        } coAnswers {
            lambda<((Beer) -> Unit)>().invoke(beer)
        }

        viewModel.fetchBeer(1)

        assert(viewModel.beerLiveData.value?.data is Beer)
        assert(viewModel.beerLiveData.value?.status == FlowState.Status.SUCCESS)
    }
}