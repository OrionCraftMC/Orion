package io.github.orioncraftmc.orion.version.v1_5_2.mixins.bridge.minecraft.entity;

import io.github.orioncraftmc.orion.api.bridge.minecraft.entity.EntityPlayerBridge;
import io.github.orioncraftmc.orion.api.bridge.minecraft.item.inventory.PlayerInventoryBridge;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

@Mixin(EntityPlayer.class)
public class PlayerEntityMixin implements EntityPlayerBridge {

	@Shadow
	public InventoryPlayer inventory;

	@NotNull
	@Override
	public PlayerInventoryBridge getPlayerInventory() {
		return (PlayerInventoryBridge) this.inventory;
	}
}
