plugins {
    id("java-gradle-plugin")
    kotlin("jvm")
    id("com.github.gmazzo.buildconfig")
    id("maven-publish")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("gradle-plugin-api"))
}

val pluginId: String by rootProject.extra

buildConfig {
    val project = project(":compiler-plugin")
    packageName(project.group.toString())
    buildConfigField("String", "PLUGIN_ID", "\"$pluginId\"")
    buildConfigField("String", "PLUGIN_GROUP", "\"${project.group}\"")
    buildConfigField("String", "PLUGIN_NAME", "\"${project.name}\"")
    buildConfigField("String", "PLUGIN_VERSION", "\"${project.version}\"")
}

gradlePlugin {
    plugins {
        create("requireNamedArgumentGradlePlugin") {
            id = pluginId
            implementationClass = "com.chao2zhang.rna.RnaGradlePlugin"
        }
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
