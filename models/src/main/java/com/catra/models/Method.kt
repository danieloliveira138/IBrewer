package com.catra.models

import com.google.gson.annotations.SerializedName

data class Method(
    @SerializedName("mash_temp") val mashTemp: List<MashTempItem>?,

    @SerializedName("fermentation") val fermentation: Fermentation?,

    @SerializedName("twist") val twist: String?
)