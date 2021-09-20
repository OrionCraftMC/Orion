package io.github.orioncraftmc.orion.version.v1_5_2.mixins.fixes.settings;

import java.io.File;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.optifine.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Minecraft.class)
public class MinecraftMixin {

	@Shadow
	public GameSettings gameSettings;

	@Redirect(method = "startGame", at = @At(value = "NEW",
			target = "net/minecraft/client/settings/GameSettings"))
	public GameSettings onNewGameSettings(Minecraft par1Minecraft, File par2File) {
		if (gameSettings != null) {
			gameSettings.mc = par1Minecraft;
			Config.setGameSettings(gameSettings);
			return gameSettings;
		}
		return new GameSettings(par1Minecraft, par2File);
	}

}
