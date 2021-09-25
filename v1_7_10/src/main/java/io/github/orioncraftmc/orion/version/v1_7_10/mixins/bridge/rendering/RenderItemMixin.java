/*
 * Copyright (C) 2021 OrionCraftMC
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package io.github.orioncraftmc.orion.version.v1_7_10.mixins.bridge.rendering;

import io.github.orioncraftmc.orion.api.bridge.minecraft.item.ItemStackBridge;
import io.github.orioncraftmc.orion.api.bridge.rendering.item.RenderItemBridge;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.item.ItemStack;

@Mixin(RenderItem.class)
public abstract class RenderItemMixin implements RenderItemBridge {
	@Shadow
	public abstract void renderItemAndEffectIntoGUI(FontRenderer fontRenderer, TextureManager textureManager, ItemStack itemStack, int i, int j);

	@Shadow
	public abstract void renderItemOverlayIntoGUI(FontRenderer fontRenderer, TextureManager textureManager, ItemStack itemStack, int i, int j);

	@Override
	public void renderItemAndEffectIntoGui(@NotNull ItemStackBridge itemStackBridge, int i, int i1) {
		this.renderItemAndEffectIntoGUI(Minecraft.getMinecraft().fontRendererObj, Minecraft.getMinecraft().getTextureManager(), (ItemStack) (Object) itemStackBridge, i, i1);
	}

	@Override
	public void renderItemOverlayIntoGui(@NotNull ItemStackBridge itemStackBridge, int i, int i1) {
		this.renderItemOverlayIntoGUI(Minecraft.getMinecraft().fontRendererObj, Minecraft.getMinecraft().getTextureManager(), (ItemStack) (Object) itemStackBridge, i, i1);
	}
}
