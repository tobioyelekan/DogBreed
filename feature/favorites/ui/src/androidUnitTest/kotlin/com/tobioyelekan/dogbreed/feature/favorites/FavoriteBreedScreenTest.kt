package com.tobioyelekan.dogbreed.feature.favorites

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
class FavoriteBreedScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val favoriteDogBreeds = dogBreeds.filter { it.isFavorite }

    @Test
    fun loadingIndicatorShouldShow_whenScreenIsInitiallyOpens() {
        setFavoriteBreedScreenContent(viewState = FavoriteBreedUIState.Loading)

        with(composeTestRule) {
            onNodeWithTag("loader").assertIsDisplayed()
        }
    }

    @Test
    fun shouldShowListOfItems_whenSuccessStateIsReceived() {
        setFavoriteBreedScreenContent(viewState = FavoriteBreedUIState.Success(favoriteDogBreeds))

        with(composeTestRule) {
            onNodeWithTag("loader").assertIsNotDisplayed()
            onAllNodesWithTag("Item").assertCountEquals(favoriteDogBreeds.size)
        }
    }

    @Test
    fun shouldShowEmptyState_whenEmptyStateIsReceived() {
        setFavoriteBreedScreenContent(viewState = FavoriteBreedUIState.Success(emptyList()))

        with(composeTestRule) {
            onNodeWithTag("loader").assertIsNotDisplayed()
            onNodeWithTag("emptyState").assertIsDisplayed()
        }
    }

    @Test
    fun shouldShowError_whenErrorStateIsReceived() {
        setFavoriteBreedScreenContent(viewState = FavoriteBreedUIState.Error("Something went wrong"))

        with(composeTestRule) {
            onNodeWithTag("loader").assertIsNotDisplayed()
            onNodeWithText("Something went wrong").assertIsDisplayed()
        }
    }

    private fun setFavoriteBreedScreenContent(
        viewState: FavoriteBreedUIState,
        onBreedClicked: (String) -> Unit = {}
    ) {
        composeTestRule.setContentWithTheme {
            FavoriteBreedScreenContent(
                viewState = viewState,
                onBreedClicked = onBreedClicked
            )
        }
    }
}