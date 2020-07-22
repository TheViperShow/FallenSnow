package me.thevipershow.fallensnow;

import me.thevipershow.fallensnow.config.Values;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

public final class ParticleManager {
    private static ParticleManager instance = null;

    private final Plugin plugin;
    private final Values values;

    private ParticleManager(Plugin plugin, Values values) {
        this.plugin = plugin;
        this.values = values;
    }

    public static ParticleManager getInstance(final Plugin plugin, final Values values) {
        if (instance == null)
            instance = new ParticleManager(plugin, values);
        return instance;
    }

    private final HashMap<World, Boolean> enabledWorlds = new HashMap<>();

    public final HashMap<World, Boolean> getEnabledWorlds() {
        return enabledWorlds;
    }

    private static void doSnow(final Player player, final Particle particle, final double range, final double speed, final int amount, final World world) {
        double x = player.getLocation().getX();
        double y = player.getLocation().getY();
        double z = player.getLocation().getZ();
        for (int k = 0; k <= amount; k++)
            world.spawnParticle(particle, x, y, z, 1, range, range, range, speed, null);
    }

    public final void startAnimation() {
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            Bukkit.getWorlds().forEach(w -> {
                if (!enabledWorlds.containsKey(w)) return;
                if (!enabledWorlds.get(w)) return;
                Bukkit.getOnlinePlayers().stream()
                        .filter(p -> p.hasPermission("fallen-snow.disabled"))
                        .forEach(p -> {
                            final Location location = p.getLocation();
                            final int y = location.getBlockY();
                            if (values.isSnowOnTop() && y < w.getHighestBlockYAt(location)) return;
                            if (values.isAboveEnabled()) {
                                if (y < values.getAboveY()) return;
                                doSnow(p, values.getParticleType(), values.getRange(), values.getSpeed(), values.getAmount(), w);
                                return;
                            }
                            doSnow(p, values.getParticleType(), values.getRange(), values.getSpeed(), values.getAmount(), w);
                        });
            });
        }, 60L, (long) (20L * values.getSpawnrate()));
    }
}
