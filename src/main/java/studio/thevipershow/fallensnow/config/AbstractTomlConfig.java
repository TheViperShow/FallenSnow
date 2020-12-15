package studio.thevipershow.fallensnow.config;

import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Objects;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.tomlj.Toml;
import org.tomlj.TomlParseResult;
import studio.thevipershow.fallensnow.FallenSnow;

import java.io.File;
import java.util.EnumMap;
import java.util.List;

@Getter
public abstract class AbstractTomlConfig<T extends Enum<T> & KeyHolder & SectionReturnType> {

    protected final File file;
    protected TomlParseResult parsedToml;
    protected final FallenSnow fallenSnow;
    protected final Class<T> enumClass;
    protected final List<T> tValues;
    protected final EnumMap<T, Object> configValues;

    protected void loadAllConfigValues() {
        for (T t : tValues) {
            var read = Objects.requireNonNull(this.parsedToml.get(t.getKey()), String.format("Reading the value %s returned null.", t.name()));
            this.configValues.put(t, read);
        }
    }

    protected void saveResource() {
        this.fallenSnow.saveResource(this.file.getName(), false);
    }

    public AbstractTomlConfig(@NotNull String fileName, @NotNull FallenSnow fallenSnow, @NotNull Class<T> enumClass) {
        this.fallenSnow = fallenSnow;
        this.file = new File(fallenSnow.getDataFolder(), fileName);
        saveResource();
        if (!file.exists() || file.isDirectory() || !file.canRead()) {
            throw new RuntimeException("The file " + fileName + " could not be read!");
        }
        try {
            this.parsedToml = Toml.parse(file.toPath());
        } catch (IOException e) {
            fallenSnow.getLogger().warning("Could not get the file " + fileName + " as path.");
        }
        this.enumClass = enumClass;
        this.tValues = ImmutableList.copyOf(enumClass.getEnumConstants());
        this.configValues = new EnumMap<>(enumClass);
        loadAllConfigValues();
    }

    /**
     * Get the value from the configuration using one of its enum values.
     *
     * @param enumEntry  The enum that represents the config field.
     * @param returnType The type of data that should be returned by this config
     *                   (note that you should never use primitive classes!)
     * @param <S>        The type of return.
     * @throws IllegalArgumentException If the cast is unsuccessful.
     * @return The value obtained from the config cast to the return type
     */
    @Nullable
    public final <S> S getConfigValue(@NotNull T enumEntry, @NotNull Class<? extends S> returnType) {
        if (this.configValues.containsKey(enumEntry)) {
            var obtained = this.configValues.get(enumEntry);
            if (returnType.isAssignableFrom(obtained.getClass()))
                return Objects.requireNonNull((S) obtained);
            else
                throw new IllegalArgumentException(String.format("The return type for %s inside config %s was %s but it has been tried to be cast to %s.",
                        enumEntry.getKey(), getClass().getSimpleName(), obtained.getClass().getSimpleName(), returnType.getSimpleName()));
        } else {
            return null;
        }
    }

}
