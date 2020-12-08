package studio.thevipershow.fallensnow;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import studio.thevipershow.fallensnow.config.ConfigurationManager;
import studio.thevipershow.fallensnow.telemetry.AbstractTelemetry;
import studio.thevipershow.fallensnow.telemetry.BStatsChartGenerator;
import studio.thevipershow.fallensnow.telemetry.FallenSnowTelemetry;

@Getter
public final class FallenSnow extends JavaPlugin {

    private ConfigurationManager configurationManager;
    private AbstractTelemetry<BStatsChartGenerator, FallenSnow> telemetry;

    @Override
    public void onEnable() {
        configurationManager = ConfigurationManager.getInstance(this);
        configurationManager.loadAllConfigs();

        telemetry = new FallenSnowTelemetry(this);
        telemetry.startTelemetry();
    }

}
