package com.catra.models

import com.google.gson.annotations.SerializedName

data class Fermentation(
    @SerializedName("temp") val temp: Temp)