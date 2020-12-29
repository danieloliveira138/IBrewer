package com.catra.models

import com.google.gson.annotations.SerializedName

data class MashTempItem(
    @SerializedName("duration") val duration: Int? = 0,

    @SerializedName("temp") val temp: Temp?
)