package studio.thevipershow.fallensnow.config;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface ConfigHolder {

    /**
     * Get the class of the config type.
     * @return The config class.
     */
    @NotNull
    Class<? extends AbstractTomlConfig<?>> getTomlConfigClass();
}
