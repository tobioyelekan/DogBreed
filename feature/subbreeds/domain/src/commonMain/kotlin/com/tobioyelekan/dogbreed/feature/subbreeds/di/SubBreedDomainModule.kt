package com.tobioyelekan.dogbreed.feature.subbreeds.di

import com.tobioyelekan.dogbreed.feature.subbreeds.usecase.GetSubBreedImageUseCase
import org.koin.dsl.module

val subBreedDomainModule = module {
    single { GetSubBreedImageUseCase(get()) }
}