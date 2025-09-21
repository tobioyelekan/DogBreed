plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.tobioyelekan.dogbreed.feature.breedDetails"
    compileSdk = 34

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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources.excludes.add("META-INF/*")
    }
}

dependencies {
    implementation(libs.hilt.core)
    ksp(libs.hilt.compiler)
    implementation(libs.kotlin.coroutine)

    implementation(projects.domain.breedDetails)
    implementation(projects.core.designsystem)
    implementation(projects.core.model)
    implementation(projects.core.common)

    testImplementation(projects.core.testing)
    testImplementation(kotlin("test"))

    androidTestImplementation(projects.core.testing)
    androidTestImplementation(kotlin("test"))

    androidTestImplementation(libs.compose.ui.test)
    debugImplementation(libs.compose.test.manifest)
}