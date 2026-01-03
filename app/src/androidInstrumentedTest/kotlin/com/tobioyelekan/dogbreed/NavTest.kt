package com.tobioyelekan.dogbreed

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.tobioyelekan.dogbreed.core.database.dao.DogBreedDao
import com.tobioyelekan.dogbreed.core.testing.integration.FakeDogBreedApiService
import com.tobioyelekan.dogbreed.robot.allBreedsRobot
import com.tobioyelekan.dogbreed.robot.breedDetailsRobot
import com.tobioyelekan.dogbreed.robot.favoriteBreedsRobot
import com.tobioyelekan.dogbreed.robot.subBreedRobot
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.get

@RunWith(AndroidJUnit4::class)
@LargeTest
class NavTest : KoinTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var dogBreedDao: DogBreedDao

    @Before
    fun setUp() {
        dogBreedDao = get()

        runBlocking {
            FakeDogBreedApiService.allBreedAPIErrorOccurred = false
            FakeDogBreedApiService.subbreedAPIErrorOccurred = false
        }
    }

    @After
    fun tearDown() {
        runBlocking {
            FakeDogBreedApiService.allBreedAPIErrorOccurred = false
            FakeDogBreedApiService.subbreedAPIErrorOccurred = false
            dogBreedDao.nukeTable()
        }
    }

    @Test
    fun canSeeBreedsScreen() {
        composeTestRule.apply {
            allBreedsRobot {}
        }
    }

//    @Test
//    fun canSeeBreedsScreen_errorOccurred() {
//        FakeDogBreedApiService.allBreedAPIErrorOccurred = true
//
//        composeTestRule.apply {
//            allBreedsRobot {
//                errorShown()
//            }
//        }
//    }

    @Test
    fun addDogBreedToFavorites() {
        composeTestRule.apply {
            allBreedsRobot {
                clickOnBreed("affenpinscher")
            }

            composeTestRule.waitForIdle()

            breedDetailsRobot("Affenpinscher") {
                addToFavouritesDisplayed()
                clicksAddFavourites()
                removeFromFavouritesDisplayed()
                pressBack()
            }

            composeTestRule.waitForIdle()

            allBreedsRobot {
                clickOnBreed("affenpinscher")
            }

            composeTestRule.waitForIdle()

            breedDetailsRobot("Affenpinscher") {
                removeFromFavouritesDisplayed()
            }
        }
    }

    @Test
    fun removeDogBreedFromFavorites() {
        composeTestRule.apply {
            allBreedsRobot {
                runBlocking {
                    dogBreedDao.updateBreed("affenpinscher", true)
                }

                clickOnBreed("affenpinscher")
            }

            breedDetailsRobot("Affenpinscher") {
                removeFromFavouritesDisplayed()
                clicksRemoveFavourites()
                addToFavouritesDisplayed()
                pressBack()
            }

            //see all breeds
            allBreedsRobot {
                clickOnBreed("affenpinscher")
            }

            //confirm removed
            breedDetailsRobot("Affenpinscher") {
                addToFavouritesDisplayed()
            }
        }
    }

    @Test
    fun canSeeFavoritesScreen() {
        composeTestRule.apply {
            favoriteBreedsRobot {
                runBlocking {
                    dogBreedDao.updateBreed("australian", true)
                }

                //move to fav
                clicksFavoritesTab()
                favoriteBreedIsDisplayed()
                clicksFavoriteBreed()
            }

            breedDetailsRobot("Australian") {
                clicksRemoveFavourites()
                pressBack()
            }

            //confirm fav removed
            favoriteBreedsRobot {
                emptyFavoriteTextIsDisplayed()
            }
        }
    }

    @Test
    fun clickOnABreedShouldSeeBreedDetailsScreen_NoSubBreedsListed() {
        composeTestRule.apply {
            allBreedsRobot {
                clickOnBreed("affenpinscher")
            }

            breedDetailsRobot("Affenpinscher") {
                seesEmptySubBreedText()
            }
        }
    }

    @Test
    fun clickOnABreedShouldSeeBreedDetailsScreen_SubBreedsListed() {
        composeTestRule.apply {
            allBreedsRobot {
                clickOnBreed("australian")
            }

            breedDetailsRobot("Australian") {
                seesSubBreed()
                clicksASubBreed()
            }

            subBreedRobot {
                verifySubBreedListDisplayed()
            }
        }
    }

//    @Test
//    fun clickOnABreedShouldSeeBreedDetailsScreen_SubBreedsListed_errorOccurred() {
//        composeTestRule.apply {
//            allBreedsRobot {
//                clickOnBreed("australian")
//            }
//
//            //breedDetails
//            breedDetailsRobot("Australian") {
//                seesSubBreed()
//                addToFavouritesDisplayed()
//                clicksASubBreed()
//            }
//
//            subBreedRobot {
//                errorShown()
//                pressBack()
//            }
//
//            breedDetailsRobot("Australian") {
//                pressBack()
//            }
//
//            allBreedsRobot { }
//        }
//    }
}