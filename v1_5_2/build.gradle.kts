import io.github.nickacpt.lightcraft.gradle.minecraft.ClientVersion

plugins {
    id("io.github.nickacpt.lightcraft.gradle")
}

lightcraft {
    clientVersion = ClientVersion.V1_5_2
    provideOptifineJarMod = true

    launch {
        enableMixinsDebug = true
        playerName = "NickAc"
    }
}

tasks.withType(JavaExec::class.java).forEach {
    it.dependsOn(gradle.includedBuild("OrionCraft").task("build"))
}
