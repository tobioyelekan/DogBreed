package com.tobioyelekan.dogbreed.robot

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick

fun ComposeTestRule.favoriteBreedsRobot(function: FavoriteBreedsRobot.() -> Unit) =
    function(FavoriteBreedsRobot(composeTestRule = this))

@OptIn(ExperimentalTestApi::class)
class FavoriteBreedsRobot(private val composeTestRule: ComposeTestRule) :
    ComposeTestRule by composeTestRule {

    init {
        waitUntilAtLeastOneExists(hasText("Favorites"))
    }

    fun clicksFavoritesTab() {
        onNodeWithText("Favorites").performClick()
    }

    fun favoriteBreedIsDisplayed() {
        onNodeWithText("australian").assertIsDisplayed()
    }

    fun clicksFavoriteBreed() {
        onNodeWithText("australian").performClick()
    }

    fun emptyFavoriteTextIsDisplayed() {
        onNodeWithText("No favorites breed found \nClick the fav icon on dog details screen")
            .assertIsDisplayed()
    }
}
