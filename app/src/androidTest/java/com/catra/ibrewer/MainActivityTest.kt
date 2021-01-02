package com.catra.ibrewer

import android.content.Intent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.catra.ibrewer.ui.main.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest

@RunWith(AndroidJUnit4::class)
class MainActivityTest : KoinTest {

    @Rule
    @JvmField
    val rule = IntentsTestRule(MainActivity::class.java, true, false)

    @Before
    fun setup() {
        rule.launchActivity(Intent())
    }

    @Test
    fun whenDisplayBeerList_ShouldDisplayToolbar() {
        MainActivityRobot {
            waitTimer()
            assetToolbarIsDisplayed()
            assertToolbarTitleIsDisplayed()
        }
    }

    @Test
    fun whenDisplayScreen_ShouldDisplayBeerList() {
        MainActivityRobot {
            waitTimer()
            assertBeerListIsDisplayed()
            clickListItem()
            clickBack()
            assertBeerListIsDisplayed()
        }
    }

    @Test
    fun whenClickAnItemOnBeerList_ShouldShowBeerDetails() {
        MainActivityRobot {
            waitTimer()
            assertBeerListIsDisplayed()
            clickListItem()
            waitTimer()
            assetToolbarTitleIsDisplayed()
            clickBack()
            assertBeerListIsDisplayed()
        }
    }

    @Test
    fun whenIsInBeerDetailsAndClickBack_ShouldReturnToBeerList() {
        MainActivityRobot {
            waitTimer()
            assertBeerListIsDisplayed()
            clickListItem()
            clickBack()
            assertBeerListIsDisplayed()
        }
    }

    @Test
    fun whenDisplayBeerDetails_ShouldShowBeerDescriptionFields() {
        MainActivityRobot {
            waitTimer()
            clickListItem()
            assetToolbarTitleIsDisplayed()
            assertBeerNameIsDisplayed()
            assertBeerTagLineIsDisplayed()
            assertBeerABVIsDisplayed()
            assertBeerIBUIsDisplayed()
            assertBeerDescriptionIsDisplayed()
        }
    }
}