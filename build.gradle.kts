val pluginId by extra("io.github.chao2zhang.rna")

plugins {
    kotlin("jvm") version "1.4.21" apply false
    id("com.vanniktech.maven.publish") version "0.13.0" apply false
    id("org.jetbrains.dokka") version "1.4.20" apply false
    id("com.github.gmazzo.buildconfig") version "2.0.2" apply false
}

subprojects {
    repositories {
        jcenter()
    }
}

allprojects {
    group = "io.github.chao2zhang"
    version = "0.1.0"
}
