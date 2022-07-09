package io.github.orioncraftmc.orion.version.v1_5_2.backport.ping;

import io.github.orioncraftmc.orion.utils.pinger.model.ServerPingResult;
import org.jetbrains.annotations.Nullable;

public interface ModernPingAwareServerData {

	@Nullable ServerPingResult getModernPingResult();

	void setModernPingResult(@Nullable ServerPingResult result);

}
