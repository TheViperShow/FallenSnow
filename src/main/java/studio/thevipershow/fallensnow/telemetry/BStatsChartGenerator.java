package studio.thevipershow.fallensnow.telemetry;

import org.bstats.bukkit.Metrics;
import org.jetbrains.annotations.NotNull;
import studio.thevipershow.fallensnow.FallenSnow;

public final class BStatsChartGenerator extends ChartGenerator {

    public BStatsChartGenerator(@NotNull Metrics metrics, @NotNull FallenSnow plugin) {
        super(metrics, plugin);
    }

    @Override
    public final void generateCustomCharts() {

    }
}
