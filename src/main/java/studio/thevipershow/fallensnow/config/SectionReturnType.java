package studio.thevipershow.fallensnow.config;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface SectionReturnType {

    /**
     * Get the return type of this section.
     * @return The return type.
     */
    @NotNull
    Class<?> getReturnType();
}
