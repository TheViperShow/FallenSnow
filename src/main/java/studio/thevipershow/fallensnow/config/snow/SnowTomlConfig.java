package studio.thevipershow.fallensnow.config.snow;

import org.jetbrains.annotations.NotNull;
import studio.thevipershow.fallensnow.FallenSnow;
import studio.thevipershow.fallensnow.config.AbstractTomlConfig;

public final class SnowTomlConfig extends AbstractTomlConfig<SnowValues> {

    public SnowTomlConfig(@NotNull FallenSnow fallenSnow) {
        super("snow-settings.toml", fallenSnow, SnowValues.class);
    }
}
