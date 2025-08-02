package com.tobioyelekan.dogbreed.robot

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick

fun ComposeTestRule.breedDetailsRobot(
    breedName: String,
    function: BreedDetailsRobot.() -> Unit
) =
    function(BreedDetailsRobot(breedName = breedName, composeTestRule = this))

@OptIn(ExperimentalTestApi::class)
class BreedDetailsRobot(
    breedName: String,
    private val composeTestRule: ComposeTestRule
) : ComposeTestRule by composeTestRule {

    init {
        waitUntilAtLeastOneExists(hasText(breedName))
    }

    fun addToFavouritesDisplayed() {
        onNodeWithContentDescription("click to add breed as favorite")
            .assertIsDisplayed()
    }

    fun clicksAddFavourites() {
        onNodeWithContentDescription("click to add breed as favorite")
            .performClick()
    }

    fun removeFromFavouritesDisplayed() {
        onNodeWithContentDescription("click to remove breed as favorite")
            .assertIsDisplayed()
    }

    fun clicksRemoveFavourites() {
        onNodeWithContentDescription("click to remove breed as favorite")
            .performClick()
    }

    fun clicksASubBreed() {
        onNodeWithText("shepherd").performClick()
    }

    fun pressBack() {
        onNodeWithContentDescription("navUp").performClick()
    }

    fun seesSubBreed() {
        onNodeWithText("Sub breeds").assertIsDisplayed()
    }

    fun seesEmptySubBreedText() {
        onNodeWithText("No sub breeds listed").assertIsDisplayed()
    }
}