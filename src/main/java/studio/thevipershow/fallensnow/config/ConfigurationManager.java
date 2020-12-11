package studio.thevipershow.fallensnow.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import studio.thevipershow.fallensnow.FallenSnow;

import java.lang.reflect.InvocationTargetException;
import java.util.EnumMap;
import java.util.Objects;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConfigurationManager {

    private final FallenSnow fallenSnow;
    private static ConfigurationManager instance = null;

    public synchronized static ConfigurationManager getInstance(@NotNull FallenSnow fallenSnow) {
        if (instance == null) {
            instance = new ConfigurationManager(Objects.requireNonNull(fallenSnow, "The plugin instance was null!"));
        }
        return instance;
    }

    @Getter
    private final EnumMap<ConfigTypes, AbstractTomlConfig<?>> loadedTomlConfigs = new EnumMap<>(ConfigTypes.class);

    public final void loadAllConfigs() {
        var logger = fallenSnow.getLogger();
        for (ConfigTypes configTypes : ConfigTypes.values()) {
            var start = System.currentTimeMillis();
            var configName = configTypes.name();

            logger.info("Loading config " + configName);

            try {
                this.loadedTomlConfigs.put(configTypes, configTypes.getTomlConfigClass().getConstructor(FallenSnow.class).newInstance(fallenSnow));
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                logger.warning("Could not load config: " + configName);
                e.printStackTrace();
            }

            logger.info(String.format("Finished loading config in %d milliseconds.", System.currentTimeMillis() - start));
        }
    }

    @Nullable
    public final <T extends AbstractTomlConfig<?>> T getConfig(@NotNull ConfigTypes configType) {
        return (T) this.loadedTomlConfigs.get(configType);
    }
}
