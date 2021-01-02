package com.catra.ibrewer

import com.catra.ibrewer.utils.ProviderContext
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class TestProviderContext : ProviderContext() {
    override val main: CoroutineContext = Dispatchers.Unconfined
    override val io: CoroutineContext = Dispatchers.Unconfined
}