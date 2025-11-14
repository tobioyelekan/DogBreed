plugins {
    id("java-library")
    id("kotlin")
    id("com.google.devtools.ksp")
}

dependencies {
    implementation(libs.hilt.core)
//    ksp(libs.hilt.compiler)

//    implementation(projects.data.subbreeds)
//    implementation(projects.core.common)
    implementation(projects.core.model)

//    testImplementation(projects.core.testing)
    testImplementation(kotlin("test"))
}
