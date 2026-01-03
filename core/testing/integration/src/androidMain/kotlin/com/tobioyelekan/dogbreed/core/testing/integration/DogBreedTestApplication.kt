package com.tobioyelekan.dogbreed.core.testing.integration

import android.app.Application
import com.tobioyelekan.dogbreed.feature.allbreeds.di.allBreedsUiModule
import com.tobioyelekan.dogbreed.feature.breedDetails.di.breedDetailsUiModule
import com.tobioyelekan.dogbreed.feature.favorites.di.favoriteBreedUiModule
import com.tobioyelekan.dogbreed.feature.subbreeds.di.subBreedUiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DogBreedTestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DogBreedTestApplication)
            modules(
                listOf(
                    testDatabaseModule,
                    testNetworkModule,
                    testCoroutineDispatcherModule,
                    allBreedsUiModule,
                    breedDetailsUiModule,
                    favoriteBreedUiModule,
                    subBreedUiModule
                )
            )
        }
    }
}
