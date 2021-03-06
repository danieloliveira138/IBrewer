package com.catra.network.api

import com.catra.models.Beer
import com.catra.network.BuildConfig
import com.catra.network.BuildConfig.BEERS_API
import com.catra.network.BuildConfig.BEER_ID
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PunkApi {

    @GET(BEERS_API)
    suspend fun fetchBeers(
        @Query(BuildConfig.BEERS_API_PAGE) page: Int,
        @Query(BuildConfig.BEERS_API_OFFSET) offset: Int
    ): List<Beer>

    @GET("$BEERS_API$BEER_ID")
    suspend fun fetchBeer(
        @Path("beer_id") id: Int
    ): List<Beer>
}