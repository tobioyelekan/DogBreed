package com.tobioyelekan.dogbreed.feature.favorites.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tobioyelekan.dogbreed.feature.favorites.FavoriteBreedScreen
import com.tobioyelekan.dogbreed.feature.favorites.FavoriteBreedViewModel
import org.koin.compose.viewmodel.koinViewModel

const val favoritesBreedRoute = "favorite_breed_route"

fun NavGraphBuilder.favoritesBreedRoute(onBreedClicked: (String) -> Unit) {
    composable(favoritesBreedRoute) {
        FavoriteBreedScreen(
            onBreedClicked = onBreedClicked,
            viewModel = koinViewModel<FavoriteBreedViewModel>()
        )
    }
}