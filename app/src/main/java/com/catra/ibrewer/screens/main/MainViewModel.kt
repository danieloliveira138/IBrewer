package com.catra.ibrewer.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.catra.ibrewer.utils.FlowState
import com.catra.ibrewer.utils.ProviderContext
import com.catra.ibrewer.utils.extensions.postError
import com.catra.ibrewer.utils.extensions.postLoading
import com.catra.ibrewer.utils.extensions.postSuccess
import com.catra.models.Beer
import kotlinx.coroutines.launch

class MainViewModel(
    private val useCase: MainUseCase,
    private val context: ProviderContext
) : ViewModel() {

    private val _beerListLiveData = MutableLiveData<FlowState<List<Beer>>>()
    val beerListLiveData: LiveData<FlowState<List<Beer>>> = _beerListLiveData
    val beerList: MutableList<Beer> = mutableListOf()

    fun fetchBeers(page: Int) {
        viewModelScope.launch(context.main) {
            _beerListLiveData.postLoading(true)

            useCase.fetchBeers(
                page,
                10,
                onSuccess = {
                    beerList.addAll(it)
                    _beerListLiveData.postLoading(false)
                    _beerListLiveData.postSuccess(it)
                },
                onError = {
                    _beerListLiveData.postLoading(false)
                    _beerListLiveData.postError(it)
                }
            )
        }
    }
}