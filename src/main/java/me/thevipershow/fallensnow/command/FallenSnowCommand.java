package me.thevipershow.fallensnow.command;

import java.util.HashMap;
import java.util.UUID;
import me.thevipershow.fallensnow.ParticleManager;
import me.thevipershow.fallensnow.config.Values;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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
        s.sendMessage(color("  &3| &7&b/fsnow enable <worldname>"));
        s.sendMessage(color("  &3| &7&b/fsnow disable <worldname>"));
        s.sendMessage(color("  &3| &7&b/fsnow toggle"));
        s.sendMessage(color("  &3| &7&b/fsnow reload"));
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
                particleManager.getEnabledWorlds().put(world.getUID(), Toggle.valueOf(toggle).isB());
                sender.sendMessage(color("&8[&bFallenSnow&8]&7: successfully changed animation status."));
                return;
            }
        }
        sender.sendMessage(color("&8[&bFallenSnow&8]&7: &cno world with this name exists."));
    }

    private final void toggle(CommandSender commandSender) {
        if (commandSender instanceof Player) {
            HashMap<UUID, Boolean> toggleMap = particleManager.getToggleSettings();
            UUID uuid = ((Player) commandSender).getUniqueId();
            Boolean value = toggleMap.get(uuid);
            if (value == null) {
                toggleMap.put(uuid, false);
                commandSender.sendMessage(color("&8[&bFallenSnow&8]&7: you have update your toggle status to &bfalse"));
            } else {
                final boolean inverted = !value;
                toggleMap.put(uuid, inverted);
                commandSender.sendMessage(color("&8[&bFallenSnow&8]&7: you have update your toggle status to &b" + inverted));
            }
        } else {
            commandSender.sendMessage(color("&8[&bFallenSnow&8]&7: &cyou need to be a player to do this."));
        }
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
                if (sender.hasPermission("fallen-snow.admin")) {
                    reload(sender, values);
                    success = true;
                } else {
                    sender.sendMessage(color("&8[&bFallenSnow&8]&7: &cmissing \"fallen-snow.admin\" permission"));
                }
            } else if (firstArg.equals("TOGGLE")) {
                toggle(sender);
                success = true;
            }
        } else if (count == 2) {
            final String firstArg = args[0].toUpperCase();
            final String secondArg = args[1];
            if (firstArg.equals("ENABLE")) {
                if (sender.hasPermission("fallen-snow.admin")) {
                    toggleWorld(firstArg, secondArg, sender);
                    success = true;
                } else {
                    sender.sendMessage(color("&8[&bFallenSnow&8]&7: &cmissing \"fallen-snow.admin\" permission"));
                }
            } else if (firstArg.equals("DISABLE")) {
                if (sender.hasPermission("fallen-snow.admin")) {
                    toggleWorld(firstArg, secondArg, sender);
                    success = true;
                } else {
                    sender.sendMessage(color("&8[&bFallenSnow&8]&7: &cmissing \"fallen-snow.admin\" permission"));
                }
            }
        }
        return success;
    }
}
