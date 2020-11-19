package me.thevipershow.fallensnow;

import java.util.Objects;
import me.thevipershow.fallensnow.command.FallenSnowCommand;
import me.thevipershow.fallensnow.config.Values;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class FallenSnow extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        final Values values = Values.getInstance(this);
        values.updateAll();
        final ParticleManager particleManager = ParticleManager.getInstance(this, values);
        Bukkit.getWorlds().forEach(w -> particleManager.getEnabledWorlds().put(w.getUID(), true));
        particleManager.startAnimation();
        final FallenSnowCommand command = new FallenSnowCommand(this, values, particleManager);
        Objects.requireNonNull(Bukkit.getPluginCommand("fsnow")).setExecutor(command);
    }

}
