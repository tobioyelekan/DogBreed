object Version {
    const val core = "1.9.0"
    const val extendedIcon = "1.6.0-beta01"
    const val navigation = "2.7.5"
    const val lifecycleRuntime = "2.6.2"
    const val composeActivity = "1.8.0"
    const val material3 = "1.2.0-alpha11"
    const val coil = "2.4.0"
    const val retrofit = "2.9.0"
    const val moshi = "1.15.0"
    const val room = "2.6.1"
    const val hilt = "2.48.1"
    const val hiltCompose = "1.1.0"
    const val kotlinSerialization = "1.5.1"
    const val kotlinJakeWhartonSerialization = "1.0.0"
    const val httpLogging = "4.11.0"
    const val timber = "5.0.1"
    const val kotlinCoroutine = "1.7.1"
    const val junit = "4.13.2"
    const val androidJUnit = "1.1.5"
    const val espresso = "3.5.1"
    const val composeBomTest = "2023.03.00"
}

object Dependencies {
    const val core = "androidx.core:core-ktx:${Version.core}"

    object JetpackCompose {
        const val iconsCore = "androidx.compose.material:material-icons-core"
        const val iconsExtended =
            "androidx.compose.material:material-icons-extended:${Version.extendedIcon}"
        const val navigation = "androidx.navigation:navigation-compose:${Version.navigation}"
        const val lifecycleRuntime =
            "androidx.lifecycle:lifecycle-runtime-compose:${Version.lifecycleRuntime}"
        const val composeActivity = "androidx.activity:activity-compose:${Version.composeActivity}"
        const val ui = "androidx.compose.ui:ui"
        const val uiGraphics = "androidx.compose.ui:ui-graphics"
        const val toolingPreview = "androidx.compose.ui:ui-tooling-preview"
        const val material3 = "androidx.compose.material3:material3:${Version.material3}"
    }

    object Coil {
        const val coilCore = "io.coil-kt:coil:${Version.coil}"
        const val coilCompose = "io.coil-kt:coil-compose:${Version.coil}"
    }

    object Retrofit {
        const val retrofitCore = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
        const val retrofitScalarsConverter = "com.squareup.retrofit2:converter-scalars:${Version.retrofit}"
        const val retrofitKotlinSerialization =
            "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Version.kotlinJakeWhartonSerialization}"
    }

    object Serialization {
        const val kotlinSerialization =
            "org.jetbrains.kotlinx:kotlinx-serialization-json:${Version.kotlinSerialization}"
    }

    object Lifecycle {
        const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycleRuntime}"
    }

    object Room {
        const val roomCore = "androidx.room:room-ktx:${Version.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Version.room}"
        const val roomTesting = "androidx.room:room-testing:${Version.room}"
    }

    object Hilt {
        const val hiltCore = "com.google.dagger:hilt-android:${Version.hilt}"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Version.hilt}"
        const val hiltAndroid = "com.google.dagger:hilt-android:${Version.hilt}"
        const val hiltCompose = "androidx.hilt:hilt-navigation-compose:${Version.hiltCompose}"
    }

    object Logging {
        const val interceptorLogging =
            "com.squareup.okhttp3:logging-interceptor:${Version.httpLogging}"
        const val timber = "com.jakewharton.timber:timber:${Version.timber}"
    }

    object Concurrency{
        const val kotlinCoroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.kotlinCoroutine}"
    }

    object Testing {
        const val junit = "junit:junit:${Version.junit}"
        const val androidJUnit = "androidx.test.ext:junit:${Version.androidJUnit}"
        const val espresso = "androidx.test.espresso:espresso-core:${Version.espresso}"
        const val composeBomTest = "androidx.compose:compose-bom:${Version.composeBomTest}"
        const val composeJunitUiTest = "androidx.compose.ui:ui-test-junit4"
        const val composeUiTooling = "androidx.compose.ui:ui-tooling"
        const val composeManifestUITest = "androidx.compose.ui:ui-test-manifest"
    }
}

object Plugins {
    const val androidLibrary = "com.android.library"
    const val kotlinAndroid = "org.jetbrains.kotlin.android"
    const val daggerHilt = "com.google.dagger.hilt.android"
    const val kotlinKapt = "kotlin-kapt"
}