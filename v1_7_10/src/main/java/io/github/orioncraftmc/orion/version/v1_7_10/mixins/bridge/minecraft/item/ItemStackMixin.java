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

package io.github.orioncraftmc.orion.version.v1_7_10.mixins.bridge.minecraft.item;

import io.github.orioncraftmc.orion.api.bridge.minecraft.item.ItemStackBridge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Mixin(ItemStack.class)
public class ItemStackMixin implements ItemStackBridge {
	@Shadow
	public int stackSize;

	@Shadow
	public Item theItem;

	@Shadow
	public int metadata;

	@Override
	public int getItemId() {
		return Item.getIdFromItem(this.theItem);
	}

	@Override
	public int getMeta() {
		return this.metadata;
	}

	@Override
	public int getStackSize() {
		return this.stackSize;
	}
}
