package com.tobioyelekan.dogbreed.feature.subbreeds

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.tobioyelekan.dogbreed.testing.data.TestData.subBreedImages
import org.junit.Rule
import org.junit.Test

class SubBreedScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun loadingIndicatorShouldShow_andAppBarTitleShows_whenScreenIsInitiallyOpens() {
        composeTestRule.setContent {
            SubBreedScreenContent(
                appBarTitle = "Subbreed",
                viewState = SubBreedUIState.Loading,
                onBackClicked = {}
            )
        }

        composeTestRule.onNodeWithTag("loader")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Subbreed")
            .assertIsDisplayed()
    }

    @Test
    fun ensureListOfSubbreedsIsDisplayed_whenSuccessStateIsReceived() {
        composeTestRule.setContent {
            SubBreedScreenContent(
                appBarTitle = "Subbreed",
                viewState = SubBreedUIState.Success(subBreedImages),
                onBackClicked = {}
            )
        }

        composeTestRule.onAllNodesWithTag("subBreedImageItem")
            .assertCountEquals(subBreedImages.size)
    }

    @Test
    fun showError_whenErrorStateIsReceived() {
        composeTestRule.setContent {
            SubBreedScreenContent(
                appBarTitle = "Subbreed",
                viewState = SubBreedUIState.Error("Something went wrong"),
                onBackClicked = {}
            )
        }

        composeTestRule.onNodeWithText("Something went wrong")
            .assertIsDisplayed()
    }
}