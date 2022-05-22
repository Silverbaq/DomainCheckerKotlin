import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
    application
}

group = "me.silverbaq"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val exposedVersion: String by project
dependencies {
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-java-time:$exposedVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
    implementation("com.h2database:h2:2.1.212")
    implementation("junit:junit:4.13.2")
    implementation("org.xerial:sqlite-jdbc:3.36.0.3")
    //implementation("joda-time:joda-time:2.10.14")

    testImplementation(kotlin("test"))
    testImplementation("org.mockito:mockito-core:4.5.1")
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
    testImplementation("org.mockito:mockito-inline:4.5.1")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.1")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}