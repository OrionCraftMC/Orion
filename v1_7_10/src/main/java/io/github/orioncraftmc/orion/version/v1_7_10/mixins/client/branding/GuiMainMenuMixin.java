package io.github.orioncraftmc.orion.version.v1_7_10.mixins.client.branding;

import io.github.orioncraftmc.orion.api.gui.screens.impl.MainMenuScreen;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GuiMainMenu.class)
public abstract class GuiMainMenuMixin extends GuiScreen {

	MainMenuScreen orionMainMenu = new MainMenuScreen() {
		@Override
		public void renderSkybox(int i, int i1, float v) {
			GuiMainMenuMixin.this.renderSkybox(i, i1, v);
		}
	};

	@Redirect(method = "<clinit>", at = @At(value = "NEW", target = "net/minecraft/util/ResourceLocation"))
	private static ResourceLocation onVanillaPanoramaCreation(String resource) {
		if (resource.startsWith("textures/gui/title/background/")) {
			return new ResourceLocation("orion", "panorama/" + resource.substring(30));
		}
		return new ResourceLocation(resource);
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
