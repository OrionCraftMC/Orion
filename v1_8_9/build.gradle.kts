import io.github.nickacpt.lightcraft.gradle.minecraft.ClientVersion

plugins {
    id("io.github.nickacpt.lightcraft.gradle")
}

lightcraft {
    clientVersion = ClientVersion.V1_8_9
    provideOptifineJarMod = true

    launch {
        enableMixinsDebug = true
    }
}
