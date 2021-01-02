package com.catra.ibrewer.ui.main.pages.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.catra.ibrewer.ui.BaseViewModel
import com.catra.ibrewer.utils.FlowState
import com.catra.ibrewer.utils.extensions.postError
import com.catra.ibrewer.utils.extensions.postLoading
import com.catra.ibrewer.utils.extensions.postSuccess
import com.catra.models.Beer
import kotlinx.coroutines.launch

class BeerListViewModel(
    private val useCase: BeerListUseCase
) : BaseViewModel() {

    private val _beerListLiveData = MutableLiveData<FlowState<List<Beer>>>()
    val beerListLiveData: LiveData<FlowState<List<Beer>>> = _beerListLiveData
    val beerList: MutableList<Beer> = mutableListOf()

    fun fetchBeers(firstPage: Boolean) {
        viewModelScope.launch(mainContext) {
            _beerListLiveData.postLoading(true)

            useCase.fetchBeers(
                firstPage,
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

    init {
        fetchBeers(firstPage = true)
    }

}
