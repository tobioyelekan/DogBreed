plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.tobioyelekan.dogbreed.core.database"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    packaging {
        resources.excludes.add("META-INF/*")
    }
}

dependencies {
    implementation(Dependencies.Room.roomCore)
    ksp(Dependencies.Room.roomCompiler)

    implementation(Dependencies.Hilt.hiltCore)
    ksp(Dependencies.Hilt.hiltCompiler)

    implementation(project(":core:model"))

    androidTestImplementation(project(":core:testing"))
    androidTestImplementation(kotlin("test"))

    androidTestImplementation(Dependencies.Room.roomTesting)
    androidTestImplementation(Dependencies.Testing.androidxTestCore)
    androidTestImplementation(Dependencies.Testing.androidxTestRunner)
}