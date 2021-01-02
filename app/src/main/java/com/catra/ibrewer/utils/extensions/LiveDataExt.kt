package com.catra.ibrewer.utils.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.catra.ibrewer.utils.FlowState

fun <D> LiveData<FlowState<D>>.observerEvent(
    lifecycleOwner: LifecycleOwner,
    onLoading: ((Boolean) -> Unit)? = null,
    onSuccess: (D) -> Unit,
    onError: (Throwable) -> Unit = {},
    onErrorWithId: ((Int) -> Unit)? = null

) {
    observe(lifecycleOwner, Observer {
        when (it.status) {
            FlowState.Status.LOADING -> {
                onLoading?.invoke(it.fullLoading)
            }
            FlowState.Status.SUCCESS -> {
                it.data?.let { data -> onSuccess.invoke(data) }
            }
            FlowState.Status.ERROR -> {
                if (it.resources != 0)
                    it.resources.let { error -> onErrorWithId?.invoke(error) }
                else
                    it.error?.let { error -> onError.invoke(error) }
            }
        }
    })
}