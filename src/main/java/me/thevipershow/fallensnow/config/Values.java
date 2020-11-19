package me.thevipershow.fallensnow.config;

import java.util.List;
import java.util.Locale;
import org.bukkit.Particle;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public final class Values {
    private static Values instance = null;

    private final Plugin plugin;

    private Values(final Plugin plugin) {
        this.plugin = plugin;
    }

    public static Values getInstance(final Plugin plugin) {
        if (instance == null)
            instance = new Values(plugin);
        return instance;
    }

    private String particle;
    private Particle particleType;
    private double range;
    private double speed;
    private int amount;
    private double spawnrate;
    private boolean snowOnTop;
    private boolean aboveEnabled;
    private boolean specifyWorlds;
    private boolean snowWhenRaining;
    private List<String> worldNames;
    private int aboveY;

    public final void updateAll() {
        plugin.reloadConfig();
        final FileConfiguration config = plugin.getConfig();
        particle = config.getString("snow.particle");
        particleType = Particle.valueOf(particle.toUpperCase(Locale.ROOT));
        range = config.getDouble("snow.range");
        speed = config.getDouble("snow.speed");
        amount = config.getInt("snow.amount");
        spawnrate = config.getDouble("snow.spawnrate");
        snowOnTop = config.getBoolean("snow.snow-only-on-top");
        aboveEnabled = config.getBoolean("snow.other-settings.above");
        aboveY = config.getInt("snow.other-settings.y");
        specifyWorlds = config.getBoolean("specify-enabled-worlds");
        worldNames = config.getStringList("enabled-world-names");
        snowOnTop = config.getBoolean("snow-when-raining");
    }

    public final boolean isSnowWhenRaining() {
        return snowWhenRaining;
    }

    public final boolean isSpecifyWorlds() {
        return specifyWorlds;
    }

    public final List<String> getWorldNames() {
        return worldNames;
    }

    public final String getParticle() {
        return particle;
    }

    public final Particle getParticleType() {
        return particleType;
    }

    public final double getRange() {
        return range;
    }

    public final double getSpeed() {
        return speed;
    }

    public final int getAmount() {
        return amount;
    }

    public final double getSpawnrate() {
        return spawnrate;
    }

    public final boolean isSnowOnTop() {
        return snowOnTop;
    }

    public final boolean isAboveEnabled() {
        return aboveEnabled;
    }

    public final int getAboveY() {
        return aboveY;
    }
}
