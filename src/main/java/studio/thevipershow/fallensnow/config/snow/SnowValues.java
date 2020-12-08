package studio.thevipershow.fallensnow.config.snow;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.tomlj.TomlArray;
import studio.thevipershow.fallensnow.config.SectionKey;
import studio.thevipershow.fallensnow.config.SectionReturnType;

@RequiredArgsConstructor
public enum SnowValues implements SectionKey, SectionReturnType {

    SNOW_PARTICLE("snow.particle", String.class),
    SNOW_RANGE("snow.range", Double.class),
    SNOW_SPEED("snow.speed", Double.class),
    SNOW_AMOUNT("snow.amount", Long.class),
    SNOW_SPAWNRATE("snow.spawnrate", Double.class),
    SNOW_WHEN_RAINING("snow.mechanics.snow-when-raining", Boolean.class),
    SNOW_ONLY_OUTSIDE("snow.mechanics.snow-only-outside", Boolean.class),
    SPECIFY_ENABLED_WORLDS("snow.mechanics.worlds.specify-enabled-worlds", Boolean.class),
    ENABLED_WORLDS("snow.mechanics.worlds.enabled-worlds", TomlArray.class),
    SNOW_ABOVE_Y("snow.mechanics.underground.snow-above-y", Boolean.class),
    Y_LEVEL("snow.mechanics.underground.y-level", Long.class)
    ;

    private final String key;
    private final Class<?> returnType;

    /**
     * Get the key of the section.
     *
     * @return The key.
     */
    @Override
    public @NotNull String getKey() {
        return this.key;
    }

    /**
     * Get the return type of this section.
     *
     * @return The return type.
     */
    @Override
    public @NotNull Class<?> getReturnType() {
        return this.returnType;
    }
}
