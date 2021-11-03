package io.github.orioncraftmc.orion.version.v1_5_2.backport.skins.ducks;

import io.github.orioncraftmc.orion.version.v1_5_2.backport.skins.OrionModelPlayer;
import net.minecraft.entity.player.EntityPlayer;

public interface RenderPlayerDuck {
	OrionModelPlayer getPlayerModelToRender(EntityPlayer entityPlayer);
}
