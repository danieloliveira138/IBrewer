package com.catra.network.utils

import retrofit2.HttpException

suspend fun <T: Any> safeApiCall(call: suspend() -> T?): ResultType<T> {
    var resultType: ResultType<T>? = null

    try {
        val remoteData = call()

        resultType = if (remoteData != null) ResultType.Success(
            remoteData
        )
        else ResultType.GenericThrowable()

    } catch(exception: Throwable) {

        resultType = if(exception is HttpException) {

            ResultType.Fail(
                GenericResponseException(
                    exception,
                    exception.code()
                )
            )
        } else {

            ResultType.Fail(exception)
        }
    } finally {

        return resultType ?: ResultType.GenericThrowable()
    }
}

sealed class ResultType<out T> {
    data class Success<out T>(val data: T): ResultType<T>()

    data class Error<out T>(val data: T): ResultType<T>()

    data class Fail(val exception: Throwable): ResultType<Nothing>()

    data class GenericThrowable(
        val exception: Throwable = Throwable("Request unexpected fail")
    ): ResultType<Nothing>()
}

fun <T> ResultType<T>.handleResultType(
    success: (T) -> Unit,
    error: (Throwable) -> Unit
) {
    when(this) {
        is ResultType.Success -> success(data)
        is ResultType.Fail -> error(exception)
    }
}

class GenericResponseException(
    cause: Throwable,
    code: Int
): Exception("", cause)
