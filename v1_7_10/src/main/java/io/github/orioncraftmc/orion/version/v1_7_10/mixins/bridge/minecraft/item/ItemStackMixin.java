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
