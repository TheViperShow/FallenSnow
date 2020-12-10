package studio.thevipershow.fallensnow.config;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface KeyHolder {

    /**
     * Get the key of the section.
     * @return The key.
     */
    @NotNull
    String getKey();
}
