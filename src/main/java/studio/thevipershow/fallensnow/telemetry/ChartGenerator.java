package studio.thevipershow.fallensnow.telemetry;

import lombok.RequiredArgsConstructor;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
public abstract class ChartGenerator implements MetricsHolder, CustomChartGenerator {

    private final Metrics metrics;
    private final Plugin plugin;

    /**
     * Get the Metrics for telemetry.
     * @return The Metrics.
     */
    @NotNull
    @Override
    public final Metrics getMetrics() {
        return this.metrics;
    }
}
