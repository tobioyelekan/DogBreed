plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
    kotlin("plugin.serialization") version "1.8.10"
}

android {
    namespace = "com.tobioyelekan.dogbreed.core.network"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "BASE_URL", "\"https://dog.ceo/api/\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(Dependencies.Retrofit.retrofitCore)
    implementation(Dependencies.Retrofit.retrofitKotlinSerialization)
    implementation(Dependencies.Retrofit.retrofitScalarsConverter)

    implementation(Dependencies.Serialization.kotlinSerialization)

    implementation(Dependencies.Logging.interceptorLogging)
    implementation(Dependencies.Logging.timber)

    implementation(Dependencies.Hilt.hiltCore)
    implementation(Dependencies.Hilt.hiltAndroid)
    ksp(Dependencies.Hilt.hiltCompiler)
}