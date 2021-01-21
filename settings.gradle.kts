rootProject.name = "RequireNamedArgument"

include(":rna-annotation")
include(":rna-compiler-plugin")
include(":rna-gradle-plugin")

pluginManagement {
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "io.gitlab.arturbosch.detekt") {
                useModule("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${requested.version}")
            }
            if (requested.id.id == "io.gitlab.arturbosch.detekt.project") {
                useModule("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${requested.version}")
            }
        }
    }
    repositories {
        mavenLocal()
        gradlePluginPortal()
    }
}
