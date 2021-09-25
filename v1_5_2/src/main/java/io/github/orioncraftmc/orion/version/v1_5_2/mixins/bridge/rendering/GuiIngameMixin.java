package io.github.orioncraftmc.orion.version.v1_5_2.mixins.bridge.rendering;

import io.github.orioncraftmc.orion.api.bridge.rendering.gui.GuiIngameBridge;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.client.gui.GuiIngame;

@Mixin(GuiIngame.class)
public abstract class GuiIngameMixin implements GuiIngameBridge {
}
