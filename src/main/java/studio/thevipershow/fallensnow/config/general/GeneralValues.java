package studio.thevipershow.fallensnow.config.general;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import studio.thevipershow.fallensnow.config.SectionKey;
import studio.thevipershow.fallensnow.config.SectionReturnType;

@RequiredArgsConstructor
public enum GeneralValues implements SectionKey, SectionReturnType {

    CONFIG_VERSION("config.version", Long.class),
    TELEMETRY_ENABLED("telemetry.enabled", Boolean.class);

    private final String key;
    private final Class<?> returnType;

    /**
     * Get the key of the section.
     *
     * @return The key.
     */
    @Override
    public final @NotNull String getKey() {
        return key;
    }

    /**
     * Get the return type of this section.
     *
     * @return The return type.
     */
    @Override
    public final @NotNull Class<?> getReturnType() {
        return returnType;
    }
}
