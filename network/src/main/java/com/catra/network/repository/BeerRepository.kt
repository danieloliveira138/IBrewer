package com.catra.network.repository

import com.catra.network.api.PunkApi
import com.catra.network.utils.safeApiCall

class BeerRepository(private val api: PunkApi) {

    suspend fun fetchBeerList(page: Int, offset: Int) = safeApiCall {
        api.fetchBeers(page = page, offset = offset)
    }

    suspend fun fetchBeer(id: Int) {
        api.fetchBeer(id = id)
    }
}
