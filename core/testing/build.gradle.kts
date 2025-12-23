plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
    }
}

//android {
//    namespace = "com.tobioyelekan.dogbreed.testing"
//    compileSdk = 34
//
//    defaultConfig {
//        minSdk = 24
//
//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//        consumerProguardFiles("consumer-rules.pro")
//    }
//
//    buildTypes {
//        release {
//            isMinifyEnabled = false
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro"
//            )
//        }
//    }
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_1_8
//        targetCompatibility = JavaVersion.VERSION_1_8
//    }
//    kotlinOptions {
//        jvmTarget = "1.8"
//    }
//}

dependencies {
    api(libs.junit)
    api(libs.turbine)
    api(libs.mockk)
    api(libs.coroutine.test)

    implementation(projects.core.model)
//    api(projects.data.allbreeds)
//    api(projects.data.breedDetails)
//    api(projects.data.subbreeds)

//    implementation(libs.hilt.android.testing)
//    implementation(libs.androidx.test.runner)
}