package com.catra.ibrewer.utils

class FlowState<D>(
    val status: Status,
    val data: D? = null,
    val error: Throwable? = null,
    val fullLoading: Boolean = true,
    val resources: Int = 0
) {
    enum class Status {
        LOADING, SUCCESS, ERROR
    }
}
