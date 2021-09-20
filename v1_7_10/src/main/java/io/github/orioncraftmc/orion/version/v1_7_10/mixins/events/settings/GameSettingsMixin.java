package io.github.orioncraftmc.orion.version.v1_7_10.mixins.events.settings;

import io.github.orioncraftmc.orion.api.event.EventBus;
import io.github.orioncraftmc.orion.api.event.impl.GameSettingsLoadEvent;
import net.minecraft.client.settings.GameSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameSettings.class)
public class GameSettingsMixin {
	@Inject(method = "loadOptions", at = @At("TAIL"))
	public void onLoadOptions(CallbackInfo ci) {
		EventBus.INSTANCE.callEvent(new GameSettingsLoadEvent());
	}
}
