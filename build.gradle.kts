val pluginId by extra("io.github.chao2zhang.rna")

plugins {
    kotlin("jvm") version "1.5.31" apply false
    id("com.vanniktech.maven.publish") version "0.18.0" apply false
    id("org.jetbrains.dokka") version "1.5.31" apply false
    id("com.github.gmazzo.buildconfig") version "2.0.2" apply false
    id("io.gitlab.arturbosch.detekt") version "1.18.1" apply false
}

repositories {
    mavenLocal()
}

subprojects {
    repositories {
        jcenter()
    }
}

allprojects {
    group = "io.github.chao2zhang"
    version = "0.3.0"
}
