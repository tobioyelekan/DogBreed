import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
    jvm()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.allbreeds.ui)
            implementation(projects.feature.breedDetails.ui)
            implementation(projects.feature.favorites.ui)
            implementation(projects.feature.subbreeds.ui)

            implementation(projects.core.network.api)
            implementation(projects.core.database.api)

            implementation(projects.core.designsystem)
            implementation(libs.navigation.compose)
            implementation(projects.core.network.implementation)
            implementation(projects.core.database.implementation)
            implementation(projects.core.coroutine)

            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
        }
        androidMain.dependencies {
            implementation(libs.koin.android)
            implementation(libs.compose.activity)
        }
        androidInstrumentedTest.dependencies {
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.androidx.test.core)
            implementation(libs.androidx.test.runner)
            implementation(libs.android.junit)
            implementation(projects.core.testing)
            implementation(projects.core.testing.ui) {
                exclude(group = "org.robolectric", module = "robolectric")
            }
            implementation(projects.core.testing.integration)

            implementation(libs.androidx.test.rules)
            implementation(libs.espresso.core)
            implementation(libs.koin.test)
            implementation(libs.koin.test.junit4)
        }
    }
}

compose.resources {
    packageOfResClass = "com.tobioyelekan.dogbreed"
    generateResClass = auto
}

android {
    namespace = "com.tobioyelekan.dogbreed"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.tobioyelekan.dogbreed"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner =
            "com.tobioyelekan.dogbreed.core.testing.integration.DogBreedTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField("String", "BASE_URL", "\"https://dog.ceo/api/\"")

        testOptions {
            unitTests {
                isIncludeAndroidResources = true
            }
        }
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
    buildFeatures {
        buildConfig = true
        compose = true
    }
    packaging {
        resources {
            excludes += setOf(
                "/META-INF/{AL2.0,LGPL2.1}",
                "META-INF/LICENSE.md",
                "META-INF/LICENSE-notice.md",
                "META-INF/DEPENDENCIES",
                "META-INF/NOTICE",
                "META-INF/LICENSE",
                "META-INF/versions/9/OSGI-INF/MANIFEST.MF"
            )
        }
    }
}

dependencies {
    debugImplementation(libs.compose.test.manifest)
}