package studio.thevipershow.fallensnow;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import studio.thevipershow.fallensnow.config.ConfigurationManager;

@Getter
public final class FallenSnow extends JavaPlugin {

    private ConfigurationManager configurationManager;

    @Override
    public void onEnable() {
        configurationManager = ConfigurationManager.getInstance(this);
        configurationManager.loadAllConfigs();
    }

}
