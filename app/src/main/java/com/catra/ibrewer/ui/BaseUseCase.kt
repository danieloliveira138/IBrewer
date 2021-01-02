package com.catra.ibrewer.ui

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

open class BaseUseCase {
    open val ioContext: CoroutineContext by lazy { Dispatchers.IO }
}