package com.catra.ibrewer.di

import com.catra.ibrewer.screens.main.MainUseCase
import com.catra.ibrewer.screens.main.MainViewModel
import com.catra.ibrewer.screens.main.pages.details.BeerUseCase
import com.catra.ibrewer.screens.main.pages.details.BeerViewModel
import com.catra.ibrewer.screens.main.pages.list.BeerListUseCase
import com.catra.ibrewer.screens.main.pages.list.BeerListViewModel
import com.catra.ibrewer.utils.ProviderContext
import com.catra.network.provider.provideBeerApi
import com.catra.network.repository.BeerRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        provideBeerApi()
    }
    single { BeerRepository(get()) }
    single { MainUseCase(get(), get()) }
    single { MainViewModel(get(), get()) }
    single { BeerListUseCase(get(), get()) }
    viewModel { BeerListViewModel(get(), get()) }
    single { BeerUseCase(get(), get()) }
    viewModel { (id: Int) -> BeerViewModel(id, get(), get()) }
    single { ProviderContext() }
}