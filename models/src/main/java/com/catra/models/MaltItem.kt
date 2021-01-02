package com.catra.models

import com.google.gson.annotations.SerializedName

data class MaltItem(
    @SerializedName("amount") val amount: Amount,

    @SerializedName("name") val name: String = ""
)