plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.tobioyelekan.dogbreed"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.tobioyelekan.dogbreed"
        minSdk = 24
        targetSdk = 34
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
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
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
    implementation(projects.feature.allbreeds)
    implementation(projects.feature.breedDetails)
    implementation(projects.feature.favorites)
    implementation(projects.feature.subbreeds)

    implementation(projects.core.designsystem)

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
    androidTestImplementation(projects.core.database)
    androidTestImplementation(projects.core.network)
    androidTestImplementation(projects.core.testing)

    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.rules)
    androidTestImplementation(libs.espresso.core)
}