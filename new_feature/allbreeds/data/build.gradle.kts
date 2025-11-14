plugins {
    id("java-library")
    id("kotlin")
    id("com.google.devtools.ksp")
}

dependencies {
    implementation(libs.hilt.core)
    ksp(libs.hilt.compiler)

    implementation(projects.newFeature.allbreeds.domain)
    implementation(projects.core.network)
    implementation(projects.core.common)
    implementation(projects.core.database)
    implementation(projects.core.model)

    testImplementation(projects.core.testing)
    testImplementation(kotlin("test"))
}
