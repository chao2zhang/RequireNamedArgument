val pluginId by extra("com.chao2zhang.explicit-param")

plugins {
    kotlin("jvm") version "1.4.21" apply false
    id("com.gradle.plugin-publish") version "0.11.0" apply false
    id("com.github.gmazzo.buildconfig") version "2.0.2" apply false
    id("maven-publish")
}

subprojects {
    repositories {
        jcenter()
    }
}

allprojects {
    group = "com.chao2zhang.explicitparam"
    version = "0.0.1"
}
