package com.catra.models

import com.google.gson.annotations.SerializedName

data class MashTempItem(
    @SerializedName("duration") val duration: Double,

    @SerializedName("temp") val temp: Temp
)