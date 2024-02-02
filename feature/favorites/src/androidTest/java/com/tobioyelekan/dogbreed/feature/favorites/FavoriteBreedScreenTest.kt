package com.tobioyelekan.dogbreed.feature.favorites

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.tobioyelekan.dogbreed.testing.data.TestData.dogBreeds
import org.junit.Rule
import org.junit.Test

class FavoriteBreedScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun loadingIndicatorShouldShow_whenScreenIsInitiallyOpens() {
        composeTestRule.setContent {
            FavoriteBreedScreenContent(
                viewState = FavoriteBreedUIState.Loading,
                onBreedClicked = {}
            )
        }

        composeTestRule.onNodeWithTag("loader")
            .assertIsDisplayed()
    }

    @Test
    fun shouldShowListOfItems_whenSuccessStateIsReceived() {
        composeTestRule.setContent {
            FavoriteBreedScreenContent(
                viewState = FavoriteBreedUIState.Success(dogBreeds.filter { it.isFavorite }),
                onBreedClicked = {}
            )
        }

        composeTestRule.onNodeWithTag("loader")
            .assertIsNotDisplayed()

        composeTestRule.onAllNodesWithTag("Item")
            .assertCountEquals(dogBreeds.filter { it.isFavorite }.size)
    }

    @Test
    fun shouldShowEmptyState_when_EmptyStateIsReceived() {
        composeTestRule.setContent {
            FavoriteBreedScreenContent(
                viewState = FavoriteBreedUIState.Empty,
                onBreedClicked = {}
            )
        }

        composeTestRule.onNodeWithTag("loader")
            .assertIsNotDisplayed()

        composeTestRule.onNodeWithTag("emptyState")
            .assertIsDisplayed()
    }

    @Test
    fun shouldShowError_whenErrorStateIsReceived() {
        composeTestRule.setContent {
            FavoriteBreedScreenContent(
                viewState = FavoriteBreedUIState.Error("Something went wrong"),
                onBreedClicked = {}
            )
        }

        composeTestRule.onNodeWithTag("loader")
            .assertIsNotDisplayed()

        composeTestRule.onNodeWithText("Something went wrong")
            .assertIsDisplayed()
    }
}