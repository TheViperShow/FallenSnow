package studio.thevipershow.fallensnow.animations;

import org.jetbrains.annotations.NotNull;
import studio.thevipershow.fallensnow.FallenSnow;
import studio.thevipershow.fallensnow.config.ConfigTypes;
import studio.thevipershow.fallensnow.config.snow.SnowTomlConfig;
import studio.thevipershow.fallensnow.config.snow.SnowValues;

public final class FallenSnowParticlesTaskManagerAsync extends AbstractAsyncConfigurableParticlesTaskManager<FallenSnow, GlobalSnowAnimation> {

    public FallenSnowParticlesTaskManagerAsync(@NotNull FallenSnow plugin) {
        super(new GlobalSnowAnimation(plugin.getConfigurationManager().getConfig(ConfigTypes.SNOW_CONFIG), plugin), plugin, ((SnowTomlConfig) plugin.getConfigurationManager().getConfig(ConfigTypes.SNOW_CONFIG)).getConfigValue(SnowValues.SNOW_SPEED, Double.class));
    }
}
