package com.tobioyelekan.dogbreed.feature.subbreeds

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tobioyelekan.dogbreed.core.testing.TestData.subBreedImages
import com.tobioyelekan.dogbreed.core.testing.ui.setContentWithTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SubBreedScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun loadingIndicatorShouldShow_andAppBarTitleShows_whenScreenIsInitiallyOpens() {
        setSubBreedScreenContent(viewState = SubBreedUIState.Loading)

        with(composeTestRule) {
            onNodeWithTag("loader").assertIsDisplayed()
            onNodeWithText("Subbreed").assertIsDisplayed()
        }
    }

    @Test
    fun ensureListOfSubbreedsIsDisplayed_whenSuccessStateIsReceived() {
        setSubBreedScreenContent(viewState = SubBreedUIState.Success(subBreedImages))

        with(composeTestRule) {
            onAllNodesWithTag("subBreedImageItem")
                .assertCountEquals(subBreedImages.size)
        }
    }

    @Test
    fun showError_whenErrorStateIsReceived() {
        setSubBreedScreenContent(viewState = SubBreedUIState.Error("Something went wrong"))

        with(composeTestRule) {
            onNodeWithText("Something went wrong").assertIsDisplayed()
        }
    }

    private fun setSubBreedScreenContent(
        appBarTitle: String = "Subbreed",
        viewState: SubBreedUIState,
        onBackClicked: () -> Unit = {}
    ) {
        composeTestRule.setContentWithTheme {
            SubBreedScreenContent(
                appBarTitle = appBarTitle,
                viewState = viewState,
                onBackClicked = onBackClicked
            )
        }
    }
}
