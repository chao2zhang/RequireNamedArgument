plugins {
    kotlin("jvm") version "1.4.21"
    id("com.chao2zhang.rna") version "0.0.1"
}

repositories {
    mavenLocal()
    jcenter()
}

dependencies {
    implementation("com.chao2zhang.rna:annotation:0.0.1")
}