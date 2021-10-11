package io.github.orioncraftmc.orion.version.v1_8_9.mixins.fixes.guava;

import com.google.common.collect.Iterators;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.util.ClassInheritanceMultiMap;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ClassInheritanceMultiMap.class)
public abstract class ClassInheritanceMultiMapMixin {
	@Shadow
	@Final
	public Map<Class<?>, List<?>> map;

	@Shadow
	public abstract Class<?> initializeClassLookup(Class<?> class_);

	/**
	 * @reason Inner class
	 * @author NickAcPT
	 */
	@Overwrite
	public <S> Iterable<S> getByClass(Class<S> class_) {
		return () -> {
			List<?> list = map.get(
					initializeClassLookup(class_));
			if (list == null) {
				return Iterators.forArray();
			} else {
				Iterator<?> iterator = list.iterator();
				return Iterators.filter(iterator, class_);
			}
		};
	}

	@Redirect(method = "iterator",at = @At(value = "INVOKE", target = "Lcom/google/common/collect/Iterators;emptyIterator()Lcom/google/common/collect/UnmodifiableIterator;"))
	public UnmodifiableIterator<?> onEmptyIterator() {
		return Iterators.forArray();
	}

}
