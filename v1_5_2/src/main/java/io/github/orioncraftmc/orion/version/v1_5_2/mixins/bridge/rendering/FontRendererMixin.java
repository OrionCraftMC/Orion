package io.github.orioncraftmc.orion.version.v1_5_2.mixins.bridge.rendering;

import io.github.orioncraftmc.orion.api.bridge.rendering.FontRendererBridge;
import net.minecraft.client.gui.FontRenderer;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(FontRenderer.class)
@Implements(@Interface(iface = FontRendererBridge.class, prefix = "bridge$"))
public abstract class FontRendererMixin {
	@Shadow
	public int FONT_HEIGHT;

	@Shadow
	public abstract int drawString(String par1Str, int par2, int par3, int par4, boolean par5);

	public void bridge$drawString(@NotNull String s, int i, int i1, int i2, boolean b) {
		drawString(s, i, i1, i2, b);
	}

	public int bridge$getFontHeight() {
		return FONT_HEIGHT;
	}
}
