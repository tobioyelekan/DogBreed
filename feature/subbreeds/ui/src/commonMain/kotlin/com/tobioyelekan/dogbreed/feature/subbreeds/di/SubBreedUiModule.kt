package com.tobioyelekan.dogbreed.feature.subbreeds.di

import com.tobioyelekan.dogbreed.feature.subbreeds.SubBreedViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val subBreedUiModule  = module {
    includes(subBreedDataModule)
    includes(subBreedDomainModule)

    viewModelOf(::SubBreedViewModel)
}