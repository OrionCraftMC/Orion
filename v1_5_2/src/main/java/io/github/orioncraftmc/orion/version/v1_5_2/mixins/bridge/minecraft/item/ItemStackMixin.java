package io.github.orioncraftmc.orion.version.v1_5_2.mixins.bridge.minecraft.item;

import io.github.orioncraftmc.orion.api.bridge.minecraft.item.ItemStackBridge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.item.ItemStack;

@Mixin(ItemStack.class)
public class ItemStackMixin implements ItemStackBridge {
	@Shadow
	public int itemID;

	@Shadow
	public int itemDamage;

	@Shadow
	public int stackSize;

	@Override
	public int getItemId() {
		return this.itemID;
	}

	@Override
	public int getMeta() {
		return this.itemDamage;
	}

	@Override
	public int getStackSize() {
		return this.stackSize;
	}
}
