package com.tobioyelekan.dogbreed

import android.app.Application
import com.tobioyelekan.dogbreed.di.initKoin
import org.koin.android.ext.koin.androidContext

class DogBreedApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@DogBreedApp)
        }
    }
}