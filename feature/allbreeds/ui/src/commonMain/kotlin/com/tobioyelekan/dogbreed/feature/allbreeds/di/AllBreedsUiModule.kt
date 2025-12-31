package com.tobioyelekan.dogbreed.feature.allbreeds.di

import com.tobioyelekan.dogbreed.feature.allbreeds.AllBreedsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val allBreedsUiModule = module {
    includes(
        allBreedDataModule,
        allBreedDomainModule,
    )

    viewModelOf(::AllBreedsViewModel)
}