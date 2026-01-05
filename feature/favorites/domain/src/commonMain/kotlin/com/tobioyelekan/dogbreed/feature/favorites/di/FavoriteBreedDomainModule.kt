package com.tobioyelekan.dogbreed.feature.favorites.di

import com.tobioyelekan.dogbreed.feature.favorites.GetFavoriteBreedsUseCase
import org.koin.dsl.module

val favoriteBreedDomainModule = module {
    single { GetFavoriteBreedsUseCase(get()) }
}
