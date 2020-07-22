package me.thevipershow.fallensnow;

import me.thevipershow.fallensnow.config.Values;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class FallenSnow extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        final Values values = Values.getInstance(this);
        final ParticleManager particleManager = ParticleManager.getInstance(this, values);
        Bukkit.getWorlds().forEach(w -> particleManager.getEnabledWorlds().put(w, true));
    }
}
