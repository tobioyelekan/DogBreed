package com.tobioyelekan.dogbreed.feature.favorites.di

import com.tobioyelekan.dogbreed.feature.breedDetails.di.breedDetailsDataModule
import com.tobioyelekan.dogbreed.feature.favorites.FavoriteBreedViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val favoriteBreedUiModule = module {
    includes(favoriteBreedDomainModule)
    includes(breedDetailsDataModule)

    viewModelOf(::FavoriteBreedViewModel)
}