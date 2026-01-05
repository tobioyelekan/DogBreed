pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "DogBreed"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":app")
include(":core:network:api")
include(":core:network:implementation")
include(":core:database:api")
include(":core:database:implementation")
include(":core:model")
include(":core:common")
include(":core:coroutine")
include(":core:designsystem")
include(":feature:allbreeds:ui")
include(":feature:allbreeds:data")
include(":feature:allbreeds:domain")
include(":feature:breedDetails:ui")
include(":feature:breedDetails:data")
include(":feature:breedDetails:domain")
include(":feature:favorites:ui")
include(":feature:favorites:domain")
include(":feature:subbreeds:ui")
include(":feature:subbreeds:data")
include(":feature:subbreeds:domain")
include(":core:testing")
include(":core:testing:ui")
include(":core:testing:integration")
