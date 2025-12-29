plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp")
    alias(libs.plugins.compose.compiler)
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
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

dependencies {
    implementation(libs.kotlin.coroutine)
    implementation(libs.hilt.core)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.compose)
    implementation(projects.feature.allbreeds.domain)
    implementation(projects.feature.allbreeds.data)
    implementation(projects.core.designsystem)
    implementation(projects.core.common)

    testImplementation(projects.core.testing)
    testImplementation(kotlin("test"))

    implementation(libs.robolectric)
    testImplementation(libs.compose.ui.test)
    debugImplementation(libs.compose.test.manifest)

}