plugins {
    kotlin("jvm")
    id("com.chao2zhang.explicit-param") version "0.0.1"
}

repositories {
    mavenLocal()
}

dependencies {
    implementation(project(":annotation"))
}