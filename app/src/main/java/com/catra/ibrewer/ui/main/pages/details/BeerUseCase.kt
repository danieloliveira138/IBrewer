package com.catra.ibrewer.ui.main.pages.details

import com.catra.ibrewer.ui.BaseUseCase
import com.catra.models.Beer
import com.catra.network.repository.BeerRepository
import com.catra.network.utils.ResultType
import com.catra.network.utils.handleResultType
import kotlinx.coroutines.withContext

class BeerUseCase(
    private val repository: BeerRepository
) : BaseUseCase() {

    suspend fun fetchBeer(
        id: Int,
        onSuccess: (Beer) -> Unit,
        onError: (Throwable) -> Unit
    ) {

        var result: ResultType<List<Beer>>?

        withContext(ioContext) {
            result = repository.fetchBeer(id)
        }

        result?.handleResultType(
            success = {
                onSuccess(it.first())
            },
            error = {
                onError(it)
            }
        )
    }
}
