package com.tobioyelekan.dogbreed

import android.app.Application
import com.tobioyelekan.dogbreed.core.coroutine.coroutineDispatcherModule
import com.tobioyelekan.dogbreed.core.database.di.dogBreedDatabaseModule
import com.tobioyelekan.dogbreed.core.network.networkModule
import com.tobioyelekan.dogbreed.feature.allbreeds.di.allBreedsUiModule
import com.tobioyelekan.dogbreed.feature.breedDetails.di.breedDetailsUiModule
import com.tobioyelekan.dogbreed.feature.favorites.di.favoriteBreedUiModule
import com.tobioyelekan.dogbreed.feature.subbreeds.di.subBreedUiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DogBreedApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@DogBreedApp)
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
}