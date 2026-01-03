package com.tobioyelekan.dogbreed.core.testing.integration

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class DogBreedTestRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader, name: String, context: Context): Application =
        super.newApplication(cl, DogBreedTestApplication::class.java.name, context)
}