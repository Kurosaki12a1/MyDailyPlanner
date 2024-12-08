rootProject.name = "MyDailyPlanner"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

include(":app")
include(":shared")
include(":features")
include(":features:home")
include(":features:settings")
include(":features:editor")
include(":features:home:api")
include(":features:home:impl")
include(":features:settings:api")
include(":features:settings:impl")
include(":features:editor:impl")
include(":features:editor:api")
include(":shared:domain")
include(":shared:data")
include(":shared:presentation")
include(":shared:utils")
