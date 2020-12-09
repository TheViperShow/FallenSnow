package studio.thevipershow.fallensnow.telemetry;

import org.bstats.bukkit.Metrics;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface MetricsHolder {

    /**
     * Get the Metrics for telemetry.
     * @return The Metrics.
     */
    @NotNull
    Metrics getMetrics();
}
