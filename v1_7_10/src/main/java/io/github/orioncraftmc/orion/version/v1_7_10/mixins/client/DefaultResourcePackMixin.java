package io.github.orioncraftmc.orion.version.v1_7_10.mixins.client;

import com.google.common.collect.ImmutableSet;
import net.minecraft.client.resources.DefaultResourcePack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DefaultResourcePack.class)
public class DefaultResourcePackMixin {

	@Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableSet;of(Ljava/lang/Object;Ljava/lang/Object;)Lcom/google/common/collect/ImmutableSet;"))
	private static <E> ImmutableSet<E> onSetDefaultResourcePackDomains(E e1, E e2) {
		// Include orion resources in the default resource pack.
		// TODO: Introduce custom resource pack entry in the future
		//noinspection unchecked
		return (ImmutableSet<E>) ImmutableSet.of(e1, e2, "orion");
	}
}
