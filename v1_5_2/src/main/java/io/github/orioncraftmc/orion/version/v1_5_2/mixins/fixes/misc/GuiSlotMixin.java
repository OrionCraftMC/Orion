package io.github.orioncraftmc.orion.version.v1_5_2.mixins.fixes.misc;

import io.github.orioncraftmc.orion.version.v1_5_2.backport.GuiSlotListSizeProvider;
import net.minecraft.client.gui.GuiSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiSlot.class)
public class GuiSlotMixin {

	@ModifyConstant(method = {"drawScreen"}, constant = @Constant(intValue = 92))
	private int getHalfListWidth(int original) {
		if (this instanceof GuiSlotListSizeProvider) {
			return ((GuiSlotListSizeProvider) this).getListWidth() / 2;
		}
		return original;
	}
	@ModifyConstant(method = {"drawScreen"}, constant = @Constant(intValue = 16))
	private int getOffset(int original) {
		if (this instanceof GuiSlotListSizeProvider) {
			return -2; // +2 but we have to invert because the operation is subtraction
		}
		return original;
	}

	@ModifyConstant(method = {"getSlotIndexFromScreenCoords", "drawScreen"}, constant = @Constant(intValue = 110))
	private int onGetHalfListWidth(int original) {
		if (this instanceof GuiSlotListSizeProvider) {
			return ((GuiSlotListSizeProvider) this).getListWidth() / 2;
		}
		return original;
	}

	@ModifyConstant(method = {"getScrollBarX"}, constant = @Constant(intValue = 124))
	private int onGetScrollBarX(int original) {
		if (this instanceof GuiSlotListSizeProvider) {
			return ((GuiSlotListSizeProvider) this).getListWidth() / 2 + 12;
		}
		return original;
	}
}
