package com.tobioyelekan.dogbreed.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.tobioyelekan.dogbreed.feature.allbreeds.navigation.allBreedRoute

@Composable
fun RowScope.DogBreedBottomNavigationItems(
    items: List<DogBreedTabModel>,
    currentDestination: NavDestination?,
    bottomBarNavController: NavHostController
) {
    items.forEachIndexed { _, screen ->

        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = screen.iconRes,
                    contentDescription = null
                )
            },
            label = {
                Text(screen.title, maxLines = 1)
            },
            selected = currentDestination?.hierarchy?.any {
                it.route?.startsWith(screen.route) == true
            } == true,
            onClick = {
                bottomBarNavController.navigate(screen.route) {
                    // Pop up to the start destination of the graph to
                    // avoid building up a large stack of destinations
                    // on the back stack as users select items
                    popUpTo(bottomBarNavController.graph.findStartDestination().id) {
                        saveState = true
                    }

                    // Avoid multiple copies of the same destination when
                    // re-selecting the same item
                    launchSingleTop = true
                    // Restore state when re-selecting a previously selected item
                    if (screen.route != allBreedRoute) {
                        restoreState = true
                    }
                }
            }
        )
    }
}