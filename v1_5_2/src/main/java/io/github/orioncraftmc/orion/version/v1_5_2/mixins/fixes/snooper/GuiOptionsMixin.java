package io.github.orioncraftmc.orion.version.v1_5_2.mixins.fixes.snooper;

import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GuiOptions.class)
public class GuiOptionsMixin {
	@Redirect(method = "initGui", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z"))
	public <E> boolean onAddSnooperButton(List<E> instance, E e) {
		if (e instanceof GuiButton button) {
			if (button.id == 104) {
				return true;
			}
		}
		return instance.add(e);
	}
}
