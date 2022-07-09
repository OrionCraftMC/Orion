package io.github.orioncraftmc.orion.version.v1_5_2.mixins.backport.ping;

import io.github.orioncraftmc.orion.api.utils.rendering.adventure.AdventureComponentRenderer;
import io.github.orioncraftmc.orion.utils.pinger.model.ServerPingResult;
import io.github.orioncraftmc.orion.version.v1_5_2.backport.GuiSlotListSizeProvider;
import io.github.orioncraftmc.orion.version.v1_5_2.backport.ping.ModernPingAwareServerData;
import static io.github.orioncraftmc.orion.version.v1_5_2.backport.ping.ModernPingConstants.MODERN_PING_MOTD;
import java.util.Objects;
import net.kyori.adventure.text.Component;
import net.minecraft.client.gui.*;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.renderer.Tessellator;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(GuiSlotServer.class)
public class GuiSlotServerMixin implements GuiSlotListSizeProvider {
	@Override
	public int getListWidth() {
		return 220 + 45;
	}

	private ServerData currentServerBeingRendered;

	@Inject(method = "func_77247_d", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiMultiplayer;drawString(Lnet/minecraft/client/gui/FontRenderer;Ljava/lang/String;III)V", ordinal = 0), locals = LocalCapture.CAPTURE_FAILHARD)
	public void onRenderServer(int j, int k, int l, int tessellator, Tessellator par5, CallbackInfo ci, ServerData var6) {
		currentServerBeingRendered = var6;
	}

	@Redirect(method = "func_77247_d", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiMultiplayer;drawString(Lnet/minecraft/client/gui/FontRenderer;Ljava/lang/String;III)V"))
	public void onRenderServerMotd(GuiMultiplayer instance, FontRenderer renderer, String string, int x, int y, int color) {
		if (!(currentServerBeingRendered instanceof ModernPingAwareServerData mpaData) || mpaData.getModernPingResult() == null) {
			instance.drawString(renderer, string, x, y, color);
			return;
		}

		if (Objects.equals(string, currentServerBeingRendered.serverName)) {
			renderer.drawString(string, x, y, color);
		} else if (Objects.equals(string, currentServerBeingRendered.populationInfo)) {
			renderer.drawString(string, x - 12, y - 11, color);
		} else if (Objects.equals(string, MODERN_PING_MOTD)) {
			Component motd = mpaData.getModernPingResult().getMotd();

			AdventureComponentRenderer.INSTANCE.drawString(motd, x, y, false);
		}
	}

	@ModifyConstant(method = "func_77247_d", constant = @Constant(intValue = 205))
	public int onComputeOffsetConstant1(int original) {
		return getListWidth() - 15;
	}

	@ModifyConstant(method = "func_77247_d", constant = @Constant(intValue = 215))
	public int onComputeOffsetConstant2(int original) {
		return getListWidth() - 5;
	}

	@ModifyConstant(method = "func_77247_d", constant = @Constant(intValue = 200))
	public int onComputeOffsetConstant3(int original) {
		return getListWidth() - 20;
	}
}
