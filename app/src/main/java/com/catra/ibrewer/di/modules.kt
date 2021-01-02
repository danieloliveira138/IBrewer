package com.catra.ibrewer.di

import com.catra.ibrewer.BuildConfig
import com.catra.ibrewer.ui.main.pages.details.BeerUseCase
import com.catra.ibrewer.ui.main.pages.details.BeerViewModel
import com.catra.ibrewer.ui.main.pages.list.BeerListUseCase
import com.catra.ibrewer.ui.main.pages.list.BeerListViewModel
import com.catra.ibrewer.utils.ProviderContext
import com.catra.network.api.PunkApi
import com.catra.network.config.provideApi
import com.catra.network.repository.BeerRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        provideApi(
            "${BuildConfig.PUNK_API_BASE_URL}${BuildConfig.PUNK_API_VERSION}",
            PunkApi::class.java
        )
    }
    single { BeerRepository(get()) }
    single { BeerListUseCase(get(), get()) }
    viewModel { BeerListViewModel(get(), get()) }
    single { BeerUseCase(get(), get()) }
    viewModel { (id: Int) -> BeerViewModel(id, get(), get()) }
    single { ProviderContext() }
}