plugins {
    kotlin("multiplatform")
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    jvmToolchain(17)
    androidTarget()
    jvm()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.test.runner)
            implementation(projects.core.designsystem)
            implementation(projects.feature.allbreeds.ui)
            implementation(projects.feature.breedDetails.ui)
            implementation(projects.feature.favorites.ui)
            implementation(projects.feature.subbreeds.ui)
            implementation(projects.core.network.api)
            implementation(projects.core.database.api)
            implementation(projects.core.database.implementation)
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.android)
            implementation(libs.koin.core)
        }
    }
}

android {
    namespace = "com.tobioyelekan.dogbreed.core.testing.integration"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}