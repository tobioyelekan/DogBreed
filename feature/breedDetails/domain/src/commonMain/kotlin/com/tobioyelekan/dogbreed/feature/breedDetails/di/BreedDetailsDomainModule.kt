package com.tobioyelekan.dogbreed.feature.breedDetails.di

import com.tobioyelekan.dogbreed.feature.breedDetails.usecase.AddFavoriteBreedUseCase
import com.tobioyelekan.dogbreed.feature.breedDetails.usecase.DeleteFavoriteBreedUseCase
import com.tobioyelekan.dogbreed.feature.breedDetails.usecase.GetBreedDetailsUseCase
import org.koin.dsl.module

val breedDetailsDomainModule = module {
    single { AddFavoriteBreedUseCase(get()) }
    single { DeleteFavoriteBreedUseCase(get()) }
    single { GetBreedDetailsUseCase(get()) }
}