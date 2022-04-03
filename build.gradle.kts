plugins {
    id("org.jetbrains.kotlin.jvm") version "1.6.0" apply false
    id("org.cadixdev.licenser") version "0.6.1" apply false
    id("io.github.nickacpt.lightcraft.gradle") version "1.3.3-SNAPSHOT" apply false

    // Apply the java-library plugin for API and implementation separation.
    `java-library`
}

allprojects {
    group = "io.github.orioncraftmc.orion"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenLocal()
        mavenCentral()
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
        maven("https://raw.githubusercontent.com/NickAcPT/LightCraftMaven/main/")
    }

    apply(plugin = "org.cadixdev.licenser")
    apply(plugin = "java-library-distribution")

    configure<org.cadixdev.gradle.licenser.LicenseExtension> {
        header(rootProject.file("HEADER"))
    }

    if (this != rootProject) {
        apply(plugin = "org.jetbrains.kotlin.jvm")

        dependencies {
            "implementation"("io.github.orioncraftmc.orion:OrionCraft")
        }
    }
}
