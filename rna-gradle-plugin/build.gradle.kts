plugins {
    id("java-gradle-plugin")
    kotlin("jvm")
    id("com.github.gmazzo.buildconfig")
    id("com.vanniktech.maven.publish")
    id("io.gitlab.arturbosch.detekt")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("gradle-plugin-api"))
}

val pluginId: String by rootProject.extra

buildConfig {
    val project = project(":rna-compiler-plugin")
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
            implementationClass = "io.github.chao2zhang.RnaGradlePlugin"
        }
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
