package com.catra.ibrewer.di

import com.catra.network.BuildConfig.PUNK_API_BASE_URL
import com.catra.network.BuildConfig.PUNK_API_VERSION
import com.catra.network.api.PunkApi
import com.catra.network.config.NetworkConfig
import com.catra.network.repository.BeerRepository
import org.koin.dsl.module

val appModule = module {
    single {
        NetworkConfig.provideApi("$PUNK_API_BASE_URL$PUNK_API_VERSION", PunkApi::class.java)
    }
    single {
        BeerRepository(get())
    }
}