package com.tobioyelekan.dogbreed

import android.app.Application
import com.tobioyelekan.dogbreed.core.database.di.dogBreedDatabaseModule
import com.tobioyelekan.dogbreed.core.network.networkModule
import com.tobioyelekan.dogbreed.feature.allbreeds.di.allBreedsUiModule
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@HiltAndroidApp
class DogBreedApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@DogBreedApp)
            modules(
                listOf(
                    networkModule,
                    dogBreedDatabaseModule,
                    allBreedsUiModule
                )
            )
        }
    }
}