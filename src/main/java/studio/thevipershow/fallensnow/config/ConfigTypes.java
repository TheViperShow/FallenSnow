package studio.thevipershow.fallensnow.config;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import studio.thevipershow.fallensnow.config.general.GeneralTomlConfig;
import studio.thevipershow.fallensnow.config.snow.SnowTomlConfig;

@RequiredArgsConstructor
public enum ConfigTypes implements ConfigHolder {

    SNOW_CONFIG(SnowTomlConfig.class),
    GENERAL_CONFIG(GeneralTomlConfig.class);

    private final Class<? extends AbstractTomlConfig<?>> tomlConfigClass;

    @Override
    public final @NotNull Class<? extends AbstractTomlConfig<?>> getTomlConfigClass() {
        return tomlConfigClass;
    }
}
