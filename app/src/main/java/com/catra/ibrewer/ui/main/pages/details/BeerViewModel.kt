package com.catra.ibrewer.ui.main.pages.details

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

class BeerViewModel(
    id: Int,
    private val useCase: BeerUseCase,
    private val providerContext: ProviderContext
) : ViewModel() {

    private val _beerLiveData = MutableLiveData<FlowState<Beer>>()
    val beerLiveData: LiveData<FlowState<Beer>> get() = _beerLiveData

    fun fetchBeer(id: Int) {
        viewModelScope.launch(providerContext.main) {
            _beerLiveData.postLoading(true)

            useCase.fetchBeer(
                id = id,
                onSuccess = { beer ->
                    _beerLiveData.postLoading(false)
                    _beerLiveData.postSuccess(beer)
                },
                onError = { throwable ->
                    _beerLiveData.postLoading(false)
                    _beerLiveData.postError(throwable)
                }
            )
        }
    }

    init {
        fetchBeer(id)
    }
}
