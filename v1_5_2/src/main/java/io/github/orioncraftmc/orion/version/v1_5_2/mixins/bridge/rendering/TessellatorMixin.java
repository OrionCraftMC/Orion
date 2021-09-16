package io.github.orioncraftmc.orion.version.v1_5_2.mixins.bridge.rendering;

import io.github.orioncraftmc.orion.api.bridge.rendering.TessellatorBridge;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Tessellator.class)
@Implements(@Interface(iface = TessellatorBridge.class, prefix = "bridge$"))
public abstract class TessellatorMixin {

	@Shadow
	public abstract int draw();

	@Shadow
	public abstract void setColorRGBA(int par1, int par2, int par3, int par4);

	@Shadow
	public abstract void startDrawing(int par1);

	public void bridge$draw() {
		draw();
	}

	public void bridge$setColor(int i, int i1, int i2, int i3) {
		setColorRGBA(i, i1, i2, i3);
	}

	public void bridge$startDrawingTriangleFan() {
		startDrawing(GL11.GL_TRIANGLE_FAN);
	}

	public void bridge$startDrawingLineLoop() {
		startDrawing(GL11.GL_LINE_LOOP);
	}

	public void bridge$startDrawingLineStrip() {
		startDrawing(GL11.GL_LINE_STRIP);
	}
}
