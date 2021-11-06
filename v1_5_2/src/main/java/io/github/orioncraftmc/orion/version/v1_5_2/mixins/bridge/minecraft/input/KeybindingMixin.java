package io.github.orioncraftmc.orion.version.v1_5_2.mixins.bridge.minecraft.input;

import io.github.orioncraftmc.orion.api.bridge.minecraft.input.VanillaKeybindingBridge;
import io.github.orioncraftmc.orion.utils.KeyBindingCategoryConstants;
import net.minecraft.client.settings.KeyBinding;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyBinding.class)
public class KeybindingMixin implements VanillaKeybindingBridge {
	@Shadow
	public int keyCode;

	@Shadow
	public String keyDescription;

	@Shadow
	public boolean pressed;
	private int defaultKeyCode;

	@Inject(method = "<init>", at = @At("TAIL"))
	void onConstruction(String string, int i, CallbackInfo ci) {
		defaultKeyCode = i;
	}

	@NotNull
	@Override
	public String getCategory() {
		return KeyBindingCategoryConstants.getKeyCategory(getDescription());
	}

	@Override
	public int getDefaultKeyCode() {
		return defaultKeyCode;
	}

	@NotNull
	@Override
	public String getDescription() {
		return keyDescription;
	}

	@Override
	public int getKeyCode() {
		return keyCode;
	}

	@Override
	public void setKeyCode(int i) {
		keyCode = i;
	}

	@Override
	public boolean bridge$isPressed() {
		return pressed;
	}

	@Override
	public void bridge$setPressed(boolean b) {
		pressed = b;
	}
}
