package com.catra.ibrewer.screens.main

import com.catra.ibrewer.utils.ProviderContext
import com.catra.models.Beer
import com.catra.network.repository.BeerRepository
import com.catra.network.utils.ResultType
import com.catra.network.utils.handleResultType
import kotlinx.coroutines.withContext

class MainUseCase(
    private val repository: BeerRepository,
    private val context: ProviderContext
) {

    suspend fun fetchBeers(
        page: Int,
        offset: Int,
        onSuccess: (List<Beer>) -> Unit,
        onError: (Throwable) -> Unit
    ) {

        var result: ResultType<ArrayList<Beer>>? = null

        withContext(context.io) {
            result = repository.fetchBeerList(page, offset)
        }

        result?.handleResultType(
            success = {
                onSuccess(it)
            },
            error = {
                onError(it)
            }
        )

    }
}