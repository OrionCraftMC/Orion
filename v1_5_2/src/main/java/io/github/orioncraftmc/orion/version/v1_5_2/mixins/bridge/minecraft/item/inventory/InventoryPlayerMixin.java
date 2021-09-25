package io.github.orioncraftmc.orion.version.v1_5_2.mixins.bridge.minecraft.item.inventory;

import java.util.Arrays;

import io.github.orioncraftmc.orion.api.bridge.minecraft.item.ItemStackBridge;
import io.github.orioncraftmc.orion.api.bridge.minecraft.item.inventory.PlayerInventoryBridge;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Mixin(InventoryPlayer.class)
public abstract class InventoryPlayerMixin implements PlayerInventoryBridge, IInventory {
	@Shadow
	public abstract String getInvName();

	@Shadow
	public ItemStack[] armorInventory;

	@Shadow
	public int currentItem;

	@Shadow
	public abstract ItemStack getCurrentItem();

	@Shadow
	public abstract boolean hasItem(int i);

	@Shadow
	public abstract int getFirstEmptyStack();

	@Shadow
	public ItemStack itemStack;

	@Shadow
	public ItemStack[] mainInventory;

	@Override
	public int getInventorySize() {
		return this.getSizeInventory();
	}

	@NotNull
	@Override
	public String getName() {
		return this.getInvName();
	}

	@NotNull
	@Override
	public ItemStackBridge getStack(int i) {
		return (ItemStackBridge) (Object) this.getStackInSlot(i);
	}

	// TODO: is this the best way????
	@NotNull
	@Override
	public ItemStackBridge[] getArmorInventory() {
		return Arrays.stream(this.armorInventory).map(ItemStackBridge.class::cast).toArray(ItemStackBridge[]::new);
	}

	@Nullable
	@Override
	public ItemStackBridge getCurrentHeldItem() {
		return (ItemStackBridge) (Object) this.getCurrentItem();
	}

	@Override
	public int getCurrentItemIndex() {
		return this.currentItem;
	}

	@Override
	public int getFirstEmptyStackSlot() {
		return this.getFirstEmptyStack();
	}

	// TODO: is this the best way????
	@NotNull
	@Override
	public ItemStackBridge[] getMainInventory() {
		return Arrays.stream(this.mainInventory).map(ItemStackBridge.class::cast).toArray(ItemStackBridge[]::new);
	}

	@Nullable
	@Override
	public ItemStackBridge getMouseHeldStack() {
		return (ItemStackBridge) (Object) this.itemStack;
	}

	@Override
	public boolean hasItemById(int i) {
		return this.hasItem(i);
	}
}
