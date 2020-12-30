package com.catra.network.provider

import com.catra.network.BuildConfig
import com.catra.network.api.PunkApi
import com.catra.network.config.NetworkConfig

fun provideBeerApi() =
    NetworkConfig.provideApi(
        "${BuildConfig.PUNK_API_BASE_URL}${BuildConfig.PUNK_API_VERSION}",
        PunkApi::class.java
        )