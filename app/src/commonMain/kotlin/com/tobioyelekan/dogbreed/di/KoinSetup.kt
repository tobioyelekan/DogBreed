package com.tobioyelekan.dogbreed.di

import com.tobioyelekan.dogbreed.core.coroutine.coroutineDispatcherModule
import com.tobioyelekan.dogbreed.core.database.di.dogBreedDatabaseModule
import com.tobioyelekan.dogbreed.core.network.networkModule
import com.tobioyelekan.dogbreed.feature.allbreeds.di.allBreedsUiModule
import com.tobioyelekan.dogbreed.feature.breedDetails.di.breedDetailsUiModule
import com.tobioyelekan.dogbreed.feature.favorites.di.favoriteBreedUiModule
import com.tobioyelekan.dogbreed.feature.subbreeds.di.subBreedUiModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration? = null) {
    startKoin {
        appDeclaration?.let { it() }
        modules(
            listOf(
                coroutineDispatcherModule,
                networkModule,
                dogBreedDatabaseModule,
                allBreedsUiModule,
                breedDetailsUiModule,
                favoriteBreedUiModule,
                subBreedUiModule
            )
        )
    }
}