package com.catra.models

import com.google.gson.annotations.SerializedName

data class Beer(
    @SerializedName("first_brewed") val firstBrewed: String = "",

    @SerializedName("attenuation_level") val attenuationLevel: Double = 0.0,

    @SerializedName("method") val method: Method,

    @SerializedName("target_og") val targetOg: Double = 0.0,

    @SerializedName("image_url") val imageUrl: String = "",

    @SerializedName("boil_volume") val boilVolume: BoilVolume,

    @SerializedName("ebc") val ebc: Double = 0.0,

    @SerializedName("description") val description: String = "",

    @SerializedName("target_fg") val targetFg: Double = 0.0,

    @SerializedName("srm") val srm: Double = 0.0,

    @SerializedName("volume") val volume: Volume,

    @SerializedName("contributed_by") val contributedBy: String = "",

    @SerializedName("abv") val abv: Double = 0.0,

    @SerializedName("food_pairing") val foodPairing: List<String>?,

    @SerializedName("name") val name: String = "",

    @SerializedName("ph") val ph: Double = 0.0,

    @SerializedName("tagline") val tagline: String = "",

    @SerializedName("ingredients") val ingredients: Ingredients,

    @SerializedName("id") val id: Int = 0,

    @SerializedName("ibu") val ibu: Double = 0.0,

    @SerializedName("brewers_tips") val brewersTips: String = ""
) {

    val descriptionList get() = listOf(
        "Nome" to name,
        "Slogan" to tagline,
        "Teor Alcoólico" to abv.toString(),
        "Escala de Amargor" to ibu.toString(),
        "Descrição" to description
    )
}
