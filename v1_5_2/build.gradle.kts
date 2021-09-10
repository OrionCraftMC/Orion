import io.github.nickacpt.lightcraft.gradle.minecraft.ClientVersion

plugins {
    id("io.github.nickacpt.lightcraft.gradle")
}

lightcraft {
    clientVersion = ClientVersion.V1_5_2
    provideOptifineJarMod = true
}
