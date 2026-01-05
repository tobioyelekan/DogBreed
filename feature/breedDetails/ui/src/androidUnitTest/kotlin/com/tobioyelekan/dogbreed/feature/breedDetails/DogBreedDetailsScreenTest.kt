package com.tobioyelekan.dogbreed.feature.breedDetails

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tobioyelekan.dogbreed.core.testing.TestData
import com.tobioyelekan.dogbreed.core.testing.ui.setContentWithTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DogBreedDetailsScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun loadingIndicatorShouldShow_andAppBarTitleShows_whenScreenIsInitiallyOpens() {
        setDogBreedDetailsScreenContent(
            appBarTitle = "app bar",
            viewState = DogBreedDetailsUIState.Loading
        )

        with(composeTestRule) {
            onNodeWithTag("loader").assertIsDisplayed()
            onNodeWithText("app bar").assertIsDisplayed()
        }
    }

    @Test
    fun ensureThatOutlinedFavoriteIconIsDisplayed_whenDogBreedIsFavoriteFalse() {
        setDogBreedDetailsScreenContent(
            viewState = DogBreedDetailsUIState.Success(TestData.dogBreeds[0])
        )

        with(composeTestRule) {
            onNode(
                hasContentDescription("click to add breed as favorite")
            ).assertIsDisplayed()
        }
    }

    @Test
    fun ensureThatFilledFavoriteIconIsDisplayed_whenDogBreedIsFavoriteTrue() {
        setDogBreedDetailsScreenContent(
            viewState = DogBreedDetailsUIState.Success(TestData.dogBreeds[2])
        )

        with(composeTestRule) {
            onNode(
                hasContentDescription("click to remove breed as favorite")
            ).assertIsDisplayed()
        }
    }

    @Test
    fun ensureThatBreedDetailsIsDisplayed_andSubbreedsIsDisplayed() {
        setDogBreedDetailsScreenContent(
            viewState = DogBreedDetailsUIState.Success(TestData.dogBreeds[0])
        )

        with(composeTestRule) {
            onNodeWithTag("image").assertIsDisplayed()
            onAllNodesWithTag("subbreedItem")
                .assertCountEquals(TestData.dogBreeds[0].subBreeds.size)
        }
    }

    @Test
    fun ensureThatBreedDetailsIsDisplayed_andSubbreedsIsNotDisplayed() {
        setDogBreedDetailsScreenContent(
            viewState = DogBreedDetailsUIState.Success(TestData.dogBreeds[1])
        )

        with(composeTestRule) {
            onNodeWithTag("image").assertIsDisplayed()
            onNodeWithText("No sub breeds listed").assertIsDisplayed()
            onNodeWithTag("subbreedItem").assertIsNotDisplayed()
        }
    }

    @Test
    fun assertThatScreenShowsError_whenErrorStateIsDisplayed() {
        setDogBreedDetailsScreenContent(
            viewState = DogBreedDetailsUIState.Error("Something went wrong")
        )

        with(composeTestRule) {
            onNodeWithText("Something went wrong").assertIsDisplayed()
        }
    }

    private fun setDogBreedDetailsScreenContent(
        appBarTitle: String = "",
        viewState: DogBreedDetailsUIState,
        onBackClicked: () -> Unit = {},
        onFavoriteClicked: (Boolean) -> Unit = {},
        onSubBreedClicked: (String, String) -> Unit = { _, _ -> }
    ) {
        composeTestRule.setContentWithTheme {
            DogBreedDetailsScreenContent(
                appBarTitle = appBarTitle,
                viewState = viewState,
                onBackClicked = onBackClicked,
                onFavoriteClicked = onFavoriteClicked,
                onSubBreedClicked = onSubBreedClicked
            )
        }
    }
}