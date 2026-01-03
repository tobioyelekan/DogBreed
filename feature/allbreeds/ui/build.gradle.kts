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
        commonMain.dependencies {
            implementation(projects.feature.allbreeds.domain)
            implementation(projects.feature.allbreeds.data)
            implementation(projects.core.designsystem)
            implementation(projects.core.common)
            implementation(projects.core.coroutine)
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.navigation.compose)
            implementation(libs.koin.compose.viewmodel)
        }
        androidUnitTest.dependencies {
            implementation(projects.core.testing)
            implementation(projects.core.testing.ui)
        }
    }
}

android {
    namespace = "com.tobioyelekan.dogbreed.feature.allbreeds"
    compileSdk = 35

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    packaging {
        resources.excludes.add("META-INF/*")
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}