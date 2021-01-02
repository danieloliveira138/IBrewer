package com.catra.ibrewer.beerList

import com.catra.ibrewer.TestProviderContext
import com.catra.ibrewer.beerListMock
import com.catra.ibrewer.ui.main.pages.list.BeerListUseCase
import com.catra.models.Beer
import com.catra.network.repository.BeerRepository
import com.catra.network.utils.ResultType
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class BeerListUseCaseTest {
    private val repository = mockk<BeerRepository>()
    private lateinit var useCase: BeerListUseCase

    @Before
    fun setup() {
        useCase = BeerListUseCase(repository, TestProviderContext())
    }

    @Test
    fun `Should return error when fetch list throw error`() = runBlocking {
        val returnMock = Throwable("error")
        val expectedReturn = ResultType.Fail(returnMock)
        var result: Throwable? = null

        coEvery {
            repository.fetchBeerList(any(), any())
        } coAnswers { expectedReturn }

        useCase.fetchBeers(
            firstPage = true,
            onSuccess = { },
            onError =  {
                result = it
            }
        )

        assert(result == returnMock)
    }

    @Test
    fun `Should return success when fetch list return Beer List`() = runBlocking {
        val returnMock = beerListMock
        val expectedReturn = ResultType.Success(returnMock)
        var result: List<Beer>? = null

        coEvery {
            repository.fetchBeerList(any(), any())
        } coAnswers { expectedReturn }

        useCase.fetchBeers(
            firstPage = true,
            onSuccess = { result = it },
            onError = { }
        )

        assert(result == returnMock)
    }
}