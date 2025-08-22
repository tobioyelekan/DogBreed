import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.tobioyelekan.dogbreed.testing"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlin {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_17
        }
    }
}

dependencies {
    api(libs.junit)
    api(libs.turbine)
    api(libs.mockk)
    api(libs.coroutine.test)

    api(project(":core:model"))
    api(project(":data:allbreeds"))
    api(project(":data:breedDetails"))
    api(project(":data:subbreeds"))

    implementation(libs.hilt.android.testing)
    implementation(libs.androidx.test.runner)
}