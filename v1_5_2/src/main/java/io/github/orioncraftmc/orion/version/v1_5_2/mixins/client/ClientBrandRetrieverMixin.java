package io.github.orioncraftmc.orion.version.v1_5_2.mixins.client;

import io.github.orioncraftmc.orion.api.OrionCraftConstants;
import net.minecraft.client.ClientBrandRetriever;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ClientBrandRetriever.class)
public class ClientBrandRetrieverMixin {

	/**
	 * @author OrionCraftMC - We change the client brand
	 */
	@Overwrite
	public static String getClientModName() {
		return OrionCraftConstants.INSTANCE.getClientBrand();
	}

}
