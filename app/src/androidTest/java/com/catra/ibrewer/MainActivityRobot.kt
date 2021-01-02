package com.catra.ibrewer

import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.schibsted.spain.barista.interaction.BaristaClickInteractions
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import com.schibsted.spain.barista.interaction.BaristaSleepInteractions

class MainActivityRobot(block: MainActivityRobot.() -> Unit) {

    init {
        block.invoke(this)
    }

    fun assetToolbarIsDisplayed() = assertDisplayed(R.id.toolbar)

    fun assertToolbarTitleIsDisplayed() = assertDisplayed("IBrewer")

    fun assertBeerListIsDisplayed() = assertDisplayed(R.id.recyclerBeer)

    fun clickListItem() = clickOn("Buzz")

    fun assetToolbarTitleIsDisplayed() = assertDisplayed(R.id.beerToolbar)

    fun assertBeerNameIsDisplayed() = assertDisplayed("Nome")

    fun assertBeerTagLineIsDisplayed() = assertDisplayed("Slogan")

    fun assertBeerABVIsDisplayed() = assertDisplayed("Teor Alcoólico")

    fun assertBeerIBUIsDisplayed() = assertDisplayed("Escala de amargor")

    fun assertBeerDescriptionIsDisplayed() = assertDisplayed("Descrição")

    fun clickBack() = BaristaClickInteractions.clickBack()

    fun waitTimer() = BaristaSleepInteractions.sleep(2500)
}