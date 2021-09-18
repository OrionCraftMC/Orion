package io.github.orioncraftmc.orion.version.v1_7_10.bridge.rendering;

import io.github.orioncraftmc.orion.api.bridge.rendering.OpenGlBridge;
import org.lwjgl.opengl.GL11;

public class OpenGlBridgeImpl implements OpenGlBridge {
	@Override
	public void disableBlend() {
		GL11.glDisable(GL11.GL_BLEND);
	}

	@Override
	public void disableTexture2D() {
		GL11.glDisable(GL11.GL_TEXTURE_2D);
	}

	@Override
	public void enableBlend() {
		GL11.glEnable(GL11.GL_BLEND);
	}

	@Override
	public void enableBlendAlphaMinusSrcAlpha() {
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	}

	@Override
	public void enableTexture2D() {
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}

	@Override
	public void popMatrix() {
		GL11.glPopMatrix();
	}

	@Override
	public void pushMatrix() {
		GL11.glPushMatrix();
	}

	@Override
	public void translate(double x, double y, double z) {
		GL11.glTranslated(x, y, z);
	}

	@Override
	public void setLineWidth(float v) {
		GL11.glLineWidth(v);
	}
}
