plugins {
    kotlin("multiplatform")
}

kotlin {
    jvmToolchain(17)
    jvm()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            api(projects.core.model)
            implementation(libs.kotlin.coroutine)
            implementation(projects.feature.breedDetails.domain)
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
        }
        jvmTest.dependencies{
            implementation(projects.core.testing)
        }
    }
}