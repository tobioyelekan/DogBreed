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
            implementation(projects.core.network.api)
            implementation(projects.core.common)
            implementation(projects.feature.subbreeds.domain)
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
        }
        jvmTest.dependencies {
            implementation(projects.core.testing)
        }
    }
}