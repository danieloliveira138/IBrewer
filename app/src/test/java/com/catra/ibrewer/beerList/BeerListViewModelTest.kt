package com.catra.ibrewer.beerList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.catra.ibrewer.TestProviderContext
import com.catra.ibrewer.beerListMock
import com.catra.ibrewer.ui.main.pages.list.BeerListUseCase
import com.catra.ibrewer.ui.main.pages.list.BeerListViewModel
import com.catra.ibrewer.utils.FlowState
import com.catra.models.Beer
import io.mockk.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest

class BeerListViewModelTest : KoinTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val usecase = mockk<BeerListUseCase>(relaxed = true)
    private lateinit var viewModel: BeerListViewModel

    @Before
    fun setup() {
        viewModel = BeerListViewModel(usecase, TestProviderContext())
    }

    @Test
    fun `If fetch beer is calling must set loading state`() {

        coEvery {
            usecase.fetchBeers(
                firstPage = true,
                onSuccess = { },
                onError = { }
            )
        } just Runs

        viewModel.fetchBeers(true)

        assert(viewModel.beerListLiveData.value?.status == FlowState.Status.LOADING)
    }

    @Test
    fun `If fetch beer details is call with error should return Flow Error state`() {
        val resultMock = Throwable("error")
        coEvery {
            usecase.fetchBeers(
                firstPage = true,
                onSuccess = any(),
                onError = captureLambda()
            )
        } coAnswers {
            lambda<((Throwable) -> Unit)>().invoke(resultMock)
        }

        viewModel.fetchBeers(true)

        assert(viewModel.beerListLiveData.value?.error?.message == "error")
        assert(viewModel.beerListLiveData.value?.error == resultMock)
        assert(viewModel.beerListLiveData.value?.status == FlowState.Status.ERROR)
    }

    @Test
    fun `If fetch beer details is call with success should return Success State`() {

        coEvery {
            usecase.fetchBeers(
                firstPage = true,
                onSuccess = captureLambda(),
                onError = any()
            )
        } coAnswers {
            lambda<((List<Beer>) -> Unit)>().invoke(beerListMock)
        }

        viewModel.fetchBeers(true)

        assert(viewModel.beerListLiveData.value?.data is List<Beer>)
        assert(viewModel.beerListLiveData.value?.status == FlowState.Status.SUCCESS)
    }
}