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
include(":features:overview")
include(":features:analytics")
include(":features:categories")
include(":features:home:data")
include(":features:home:domain")
include(":features:home:presentation")
include(":features:overview:data")
include(":features:overview:domain")
include(":features:overview:presentation")
include(":features:categories:data")
include(":features:categories:domain")
include(":features:categories:presentation")
include(":features:settings:domain")
include(":features:settings:data")
include(":features:settings:presentation")
include(":features:analytics:data")
include(":features:analytics:domain")
include(":features:analytics:presentation")
include(":shared:domain")
include(":shared:data")
include(":shared:presentation")
include(":shared:utils")


