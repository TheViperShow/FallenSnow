package me.thevipershow.fallensnow.command;

import me.thevipershow.fallensnow.ParticleManager;
import me.thevipershow.fallensnow.config.Values;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public final class FallenSnowCommand implements CommandExecutor {

    private final Plugin plugin;
    private final Values values;
    private final ParticleManager particleManager;

    public FallenSnowCommand(final Plugin plugin, final Values values, final ParticleManager particleManager) {
        this.plugin = plugin;
        this.values = values;
        this.particleManager = particleManager;
    }

    private static String color(final String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    private static long time() {return System.currentTimeMillis();}

    private static void help(final CommandSender s) {
        s.sendMessage(color("&8[&bFallenSnow&8]&7: help page"));
        s.sendMessage(color("&7&b/fsnow enable <worldname>"));
        s.sendMessage(color("&7&b/fsnow disable <worldname>"));
        s.sendMessage(color("&7&b/fsnow reload"));
    }

    private static void reload(final CommandSender s, final Values values) {
        s.sendMessage(color("&8[&bFallenSnow&8]&7: reloading config.yml"));
        final long then = time();
        values.updateAll();
        s.sendMessage(color(String.format("&7Reloaded plugin in %d(ms).", time() - then)));
    }

    private enum Toggle {
        ENABLE(true), DISABLE(false);

        private final boolean b;

        Toggle(final boolean b) {
            this.b = b;
        }

        public boolean isB() {
            return b;
        }
    }

    public final void toggleWorld(final String toggle , final String worldName, final CommandSender sender) {
        for (final World world : Bukkit.getWorlds()) {
            if (world.getName().equals(worldName)) {
                particleManager.getEnabledWorlds().put(world, Toggle.valueOf(toggle).isB());
                sender.sendMessage(color("&8[&bFallenSnow&8]&7: successfully changed animation status."));
                break;
            }
        }
        sender.sendMessage(color("&8[&bFallenSnow&8]&7: &cno world with this name exists."));
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        final int count = args.length;
        boolean success = false;
        if (count == 0) {
            help(sender);
            success = true;
        } else if (count == 1) {
            final String firstArg = args[0].toUpperCase();
            if (firstArg.equals("RELOAD")) {
                reload(sender, values);
                success = true;
            }
        } else if (count == 2) {
            final String firstArg = args[0].toUpperCase();
            final String secondArg = args[1];
            if (firstArg.equals("ENABLE")) {
                toggleWorld(firstArg, secondArg, sender);
                success = true;
            } else if (firstArg.equals("DISABLE")) {
                toggleWorld(firstArg, secondArg, sender);
                success = true;
            }
        }
        return success;
    }
}
