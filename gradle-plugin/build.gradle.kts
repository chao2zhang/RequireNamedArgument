plugins {
    id("java-gradle-plugin")
    kotlin("jvm")
    id("com.github.gmazzo.buildconfig")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("gradle-plugin-api"))
}

val pluginId: String by rootProject.extra

buildConfig {
    val project = project(":compiler-plugin")
    packageName(project.group.toString())
    buildConfigField("String", "KOTLIN_PLUGIN_ID", "\"$pluginId\"")
    buildConfigField("String", "KOTLIN_PLUGIN_GROUP", "\"${project.group}\"")
    buildConfigField("String", "KOTLIN_PLUGIN_NAME", "\"${project.name}\"")
    buildConfigField("String", "KOTLIN_PLUGIN_VERSION", "\"${project.version}\"")
}

gradlePlugin {
    plugins {
        create("explicitParamGradlePlugin") {
            id = pluginId
            implementationClass = "com.chao2zhang.explicitparam.ExplicitParamGradlePlugin"
        }
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
