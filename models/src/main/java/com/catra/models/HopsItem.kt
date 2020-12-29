package com.catra.models

import com.google.gson.annotations.SerializedName

data class HopsItem(
    @SerializedName("add") val add: String? = "",

    @SerializedName("amount") val amount: Amount,

    @SerializedName("name") val name: String? = "",

    @SerializedName("attribute") val attribute: String? = ""
)