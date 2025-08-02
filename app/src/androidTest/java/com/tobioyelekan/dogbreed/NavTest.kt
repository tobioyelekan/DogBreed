//package com.tobioyelekan.dogbreed
//
//import androidx.compose.ui.test.ExperimentalTestApi
//import androidx.compose.ui.test.assertIsDisplayed
//import androidx.compose.ui.test.hasText
//import androidx.compose.ui.test.junit4.createAndroidComposeRule
//import androidx.compose.ui.test.onAllNodesWithTag
//import androidx.compose.ui.test.onFirst
//import androidx.compose.ui.test.onNodeWithContentDescription
//import androidx.compose.ui.test.onNodeWithTag
//import androidx.compose.ui.test.onNodeWithText
//import androidx.compose.ui.test.performClick
//import com.tobioyelekan.dogbreed.core.database.dao.DogBreedDao
//import com.tobioyelekan.dogbreed.core.network.FakeDogBreedApiService
//import dagger.hilt.android.testing.HiltAndroidRule
//import dagger.hilt.android.testing.HiltAndroidTest
//import kotlinx.coroutines.runBlocking
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import javax.inject.Inject
//
//@HiltAndroidTest
//@OptIn(ExperimentalTestApi::class)
//class NavTest {
//
//    @get:Rule(order = 0)
//    val hiltRule = HiltAndroidRule(this)
//
//    @get:Rule(order = 1)
//    val composeTestRule = createAndroidComposeRule<MainActivity>()
//
//    @Inject
//    lateinit var dogBreedDao: DogBreedDao
//
//    @Before
//    fun setUp() {
//        hiltRule.inject()
//
//        runBlocking {
//            FakeDogBreedApiService.allBreedAPIErrorOccurred = false
//            FakeDogBreedApiService.subbreedAPIErrorOccurred = false
//            dogBreedDao.nukeTable()
//        }
//    }
//
//    @Test
//    fun canSeeBreedsScreen() {
//        composeTestRule.apply {
//            waitUntilAtLeastOneExists(hasText("All Breeds"))
//            //can see a breed
//            onNodeWithText("affenpinscher").assertIsDisplayed()
//        }
//    }
//
//    @Test
//    fun canSeeBreedsScreen_errorOccurred() {
//        FakeDogBreedApiService.allBreedAPIErrorOccurred = true
//
//        composeTestRule.apply {
//            waitUntilAtLeastOneExists(hasText("All Breeds"))
//            onNodeWithTag("ErrorScreen").assertIsDisplayed()
//        }
//    }
//
//    @Test
//    fun addDogBreedToFavorites() {
//        composeTestRule.apply {
//            waitUntilAtLeastOneExists(hasText("All Breeds"))
//            onNodeWithText("affenpinscher").performClick()
//
//            //breedDetails
//            waitUntilAtLeastOneExists(hasText("Affenpinscher"))
//            onNodeWithContentDescription("click to add breed as favorite")
//                .assertIsDisplayed()
//
//            //click on add favorites
//            onNodeWithContentDescription("click to add breed as favorite")
//                .performClick()
//            onNodeWithContentDescription("click to remove breed as favorite")
//                .assertIsDisplayed()
//
//            //click back
//            onNodeWithContentDescription("navUp").performClick()
//
//            //see breedDetails
//            waitUntilAtLeastOneExists(hasText("All Breeds"))
//            onNodeWithText("affenpinscher").performClick()
//
//            //confirm still favorite
//            waitUntilAtLeastOneExists(hasText("Affenpinscher"))
//            onNodeWithContentDescription("click to remove breed as favorite")
//                .assertIsDisplayed()
//        }
//    }
//
//    @Test
//    fun removeDogBreedFromFavorites() {
//        composeTestRule.apply {
//            waitUntilAtLeastOneExists(hasText("All Breeds"))
//
//            runBlocking {
//                dogBreedDao.updateBreed("affenpinscher", true)
//            }
//
//            onNodeWithText("affenpinscher").performClick()
//
//            //breedDetails
//            waitUntilAtLeastOneExists(hasText("Affenpinscher"))
//            onNodeWithContentDescription("click to remove breed as favorite")
//                .assertIsDisplayed()
//
//            //click to remove favorites
//            onNodeWithContentDescription("click to remove breed as favorite")
//                .performClick()
//            onNodeWithContentDescription("click to add breed as favorite")
//                .assertIsDisplayed()
//
//            //click back
//            onNodeWithContentDescription("navUp").performClick()
//
//            //see all breeds
//            waitUntilAtLeastOneExists(hasText("All Breeds"))
//            onNodeWithText("affenpinscher").performClick()
//
//            //confirm removed
//            waitUntilAtLeastOneExists(hasText("Affenpinscher"))
//            onNodeWithContentDescription("click to add breed as favorite")
//                .assertIsDisplayed()
//        }
//    }
//
//    @Test
//    fun canSeeFavoritesScreen() {
//        composeTestRule.apply {
//            waitUntilAtLeastOneExists(hasText("Favorites"))
//
//            runBlocking {
//                dogBreedDao.updateBreed("australian", true)
//            }
//
//            //move to fav
//            onNodeWithText("Favorites").performClick()
//
//            //confirm australian exists in fav
//            onNodeWithText("australian").assertIsDisplayed()
//
//            //remove australian from favorites
//            onNodeWithText("australian").performClick()
//
//            //breed details
//            waitUntilAtLeastOneExists(hasText("Australian"))
//            onNodeWithContentDescription("click to remove breed as favorite")
//                .performClick()
//
//            //navUp
//            onNodeWithContentDescription("navUp").performClick()
//
//            //confirm removed from favList
//            waitUntilAtLeastOneExists(hasText("Favorites"))
//            onNodeWithText("No favorites breed found \nClick the fav icon on dog details screen")
//                .assertIsDisplayed()
//        }
//    }
//
//    @Test
//    fun clickOnABreedShouldSeeBreedDetailsScreen_NoSubBreedsListed() {
//        composeTestRule.apply {
//            waitUntilAtLeastOneExists(hasText("All Breeds"))
//            onNodeWithText("affenpinscher").performClick()
//
//            //breedDetails
//            waitUntilAtLeastOneExists(hasText("Affenpinscher"))
//            onNodeWithText("No sub breeds listed").assertIsDisplayed()
//        }
//    }
//
//    @Test
//    fun clickOnABreedShouldSeeBreedDetailsScreen_SubBreedsListed() {
//        composeTestRule.apply {
//            waitUntilAtLeastOneExists(hasText("All Breeds"))
//            onNodeWithText("australian").performClick()
//
//            //breedDetails
//            waitUntilAtLeastOneExists(hasText("Australian"))
//            onNodeWithText("Sub breeds").assertIsDisplayed()
//
//            //click on a subbreed
//            onNodeWithText("shepherd").performClick()
//
//            //subbreed screen
//            waitUntilAtLeastOneExists(hasText("Australian Shepherd"))
//            onAllNodesWithTag("subBreedImageItem")
//                .onFirst()
//                .assertExists()
//        }
//    }
//
//    @Test
//    fun clickOnABreedShouldSeeBreedDetailsScreen_SubBreedsListed_errorOccurred() {
//        FakeDogBreedApiService.subbreedAPIErrorOccurred = true
//
//        composeTestRule.apply {
//            waitUntilAtLeastOneExists(hasText("All Breeds"))
//            onNodeWithText("australian").performClick()
//
//            //breedDetails
//            waitUntilAtLeastOneExists(hasText("Australian"))
//            onNodeWithText("Sub breeds").assertIsDisplayed()
//            onNodeWithContentDescription("click to add breed as favorite")
//                .assertIsDisplayed()
//
//            //click on a subbreed
//            onNodeWithText("shepherd").performClick()
//
//            //subbreed screen
//            waitUntilAtLeastOneExists(hasText("Australian Shepherd"))
//            onNodeWithTag("ErrorScreen").assertIsDisplayed()
//
//            //nav back to details
//            onNodeWithContentDescription("navUp").performClick()
//            waitUntilAtLeastOneExists(hasText("Australian"))
//
//            //nav back to home
//            onNodeWithContentDescription("navUp").performClick()
//            waitUntilAtLeastOneExists(hasText("All Breeds"))
//            waitUntilAtLeastOneExists(hasText("Favorites"))
//        }
//    }
//}