plugins {
    kotlin("jvm") version "1.4.21"
    id("io.github.chaozhang.rna") version "0.1.0"
}

repositories {
    mavenLocal()
    jcenter()
}

dependencies {
    implementation("io.github.chao2zhang:rna-annotation:0.1.0")
}