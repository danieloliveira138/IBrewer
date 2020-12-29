package com.catra.models

import com.google.gson.annotations.SerializedName

data class Ingredients(
    @SerializedName("hops") val hops: List<HopsItem>?,

    @SerializedName("yeast") val yeast: String? = "",

    @SerializedName("malt") val malt: List<MaltItem>?
)