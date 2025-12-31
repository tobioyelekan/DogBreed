package com.tobioyelekan.dogbreed.feature.breedDetails.di

import com.tobioyelekan.dogbreed.feature.breedDetails.DogBreedDetailsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val breedDetailsUiModule = module {
    includes(
        breedDetailsDataModule,
        breedDetailsDomainModule
    )

    viewModelOf(::DogBreedDetailsViewModel)
}