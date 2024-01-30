package com.tobioyelekan.dogbreed.feature.subbreeds.navigation
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tobioyelekan.dogbreed.feature.subbreeds.SubBreedsScreen

const val breedNameArgs = "breedName"
const val subBreedNameArgs = "subBreedName"
const val subBreedRoute = "sub_breed_breed_route/{$breedNameArgs}/{$subBreedNameArgs}"

fun NavController.navigateToSubBreed(breedName:String, subBreedName:String) {
    this.navigate("sub_breed_breed_route/${breedName}/${subBreedName}")
}

fun NavGraphBuilder.subBreedRoute(onBackClicked: () -> Unit) {
    composable(subBreedRoute) {
        SubBreedsScreen(onBackClicked)
    }
}