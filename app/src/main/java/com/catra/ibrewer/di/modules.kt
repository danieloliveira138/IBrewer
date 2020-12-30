package com.catra.ibrewer.di

import com.catra.ibrewer.screens.main.MainUseCase
import com.catra.ibrewer.screens.main.MainViewModel
import com.catra.ibrewer.utils.ProviderContext
import com.catra.network.provider.provideBeerApi
import com.catra.network.repository.BeerRepository
import org.koin.dsl.module

val appModule = module {
    single {
        provideBeerApi()
    }
    single { BeerRepository(get()) }
    single { MainUseCase(get(), get()) }
    single { MainViewModel(get(), get()) }
    single { ProviderContext() }
}