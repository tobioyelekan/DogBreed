package com.tobioyelekan.dogbreed.feature.allbreeds

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.tobioyelekan.dogbreed.testing.data.TestData.dogBreeds
import org.junit.Rule
import org.junit.Test

class AllBreedScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun loadingIndicatorShouldShow_whenScreenIsInitiallyOpens() {
        composeTestRule.setContent {
            AllBreedScreenContent(
                viewState = AllBreedsUiState.Loading,
                onBreedClicked = {}
            )
        }

        composeTestRule.onNodeWithTag("loader")
            .isDisplayed()
    }

    @Test
    fun shouldShowListOfItems_whenSuccessStateIsReceived() {
        composeTestRule.setContent {
            AllBreedScreenContent(
                viewState = AllBreedsUiState.Success(dogBreeds),
                onBreedClicked = {}
            )
        }

        composeTestRule.onNodeWithTag("loader")
            .assertIsNotDisplayed()

        composeTestRule.onAllNodesWithTag("Item")
            .assertCountEquals(dogBreeds.size)
    }

    @Test
    fun shouldShowError_whenErrorStateIsReceived() {
        composeTestRule.setContent {
            AllBreedScreenContent(
                viewState = AllBreedsUiState.Error("Something went wrong"),
                onBreedClicked = {}
            )
        }

        composeTestRule.onNodeWithTag("loader")
            .assertIsNotDisplayed()

        composeTestRule.onNodeWithText("Something went wrong")
            .isDisplayed()
    }
}