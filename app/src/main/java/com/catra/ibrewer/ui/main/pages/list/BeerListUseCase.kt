package com.catra.ibrewer.ui.main.pages.list

import com.catra.ibrewer.utils.ProviderContext
import com.catra.models.Beer
import com.catra.network.repository.BeerRepository
import com.catra.network.utils.ResultType
import com.catra.network.utils.handleResultType
import kotlinx.coroutines.withContext

private const val OFFSET = 10

class BeerListUseCase(
    private val repository: BeerRepository,
    private val providerContext: ProviderContext
) {

    private var page = 0

    suspend fun fetchBeers(
        firstPage: Boolean,
        onSuccess: (List<Beer>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        if (firstPage) page = 1 else page += 1

        var result: ResultType<List<Beer>>?

        withContext(providerContext.io) {
            result = repository.fetchBeerList(page, OFFSET)
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
