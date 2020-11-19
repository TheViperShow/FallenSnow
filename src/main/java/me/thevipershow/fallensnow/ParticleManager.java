package me.thevipershow.fallensnow;

import me.thevipershow.fallensnow.config.Values;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.UUID;
import org.bukkit.scheduler.BukkitTask;

public final class ParticleManager {
    private static ParticleManager instance = null;

    private final Plugin plugin;
    private final Values values;
    private BukkitTask runningTask = null;
    private final HashMap<UUID, Boolean> toggleSettings = new HashMap<>();

    public final HashMap<UUID, Boolean> getToggleSettings() {
        return toggleSettings;
    }

    public final BukkitTask getRunningTask() {
        return runningTask;
    }

    private ParticleManager(Plugin plugin, Values values) {
        this.plugin = plugin;
        this.values = values;
    }

    public static ParticleManager getInstance(final Plugin plugin, final Values values) {
        if (instance == null)
            instance = new ParticleManager(plugin, values);
        return instance;
    }

    private final HashMap<UUID, Boolean> enabledWorlds = new HashMap<>();

    public final HashMap<UUID, Boolean> getEnabledWorlds() {
        return enabledWorlds;
    }

    private static void doSnow(final Player player, final Particle particle, final double range, final double speed, final int amount) {
        double x = player.getLocation().getX();
        double y = player.getLocation().getY();
        double z = player.getLocation().getZ();
        for (int k = 0; k <= amount; k++) {
            player.spawnParticle(particle, x, y, z, 1, range, range, range, speed, null);
        }
    }

    public final void startAnimation() {
        if (runningTask != null) {
            runningTask.cancel();
        } else {
            runningTask = plugin.getServer().getScheduler().runTaskTimer(plugin, () -> {
                final Server server = plugin.getServer();
                for (final World world : server.getWorlds()) {
                    if (!values.isSpecifyWorlds() || values.getWorldNames().contains(world.getName())) {

                        if ((world.isThundering() || world.hasStorm()) && !values.isSnowWhenRaining()) {
                            return;
                        }

                        if (!enabledWorlds.containsKey(world.getUID())) {
                            return;
                        }
                        if (!enabledWorlds.get(world.getUID())) {
                            return;
                        }

                        for (final Player onlinePlayer : world.getPlayers()) {

                            final Location playerLocation = onlinePlayer.getLocation();



                            UUID playerUUID = onlinePlayer.getUniqueId();
                            Boolean toggleSetting = this.toggleSettings.get(playerUUID);
                            if (toggleSetting != null && !toggleSetting) {
                                return;
                            }
                            if (onlinePlayer.hasPermission("fallen-snow.enable")) {
                                int y = playerLocation.getBlockY();
                                if (values.isSnowOnTop() && y < world.getHighestBlockYAt(playerLocation)) {
                                    return;
                                }
                                if (values.isAboveEnabled()) {
                                    if (y < values.getAboveY()) {
                                        return;
                                    }

                                }
                                doSnow(onlinePlayer, values.getParticleType(), values.getRange(), values.getSpeed(), values.getAmount());
                            }
                        }
                    }
                }
            }, 60L, (long) (20L * values.getSpawnrate()));
        }
    }
}
