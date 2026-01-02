package com.tobioyelekan.dogbreed.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tobioyelekan.dogbreed.feature.allbreeds.navigation.allBreedRoute
import com.tobioyelekan.dogbreed.feature.favorites.navigation.favoritesBreedRoute

const val tabHostDestination = "tabHostDestination"

data class DogBreedTabModel(
    val title: String,
    val route:String,
    val iconRes: ImageVector
)

private val tabs = mutableListOf(
    DogBreedTabModel(
        title = "All Breeds",
        route = allBreedRoute,
        iconRes = Icons.Filled.Pets
    ),
    DogBreedTabModel(
        title = "Favorites",
        route = favoritesBreedRoute,
        iconRes = Icons.Filled.Favorite
    )
)

@Composable
fun DogBreedMainTabComponent(
    mainNavController: NavHostController,
) {

    val bottomBarNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomAppBar {
                val navBackStackEntry by bottomBarNavController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                DogBreedBottomNavigationItems(
                    items = tabs,
                    currentDestination = currentDestination,
                    bottomBarNavController = bottomBarNavController
                )
            }
        }) { innerPadding ->
        DogBreedBottomNavComposeDestinations(
            bottomBarNavController = bottomBarNavController,
            mainNavController = mainNavController,
            innerPadding = innerPadding
        )
    }
}