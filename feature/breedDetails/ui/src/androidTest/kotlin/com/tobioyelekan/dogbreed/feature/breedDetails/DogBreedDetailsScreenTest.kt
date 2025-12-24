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
import com.tobioyelekan.dogbreed.testing.data.TestData.dogBreeds
import org.junit.Rule
import org.junit.Test

class DogBreedDetailsScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun loadingIndicatorShouldShow_andAppBarTitleShows_whenScreenIsInitiallyOpens() {
        composeTestRule.setContent {
            DogBreedDetailsScreenContent(
                appBarTitle = "app bar",
                viewState = DogBreedDetailsUIState.Loading,
                onBackClicked = {},
                onFavoriteClicked = {},
                onSubBreedClicked = { a, b -> }
            )
        }

        composeTestRule.onNodeWithTag("loader")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("app bar")
            .assertIsDisplayed()
    }

    @Test
    fun ensureThatOutlinedFavoriteIconIsDisplayed_whenDogBreed_isFavorite_isFalse() {
        composeTestRule.setContent {
            DogBreedDetailsScreenContent(
                appBarTitle = "",
                viewState = DogBreedDetailsUIState.Success(dogBreeds[0]),
                onBackClicked = {},
                onFavoriteClicked = { },
                onSubBreedClicked = { a, b -> }
            )
        }

        composeTestRule.onNode(
            hasContentDescription("click to add breed as favorite")
        ).assertIsDisplayed()
    }

    @Test
    fun ensureThatFilledFavoriteIconIsDisplayed_whenDogBreed_isFavorite_isTrue() {
        composeTestRule.setContent {
            DogBreedDetailsScreenContent(
                appBarTitle = "",
                viewState = DogBreedDetailsUIState.Success(dogBreeds[2]),
                onBackClicked = {},
                onFavoriteClicked = { },
                onSubBreedClicked = { a, b -> }
            )
        }

        composeTestRule.onNode(
            hasContentDescription("click to remove breed as favorite")
        ).assertIsDisplayed()
    }

    @Test
    fun ensureThatBreedDetailsIsDisplayed_and_Subbreeds_isDisplayed(){
        composeTestRule.setContent {
            DogBreedDetailsScreenContent(
                appBarTitle = "",
                viewState = DogBreedDetailsUIState.Success(dogBreeds[0]),
                onBackClicked = {},
                onFavoriteClicked = { },
                onSubBreedClicked = { a, b -> }
            )
        }

        composeTestRule.onNodeWithTag("image")
            .assertIsDisplayed()

        composeTestRule.onAllNodesWithTag("subbreedItem")
            .assertCountEquals(dogBreeds[0].subBreeds.size)
    }

    @Test
    fun ensureThatBreedDetailsIsDisplayed_and_Subbreeds_isNotDisplayed_w(){
        composeTestRule.setContent {
            DogBreedDetailsScreenContent(
                appBarTitle = "",
                viewState = DogBreedDetailsUIState.Success(dogBreeds[1]),
                onBackClicked = {},
                onFavoriteClicked = { },
                onSubBreedClicked = { a, b -> }
            )
        }

        composeTestRule.onNodeWithTag("image")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Sub breeds")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("No sub breeds listed")
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag("subbreedItem")
            .assertIsNotDisplayed()
    }

    @Test
    fun assertThatScreenShowsError_when_ErrorStateIsDisplayed(){
        composeTestRule.setContent {
            DogBreedDetailsScreenContent(
                appBarTitle = "",
                viewState = DogBreedDetailsUIState.Error("Something went wrong"),
                onBackClicked = { },
                onFavoriteClicked = {},
                onSubBreedClicked = {a, b->}
            )
        }

        composeTestRule.onNodeWithText("Something went wrong")
            .assertIsDisplayed()
    }
}