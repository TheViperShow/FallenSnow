package studio.thevipershow.fallensnow.config.general;

import org.jetbrains.annotations.NotNull;
import studio.thevipershow.fallensnow.FallenSnow;
import studio.thevipershow.fallensnow.config.AbstractTomlConfig;

public final class GeneralTomlConfig extends AbstractTomlConfig<GeneralValues> {

    public GeneralTomlConfig(@NotNull FallenSnow fallenSnow) {
        super("general-settings.toml", fallenSnow, GeneralValues.class);
    }

}
