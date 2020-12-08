package studio.thevipershow.fallensnow.config;

import com.google.common.collect.ImmutableList;
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
public abstract class AbstractTomlConfig<T extends Enum<T> & SectionKey & SectionReturnType> {

    protected final File file;
    protected final TomlParseResult parsedToml;
    protected final FallenSnow fallenSnow;
    protected final Class<? extends T> enumClass;
    protected final List<T> tValues;
    protected final EnumMap<T, Object> configValues;

    protected void loadAllConfigValues() {
        tValues.forEach(v -> configValues.put(v, parsedToml.get(v.getKey())));
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
        this.parsedToml = Toml.parse(file.getPath());
        this.enumClass = enumClass;
        this.tValues = ImmutableList.copyOf(enumClass.getEnumConstants());
        this.configValues = new EnumMap<>(enumClass);
        loadAllConfigValues();
    }

    @Nullable
    public final <S> S getConfigValue(@NotNull T enumEntry, @NotNull Class<S> returnType) {
        if (this.configValues.containsKey(enumEntry)) {
            return (S) this.configValues.get(enumEntry);
        } else {
            return null;
        }
    }

}
