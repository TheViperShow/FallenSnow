package me.thevipershow.fallensnow.config;

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
    private int aboveY;

    public void updateAll() {
        plugin.reloadConfig();
        final FileConfiguration config = plugin.getConfig();
        particle = config.getString("snow.particle");
        particleType = Particle.valueOf(particle.toUpperCase());
        range = config.getDouble("snow.range");
        speed = config.getDouble("snow.speed");
        amount = config.getInt("snow.amount");
        spawnrate = config.getDouble("snow.spawnrate");
        snowOnTop = config.getBoolean("snow.snow-only-on-top");
        aboveEnabled = config.getBoolean("snow.other-settings.above");
        aboveY = config.getInt("snow.other-settings.y");
    }

    public String getParticle() {
        return particle;
    }

    public Particle getParticleType() {
        return particleType;
    }

    public double getRange() {
        return range;
    }

    public double getSpeed() {
        return speed;
    }

    public int getAmount() {
        return amount;
    }

    public double getSpawnrate() {
        return spawnrate;
    }

    public boolean isSnowOnTop() {
        return snowOnTop;
    }

    public boolean isAboveEnabled() {
        return aboveEnabled;
    }

    public int getAboveY() {
        return aboveY;
    }
}
