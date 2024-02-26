plugins {
    kotlin("jvm") version "1.9.22"
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
    testImplementation(kotlin("test"))

    // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-core
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    // logging
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")
    implementation("ch.qos.logback:logback-classic:1.4.14")
}

tasks.test {
    useJUnitPlatform()
}

// 라이브러리
tasks.register<Jar>("sourcesJar") {
    archiveClassifier.set("sources")
    from(sourceSets["main"].allSource)
}

kotlin {
    jvmToolchain(19)
}
