package studio.thevipershow.fallensnow.telemetry;

import lombok.Getter;
import lombok.val;
import org.bstats.bukkit.Metrics;
import org.jetbrains.annotations.NotNull;
import studio.thevipershow.fallensnow.FallenSnow;
import studio.thevipershow.fallensnow.config.ConfigTypes;
import studio.thevipershow.fallensnow.config.general.GeneralTomlConfig;
import studio.thevipershow.fallensnow.config.general.GeneralValues;

@Getter
public final class FallenSnowTelemetry extends AbstractTelemetry<BStatsChartGenerator, FallenSnow> {

    private static FallenSnowTelemetry instance = null;

    public FallenSnowTelemetry(@NotNull FallenSnow fallenSnow) {
        super(fallenSnow, 9590);
    }

    /**
     * Get the telemetry instance singleton.
     * @param fallenSnow The FallenSnow instance.
     * @return The FallenSnowTelemetry singleton.
     */
    public static synchronized FallenSnowTelemetry getInstance(@NotNull FallenSnow fallenSnow) {
        if (instance == null) {
            instance = new FallenSnowTelemetry(fallenSnow);
        }
        return instance;
    }

    /**
     * Start the telemetry service.
     */
    public final void startTelemetry() {
        if (isTelemetryStarted) {
            throw new UnsupportedOperationException("The plugin tried to start telemetry twice!" +
                    "\nThis may be caused by \"/reload\" or PlugMan usage, please STOP using such.");
        }

        val metrics = new Metrics(plugin, metricsID);
        this.chartGenerator = new BStatsChartGenerator(metrics, plugin);
        this.chartGenerator.generateCustomCharts();
        setTelemetryStarted(true);
        plugin.getLogger().info("Telemetry for this plugin has been enabled correctly.");
    }

    /**
     * Get the status of the telemetry service.
     * @return The telemetry status.
     */
    public final boolean isTelemetryEnabled() {
        GeneralTomlConfig generalConfig = plugin.getConfigurationManager().getConfig(ConfigTypes.GENERAL_CONFIG);
        val isTelemetryEnabled = generalConfig.getConfigValue(GeneralValues.TELEMETRY_ENABLED, Boolean.class);
        return isTelemetryEnabled == null || isTelemetryEnabled;
    }
}
