import io.github.nickacpt.lightcraft.gradle.minecraft.ClientVersion

plugins {
    id("io.github.nickacpt.lightcraft.gradle") version "1.0-SNAPSHOT"
}

repositories {
    mavenCentral()
}

lightcraft {
    clientVersion = ClientVersion.V1_7_10
    provideOptifineJarMod = true
}
