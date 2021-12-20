package io.github.orioncraftmc.orion.version.v1_8_9.mixins.client;

import com.google.common.collect.ImmutableSet;
import net.minecraft.client.resources.DefaultResourcePack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(DefaultResourcePack.class)
public class DefaultResourcePackMixin {

	@Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableSet;of(Ljava/lang/Object;Ljava/lang/Object;)Lcom/google/common/collect/ImmutableSet;"))
	private static <E> ImmutableSet<E> onCreateResourcePackDomains(E e1, E e2) {
		return (ImmutableSet<E>) ImmutableSet.of(e1, e2, "orion");
	}
}
