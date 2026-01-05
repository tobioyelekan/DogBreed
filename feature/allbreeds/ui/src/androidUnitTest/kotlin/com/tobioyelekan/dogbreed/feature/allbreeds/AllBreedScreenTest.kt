package com.tobioyelekan.dogbreed.feature.allbreeds

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tobioyelekan.dogbreed.core.testing.TestData.dogBreeds
import com.tobioyelekan.dogbreed.core.testing.ui.setContentWithTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AllBreedScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun loadingIndicatorShouldShow_whenScreenIsInitiallyOpens() {
        setAllBreedScreenContent(viewState = AllBreedsUiState.Loading)

        with(composeTestRule) {
            onNodeWithTag("loader").assertIsDisplayed()
        }
    }

    @Test
    fun shouldShowListOfItems_whenSuccessStateIsReceived() {
        setAllBreedScreenContent(viewState = AllBreedsUiState.Success(dogBreeds))

        with(composeTestRule) {
            onNodeWithTag("loader").assertIsNotDisplayed()
            onAllNodesWithTag("Item").assertCountEquals(dogBreeds.size)
        }
    }

    @Test
    fun shouldShowError_whenErrorStateIsReceived() {
        setAllBreedScreenContent(viewState = AllBreedsUiState.Error("Something went wrong"))

        with(composeTestRule) {
            onNodeWithTag("loader").assertIsNotDisplayed()
            onNodeWithText("Something went wrong").assertIsDisplayed()
        }
    }

    private fun setAllBreedScreenContent(
        viewState: AllBreedsUiState,
        onBreedClicked: (String) -> Unit = {}
    ) {
        composeTestRule.setContentWithTheme {
            AllBreedScreenContent(
                viewState = viewState,
                onBreedClicked = onBreedClicked
            )
        }
    }
}