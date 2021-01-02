package com.catra.ibrewer.utils.extensions

import androidx.lifecycle.MutableLiveData
import com.catra.ibrewer.utils.FlowState

fun <D> MutableLiveData<FlowState<D>>.postLoading(fullLoading: Boolean = false) {
    value = FlowState(FlowState.Status.LOADING, fullLoading = fullLoading)
}

fun <D> MutableLiveData<FlowState<D>>.postSuccess(data: D?) {
    value = FlowState(FlowState.Status.SUCCESS, data = data)
}

fun <D> MutableLiveData<FlowState<D>>.postError(error: Throwable) {
    value = FlowState(FlowState.Status.ERROR, error = error)
}

fun <D> MutableLiveData<FlowState<D>>.postError(resources: Int) {
    value = FlowState(FlowState.Status.ERROR, resources = resources)
}
