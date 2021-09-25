package io.github.orioncraftmc.orion.version.v1_5_2.mixins.bridge.rendering;

import io.github.orioncraftmc.orion.api.bridge.minecraft.item.ItemStackBridge;
import io.github.orioncraftmc.orion.api.bridge.rendering.item.RenderItemBridge;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.render.RenderEngine;
import net.minecraft.client.render.RenderItem;
import net.minecraft.item.ItemStack;

@Mixin(RenderItem.class)
public abstract class RenderItemMixin implements RenderItemBridge {
	@Shadow
	public abstract void renderItemAndEffectIntoGUI(FontRenderer fontRenderer, RenderEngine renderEngine, ItemStack itemStack, int i, int j);

	@Shadow
	public abstract void renderItemOverlayIntoGUI(FontRenderer fontRenderer, RenderEngine renderEngine, ItemStack itemStack, int i, int j);

	@Override
	public void renderItemAndEffectIntoGui(@NotNull ItemStackBridge itemStackBridge, int i, int i1) {
		this.renderItemAndEffectIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().renderEngine,(ItemStack) (Object) itemStackBridge, i, i1);
	}

	@Override
	public void renderItemOverlayIntoGui(@NotNull ItemStackBridge itemStackBridge, int i, int i1) {
		this.renderItemOverlayIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().renderEngine, (ItemStack) (Object) itemStackBridge, i, i1);
	}
}
