package studio.thevipershow.fallensnow.worlds;

import org.bukkit.World;
import org.jetbrains.annotations.NotNull;
import org.tomlj.TomlArray;
import studio.thevipershow.fallensnow.FallenSnow;
import studio.thevipershow.fallensnow.config.ConfigTypes;
import studio.thevipershow.fallensnow.config.snow.SnowTomlConfig;
import studio.thevipershow.fallensnow.config.snow.SnowValues;

public final class FallenSnowWorldsHolder extends AbstractWorldsHolder<FallenSnow, FallenSnowAutomaticWorldRemover> {

    public FallenSnowWorldsHolder(@NotNull FallenSnow plugin) {
        super(plugin);
        setWorldRemover(new FallenSnowAutomaticWorldRemover(plugin, this));
    }


    /**
     * Remove undesired worlds.
     */
    @Override
    public final void removeUndesiredWorlds() {
        var configManager = plugin.getConfigurationManager();
        SnowTomlConfig snowConfig = configManager.getConfig(ConfigTypes.SNOW_CONFIG);
        if (snowConfig == null) {
            throw new RuntimeException("TOML snow config was invalid!");
        }
        var specifyWorlds = snowConfig.getConfigValue(SnowValues.SPECIFY_ENABLED_WORLDS, Boolean.class);
        if (specifyWorlds == null || !specifyWorlds) {
            return;
        }
        var enabledWorlds = snowConfig.getConfigValue(SnowValues.ENABLED_WORLDS, TomlArray.class);
        if (enabledWorlds == null) {
            throw new RuntimeException("The enabled worlds section was not set!");
        }
        for (int i = 0; i < enabledWorlds.size(); i++) {
            String worldName = enabledWorlds.getString(i);
            World world = plugin.getServer().getWorld(worldName);
            if (world == null) {
                plugin.getLogger().info(String.format("The world specified in the config with name \"%s\" does not seem to be present.", worldName));
                continue;
            }
            addWorld(world);
        }
    }
}
