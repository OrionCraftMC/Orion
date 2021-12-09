/*
 * Copyright (C) 2021 OrionCraftMC
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package io.github.orioncraftmc.orion.version.v1_5_2.mixins.fixes.snooper;

import net.minecraft.profiler.PlayerUsageSnooper;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerUsageSnooper.class)
public class PlayerUsageSnooperMixin {
	@Shadow
	public boolean isRunning;

	@Inject(method = "startSnooper", at = @At("HEAD"), cancellable = true)
	private void onStartSnooper(CallbackInfo ci) {
		isRunning = true;
		ci.cancel();
	}

	/**
	 * NO-OP this call
	 *
	 * @author OrionCraftMc
	 */
	@Overwrite
	public void addBaseDataToSnooper() {
	}

	/**
	 * NO-OP this call
	 *
	 * @author OrionCraftMc
	 */
	@Overwrite
	public void addJvmArgsToSnooper() {
	}

	/**
	 * NO-OP this call
	 *
	 * @author OrionCraftMc
	 */
	@Overwrite
	public void addMemoryStatsToSnooper() {
	}

	/**
	 * NO-OP this call
	 *
	 * @author OrionCraftMc
	 */
	@Overwrite
	public void addData(String string, Object object) {
	}
}
