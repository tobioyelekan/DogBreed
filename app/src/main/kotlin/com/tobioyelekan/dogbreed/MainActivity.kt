package com.tobioyelekan.dogbreed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tobioyelekan.dogbreed.core.designsystem.theme.DogBreedTheme
import com.tobioyelekan.dogbreed.navigation.DogBreedMainTabComponent
import com.tobioyelekan.dogbreed.navigation.tabHostDestination
import com.tobioyelekan.dogbreed.feature.breedDetails.navigation.breedDetailsRoute
import com.tobioyelekan.dogbreed.feature.subbreeds.navigation.navigateToSubBreed
import com.tobioyelekan.dogbreed.feature.subbreeds.navigation.subBreedRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DogBreedTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = tabHostDestination
                    ) {
                        composable(tabHostDestination) {
                            DogBreedMainTabComponent(mainNavController = navController)
                        }

                        breedDetailsRoute(
                            onSubBreedClicked = navController::navigateToSubBreed,
                            onBackClicked = navController::popBackStack
                        )

                        subBreedRoute(navController::popBackStack)
                    }
                }
            }
        }
    }
}