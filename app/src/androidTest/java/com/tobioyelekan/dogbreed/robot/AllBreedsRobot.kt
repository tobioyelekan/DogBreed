package com.tobioyelekan.dogbreed.robot

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick

fun ComposeTestRule.allBreedsRobot(function: AllBreedsRobot.() -> Unit) =
    function(AllBreedsRobot(composeTestRule = this))

@OptIn(ExperimentalTestApi::class)
class AllBreedsRobot(private val composeTestRule: ComposeTestRule) :
    ComposeTestRule by composeTestRule {

    init {
        waitUntilAtLeastOneExists(hasText("All Breeds"))
    }

    fun clickOnBreed(breed: String) {
        onNodeWithText(breed).performClick()
    }

    fun errorShown() {
        onNodeWithTag("ErrorScreen").assertIsDisplayed()
    }
}