plugins {
    kotlin("jvm")
    // id("org.jlleitschuh.gradle.ktlint")
}

group = "kotline-coroutines"
version = "1.0.0"

repositories {
    mavenCentral()
}

apply {
    plugin("idea")
    plugin("kotlin")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-core
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")
    implementation("ch.qos.logback:logback-classic:1.4.14")
}

tasks.test {
    useJUnitPlatform()
}
