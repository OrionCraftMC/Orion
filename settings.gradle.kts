pluginManagement {
    repositories {
        maven("https://raw.githubusercontent.com/NickAcPT/LightCraftMaven/main/")
        mavenCentral()
        maven("https://jitpack.io/")
        maven("https://maven.fabricmc.net/")
        maven("https://maven.quiltmc.org/repository/release/")
        gradlePluginPortal()
    }
}

rootProject.name = "Orion"
include("v1_5_2")
