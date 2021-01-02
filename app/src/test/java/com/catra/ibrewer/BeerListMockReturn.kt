package com.catra.ibrewer

import com.catra.models.*

val beer = Beer(
    id = 1,
    name = "dummy",
    abv = 0.0,
    attenuationLevel = 0.0,
    boilVolume = BoilVolume(
        unit = "dummy",
        value = 0.0
    ),
    brewersTips = "dummy",
    contributedBy = "dummy",
    description = "dummy",
    firstBrewed = "dummy",
    foodPairing = listOf("dummy", "dummy", "dummy"),
    imageUrl = "dummy",
    tagline = "dummy",
    ebc = 0.0,
    ibu = 0.0,
    ingredients = Ingredients(
        malt = listOf(),
        hops = listOf(),
        yeast = "dummy"
    ),
    method = Method(
        mashTemp = listOf(),
        fermentation = Fermentation(
            temp = Temp(
                unit = "dummy",
                value = 0
            )
        ),
        twist = "dummy"
    ),
    ph = 0.0,
    srm = 0.0,
    targetFg = 0.0,
    targetOg = 0.0,
    volume = Volume(
        unit = "dummy",
        value = 0
    )
)

val beerListMock = listOf<Beer>(beer, beer, beer)
