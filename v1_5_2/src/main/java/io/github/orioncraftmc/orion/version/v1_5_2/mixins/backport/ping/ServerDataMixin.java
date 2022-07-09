package io.github.orioncraftmc.orion.version.v1_5_2.mixins.backport.ping;

import io.github.orioncraftmc.orion.utils.pinger.model.ServerPingResult;
import io.github.orioncraftmc.orion.version.v1_5_2.backport.ping.ModernPingAwareServerData;
import net.minecraft.client.multiplayer.ServerData;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ServerData.class)
public class ServerDataMixin implements ModernPingAwareServerData {
	private ServerPingResult modernPingResult;

	@Nullable
	@Override
	public ServerPingResult getModernPingResult() {
		return modernPingResult;
	}

	@Override
	public void setModernPingResult(@Nullable ServerPingResult result) {
		this.modernPingResult = result;
	}
}
