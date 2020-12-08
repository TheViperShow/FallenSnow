package studio.thevipershow.fallensnow;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import studio.thevipershow.fallensnow.config.ConfigurationManager;
import studio.thevipershow.fallensnow.telemetry.AbstractTelemetry;
import studio.thevipershow.fallensnow.telemetry.BStatsChartGenerator;
import studio.thevipershow.fallensnow.telemetry.FallenSnowTelemetry;
import studio.thevipershow.fallensnow.worlds.AbstractWorldsHolder;
import studio.thevipershow.fallensnow.worlds.FallenSnowAutomaticWorldRemover;
import studio.thevipershow.fallensnow.worlds.FallenSnowWorldsHolder;

@Getter
public final class FallenSnow extends JavaPlugin {

    private ConfigurationManager configurationManager;
    private AbstractTelemetry<BStatsChartGenerator, FallenSnow> telemetry;
    private AbstractWorldsHolder<FallenSnow, FallenSnowAutomaticWorldRemover> worldsHolder;

    @Override
    public void onEnable() {
        configurationManager = ConfigurationManager.getInstance(this);
        configurationManager.loadAllConfigs();

        worldsHolder = new FallenSnowWorldsHolder(this);
        worldsHolder.addAllLoaded(this);
        worldsHolder.removeUndesiredWorlds();

        telemetry = new FallenSnowTelemetry(this);
        telemetry.startTelemetry();
    }

}
