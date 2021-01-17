val pluginId by extra("io.github.chaozhang.rna")

plugins {
    kotlin("jvm") version "1.4.21" apply false
    id("com.gradle.plugin-publish") version "0.11.0" apply false
    id("com.github.gmazzo.buildconfig") version "2.0.2" apply false
}

subprojects {
    repositories {
        jcenter()
    }
}

allprojects {
    group = "io.github.chao2zhang"
    version = "0.0.1"
}
