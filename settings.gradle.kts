pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "DogBreed"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":app")
include(":core:network")
include(":core:database")
include(":core:model")
include(":core:common")
include(":core:designsystem")
include(":feature:allbreeds")
include(":feature:favorites")
include(":feature:subbreeds")
include(":data:allbreeds")
include(":data:breedDetails")
include(":data:subbreeds")
include(":domain:allbreeds")
include(":domain:breedDetails")
include(":domain:subbreeds")
include(":domain:favorites")
include(":feature:breedDetails")
include(":core:testing")
