package io.github.orioncraftmc.orion.version.v1_7_10.mixins.bridge.minecraft;

import io.github.orioncraftmc.orion.api.bridge.minecraft.GameSettingsBridge;
import net.minecraft.client.settings.GameSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GameSettings.class)
public class GameSettingsMixin implements GameSettingsBridge {
	@Shadow
	public float gammaSetting;

	@Override
	public float getGammaValue() {
		return gammaSetting;
	}

	@Override
	public void setGammaValue(float v) {
		gammaSetting = v;
	}
}
