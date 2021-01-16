pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
    }
}

rootProject.name = "explicit-param"

include(":annotation")
include(":compiler-plugin")
include(":gradle-plugin")
include(":sample")