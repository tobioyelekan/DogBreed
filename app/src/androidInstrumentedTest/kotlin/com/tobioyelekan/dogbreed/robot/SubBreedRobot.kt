package com.tobioyelekan.dogbreed.robot

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick

fun ComposeTestRule.subBreedRobot(function: SubBreedRobot.() -> Unit) =
    function(SubBreedRobot(composeTestRule = this))

@OptIn(ExperimentalTestApi::class)
class SubBreedRobot(private val composeTestRule: ComposeTestRule) :
    ComposeTestRule by composeTestRule {

    init {
        waitUntilAtLeastOneExists(hasText("Australian Shepherd"))
    }

    fun verifySubBreedListDisplayed() {
        onAllNodesWithTag("subBreedImageItem")
            .onFirst()
            .assertExists()
    }

    fun errorShown() {
        onNodeWithTag("ErrorScreen").assertIsDisplayed()
    }

    fun pressBack() {
        onNodeWithContentDescription("navUp").performClick()
    }
}
