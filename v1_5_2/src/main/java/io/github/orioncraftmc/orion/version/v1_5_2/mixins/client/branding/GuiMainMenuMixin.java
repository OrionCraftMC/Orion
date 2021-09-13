package io.github.orioncraftmc.orion.version.v1_5_2.mixins.client.branding;

import io.github.orioncraftmc.orion.api.gui.screens.impl.MainMenuScreen;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiMainMenu.class)
public abstract class GuiMainMenuMixin extends GuiScreen {

	MainMenuScreen orionMainMenu = new MainMenuScreen() {
		@Override
		public void renderSkybox(int i, int i1, float v) {
			GuiMainMenuMixin.this.renderSkybox(i, i1, v);
		}
	};

	@ModifyConstant(method = "<clinit>")
	private static String onVanillaPanoramaCreation(String original) {
		if (original.startsWith("/title/bg/panorama")) {
			return "/assets/orion/panorama/panorama_" + original.substring(18);
		}
		return original;
	}

	@Shadow
	public abstract void renderSkybox(int mouseX, int mouseY, float renderPartialTicks);

	/**
	 * @author OrionCraftMc
	 */
	@Overwrite
	public void drawScreen(int mouseX, int mouseY, float renderPartialTicks) {
		orionMainMenu.drawScreen(mouseX, mouseY, renderPartialTicks);
	}

	/**
	 * @author OrionCraftMc
	 */
	@Overwrite
	public void mouseClicked(int mouseX, int mouseY, int clickedButtonId) {
		orionMainMenu.handleMouseClick(mouseX, mouseY, clickedButtonId);
	}




}
