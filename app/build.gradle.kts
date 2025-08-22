import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dagger.hilt)
}

android {
    namespace = "com.tobioyelekan.dogbreed"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.tobioyelekan.dogbreed"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.tobioyelekan.dogbreed.testing.DogBreedTestRunner"
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
    kotlin {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_17
        }
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
                "META-INF/LICENSE"
            )
        }
    }
}

dependencies {
    implementation(project(":feature:allbreeds"))
    implementation(project(":feature:breedDetails"))
    implementation(project(":feature:favorites"))
    implementation(project(":feature:subbreeds"))

    implementation(project(":core:designsystem"))

    implementation(libs.compose.icons.extended)

    implementation(libs.hilt.compose)
    implementation(libs.hilt.core)
    implementation(libs.androidx.test.core)
    ksp(libs.hilt.compiler)

    kspTest(libs.hilt.compiler)
    kspAndroidTest(libs.hilt.compiler)

    debugImplementation(libs.compose.test.manifest)
    debugImplementation(libs.hilt.android.testing)

    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.hilt.android.testing)
    androidTestImplementation(libs.compose.ui.test)
    androidTestImplementation(libs.android.junit)
    androidTestImplementation(project(":core:database"))
    androidTestImplementation(project(":core:network"))
    androidTestImplementation(project(":core:testing"))

    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.rules)
    androidTestImplementation(libs.espresso.core)
}