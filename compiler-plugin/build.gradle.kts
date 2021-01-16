plugins {
    kotlin("jvm")
    kotlin("kapt")
    id("com.github.gmazzo.buildconfig")
}

dependencies {
    compileOnly("org.jetbrains.kotlin:kotlin-compiler-embeddable")
    kapt("com.google.auto.service:auto-service:1.0-rc7")
    compileOnly("com.google.auto.service:auto-service-annotations:1.0-rc7")
}

buildConfig {
    packageName(group.toString())
    buildConfigField("String", "KOTLIN_PLUGIN_ID", "\"${rootProject.extra["pluginId"]}\"")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}