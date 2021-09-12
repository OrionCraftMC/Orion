package io.github.orioncraftmc.orion.version.v1_7_10.mixins.bridge.minecraft;

import io.github.orioncraftmc.orion.api.bridge.minecraft.ScaledResolutionBridge;
import net.minecraft.client.gui.ScaledResolution;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ScaledResolution.class)
@Implements(@Interface(iface = ScaledResolutionBridge.class, prefix = "bridge$"))
public class ScaledResolutionMixin {
	@Shadow
	public int scaleFactor;

	@Shadow
	public int scaledHeight;

	@Shadow
	public int scaledWidth;

	@Shadow
	public double scaledHeightD;

	@Shadow
	public double scaledWidthD;

	public int bridge$getScaleFactor() {
		return scaleFactor;
	}

	public int bridge$getScaledHeight() {
		return scaledHeight;
	}

	public int bridge$getScaledWidth() {
		return scaledWidth;
	}

	public float bridge$getScaledHeightFloat() {
		return (float) scaledHeightD;
	}

	public float bridge$getScaledWidthFloat() {
		return (float) scaledWidthD;
	}
}
