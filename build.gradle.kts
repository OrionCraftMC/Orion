plugins {
    id("org.jetbrains.kotlin.jvm") version "1.6.0" apply false
    id("org.cadixdev.licenser") version "0.6.1" apply false
    id("io.github.nickacpt.lightcraft.gradle") version "2.1.3-SNAPSHOT" apply false

    // Apply the java-library plugin for API and implementation separation.
    `java-library`
}

allprojects {
    group = "io.github.orioncraftmc.orion"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
        maven("https://raw.githubusercontent.com/NickAcPT/LightCraftMaven/main/")
    }

    apply(plugin = "org.cadixdev.licenser")

    configure<org.cadixdev.gradle.licenser.LicenseExtension> {
        header(rootProject.file("HEADER"))
    }

    if (this != rootProject) {
        apply(plugin = "java-library")
        apply(plugin = "org.jetbrains.kotlin.jvm")

        dependencies {
            "api"("io.github.orioncraftmc.orion:OrionCraft")
        }
    }
}
