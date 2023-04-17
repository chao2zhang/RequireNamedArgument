plugins {
    kotlin("jvm") version "1.8.20"
    id("io.github.detekt.gradle.compiler-plugin") version "1.23.0-RC1"
}

repositories {
    jcenter()
}

detekt {
    config = files("config/detekt/detekt.yml")
    buildUponDefaultConfig = true
    debug = true
    enableCompilerPlugin.set(true)
    reports {
        xml.enabled = false
        html.enabled = false
        sarif.enabled = false
        txt.enabled = false
    }
}

dependencies {
    implementation("io.github.chao2zhang:rna-annotation:0.2.0")
}