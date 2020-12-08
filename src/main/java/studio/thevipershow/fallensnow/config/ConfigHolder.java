package studio.thevipershow.fallensnow.config;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface ConfigHolder {

    @NotNull
    Class<? extends AbstractTomlConfig<?>> getTomlConfigClass();
}
