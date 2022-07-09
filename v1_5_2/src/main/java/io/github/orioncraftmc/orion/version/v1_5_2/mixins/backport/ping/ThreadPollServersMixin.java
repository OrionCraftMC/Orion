package io.github.orioncraftmc.orion.version.v1_5_2.mixins.backport.ping;

import io.github.orioncraftmc.orion.utils.pinger.ModernServerPinger;
import io.github.orioncraftmc.orion.utils.pinger.model.ServerPingResult;
import io.github.orioncraftmc.orion.version.v1_5_2.backport.ping.ModernPingAwareServerData;
import static io.github.orioncraftmc.orion.version.v1_5_2.backport.ping.ModernPingConstants.MODERN_PING_MOTD;
import net.minecraft.ThreadPollServers;
import net.minecraft.client.multiplayer.ServerAddress;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.util.EnumChatFormatting;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ThreadPollServers.class)
public class ThreadPollServersMixin {
	@Shadow
	@Final
	public ServerData pollServersServerData;

	@Inject(method = "run", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiMultiplayer;func_82291_a(Lnet/minecraft/client/multiplayer/ServerData;)V"), cancellable = true)
	private void onRun(CallbackInfo ci) {
		try {
			if (pollServersServerData instanceof ModernPingAwareServerData) {
				ServerAddress address = ServerAddress.fromString(pollServersServerData.serverIP);
				ServerPingResult result = ModernServerPinger.INSTANCE.ping(address.getIP(), address.getPort());

				if (result != null) {
					((ModernPingAwareServerData) pollServersServerData).setModernPingResult(result);

					pollServersServerData.populationInfo = EnumChatFormatting.GRAY.toString() + result.getPlayers()
							.getOnline() + "/" + result.getPlayers().getMax();

					pollServersServerData./* protocolVersion */field_82821_f = result.getVersion().getProtocol() + 1000;
					pollServersServerData.pingToServer = result.getTime();
					pollServersServerData.pingToServer = result.getTime();
					pollServersServerData.serverMOTD = MODERN_PING_MOTD;
					ci.cancel();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
