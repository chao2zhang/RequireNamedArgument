pluginManagement {
    plugins {
        repositories {
            gradlePluginPortal()
        }
    }
}

rootProject.name = "explicit-param"

include(":annotation")
include(":compiler-plugin")
include(":gradle-plugin")
include(":sample")